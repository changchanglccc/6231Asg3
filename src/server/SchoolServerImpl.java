package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

import javax.jws.WebService;


@WebService(endpointInterface = "server.SchoolServer")
public class SchoolServerImpl extends PublishCS implements SchoolServer {


    public SchoolServerImpl() {
        super();
        name = "";
        records = RecordContainer.getRecordContainer(name);

        logFile(name, "Server " + name + "is running");
    }


    @Override
    public String createTRecord(String managerID, String firstName, String lastName, String address, String phone,
                                String specialization, String location) {
        TeacherRecord record = new TeacherRecord(records.getNextFreeId(), firstName, lastName, address, phone, specialization, location);
        String id = record.getId();
        records.addRecord(record);
        logFile(this.name, " Create Teacher Record: " + id + "\n" + record.toString());
        return id + "\n" + record.toString();

    }

    @Override
    public String createSRecord(String managerID, String firstName, String lastName, String designation, String status,
                                String statusDate) {

        StudentRecord record = new StudentRecord(records.getNextFreeId(), firstName, lastName, designation, status, statusDate);
        String id = record.getId();
        records.addRecord(record);
        logFile(this.name, " Create Student Record: " + id + "\n" + record.toString());
        return id + "\n" + record.toString();

    }

    @Override
    public String getRecordCounts(String managerID) {

        String counts = "";
        for (String schoolName : schoolServers) {
            try {
                // only kind of request but send it anyways
                String request = "action:recordCount";

                DatagramSocket socket = new DatagramSocket();
                InetAddress addr = InetAddress.getByName("localhost");
                int port = PublishCS.portHash(schoolName);
                DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), addr, port);
                socket.send(packet);
                byte[] buffer = new byte[1500];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // 
                String response = new String(buffer, 0, packet.getLength());

                System.out.println(response);

                if (response.startsWith("recordCount:")) {
                    String count = response.substring(response.indexOf(":") + 1);
                    counts += schoolName + " : " + count + ", ";
                } else {
                    counts += schoolName + " responded badly, ";
                }

                socket.close();
            } catch (SocketException ex) {
                throw new RuntimeException(ex);
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                // station unavailible
                throw new RuntimeException(ex);
            }
        }
        logFile(this.name, "Get the record count --" + "\n" + counts);
        return counts;

    }

    @Override
    public String editRecord(String managerID, String recordID, String fieldName, String newValue) {

        if (recordID.contains("TR")) {
            Record trecord = records.getRecord(recordID);
            if (trecord != null) {
                synchronized (trecord) {
                    if (fieldName.equalsIgnoreCase("address")) {
                        trecord.setAddress(newValue);
                    } else if (fieldName.equalsIgnoreCase("phone")) {
                        trecord.setPhone(newValue);
                    } else if (fieldName.equalsIgnoreCase("specialization")) {
                        trecord.setSpecialization(newValue);
                    } else if (fieldName.equalsIgnoreCase("location")) {
                        trecord.setLocation(newValue);
                    }
                }

            }
        } else if (recordID.contains("SR")) {
            Record srecord = records.getRecord(recordID);
            if (srecord != null) {
                synchronized (srecord) {
                    if (fieldName.equalsIgnoreCase("designation")) {
                        srecord.setDesignation(newValue);
                    } else if (fieldName.equalsIgnoreCase("status")) {
                        srecord.setStatus(newValue);
                        ;
                    } else if (fieldName.equalsIgnoreCase("statusDate")) {
                        srecord.setStatusDate(newValue);
                    }
                }
            }
        }
        logFile(this.name, "Manger has edit the " + fieldName + " of " + recordID + " to new value: " + newValue);
        return "Manger has edit the " + fieldName + " of " + recordID + " to new value: " + newValue;

    }

    @Override
    public String transferRecord(String managerID, String recordID, String remoteSchoolServerName) {

        if (!Arrays.asList(schoolServers).contains(remoteSchoolServerName)) {
            //logFile(this.name, remoteSchoolServerName + " server is not in list");
            return "_FAIL_";
        }
        try {
            Record record = records.getRecord(recordID);
            if (record == null) {
                //logFile(this.name, "  transferRecord <FAILED> -- record not found");
                return "_FAIL_";
            }
            Map<String, String> request = RecordFactory.createMapFromRecord(record);

            request.put("action", "transfer");

            DatagramSocket socket = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("localhost");

            String requeststr = MapSerializer.stringify(request);

            int port = PublishCS.portHash(remoteSchoolServerName);

            DatagramPacket packet = new DatagramPacket(requeststr.getBytes(), requeststr.length(), addr, port);
            socket.send(packet);

            byte[] buffer = new byte[1500];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            Map<String, String> response = MapSerializer.parse(new String(buffer, 0, packet.getLength()));

            String status = response.get("status");

            if ("success".equals(status)) {
                records.removeRecord(record.getId(), record.getLastName());
                logFile(this.name, " TransferRecord Success : the Record - " + recordID + "has been transferred to " + remoteSchoolServerName);
            } else {
                logFile(this.name, " transferRecord <FAIL> ");
            }

            socket.close();

            if (response.containsKey("id")) {
                return response.get("id") + " has been transferred to " + remoteSchoolServerName; // return new id
            }
            return "_FAIL_";
        } catch (Exception ex) {
            logFile(this.name, " transferRecord <FAILED> connection failed");
            return "_FAIL_";
        }
    }


}
