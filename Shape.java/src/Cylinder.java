public class Cylinder extends Shape {
    private final double radius;
    private final double height;

    public Cylinder(double radius, double height) {
        super("Cylinder");
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

    @Override
    public double surface_area() {
        // Total surface area = 2πr^2 + 2πrh
        return (2.0 * Math.PI * radius * radius) + (2.0 * Math.PI * radius * height);
    }

    @Override
    public double volume() {
        // Volume = πr^2h
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return getLabel()
                + " (r=" + fmt(radius) + ", h=" + fmt(height) + ")\n"
                + "  Surface Area: " + fmt(surface_area()) + "\n"
                + "  Volume:       " + fmt(volume());
    }
}