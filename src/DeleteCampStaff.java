import java.io.IOException;
/** 
 * DeleteCampStaff initializes a camp staff member and provides a method to delete a camp, gathering details and updating information in CSV files.
 * @version 1.0
 * @author team1
 * @since 24/11/2023
*/
public class DeleteCampStaff extends ManageCampStaff{
    /**  
     * Constructor that initializes a DeleteCampStaff with Staff attributes.
     * @param userName Username of Staff
     * @param userID User ID of Staff
     * @param password Password of Staff
     * @param faculty Faculty of Staff
     * @param email Email of Staff
     * @param profile Profile type of Staff
    */
    public DeleteCampStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method removes a camp from the array of CampInformation objects at the specified index.
     * @param camp[] CampInformation object
     * @param index Index of selected camp to be deleted
     * @return boolean returns true if camp is deleted. Returns false if camp cannot be deleted as it is not empty
     */
    public boolean deleteCamp(CampInformation camp[], int index)
    {
        if(camp[index].getRemainingSlots() != camp[index].getTotalSlots()){ //checks if camp is not empty
            System.out.println();
            System.out.println("Camp is not empty. Cannot be deleted.");
            System.out.println();
            return false;
        }

        CampUpdateCSV csv = new CampUpdateCSV();
        csv.deleteCampFolder(camp[index].getCampName()); //deletes the camp folder inside Camps

        if (index >= 0 && index < camp.length) {
            for (int i = index; i < camp.length - 1; i++) {
                camp[i] = camp[i + 1];
            }
            camp[camp.length - 1] = null; // Clear the last entry
        }
        return true;
    }
}