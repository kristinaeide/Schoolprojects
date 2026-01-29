import java.util.Comparator;

public class SortByRollNumber implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord s1, StudentRecord s2) {
        return Integer.compare(s1.getRollNumber(), s2.getRollNumber());
    }
}