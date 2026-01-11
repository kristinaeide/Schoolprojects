public class ShapeArray {

    private static void printDivider() {
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        // Part 4: Instantiate shapes
        Shape sphere = new Sphere(3.5);
        Shape cylinder = new Cylinder(2.0, 6.0);
        Shape cone = new Cone(2.5, 4.0);

        // Store into an array named shapeArray
        Shape[] shapeArray = { sphere, cylinder, cone };

        // Loop and print each instance using toString
        for (int i = 0; i < shapeArray.length; i++) {
            System.out.println("Shape #" + (i + 1));
            System.out.println(shapeArray[i].toString());
            printDivider();
        }

        // Extra test case output (helpful for screenshots)
        Shape[] moreTests = {
                new Sphere(1.0),
                new Cylinder(3.0, 1.25),
                new Cone(5.0, 2.0)
        };

        System.out.println("\nAdditional Test Cases");
        printDivider();
        for (Shape s : moreTests) {
            System.out.println(s);
            printDivider();
        }
    }
}