import java.util.Scanner;
/**
 * ManageSuggestionCM allows Camp Committee Members to manage camp suggestions. It provides functions for adding, editing, deleting suggestions, and a suggestion menu for interaction.
 * @version 1.0
 * @author Keith Ang
 * @since 24/11/2023
*/
public class ManageSuggestionCM extends CampCommitteeMember {
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);
    /** 
     * Constructor for the ManageSuggestionCM class. It initializes a ManageSuggestionCM object with the provided Camp Committee Member information.
     * @param userName Username of camp camp committee member
     * @param userID User ID of camp camp committee member
     * @param password Password of camp camp committee member
     * @param faculty Faculty of camp camp committee member
     * @param email Email of camp camp committee member
     * @param profile Profile type of camp camp committee member
     */
    public ManageSuggestionCM(String userName, String userID, String password, String faculty, String email,
            int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method retrieves and displays camp suggestions for a Camp Committee Member, utilizing suggestionUpdate to handle the retrieval from CSV files.
     * @param camp CampInformation object
     */
    public void retrieveSuggestionsCM(CampInformation camp) {
        SuggestionUpdateCSV suggestionUpdate = new SuggestionUpdateCSV();
        String fileName = "/" + camp.getCampName() + "_Suggestion.csv";
        suggestionUpdate.retrieveSuggestions(camp, camp.getCampName(), fileName);
    }

    /** 
     * This method approves a specific camp suggestion for a Camp Committee Member, updating the CSV file through `suggestionUpdate` based on the provided suggestion index.
     * @param camp CampInformation object
     * @param suggestionIndex Index of suggestion to be approved
     */
    public void approveSuggestionsCM(CampInformation camp, int suggestionIndex) {
        SuggestionUpdateCSV suggestionUpdate = new SuggestionUpdateCSV();
        String fileName = "/" + camp.getCampName() + "_Suggestion.csv";
        suggestionUpdate.updateExcelFile(camp, suggestionIndex, this.getUserName(), camp.getCampName(), fileName);
    }

    
    /** 
     * The method allows a Camp Committee Member to suggest changes to camp details, providing a menu for modifying aspects like name, location, and dates.
     * @param camp CampInformation object
     */
    public void addSuggestionsCM(CampInformation camp){
        SuggestionAssignCSV suggestionAssign = new SuggestionAssignCSV();
        String fileNameSuggestion = camp.getCampName() + "_Suggestion.csv";
        int suggChoice = 0;
        do {
            System.out.println();
            System.out.println("------- " + camp.getCampName() + " Suggestions-------");
            System.out.println("1.Change Name");
            System.out.println("2.Change Location");
            System.out.println("3.Change Description");
            System.out.println("4.Change Camp Dates");
            System.out.println("5.Change Camp Registration End Date");
            System.out.println("6.Change Total Participation Slots");
            System.out.println("7.Quit");
            System.out.println("Enter Camp Details you wish to change: ");
            suggChoice = sc.nextInt();
            sc.nextLine();

            switch (suggChoice) {
                case 1:
                    System.out.println("Enter New Camp Name:");
                    String newName = sc.nextLine();
                    String newSuggestion = "(New Camp Name)" + newName;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 2:
                    System.out.println("Enter New Camp Location:");
                    String newLocation = sc.nextLine();
                    newSuggestion = "(New Location Name)" + newLocation;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 3:
                    System.out.println("Enter New Camp Describtion:");
                    String newDescription = sc.nextLine();
                    newSuggestion = "(New Description)" + newDescription;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 4:
                    System.out.println("Enter New Camp Dates:");
                    String newCampDates = sc.nextLine();
                    newSuggestion = "(New Camp Dates)" + newCampDates;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 5:
                    System.out.println("Enter New Camp Registration End Date:");
                    String newRegDate = sc.nextLine();
                    newSuggestion = "(New Registration End Date)" + newRegDate;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 6:
                    System.out.println("Enter New Total Number of Camp Slots:");
                    String newTotalSlots = sc.nextLine();
                    newSuggestion = "(New Total Slots)" + newTotalSlots;
                    suggestionAssign.addSuggestions(newSuggestion, this.getUserName(), camp.getCampName(), fileNameSuggestion,
                            false);
                    suggestionAssign.addPointsAssign(camp, this.getUserName(), camp.getCampName(), fileNameSuggestion);
                    break;
                case 7:
                    return;
            }
        } while (suggChoice != 7);
    }

    
    /** 
     * This method facilitates Camp Committee Members in managing camp suggestions. It enables adding, editing, and deleting suggestions, providing a suggestion menu.
     * @param camp CampInformation object
     */
    public void suggestionMenuCM(CampInformation camp) {
        //String fileNameSuggestion = camp.getCampName() + "_Suggestion.csv";
        SuggestionAssignCSV suggestionAssign = new SuggestionAssignCSV();
        int suggChoice = 0;
        do
        {
            System.out.println();
            System.out.println("------- " + camp.getCampName() + " Suggestion Actions-------");
            System.out.println("1.New Suggestions");
            System.out.println("2.Edit Suggestions");
            System.out.println("3.Delete Suggestions");
            System.out.println("4.Return");
            System.out.println("What do you wish to do? ");
            suggChoice = sc.nextInt();
            sc.nextLine();

            switch (suggChoice) {
                case 1:
                    addSuggestionsCM(camp);
                    break;
                case 2:
                    System.out.println("-----Suggestions-----");
                    retrieveSuggestionsCM(camp);
                    System.out.println("Select suggestion to edit: ");
                    int suggIndexEdit = sc.nextInt();
                    String fileNameEdit = camp.getCampName() + "_Suggestion.csv";
                    sc.nextLine();
                    System.out.println("Enter Edit Details:");
                    String suggestionEditReply = sc.nextLine();
                    suggestionAssign.assignExcel(camp, camp.getCampName(), fileNameEdit, suggIndexEdit, suggestionEditReply);
                    break;
                case 3:
                    System.out.println("-----Suggestions-----:");
                    retrieveSuggestionsCM(camp);
                    System.out.println("Select suggestion to delete: ");
                    int suggIndexDel = sc.nextInt();  
                    String fileNameDel = camp.getCampName() + "_Suggestion.csv";
                    suggestionAssign.deleteSuggestions(camp, camp.getCampName(), fileNameDel, suggIndexDel);
                    break;
                case 4:
                    return;
            }
        }while(suggChoice != 4);
    }
}
    
