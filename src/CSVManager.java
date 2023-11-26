import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * CSVManager provides methods to check if a given name is a Camp Committee Member or a participant in a camp, based on the provided file path and name. The methods return true if the name matches the criteria, otherwise false.
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/
public class CSVManager {
    /** 
     * This method checks if a provided student is a committee member of a camp by comparing their name with the contents of the specified Excel file, removing points information.
     * @param classCommitteeMemberName Camp Committee Member's name
     * @param excelFilePath Folder and file path to be updated
     * @return boolean Returns True if name provided is a Camp Committee Membero f a camp, else returns False
     */
    // Returns true if student is a committee member of a camp
    public boolean checkifClassCommitteeMember(String classCommitteeMemberName, String excelFilePath){
        try{BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
        // String header = br.readLine();
        String line1 = "";

        while((line1 = br.readLine()) != null){
            line1 = line1.replaceAll(",\\d{1,2}",""); //create string consisting of the UserName
            if (classCommitteeMemberName.equals(line1)){
                return true;
            }
        }
        }
        catch (IOException e) {
        e.printStackTrace();
        }    
        return false;
    }

    /** 
     * This method verifies if a provided name corresponds to a participant by comparing it with the contents of the specified Excel file, considering the first line as the header.
     * @param participantName Participant's name
     * @param excelFilePath Folder and file path to be updated
     * @return boolean boolean Returns True if name provided is a participant of a camp, else returns False
     */
    public boolean checkifParticipant(String participantName, String excelFilePath){
        try{BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
        String header = br.readLine();
        String line1 = "";

        while((line1 = br.readLine()) != null){
            if (participantName.equals(line1)){
                return true;
            }
        }
        }
        catch (IOException e) {
        e.printStackTrace();
        }    
        return false;
    }
}
