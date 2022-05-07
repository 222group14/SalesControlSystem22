package src.product;

import src.enums.Gender;
import src.enums.Size;

public class Clothes extends Product {

	private Size size;
	private String materialType;
	private String color;
	private boolean summeryState;
	private Gender gender;

	public Clothes(String name, String brand, String type, double entryPrice,
					 Size size, String materialType, String color, 
					 boolean summeryState, Gender gender) {
		super(name, brand, type, entryPrice);
		this.size = size;
		this.materialType = materialType;
		this.color = color;
		this.summeryState = summeryState;
		this.gender = gender;
	}

	public Size getSize() {
		return size;
	}

	public String getMaterialType() {
		return materialType;
	}

	public String getColor() {
		return color;
	}

	public boolean isSummery() {
		return summeryState;
	}

	public Gender getGender() {
		return gender;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nSize: " + getSize());
		sb.append("\nMaterial Type: " + getMaterialType());
		sb.append("\nColor: " + getColor());
		sb.append("\nSummery State: " + isSummery());
		sb.append("\nGender: " + getGender());

		return sb.toString();
	}

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