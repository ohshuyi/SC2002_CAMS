/**
 * ManageReportCM initiates camp report generation, utilizing the Report class for creation based on Camp Committee Member details
 * @version 1.0
 * @author Aaron Lim
 * @since 24/11/2023
*/

public class ManageReportCM extends CampCommitteeMember {
    /** 
     * Constructor for the ManageReportCM class. It initializes a ManageReportCM object with the provided Camp Committee Member information.
     * @param userName User name of camp camp committee member
     * @param userID User ID of camp camp committee member
     * @param password Password of camp camp committee member
     * @param faculty Faculty of camp camp committee member
     * @param email Email of camp camp committee member
     * @param profile Profile type of camp camp committee member
     */
    public ManageReportCM(String userName, String userID, String password, String faculty, String email, int profile) {
        super(userName, userID, password, faculty, email, profile);
    }

    /** 
     * This method initiates the generation of a camp report for a specific camp, utilizing the Report class to handle the report creation process.
     * @param InvolvedCamp CampInformation object
     */

    public void generateCampReport(CampInformation InvolvedCamp) 
    { // generates campreport
        Report campNewReport = new Report();
        campNewReport.generateCampReport(InvolvedCamp, this);
    }
}
