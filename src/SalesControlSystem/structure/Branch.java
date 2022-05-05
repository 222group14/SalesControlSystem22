package SalesControlSystem.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

import SalesControlSystem.bst.*;
import SalesControlSystem.product.Product;
import SalesControlSystem.user.BranchEmployee;
import SalesControlSystem.user.BranchManager;
import SalesControlSystem.user.Customer;

public class Branch {

	private String branchName;
	private BranchManager manager;
	private BinarySearchTree<BranchEmployee> employees = new BinarySearchTree<BranchEmployee>();							
	private ArrayList<LinkedList<Product>> products = new ArrayList<LinkedList<Product>>();
	private BinarySearchTree<Customer> customers = new BinarySearchTree<Customer>();
	private PriorityQueue<Product> requestedProducts = new PriorityQueue<Product>(); //compare entryprices

	public Branch(String branchName) {
		this.branchName = branchName;
	}

	public boolean setBranchManager(BranchManager manager) {
		Branch branch = manager.getBranch();
		// make sure manager is not owner of another branch
		if (branch != null && !branch.equals(this))
			return false;
		manager.setBranch(this);
		this.manager = manager;
		return true;
	}

	public BinarySearchTree<BranchEmployee> getBranchEmployees() {
		return employees;
	}

	public ArrayList<LinkedList<Product>> getProducts() {
		return products;
	}

	public String getBranchName() {
		return branchName;
	}

	public BinarySearchTree<Customer> getCustomers() {
		return customers;
	}

	public BranchManager getBranchManager() {
		return manager;
	}

	public PriorityQueue<Product> getRequestedProducts() {
		return requestedProducts;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Branch Name: " + branchName + "\nBranchManager: " + manager);
		sb.append("\nEmployees:\n" + employees);
		sb.append("\nProducts:\n" + products);
		sb.append("\nCustomers:\n" + customers + "\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		// implement equals
		return true;
	}

	@Override
	public int hashCode() {
		//implement hashcode
		return 0;
	}
}