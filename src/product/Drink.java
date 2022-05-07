package src.product;

public class Drink extends Product {

    private String expDate;
	private double liter;

    public Drink(String name, String brand, String type, double entryPrice, 
                 String expDate, double liter) {
        super(name, brand, type, entryPrice);
        this.expDate = expDate;
		this.liter = liter;
    }

    public String getExpDate(){
        return expDate;
    }

	public double getSizeLiter(){
		return liter;
	}

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nExpiry Date: " + getExpDate());


		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Drink){
			Drink otherDrink = (Drink) other;

			//comparisons
			if(!super.equals(otherDrink) ||
                !this.getExpDate().equals(otherDrink.getExpDate()))
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

		return hcode;
	}
}