package src.incommon;

public class Location {
    /** x position on 2D space */
    private int x;
    /** y position on 2D space */
    private int y;

    /**
     * Constructs an location
     * @param x x position of location
     * @param y y position of location
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs an location (0, 0)
     */
    public Location() {
        y = x = 0;
    }

    /**
     * Calculates Euclidean distance between two point
     * @param other Other location
     * @return The distance between two location
     */
    public double measureDistance(Location other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)); 
    }
}
