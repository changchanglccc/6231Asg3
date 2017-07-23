package server;

public class StudentRecord extends Record {

    public StudentRecord(String recordID, String firstName, String lastName, String courseRegistered, String status, String statusDate) {
        super(recordID, firstName, lastName, courseRegistered, status, statusDate);
    }

    @Override
    public String getRecordType() {
        // TODO Auto-generated method stub
        return "SR";
    }

    public String toString() {
        String sr = "First Name: " + getFirstName() + "\n"
                + "Last Name: " + getLastName() + "\n"
                + "Designation: " + getCourseRegistered() + "\n"
                + "Status: " + getStatus() + "\n"
                + "StatusDate: " + getStatusDate() + "\n";
        return sr;
    }

}

