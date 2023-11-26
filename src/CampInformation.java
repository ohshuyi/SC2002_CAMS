/**
 * CampInformation class represents a camp and it's details in a management system. It includes information such as name, dates, slots, description, and visibility.
 * @version 1.0
 * @author Oh Shuyi
 * @since 24/11/2023
*/

public class CampInformation {
    /**
     * The camp's name
     * The location of the camp
     * The faculty open to the camp. Can be a specialised faculty or open to all NTU students
     * The camp's description
     * The staff who created the camp
     */
    private String campName, location, faculty, description, staffInCharge;
    
    /**
     * The starting date of the camp, format is in YYYY-MM-DD
     * The ending date of the camp, format is in YYYY-MM_DD
     * The registration closing date of the camp, format is in YYYY-MM_DD
     */
    private String startDate, endDate, registrationClosingDate;

    /**
     * The total number of slots for the camp, inclusive of participants and camp committee members
     * The total number of camp committee members slots, maximum should be 10
     * The remaining number of slots for the camp, inclusive of participants and camp committee members
     * The remaining number of camp committee members slots
     */
    private int totalSlots, campCommitteeSlots, remainingSlots, campCommitteeRemainingSlots;
    
    /**
     * The visibility of the camp to students: true is visibile, false is invisible.
     */
    boolean visibility = false;

    /** 
     * The constructor for CampInformation object with details such as name, dates, slots, description, and visibility in a camp management system.
     * @param campName The camp's name
     * @param startDate The starting date of the camp, format is in YYYY-MM-DD
     * @param endDate The ending date of the camp, format is in YYYY-MM_DD
     * @param registrationClosingDate The registration closing date of the camp, format is in YYYY-MM_DD
     * @param faculty The faculty open to the camp. Can be a specialised faculty or open to all NTU students
     * @param location The location of the camp
     * @param totalSlots The total number of slots for the camp, inclusive of participants and camp committee members
     * @param remainingSlots The remaining number of slots for the camp, inclusive of participants and camp committee members
     * @param campCommitteeSlots The total number of camp committee members slots, maximum should be 10
     * @param campCommitteeRemainingSlots The remaining number of camp committee members slots
     * @param description The camp's description
     * @param staffInCharge The staff who created the camp
     * @param visibility The visibility of the camp to students: true is visibile, false is invisible.
    */
    public CampInformation(String campName, String startDate, String endDate, String registrationClosingDate, String faculty, 
    String location, int totalSlots, int remainingSlots, int campCommitteeSlots, int campCommitteeRemainingSlots, String description, String staffInCharge, boolean visibility) 
    {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationClosingDate = registrationClosingDate;
        this.faculty = faculty;
        this.location = location;
        this.totalSlots = totalSlots;
        this.remainingSlots = remainingSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.campCommitteeRemainingSlots = campCommitteeRemainingSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.visibility = visibility;
    }

    /** 
     * Set method for camp name
     * @param campName String for camp name
    */
     // ---- CampName ----
    public void setCampName(String campName)
    {
        this.campName = campName;
    }

    /** 
     * Get method for camp name
     * @return String Returns camp name
     */
    public String getCampName()
    {
        return campName;
    }

    
    /** 
     * Set method for start date
     * @param startDate String for start date
     */
    // ---- CampDates ---- 
    public void setStartCampDate(String startDate)
    {
        this.startDate = startDate;
    }

    /** 
     * Get method for start date
     * @return String Returns start date
     */
    public String getStartCampDate()
    {
        return startDate;
    }

    /** 
     * Set method for end date
     * @param endDate String for end date
     */
    public void setEndCampDate(String endDate)
    {
        this.endDate = endDate;
    }

    /** 
     * Get method for end date
     * @return String Returns end date
     */
    public String getEndCampDates()
    {
        return endDate;
    }

    
    /** 
     * Set method for closing date
     * @param registrationClosingDate String for registration closing date
     */
    // ---- ClosingRegDates ---- 
    public void setClosingRegDates(String registrationClosingDate)
    {
        this.registrationClosingDate = registrationClosingDate;
    }

    /** 
     * Get method for registration closing date
     * @return String Returns registration closing date
     */
    public String getClosingRegDates()
    {
        return registrationClosingDate;
    }

    
    /** 
     * Set method for faculty
     * @param faculty String for faculty
     */
    // ---- faculty ---- 
    public void setFaculty(String faculty)
    {
        this.faculty =  faculty;
    }
    
    
    /** 
     * Get method for faculty
     * @return String Returns faculty
     */
    public String getFaculty()
    {
        return faculty;
    }

    
    /** 
     * Set method for location
     * @param location String for location
     */
    // ---- location ---- 
    public void setLocation(String location)
    {
        this.location = location;
    }

    /** 
     * Get method for location
     * @return String Returns location
     */
    public String getLocation()
    {
        return location;
    }

    /** 
     * Set method for total slots
     * @param totalSlots Integer for total slots
     */
    // ---- totalSlots ---- 
    public void setTotalSlots(int totalSlots)
    {
        this.totalSlots =  totalSlots;
    }

    /** 
     * Get method for total slots
     * @return int Returns total slots
     */
    public int getTotalSlots()
    {
        return totalSlots;
    }

    /** 
     * Set method for remaining slots
     * @param remainingSlots Integer for remaining slots
     */
    // ---- remainingSlots ---- 
    public void setRemainingSlots(int remainingSlots)
    {
        this.remainingSlots =  remainingSlots;
    }

    /** 
     * Get method for remaining slots
     * @return int Returns remaining slots
     */
    public int getRemainingSlots()
    {
        return remainingSlots;
    }

    /** 
     * Set method for camp committee slots
     * @param campCommitteeSlots Integer for camp committee slots
     */
    // ---- camp committee slots ---- 
    public void setCampCommitteeSlots(int campCommitteeSlots)
    {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /** 
     * Get method for camp committee slots
     * @return int Returns camp committee slots
     */
    public int getCampCommitteeSlots()
    {
        return campCommitteeSlots;
    }

    /** 
     * Set method for remaining camp committee slots
     * @param campCommitteeRemainingSlots Integer for remaining camp committee slots
     */
    // ---- camp committee slots ---- 
    public void setCampCommitteeRemainingSlots(int campCommitteeRemainingSlots)
    {
        this.campCommitteeRemainingSlots = campCommitteeRemainingSlots;
    }

    /** 
     * Get method for remaining camp committee slots
     * @return int Returns remaining camp committee slots
     */
    public int getCampCommitteeRemainingSlots()
    {
        return campCommitteeRemainingSlots;
    }
    
    
    /** 
     * Set method for description
     * @param description String for description
     */
    // ---- description ---- 
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    
    /** 
     * Get method for description
     * @return String Returns description
     */
    public String getDescription()
    {
        return description;
    }

    
    /** 
     * Set method for Staff in charge
     * @param staffInCharge String for Staff in charge
     */
    // ---- staffInCharge ---- 
    public void setStaffInCharge(String staffInCharge)
    {
        this.staffInCharge =  staffInCharge;
    }

    /** 
     * Get method for Staff in charge
     * @return String Returns Staff in charge
     */
    public String getStaffInCharge()
    {
        return staffInCharge;
    }

    
    /** 
     * Method to change visibility
     * @param visible Boolean variable for visibility
     */
    // ---- visbility ---- 
    public void changeVisiblity(boolean visible)
    {
        if(visible)
        {
            this.visibility = true;
        }
        else
            this.visibility = false;
    }

    
    /** 
     * Get method for visibility
     * @return boolean Returns visibility
     */
    public boolean getVisibility() {
        return visibility;
    }
}


