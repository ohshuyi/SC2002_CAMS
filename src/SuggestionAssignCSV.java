import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.*;

/**
 * SuggestionAssignCSV manages camp-related suggestions. It allows Camp Committee members to delete, edit, and add suggestions to a file, incorporating features such as specifying approval status and appending suggestions to an existing CSV file. 
 * @version 1.0
 * @author Keith Ang
 * @since 24/11/2023
*/

public class SuggestionAssignCSV extends AssignCSV{
    
    /** 
     * This method enables the deletion of suggestions in a camp. It removes the specified suggestion at the given index from the file, updating the content accordingly.
     * @param camp Camp object
     * @param campFolderName Selected camp folder name to be deleted
     * @param fileName Selected camp file name to be deleted
     * @param suggestionEditIndex Selected index to be deleted
     */
    // Allows a camp committee to delete a sent suggestion
    public void deleteSuggestions(CampInformation camp, String campFolderName, String fileName, int suggestionEditIndex){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        
        try {
            List<String> lines = Files.readAllLines(filePath);

            // Ensure the index is valid
            if (suggestionEditIndex >= 0 && suggestionEditIndex < lines.size()) {
                lines.remove(suggestionEditIndex);

                // Write the updated content back to the file
                Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                System.out.println("Invalid index for deletion.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * This method allows the editing of suggestions in a camp. It modifies the specified suggestion at the given index in the file, updating the content accordingly.
     * @param camp Camp object
     * @param campFolderName Selected camp folder name to be edited
     * @param fileName Selected camp file name to be edited
     * @param suggestionEditIndex Selected index to be edited
     * @param suggestionEditReply Reply string to be edited
     */
    public void assignExcel(CampInformation camp, String campFolderName, String fileName, int suggestionEditIndex, String suggestionEditReply){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            int index = 0;
            StringBuilder newContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (index++ == suggestionEditIndex) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String sender = parts[1];
                        String updatedLine = String.format("%s,%s,%s%n", suggestionEditReply, sender, "false");
                        newContent.append(updatedLine);
                    }
                } else{
                    newContent.append(line).append("\n");
                    continue;
                }
            }
            try (FileWriter writer = new FileWriter(filePath.toString())) {
                writer.write(newContent.toString());
                System.out.println("Edit Sucessful");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * This method adds a new suggestion to a camp's suggestion file. It appends the provided message, the camp committee member's name, and an approval status to the file. If successful, it prints "Suggestion Sent!" to the console; otherwise, it prints "Suggestion Unsuccessful!" and displays the stack trace for any encountered exceptions.
     * @param message Message string to be added
     * @param campComName Camp committee name
     * @param campFolderName Selected camp file folder to be edited
     * @param fileName Selected camp file name to be edited
     * @param approval Value to indicated accepted/unaccepted suggestion
     */
    // Allows a Camp Committee to add Suggestions
    public void addSuggestions(String message, String campComName, String campFolderName, String fileName, boolean approval){
        try {
            Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
            // String folderPath = "./Camps/" + excelFilePath + "/";
            // Open the Excel file in append mode
            FileWriter writer = new FileWriter(filePath.toString(), true);
            String csvLine = String.format("%s,%s,%s\n", message, campComName, approval);
            // Write the enquiry to a new line from the first column (assuming a CSV format)
            writer.append(csvLine);

            writer.flush();
            writer.close();
            System.out.println("Suggestion Sent!");

            // implement the add points systen

        } catch (IOException e) {
            System.out.println("Suggestion Unsuccessful!");
            e.printStackTrace();
        }
    }


    
    /** 
     * This method adds points to the respondent after suggestion is sent
     * @param camp Camp object
     * @param respondent Add points to respondent
     * @param campFolderName Selected camp folder name to be deleted
     * @param fileName Selected camp file name to be deleted
     */
    public void addPointsAssign(CampInformation camp, String respondent, String campFolderName, String fileName)
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
