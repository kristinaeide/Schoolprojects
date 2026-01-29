public class StudentRecord {

    private int rollNumber;
    private String studentName;
    private String studentAddress;

    public StudentRecord(int rollNumber, String studentName, String studentAddress) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    @Override
    public String toString() {
        return rollNumber + " | " + studentName + " | " + studentAddress;
    }
}