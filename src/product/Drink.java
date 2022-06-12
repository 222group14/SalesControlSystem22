package src.product;

public class Drink extends Product {

	/**
	 * Expiry date of the drink
	 */
    private String expDate;
	/**
	 * Liter of the drink
	 */
	private double liter;

	/**
	 * Constructs a Drink object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the drink
	 * @param brand Name of the brand
	 * @param type Type of the product (Drink)
	 * @param entryPrice Entry price for the product
     * @param expDate Expiry date of the drink
	 * @param liter Liter of the drink
	 */
    public Drink(String name, String brand, String type, double entryPrice, 
                 String expDate, double liter) {
        super(name, brand, type, entryPrice);
        this.expDate = expDate;
		this.liter = liter;
    }

	/**
	 * Constructs a Drink object with given properties 
	 * @param name Name of the drink
	 * @param brand Name of the brand
	 * @param type Type of the product (Drink)
	 * @param entryPrice Entry price for the product
     * @param expDate Expiry date of the drink
	 * @param liter Liter of the drink
	 * @param numStock Number of stock for the product
	 */
    public Drink(String name, String brand, String type, double entryPrice, 
                 String expDate, double liter, int numStock) {
        super(name, brand, type, entryPrice, numStock);
        this.expDate = expDate;
		this.liter = liter;
    }

	/**
	 * Returns the expiry date of the product
	 * @return Expiry date of the product
	 */
    public String getExpDate(){
        return expDate;
    }

	/**
	 * Returns the liter of the drink
	 * @return Liter of the drink
	 */
	public double getSizeLiter(){
		return liter;
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, expiry date, liter
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nExpiry Date: " + getExpDate());
		sb.append("\nLiter: " + getSizeLiter());
		return sb.toString();
	}

	/**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, expiry date, liter
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Drink){
			Drink otherDrink = (Drink) other;

			//comparisons
			if(!super.equals(otherDrink) ||
                !this.getExpDate().equals(otherDrink.getExpDate()) ||
				this.getSizeLiter() != otherDrink.getSizeLiter())
				{
					return false;
				}
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class Drink.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
        hcode += getExpDate().hashCode()* 37;
		hcode += Double.valueOf(getSizeLiter()).hashCode() * 43;
		return hcode;
	}
}