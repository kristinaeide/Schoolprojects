/**
 * Abstract base for 3D shapes.
 * Each concrete shape must provide surface area and volume.
 */
public abstract class Shape {
    private final String label;

    protected Shape(String label) {
        this.label = (label == null || label.isBlank()) ? "Shape" : label.trim();
    }

    public String getLabel() {
        return label;
    }

    public abstract double surface_area();
    public abstract double volume();

    /** Helper for consistent numeric formatting in toString methods. */
    protected String fmt(double value) {
        return String.format("%.4f", value);
    }
}
