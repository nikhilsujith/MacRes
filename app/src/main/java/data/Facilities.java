package data;

public class Facilities {
    String facilityName, type, timeInterval, duration, venue, deposit;

    public Facilities()
    {
        setFacilityName("");
        setType("");
        setTimeInterval("");
        setDuration("");
        setVenue("");
        setDeposit("");
    }

    public Facilities(String facilityName)
    {
        setFacilityName(facilityName);
    }

    public void setFacilityName(String facilityName) {this.facilityName = facilityName;}
    public String getFacilityName() {return facilityName;}

    public void setType(String type) {this.type = type;}
    public String getType() {return type;}

    public void setTimeInterval(String timeInterval) {this.timeInterval = timeInterval;}
    public String getTimeInterval() {return timeInterval;}

    public void setDuration(String duration) {this.duration = duration;}
    public String getDuration() {return duration;}

    public void setVenue(String venue) {this.venue = venue;}
    public String getVenue() {return venue;}

    public void setDeposit(String deposit) {this.deposit = deposit;}
    public String getDeposit() {return deposit;}
}
