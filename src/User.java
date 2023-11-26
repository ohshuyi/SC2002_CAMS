/**
 * User class represents a system user, storing attributes such as username, userID, password, faculty, email, and profile. It includes methods for retrieving and updating user information.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/

public class User extends Exception{
    /**
     * The User's Login username
     */
    private String userName;
    /**
     * The User's userID
     */
    private String userID;
    /**
     * The User's Login password
     */
    private String password;
    /**
     * The User's faculty
     */
    private String faculty;
        /**
     * The User's email
     */
    private String email;
    /**
     * The User's profile as staff, particpant or camp committee member
     */
    private int profile;
    /**
     * student's file post-fix, replaced with your CSV file's name
     */
    String studfile = "student_list.csv"; 
    /**
     * staff's file post-fix, replaced with your CSV file's name
     */
    String stafffile = "staff_list.csv";
    /**
     * To instantiate a string, line
     */
    String line = "";
    /**
     *  Set the appropriate delimiter (e.g., "," for a comma-separated file)
     */
    String csvSplitBy = ",";
    /** 
     * Constructor that initializes a User which initializes user attributes such as username, userID, password, faculty, email, and profile based on provided parameters.
     * @param userName The User's Login username
     * @param userID The User's userID
     * @param password The User's Login password
     * @param faculty The User's faculty
     * @param email The User's email
     * @param profile student's file post-fix, replaced with your CSV file's name
    */
    public User(String userName, String userID, String password, String faculty, String email, int profile)
    {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.email = email;
        this.profile = profile;
    }

    
    /** 
     * Get method for username
     * @return String Returns user name
     */
    //getter methods
    public String getUserName()
    {
        return this.userName;
    }

    /** 
     * Set method for user name
     * @param userName String for user name
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /** 
     * Get method for UserID
     * @return String Returns UserID
     */
    public String getUserID()
    {
        return this.userID;
    }

    /** 
     * Set method for UserID
     * @param userID String for UserID
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    /** 
     * Get method for password
     * @return String Returns password
     */
    public String getPassword()
    {
        return this.password;
    }

    /** 
     * Set method for password
     * @param password String for passowrd
     */
    public void setPassword(String password)
    { 
        this.password = password;
    }

    /** 
     * Get method for faculty
     * @return String Returns faculty
     */
    public String getFaculty()
    {
        return this.faculty;
    }

    /** 
     * Set method for faculty
     * @param faculty String for faculty
     */
    public void setFaculty(String faculty)
    {
        this.faculty = faculty;
    }

    /** 
     * Get method for email
     * @return String Returns email
     */
    public String getEmail()
    {
        return this.email;
    }

    /** 
     * Set method for email
     * @param email String for email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /** 
     * Get method for profile
     * @return int Returns profile
     */
    public int getProfile()
    {
        return this.profile;
    }

    /** 
     * Set method for profile
     * @param profile Integer for profile
     */
    public void setProfile(int profile)
    {
        this.profile = profile;
    }

    /** 
     * This method prints user information based on the user's profile type, including name, userID, email, faculty, and specific profile designation.
     */
    public void displayUserInfo()
    {
        if(profile == 0)
        {
            System.out.println("Name:    " + userName);
            System.out.println("User ID: " + userID);
            System.out.println("Email:   " + email);
            System.out.println("Faculty: " + faculty);
            System.out.println("Profile: Staff");
        }
        else if(profile == 1)
        {
            System.out.println("Name:    " + userName);
            System.out.println("User ID: " + userID);
            System.out.println("Email:   " + email);
            System.out.println("Faculty: " + faculty);
            System.out.println("Profile: Student");
        }
        else if(profile == 2)
        {
            System.out.println("Name:    " + userName);
            System.out.println("User ID: " + userID);
            System.out.println("Email:   " + email);
            System.out.println("Faculty: " + faculty);
            System.out.println("Profile: Camp Committee Member");
        }
    }
}






