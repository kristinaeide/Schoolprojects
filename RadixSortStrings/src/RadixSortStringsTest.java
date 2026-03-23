public class RadixSortStringsTest {

    public static void main(String[] args) {
        String[] words = {
            "joke", "book", "back", "dig", "desk", "word",
            "fish", "ward", "dish", "wit", "deed", "fast",
            "dog", "bend"
        };

        System.out.println("Original array:");
        RadixSortStrings.printArray("", words);

        RadixSortStrings.radixSort(words);

        System.out.println("\nSorted array:");
        RadixSortStrings.printArray("", words);
    }
}