import java.util.Comparator;

public class SortByStudentName implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord s1, StudentRecord s2) {
        return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
    }
}