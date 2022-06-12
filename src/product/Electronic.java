package src.product;

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
	 * @param type Type of the product (Electronic)
	 * @param entryPrice Entry price for the product
     * @param guaranteeTime Guarantee time of the electronic product 
     * @param displayProduct Display state of the electronic product 
     * @param width Width of the electronic product 
     * @param height Height of the electronic product  
	 */
    public Electronic(String name, String brand, String type, double entryPrice,
                        int guaranteeTime, boolean displayProduct
                        , double width, double height) {
        super(name, brand, type, entryPrice);
        this.guaranteeTime = guaranteeTime;
        this.displayProduct = displayProduct;
        this.width = width; 
        this.height = height; 
    }

    /**
	 * Constructs a Electronic object with given properties
	 * @param name Name of the  electronic product 
	 * @param brand Name of the brand
	 * @param type Type of the product (Electronic)
	 * @param entryPrice Entry price for the product
     * @param guaranteeTime Guarantee time of the electronic product 
     * @param displayProduct Display state of the electronic product 
     * @param width Width of the electronic product 
     * @param height Height of the electronic product  
     * @param numStock Number of stock for the product
	 */
    public Electronic(String name, String brand, String type, double entryPrice,
                        int guaranteeTime, boolean displayProduct
                        , double width, double height, int numStock) {
        super(name, brand, type, entryPrice, numStock);
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
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, guarantee time, display state, witdh, height
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nGuarantee Time: " + getGuaranteeTime());
        sb.append("\nDisplay Product: " + isDisplayProduct());
        sb.append("\nWidth: " + getWidth());
        sb.append("\nHeigth: " + getHeight());
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
