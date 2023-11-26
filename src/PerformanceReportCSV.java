import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.nio.file.*;
/**
 * PerformanceReportCSV creates sorted array of Camp Committee Members' points and generates a TXT performance report for a camp, saved in "Camps/Performance Reports" directory with a unique ID.
 * @version 1.0
 * @author Aaron Lim
 * @since 24/11/2023
*/
public class PerformanceReportCSV {
    /**
     * Scanner object
     */
    Scanner sc = new Scanner(System.in);
    
    /** 
     * This method creates an array of camp committee members with their points based on the specified sorting choice. It reads a CSV file containing usernames and points, sorts them alphabetically or by points (ascending or descending), and returns the sorted array.
     * @param camp Camp object
     * @param sortChoice Sort choice
     * @return String[] Returns sorted array
     */
    //returns sorted array of CCM UserNames+Points from specified camp
    public String[] createCCMPointsArray(CampInformation camp, int sortChoice){ 
        String campCommitteMemberFile = camp.getCampName() + "_CampCommitteeMember.csv";
        Path CMfilePath = Paths.get(".", "Camps", camp.getCampName(), campCommitteMemberFile);

        List<String> CCMUserNameAL = new ArrayList<String>();
        
        try{BufferedReader br = new BufferedReader(new FileReader(CMfilePath.toString()));
        String header = br.readLine();
        String line1 = "";

        while((line1 = br.readLine()) != null){
            CCMUserNameAL.add(line1); //add each username+points into arraylist
        }
        }
        catch (IOException e){
            e.printStackTrace();
        }   

        switch(sortChoice){
            case 1:
                Collections.sort(CCMUserNameAL);   //sort arraylist by alphabetical order
                break;

            case 2:
                Collections.sort(CCMUserNameAL, new Comparator<String>() { //sort in descending order of points
                public int compare(String o1, String o2) {
                    return extractInt(o2) - extractInt(o1);
                }
            
                int extractInt(String s) {
                    String num = s.replaceAll("\\D", "");
                    // return 0 if no digits found
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                }
            });
            break;

            case 3:
                Collections.sort(CCMUserNameAL, new Comparator<String>() { //sort in ascending order of points
                public int compare(String o1, String o2) {
                    return extractInt(o1) - extractInt(o2);
                }
            
                int extractInt(String s) {
                    String num = s.replaceAll("\\D", "");
                    // return 0 if no digits found
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                }
            });
        }

        String[] CMMUserNameArray = new String[CCMUserNameAL.size()]; //create array with the same size as arraylist
        CMMUserNameArray = CCMUserNameAL.toArray(CMMUserNameArray); 

        return CMMUserNameArray;
    }

    /** 
     * This method creates an array of camp committee members with their points based on the specified sorting choice. It reads a CSV file containing usernames and points, sorts them alphabetically or by points (ascending or descending), and returns the sorted array.
     * @param camp Camp object
     * @param sortChoice Sort choice
     */
    //generates Performance Report in TXT format
    public void generatePerformanceReportTXT(CampInformation[] camp, int sortChoice){
        int size = camp.length;
        String uniqueID = UUID.randomUUID().toString(); 
        String folderPath = "Camps/Performance Reports";
        String txtFileName = folderPath + "/Performance Report " + "#" + uniqueID + ".txt"; //generates performance report with a random ID

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

                String[] CMMUserNameArray = createCCMPointsArray(camp[i],sortChoice);
                writer.write("Camp Committee Members | Points");
                writer.newLine();

                for(int a=0;a<CMMUserNameArray.length;a++){
                    writer.write((a+1) + ". " + CMMUserNameArray[a].replaceAll(",\\d{1,2}","") + " | " + CMMUserNameArray[a].replaceAll("[^0-9]", ""));
                    writer.newLine();
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
        System.out.println("Performance Report #" + uniqueID + " has been created successfully!");
        System.out.println();
    }
}


