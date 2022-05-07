package src.product;

public class PersonalCare extends Product{

    public PersonalCare(String name, String brand, String type, double entryPrice) {
        super(name, brand, type, entryPrice);
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());

		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof PersonalCare){
			PersonalCare otherPersonalCare = (PersonalCare) other;

			//comparisons
			if(!super.equals(otherPersonalCare)) 
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
