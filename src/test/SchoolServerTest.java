package test;

import clientLVL.SchoolServer;
import clientLVL.SchoolServerImplService;

public class SchoolServerTest {

    public static void main(String[] args) {
        SchoolServerImplService CSIS = new SchoolServerImplService();
        SchoolServer record = CSIS.getSchoolServerImplPort();
        System.out.println(record.getRecordCounts("MTL2000"));
    }

}
