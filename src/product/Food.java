package src.product;

public class Food extends Product {

    private String expDate;

    public Food(String name, String brand, String type, double entryPrice, 
                 String expDate) {
        super(name, brand, type, entryPrice);
        this.expDate = expDate;
    }

    public String getExpDate(){
        return expDate;
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
		if(other != null && other instanceof Food){
			Food otherFood = (Food) other;

			//comparisons
			if(!super.equals(otherFood) ||
                !this.getExpDate().equals(otherFood.getExpDate()))
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