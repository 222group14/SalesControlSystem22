package src.product;

public class Book extends Product{
	/**
	 * The kind of book
	 */
    private String Kind;

	/**
	 * The author of book
	 */
    private String author;

	/**
	 * Constructs the product with given parameters
	 * @param name Product name
	 * @param brand Brand 
	 * @param entryPrice Entry price
	 * @param Kind Kind of the book
	 * @param author Author of the book
	 */
    public Book(String name, String brand, double entryPrice, String Kind, String author) {
        super(name, brand, ProductType.BOOK, entryPrice);
        this.Kind = Kind;
        this.author = author;
    }

	/**
	 * Returns the kind of the book
	 * @return The kind of book
	 */
    public String getKind(){
        return Kind;
    }

	/**
	 * Returns the author of the book
	 * @return The author of the book
	 */
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
