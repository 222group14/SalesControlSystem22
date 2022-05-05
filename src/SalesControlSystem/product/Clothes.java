package SalesControlSystem.product;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.enums.Size;

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
		// insert
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		// insert
		return false;
	}

	@Override
	public int hashCode() {
		//insert
		return 0;
	}
}