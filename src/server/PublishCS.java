package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

public class PublishCS {

    public final static String[] clinicServers = {"MTL", "LVL", "DDO"};

    public String name, managerID;
    public RecordContainer records;

    /**
     * Start all of the servers
     *
     * @param args
     */
    public static void main(String[] args) {

        // Start a thread of each station
        SchoolServerImpl clinicMTL = new SchoolServerImpl();
        clinicMTL.setName("MTL");
        SchoolServerImpl clinicLVL = new SchoolServerImpl();
        clinicLVL.setName("LVL");
        SchoolServerImpl clinicDDO = new SchoolServerImpl();
        clinicDDO.setName("DDO");
        Endpoint epMTL = Endpoint.publish("http://localhost:4010/clinic", clinicMTL);
        System.out.println("epMTL is running: " + epMTL.isPublished());
        Endpoint epLVL = Endpoint.publish("http://localhost:4020/clinic", clinicLVL);
        System.out.println("epLVL is running: " + epLVL.isPublished());
        Endpoint epDDO = Endpoint.publish("http://localhost:4030/clinic", clinicDDO);
        System.out.println("epDDO is running: " + epDDO.isPublished());
        //for (String clinicServer : clinicServers) {

        clinicMTL.startServerThreads();
        clinicLVL.startServerThreads();
        clinicDDO.startServerThreads();

        // }

//		Endpoint endPoint = Endpoint.publish("http://localhost:7777/clinic", new SchoolServerImpl());
//		System.out.println(endPoint.isPublished());
    }

    /**
     * Creates Logs
     **/
    public static void logFile(String fileName, String Operation)
            throws SecurityException {
        // System.out.println("loggin file");
        fileName = fileName + "_ServerLog.txt";
        File log = new File(fileName);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            if (!log.exists()) {
            }
            log.setWritable(true);
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Operation + " "
                    + dateFormat.format(date));
            bufferedWriter.newLine();
            bufferedWriter.close();
            // System.out.println("logged file");
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }


    protected void startServerThreads() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                exportUDP();
            }
        }).start();

    }

    /**
     * Setting up the UDP connection
     */
    private void exportUDP() {
        int recvPort = portHash(name);
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(recvPort);
        } catch (SocketException ex) {
            System.out.println("UDP server could not be started, UDP will be unavailable");
            return;
        }
        System.out.println("UDP for " + name + " on " + portHash(name));
        // wait for packets
        byte[] buffer = new byte[1500];
        while (true) {
            DatagramPacket recvPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(recvPacket);

                InetAddress sendAddr = recvPacket.getAddress();
                int sendPort = recvPacket.getPort();
                String requestString = new String(buffer).substring(0, recvPacket.getLength());

                Map<String, String> request = MapSerializer.parse(requestString);
                Map<String, String> response = handleRequest(request);
                String responseString = MapSerializer.stringify(response);

                DatagramPacket sendPacket = new DatagramPacket(responseString.getBytes(), responseString.length(), sendAddr, sendPort);
                socket.send(sendPacket);

            } catch (IOException ex) {
                logFile(name, ex.toString() + " " + ex.getMessage());
            }
        }
    }

    /**
     * Take a UDP request map and return a response map
     *
     * @param request
     * @return
     */
    private Map<String, String> handleRequest(Map<String, String> request) {
        String action = request.get("action");
        HashMap<String, String> response = new HashMap<String, String>();

        if (action == null) {
            action = null;
        }
        if ("recordCount".equals(action)) {
            int count = records.getRecordCount();
            response.put("recordCount", Integer.toString(count));
            return response;
        }
        if ("transfer".equals(action)) {
            String newid = records.getNextFreeId();
            request.put("id", newid);
            Record record = RecordFactory.createRecordFromMap(request);
            response.put("status", "success");
            records.addRecord(record);
            response.put("id", record.getId());
            return response;
        }

        response.put("error", "invalid Action");

        return response;
    }

    /**
     * Given a serverName, determine which port its UDP server is on
     *
     * @param string clinicName
     * @return port
     */
    public static int portHash(String string) {
        int bucket = 0;
        for (char ch : string.toCharArray()) {
            // totally awkward mixing function
            bucket = (bucket * 94 + ch - 33) % (94 * 94 - 36);
        }
        // make sure the port number is not restricted by adding the first
        // non restricted port
        return bucket + 1024;
    }

    /**
     * Getter of the name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the name
     *
     * @return
     */
    public RecordContainer getRecords() {
        return records;
    }

    /**
     * Setter of the name
     *
     * @param records
     */
    public void setRecords(RecordContainer records) {
        this.records = records;
    }

}
