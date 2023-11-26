import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * ManageCampParticipant includes a method to allow a student to withdraw from a camp, updating slots and preventing re-registration.
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/
public class ManageCampParticipant extends Participant{
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);
    /** 
     * Constructor that initializes a ManageCampParticipant with Participant attributes.
     * @param userName Username of camp participant
     * @param userID User ID of camp participant
     * @param password Password of camp participant
     * @param faculty Faculty of camp participant
     * @param email Email of camp participant
     * @param profile Profile type of camp participant
    */
    public ManageCampParticipant(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method allows a student to withdraw from a camp, updating the remaining slots, and preventing re-registration for the same camp.
     * @param camp Camp object
     */
    // function to withdrawFromCamp
    @Override
    public void withdrawFromCamp(CampInformation camp){
      //A student is allowed to withdraw from camps that his/her has already registered for. The remaining slot will be updated automatically. 
      //But the student is not allowed to register the same camp again.
        CampUpdateCSV csv = new CampUpdateCSV(); 
        Path filePathParticipant = Paths.get(".", "Camps", camp.getCampName(),camp.getCampName() + "_Participant.csv");

        System.out.println("Would you like to withdraw from the following Camp: ");
        System.out.println(camp.getCampName());
        System.out.printf("Choice(Y/N): ");

        String choice = sc.nextLine();

        if(choice.equals("Y"))
        {
            csv.removeParticipant(this.getUserName(), filePathParticipant.toString());
            camp.setRemainingSlots(camp.getRemainingSlots() + 1);
        }
    }
}



