package src.product;

import java.util.NoSuchElementException;

public class Product implements Comparable<Product> {
	/** 
	 * Default number of stock
	 */
	public static final int DEFAULT_NUM_STOCK = 0;  

	/**
	 * The profit rate of the product
	 */
	public static final double PROFIT_RATE = 1.3;

	/**
	 * Name of the product
	 */
	private String name;

	/**
	 * Name of the brand
	 */
	private String brand;

	/**
	 * Type of the product (Electronic, Food, Clothes ...)
	 */
	private String type;

	/**
	 * Entry price for the product
	 */
	private double entryPrice;

	/**
	 * Number of stock for the product
	 */
	private int numStock;
	
	/**
	 * Constructs a Product object with given properties
	 * @param name Name of the product
	 * @param brand Name of the brand
	 * @param type Type of the product (Electronic, Food, Clothes ...)
	 * @param entryPrice Entry price for the product
	 * @param numStock Number of stock for the product
	 */
	public Product(String name, String brand, String type, double entryPrice, int numStock) {
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.entryPrice = entryPrice;
		this.numStock = numStock;
	}

	/**
	 * Constructs a Product object with given properties.
	 * (Number of stock is set to its default value 0)   
	 * @param name Name of the product
	 * @param brand Name of the brand
	 * @param type Type of the product (Electronic, Food, Clothes ...)
	 * @param entryPrice Entry price for the product
	 * @param numStock Number of stock for the product
	 */

	public Product(String name, String brand, String type, double entryPrice) {
		this(name, brand, type, entryPrice, 0);
	}

	/**
	 * Returns the price of the sale
	 * @return Price of the sale
	 */
	public double getSalePrice() {
		return PROFIT_RATE*entryPrice;
	}

	/**
	 * Returns the name of the product
	 * @return Name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the name of the brand
	 * @return Name of the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Returns type of the product
	 * @return Type of the product
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the entry price
	 * @return Returns the entry price
	 */
	public double getEntryPrice() {
		return entryPrice;
	}

	/**
	 * Returns the number of stock for the product
	 * @return The number of stock 
	 */
	public int getStock() {
		return numStock;
	}

	/**
	 * Sets the number of stock for the product
	 * @param numStock New number of stock
	 * @return True if the new stock is nonnegative integer value
	 */
	public boolean setStock(int numStock) {
		if (numStock < 0)
			return false;
		this.numStock = numStock;
		return true;
	}

	/**
	 * Decrease the number of stock by one (a product has been sold)
	 * @throws NoSuchElementException Throws exception, if there is no stock left to decrease 
	 */
	public void decreaseStock() throws NoSuchElementException {
		if (numStock <= 0)
			throw new NoSuchElementException();
		--numStock;
	}

	/**
	 * Sets the name of the product
	 * @param name New product name
	 * @return The previous name of the product
	 */
	public String setName(String name) {
		String r = this.name;
		this.name = name;
		return r;
	} 

	/**
	 * Sets the brand name of the product
	 * @param brand New product name
	 * @return The previous brand name of the product
	 */
	public String setBrand(String brand) {
		String r = this.brand;
		this.brand = brand;
		return r;
	}

	/**
	 * Sets the type of the product
	 * @param type New product type
	 * @return The previous type of the product
	 */
	public String setType(String type) {
		String r = this.type;
		this.type = type;
		return r;
	}

	/**
	 * Sets the entry price of the product
	 * @param entryPrice Nonnegative new entry price
	 * @return The previous entry price or -1.0 for negative price
	 */
	public double setEntryPrice(double entryPrice) {
		if (entryPrice < 0.0)
			return -1.0;
		double r = this.entryPrice;
		this.entryPrice = entryPrice;
		return r;		
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entry price
	 * @return String of the properties of the product
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nName: " + getName());
		sb.append("\nBrand Name: " + getBrand());
		sb.append("\nType: " + getType());
		sb.append("\nEntry Price: " + getEntryPrice());
		return sb.toString();
	}

	/**
	 * Checks whether two product objects are equal or not.
	 * Compares : Name, brand, type and entryprice
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Product){
			Product otherProduct = (Product) other;

			//comparisons
			if(!this.getName().equals(otherProduct.getName()) ||
				!this.getBrand().equals(otherProduct.getBrand()) ||
				!this.getType().equals(otherProduct.getType()) ||
				this.getEntryPrice() != otherProduct.getEntryPrice())
				{
					return false;
				}
			
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class Product.
	 * <p>
	 * Note: Product stock is not included in the hash code (it's not a specific property)
	 * </p>
	 */
	@Override
	public int hashCode() {
		int hcode = getName().hashCode()* 7;
		hcode += getBrand().hashCode()* 13;
		hcode += getType().hashCode() * 19;
		hcode += getEntryPrice()* 29;
		return hcode;
	}

	/**
	 * Compares the products according to their entry price
	 */
	@Override
	public int compareTo(Product other) {
		if(entryPrice > other.entryPrice)
			return 1;
		else if(entryPrice < other.entryPrice)
			return -1;
		return 0;
	}
}