import java.util.ArrayList;

public class StudentSort {

    private static void displayList(String title, ArrayList<StudentRecord> list) {
        System.out.println(title);
        for (StudentRecord student : list) {
            System.out.println(student);
        }
        System.out.println();
    }

    private static ArrayList<StudentRecord> duplicateList(ArrayList<StudentRecord> original) {
        return new ArrayList<>(original);
    }

    public static void main(String[] args) {

        ArrayList<StudentRecord> studentList = new ArrayList<>();

        // YOUR UNIQUE STUDENT DATA
        studentList.add(new StudentRecord(28, "Sienna", "New Orleans, LA"));
        studentList.add(new StudentRecord(37, "Lee", "Portland, OR"));
        studentList.add(new StudentRecord(11, "Amaya", "Salt Lake City, UT"));
        studentList.add(new StudentRecord(26, "Paige", "Phoenix, AZ"));
        studentList.add(new StudentRecord(27, "Trinity", "Jacksonville, FL"));
        studentList.add(new StudentRecord(32, "Alyssa", "Billings, MT"));
        studentList.add(new StudentRecord(17, "Josie", "San Diego, CA"));
        studentList.add(new StudentRecord(88, "Diane", "Spokane, WA"));
        studentList.add(new StudentRecord(42, "Spencer", "Dallas, TX"));
        studentList.add(new StudentRecord(9, "Adalynn", "Boise, ID"));

        displayList("Original Student List:", studentList);

        ArrayList<StudentRecord> sortedByName = duplicateList(studentList);
        CustomSelectionSort.sort(sortedByName, new SortByStudentName());
        displayList("Sorted by Student Name:", sortedByName);

        ArrayList<StudentRecord> sortedByRoll = duplicateList(studentList);
        CustomSelectionSort.sort(sortedByRoll, new SortByRollNumber());
        displayList("Sorted by Roll Number:", sortedByRoll);
    }
}