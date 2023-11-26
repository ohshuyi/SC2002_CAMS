import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.nio.file.*;
/**
 * ReportCSV generates TXT camp reports with participant and Camp Committee Member details, saved in "Camps/Camp Reports" directory with a unique ID.
 * @version 1.0
 * @author Aaron Lim
 * @since 24/11/2023
*/
public class ReportCSV extends CSVManager{
    
    /** 
     * This method generates a TXT camp report with camp details and participant/committee lists. Saved with a unique ID in "Camps/Camp Reports." Supports various filtering options for detailed reporting.
     * @param camp Camp object
     * @param filter Filter choice
     */
    //generates Camp Report in TXT format
    public void generateCampReportTXT(CampInformation[] camp,int filter){
        int size = camp.length;
        String uniqueID = UUID.randomUUID().toString(); 
        String folderPath = "Camps/Camp Reports";
        String txtFileName = folderPath + "/Camp Report " + "#" + uniqueID + ".txt"; //generates camp report with a random ID

        for(int i=0;i<size;i++){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName, true))) {

            // Writing campInfo data
                writer.write("Camp Name: " + camp[i].getCampName());
                writer.newLine();
                writer.write("Start Date: " + camp[i].getStartCampDate());
                writer.newLine();
                writer.write("End Date: " + camp[i].getEndCampDates());
                writer.newLine();
                writer.write("Registration Closing Date: " + camp[i].getClosingRegDates());
                writer.newLine();
                writer.write("Faculty: " + camp[i].getFaculty());
                writer.newLine();
                writer.write("Location: " + camp[i].getLocation());
                writer.newLine();
                writer.write("Total Slots: " + camp[i].getTotalSlots());
                writer.newLine();
                writer.write("Remaining Slots: " + camp[i].getRemainingSlots());
                writer.newLine();
                writer.write("Camp Committee Slots: " + camp[i].getCampCommitteeSlots());
                writer.newLine();
                writer.write("Staff in Charge: " + camp[i].getStaffInCharge());
                writer.newLine();
                writer.write("Description: " + camp[i].getDescription());
                writer.newLine();
                // Add more lines for other fields if needed
                // ...

                writer.newLine();
        
            
            switch(filter){
                case 1: //both Partipants and CCM
                String[] CMMUserNameArray = createCCMUserNameArray(camp[i]);  //for CCM members 
                writer.write("Camp Committee Members:");
                writer.newLine();

                for(int a=0;a<CMMUserNameArray.length;a++){
                    writer.write((a+1) + ". " + CMMUserNameArray[a]);
                    writer.newLine();
                }

                writer.newLine();

                String[] participantUserNameArray = createParticipantUserNameArray(camp[i]); //for partipants
                writer.write("Participants:");
                writer.newLine();

                for(int a=0;a<participantUserNameArray.length;a++){
                    writer.write((a+1) + ". " + participantUserNameArray[a]);
                    writer.newLine();
                }
                break;

                case 2: //for Participants Only
                participantUserNameArray = createParticipantUserNameArray(camp[i]);
                writer.write("Participants:");
                writer.newLine();

                for(int a=0;a<participantUserNameArray.length;a++){
                    writer.write((a+1) + ". " + participantUserNameArray[a]);
                    writer.newLine();
                }
                break;

                case 3: //for CCM Only
                CMMUserNameArray = createCCMUserNameArray(camp[i]);
                writer.write("Camp Committee Members:");
                writer.newLine();

                for(int a=0;a<CMMUserNameArray.length;a++){
                    writer.write((a+1) + ". " + CMMUserNameArray[a]);
                    writer.newLine();
                }
                break;
            }

            writer.newLine();
            writer.write("--------------------------------------------");
            writer.newLine();
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        System.out.println();
        System.out.println("Camp Report #" + uniqueID + " has been created successfully!");
        System.out.println();
    }

    /** 
     * This method Returns an alphabetically sorted array of participant usernames from a specified camp using data from "_Participant.csv" in the "Camps" directory.
     * @param camp Camp object
     * @return String[] Returns filtered participant array
     */
    //returns alphabetically sorted array of partipant UserNames from specified camp
    public String[] createParticipantUserNameArray(CampInformation camp){
        String participantFile = camp.getCampName() + "_Participant.csv";
        Path participantFilePath = Paths.get(".", "Camps", camp.getCampName(), participantFile);

        List<String> participantUserNameAL = new ArrayList<String>();

        try{BufferedReader br = new BufferedReader(new FileReader(participantFilePath.toString()));
        String header = br.readLine();
        String line1 = "";

        while((line1 = br.readLine()) != null){
            participantUserNameAL.add(line1); //add each username into arraylist
        }
        }
        catch (IOException e){
            e.printStackTrace();
        }   

        Collections.sort(participantUserNameAL);   //sort arraylist by alphabetical order

        String[] participantUserNameArray = new String[participantUserNameAL.size()]; //create array with the same size as arraylist
        participantUserNameArray = participantUserNameAL.toArray(participantUserNameArray); 
        
        return participantUserNameArray;
    }

    
    /** 
     * This method returns an alphabetically sorted array of Camp Committee Members' usernames from a specified camp using data from "_CampCommitteeMember.csv" in the "Camps" directory.
     * @param camp Camp object
     * @return String[] Returns filtered Camp Committee Member array
     */
    //returns alphabetically sorted array of CCM UserNames from specified camp
    public String[] createCCMUserNameArray(CampInformation camp){ 
        String campCommitteMemberFile = camp.getCampName() + "_CampCommitteeMember.csv";
        Path CMfilePath = Paths.get(".", "Camps", camp.getCampName(), campCommitteMemberFile);

        List<String> CCMUserNameAL = new ArrayList<String>();
        
        try{BufferedReader br = new BufferedReader(new FileReader(CMfilePath.toString()));
        String header = br.readLine();
        String line1 = "";

        while((line1 = br.readLine()) != null){
            line1 = line1.replaceAll(",\\d{1,2}",""); //create string consisting of the UserName, remove points up to 2 digits
            CCMUserNameAL.add(line1); //add each username into arraylist
        }
        }
        catch (IOException e){
            e.printStackTrace();
        }   

        Collections.sort(CCMUserNameAL);   //sort arraylist by alphabetical order

        String[] CMMUserNameArray = new String[CCMUserNameAL.size()]; //create array with the same size as arraylist
        CMMUserNameArray = CCMUserNameAL.toArray(CMMUserNameArray); 

        return CMMUserNameArray;
    }
}
