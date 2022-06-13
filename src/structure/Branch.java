package src.structure;

import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import src.bst.BinarySearchTree;
import src.bst.RedBlackTree;
import src.product.Product;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

/**
 * A class that holds branch information.
 */ 
public class Branch {
	/**
	 * Name of branch
	 */ 
	private String branchName;

	/**
	 * Name of branch manager
	 */  
	private BranchManager manager;
	
	/** 
	 * Employees are keept in Red Black Tree to provide efficent search basis on employee name
	 */
	private BinarySearchTree<BranchEmployee> employees = new RedBlackTree<BranchEmployee>(); 	

	/**
	 * All Products that branch has
	 */ 
	private TreeSet<Product> products = new TreeSet<>(); 

	/** 
	 * Customers are keept in a skiplist according to their name. 
	 * Skiplist provides concurrent access which can be useful for multiple access during sales.
	 */
	private ConcurrentSkipListSet<Customer> customers = new ConcurrentSkipListSet<Customer>(); 

	/** 
	 * Requested products are are keept in a priority queue according to entry price of the products.
	 */
	private PriorityQueue<Product> requestedProducts = new PriorityQueue<Product>(); 

	/**
	 * Constructs the branch with its name
	 * @param branchName Name of the branch
	 */
	public Branch(String branchName) {  
		this.branchName = branchName;
	}

	/**
	 * Sets the branch's manager, given manager should not be the owner of another branch
	 * @param manager branch manager
	 * @return true if set operation is successful
	 */ 
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

	/**
	 * Getter for branch employees
	 * @return branch employees as binarySearchTree
	 */ 
	public BinarySearchTree<BranchEmployee> getBranchEmployees() {
		return employees;
	}

	/**
	 * Getter for products
	 * @return products as ArrayList of LinkedList
	 */ 
	public TreeSet<Product> getProducts() {
		return products;
	}

	/**
	 * Getter for branch name
	 * @return branch name
	 */ 
	public String getBranchName() {
		return branchName;
	}

	/**
	 * Getter for customers.
	 * @return customers as BinarySearchTree
	 */ 
	public ConcurrentSkipListSet<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Getter for branch manager
	 * @return branch manager
	 */  
	public BranchManager getBranchManager() {
		return manager;
	}

	/**
	 * Getter for requested products
	 * @return requested products as PriorityQueue
	 */  
	public PriorityQueue<Product> getRequestedProducts() {
		return requestedProducts;
	}
	
	/**
	 * Checks if branch has that product.
	 * @param p The product being searched
	 * @return True if branch has that product.
	 */ 
	public boolean hasProduct(Product p) {
		return products.contains(p); 
	}

	/**
	 * Returns the string representation of all the employees in a table
	 * @return The string representation of all the employees in a table
	 */
	public String getStringEmployees() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Employees:\n");
		sb.append(String.format("  %-20s %-10s %s\n", "Name", "Gender", "Age") );
		sb.append("------------------------------------------------------------\n");
		for (var e : employees) 
			sb.append(String.format("%  -20s %-10s %d\n", 
				e.getName(), e.getGender(), e.getAge()));
		return sb.toString();
	}

	/**
	 * Returns the string representation of all the customers in a table
	 * @return The string representation of all the customers in a table
	 */
	public String getStringCustomers() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n------------------------- Customers ------------------------\n");
		sb.append(String.format("  %-20s %10s %15s\n", "Name", "Gender", "Age") );
		sb.append("------------------------------------------------------------\n");
		for (var c : customers) 
			sb.append(String.format("  %-20s %10s %12d\n", 
				c.getName(), c.getGender(), c.getAge()));
		return sb.toString();
	}

	/**
	 * Returns the string representation of all the products in a table
	 * @return The string representation of all the products in a table
	 */
	public String getStringProducts() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n------------------------- Products -------------------------\n");
		sb.append(String.format("  %-15s %-15s %-18s %s\n", "Product Type", "Brand Name", "Product Name", "Stock") );
		sb.append("------------------------------------------------------------\n");
		for (var p : products) 
			sb.append(String.format("  %-15s %-15s %-19s %d\n", 
				p.getType(), p.getBrand(), p.getName(), p.getStock()));
		return sb.toString();
	}

	/**
	 * Returns String representation of branch information
	 * @return String representation of branch
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n------------------------------------------------------------\n");
		sb.append(" Branch Name: " + branchName);
		sb.append("\n BranchManager: " + (manager == null ? "None" : manager.getName()));
		sb.append(getStringEmployees());
		sb.append(getStringCustomers());
		sb.append(getStringProducts());
		sb.append("------------------------------------------------------------\n");
			return sb.toString();
	}

	/**
	 * Compares two branch
	 * @param other other branch
	 * @return true if two branch is equal
	 */ 
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

	/**
	 * Returns hash code value
	 * @return hash code value
	 */ 
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