package src.product;

public class Toy extends Product{

	private int playingAge;
	private String kind;

    public Toy(String name, String brand, String type, double entryPrice,
				int playingAge, String kind) {
        super(name, brand, type, entryPrice);
		this.playingAge = playingAge;
		this.kind = kind;              
    }

	public int getPlayingAge(){
		return playingAge;
	}

	public String getKind(){
		return kind;
	}

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nPlaying Age: " + getPlayingAge());
		sb.append("\nKind: " + getKind());

		return sb.toString();
	}

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

	@Override
	public int hashCode() {
		int hcode = super.hashCode();
		hcode += getPlayingAge() * 37;
		hcode += getKind().hashCode() * 43;

		return hcode;
	}
}
