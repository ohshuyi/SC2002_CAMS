import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
/**
 * AssignCSV contains the method assignExcel to populate an array with either Student or Staff objects based on the contents of a specified CSV file.
 * @version 1.0
 * @author Koh Yihao Kendrick
 * @since 24/11/2023
*/
public class AssignCSV extends CSVManager{
    /** 
     * This method creates Student or Staff objects based on data from a CSV file in the specified folder path and assigns them to an array.
     * @param excelList Student[] object or Staff[] object containing the user's information
     * @param fileName File name to be assigned
     * @param folderPath Folder name to be assigned
     */
    // Student and staff, create student or staff object based on folder
    public void assignExcel(Object[] excelList, String fileName, String folderPath) {
        Path filePath = Paths.get(".", folderPath, fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            int index = 0;
            String line;
            boolean firstRow = true; // Flag to skip the first row
    
            while ((line = br.readLine()) != null && index < excelList.length) {
                if (firstRow) {
                    // Skip the first row (header)
                    firstRow = false;
                    continue;
                }
    
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String userName = parts[0];
                    String userID = parts[1];
                    String email = parts[2];
                    String faculty = parts[3];
                    String password = parts[4];
                    int profile = Integer.parseInt(parts[5]);
    
                    // Create a Student or Staff object and add it to the array
                    if (fileName.equals("student_list.csv")) {
                        Student student = new Student(userName, userID, password, faculty, email, profile);
                        excelList[index] = student;
                    } 
                    else if(fileName.equals("staff_list.csv")){
                        Staff staff = new Staff(userName, userID, password, faculty, email, profile);
                        excelList[index] = staff;
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}