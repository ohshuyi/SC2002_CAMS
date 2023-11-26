import java.io.IOException;
/**
 * CreateCampStaff initializes a camp staff member and provides a method to create a new camp, gathering details and updating information with CSV files.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class CreateCampStaff extends ManageCampStaff{
    /** 
     * Constructor that initializes a CreateCampStaff with Staff attributes.
     * @param userName Username of Staff
     * @param userID User ID of Staff
     * @param password Password of Staff
     * @param faculty Faculty of Staff
     * @param email Email of Staff
     * @param profile Profile type of Staff
    */
    public CreateCampStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName,  userID,  password, faculty, email, profile);
    }

    /** 
     * This method initializes a new camp, gathering user input for camp details, updating information, and generating associated CSV files using the `CampUpdateCSV` class.
     * @param camp[] CampInformation object
     * @param index Index of camp to be edited
     * @throws IOException if there was an error in handling the csv file paths
     */
    public void createCamp(CampInformation camp[], int index) throws IOException
    {
        CampUpdateCSV csv = new CampUpdateCSV();

        camp[index].setStaffInCharge(getUserID());
        
        System.out.println("Enter Camp Name: ");
        String campName = sc.nextLine();
        camp[index].setCampName(campName);

        System.out.println("Enter Camp Start Date (YYYY-MM-DD): ");
        String startCamp = sc.nextLine();
        camp[index].setStartCampDate(startCamp);

        System.out.println("Enter Camp End Date (YYYY-MM-DD): ");
        String endCamp = sc.nextLine();
        camp[index].setEndCampDate(endCamp);
                    
        System.out.println("Enter Camp Registration Closing Date (YYYY-MM-DD): ");
        String regCloseDates = sc.nextLine();
        camp[index].setClosingRegDates(regCloseDates);

        System.out.println("Enter Camp's Organising Faculty: ");
        String campFacu = sc.nextLine();
        camp[index].setFaculty(campFacu);
        
        System.out.println("Enter Camp Location: ");
        String campLocation = sc.nextLine();
        camp[index].setLocation(campLocation);
        
        System.out.println("Enter Number of Participation Slots: ");
        int campSlots = sc.nextInt();
        camp[index].setTotalSlots(campSlots);
        sc.nextLine();

        camp[index].setRemainingSlots(camp[index].getTotalSlots());

        System.out.println("Enter Number of Camp Committee Slots: ");
        int campCompSlots = sc.nextInt();
        camp[index].setCampCommitteeSlots(campCompSlots);
        sc.nextLine();

        camp[index].setCampCommitteeRemainingSlots(campCompSlots);

        System.out.println("Enter Camp's Description: ");
        String campDesc = sc.nextLine();
        camp[index].setDescription(campDesc);
        
        System.out.println("Enter Camp's Visibility(On/Off): ");
        String visibility = sc.nextLine().toUpperCase();
        if(visibility.equals("ON"))
        {
            camp[index].changeVisiblity(true);
        }
        else
        {
            camp[index].changeVisiblity(false);
        }

        csv.createNewCamp(camp[index].getCampName(), campsFolderPath);
    }
}