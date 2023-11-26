import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * ManageEnquiryParticipant manages camp-related enquiries. Users can send, edit, delete enquiries, or return to the main menu in a camp context.
 * @version 1.0
 * @author Kendrick Koh
 * @since 24/11/2023
*/
public class ManageEnquiryParticipant extends Participant {
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);
    /** 
     * Constructor that initializes a ManageEnquiryParticipant with Participant attributes.
     * @param userName Username of participant
     * @param userID User ID of participant
     * @param password Password of participant
     * @param faculty Faculty of participant
     * @param email Email of participant
     * @param profile Profile type of participant
    */
    public ManageEnquiryParticipant(String userName, String userID, String password, String faculty, String email,
            int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method manages interactions related to camp enquiries, allowing users to send, edit, delete enquiries, or return to the main menu in a camp context.
     * @param camp Camp object
     */
    public void enquiryMenu(CampInformation camp) {
        // sc.nextLine();
        int enquiryChoice = 0;
        do {
            System.out.println();
            System.out.println("What action would you like to perform?");
            System.out.println("1. Send an enquiry");
            System.out.println("2. Edit an existing enquiry");
            System.out.println("3. Delete an existing enquiry");
            System.out.println("4. Return to Main Menu");
            enquiryChoice = sc.nextInt();
            switch (enquiryChoice) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter Enquiry: ");
                    String enquiryStr = sc.nextLine();
                    submitEnquiry(enquiryStr, camp);
                    break;
                case 2:
                    sc.nextLine();
                    retrieveStudentEnquiries(camp);
                    System.out.println("Choose enquiry to edit");
                    int enquiryEditIndex = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter edit");
                    String enquiryEditReply = sc.nextLine();
                    editStudentEnquiry(camp, enquiryEditIndex, enquiryEditReply);
                    break;
                case 3:
                    System.out.println("Delete enquiry");
                    sc.nextLine();
                    retrieveStudentEnquiries(camp);
                    System.out.println("Choose enquiry to Delete");
                    int enquiryDeleteIndex = sc.nextInt();
                    sc.nextLine();
                    deleteStudentEnquiry(camp, enquiryDeleteIndex);
                    break;
                case 4:
                    return;
            }
        } while (enquiryChoice != 4);
    }
    
    /** 
     * This method facilitates the submission of an enquiry for a specific camp, associating it with the user and updating the corresponding CSV file.
     * @param message Message string to be submitted
     * @param camp Camp object
     */
    // function to submit an enquiry to a specific camp
    @Override
    public void submitEnquiry(String message, CampInformation camp) {
        EnquiresUpdateCSV enquiresUpdate = new EnquiresUpdateCSV();
        String fileName = camp.getCampName() + "_Enquiry.csv";
        enquiresUpdate.updateExcelFile(message, super.getUserName(), camp.getCampName(), fileName);
    }
    
    /** 
     * This method reads and displays enquiries from a specific camp made by the current user, excluding other participants' enquiries.
     * @param camp Camp object
     */
    @Override
    public void retrieveStudentEnquiries(CampInformation camp) {
        String fileName = "/" + camp.getCampName() + "_Enquiry.csv";
        Path filePath = Paths.get(".", "Camps/", camp.getCampName() + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            int index = 1;
            String line;
            boolean firstRow = true; // Flag to skip the first row

            while ((line = br.readLine()) != null) {
                if (firstRow) {
                    // Skip the first row (header)
                    firstRow = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String enquiry = parts[0];
                    String sender = parts[1];
                    if (sender.equals(super.getUserName())) {
                        System.out.println("Enquiry " + index + ": " + enquiry);
                        System.out.println("Sent by: " + sender);
                        System.out.println();
                        index++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * This method edits the reply of a specific enquiry made by the current user for a given camp. It updates the corresponding CSV file.
     * @param camp Camp object
     * @param enquiryEditIndex Index of enquiry to be edited
     * @param enquiryEditReply Reply to enquiry to be edited
     */
    @Override
    public void editStudentEnquiry(CampInformation camp, int enquiryEditIndex, String enquiryEditReply) {
        EnquiresAssignCSV enquiresAssign = new EnquiresAssignCSV();
        String fileName = camp.getCampName() + "_Enquiry.csv";
        enquiresAssign.editEnquiry(camp, camp.getCampName(), fileName, enquiryEditIndex, enquiryEditReply);
    }
    
    /** 
     * This method removes a specific enquiry made by the current user for a given camp. It updates the corresponding CSV file accordingly.
     * @param camp Camp object
     * @param enquiryDeleteIndex Index of enquiry to be deleted
     */
    @Override
    public void deleteStudentEnquiry(CampInformation camp, int enquiryDeleteIndex) {
        EnquiresAssignCSV enquiresAssign = new EnquiresAssignCSV();
        String fileName = camp.getCampName() + "_Enquiry.csv";
        enquiresAssign.assignExcel(camp, camp.getCampName(), fileName, enquiryDeleteIndex);
    }
}
