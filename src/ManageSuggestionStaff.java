/**
 * ManageSuggestionStaff manages staff's approval of camp suggestions. It retrieves, displays, and approves or rejects suggestions based on user input, updating related CSV files.
 * @version 1.0
 * @author Keith Ang
 * @since 24/11/2023
*/

public class ManageSuggestionStaff extends ManageEnquiryStaff{
    /** 
     * Constructor for the ManageSuggestionStaff class. It initializes a ManageReportCM object with the provided Staff information.
     * @param userName Username of camp Staff
     * @param userID User ID of camp Staff
     * @param password Password of camp Staff
     * @param faculty Faculty of camp Staff
     * @param email Email of camp Staff
     * @param profile Profile type of camp Staff
     */
    public ManageSuggestionStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method handles staff's approval of camp suggestions. It retrieves, displays, and approves or rejects suggestions based on user input, updating related CSV files.
     * @param camp CampInformation object
     */
    public void manageSuggestionStaff(CampInformation camp){
        SuggestionUpdateCSV suggestionUpdate = new SuggestionUpdateCSV();

        String fileName = camp.getCampName() + "_Suggestion.csv";
        suggestionUpdate.retrieveSuggestions(camp, camp.getCampName(), fileName);
        System.out.println("Select suggestion to approve: ");
        int indexSugg = sc.nextInt();
        System.out.println("Approve? (Y/N)");
        sc.nextLine();
        char suggestionApproval = sc.next(). charAt(0);
        if(suggestionApproval == 'y' || suggestionApproval == 'Y')
        {
            // suggestionUpdate.approveSuggestions(camp, indexSugg, super.getUserName(),camp.getCampName(), fileName);
            String ccmName = suggestionUpdate.updateExcelFile(camp, indexSugg, super.getUserName(),camp.getCampName(), fileName);
            suggestionUpdate.addPointsUpdate(camp,ccmName,camp.getCampName(), fileName);
        }
        else
        {
            System.out.println("Suggestion is not Approved!");
        }
    }

    //create function to credit camp comm member whose suggestion is approved (add one point)
}
