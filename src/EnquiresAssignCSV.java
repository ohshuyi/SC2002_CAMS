import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.*;

/**
 * EnquiresAssignCSV manages camp-related enquiries, allowing students to delete, edit, and reply to their enquiries, with additional functionality for updating points in a Camp Committee Member's file based on their responses.
 * @version 1.0
 * @author Koh Yihao Kendrick
 * @since 24/11/2023
*/

public class EnquiresAssignCSV extends AssignCSV{
    /** 
     * This method deletes an enquiry from a camp's enquiry.csv file. It reads all lines, removes the specified index, and writes the updated content back to the file.
     * @param camp Camp object
     * @param campFolderName Selected camp folder name to be deleted
     * @param fileName Selected camp file name to be deleted
     * @param enquiryEditIndex Index to be deleted
     */
    // Allows a student to delete a sent enquiry
    public void assignExcel(CampInformation camp, String campFolderName, String fileName, int enquiryEditIndex){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        
        try {
            List<String> lines = Files.readAllLines(filePath);

            // Ensure the index is valid
            if (enquiryEditIndex >= 0 && enquiryEditIndex < lines.size()) {
                lines.remove(enquiryEditIndex);

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
     * This method enables a student to edit their enquiry in a camp. It reads the enquiry file, updates the specified index with the new reply, and writes the changes back to the file.
     * @param camp Camp object
     * @param campFolderName Selected camp folder name
     * @param fileName Selected camp file name
     * @param enquiryEditIndex Index of enquiry to be edited
     * @param enquiryEditReply Reply of enquiry to be edited
     */
    // Allows a student to edit his enquiry
    public void editEnquiry(CampInformation camp, String campFolderName, String fileName, int enquiryEditIndex, String enquiryEditReply){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            int index = 0;
            StringBuilder newContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (index++ == enquiryEditIndex) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        String sender = parts[1];
                        String updatedLine = String.format("%s,%s,%s,%s,open%n", enquiryEditReply, sender, " ",  " ");
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
     * This method allows for replying to an enquiry in a camp. It updates the specified enquiry with a reply, sets it to "closed" status, and records the respondent's information. Additionally, if the respondent is a Camp Committee Member (type "CM"), their points are updated in the corresponding file.
     * @param camp Camp object
     * @param enquiryIndex Index of enquiry to be edited
     * @param enquiryReply Reply of Staff/Committee Member
     * @param respondent Name of respondent Staff/Committee Member
     * @param campFolderName Name of camp folder to be edited
     * @param fileName Name of camp file to be edited
     * @param type Variable to check if Staff or Campcommittee Member
     */
    // Allows a staff to reply to a camp's enquiries and updates excel
    public void replyEnquiry(CampInformation camp, int enquiryIndex, String enquiryReply, String respondent, String campFolderName, String fileName, String type){
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            int index = 0;
            // enquiryIndex++;
            StringBuilder newContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (index++ == enquiryIndex) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        String enquiry = parts[0];
                        String sender = parts[1];
                        String updatedLine = String.format("%s,%s,%s,%s,closed%n", enquiry, sender, respondent, enquiryReply);
                        newContent.append(updatedLine);
                    }
                } else{
                    newContent.append(line).append("\n");
                    continue;
                }
            }
            try (FileWriter writer = new FileWriter(filePath.toString())) {
                writer.write(newContent.toString());
                System.out.println();
                System.out.println("Reply Sucessful!");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(type.equals("CM")){
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
                        System.out.println("Current points: " + updatedPoint);
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
}
