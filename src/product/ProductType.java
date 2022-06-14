package src.product;

public enum ProductType {
    BOOK("Book"),
    CLOTHES("Clothes"), 
    DRINK("Drink"), 
    ELECTRONIC("Electronic"), 
    FOOD("Food"), 
    FURNITURE("Furniture"), 
    PERSONALCARE("Personal Car"), 
    TOY("Toy"),
    OTHER("Other");

    /**
     * String equivalent of the product type
     */
    private String typeName;

    /**
     * Constructor to define the type
     * @param typeName String equivalent of the product type
     */
    private ProductType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}