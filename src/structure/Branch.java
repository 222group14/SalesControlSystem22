package src.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import src.bst.BinarySearchTree;
import src.incommon.Location;
import src.product.Product;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

public class Branch {
	private String branchName;
	private BranchManager manager;
	/** Branch location */
	private Location loc;
	/** Employees are keept in BST to provide efficent search basis on employee name */
	private BinarySearchTree<BranchEmployee> employees = new BinarySearchTree<BranchEmployee>();							
	private ArrayList<LinkedList<Product>> products = new ArrayList<LinkedList<Product>>();
	/** Customers are keept in BST to provide efficent search basis on customer name */
	private BinarySearchTree<Customer> customers = new BinarySearchTree<Customer>();
	/** Requested products are are keept in Binary Heap to  */
	private PriorityQueue<Product> requestedProducts = new PriorityQueue<Product>(); //compare entryprices

	//! add location to constructor
	// public Branch(String branchName, Location loc) {  
	public Branch(String branchName) { 
		this.branchName = branchName;
	}

	public boolean setBranchManager(BranchManager manager) {
		Branch newManagerBranch = manager.getBranch();
		// make sure manager is not owner of another branch
		if (newManagerBranch == null)
			manager.setBranch(this);
		else if (!newManagerBranch.equals(this))
			return false;
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
		sb.append("Branch Name: " + branchName);
		sb.append("\nBranchManager: ");
		
		if(manager == null)
			sb.append("none");
		else
			sb.append(manager.getName());

		sb.append("\nEmployees:\n");

		int i = 0;
		for(BranchEmployee employee: employees)
			sb.append(++i + "- " + employee.getName() + "\n");

		sb.append("\n\nProducts:\n");

		i = 0;
		for(LinkedList<Product> innerProducts: products) {
			for(Product product : innerProducts)
				sb.append(++i + "- " + product.getName() + "\n");
		}

		sb.append("\n\nCustomers:\n");
 		i = 0;
		for(Customer customer: customers)
			sb.append(++i + "- " + customer.getName() + "\n");
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Branch) {
			if (this == other)
				return true;
			
			Branch otherBranch = (Branch) other;

			if (manager != null && otherBranch.manager != null)
				if (!manager.equals(otherBranch.manager))
					return false; 

			else if (manager != null || otherBranch.manager != null)
				return false;

			if (!otherBranch.branchName.equals(branchName) || !otherBranch.employees.equals(employees)
				|| !otherBranch.products.equals(products) || !otherBranch.customers.equals(customers)
				|| !otherBranch.requestedProducts.equals(requestedProducts)) {
				
				return false;				
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hcode = branchName.hashCode()* 7 + 5;
		if(manager != null)
			hcode += manager.hashCode()*31 + 3;
		hcode += employees.hashCode() * 11;
		hcode += products.hashCode() * 13;
		hcode += customers.hashCode() * 17;
		hcode += requestedProducts.hashCode() * 3;

		return hcode;
	}
}