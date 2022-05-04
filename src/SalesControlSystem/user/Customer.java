package SalesControlSystem.user;

import java.util.ArrayList;
import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.product.Product;
import SalesControlSystem.structure.Branch;

public class Customer extends User {

	private Branch branch;
    private List<Product> basket = new ArrayList<Product>();
	private List<String> orderHistory = new ArrayList<String>(); // stack?

	public Customer(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		List<Customer> customers = this.branch.getCustomers();
		customers.add(this);
	}

	public void addProduct(Product product ) {
		// insert: update order history
		basket.add(product);
	}

	public Product removeProduct(Product product) {
		basket.remove(product);		
		// insert: update order history
		
		// insert: return value
		// return deleted if remove is successful, otherwise null	
		return null;				
	}

	public boolean requestProduct(Product product) {
		
		List<Customer> customers = branch.getCustomers();
		List<Product> products = branch.getProducts();
		// insert: check if product is already exist or not.

		List<Product> requestProducts = branch.getRequestedProducts();
		requestProducts.add(product);
		return true;
		// insert rest
	}

	public void displayProducts() {

		List<Customer> customers = branch.getCustomers();
		// insert: print products
	}

	public void printOrderHistory() {
		// insert: print
	}


}