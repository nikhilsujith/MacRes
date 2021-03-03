package data;

public class Users {
    String username, password, firstName, lastName, utaID, role, phone, email, address, city, state, zipCode, no_shows, revoked, violations;

    public Users()
    {
        setUsername("");
        setPassword("");
        setFirstName("");
        setLastName("");
        setUtaID("");
        setRole("");
        setPhone("");
        setEmail("");
        setAddress("");
        setCity("");
        setState("");
        setZipCode("");
        setNo_shows("");
        setRevoked("");
        setViolations("");
    }

    public Users(String username, String firstName, String lastName, String role)
    {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(role);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setUtaID(String utaID) {
        this.utaID = utaID;
    }
    public String getUtaID() {
        return utaID;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setNo_shows(String no_shows) {
        this.no_shows = no_shows;
    }
    public String getNo_shows() {
        return no_shows;
    }

    public void setRevoked(String revoked) {
        this.revoked = revoked;
    }
    public String getRevoked() {
        return revoked;
    }

    public void setViolations(String violations) {
        this.violations = violations;
    }
    public String getViolations() {
        return violations;
    }
}
