import java.util.Scanner;
/**
 * EditCampDetailsStaff manages camp details, including editing, deleting, and toggling visibility for camps created or managed by staff members.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class EditCampDetailsStaff extends ManageCampStaff{
    /** 
     * Constructor that initializes a EditCampDetailsStaff with Staff attributes.
     * @param userName Username of Staff
     * @param userID User ID of Staff
     * @param password Password of Staff
     * @param faculty Faculty of Staff
     * @param email Email of Staff
     * @param profile Profile type of Staff
    */
    public EditCampDetailsStaff(String userName, String userID, String password, String faculty, String email, int profile)
    {
        super(userName,  userID,  password, faculty, email, profile);
    }

    /** 
     * This method allows users to interactively edit various details of a camp, including name, dates, slots, description, staff, and visibility.
     * @param camp CampInformation object
     */
    public void manageCamps(CampInformation camp)
    {
        CampUpdateCSV csv = new CampUpdateCSV();   
        int choice;
        do
        {
            System.out.println();
            System.out.println("---------Edit Camp Information---------");
            System.out.println("1.  Camp Name                          ");
            System.out.println("2.  Camp Dates                         ");
            System.out.println("3.  Camp Registration Closing Date     ");
            System.out.println("4.  Camp Organising Faculty            ");
            System.out.println("5.  Camp Location                      ");
            System.out.println("6.  Camp Participation Slots           ");
            System.out.println("7.  Camp Committee Slots               ");
            System.out.println("8.  Camp Description                   ");
            System.out.println("9.  Staff In Charge of Camp            ");
            System.out.println("10. Camp Visibility                    ");
            System.out.println("11. Quit to Main Menu                  ");
            System.out.println("---------------------------------------");

            System.out.println("Enter Camp details you wish to change: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(choice)
            {
                case 1: //Camp Name
                    System.out.println("Enter Camp Name: ");
                    String campName = sc.nextLine();
                    camp.setCampName(campName);
                    break;
                case 2: //Camp Dates
                    System.out.println("Enter Camp Start Date (YYYY-MM-DD): ");
                    String startCamp = sc.nextLine();
                    camp.setStartCampDate(startCamp);

                    System.out.println("Enter Camp End Date (YYYY-MM-DD): ");
                    String endCamp = sc.nextLine();
                    camp.setEndCampDate(endCamp);
                    break;
                case 3: //Camp Registration Closing Date
                    System.out.println("Enter Camp Registration Closing Date (YYYY-MM-DD): ");
                    String regCloseDates = sc.nextLine();
                    camp.setClosingRegDates(regCloseDates);
                    break;
                case 4: //Camp Organising Faculty
                    System.out.println("Enter Camp's Organising Faculty: ");
                    String campFacu = sc.nextLine();
                    camp.setFaculty(campFacu);
                    break;
                case 5: //Camp Location
                    System.out.println("Enter Camp Location: ");
                    String campLocation = sc.nextLine();
                    camp.setLocation(campLocation);
                    break;
                case 6: //Camp Participation Slots
                    int currentSignups = camp.getTotalSlots() - camp.getRemainingSlots(); 
                    boolean signUps = false;
                    do
                    {
                        System.out.println("Enter Number of Participation Slots: ");
                        int campSlots = sc.nextInt();

                        if(campSlots - currentSignups < 0)
                        {
                            System.out.println("Error! Too little slots based on current signups");
                            continue;
                        }
                        
                        camp.setTotalSlots(campSlots);
                        camp.setRemainingSlots(campSlots - (currentSignups));
                        signUps = true;

                    }while(signUps != true);
                    break;
                case 7: //Camp Description
                    int currentCommSignups = camp.getCampCommitteeSlots() - camp.getCampCommitteeRemainingSlots();
                    boolean pass = false;
                    
                    do{
                        System.out.println("Enter Number of Camp Committee Slots");
                        int campCommSlots = sc.nextInt();
                        
                        if(campCommSlots - currentCommSignups < 0)
                        {
                            System.out.println("Error! Too little Slots based on current signups");
                            continue;
                        }
                        
                        camp.setTotalSlots(campCommSlots);
                        camp.setRemainingSlots(campCommSlots - currentCommSignups);
                        pass = true;

                    }while(pass != true);

                    break;
                
                case 8:
                    System.out.println("Enter Camp's Description: ");
                    String campDesc = sc.nextLine();
                    camp.setDescription(campDesc);
                    break;
                case 9: //Staff In Charge of Camp
                    System.out.println("Enter Camp's Staff In Charge: ");
                    String campStaff = sc.nextLine();
                    camp.setStaffInCharge(campStaff);
                    break;
                case 10:
                    campVisibility(camp, camp.getVisibility());
                    break;
                case 11:
                    break;
                default:    
                    System.out.println("Invalid Option!");
            }

        }while(choice != 11);
    }

    
    /** 
     * This method returns the camp index
     * @param campIndex Camp index to be returned
     * @return int Returns camp index
     */
    public int updateCampIndex(int campIndex)
    {
        return campIndex;
    }
    
    /** 
     * This method toggles a camp visibility based on user input
     * @param camp CampInformation object
     * @param toggle Boolean variable to toggle a camp's visibility
     */
    //toggle the visibility of the camp to be “on” or “off
    //reflected in the camp list that will be visible to students
    public void campVisibility(CampInformation camp, boolean toggle)
    {
        Scanner sc = new Scanner(System.in);
        String choice1 = "";
        if(toggle)
        {
            System.out.println("Visibility ON");
            System.out.println("Turn visiblity off?(Y/N)");
            do
            {
                choice1 = sc.nextLine().toUpperCase();
                
                if(choice1.equals("Y"))
                {
                    camp.changeVisiblity(!toggle);
                    break;
                }
                
                else if(!choice1.equals("Y") | !choice1.equals("N"))
                {
                    System.out.println("Invalid Input! Enter Y or N");
                    continue;
                }

            }while(!choice1.equals("Y") | !choice1.equals("N"));
        }
        else
        {
            System.out.println("Visibility OFF");
            System.out.println("Turn visiblity on?(Y/N)");

            do{
                choice1 = sc.nextLine().toUpperCase();

                if(choice1.equals("Y"))
                {
                    camp.changeVisiblity(!toggle);
                    break;
                }
                
                else if(!choice1.equals("Y") | !choice1.equals("N"))
                {
                    System.out.println("Invalid Input! Enter Y or N");
                    continue;
                }

            }while(!choice1.equals("Y") | !choice1.equals("N"));
        }
    }
    
    /** 
     * This method lists and selects camps created or managed by a Staff in charge, returning the array index of the selected camp.
     * @param camp[] CampInformation object
     * @param index Index of camp selected
     * @return int Returns selected camp index
     */
    //list current list of camps in the excel that the staff is incharge/created and allow user to select the camp they want to edit, return array index of camp
    public int campSelection(CampInformation camp[], int index)
    {
        int count = 1;
        System.out.println();
        System.out.println("---------Camp Selection---------");
        for(int i = 0; i < index + 1; i++)
        {
            if(camp[i].getStaffInCharge().equals(this.getUserID()))
            {
                System.out.println((count) + ": " + camp[i].getCampName());
                count++;
            }
            
        }

        index = sc.nextInt() - 1;
        System.out.println();
        return index;
    }
}
