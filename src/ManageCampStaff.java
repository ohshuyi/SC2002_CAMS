import java.util.Scanner;
/**
 * ManageCampStaff allows Staff to interactively manage camp details, inquiries, suggestions, and perform edits.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class ManageCampStaff extends Staff {
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);
    /**
     * The camp file name
     */
    String campFile = "camp.csv";
    /**
     * The camp folder path
     */
    String campsFolderPath = "Camps";
    /**
     * The camp index
     */
    int campIndex = 0;
    /** 
     * Constructor that initializes a ManageCampStaff with Staff attributes.
     * @param userName Username of camp Staff
     * @param userID User ID of camp Staff
     * @param password Password of camp Staff
     * @param faculty Faculty of camp Staff
     * @param email Email of camp Staff
     * @param profile Profile type of camp Staff
    */
    public ManageCampStaff(String userName, String userID, String password, String faculty, String email, int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method presents options to view camp details, reply to inquiries, manage suggestions, edit camp details, or return to the main menu for a specific camp.
     * @param camp CampInformation object
     */
    public void campMenu(CampInformation camp) {
        int choice = 0;

        do {
            System.out.println("====== " + camp.getCampName() + " ======");
            System.out.println("1. View Camp Details");
            System.out.println("2. Reply Enquiry");
            System.out.println("3. Manage Suggestions");
            System.out.println("4. Edit Camp Details");
            System.out.println("5. Return to Main Menu");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ListCampDetails campDetails = new ListCampDetails(camp.getCampName(), camp.getStartCampDate(),
                            camp.getEndCampDates(),
                            camp.getClosingRegDates(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(),
                            camp.getRemainingSlots(), camp.getCampCommitteeSlots(),
                            camp.getCampCommitteeRemainingSlots(), camp.getDescription(), camp.getStaffInCharge(),
                            camp.getVisibility());
                    campDetails.campDetails(camp);
                    break;
                case 2:
                    ManageEnquiryStaff manageEnquiryStaff = new ManageEnquiryStaff(this.getUserName(), this.getUserID(),
                            this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    manageEnquiryStaff.manageEnquiry(camp);
                    break;
                case 3:
                    ManageSuggestionStaff manageSuggestionStaff = new ManageSuggestionStaff(this.getUserName(),
                            this.getUserID(), this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    manageSuggestionStaff.manageSuggestionStaff(camp);
                    break;
                case 4:
                    EditCampDetailsStaff editCampDetailsStaff = new EditCampDetailsStaff(this.getUserName(),
                            this.getUserID(), this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    editCampDetailsStaff.manageCamps(camp);
                    break;
                case 5:
                    return;
            }
        } while (choice != 5);
    }
}
