package src.product;

public class Toy extends Product{

    public Toy(String name, String brand, String type, double entryPrice) {
        super(name, brand, type, entryPrice);              
    }
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nCategory: " + this.getClass().getName());
		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Toy){
			Toy otherToy = (Toy) other;

			//comparisons
			if(!super.equals(otherToy)) 
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

		return hcode;
	}
}
