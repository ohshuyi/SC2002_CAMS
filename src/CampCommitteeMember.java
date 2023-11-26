/**
 * CampCommitteeMember represents a member of the camp committee, inheriting from the Student class.
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/

public class CampCommitteeMember extends Student {
    /** 
     * Constructor for the CampCommitteeMember class. It initializes a CampCommitteeMember object with the provided Camp Committee Member information.
     * @param userName This camp committee member's name
     * @param userID This camp committee member's name
     * @param password This camp committee member's name
     * @param faculty This camp committee member's name  
     * @param email This camp committe member's name
     * @param profile This camp committee member's profile. 1 for Participant. 2 for Camp Committee Member
     */
    public CampCommitteeMember(String userName, String userID, String password, String faculty, String email,
            int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method allows Camp Committee Members to manage a specific camp. It provides options to reply to inquiries, view camp details, manage suggestions, generate camp reports, or return to the main menu.
     * @param camp CampInformation object
     * @param student Camp Committee Member object
     */
    public void campManagementC(CampInformation camp, CampCommitteeMember student) {
        int choice = 0;
        do {
            System.out.println("====== " + camp.getCampName() + " ======");
            System.out.println("1. Reply Enquiry");
            System.out.println("2. View Camp Details");
            System.out.println("3. Suggestion Management");
            System.out.println("4. Generate Camp Report");
            System.out.println("5. Return to Main Menu");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ManageEnquiryCM manageEnquiryCM = new ManageEnquiryCM(this.getUserName(), this.getUserID(), this.getPassword(),
                    this.getFaculty(), this.getEmail(), this.getProfile());
                    manageEnquiryCM.replyEnquiryCM(camp);
                    break;
                case 2:
                    ListCampDetails campDetails = new ListCampDetails(camp.getCampName(), camp.getStartCampDate(), camp.getEndCampDates(),
                            camp.getClosingRegDates(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(),
                            camp.getRemainingSlots(), camp.getCampCommitteeSlots(),
                            camp.getCampCommitteeRemainingSlots(), camp.getDescription(), camp.getStaffInCharge(),
                            camp.getVisibility());
                    campDetails.campDetails(camp);
                    break;
                case 3:
                    ManageSuggestionCM manageSuggestionCM = new ManageSuggestionCM(this.getUserName(),
                            this.getUserID(), this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    manageSuggestionCM.suggestionMenuCM(camp);
                    break;
                case 4:
                    ManageReportCM manageCampCM = new ManageReportCM(this.getUserName(), this.getUserID(),
                            this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    manageCampCM.generateCampReport(camp);
                    break;
                case 5:
                    return;
            }
        } while (choice != 5);
        return;
    }
}