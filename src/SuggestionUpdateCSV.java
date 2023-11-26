import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
/**
 * SuggestionUpdateCSV provides methods for retrieving and printing suggestions from a camp's "suggestion.csv" file and allowing staff to approve or reject camp suggestions, updating the associated Excel file.
 * @version 1.0
 * @author Keith Ang
 * @since 24/11/2023
*/
public class SuggestionUpdateCSV extends UpdateCSV{
    /** 
      * The method retrieves suggestions to be listed out for User to view
      * @param camp Camp object
      * @param campFolderName Folder name to be retrieved
      * @param fileName File name to be retrieved
      */
     // Returns all suggestions from a camp's suggestion.csv and prints them out
    public void retrieveSuggestions(CampInformation camp, String campFolderName, String fileName){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            int index = 1;
            String line;
            boolean firstRow = true; // Flag to skip the first row
    
            while ((line = br.readLine()) != null) {
                if (firstRow) {
                    // Skip the first row (header)
                    firstRow = false;
                    continue;
                }
    
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String suggestion = parts[0];
                    String sender = parts[1];
                    System.out.println("Suggestion " + index + ": " + suggestion);
                    System.out.println("Sent by: " + sender);
                    System.out.println();
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Allows a staff to approve/reject to a camp's suggestions and updates excel
     * @param camp Camp object
     * @param suggestionIndex Index of suggestion to be approved
     * @param respondent Respondent's name
     * @param campFolderName Folder name to be approved
     * @param fileName File name to be approved
     * @return String returns Camp Committee name to add points 
     */
    public String updateExcelFile(CampInformation camp, int suggestionIndex, String respondent, String campFolderName, String fileName){
        Path filePathSugg = Paths.get(".", "Camps", campFolderName, fileName);
        String CCM="";
        try (BufferedReader br = new BufferedReader(new FileReader(filePathSugg.toString()))) {
            String line;
            int index = 0;
            StringBuilder newContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String sender = parts[1];
                if (index++ == suggestionIndex) {
                    if (parts.length == 3) {
                        String suggestion = parts[0];
                        sender = parts[1];
                        CCM = sender;
                        String updatedLine = String.format("%s,%s,%s%n", suggestion, sender, true);
                        newContent.append(updatedLine);
                    }
                } else{
                    newContent.append(line).append("\n");
                    continue;
                }
            }
            try (FileWriter writer = new FileWriter(filePathSugg.toString())) {
                writer.write(newContent.toString());
                System.out.println("Suggestion Approved!");
            } 
            } catch (IOException e) {
                e.printStackTrace();
            }
            return CCM;
    }

        
        /** 
         * Adds points to the respondent
         * @param camp Camp object
         * @param respondent Respondent's name
         * @param campFolderName Folder name to be approved
         * @param fileName File name to be approved
         */
        public void addPointsUpdate(CampInformation camp, String respondent, String campFolderName, String fileName)
    {
            String fileNameCM = camp.getCampName() + "_CampCommitteeMember.csv";
            Path filePathCM = Paths.get(".", "Camps", campFolderName, fileNameCM);
            
            try (BufferedReader br = new BufferedReader(new FileReader(filePathCM.toString()))) {
            String line;
            StringBuilder newContent = new StringBuilder();
            while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    
                    String sender = parts[0];
                    String point = parts[1];
                    if(respondent.equals(sender)){
                        int pointInt = Integer.parseInt(point);
                        pointInt++;
                        String updatedPoint = Integer.toString(pointInt);
                        String updatedLine = String.format("%s,%s%n", sender, updatedPoint);
                        newContent.append(updatedLine);
                        System.out.println(respondent + " current points: " + updatedPoint);
                        System.out.println();
                    }else{
                        newContent.append(line).append("\n");
                        continue;
                    }
            }
            try (FileWriter writer = new FileWriter(filePathCM.toString())) {
                writer.write(newContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
