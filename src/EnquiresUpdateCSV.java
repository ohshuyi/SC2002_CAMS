import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
/**
 * EnquiresUpdateCSV manages updating and retrieving camp-related enquiries. It allows the retrieval of enquiries from a camp's "enquiry.csv" file, printing them out, and adding new enquiries to the file. 
 * @version 1.0
 * @author Koh Yihao Kendrick
 * @since 24/11/2023
*/
public class EnquiresUpdateCSV extends UpdateCSV{
    
    /** 
     * Returns all enquiries from a camp's enquiry.csv and prints them out. Closed enquires are filtered out and number of enquires are returned
     * @param camp Camp object
     * @param campFolderName Selected camp folder name to be retrieved
     * @param fileName Selected camp file name to be retrieved
     * @return returns number of enquiries
     */
    public int retrieveEnquiries(CampInformation camp, String campFolderName, String fileName){
        /**
         * Provides filepath to retreive file from filepath
         */
        Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
        /**
         * The number of enquiries in a particular CSV file
         */
        int numberOfEnquiries = 0;
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
                if (parts.length == 5) {
                    String enquiry = parts[0];
                    String sender = parts[1];
                    String status = parts[4];
                    if(status.equals("closed")){
                        continue;
                    }else{
                        System.out.println("Enquiry " + index + ": " + enquiry);
                        System.out.println("Sent by: " + sender);
                        System.out.println();
                        numberOfEnquiries++;
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfEnquiries;
    }

    
    /** 
     * Adds enquiry to next line of a given camp enquiry csv
     * @param message Message of enqury to be added
     * @param participantName Participant name
     * @param campFolderName Camp folder name to be added
     * @param fileName Camp file name to be added
     */
    public void updateExcelFile(String message, String participantName, String campFolderName, String fileName){
        try {
            Path filePath = Paths.get(".", "Camps", campFolderName, fileName);
            // String folderPath = "./Camps/" + excelFilePath + "/";
            // Open the Excel file in append mode
            FileWriter writer = new FileWriter(filePath.toString(), true);
            String csvLine = String.format("%s,%s,%s,%s,%s\n", message, participantName, " ", " ", "Open");
            // Write the enquiry to a new line from the first column (assuming a CSV format)
            writer.append(csvLine);

            writer.flush();
            writer.close();
            System.out.println("Enquiry Sent!");
        } catch (IOException e) {
            System.out.println("Enquiry Unsuccessful!");
            e.printStackTrace();
        }
    }
}
