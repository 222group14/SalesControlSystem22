package src.product;

public class Electronic extends Product{
    
    private int guaranteeTime;
    private boolean displayProduct;
    private double width; 
    private double height; 

    public Electronic(String name, String brand, String type, double entryPrice,
                        int guaranteeTime, boolean displayProduct
                        , double width, double height) {
        super(name, brand, type, entryPrice);
        this.guaranteeTime = guaranteeTime;
        this.displayProduct = displayProduct;
        this.width = width; 
        this.height = height; 
    }

    public int getGuaranteeTime(){
        return guaranteeTime;
    }

    public boolean isDisplayProduct(){
        return displayProduct;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    } 

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\n Guarantee Time: " + getGuaranteeTime());
        sb.append("\n Display Product: " + isDisplayProduct());
        sb.append("\n Width: " + getWidth());
        sb.append("\n Heigth: " + getHeight());


		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Electronic){
			Electronic otherElectronic = (Electronic) other;

			//comparisons
			if(!super.equals(otherElectronic) ||
                this.getGuaranteeTime() != otherElectronic.getGuaranteeTime() ||
                this.isDisplayProduct() != otherElectronic.isDisplayProduct() || 
                this.getWidth() != otherElectronic.getWidth() ||
                this.getHeight() != otherElectronic.getHeight()) 
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
        hcode += getGuaranteeTime()* 37;
        hcode += getWidth()* 43;
		hcode += getHeight()* 53;

		return hcode;
	}
    
}
