public class Sphere extends Shape {
    private final double radius;

    public Sphere(double radius) {
        super("Sphere");
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be > 0. Given: " + radius);
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double surface_area() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    public double volume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    @Override
    public String toString() {
        return getLabel()
                + " (r=" + fmt(radius) + ")\n"
                + "  Surface Area: " + fmt(surface_area()) + "\n"
                + "  Volume:       " + fmt(volume());
    }
}