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

import clientDDO.*;

public class Client_Configure {

    String managerID;
    String clinicServer;

    clientMTL.SchoolServer ClinicMTL;
    clientLVL.SchoolServer ClinicLVL;
    SchoolServer ClinicDDO;

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
            System.out.println("COULD NOT LOG!!");
        }
    }

    public Client_Configure(String managerID) {
        String pattern = "^(MTL|LVL|DDO)(\\d{5})$";
        Pattern re = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = re.matcher(managerID);
        if (matcher.find()) {
            throw new RuntimeException("Bad Manager ID");
        }
        this.managerID = managerID;
        this.clinicServer = managerID.substring(0, 3);
        logFile(managerID, "Manager - " + managerID + "Log In DSMS system.");
    }


    public void connect() {
        try {
            // ghetto hardcode the parameters

            switch (this.clinicServer) {
                case "MTL":
                    clientMTL.SchoolServerImplService CSIServiceMTL = new clientMTL.SchoolServerImplService();
                    ClinicMTL = CSIServiceMTL.getClinicServerImplPort();
                    break;

                case "LVL":
                    clientLVL.SchoolServerImplService CSIServiceLVL = new clientLVL.SchoolServerImplService();
                    ClinicLVL = CSIServiceLVL.getClinicServerImplPort();
                    break;

                case "DDO":
                    SchoolServerImplService CSIServiceDDO = new SchoolServerImplService();
                    ClinicDDO = CSIServiceDDO.getClinicServerImplPort();
                    break;
            }
            logFile(managerID, "Connected!");
        } catch (Exception ex) {
            logFile(managerID, ex.toString() + ex.getMessage());
        }

    }


    public String createDRecord(String managerID, String firstName, String lastName, String address, String phone,
                                String specialization, String location) {

        logFile(managerID, "Manager choose to create Doctor Record !" + "\n");
        String result = null;

        switch (this.clinicServer) {
            case "MTL":
                result = ClinicMTL.createDRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;

            case "LVL":
                result = ClinicLVL.createDRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;

            case "DDO":
                result = ClinicDDO.createDRecord(managerID, firstName, lastName, address, phone, specialization, location);
                break;
        }

        logFile(managerID, "Manager Create Doctor Record Succeed!" + "\n" + result);
        return result;
    }

    public String createNRecord(String managerID, String firstName, String lastName, String designation, String status,
                                String statusDate) {

        logFile(managerID, "Manager choose to create Nurse Record !" + "\n");
        String result = null;
        switch (this.clinicServer) {
            case "MTL":
                result = ClinicMTL.createNRecord(managerID, firstName, lastName, designation, status, statusDate);
                break;

            case "LVL":
                result = ClinicLVL.createNRecord(managerID, firstName, lastName, designation, status, statusDate);
                break;

            case "DDO":
                result = ClinicDDO.createNRecord(managerID, firstName, lastName, designation, status, statusDate);
                break;
        }
        logFile(managerID, "Manager Create Nurse Record Succeed!" + "\n" + result);
        return result;
    }

    public String getRecordCounts(String managerID) {

        logFile(managerID, "Manager choose to get Record counts !" + "\n");
        String result = null;
        switch (this.clinicServer) {
            case "MTL":
                result = ClinicMTL.getRecordCounts(managerID);
                break;

            case "LVL":
                result = ClinicLVL.getRecordCounts(managerID);
                break;

            case "DDO":
                result = ClinicDDO.getRecordCounts(managerID);
                break;
        }
        logFile(managerID, "The Record counts --" + "\n" + result);
        return result;
    }

    public String editRecord(String managerID, String recordID, String fieldName, String newValue) {

        logFile(managerID, "Manager choose to edit Record !" + "\n");
        String result = null;
        switch (this.clinicServer) {
            case "MTL":
                result = ClinicMTL.editRecord(managerID, recordID, fieldName, newValue);
                break;

            case "LVL":
                result = ClinicLVL.editRecord(managerID, recordID, fieldName, newValue);
                break;

            case "DDO":
                result = ClinicDDO.editRecord(managerID, recordID, fieldName, newValue);
                break;
        }
        return result;
    }

    public String transferRecord(String managerID, String recordID, String remoteClinicLocation) {

        logFile(managerID, "Manager choose to transfer Recrod !" + "\n");
        String result = null;
        switch (this.clinicServer) {
            case "MTL":
                result = ClinicMTL.transferRecord(managerID, recordID, remoteClinicLocation);
                break;

            case "LVL":
                result = ClinicLVL.transferRecord(managerID, recordID, remoteClinicLocation);
                break;

            case "DDO":
                result = ClinicDDO.transferRecord(managerID, recordID, remoteClinicLocation);
                break;
        }
        return result;
    }

}