package src.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import src.graph.*;
import src.bst.BinarySearchTree;
import src.incommon.Location;
import src.product.Product;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

/**
 * 
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
	 * Employees are keept in BST to provide efficent search basis on employee name
	 */
	private BinarySearchTree<BranchEmployee> employees = new BinarySearchTree<BranchEmployee>(); //! use red-black tree / skiplist	

	/**
	 * List of products, according to their type
	 */ 
	private ArrayList<LinkedList<Product>> products = new ArrayList<LinkedList<Product>>(); //! 

	/** 
	 * Customers are keept in BST to provide efficent search basis on customer name
	 */
	private BinarySearchTree<Customer> customers = new BinarySearchTree<Customer>(); //! use red-black tree / skiplist

	/** 
	 * Requested products are are keept in Binary Heap(min heap).
	 */
	private PriorityQueue<Product> requestedProducts = new PriorityQueue<Product>(); //compare entryprices

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
	public ArrayList<LinkedList<Product>> getProducts() {
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
	public BinarySearchTree<Customer> getCustomers() {
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
	 * @param product product
	 * @return true if branch has that product.
	 */ 
	public boolean hasProduct(Product product) {
		for(int i = 0; i < products.size(); ++i) {
			if((products.get(i) != null && products.get(i).size() > 0)
				&& products.get(i).get(0).getClass().equals(product.getClass())) {
				return products.get(i).contains(product);
			}
		}
		return false;
	}

	/**
	 * Returns String representation of branch information
	 * @return String representation of branch
	 */ 
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