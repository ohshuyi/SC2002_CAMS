/**
 * ListCampDetails provides methods to view remaining participation and camp committee slots, and display camp details.
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/

public class ListCampDetails extends CampInformation{
    /** 
     * Constructor for the ListCampDetails class. It initializes a ListCampDetails object with the provided CampInformation information.
     * @param campName Camp name
     * @param startDate Start date of camp
     * @param endDate End date of camp
     * @param registrationClosingDate Closing date of camp
     * @param faculty Faculty hosting camp
     * @param location Location of camp
     * @param totalSlots Total slots of camp
     * @param remainingSlots Remaining participants slots of camp
     * @param campCommitteeSlots Total camp committee of camp
     * @param campCommitteeRemainingSlots Remaining Camp Committee slots of camp
     * @param description Description of camp
     * @param staffInCharge Name of Staff in charge of camp
     * @param visibility Visibility of camp
     */
    public ListCampDetails(String campName, String startDate, String endDate, String registrationClosingDate, String faculty, 
    String location, int totalSlots, int remainingSlots, int campCommitteeSlots, int campCommitteeRemainingSlots, String description, String staffInCharge, boolean visibility) 
    {
        super(
            campName,
            startDate,
            endDate,
            registrationClosingDate,
            faculty,
            location,
            totalSlots,
            remainingSlots,
            campCommitteeSlots,
            campCommitteeRemainingSlots,
            description,
            staffInCharge,
            visibility
        );
    }

    
    /** 
     * This method displays the remaining participation slots and camp committee slots for a given `CampInformation` object.
     * @param camp Campinformation object
     */
    public void viewRemainingSlot(CampInformation camp) {
        
        System.out.println("Participation Slots left:   " + camp.getRemainingSlots());
        System.out.println("Camp Committee Slots left:  " + camp.getCampCommitteeRemainingSlots());
        System.out.println();
        
    }
    
    /** 
     * This method prints essential details of a `CampInformation` object, including its name, dates, location, faculty, total slots, remaining slots, description, and staff in charge.
     * @param camp CampInformation object
     */
    public void campDetails(CampInformation camp)
    {
        System.out.println();
        System.out.println("Camp Details:");
        System.out.println();
        System.out.println("Camp Name:" + camp.getCampName());
        System.out.println("Start Date:" + camp.getStartCampDate());
        System.out.println("End Date:" + camp.getEndCampDates());
        System.out.println("Location:" + camp.getLocation());
        System.out.println("Faculty:" + camp.getFaculty());
        System.out.println("Total Slots:" + camp.getTotalSlots());
        System.out.println("Remaining Slots:" + camp.getRemainingSlots());
        System.out.println("Description:" + camp.getDescription());
        System.out.println("Staff In Charge:" + camp.getStaffInCharge());
        System.out.println();
    }
}
