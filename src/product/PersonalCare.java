package src.product;

public class PersonalCare extends Product{

	private String usageAim;
	private String expDate;

    public PersonalCare(String name, String brand, String type, double entryPrice, String usageAim, String expDate) {
        super(name, brand, type, entryPrice);
		this.usageAim = usageAim;
		this.expDate = expDate;
    }

	public String getUsageAim(){
		return usageAim;
	}

	public String getExpDate(){
		return expDate;
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
