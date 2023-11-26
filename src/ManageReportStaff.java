import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * ManageReportStaff facilitates camp and performance report generation with dynamic filtering.
 * @version 1.0
 * @author Aaron Lim
 * @since 24/11/2023
*/
public class ManageReportStaff extends Staff{
    /** 
     * Constructor that initializes a ManageReportStaff with Staff attributes.
     * @param userName Username of camp Staff
     * @param userID User ID of camp Staff
     * @param password Password of camp Staff
     * @param faculty Faculty of camp Staff
     * @param email Email of camp Staff
     * @param profile Profile type of camp Staff
    */
    public ManageReportStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method prompts users to choose between generating a camp report or a performance report using the provided array of `CampInformation`.
     * @param camp CampInformation[] object
     */
    public void campReportMenu(CampInformation[] camp)
    {
        System.out.println();
        System.out.println("Select Report Type to Generate:");
        System.out.println("1. Camp Report");
        System.out.println("2. Performance Report");
        int choice = sc.nextInt();

        if(choice == 1)
        {
            generateCampReportStaff(camp);
        }
        else if(choice == 2)
        {
            generatePerformanceReport(camp);
        }
    }

    
    /** 
     * This method allows users to filter and generate a camp report based on various criteria from the provided array of `CampInformation`.
     * @param campInfo CampInformation[] object
     */
    //generates camp report
    public void generateCampReportStaff(CampInformation[] campInfo){ 
        CampInformation[] createdCamps = createdCampsArray(campInfo);
        Scanner sc = new Scanner(System.in);
        int filterChoice;

        do{
        System.out.println();
        System.out.println("---Select Camps filter---");
        System.out.println("1. All created camps (No filter)");
        System.out.println("2. By Specified Camp Name");
        System.out.println("3. Camps with specified Location");
        System.out.println("4. By Specified faculty");
        System.out.println("5. Camps with x to y range of total slots");
        System.out.println("6. Camps with x to y range of occupied slots");
        System.out.println("7. Camps with x to y range of remaining slots");
        System.out.println("8. Camps with specified student involved (Either as Participant or Camp Committee Member)");
        System.out.println("9. Camps with duration within YYYY-MM-DD to YYYY-MM-DD");
        System.out.println("10. Camps BEFORE closing registration date");
        System.out.println("11. Camps AFTER closing registration date");

        filterChoice = sc.nextInt();

        if(filterChoice<1 || filterChoice>11)
        System.out.println("Invalid option. Try again");

        }while(filterChoice<1 || filterChoice>11);

        Report campNewReport = new Report();

        CampInformation[] filteredCreatedCamps = filteredCampsArray(createdCamps, filterChoice);
        campNewReport.generateCampReport(filteredCreatedCamps,this);
    }

    
    /** 
     * This method dynamically filters camps based on user input, covering criteria like name, location, slots, student involvement, date range, and registration status.
     * @param camp[] CampInformation object
     * @param filter Filter type
     * @return CampInformation[] object
     */
    //returns array of camps that staff created according to selected filter
    public CampInformation[] filteredCampsArray(CampInformation camp[], int filter){ //argument passed in is the createdCampsArray
        //switch to filter based on filter chosen and value
        CSVManager CSVManager = new CSVManager();
        List<CampInformation> filteredCreatedCampsAL = new ArrayList<CampInformation>(); //create ArrayList
        CampInformation[] filteredCreatedCampsArray; //creat

        switch(filter){
            case 1: 
            return camp;

            case 2:
            do{
            System.out.println();
            sc.nextLine();
            System.out.println("Enter Camp Name:");
            String value = sc.nextLine();

            for(int i=0;i<camp.length;i++){
                if(camp[i].getCampName().equals(value)){
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp named '" + value + "'." + " Try again.");

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;
        
            case 3:
            do{
            System.out.println();
            System.out.println("Enter Location:");
            sc.nextLine();
            String value = sc.nextLine();

            for(int i=0;i<camp.length;i++){
                if(camp[i].getLocation().equals(value)){
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp with location '" + value + "'." + " Try again.");

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 4:
            do{
            System.out.println();
            System.out.println("Enter Faculty:");
            sc.nextLine();
            String value = sc.nextLine();

            for(int i=0;i<camp.length;i++){
                if(camp[i].getFaculty().equals(value)){
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp with faculty '" + value + "'." + " Try again.");

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 5:
            do{
            System.out.println();
            System.out.println("Enter value of x:");
            int x = sc.nextInt();
            System.out.println();
            System.out.println("Enter value of y:");
            int y = sc.nextInt();


            for(int i=0;i<camp.length;i++){

                if(camp[i].getTotalSlots() >= x && camp[i].getTotalSlots() <= y){ //total slots within range
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp within total slots in range of '" + x + "and" + y + "'." + " Try again."); //total slots include CCM + Participants Slots

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 6:
            do{
            System.out.println();
            System.out.println("Enter value of x:");
            int x = sc.nextInt();
            System.out.println();
            System.out.println("Enter value of y:");
            int y = sc.nextInt();


            for(int i=0;i<camp.length;i++){

                if(camp[i].getTotalSlots() - camp[i].getRemainingSlots() >= x && camp[i].getTotalSlots() - camp[i].getRemainingSlots() <= y){ //occupied slots within range
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp with occupied slots in range of '" + x + "and" + y + "'." + " Try again."); //occupied slots include CCM + Participants Slots

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 7:
            do{
            System.out.println();
            System.out.println("Enter value of x:");
            int x = sc.nextInt();
            System.out.println();
            System.out.println("Enter value of y:");
            int y = sc.nextInt();


            for(int i=0;i<camp.length;i++){

                if(camp[i].getRemainingSlots() >= x && camp[i].getRemainingSlots() <= y){ //remaining slots within range
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0)
                System.out.println("No existing camp with remaining slots in range of '" + x + "and" + y + "'." + " Try again."); //remaining slots include CCM + Participants Slots

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 8:
            do{
            System.out.println();
            System.out.println("Enter Name of Student:");
            sc.nextLine();
            String name = sc.nextLine();

            for(int i=0;i<camp.length;i++){
                Path filePathParticipant = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_Participant.csv");
                Path filePathCM = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_CampCommitteeMember.csv");

                if(CSVManager.checkifParticipant(name,filePathParticipant.toString())){
                    filteredCreatedCampsAL.add(camp[i]);
                }

                if(CSVManager.checkifClassCommitteeMember(name, filePathCM.toString())){
                    filteredCreatedCampsAL.add(camp[i]);
                }
            }

            if(filteredCreatedCampsAL.size() == 0){
                System.out.println("No existing camp with student named '" + name + "' inside." + " Try again."); 
            }

            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 9:
            LocalDate startDate = null;
            LocalDate endDate = null;
            String startDateString = null;
            String endDateString = null;
            boolean isValidRange = true;
            boolean isValidDate = false;
            sc.nextLine();
            do{ //repeats loop if no camps found within date range

            do{ //repeats loop if invalid date range, e.g 2024-12-12 to 2023-01-01

            isValidDate = false;
            isValidRange = true;
    
            while(!isValidDate){

            do{
            System.out.println();
            System.out.println("Enter start of date range YYYY-MM-DD:");
            startDateString = sc.nextLine();

            if (startDateString.length() != 10) {
                System.out.println("Input does not follow the format: YYYY-MM-DD"); // Check if the entered string length matches the expected format
            }

            }while(startDateString.length() != 10);
            
            try{
            startDate = LocalDate.parse(startDateString); //throws Exception if input is not a valid date, isValidDate remains false
            isValidDate = true;
            }catch(Exception e) {
            System.out.println();    
            System.out.println("Input does not follow the format: YYYY-MM-DD");
            }
            }
            
            isValidDate = false;
            

            while(!isValidDate){

            do{
            System.out.println();
            System.out.println("Enter end of date range YYYY-MM-DD:");
            endDateString = sc.nextLine();

            if (endDateString.length() != 10) {
                System.out.println("Input does not follow the format: YYYY-MM-DD"); // Check if the entered string length matches the expected format
            }
            
            }while(endDateString.length() != 10);

            try{
            endDate = LocalDate.parse(endDateString); //throws Exception if input is not a valid date, isValidDate remains false
            isValidDate = true;
            }catch(Exception e) {
            System.out.println();
            System.out.println("Input does not follow the format: YYYY-MM-DD");
            }
            }

            if(startDate.compareTo(endDate) > 0){ //only check if start date is after end date
                isValidRange = false;
                System.out.println();
                System.out.println(startDateString + " to " + endDateString + " is an invalid date range. Try again.");
            }

            }while(!isValidRange); //to get proper input date range

            for(int i=0;i<camp.length;i++){
                try{
                LocalDate campStartDate = LocalDate.parse(camp[i].getStartCampDate()); //the camp starting date
                LocalDate campEndDate = LocalDate.parse(camp[i].getEndCampDates()); //the camp end date
                
                    
                if(campStartDate.compareTo(startDate) >= 0 && campEndDate.compareTo(endDate) <= 0){ //if camp date range is within the specified range, add to array list
                    filteredCreatedCampsAL.add(camp[i]);
                }
                }catch(Exception e){}
            }

            if(filteredCreatedCampsAL.size() == 0){
                System.out.println();
                System.out.println("No existing camp within date range '" + startDateString + " to " + endDateString + "'. Try Again.");
            }


            }while(filteredCreatedCampsAL.size() == 0);

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 10:
            startDate = null;
            endDate = null;
            startDateString = null;
            endDateString = null;
            isValidRange = true;
            isValidDate = false;
            LocalDate currentDate = LocalDate.now(); 
            System.out.println();
            System.out.println("Today's Date is " + currentDate);

            for(int i=0;i<camp.length;i++){
                try{
                LocalDate campRegClosingDate = LocalDate.parse(camp[i].getClosingRegDates()); //the camp starting date
                
                if(currentDate.compareTo(campRegClosingDate) <= 0){ //if current date is before camp registration closing date, add to array
                    filteredCreatedCampsAL.add(camp[i]);
                }
                }catch(Exception e){} //throws exception if date taken from camp array is invalid, however it is impossible due to previous check made during camp creation
            }

            if(filteredCreatedCampsAL.size() == 0){ //if all camps have closed registration, no camps exist in the filtered array
                System.out.println();
                System.out.println("No camps open for registration");
                return null;
            }


            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;

            case 11:
            startDate = null;
            endDate = null;
            startDateString = null;
            endDateString = null;
            isValidRange = true;
            isValidDate = false;
            currentDate = LocalDate.now(); 
            System.out.println();
            System.out.println("Today's Date is " + currentDate);

            for(int i=0;i<camp.length;i++){
                try{
                LocalDate campRegClosingDate = LocalDate.parse(camp[i].getClosingRegDates()); //the camp starting date
                
                if(currentDate.compareTo(campRegClosingDate) >= 0){ //if current date is after camp registration closing date, add to array
                    filteredCreatedCampsAL.add(camp[i]);
                }
                }catch(Exception e){} //throws exception if date taken from camp array is invalid, however it is impossible due to previous check made during camp creation
            }

            if(filteredCreatedCampsAL.size() == 0){ //if all camps are still open for registration, no camps exist in the filtered array
                System.out.println();
                System.out.println("All camps still open for registration");
                return null;
            }

            filteredCreatedCampsArray = new CampInformation[filteredCreatedCampsAL.size()]; //create array with the same size as arraylist
            filteredCreatedCampsArray = filteredCreatedCampsAL.toArray(filteredCreatedCampsArray); 
    
            return filteredCreatedCampsArray;
        }
        return null;
    }

    
    /** 
     * This method generates a performance report for camps created by the staff, utilizing the `createdCampsArray` method to filter relevant camp information.
     * @param campInfo CampInformation[] object
     */
    //generate performance report
    public void generatePerformanceReport(CampInformation[] campInfo){ 
        CampInformation[] createdCamps = createdCampsArray(campInfo);
        Report campNewReport = new Report();
        campNewReport.generatePerformanceReport(createdCamps,this);
    }

    /**
     * This method returns an array of camps thats been created by the staff who called the method, used for generating camp report
     * @param campInfo CampInformation[] object
     * @return CampInformation[] object
     */
    //returns array of camps that staff created
    public CampInformation[] createdCampsArray(CampInformation campInfo[]) //pass in all campinfo, called in generateCampReport
    {
        List<CampInformation> createdCampsAL = new ArrayList<CampInformation>();
        int i = 0;

        do
        {
            if(campInfo[i].getStaffInCharge().equals(this.getUserID()))
            {
                createdCampsAL.add(campInfo[i]);
            }

            i++;
        }while(campInfo[i] != null);

        CampInformation[] createdCampsArray = new CampInformation[createdCampsAL.size()]; //create array with the same size as arraylist
        createdCampsArray = createdCampsAL.toArray(createdCampsArray); 

        return createdCampsArray;
    }
}
