package SalesControlSystem;

import java.util.ArrayList;
import java.util.List;

public class Branch {

	private String branchName;
	private BranchManager manager;
	private List<BranchEmployee> employees = new ArrayList<BranchEmployee>(); 									
	private List<Product> products = new ArrayList<Product>();   
	private List<Customer> customers = new ArrayList<Customer>();   	// list?
	private List<Product> requestedProducts = new ArrayList<Product>();	// list?

	public Branch(BranchManager manager, String branchName) {
		this.branchName = branchName;
		this.manager = manager;
	}

	public List<BranchEmployee> getBranchEmployees() {
		return employees;
	}

	public List<Product> getProducts() {
		return products;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public BranchManager getBranchManager() {
		return manager;
	}

	public List<Product> getRequestedProducts() {
		return requestedProducts;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// insert
		return sb.toString();
	}
}