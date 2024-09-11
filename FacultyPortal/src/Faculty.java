public class Faculty {
    private String facultyId;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String phoneNumber;

    // Constructor
    public Faculty(String facultyId, String firstName, String lastName, String email, String department, String phoneNumber) {
        this.facultyId = facultyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to display faculty details
    @Override
    public String toString() {
        return "Faculty ID: " + facultyId + ", Name: " + firstName + " " + lastName + ", Email: " + email + 
               ", Department: " + department + ", Phone: " + phoneNumber;
    }
}
