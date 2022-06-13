package src.product;

public class Book extends Product{

    private String Kind;
    private String author;

    public Book(String name, String brand, String type, double entryPrice,
                String Kind, String author) {
        super(name, brand, type, entryPrice);
        this.Kind = Kind;
        this.author = author;
    }

    public String getKind(){
        return Kind;
    }

    public String getAuthor(){
        return author;
    }

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Category: " + this.getClass().getName());
		sb.append(super.toString());
        sb.append("\n Book Kind: " + getKind());
        sb.append("\n Author: " + getAuthor());

		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Book){
			Book otherBook = (Book) other;

			//comparisons
			if(!super.equals(otherBook) ||
                !this.getKind().equals(otherBook.getKind()) ||
                !this.getAuthor().equals(otherBook.getAuthor()))
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
        hcode += getKind().hashCode()* 37;
        hcode += getAuthor().hashCode()* 43;

		return hcode;
	}
}
