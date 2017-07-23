package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Client_Configure {

    String managerID;
    String schoolServer;

    clientMTL.SchoolServer SchoolMTL;
    clientLVL.SchoolServer SchoolLVL;
    clientDDO.SchoolServer SchoolDDO;

    /**
     * LOGing is done here
     **/
    public static void logFile(String fileName, String Operation)
            throws SecurityException {
        fileName = fileName + "_Log.txt";
        File log = new File(fileName);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        try {
            log.setWritable(true);
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Operation + " " + dateFormat.format(date));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("CAN NOT LOG!!");
        }
    }

    public Client_Configure(String managerID) {
        String pattern = "^(MTL|LVL|DDO)(\\d{5})$";
        Pattern re = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = re.matcher(managerID);
        if (!matcher.find()) {
            throw new RuntimeException("Bad Manager ID");
        }
        this.managerID = managerID;
        this.schoolServer = managerID.substring(0, 3);
        logFile(managerID, "Manager - " + managerID + "Log In DSMS system.");
    }


    public void connect() {
        try {
            // let's hardcode the parameters

            switch (this.schoolServer) {
                case "MTL":
                    clientMTL.SchoolServerImplService CSIServiceMTL = new clientMTL.SchoolServerImplService();
                    SchoolMTL = CSIServiceMTL.getSchoolServerImplPort();
                    break;

                case "LVL":
                    clientLVL.SchoolServerImplService CSIServiceLVL = new clientLVL.SchoolServerImplService();
                    SchoolLVL = CSIServiceLVL.getSchoolServerImplPort();
                    break;

                case "DDO":
                    clientDDO.SchoolServerImplService CSIServiceDDO = new clientDDO.SchoolServerImplService();
                    SchoolDDO = CSIServiceDDO.getSchoolServerImplPort();
                    break;
            }
            logFile(managerID, "Connected!");
        } catch (Exception ex) {
            logFile(managerID, ex.toString() + ex.getMessage());
        }

    }


    public String createTRecord(String managerID, String firstName, String lastName, String address, String phone,
                                String specialization, String location) {

        logFile(managerID, "Manager choose to create Teacher Record !" + "\n");
        String result = null;

        switch (this.schoolServer) {
            case "MTL":
                result = SchoolMTL.createTRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;

            case "LVL":
                result = SchoolLVL.createTRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;

            case "DDO":
                result = SchoolDDO.createTRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;
        }

        logFile(managerID, "Manager Create Teacher Record Succeed!" + "\n" + result);
        return result;
    }

    public String createSRecord(String managerID, String firstName, String lastName, String courseRegistered, String status,
                                String statusDate) {

        logFile(managerID, "Manager choose to create Student Record !" + "\n");
        String result = null;
        switch (this.schoolServer) {
            case "MTL":
                result = SchoolMTL.createSRecord(managerID, firstName, lastName, courseRegistered, status, statusDate);
                break;

            case "LVL":
                result = SchoolLVL.createSRecord(managerID, firstName, lastName, courseRegistered, status, statusDate);
                break;

            case "DDO":
                result = SchoolDDO.createSRecord(managerID, firstName, lastName, courseRegistered, status, statusDate);
                break;
        }
        logFile(managerID, "Manager Create Student Record Succeed!" + "\n" + result);
        return result;
    }

    public String getRecordCounts(String managerID) {

        logFile(managerID, "Manager choose to get Record counts !" + "\n");
        String result = null;
        switch (this.schoolServer) {
            case "MTL":
                result = SchoolMTL.getRecordCounts(managerID);
                break;

            case "LVL":
                result = SchoolLVL.getRecordCounts(managerID);
                break;

            case "DDO":
                result = SchoolDDO.getRecordCounts(managerID);
                break;
        }
        logFile(managerID, "The Record counts --" + "\n" + result);
        return result;
    }

    public String editRecord(String managerID, String recordID, String fieldName, String newValue) {

        logFile(managerID, "Manager choose to edit Record !" + "\n");
        String result = null;
        switch (this.schoolServer) {
            case "MTL":
                result = SchoolMTL.editRecord(managerID, recordID, fieldName, newValue);
                break;

            case "LVL":
                result = SchoolLVL.editRecord(managerID, recordID, fieldName, newValue);
                break;

            case "DDO":
                result = SchoolDDO.editRecord(managerID, recordID, fieldName, newValue);
                break;
        }
        return result;
    }

    public String transferRecord(String managerID, String recordID, String remoteSchoolLocation) {

        logFile(managerID, "Manager choose to transfer Recrod !" + "\n");
        String result = null;
        switch (this.schoolServer) {
            case "MTL":
                result = SchoolMTL.transferRecord(managerID, recordID, remoteSchoolLocation);
                break;

            case "LVL":
                result = SchoolLVL.transferRecord(managerID, recordID, remoteSchoolLocation);
                break;

            case "DDO":
                result = SchoolDDO.transferRecord(managerID, recordID, remoteSchoolLocation);
                break;
        }
        return result;
    }

}