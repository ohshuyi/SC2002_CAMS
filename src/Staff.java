import java.util.Scanner;
/**
 * Staff represents staff members in a camp management system. It includes methods for camp creation, deletion, management, and report generation.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class Staff extends User {
    /**
     *  Scanner object
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor that initializes a Staff with User attributes
     * @param userName Username of Staff
     * @param userID User ID of Staff
     * @param password Password of Staff
     * @param faculty Faculty of Staff
     * @param email Email of Staff
     * @param profile Profile type of Staff
     */
    // staff can view all camps.
    public Staff(String userName, String userID, String password, String faculty, String email, int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    
    /** 
     * This method facilitates staff interaction with a camp management system, enabling camp creation, deletion, management, and report generation through a menu-driven interface.
     * @param camp[] CampInformation object
     * @param index Index of camp to be displayed
     */
    public void campDirectoryStaff(CampInformation camp[], int index) throws Exception {
        String campFile = "camp.csv";
        String campsFolderPath = "Camps";
        CampUpdateCSV csvU = new CampUpdateCSV();
        CampAssignCSV csvA = new CampAssignCSV();
        System.out.println();
        int campDir;
        do {
            System.out.println("---------Camp Directory---------");
            System.out.println("1. Create Camp");
            System.out.println("2. Delete Camp");
            System.out.println("3. Manage Camps");
            System.out.println("4. View All Camps");
            System.out.println("5. Generate Report");
            System.out.println("6. Return to main menu");
            System.out.printf("Choice: ");
            campDir = sc.nextInt();
            switch (campDir) {
                case 1:
                    index++;

                    CreateCampStaff newCampStaff = new CreateCampStaff(this.getUserName(), this.getUserID(),
                            this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());

                    camp[index] = new CampInformation("", "", "", "", "", "", 0, 0, 0, 0, "", "", false);
                    newCampStaff.createCamp(camp, index);
                    csvU.updateExcelFile(camp, campFile, index + 1, campsFolderPath);
                    csvA.assignExcelFile(camp, campFile, campsFolderPath);
                    camp[index] = null;  //ensures that the camp element after the last camp in the array is null
                    break;

                case 2:
                    DeleteCampStaff deleteCampStaff = new DeleteCampStaff(this.getUserName(), this.getUserID(),
                            this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    System.out.println();
                    System.out.println("Choose a camp: ");
                    int choice1 = viewCreatedCamps(camp, index);

                    System.out.println(
                            "Would you like to delete the following Camp: '" + camp[choice1].getCampName() + "'"); // double
                                                                                                                   // confirms
                                                                                                                   // choice
                    System.out.println();
                    System.out.printf("Choice(Y/N): ");

                    sc.nextLine();
                    String ynChoice = sc.nextLine();

                    if (ynChoice.equals("Y")) {
                        if (deleteCampStaff.deleteCamp(camp, choice1) == true) { // if the method returns false, the
                                                                                 // camp was not deleted as it is not
                                                                                 // empty, code is skipped.
                            index--;
                            csvU.updateExcelFile(camp, campFile, index, campsFolderPath);
                            csvA.assignExcelFile(camp, campFile, campsFolderPath);
                        }
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("Choose a camp: ");
                    int choice = viewCreatedCamps(camp, index);
                    ManageCampStaff manageCampStaff = new ManageCampStaff(this.getUserName(), this.getUserID(),
                            this.getPassword(),
                            this.getFaculty(), this.getEmail(), this.getProfile());
                    manageCampStaff.campMenu(camp[choice]);
                    break;

                case 4:
                    int i = 0;
                    System.out.println();
                    System.out.println("---------All Camps---------"); // prints all camps
                    do {
                        System.out.println((i + 1) + ". " + camp[i].getCampName());
                        i++;
                    } while (camp[i] != null);
                    System.out.println();
                    break;

                case 5:
                    ManageReportStaff manageReportStaff = new ManageReportStaff(this.getUserName(), this.getUserID(),
                            this.getPassword(), this.getFaculty(), this.getEmail(), this.getProfile());

                    manageReportStaff.campReportMenu(camp);
                    break;

                case 6:
                    return;
            }

        } while (campDir != 6);

    }

    
    /** 
     * This method lists camps created by the staff, allowing them to select a camp from the menu and returns the chosen camp's array index.
     * @param camp[] CampInformation object
     * @param index Index of camp to be displayed
     * @return int returns camp index
     */
    // see list of camps that his/her created in a separate menu list
    public int viewCreatedCamps(CampInformation camp[], int index) {
        int count = 1;
        int count2 = 0;
        int[] campIndex = new int[10];
        System.out.println("-----Camps Created-----");

        for (int i = 0; i < index; i++) {
            if (camp[i].getStaffInCharge().equals(this.getUserID())) {
                System.out.println(count++ + "." + camp[i].getCampName());
                campIndex[count2] = i;
                count2++;
            }
        }
        int choice;
        choice = sc.nextInt();
        System.out.println();
        return campIndex[choice - 1];
    }
}
