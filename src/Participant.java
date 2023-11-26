/**
 * The Participant class extends Student and includes methods for camp management
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/

public class Participant extends Student{
    /** 
     * Constructor for the Participant class. It initializes a Participant object with the provided user information.
     * @param userName Username of camp participant
     * @param userID User ID of camp participant
     * @param password Password of camp participant
     * @param faculty Faculty of camp participant
     * @param email Email of camp participant
     * @param profile Profile type of camp participant
     */
    public Participant(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile); 
    }

    /** 
     * This method presents a menu for camp management to participants. It allows them to perform actions such as making enquiries, viewing camp details, withdrawing from the camp, or returning to the main menu.
     * @param camp Camp object
     * @param student Student object
     */
    public void campManagementP(CampInformation camp, Participant student)
    {
        int choice = 0;
        do {
            System.out.println("====== " + camp.getCampName() + " ======");
            System.out.println("1. Enquiry");
            System.out.println("2. View Camp Details");
            System.out.println("3. Withdraw From Camp");
            System.out.println("4. Return to Main Menu");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ManageEnquiryParticipant manageEnquiryParticipant = new ManageEnquiryParticipant(this.getUserName(), this.getUserID(), this.getPassword(),
                    this.getFaculty(), this.getEmail(), this.getProfile());
                    manageEnquiryParticipant.enquiryMenu(camp);
                    break;
                case 2:
                    ListCampDetails campDetails = new ListCampDetails(camp.getCampName(), camp.getStartCampDate(),
                            camp.getEndCampDates(),
                            camp.getClosingRegDates(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(),
                            camp.getRemainingSlots(), camp.getCampCommitteeSlots(),
                            camp.getCampCommitteeRemainingSlots(), camp.getDescription(), camp.getStaffInCharge(),
                            camp.getVisibility());
                    campDetails.campDetails(camp);
                    break;
                case 3:
                    ManageCampParticipant manageCampParticipant = new ManageCampParticipant(this.getUserName(), this.getUserID(), this.getPassword(),
                    this.getFaculty(), this.getEmail(), this.getProfile());
                    manageCampParticipant.withdrawFromCamp(camp);
                    return;
                case 4:
                    return;
            }
        }while(choice != 4);
        return;
    }

    /** 
     * This method presents a menu for camp details
     * @param camp Camp object
     */
    public void campDetails(CampInformation camp) {
    }
    /** 
     * This method presents a menu for enquires
     * @param camp Camp object
     */
    public void enquiryMenu(CampInformation camp) {
    }
    /** 
     * This method allows a participant to withdraw from a camp
     * @param camp Camp object
     */
    public void withdrawFromCamp(CampInformation camp) {
    }
    /** 
     * This method allows a participant to submit and enquiry
     * @param message Enquiry message to be submitted
     * @param camp Camp object
     */
    public void submitEnquiry(String message, CampInformation camp) {
    }
    /** 
     * This method allows a Staff/Camp committee member to retrieve student enquires
     * @param camp Camp object
     */
    public void retrieveStudentEnquiries(CampInformation camp) {
    }
    /** 
     * This method allows a Participant to edit enquires
     * @param camp Camp object
     * @param enquiryEditIndex Index of enquiry to be edited
     * @param enquiryEditReply Reply to be edited
     */
    public void editStudentEnquiry(CampInformation camp, int enquiryEditIndex, String enquiryEditReply) {
    }
    /** 
     * This method allows a Participant to delete enquires
     * @param camp Camp object
     * @param enquiryDeleteIndex Index of enquiry to be deleted
     */
    public void deleteStudentEnquiry(CampInformation camp, int enquiryDeleteIndex) {
    }
}
