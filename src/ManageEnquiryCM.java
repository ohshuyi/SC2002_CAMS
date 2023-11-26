import java.util.Scanner;

/**
 * ManageEnquiryCM replies to camp-related enquiries, utilizing CSV files for management.
 * @version 1.0
 * @author Kendrick Koh
 * @since 24/11/2023
*/
public class ManageEnquiryCM extends ManageReportCM {
    /**
     * PerformanceReportCSV object used to generate performance report
     */
    PerformanceReportCSV csv = new PerformanceReportCSV();
    /**
     * EnquiresUpdateCSV object used to update enquires CSVs
     */
    EnquiresUpdateCSV enquiresUpdate = new EnquiresUpdateCSV();
    /**
     * SuggestionAssignCSV object used to update suggestions CSVs
     */
    SuggestionAssignCSV suggestionAssign = new SuggestionAssignCSV();
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);

    /** 
     * Constructor for the ManageEnquiryCM class. It initializes a ManageEnquiryCM object with the provided ManageReportCM information.
     * @param userName Username of camp committee member
     * @param userID User ID of camp committee member
     * @param password Password of camp committee member
     * @param faculty Faculty of camp committee member
     * @param email Email of camp committee member
     * @param profile Profile type of camp committee member
     */
    public ManageEnquiryCM(String userName, String userID, String password, String faculty, String email, int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method replies to a camp-related enquiry, retrieving and displaying the enquiries for selection. It utilizes EnquiresAssignCSV for managing enquiries and replies.
     * @param camp CampInformation object
     */
    // change function to reply student enquiry
    public void replyEnquiryCM(CampInformation camp) {
        EnquiresAssignCSV enquiresAssign = new EnquiresAssignCSV();

        String fileName = camp.getCampName() + "_Enquiry.csv";
        int numberOfEnquiries = enquiresUpdate.retrieveEnquiries(camp, camp.getCampName(), fileName);
        if(numberOfEnquiries != 0){
            System.out.println("Select enquiry to reply: ");
            int enquiryIndex = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Reply: ");
            String enquiryReply = sc.nextLine();
            enquiresAssign.replyEnquiry(camp, enquiryIndex, enquiryReply, super.getUserName(), camp.getCampName(), fileName,"CM");
        }else{
            System.out.println("No enquires, exiting");
            System.out.println();
            return;
        }
    }
}