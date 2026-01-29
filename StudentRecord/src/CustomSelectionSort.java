import java.util.ArrayList;
import java.util.Comparator;

public class CustomSelectionSort {

    public static <T> void sort(ArrayList<T> list, Comparator<T> comparator) {

        for (int i = 0; i < list.size() - 1; i++) {
            int smallestIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(smallestIndex)) < 0) {
                    smallestIndex = j;
                }
            }

            if (smallestIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(smallestIndex));
                list.set(smallestIndex, temp);
            }
        }
    }
}