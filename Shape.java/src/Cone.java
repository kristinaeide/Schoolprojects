public class Cone extends Shape {
    private final double radius;
    private final double height;

    public Cone(double radius, double height) {
        super("Cone");
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be > 0. Given: " + radius);
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be > 0. Given: " + height);
        }
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    private double slantHeight() {
        // l = sqrt(r^2 + h^2)
        return Math.sqrt((radius * radius) + (height * height));
    }

    @Override
    public double surface_area() {
        // Total surface area = πr^2 + πrl
        return (Math.PI * radius * radius) + (Math.PI * radius * slantHeight());
    }

    @Override
    public double volume() {
        // Volume = (1/3)πr^2h
        return (1.0 / 3.0) * Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return getLabel()
                + " (r=" + fmt(radius) + ", h=" + fmt(height) + ", l=" + fmt(slantHeight()) + ")\n"
                + "  Surface Area: " + fmt(surface_area()) + "\n"
                + "  Volume:       " + fmt(volume());
    }
}