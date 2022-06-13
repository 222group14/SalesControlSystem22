package src.product;

import java.util.Comparator;

public class Electronic extends Product{
    
    /**
     * Guarantee time of the electonic product
     */
    private int guaranteeTime;

    /**
     * Display state of the electonic product
     */
    private boolean displayProduct;

    /**
     * Width of the electonic product
     */
    private double width; 

    /**
     * Height of the electonic product
     */
    private double height; 

    /**
	 * Constructs a Electronic object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the  electronic product 
	 * @param brand Name of the brand
	 * @param entryPrice Entry price for the product
     * @param guaranteeTime Guarantee time of the electronic product 
     * @param displayProduct Display state of the electronic product 
     * @param width Width of the electronic product 
     * @param height Height of the electronic product  
	 */
    public Electronic(String name, String brand, double entryPrice,
                        int guaranteeTime, boolean displayProduct
                        , double width, double height) {
        super(name, brand, ProductType.ELECTRONIC, entryPrice);
        this.guaranteeTime = guaranteeTime;
        this.displayProduct = displayProduct;
        this.width = width; 
        this.height = height; 
    }

    /**
	 * Returns the guarantee time of the electonic product
	 * @return Guarantee time of the electonic product
	 */
    public int getGuaranteeTime(){
        return guaranteeTime;
    }

    /**
	 * Returns the display state of the electonic product
	 * @return Display state of the electonic product
	 */
    public boolean isDisplayProduct(){
        return displayProduct;
    }

    /**
	 * Returns the width of the electonic product
	 * @return Width of the electonic product
	 */
    public double getWidth(){
        return width;
    }


    /**
	 * Returns the height of the electonic product
	 * @return Height of the electonic product
	 */
    public double getHeight(){
        return height;
    } 

	/**
	 * Comparator class for sorting the electornic products according to their width
	 */
	public static class compareByWidth implements Comparator<Electronic> {
		@Override
		public int compare(Electronic arg0, Electronic arg1) {
			double comp = arg0.getWidth() - arg1.getWidth();
            if (comp < 0.0) 
                return -1;
            else if (comp > 0.0)
                return 1;
            else
                return 0;
		}
	}

	/**
	 * Comparator class for sorting the electornic products according to their height
	 */
	public static class compareByHeight implements Comparator<Electronic> {
		@Override
		public int compare(Electronic arg0, Electronic arg1) {
			double comp = arg0.getHeight() - arg1.getHeight();
            if (comp < 0.0) 
                return -1;
            else if (comp > 0.0)
                return 1;
            else
                return 0;
		}
	}

    /**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, guarantee time, display state, witdh, height
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\n Guarantee Time: " + getGuaranteeTime());
        sb.append("\n Display Product: " + isDisplayProduct());
        sb.append("\n Width: " + getWidth());
        sb.append("\n Heigth: " + getHeight());


		return sb.toString();
	}

    /**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, guarantee time, display state, witdh, height
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Electronic){
			Electronic otherElectronic = (Electronic) other;

			//comparisons
			if(!super.equals(otherElectronic) ||
                this.getGuaranteeTime() != otherElectronic.getGuaranteeTime() ||
                this.isDisplayProduct() != otherElectronic.isDisplayProduct() || 
                this.getWidth() != otherElectronic.getWidth() ||
                this.getHeight() != otherElectronic.getHeight()) 
				{
					return false;
				}
			return true;
		}
		return false;
	}

    /**
	 * Creates a hash code for the object of the class Electronic.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
        hcode += getGuaranteeTime()* 37;
        hcode += getWidth()* 43;
		hcode += getHeight()* 53;
		return hcode;
	}
    
}
