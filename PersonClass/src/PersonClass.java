import java.util.Scanner;

public class PersonClass {

    // -------- Person Class --------
    static class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName.trim();
            this.lastName = lastName.trim();
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", Age: " + age;
        }
    }

    // -------- Queue Class --------
    static class PersonQueue {

        private static class Node {
            Person data;
            Node next;

            public Node(Person data) {
                this.data = data;
            }
        }

        private Node front;
        private Node rear;
        private int size;

        public void enqueue(Person person) {
            Node newNode = new Node(person);

            if (rear == null) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }

            size++;
        }

        public boolean isEmpty() {
            return front == null;
        }

        public void displayQueue() {
            Node current = front;

            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }

        public Person[] toArray() {
            Person[] arr = new Person[size];
            Node current = front;
            int index = 0;

            while (current != null) {
                arr[index++] = current.data;
                current = current.next;
            }

            return arr;
        }

        // -------- Quick Sort by Last Name (Descending) --------
        public static void quickSortByLastName(Person[] arr, int low, int high) {
            if (low < high) {
                int pivotIndex = partitionLastName(arr, low, high);
                quickSortByLastName(arr, low, pivotIndex - 1);
                quickSortByLastName(arr, pivotIndex + 1, high);
            }
        }

        private static int partitionLastName(Person[] arr, int low, int high) {
            Person pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].getLastName().compareToIgnoreCase(pivot.getLastName()) > 0) {
                    i++;
                    swap(arr, i, j);
                }
            }

            swap(arr, i + 1, high);
            return i + 1;
        }

        // -------- Quick Sort by Age (Descending) --------
        public static void quickSortByAge(Person[] arr, int low, int high) {
            if (low < high) {
                int pivotIndex = partitionAge(arr, low, high);
                quickSortByAge(arr, low, pivotIndex - 1);
                quickSortByAge(arr, pivotIndex + 1, high);
            }
        }

        private static int partitionAge(Person[] arr, int low, int high) {
            Person pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].getAge() > pivot.getAge()) {
                    i++;
                    swap(arr, i, j);
                }
            }

            swap(arr, i + 1, high);
            return i + 1;
        }

        private static void swap(Person[] arr, int i, int j) {
            Person temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static void displayArray(Person[] arr) {
            for (Person p : arr) {
                System.out.println(p);
            }
        }
    }

    // -------- Main Method --------
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char choice;

        do {
            PersonQueue queue = new PersonQueue();

            System.out.println("\n--- Queue Sorting Program ---");
            System.out.println("Enter 5 people:\n");

            for (int i = 1; i <= 5; i++) {
                System.out.println("Person #" + i);

                System.out.print("First name: ");
                String firstName = input.nextLine();

                System.out.print("Last name: ");
                String lastName = input.nextLine();

                int age;
                while (true) {
                    System.out.print("Age: ");
                    if (input.hasNextInt()) {
                        age = input.nextInt();
                        input.nextLine();

                        if (age >= 0) break;
                        else System.out.println("Enter a valid age.");
                    } else {
                        System.out.println("Numbers only.");
                        input.nextLine();
                    }
                }

                queue.enqueue(new Person(firstName, lastName, age));
                System.out.println();
            }

            // Original Queue
            System.out.println("Original Queue:");
            queue.displayQueue();

            // Sort by Last Name
            Person[] lastSorted = queue.toArray();
            PersonQueue.quickSortByLastName(lastSorted, 0, lastSorted.length - 1);

            System.out.println("\nSorted by Last Name (Descending):");
            PersonQueue.displayArray(lastSorted);

            // Sort by Age
            Person[] ageSorted = queue.toArray();
            PersonQueue.quickSortByAge(ageSorted, 0, ageSorted.length - 1);

            System.out.println("\nSorted by Age (Descending):");
            PersonQueue.displayArray(ageSorted);

            // Restart option
            while (true) {
                System.out.print("\nRun again with new people? (y/n): ");
                String response = input.nextLine().toLowerCase();

                if (response.equals("y") || response.equals("n")) {
                    choice = response.charAt(0);
                    break;
                } else {
                    System.out.println("Please enter y or n.");
                }
            }

        } while (choice == 'y');

        System.out.println("\nProgram finished.");
        input.close();
    }
}