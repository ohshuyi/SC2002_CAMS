import java.util.Scanner;
/**
 * PasswordManager provides methods to change passwords securely, meeting length, case, and special character requirements.
 * @version 1.0
 * @author Oh ShuYi
 * @since 24/11/2023
*/
public class PasswordManager extends User{
    /** 
     * Constructor that initializes a PasswordManager with User attributes.
     * @param userName Username of camp User
     * @param userID User ID of camp User
     * @param password Password of camp User
     * @param faculty Faculty of camp User
     * @param email Email of camp User
     * @param profile Profile type of camp User
    */
    public PasswordManager(String userName, String userID, String password, String faculty, String email, int profile){
        super(userName, userID, password, faculty, email, profile);
    }
    /** 
     * This method facilitates the Student in changing their password, ensuring security requirements, including length, case diversity, and special characters, are met.
     * @param student Student class required to change password
     */
    public void changePasswordStudent(Student student)
    {
        String currentPw;
        String newPassword;
        String newPassword2;

        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Enter current password: ");
            currentPw = sc.nextLine();

            if(!currentPw.equals(super.getPassword()))
            {
                System.out.println();
                System.out.println("Current password is wrong.");
            }
        }
        while(!currentPw.equals(super.getPassword())); // checks for current password
        
        
        while(true)
        {
            System.out.println();
            System.out.println("Enter new password: ");
            newPassword = sc.nextLine();
            
            System.out.println();
            System.out.println("Reenter password: ");
            newPassword2 = sc.nextLine();

            if(!newPassword.equals(newPassword2))
            {
                System.out.println();
                System.out.println("Invalid Password - Passwords do not match. Don't you try again and waste my time");
                continue;
            }
            
            if(currentPw.equals(newPassword))
            {
                System.out.println();
                System.out.println("Invalid Password - Same Password");
                continue;
            }

            if(newPassword.length() < 8)
            {
                System.out.println();
                System.out.println("Password too short. It must be at least 8 characters long");
                continue;
            }
            if (!containsUppercase(newPassword)) 
            {

                System.out.println();
                System.out.println("New password must contain at least one uppercase letter.");
                continue;
            }

            if (!containsLowercase(newPassword)) 
            {

                System.out.println();
                System.out.println("New password must contain at least one lowercase letter.");
                continue;
            }

            if (!containsSpecialCharacter(newPassword)) 
            {
                System.out.println();
                System.out.println("New password must contain at least one special character (!@#$%^&*).");
                continue;
            }        

            break;
        }
        
        student.setPassword(newPassword);
        System.out.println("Password successfully changed.");
        
    }

    
    /** 
     * This method facilitates the Staff in changing their password, ensuring security requirements, including length, case diversity, and special characters, are met.
     * @param staff Staff class required to change password
     */
    public void changePasswordStaff(Staff staff)
    {
        String currentPw;
        String newPassword;
        String newPassword2;

        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Enter current password: ");
            currentPw = sc.nextLine();

            if(!currentPw.equals(super.getPassword()))
            {
                System.out.println();
                System.out.println("Current password is wrong.");
            }
        }
        while(!currentPw.equals(super.getPassword())); // checks for current password
        
        
        while(true)
        {
            System.out.println();
            System.out.println("Enter new password: ");
            newPassword = sc.nextLine();
            
            System.out.println();
            System.out.println("Reenter password: ");
            newPassword2 = sc.nextLine();

            if(!newPassword.equals(newPassword2))
            {
                System.out.println();
                System.out.println("Invalid Password - Passwords do not match. Don't you try again and waste my time");
                continue;
            }
            
            if(currentPw.equals(newPassword))
            {
                System.out.println();
                System.out.println("Invalid Password - Same Password");
                continue;
            }

            if(newPassword.length() < 8)
            {
                System.out.println();
                System.out.println("Password too short. It must be at least 8 characters long");
                continue;
            }
            if (!containsUppercase(newPassword)) 
            {

                System.out.println();
                System.out.println("New password must contain at least one uppercase letter.");
                continue;
            }

            if (!containsLowercase(newPassword)) 
            {

                System.out.println();
                System.out.println("New password must contain at least one lowercase letter.");
                continue;
            }

            if (!containsSpecialCharacter(newPassword)) 
            {
                System.out.println();
                System.out.println("New password must contain at least one special character (!@#$%^&*).");
                continue;
            }        

            break;
        }
        
        staff.setPassword(newPassword);
        System.out.println("Password successfully changed.");
        
    }
    
    /** 
     * This method checks if a given password contains at least one uppercase letter. Returns true if uppercase letter is found, otherwise false.
     * @param password Password inputted
     * @return boolean Returns True if the password contains an Uppercase alphabet, otherwise returns False
     */
    private static boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    
    /** 
     * This method checks if a given password contains at least one lowercase letter. Returns true if lowercase letter is found, otherwise false.
     * @param password Password inputted
     * @return boolean Returns True if the password contains a Lowercase alphabet, otherwise returns False
     */
    private static boolean containsLowercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    
    /** 
     * This method checks if a given password contains at least one special character. Returns true if a special character is found, otherwise false.
     * @param password Password inputted
     * @return boolean Returns True if the password contains a special character, otherwise returns False
     */
    private static boolean containsSpecialCharacter(String password) {
        String specialChars = "!@#$%^&*";
        for (char c : password.toCharArray()) {
            if (specialChars.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
