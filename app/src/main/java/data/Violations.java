package data;

public class Violations {
    String violationID, username, facilityName, violationDate, violationTime, description, deposit;

    public Violations() {
        setViolationID("");
        setUsername("");
        setFacilityName("");
        setViolationDate("");
        setViolationTime("");
        setDescription("");
        setDeposit("");
    }

    public Violations(String violationID) {
        setViolationID(violationID);
    }

    public void setViolationID(String violationID) {
        this.violationID = violationID;
    }
    public String getViolationID() {
        return violationID;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    public String getFacilityName() {
        return facilityName;
    }

    public void setViolationDate(String violationDate) {
        this.violationDate = violationDate;
    }
    public String getViolationDate() {
        return violationDate;
    }

    public void setViolationTime(String violationTime) {
        this.violationTime = violationTime;
    }
    public String getViolationTime() {
        return violationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getDeposit() {
        return deposit;
    }
}
