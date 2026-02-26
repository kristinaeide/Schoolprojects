import java.util.ArrayList;

/*
 * Bag Data Structure
 * Author: Kristina Eide
 * This program demonstrates a generic Bag (multiset) 
 * that allows duplicates and does not maintain order.
 */

class Bag<T> {

    // Using ArrayList to store the bag items
    private ArrayList<T> items;

    // Default constructor
    public Bag() {
        items = new ArrayList<>();
    }

    // Adds an item to the bag
    public void add(T item) {
        items.add(item);
    }

    // Removes one occurrence of the item (if it exists)
    public void remove(T item) {
        if (items.contains(item)) {
            items.remove(item); // removes first occurrence
        }
    }

    // Checks if the bag contains the item
    public boolean contains(T item) {
        return items.contains(item);
    }

    // Counts how many times an item appears in the bag
    public int count(T item) {
        int counter = 0;

        for (T element : items) {
            if (element.equals(item)) {
                counter++;
            }
        }

        return counter;
    }

    // Prints the contents of the bag
    public void printBag() {
        System.out.println("Bag Contents: " + items);
    }
}


public class KristinaBagAssignment {

    public static void main(String[] args) {

        // Creating a Bag of Strings
        Bag<String> snackBag = new Bag<>();

        // Adding snacks (duplicates included on purpose)
        snackBag.add("Goldfish");
        snackBag.add("Fruit Snacks");
        snackBag.add("Granola Bar");
        snackBag.add("Goldfish");
        snackBag.add("Juice Box");
        snackBag.add("Goldfish");

        System.out.println("Initial Snack Bag:");
        snackBag.printBag();

        // Testing contains
        System.out.println("\nDoes the bag contain Goldfish? " + snackBag.contains("Goldfish"));
        System.out.println("Does the bag contain Apples? " + snackBag.contains("Apples"));

        // Testing count
        System.out.println("\nHow many Goldfish are in the bag? " + snackBag.count("Goldfish"));
        System.out.println("How many Juice Boxes are in the bag? " + snackBag.count("Juice Box"));

        // Removing one occurrence
        System.out.println("\nRemoving one Goldfish...");
        snackBag.remove("Goldfish");

        System.out.println("\nSnack Bag After Removal:");
        snackBag.printBag();

        // Testing again after removal
        System.out.println("\nDoes the bag still contain Goldfish? " + snackBag.contains("Goldfish"));
        System.out.println("How many Goldfish are left? " + snackBag.count("Goldfish"));
    }
}