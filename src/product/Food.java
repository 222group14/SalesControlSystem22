package src.product;

import java.util.Comparator;

public class Food extends Product {

	/**
	 * Expiry date of the food
	 */
    private String expDate;

	/**
	 * Weight of the food
	 */
	private double weight;

	/**
	 * Constructs a Dood object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the food
	 * @param brand Name of the brand
	 * @param entryPrice Entry price for the product
     * @param expDate Expiry date of the food
	 * @param weight Weight of the food
	 */
    public Food(String name, String brand, double entryPrice, 
                 String expDate, double weight) {
        super(name, brand, ProductType.FOOD, entryPrice);
        this.expDate = expDate;
		this.weight = weight;
    }

	/**
	 * Constructs a Food object with given properties
	 * @param name Name of the food
	 * @param brand Name of the brand
	 * @param entryPrice Entry price for the product
     * @param expDate Expiry date of the food
	 * @param weight Weight of the food
	 * @param numStock Number of stock for the product
	 */
    public Food(String name, String brand, String type, double entryPrice, 
                 String expDate, double weight, int numStock) {
        super(name, brand, ProductType.FOOD, entryPrice, numStock);
        this.expDate = expDate;
		this.weight = weight;
    }

	/**
	 * Returns the expiry date of the food
	 * @return Expiry date of the food
	 */
    public String getExpDate(){
        return expDate;
    }

	/**
	 * Returns the weight of the food
	 * @return Weight of the food
	 */
	public double getWeight(){
		return weight;
	}

	/**
	 * Comparator class for sorting the foods according to expiry date
	 */
	public static class compareByExpDate implements Comparator<Food> {
		@Override
		public int compare(Food arg0, Food arg1) {
			return arg0.getExpDate().compareTo(arg1.getExpDate());			
		}
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, expiry date, weight
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\n Expiry Date: " + getExpDate());
		sb.append("\n Weight(kg): " + getWeight());

		return sb.toString();
	}

	/**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, expiry date, weight
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Food){
			Food otherFood = (Food) other;

			//comparisons
			if(!super.equals(otherFood) ||
                !this.getExpDate().equals(otherFood.getExpDate()) ||
				this.getWeight() != otherFood.getWeight())
				{
					return false;
				}
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class Food.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
        hcode += getExpDate().hashCode()* 37;
		hcode += Double.valueOf(getWeight()).hashCode() * 43;
		return hcode;
	}
}