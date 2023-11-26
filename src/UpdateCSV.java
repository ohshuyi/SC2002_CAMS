import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
/**
 * UpdateCSV updates student or staff excel files based on the provided data array, file name, and folder path, maintaining file structure and updating relevant information.
 * @version 1.0
 * @author Koh Yihao Kendrick
 * @since 24/11/2023
*/
public class UpdateCSV extends CSVManager{
    
    /** 
     * This method updates a student or staff Excel file based on the provided data, file name, and folder path. It reads the existing file, modifies the specified rows, and rewrites the entire content back to the file.
     * @param data Staff/Student arrays to be updated
     * @param excelFile File name to be updated
     * @param folderPath Folder name to be updated
     */
    // Update student or staff excel file based on folderpath
    public void updateExcelFile(Object[] data, String excelFile, String folderPath) {
        Path filePath = Paths.get(".", folderPath, excelFile);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            // Read the header line and append it to the fileContent
            if ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            // Iterate through the rest of the lines in the file
            int dataIndex = 0; // Start from the first row

            while ((line = br.readLine()) != null && dataIndex < data.length) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String userName = ""; // Extract the user-specific data from the data array
                    String userID = "";
                    String email = "";
                    String faculty = "";
                    String password = "";
                    String profile = "";

                    if (data[dataIndex] instanceof Student) {
                        Student student = (Student) data[dataIndex];
                        userName = student.getUserName();
                        userID = student.getUserID();
                        email = student.getEmail();
                        faculty = student.getFaculty();
                        password = student.getPassword();
                        profile = String.valueOf(student.getProfile());
                    } else if (data[dataIndex] instanceof Staff) {
                        Staff staff = (Staff) data[dataIndex];
                        userName = staff.getUserName();
                        userID = staff.getUserID();
                        email = staff.getEmail();
                        faculty = staff.getFaculty();
                        password = staff.getPassword();
                        profile = String.valueOf(staff.getProfile());
                    }

                    String updatedLine = String.join(",", userName, userID, email, faculty, password, String.valueOf(profile));
                    fileContent.append(updatedLine).append("\n");
                    dataIndex++;
                }
            }

            // Write the updated content back to the Excel file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString()))) {
                bw.write(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}