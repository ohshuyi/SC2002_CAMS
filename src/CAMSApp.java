import java.util.Scanner;
/**
 * This is the boundary class for our Camp application
 * @author Team 1
*/
public class CAMSApp {
    /** 
     * The CAMSApp class represents the main application entry point.
     * The main method is the entry point for the application. It initializes the application and starts its execution.
     * @param args The command line arguments passed to the application.
    */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String userID;
        String password;
        boolean loginSuccessful = false;
        boolean quitApp = false;
        String studfile = "student_list.csv"; // Replace with your CSV file's name
        String stafffile = "staff_list.csv";
        String storedUserID = "";
        String storedPassword = "";
        String accountFolderPath = "Accounts";
        String campsFolderPath = "Camps";

        int choice;
        int studentIndex = 0;
        int staffIndex = 0;
        int campCount = 0;

        Student studentList[] = new Student[100];
        Staff staffList[] = new Staff[6];
        UpdateCSV updateCSV = new UpdateCSV();
        AssignCSV assignCSV = new AssignCSV();
        CampUpdateCSV csvU = new CampUpdateCSV();
        CampAssignCSV csvA = new CampAssignCSV();
        
        System.out.println();
        System.out.println("\n" + //
        "╔═════════════════════════════════════════╗\n"+
        "║                                         ║\n"+
        "║                                         ║\n"+
        "║   ░█████╗░░█████╗░███╗░░░███╗░██████╗   ║\n" + //
        "║   ██╔══██╗██╔══██╗████╗░████║██╔════╝   ║\n" + //
        "║   ██║░░╚═╝███████║██╔████╔██║╚█████╗░   ║\n" + //
        "║   ██║░░██╗██╔══██║██║╚██╔╝██║░╚═══██╗   ║\n" + //
        "║   ╚█████╔╝██║░░██║██║░╚═╝░██║██████╔╝   ║\n" + //
        "║   ░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═════╝░   ║\n"+
        "║                                         ║\n"+
        "║                                         ║\n"+
        "║                𝙽𝚃𝚄 𝙲𝚊𝚖𝚙𝚜                ║\n"+
        "║            𝙼𝚊𝚗𝚊𝚐𝚎𝚖𝚎𝚗𝚝 𝚂𝚢𝚜𝚝𝚎𝚖            ║\n"+
        "║                                         ║\n"+
        "╚═════════════════════════════════════════╝"
        );
        System.out.println();

        String campFile = "camp.csv";
        CampInformation[] campInfo = new CampInformation[100];
        boolean firstTIme = true;
        

        while(!quitApp)
        {
            campCount = csvA.assignExcelFile(campInfo, campFile, campsFolderPath) - 1;
            System.out.println("╭――――――――――――――――――――――――╮");
            System.out.println("│                        │");
            System.out.println("│   -----LOGIN-----      │");
            System.out.println("│   1: Student           │");
            System.out.println("│   2: Staff             │");
            System.out.println("│   3: Quit Application  │");
            System.out.println("│                        │");
            System.out.println("╰――――――――――――――――――――――――╯");
            System.out.println();
            System.out.printf("Choice: ");
            choice = sc.nextInt();
            
            while(choice < 1 | choice > 3)
            {
                System.out.println();
                System.out.println("Invalid input! Re-Enter Input:");
                choice = sc.nextInt();
            }
            sc.nextLine();
            

            switch(choice)
            {
                case 1:
                    assignCSV.assignExcel(studentList, studfile, accountFolderPath);

                    while (!loginSuccessful) 
                    {
                        System.out.println();
                        System.out.println("UserID: ");
                        userID = sc.nextLine();

                        System.out.println();
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        System.out.println();

                        for(int i = 0; i < 100; i++)
                        {
                            
                            storedPassword = studentList[i].getPassword();
                            storedUserID = studentList[i].getUserID();
                            if(storedUserID.equals(userID) && storedPassword.equals(password))
                            {                            
                                System.out.println("Login successful!");
                                studentIndex = i;
                                if(storedPassword.equals("password"))
                                {
                                    
                                    System.out.println("First Time Login - Please Change your password");
                                    PasswordManager passwordManager = new PasswordManager(studentList[studentIndex].getUserName(), studentList[studentIndex].getUserID(), studentList[studentIndex].getPassword(), studentList[studentIndex].getFaculty(), studentList[studentIndex].getEmail(), studentList[studentIndex].getProfile());
                                    passwordManager.changePasswordStudent(studentList[studentIndex]);
                                    System.out.println("Please Login again with your new password");
                                    updateCSV.updateExcelFile(studentList, studfile, accountFolderPath);
                                    assignCSV.assignExcel(studentList, studfile, accountFolderPath);
                                    firstTIme = false;
                                    break;
                                }
                                firstTIme = false;
                                loginSuccessful = true; // Set the flag to indicate a successful login
                                break; // Exit the loop since the login was successful
                                
                            }

                        }
                            if (!loginSuccessful && firstTIme) 
                            {
                                System.out.println("Login failed. Incorrect UserID or Password.");
                                System.out.println("Please try again.");
            
                                loginSuccessful = false;
                            }
                        
                    }
                    
                    studentList[studentIndex] =  new ManageCampParticipant(studentList[studentIndex].getUserName(), studentList[studentIndex].getUserID(), studentList[studentIndex].getPassword(), studentList[studentIndex].getFaculty(), studentList[studentIndex].getEmail(), studentList[studentIndex].getProfile());
                    
                    while(loginSuccessful)
                    {
                        campCount = csvA.assignExcelFile(campInfo, campFile, campsFolderPath) - 1;

                        System.out.println();
                        System.out.println("------Student Profile------");
                        studentList[studentIndex].displayUserInfo();
                        System.out.println();
                        System.out.println("======Profile Options=====");
                        System.out.println("1: Change Password");
                        System.out.println("2. Camp Directory");
                        System.out.println("3. Log Out");
                        System.out.println();

                        switch(sc.nextInt())
                        {
                            case 1:
                                PasswordManager passwordManager = new PasswordManager(studentList[studentIndex].getUserName(), studentList[studentIndex].getUserID(), studentList[studentIndex].getPassword(), studentList[studentIndex].getFaculty(), studentList[studentIndex].getEmail(), studentList[studentIndex].getProfile());

                                passwordManager.changePasswordStudent(studentList[studentIndex]);
                                updateCSV.updateExcelFile(studentList, studfile, accountFolderPath);
                                loginSuccessful = false;
                                break;

                            case 2:
                                studentList[studentIndex].campDirectory(campInfo, campCount + 1, studentList, studentIndex);
                                updateCSV.updateExcelFile(studentList, studfile, accountFolderPath);
                                assignCSV.assignExcel(studentList, studfile, accountFolderPath);
                                break;

                            case 3:
                                System.out.println("You have successfully logged out.");
                                updateCSV.updateExcelFile(studentList, studfile, accountFolderPath);
                                loginSuccessful = false; 
                                System.out.println();
                                firstTIme = true;
                                break;
                            }
                    }
                    break;

                case 2:
                assignCSV.assignExcel(staffList, stafffile, accountFolderPath);    
                while (!loginSuccessful) 
                    {
                        System.out.println();
                        System.out.println("UserID: ");
                        userID = sc.nextLine();

                        System.out.println();
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        System.out.println();
                        for(int i = 0; i < 5; i++)
                        { 
                            storedPassword = staffList[i].getPassword();
                            storedUserID = staffList[i].getUserID();
                            if(storedUserID.equals(userID) && storedPassword.equals(password))
                            {                            
                                System.out.println("Login successful!");
                                staffIndex = i;
                                if(storedPassword.equals("password"))
                                {
                                    
                                    System.out.println("First Time Login - Please Change your password");
                                    PasswordManager passwordManager = new PasswordManager(staffList[staffIndex].getUserName(), staffList[staffIndex].getUserID(), staffList[staffIndex].getPassword(), staffList[staffIndex].getFaculty(), staffList[staffIndex].getEmail(), staffList[staffIndex].getProfile());
                                    passwordManager.changePasswordStaff(staffList[staffIndex]);
                                    System.out.println("Please Login again with your new password");
                                    updateCSV.updateExcelFile(staffList, stafffile, accountFolderPath);
                                    assignCSV.assignExcel(staffList, stafffile, accountFolderPath);
                                    firstTIme = false;
                                    break;
                                }
                                firstTIme = false;
                                loginSuccessful = true; // Set the flag to indicate a successful login
                                break; // Exit the loop since the login was successful
                            }
                        }
                        if (!loginSuccessful && firstTIme) 
                        {
                            System.out.println("Login failed. Incorrect UserID or Password.");
                            System.out.println("Please try again.");
                            loginSuccessful = false;
                        }
                    }
                    
                    
                    System.out.println();
                    while(loginSuccessful)
                    {
                        campCount = csvA.assignExcelFile(campInfo, campFile, campsFolderPath) - 1;

                        ManageCampStaff campNewStaff = new ManageCampStaff(staffList[staffIndex].getUserName(), staffList[staffIndex].getUserID(), staffList[staffIndex].getEmail(), staffList[staffIndex].getFaculty(), staffList[staffIndex].getPassword(), staffList[staffIndex].getProfile());
                        System.out.println();
                        System.out.println("------Staff Profile------");
                        staffList[staffIndex].displayUserInfo();
                        System.out.println();
                        System.out.println("======Profile Options=====");
                        System.out.println("1: Change Password");
                        System.out.println("2: Camp Directory");
                        System.out.println("3: Log Out");

                        switch (sc.nextInt())
                        {
                            case 1:
                                PasswordManager passwordManager = new PasswordManager(staffList[staffIndex].getUserName(), staffList[staffIndex].getUserID(), staffList[staffIndex].getEmail(), staffList[staffIndex].getFaculty(), staffList[staffIndex].getPassword(), staffList[staffIndex].getProfile());
                                passwordManager.changePasswordStaff(staffList[staffIndex]);
                                updateCSV.updateExcelFile(staffList, stafffile, accountFolderPath);
                                loginSuccessful = false;
                                break;
                            case 2:
                                staffList[staffIndex].campDirectoryStaff(campInfo, campCount + 1);
                                break;
                            case 3:
                                System.out.println("You have successfully logged out.");
                                updateCSV.updateExcelFile(staffList, stafffile, accountFolderPath);
                                loginSuccessful = false; 
                                System.out.println();   
                                firstTIme = true;
                                break;
                            
                            default:
                                System.out.println("Invalid command, please try again!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Quitting App...");
                    System.out.println("You have successfully quit the Application");
                    quitApp = true;
                    break;
                    
            }
        }
    }
}
