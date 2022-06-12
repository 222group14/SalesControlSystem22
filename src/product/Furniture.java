package src.product;

public class Furniture extends Product{

    /** 
     * Width of the furniture 
     */
    private double width;

    /** 
     * Height of the furniture 
     */
    private double height;

    /** 
     * Color of the furniture 
     */
    private String color;

    /**
	 * Constructs a Furniture object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the furniture
	 * @param brand Name of the brand
	 * @param type Type of the product (Furniture)
	 * @param entryPrice Entry price for the product
     * @param width Width of the furniture 
     * @param height Height of the furniture 
     * @param color Color of the furniture 
	 */
    public Furniture(String name, String brand, String type, double entryPrice,
                        double width, double height, String color) {
        super(name, brand, type, entryPrice);
        this.width = width; 
        this.height = height;  
        this.color = color;                
    }

    /**
	 * Constructs a Furniture object with given properties  
	 * @param name Name of the furniture
	 * @param brand Name of the brand
	 * @param type Type of the product (Furniture)
	 * @param entryPrice Entry price for the product
     * @param width width of the furniture 
     * @param height height of the furniture 
     * @param color color of the furniture 
	 * @param numStock Number of stock for the product
	 */
    public Furniture(String name, String brand, String type, double entryPrice,
                        double width, double height, String color, int numStock) {
        super(name, brand, type, entryPrice, numStock);
        this.width = width; 
        this.height = height;  
        this.color = color;                
    }

    /**
	 * Returns the width of the furniture 
	 * @return Width of the furniture 
	 */
    public double getWidth(){
        return width;
    }

    /**
	 * Returns the height of the furniture 
	 * @return Height of the furniture 
	 */
    public double getHeight(){
        return height;
    }

    /**
	 * Returns the color of the furniture 
	 * @return Color of the furniture 
	 */
    public String getColor(){
        return color;
    }

    /**
	 * Returns string of the properties of the product : 
     * Name, brand, type, entry price, color, width, height
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nColor: " + getColor());
        sb.append("\nWidth: " + getWidth());
        sb.append("\nHeigth: " + getHeight());
		return sb.toString();
	}

    /**
	 * Checks whether two furniture objects are equal or not.
	 * Compares : Name, brand, type, entryprice, color, width, height
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Furniture){
			Furniture otherFurniture = (Furniture) other;

			//comparisons
			if(!super.equals(otherFurniture) ||
                !this.getColor().equals(otherFurniture.getColor()) || 
                this.getWidth() != otherFurniture.getWidth() ||
                this.getHeight() != otherFurniture.getHeight()) 
				{
					return false;
				}
			return true;
		}
		return false;
	}

    /**
	 * Creates a hash code for the object of the class Furniture.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
        hcode += getColor().hashCode()* 37;
        hcode += getWidth()* 43;
		hcode += getHeight()* 53;
		return hcode;
	}
}
