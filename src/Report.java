import java.util.Scanner;

/**
 * This class provides methods to generate camp reports and performance reports. It allows selection of filters for participants, committee members, and sorting criteria for staff in TXT format.
 * @version 1.0
 * @author Aaron Lim
 * @since 24/11/2023
*/
public class Report{

    /** This method generates a camp report for a specified camp, allowing selection of filters for participants and committee members. The output is in TXT format.
     * @param involvedCamp CampInformation object
     * @param user Camp Committee Member object
     */
    public void generateCampReport(CampInformation involvedCamp, CampCommitteeMember user){ //this method is ran when a CCM calls it (method overloading)
        ReportCSV reportCSV = new ReportCSV();
        if(involvedCamp==null){
            return;
        }

        Scanner sc = new Scanner(System.in);
        CampInformation[] involvedCampArray = {involvedCamp};
        
        System.out.println();
        System.out.println("----Select Student filters---");
        System.out.println("1. Participants and Camp Committee Members");
        System.out.println("2. Participants Only");
        System.out.println("3. Camp Committee Members Only");

        int filterChoice = sc.nextInt();
        reportCSV.generateCampReportTXT(involvedCampArray, filterChoice); //have to convert to an array despite only have one camp in order to pass in as an argument
    }

    /** 
     * This method generates a camp report for a specified camp, allowing selection of filters for participants and committee members. The output is in TXT format.
     * @param involvedCampArray CampInformation[] object
     * @param user Staff object
     */
    public void generateCampReport(CampInformation[] involvedCampArray, Staff user){ //this method is ran when a staff calls it (method overloading), pass in array as Staff can be involved in multiple camps
        ReportCSV reportCSV = new ReportCSV();
        if(involvedCampArray==null){
            System.out.println();
            System.out.println("No camps exist with current filter. No report generated");
            return;
        }
        Scanner sc = new Scanner(System.in);
        
        System.out.println();
        System.out.println("----Select Student filters---");
        System.out.println("1. Participants and Camp Committee Members");
        System.out.println("2. Participants Only");
        System.out.println("3. Camp Committee Members Only");

        int filterChoice = sc.nextInt();
        reportCSV.generateCampReportTXT(involvedCampArray, filterChoice);
    }

    
    /** 
     * This method generates a performance report for staff involved in multiple camps. Users can choose sorting criteria, such as alphabetical order or points, in TXT format.
     * @param involvedCampArray CampInformation[] object
     * @param user Staff object
     */
    public void generatePerformanceReport(CampInformation[] involvedCampArray, Staff user){ //this method is ran when a staff calls it (method overloading), pass in array as Staff can be involved in multiple camps
        PerformanceReportCSV csv = new PerformanceReportCSV();
        Scanner sc = new Scanner(System.in);
        int filterChoice;

        do{
        System.out.println();
        System.out.println("----Select Sorting Type---");
        System.out.println("1. By Alphabetical Order");
        System.out.println("2. In Descending Order of Points");
        System.out.println("3. In Ascending Order of Points");
        
        filterChoice = sc.nextInt();

        if(filterChoice<1 || filterChoice>3)
        System.out.println("Invalid option. Try again");

        }while(filterChoice<1 || filterChoice>3);
        
        csv.generatePerformanceReportTXT(involvedCampArray,filterChoice);
    }
}       


