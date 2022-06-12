package src.product;

public class Toy extends Product{

	/**
	 * Playing age of the toy
	 */
	private int playingAge;

	/*
	 * Kind of the toy
	 */
	private String kind;

	/**
	 * Constructs a Toy object with given properties 
	 * (Number of stock is set to its default value 0)  
	 * @param name Name of the toy
	 * @param brand Name of the brand
	 * @param type Type of the product (Toy)
	 * @param entryPrice Entry price for the product
     * @param playingAge Playing age of the toy
	 * @param kind Kind of the toy 
	 */
    public Toy(String name, String brand, String type, double entryPrice,
				int playingAge, String kind) {
        super(name, brand, type, entryPrice);
		this.playingAge = playingAge;
		this.kind = kind;              
    }

	/**
	 * Constructs a Toy object with given properties 
	 * @param name Name of the toy
	 * @param brand Name of the brand
	 * @param type Type of the product (Toy)
	 * @param entryPrice Entry price for the product
     * @param playingAge Playing age of the toy
	 * @param kind Kind of the toy 
	 * @param numStock Number of stock for the product
	 */
    public Toy(String name, String brand, String type, double entryPrice,
				int playingAge, String kind, int numStock) {
        super(name, brand, type, entryPrice, numStock);
		this.playingAge = playingAge;
		this.kind = kind;              
    }

	/**
	 * Returns playing age of the toy
	 * @return Playing age of the toy
	 */
	public int getPlayingAge(){
		return playingAge;
	}

	/**
	 * Returns kind of the toy
	 * @return Kind of the toy
	 */
	public String getKind(){
		return kind;
	}

	/**
	 * Returns string of the properties of the product : 
	 * Name, brand, type, entryprice, playing age, kind
     * @return String of the properties of the product
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nPlaying Age: " + getPlayingAge());
		sb.append("\nKind: " + getKind());
		return sb.toString();
	}

	/**
	 * Checks whether two personalcare objects are equal or not.
	 * Compares : Name, brand, type, entryprice, playing age, kind
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Toy){
			Toy otherToy = (Toy) other;

			//comparisons
			if(!super.equals(otherToy) ||
				!this.getKind().equals(otherToy.getKind()) ||
			    this.getPlayingAge() != otherToy.getPlayingAge()) 
            {
                return false;
            }
			return true;
		}
		return false;
	}

	/**
	 * Creates a hash code for the object of the class Toy.
	 */
	@Override
	public int hashCode() {
		int hcode = super.hashCode();
		hcode += getPlayingAge() * 37;
		hcode += getKind().hashCode() * 43;
		return hcode;
	}
}
