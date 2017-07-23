package server;

public class TeacherRecord extends Record {

    public TeacherRecord(String recordID, String firstName, String lastName, String address, String phone, String specialization, String location) {
        super(recordID, firstName, lastName, address, phone, specialization, location);
    }

    @Override
    public String getRecordType() {
        // TODO Auto-generated method stub
        return "TR";
    }

    public String toString() {
        String tr = "First Name: " + getFirstName() + "\n"
                + "Last Name: " + getLastName() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Phone: " + getPhone() + "\n"
                + "specialization: " + getSpecialization() + "\n"
                + "location: " + getLocation() + "\n";
        return tr;
    }

}
