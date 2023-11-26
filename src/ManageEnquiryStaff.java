/**
 * ManageEnquiryStaff retrieves camp inquiries, allows staff to select one, and reply to it by updating the CSV file.
 * @version 1.0
 * @author Kendrick Koh
 * @since 24/11/2023
*/
public class ManageEnquiryStaff extends ManageCampStaff{
    /** 
     * Constructor that initializes a ManageEnquiryStaff with Staff attributes.
     * @param userName Username of Staff
     * @param userID User ID of Staff
     * @param password Password of Staff
     * @param faculty Faculty of Staff
     * @param email Email of Staff
     * @param profile Profile type of Staff
    */
    public ManageEnquiryStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method retrieves camp inquiries, allows staff to select an inquiry, and reply to it by updating the CSV file.
     * @param camp CampInformation object
     */
    public void manageEnquiry(CampInformation camp){
        EnquiresAssignCSV enquiresAssign = new EnquiresAssignCSV();
        EnquiresUpdateCSV enquiresUpdate = new EnquiresUpdateCSV();
        String fileName = camp.getCampName() + "_Enquiry.csv";
        int numberOfEnquiries = enquiresUpdate.retrieveEnquiries(camp, camp.getCampName(), fileName);
        if(numberOfEnquiries != 0){
            System.out.println("Select enquiry to reply: ");
            int enquiryIndex = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Reply: ");
            String enquiryReply = sc.nextLine();
            enquiresAssign.replyEnquiry(camp, enquiryIndex, enquiryReply, super.getUserName(), camp.getCampName(), fileName,"");
        }else{
            System.out.println("No enquires, exiting");
            System.out.println();
            return;
        }
    }
}
