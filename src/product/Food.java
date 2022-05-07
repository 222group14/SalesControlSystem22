package src.product;

public class Food extends Product {

    private String expDate;
	private double weight;

    public Food(String name, String brand, String type, double entryPrice, 
                 String expDate, double weight) {
        super(name, brand, type, entryPrice);
        this.expDate = expDate;
		this.weight = weight;
    }

    public String getExpDate(){
        return expDate;
    }

	public double getWeight(){
		return weight;
	}

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nExpiry Date: " + getExpDate());
		sb.append("\nWeight(kg): " + getWeight());

		return sb.toString();
	}

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

	@Override
	public int hashCode() {
		int hcode = super.hashCode();
        hcode += getExpDate().hashCode()* 37;
		hcode += Double.valueOf(getWeight()).hashCode() * 43;

		return hcode;
	}
}