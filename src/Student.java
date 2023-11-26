import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * Student has methods for viewing the camp list, managing camp directory, and handling camp registrations for participants and committee members.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class Student extends User {
  /**
   * Scanner object for user input 
   */
  Scanner sc = new Scanner(System.in);
  /** 
    * Constructor for the Student class. It initializes a Student object with the provided Student information.
     * @param userName The User's Login username
     * @param userID The User's userID
     * @param password The User's Login password
     * @param faculty The User's faculty
     * @param email The User's email
     * @param profile student's file post-fix, replaced with your CSV file's name
    */
  public Student(String userName, String userID, String password, String faculty, String email, int profile) {
    super(userName, userID, password, faculty, email, profile);
  }

  /** 
   * This method allows a student to view the list of available camps based on faculty and visibility. It prompts the user to choose a camp and returns the corresponding index.
   * @param camp[] CampInformation object
   * @param index Total number of Camps
   * @return int Returns chosens camp's index
   */
  // function to viewCampList
  // A student can see the camps that his/her has already registered for and
  // his/her roles (attendees OR camp committee)
  public int viewCampList(CampInformation camp[], int index) {
    /**
     * First counter, count
     */
    int count = 1;
    /**
     * Second counter, count 2
     */
    int count2 = 0;
    /**
     * array that stores the camp's indexes
     */
    int[] campIndex = new int[10];
    System.out.println("-----Camp Directory-----");

    for (int i = 0; i < index; i++) {
      if (camp[i].getFaculty().equals(this.getFaculty()) && camp[i].getVisibility()
          || camp[i].getFaculty().equals("NTU") && camp[i].getVisibility()) {
        System.out.println(count++ + "." + camp[i].getCampName());
        campIndex[count2] = i;
        count2++;
      }
    }
    System.out.println();
    System.out.printf("Choose a camp: ");
    int choice;
    choice = sc.nextInt();
    System.out.println();
    return campIndex[choice - 1];
  }

  
  /** 
   * This method directs students to manage a selected camp based on their role (committee member or participant), handling registration and interaction with camp details.
   * @param camp[] CampInformation object
   * @param index Selected camp index
   * @param studentList[] Student object
   * @param studentIndex Selected student index
   */
  public void campDirectory(CampInformation camp[], int index, Student studentList[], int studentIndex) {
    /**
     * Initialises an object of CampUpdateCSV
     */
    CampUpdateCSV csvU = new CampUpdateCSV();
    /**
     * Initialises an object of CampAssignCSV
     */
    CampAssignCSV csvA = new CampAssignCSV();
    System.out.println();
    /**
     * Holds selected camp details
     */
    int i = viewCampList(camp, index);
    /**
     * Gives filepath to access a Participant's file
     */
    Path filePathParticipant = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_Participant.csv");
    /**
     * Gives filepath to access a Camp Committee Member's file
     */
    Path filePathCM = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_CampCommitteeMember.csv");
    
    if(csvU.checkifClassCommitteeMember(this.getUserName(), filePathCM.toString()))
    {
      CampCommitteeMember c = new CampCommitteeMember(studentList[studentIndex].getUserName(), studentList[studentIndex].getUserID(), studentList[studentIndex].getPassword(), studentList[studentIndex].getFaculty(), studentList[studentIndex].getEmail(), studentList[studentIndex].getProfile());
      c.campManagementC(camp[i], c);

    }
    
    else if(csvU.checkifParticipant(this.getUserName(), filePathParticipant.toString()))
    {
      Participant p = new Participant(studentList[studentIndex].getUserName(), studentList[studentIndex].getUserID(), studentList[studentIndex].getPassword(), studentList[studentIndex].getFaculty(), studentList[studentIndex].getEmail(), studentList[studentIndex].getProfile());
      p.campManagementP(camp[i], p);
      csvU.updateExcelFile(camp, "camp.csv" , index, "Camps");
      csvA.assignExcelFile(camp, "camp.csv", "Camps");
    }
    
    else
    {
      registrationCamp(camp, i);
      csvU.updateExcelFile(camp, "camp.csv" , index, "Camps");
      csvA.assignExcelFile(camp, "camp.csv", "Camps");

      return;
    }
    System.out.println("");
  }

  
  /** 
   * This method facilitates camp registration for participants or committee members, displaying camp details and prompting users to choose their registration type.
   * @param camp[] Campinformation object
   * @param index Selected camp index
   */
  public void registrationCamp(CampInformation camp[], int index)
  {
    ListCampDetails listCampDetails = new ListCampDetails(camp[index].getCampName(), camp[index].getStartCampDate(), camp[index].getEndCampDates(),
    camp[index].getClosingRegDates(), camp[index].getFaculty(), camp[index].getLocation(), camp[index].getTotalSlots(),
    camp[index].getRemainingSlots(), camp[index].getCampCommitteeSlots(),
    camp[index].getCampCommitteeRemainingSlots(), camp[index].getDescription(), camp[index].getStaffInCharge(),
    camp[index].getVisibility());

    listCampDetails.viewRemainingSlot(camp[index]);
    
    System.out.println("What do you want to register as?");
    System.out.println("1. Camp Participant");
    System.out.println("2. Camp Committee Member");
    System.out.println("3. Return to Home Page");
    
    int campChoice = sc.nextInt();
    
    if (campChoice == 1)
    {
      registerAsParticipant(camp, index);
      return;
    }

    else if(campChoice == 2)
    {
      registerAsCommiteeMember(camp, index);
      return;
    }

    else
      return;
  }

  /** 
   * This method facilitates camp registration for participants, checking conditions such as registration deadlines, available slots, and date clashes with existing registrations.
   * @param camp[] CampInformation object
   * @param index Selected camp index
   */
  public void registerAsParticipant(CampInformation camp[], int index) {

    CampUpdateCSV csvU = new CampUpdateCSV();
    String participantFile = camp[index].getCampName() + "_Participant.csv";
    String commMemFile = camp[index].getCampName() + "_CampCommitteeMember.csv";
    Path filePath = Paths.get(".", "Camps", camp[index].getCampName(), participantFile);
    Path filePath2 = Paths.get(".", "Camps", camp[index].getCampName(), commMemFile);

    String fileName = camp[index].getCampName() + "_Participant.csv";
    String line1 = "", line2 = "";

    LocalDate currentDate = LocalDate.now();
    LocalDate campRegClosingDate = LocalDate.parse(camp[index].getClosingRegDates());
    LocalDate campToRegStartDate = LocalDate.parse(camp[index].getStartCampDate());
    LocalDate campToRegEndDate = LocalDate.parse(camp[index].getEndCampDates());
    LocalDate involvedCampStartDate = null;
    LocalDate involvedCampEndDate = null;
    List<CampInformation> involvedCampsAL = new ArrayList<CampInformation>();
    CSVManager csvManager = new CSVManager();
    List<LocalDate> involvedStartDateAL = new ArrayList<LocalDate>(); //
    List<LocalDate> involvedEndDateAL = new ArrayList<LocalDate>(); // index i from both arrays correspond to a single range from an involved camp

    try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
        BufferedReader br2 = new BufferedReader(new FileReader(filePath2.toString()))) {
      String header = br.readLine();
      while ((line1 = br.readLine()) != null || (line2 = br2.readLine()) != null) {
        if (this.getUserName().equals(line1)) {
          System.out.println("Unable to register - Already Registered as a Participant");
          System.out.println();
          return;
        }

        else if (this.getUserName().equals(line2)) {
          System.out.println("Unable to register - Already Registered as a Camp Committee Member");
          System.out.println();
          return;
        }

        else if (camp[index].getRemainingSlots() == camp[index].getCampCommitteeRemainingSlots()) { //camp participants slots are full since remaining slots include camp committee slots
          System.out.println("Camp is Full!");
          System.out.println();
          return;
        }

        else if (campRegClosingDate.compareTo(currentDate) < 0){
          System.out.println();
          System.out.println("Unable to register - Camp registration was closed on " + campRegClosingDate + ". Current date is " + currentDate + ".");
          System.out.println();
          return;
        }
        }

        int i = 0;

        //checks for any clashing dates 1/2
        do{
          Path filePathParticipant = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_Participant.csv");
          Path filePathCM = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_CampCommitteeMember.csv");

          if(csvManager.checkifParticipant(this.getUserName(),filePathParticipant.toString())){
            involvedCampStartDate = LocalDate.parse(camp[i].getStartCampDate());
            involvedCampEndDate = LocalDate.parse(camp[i].getEndCampDates());
            involvedCampsAL.add(camp[i]);
            involvedStartDateAL.add(involvedCampStartDate);
            involvedEndDateAL.add(involvedCampEndDate);
          }

          if(csvManager.checkifClassCommitteeMember(this.getUserName(), filePathCM.toString())){
            involvedCampStartDate = LocalDate.parse(camp[i].getStartCampDate());
            involvedCampEndDate = LocalDate.parse(camp[i].getEndCampDates());
            involvedCampsAL.add(camp[i]);
            involvedStartDateAL.add(involvedCampStartDate);
            involvedEndDateAL.add(involvedCampEndDate); // index i from both arrays correspond to a single range from an already involved camp
          }

          i++;

        }while(camp[i] != null);

        //checks for any clashing dates 2/2
        for(i=0;i<involvedStartDateAL.size();i++){
          LocalDate tempStartDate = involvedStartDateAL.get(i);
          LocalDate tempEndDate = involvedEndDateAL.get(i);
          if(tempStartDate.compareTo(campToRegEndDate) <= 0 && campToRegStartDate.compareTo(tempEndDate) <= 0){ //(StartDate1 <= EndDate2) and (StartDate2 <= EndDate1) for overlapping date ranges
            System.out.println();
            System.out.println("Unable to register - Camp '" + camp[index].getCampName() + "' dates '" + camp[index].getStartCampDate() + " to " + camp[index].getEndCampDates() + "' conflict with existing camp '" + involvedCampsAL.get(i).getCampName() + "' dates '" + tempStartDate + " to " + tempEndDate + "'.");
            return;
          }
        }

        //if any of the checks return, no registration is done.
    } catch (IOException e) {
      e.printStackTrace();
    }
    csvU.addMembers(super.getUserName(), camp[index].getCampName(), fileName, 1);
    camp[index].setRemainingSlots(camp[index].getRemainingSlots() - 1);
  }

  /** 
   * This function enables registration as a camp committee member, ensuring conditions such as registration deadlines, available committee slots, and absence of date clashes with existing registrations.
   * @param camp[] CampInformation object
   * @param index Selected camp index
   */
  // function to registerAsCommiteeMember
  public void registerAsCommiteeMember(CampInformation camp[], int index) {
    // A student is only able to be in the camp committee for one camp but can
    // attend multiple camps.
    // The status of the student as a camp committee will be reflected in their
    // profile.
    CampUpdateCSV csvU = new CampUpdateCSV();
    String filePath = "./Camps/" + camp[index].getCampName() + "/" + camp[index].getCampName()
        + "_CampCommitteeMember.csv";
    String filePath2 = "./Camps/" + camp[index].getCampName() + "/" + camp[index].getCampName() + "_Participant.csv";
    String fileName = camp[index].getCampName() + "_CampCommitteeMember.csv";
    String line1 = "";
    String line2 = "";

    LocalDate currentDate = LocalDate.now();
    LocalDate campRegClosingDate = LocalDate.parse(camp[index].getClosingRegDates());
    LocalDate campToRegStartDate = LocalDate.parse(camp[index].getStartCampDate());
    LocalDate campToRegEndDate = LocalDate.parse(camp[index].getEndCampDates());
    LocalDate involvedCampStartDate = null;
    LocalDate involvedCampEndDate = null;
    List<CampInformation> involvedCampsAL = new ArrayList<CampInformation>();
    CSVManager csvManager = new CSVManager();
    List<LocalDate> involvedStartDateAL = new ArrayList<LocalDate>(); //
    List<LocalDate> involvedEndDateAL = new ArrayList<LocalDate>(); // index i from both arrays correspond to a single range from an involved camp

    try (BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedReader br2 = new BufferedReader(new FileReader(filePath2))) {
      String header = br.readLine();
      while ((line1 = br.readLine()) != null || (line2 = br2.readLine()) != null) {

        if (this.getProfile() == 2) {
          System.out.println();
          System.out.println("Unable to register - Already Registered as a Camp Comittee Member");
          System.out.println();
          return;
        } else if (this.getUserName().equals(line2)) {
          System.out.println();
          System.out.println("Unable to register - Already Registered as a Participant");
          System.out.println();
          return;
        } else if (camp[index].getCampCommitteeRemainingSlots() == 0) {
          System.out.println();
          System.out.println("Camp Committee is Full!");
          System.out.println();
          return;
        }

        else if (campRegClosingDate.compareTo(currentDate) < 0){
          System.out.println();
          System.out.println("Unable to register - Camp registration was closed on " + campRegClosingDate + ". Current date is " + currentDate + ".");
          System.out.println();
          return;
        }
      }

      int i = 0;

        //checks for any clashing dates 1/2
        do{
          Path filePathParticipant = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_Participant.csv");
          Path filePathCM = Paths.get(".", "Camps", camp[i].getCampName(),camp[i].getCampName() + "_CampCommitteeMember.csv");

          if(csvManager.checkifParticipant(this.getUserName(),filePathParticipant.toString())){
            involvedCampStartDate = LocalDate.parse(camp[i].getStartCampDate());
            involvedCampEndDate = LocalDate.parse(camp[i].getEndCampDates());
            involvedCampsAL.add(camp[i]);
            involvedStartDateAL.add(involvedCampStartDate);
            involvedEndDateAL.add(involvedCampEndDate);
          }

          if(csvManager.checkifClassCommitteeMember(this.getUserName(), filePathCM.toString())){
            involvedCampStartDate = LocalDate.parse(camp[i].getStartCampDate());
            involvedCampEndDate = LocalDate.parse(camp[i].getEndCampDates());
            involvedCampsAL.add(camp[i]);
            involvedStartDateAL.add(involvedCampStartDate);
            involvedEndDateAL.add(involvedCampEndDate); // index i from both arrays correspond to a single range from an already involved camp
          }

          i++;

        }while(camp[i] != null);

        //checks for any clashing dates 2/2
        for(i=0;i<involvedStartDateAL.size();i++){
          LocalDate tempStartDate = involvedStartDateAL.get(i);
          LocalDate tempEndDate = involvedEndDateAL.get(i);
          if(tempStartDate.compareTo(campToRegEndDate) <= 0 && campToRegStartDate.compareTo(tempEndDate) <= 0){ //(StartDate1 <= EndDate2) and (StartDate2 <= EndDate1) for overlapping date ranges
            System.out.println();
            System.out.println("Unable to register - Camp '" + camp[index].getCampName() + "' dates '" + camp[index].getStartCampDate() + " to " + camp[index].getEndCampDates() + "' conflict with existing camp '" + involvedCampsAL.get(i).getCampName() + "' dates '" + tempStartDate + " to " + tempEndDate + "'.");
            return;
          }
        }

    } catch (IOException e) {
      e.printStackTrace();
    }
    csvU.addMembers(this.getUserName(), camp[index].getCampName(), fileName, 2);
    this.setProfile(2);
    camp[index].setCampCommitteeRemainingSlots(camp[index].getCampCommitteeRemainingSlots() - 1);
    camp[index].setRemainingSlots(camp[index].getRemainingSlots() - 1);

  }
  // A student is only able to be in the camp committee for one camp but can
  // attend multiple camps.
  // The status of the student as a camp committee will be reflected in their
  // profile.
}
