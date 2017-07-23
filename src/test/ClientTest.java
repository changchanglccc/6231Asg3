package test;

import java.util.Scanner;


public class ClientTest {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String managerID = "";
        System.out.print("Please Input Manager ID: ");
        managerID = input.nextLine();

        Client_Configure client = new Client_Configure(managerID);
        client.connect();

        int response;

        do {
            System.out.print("\n **** Manager Menu for " + managerID + "**** \n"
                    + "1. Create Teacher Record\n"
                    + "2. Create Student Record\n"
                    + "3. Get Record Count\n"
                    + "4. Edit an Existing Record\n"
                    + "5. Transfer Record \n"
                    + "0. Quit\n"
                    + "\n"
                    + "Enter Selection: ");

            try {
                String responseLine = input.nextLine();
                response = Integer.parseInt(responseLine.trim());
            } catch (Exception ex) {
                response = -1;
            }

            String result;

            switch (response) {
                case 1:
                    try {
                        System.out.println("Enter first name:");
                        String firstName = input.next();
                        System.out.println("Enter last name:");
                        String lastName = input.next();
                        System.out.println("Enter address:");
                        String address = input.next();
                        System.out.println("Enter phone number:");
                        String phone = input.next();
                        System.out.println("Enter specialization:");
                        String specialization = input.next();
                        System.out.println("Enter Location(MTL/LVL/DDO):");
                        String location = input.next();

                        result = client.createTRecord(managerID, firstName, lastName, address, phone, specialization, location);
                        System.out.println(result);
                    } catch (Exception ex) {
                        System.out.println(ex.toString() + ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter first name:");
                        String firstName = input.next();
                        System.out.println("Enter last name:");
                        String lastName = input.next();
                        System.out.println("Enter course registered:");
                        String designation = input.next();
                        System.out.println("Enter status:");
                        String status = input.next();
                        System.out.println("Enter status date:");
                        String statusDate = input.next();

                        result = client.createSRecord(managerID, firstName, lastName, designation, status, statusDate);
                        System.out.println(result);
                    } catch (Exception ex) {
                        System.out.println(ex.toString() + ex.getMessage());
                    }
                    break;
                case 3:
                    result = client.getRecordCounts(managerID);
                    System.out.println(result);
                    break;
                case 4:
                    System.out.println("Enter RecordID");
                    String recordID = input.next();
                    System.out.println("Enter Field Name");
                    String fieldName = input.next();
                    System.out.println("Enter New Value");
                    String newValue = input.next();

                    result = client.editRecord(managerID, recordID, fieldName, newValue);
                    System.out.println(result);
                    break;
                case 5:
                    System.out.println("Enter Record ID:");
                    recordID = input.next();
                    System.out.println("Enter Remote Server:");
                    String remoteSchoolLocation = input.next();

                    result = client.transferRecord(managerID, recordID, remoteSchoolLocation);
                    System.out.println(result);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Bad response... ):");
            }

        } while (response != 0);
    }

}
