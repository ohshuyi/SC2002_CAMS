import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
/**
 * CampUpdateCSV manages camp-related CSV operations. It includes methods to update, create, add members, and remove participants from camp files.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/
public class CampUpdateCSV extends UpdateCSV{
    /** 
     * The method overwrites a CSV file with new camp information, taking an array of `CampInformation` objects and updating the specified CSV file.
     * @param excelList Student or Staff list
     * @param fileName File name to be updated
     * @param length Length of list
     * @param folderPath Folder path to be updated
     */
    // Update camp.csv, rewrites the entire camps.csv
    public void updateExcelFile(Object[] excelList, String fileName, int length, String folderPath) {
        Path filePath = Paths.get(".", folderPath, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString()))) {
            // Write the header
            bw.write("CampName,StartDate,EndDate,RegistrationClosingDate,Faculty,Location,TotalSlots,RemainingSlots,CampCommitteeSlots,CampCommitteeRemainingSlots,Description,StaffInCharge,Visibility");
            bw.newLine();

            for (int i = 0; i < length; i++) {
                if (excelList[i] instanceof CampInformation) {
                    CampInformation campInfo = (CampInformation) excelList[i];
                    bw.write(String.join(",",
                            campInfo.getCampName(),
                            campInfo.getStartCampDate(), // Format as needed
                            campInfo.getEndCampDates(),   // Format as needed
                            campInfo.getClosingRegDates(), // Format as needed
                            campInfo.getFaculty(),
                            campInfo.getLocation(),
                            String.valueOf(campInfo.getTotalSlots()),
                            String.valueOf(campInfo.getRemainingSlots()),
                            String.valueOf(campInfo.getCampCommitteeSlots()),
                            String.valueOf(campInfo.getCampCommitteeRemainingSlots()),
                            campInfo.getDescription(),
                            campInfo.getStaffInCharge(),
                            String.valueOf(campInfo.getVisibility())
                    ));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * The method generates a new camp folder with associated CSV files for participants, committee members, inquiries, and suggestions based on the provided camp name and folder.
     * @param campName Camp name to be created
     * @param folderName Folder name to be created
     * @throws IOException if there are file path issues
     */
    // Create folder and csv files for a given file
    public void createNewCamp(String campName, String folderName) throws IOException
    {
        Path filePath = Paths.get(".", folderName, campName);
        // String folderPath = folderName + "/" + campName; // Replace 'path/to/your/accounts/' with the relevant directory
        File folder = new File(filePath.toString());

        // Create the directory if it doesn't exist
        if (!folder.exists()) {
            boolean isCreated = folder.mkdirs();
            if (isCreated) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Failed to create directory");
                return;
            }
        }

        String campFile1 = campName + "_Participant.csv";
        Path filePath1 = Paths.get(".", folderName, campName, campFile1);
        PrintWriter pw = new PrintWriter(new FileWriter(filePath1.toString()));

        // Write the headers without quotes
        pw.println("Camp Participants");

        pw.close();
        
        String campFile2 = campName + "_CampCommitteeMember.csv";
        Path filePath2 = Paths.get(".", folderName, campName, campFile2);
        pw = new PrintWriter(new FileWriter(filePath2.toString()));

        // Write the headers without quotes
        pw.println("Camp Committee Member");

        pw.close();

        String campFile3 = campName + "_Enquiry.csv";
        Path filePath3 = Paths.get(".", folderName, campName, campFile3);
        pw = new PrintWriter(new FileWriter(filePath3.toString()));

        // Write the headers without quotes
        pw.println("Enquiry,Sender,Respondent,Reply,Status");

        pw.close();

        String campFile4 = campName + "_Suggestion.csv";
        Path filePath4 = Paths.get(".", folderName, campName, campFile4);
        pw = new PrintWriter(new FileWriter(filePath4.toString()));

        // Write the headers without quotes
        pw.println("SuggestionArea,Suggestion");

        pw.close();
    }

    /** 
     * The method appends a participant or camp committee member's name to a CSV file associated with a specific camp in a camp management system. The user parameter determines whether the participant or camp committee file is updated.
      * @param participantName Participant to be added
      * @param campFolderName Camp folder name add participant
      * @param fileName File name to be edited
      * @param user User type
      */
    // Function to add student or camp committee member to a camp
    public void addMembers(String participantName, String campFolderName, String fileName, int user) {
        try {
            Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
            // String folderPath = "./Camps/" + excelFilePath + "/";
            // Open the Excel file in append mode
            FileWriter writer = new FileWriter(filePath.toString(), true);

            // Write the participant's name to a new line in the first column (assuming a CSV format)
            //user = 1, partifipant file
            //user = 2, campcomm file
            if(user == 1)
            {
                writer.append(participantName + "\n");

                writer.flush();
                writer.close();
            }
            else if(user == 2)
            {
                writer.append(participantName + ",0" + "\n");

                writer.flush();
                writer.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * This method removes a participant from a camp's CSV file in a camp management system. Reads the file, omits lines with the specified participant, and writes back the modified data.
     * @param participantName Participant's name to be removed
     * @param filePath File path to be edited
     */
    // Remove a participant from a camp
    public void removeParticipant(String participantName, String filePath){
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && !parts[0].equals(participantName)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
        }

        // Write modified data back to the file using BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    /** 
     * This method deletes the camp folder specified. It calls upon the recursive static method deleteFolderRecursively to delete all contents within the folder.
     * @param campName Name of the camp folder to be deleted
     */
    //deletes folder of the specified camp
    public void deleteCampFolder(String campName)
    {
        String campFolder = campName;
        String folderPath = "Camps/" + campFolder; 

        //create a File object representing the camp folder
        File folder = new File(folderPath);
    
            //check if the folder exists before attempting to delete
            if (folder.exists()) {
                // Call the deleteFolder() method to delete the folder
                if (deleteFolderRecursively(folder)) {
                    System.out.println("Camp Deleted Successfully");
                    System.out.println();
                } else {
                    System.out.println("Failed to delete Camp"); //should not occur if camp creation was done properly
                    System.out.println();
                }
            } else {
                System.out.println("Camp folder does not exist"); //should not occur if camp creation was done properly
                System.out.println();
        }
    }    
    

    /** 
     * This method is called recursively, delete all contents within the folder specified.
     * @param folder Name of camp folder to be deleted
     * @return boolean Returns true if the file/folder is deleted succesfully. Returns false if it failed
     */
    //recursive method to delete a folder and its contents
    private static boolean deleteFolderRecursively(File folder) {
        if (folder.isDirectory()) {
            //get all files and subdirectories in the folder
            File[] files = folder.listFiles();

            //if the folder is empty, delete it
            if (files != null) {
                for (File file : files) {
                    //recursively delete files and subdirectories
                    if (!deleteFolderRecursively(file)) {
                        return false; //if deletion fails, return false
                    }
                }
            }
        }
        //delete the folder once all its contents are deleted
        return folder.delete();
    }
}