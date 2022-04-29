package SalesControlSystem;

import java.util.ArrayList;
import java.util.List;
import Enums.Gender;

public class Customer extends User {

    private List<Product> basket = new ArrayList<Product>();
	private List<String> orderHistory = new ArrayList<String>(); // stack?

	public Customer(String name, int age, Gender gender) {
		super(name, age, gender);
	}

	public void addProduct(Branch branch, Product product ) {
		// insert: check
		// insert: update order history
		basket.add(product);
	}

	public Product removeProduct(Branch branch, Product product) {
		// insert: check
		basket.remove(product);
		
		// insert: update order history

		// insert: return value
		// return deleted if remove is successful, otherwise null	
		return null;				
	}

	public boolean requestProduct(Branch branch, Product product) {
		
		List<Customer> customers = branch.getCustomers();
		// insert: check if customer is part of the branch
		
		List<Product> products = branch.getProducts();
		// insert: check if product is already exist or not.

		List<Product> requestProducts = branch.getRequestedProducts();
		requestProducts.add(product);
		return true;
		// insert rest
	}

	public void displayProducts(Branch branch) {

		List<Customer> customers = branch.getCustomers();
		// insert: check if customer is part of the branch

		// insert: print products
	}

	public void printOrderHistory() {
		// insert: print
	}


}