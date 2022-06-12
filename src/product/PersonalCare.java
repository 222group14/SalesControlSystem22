package src.product;

public class PersonalCare extends Product{

	/**
	 * Usage aim of the personal care product
	 */
	private String usageAim;
	
	/**
	 * Expiry date of the personal care product
	 */
	private String expDate;

	/**
	 * Constructs a PersonalCare object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the personal care product
	 * @param brand Name of the brand
	 * @param type Type of the product (PersonalCare)
	 * @param entryPrice Entry price for the product
	 * @param usageAim Usage aim of the personal care product
	 * @param expDate Expiry date of the personal care product
	 */
    public PersonalCare(String name, String brand, String type, double entryPrice,
					 String usageAim, String expDate) {
        super(name, brand, type, entryPrice);
		this.usageAim = usageAim;
		this.expDate = expDate;
    }

	/**
	 * Constructs a PersonalCare object with given properties
	 * @param name Name of the personal care product
	 * @param brand Name of the brand
	 * @param type Type of the product (PersonalCare)
	 * @param entryPrice Entry price for the product
	 * @param usageAim Usage aim of the personal care product
	 * @param expDate Expiry date of the personal care product
	 * @param numStock Number of stock for the product
	 */
    public PersonalCare(String name, String brand, String type, double entryPrice,
					 String usageAim, String expDate, int numStock) {
        super(name, brand, type, entryPrice, numStock);
		this.usageAim = usageAim;
		this.expDate = expDate;
    }

	/** 
	 * Returns the usage aim of the product
	 * @return usage aim of the product
	 */
	public String getUsageAim(){
		return usageAim;
	}

	/**
	 * Returns the expiry date of the product
	 * @return Expiry date of the product
	 */
	public String getExpDate(){
		return expDate;
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entry price, usage aim, expiry date
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nUsage Aim: " + getUsageAim());
		sb.append("\nExpiry Date: " + getExpDate());
		return sb.toString();
	}

	/**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, expiry date, usage aim
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof PersonalCare){
			PersonalCare otherPersonalCare = (PersonalCare) other;

			//comparisons
			if(!super.equals(otherPersonalCare) ||
				!this.getExpDate().equals(otherPersonalCare.getExpDate()) ||
				!this.getUsageAim().equals(otherPersonalCare.getUsageAim())) 
            {
                return false;
            }
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class PersonalCare.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
		hcode += getExpDate().hashCode() * 37;
		hcode += getUsageAim().hashCode() * 43;
		return hcode;
	}
}
