package SalesControlSystem.structure;

import java.util.ArrayList;
import java.util.List;

import SalesControlSystem.product.Product;
import SalesControlSystem.user.BranchEmployee;
import SalesControlSystem.user.BranchManager;
import SalesControlSystem.user.Customer;

public class Branch {

	private String branchName;
	private BranchManager manager;
	private List<BranchEmployee> employees;	//! keeps in BST								
	private List<Product> products = new ArrayList<Product>();   
	private List<Customer> customers = new ArrayList<Customer>();   	// list?
	private List<Product> requestedProducts = new ArrayList<Product>();	//! Keeps in Priorty Quee (Max-heap according to request time)

	public Branch(String branchName) {
		this.branchName = branchName;
	}

	public void setBranchManager(BranchManager manager) {
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