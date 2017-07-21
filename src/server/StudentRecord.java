package server;

public class StudentRecord extends Record {

    public StudentRecord(String recordID, String firstName, String lastName, String designation, String status, String statusDate) {
        super(recordID, firstName, lastName, designation, status, statusDate);
    }

    @Override
    public String getRecordType() {
        // TODO Auto-generated method stub
        return "NR";
    }

    public String toString() {
        String nr = "First Name: " + getFirstName() + "\n"
                + "Last Name: " + getLastName() + "\n"
                + "Designation: " + getDesignation() + "\n"
                + "Status: " + getStatus() + "\n"
                + "StatusDate: " + getStatusDate() + "\n";
        return nr;
    }

}

