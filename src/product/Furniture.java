package src.product;

public class Furniture extends Product{

    private double width;
    private double height;
    private String color;

    public Furniture(String name, String brand, String type, double entryPrice,
                        double width, double height, String color) {
        super(name, brand, type, entryPrice);
        this.width = width; 
        this.height = height;  
        this.color = color;                
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public String getColor(){
        return color;
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
        sb.append("\nColor: " + getColor());
        sb.append("\nWidth: " + getWidth());
        sb.append("\nHeigth: " + getHeight());


		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Furniture){
			Furniture otherFurniture = (Furniture) other;

			//comparisons
			if(!super.equals(otherFurniture) ||
                !this.getColor().equals(otherFurniture.getColor()) || 
                this.getWidth() != otherFurniture.getWidth() ||
                this.getHeight() != otherFurniture.getHeight()) 
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
        hcode += getColor().hashCode()* 37;
        hcode += getWidth()* 43;
		hcode += getHeight()* 53;

		return hcode;
	}
}
