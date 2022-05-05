package SalesControlSystem.product;

import java.util.ArrayList;
import java.util.List;

public abstract class Product implements Comparable<Product> {

	private String name;
	private String brand;
	private String type;
	private double entryPrice;

	private static final double PROFIT_RATE = 1.3;
	
	public Product(String name, String brand, String type, double entryPrice) {
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.entryPrice = entryPrice;
	}

	public double getSalePrice() {
		return PROFIT_RATE*entryPrice;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getType() {
		return type;
	}

	public double getEntryPrice() {
		return entryPrice;
	}

	public void setName(String name) {
		this.name = name;
	} 

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEntryPrice(double entryPrice) {
		// insert: error checkings
		this.entryPrice = entryPrice;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// implement
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		// implement equals
		return true;
	}

	@Override
	public int hashCode() {
		//implement hashcode
		return 0;
	}

	@Override
	public int compareTo(Product other) {
		if(entryPrice > other.entryPrice)
			return 1;
		else if(entryPrice < other.entryPrice)
			return -1;
		return 0;
	}
}