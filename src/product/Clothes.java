package src.product;

public class Clothes extends Product {
 
	/**
	 * Size of the cloth 
	 */
	private Size size;

	/**
	 * Material Type of the cloth
	 */
	private String materialType;

	/**
	 * Color of the cloth
	 */
	private String color;

	/** 
	 * Summery State of the cloth 
	 */
	private boolean summeryState;

	/** 
	 * Gender of the cloth 
	 */
	private Gender gender;

	/**
	 * Constructs a Clothes object with given properties
     * (Number of stock is set to its default value 0)  
	 * @param name Name of the cloth
	 * @param brand Name of the brand
	 * @param entryPrice Entry price for the product
     * @param size Size of the cloth
	 * @param materialType Material Type of the cloth
	 * @param color Color of the cloth
	 * @param summeryState Summery state of the cloth 
	 * @param gender Gender of the cloth
	 */
	public Clothes(String name, String brand, double entryPrice,
					 Size size, String materialType, String color, 
					 boolean summeryState, Gender gender) {
		super(name, brand, ProductType.CLOTHES, entryPrice);
		this.size = size;
		this.materialType = materialType;
		this.color = color;
		this.summeryState = summeryState;
		this.gender = gender;
	}

	/**
	 * Constructs a Clothes object with given properties
	 * @param name Name of the cloth
	 * @param brand Name of the brand
	 * @param entryPrice Entry price for the product
     * @param size Size of the cloth
	 * @param materialType Material Type of the cloth
	 * @param color Color of the cloth
	 * @param summeryState Summery state of the cloth 
	 * @param gender Gender of the cloth
	 * @param numStock Number of stock for the product
	 */
	public Clothes(String name, String brand, double entryPrice,
					 Size size, String materialType, String color, 
					 boolean summeryState, Gender gender, int numStock) {
		super(name, brand, ProductType.CLOTHES, entryPrice, numStock);
		this.size = size;
		this.materialType = materialType;
		this.color = color;
		this.summeryState = summeryState;
		this.gender = gender;
	}

	/**
	 * Returns the size of the cloth
	 * @return Size of the cloth
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * Returns the material type of the cloth
	 * @return Material type of the cloth
	 */
	public String getMaterialType() {
		return materialType;
	}

	/**
	 * Returns the color of the cloth
	 * @return Color of the cloth
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Returns the summery state of the cloth
	 * @return Summery state of the cloth
	 */
	public boolean isSummery() {
		return summeryState;
	}

	/**
	 * Returns the gender of the cloth
	 * @return Gender of the cloth
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, size, material type, color, summery state, gender
     * @return String of the properties of the product
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\n Size: " + getSize());
		sb.append("\n Material Type: " + getMaterialType());
		sb.append("\n Color: " + getColor());
		sb.append("\n Summery State: " + isSummery());
		sb.append("\n Gender: " + getGender());

		return sb.toString();
	}

	/**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, size, material type, color, summery state, gender
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Clothes){
			Clothes otherClothes = (Clothes) other;

			//comparisons
			if(!super.equals(otherClothes) ||
				!this.getSize().equals(otherClothes.getSize()) ||
			    !this.getMaterialType().equals(otherClothes.getMaterialType()) ||
				!this.getColor().equals(otherClothes.getColor()) ||
				this.isSummery() != otherClothes.isSummery() ||
				!this.getGender().equals(otherClothes.getGender()))
				{
					return false;
				}
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class Clothes.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
		hcode += getType().hashCode() * 37;
		hcode += getSize().hashCode()* 43;
		hcode += getMaterialType().hashCode() * 53;
		hcode += getColor().hashCode()* 61;
		hcode += getGender().hashCode()* 79;
		return hcode;
	}
}