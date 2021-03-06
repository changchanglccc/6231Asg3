package server;

import java.util.HashMap;
import java.util.Map;


public class RecordFactory {

    public static Record createRecordFromMap(Map<String, String> map) {

        String recordID = map.get("id");
        String firstName = map.get("fn");
        String lastName = map.get("ln");
        String type = map.get("ty");

        String idnum = Long.toString(Long.parseLong(recordID.replace(type, ""), 10));

        if ("TR".equals(type)) {
            String address = map.get("ad");
            String phone = map.get("ph");
            String specialization = map.get("sp");
            String location = map.get("lo");
            return new TeacherRecord(idnum, firstName, lastName, address, phone, specialization, location);

        }
        if ("SR".equals(type)) {

            String designation = map.get("de");
            String status = map.get("st");
            String statusDate = map.get("sd");
            return new StudentRecord(idnum, firstName, lastName, designation, status, statusDate);
        }

        return null;
    }

    public static Map<String, String> createMapFromRecord(Record record) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", record.getId());
        map.put("fn", record.getFirstName());
        map.put("ln", record.getLastName());
        map.put("ty", record.getRecordType());

        try {
            TeacherRecord tr = (TeacherRecord) record;
            map.put("ad", tr.getAddress());
            map.put("ph", tr.getPhone());
            map.put("sp", tr.getSpecialization());
            map.put("lo", tr.getLocation());

        } catch (ClassCastException ex) {
        }

        try {
            StudentRecord sr = (StudentRecord) record;
            map.put("de", sr.getCourseRegistered());
            map.put("st", sr.getStatus());
            map.put("sd", sr.getStatusDate());
        } catch (ClassCastException ex) {
        }

        return map;
    }

}
