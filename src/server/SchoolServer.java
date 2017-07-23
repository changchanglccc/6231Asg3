package server;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface SchoolServer {

    /**
     * Operation createTRecord
     */
    @WebMethod
    public String createTRecord(String managerID, String firstName, String lastName, String address, String phone, String specialization, String location);

    /**
     * Operation createSRecord
     */
    @WebMethod
    public String createSRecord(String managerID, String firstName, String lastName, String designation, String status, String statusDate);

    /**
     * Operation getRecordCounts
     */
    @WebMethod
    public String getRecordCounts(String managerID);

    /**
     * Operation editRecord
     */
    @WebMethod
    public String editRecord(String managerID, String recordID, String fieldName, String newValue);

    /**
     * Operation transferRecord
     */
    @WebMethod
    public String transferRecord(String managerID, String recordID, String remoteClinicServerName);

}
