import java.util.Scanner;

public class RecursiveProduct {

    // Recursive method to calculate the product of the numbers
    public static int calculateProduct(int[] numbers, int index) {
        // Base case: when we reach the end of the array
        if (index == numbers.length) {
            return 1;
        }

        // Recursive call
        return numbers[index] * calculateProduct(numbers, index + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];

        System.out.println("Enter five numbers:");

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        int product = calculateProduct(numbers, 0);

        System.out.println("The product of the five numbers is: " + product);
        scanner.close();
    }
}