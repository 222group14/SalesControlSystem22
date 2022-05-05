package src.product;

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
		sb.append("\nName: " + getName());
		sb.append("\nBrand Name: " + getBrand());
		sb.append("\nType: " + getType());
		sb.append("\nEntry Price: " + getEntryPrice());
		return sb.toString();
	}

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

	@Override
	public int hashCode() {
		int hcode = getName().hashCode()* 7;
		hcode += getBrand().hashCode()* 13;
		hcode += getType().hashCode() * 19;
		hcode += getEntryPrice()* 29;
		
		return hcode;
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