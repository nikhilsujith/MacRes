package data;

public class Reservations {
    String reservationID, facilityName, reserveDate, reserveTime, username, deposit;

    public Reservations()
    {
        setReservationID("");
        setFacilityName("");
        setReserveDate("");
        setReserveTime("");
        setUsername("");
        setDeposit("");
    }

    public Reservations(String reservationID, String facilityName, String reserveDate, String reserveTime)
    {
        setReservationID(reservationID);
        setFacilityName(facilityName);
        setReserveDate(reserveDate);
        setReserveTime(reserveTime);
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
    public String getReservationID() {
        return reservationID;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    public String getFacilityName() {
        return facilityName;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }
    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }
    public String getReserveTime() {
        return reserveTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getDeposit() {
        return deposit;
    }
}
