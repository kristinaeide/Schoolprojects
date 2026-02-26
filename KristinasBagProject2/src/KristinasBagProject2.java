import java.util.ArrayList;

/*
 * Bag Data Structure - Extended Version
 * Author: Kristina Eide
 *
 * This example uses a "Kids Snack Bin" theme because in my house,
 * snacks multiply and disappear constantly.
 * This demonstrates size(), merge(), and distinct() methods.
 */

class Bag<T> {

    private ArrayList<T> items;

    public Bag() {
        items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        if (items.contains(item)) {
            items.remove(item); // removes first occurrence
        }
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    public int count(T item) {
        int counter = 0;
        for (T element : items) {
            if (element.equals(item)) {
                counter++;
            }
        }
        return counter;
    }

    // Returns total number of snacks (duplicates included)
    public int size() {
        return items.size();
    }

    // Merges another snack bin into this one
    public void merge(Bag<T> otherBag) {
        for (T element : otherBag.items) {
            this.items.add(element);
        }
    }

    // Returns a new bag with only distinct snacks
    public Bag<T> distinct() {
        Bag<T> distinctBag = new Bag<>();

        for (T element : this.items) {
            if (!distinctBag.contains(element)) {
                distinctBag.add(element);
            }
        }

        return distinctBag;
    }

    public void printBag(String label) {
        System.out.println(label + ": " + items);
    }
}


public class KristinasBagProject2 {

    public static void main(String[] args) {

        // Snack bin #1 (Amaya’s side of the pantry)
        Bag<String> amayaSnackBin = new Bag<>();
        amayaSnackBin.add("Goldfish");
        amayaSnackBin.add("Fruit Snacks");
        amayaSnackBin.add("Granola Bar");
        amayaSnackBin.add("Goldfish"); // duplicate
        amayaSnackBin.add("Juice Box");

        // Snack bin #2 (Adalynn’s side of the pantry)
        Bag<String> adalynnSnackBin = new Bag<>();
        adalynnSnackBin.add("Fruit Snacks");
        adalynnSnackBin.add("Applesauce");
        adalynnSnackBin.add("Goldfish");
        adalynnSnackBin.add("Fruit Snacks"); // duplicate
        adalynnSnackBin.add("Cheez-Its");

        System.out.println("---- BEFORE MERGE ----");
        amayaSnackBin.printBag("Amaya's Snack Bin");
        adalynnSnackBin.printBag("Adalynn's Snack Bin");

        // Print sizes
        System.out.println("\nAmaya's Bin Size: " + amayaSnackBin.size());
        System.out.println("Adalynn's Bin Size: " + adalynnSnackBin.size());

        // Merge bins (because realistically they end up mixed anyway)
        System.out.println("\nMerging snack bins...");
        amayaSnackBin.merge(adalynnSnackBin);

        System.out.println("\n---- AFTER MERGE ----");
        amayaSnackBin.printBag("Combined Snack Bin");
        System.out.println("Combined Bin Size: " + amayaSnackBin.size());

        // Create distinct-only snack bin
        System.out.println("\nCreating a bin with only distinct snacks...");
        Bag<String> distinctSnacks = amayaSnackBin.distinct();

        System.out.println("\n---- DISTINCT SNACK BIN ----");
        distinctSnacks.printBag("Distinct Snacks Only");
        System.out.println("Distinct Snack Count: " + distinctSnacks.size());
    }
}