import java.util.Arrays;

public class RadixSortStrings {

    public static void radixSort(String[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int maxLength = getMaxLength(arr);

        // Start from the rightmost character and move left
        for (int position = maxLength - 1; position >= 0; position--) {
            countingSortByCharacter(arr, position);
        }
    }

    private static void countingSortByCharacter(String[] arr, int position) {
        int n = arr.length;
        String[] output = new String[n];

        // 256 covers standard ASCII characters
        int[] count = new int[256];

        // Count frequency of each character at the current position
        for (String word : arr) {
            int charIndex = getCharacterAt(word, position);
            count[charIndex]++;
        }

        // Convert count array into cumulative count
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Build output array from right to left to keep the sort stable
        for (int i = n - 1; i >= 0; i--) {
            int charIndex = getCharacterAt(arr[i], position);
            output[count[charIndex] - 1] = arr[i];
            count[charIndex]--;
        }

        // Copy sorted values back into original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    private static int getCharacterAt(String word, int position) {
        // If the word is shorter than the current position,
        // return 0 so shorter words come first
        if (position >= word.length()) {
            return 0;
        }
        return word.charAt(position);
    }

    private static int getMaxLength(String[] arr) {
        int max = 0;
        for (String word : arr) {
            if (word.length() > max) {
                max = word.length();
            }
        }
        return max;
    }

    public static void printArray(String message, String[] arr) {
        System.out.println(message + Arrays.toString(arr));
    }
}