import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
/**
 * CampAssignCSV reads camp information from a CSV file, creates CampInformation objects, appends them to an array, and returns the total number of camps processed.
 * @version 1.0
 * @author Koh Yihao Kendrick
 * @since 24/11/2023
*/
public class CampAssignCSV extends AssignCSV{
    
    /** 
     * This method reads camp information from a CSV file, creates CampInformation objects, adds them to an array, and returns the total number of camps processed.
     * @param excelList CampInfo[] object containing camp information
     * @param fileName File name to be updated
     * @param folderPath Folder path to be updated
     * @return Int returns total number of camps
     */
    // Create camp objects, appends camp.csv, returns total number of camps
    public int assignExcelFile(Object[] excelList, String fileName, String folderPath) {
        int index = 0;
        Path filePath = Paths.get(".", folderPath, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            boolean firstRow = true;
            while ((line = br.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 13) {
                    String campName = parts[0];
                    String startDate = parts[1];
                    String endDate = parts[2];
                    String regClosingDate = parts[3];
                    String faculty = parts[4];
                    String location = parts[5];
                    int totalSlots = Integer.parseInt(parts[6]);
                    int remainingSlots = Integer.parseInt(parts[7]);
                    int campCommitteeSlots = Integer.parseInt(parts[8]);
                    int campCommitteeRemainingSlots = Integer.parseInt(parts[9]);
                    String description = parts[10];
                    String staffInCharge = parts[11];
                    boolean visibility = Boolean.parseBoolean(parts[12]);

                    CampInformation campInfo = new CampInformation(campName, startDate, endDate, regClosingDate, faculty, location, totalSlots, remainingSlots, campCommitteeSlots, campCommitteeRemainingSlots, description, staffInCharge, visibility);
                    excelList[index] = campInfo;
                    index++;
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }
}
