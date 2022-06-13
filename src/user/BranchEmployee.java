package src.user;

import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Iterator;

import src.incommon.Gender;
import src.product.Product;
import src.structure.Branch;

/**
 * 
 * Branch Employee class which extends User, and holds Branch reference that is employee's current branch.
 * Branch employee has the abilities which are adding, removing products etc..
 */ 
public class BranchEmployee extends User implements Comparable<BranchEmployee> {
	/**
	 * Branch where the employee works
	 */
	private Branch branch;

	/**
	 * Constructs a branch employee with given properties
	 * @param name Name of the employee
	 * @param age Age of the employee
	 * @param gender Gender of the employee
	 * @param username Username of the employee
	 * @param password Password of the employee
	 * @param branch Branch where the employee works
	 */
	public BranchEmployee(String name, int age, Gender gender, String username, String password, Branch branch) {
		super(name, age, gender, username, password);
		this.branch = branch;
		branch.getBranchManager().addBranchEmployee(this);
	}

	/**
	 * Returns Employee's current branch
	 * @return branch
	 */ 
	public Branch getBranch() {
		return branch;
	}

	/**
	 * Adds given product to the branch 
	 * @param p The product that being inserted
	 * @return True if the given product is not already contained in the branch stock
	 */
	public boolean addProduct(Product p) {
		return branch.getProducts().add(p);
	}

	/**
	 * Adds the new coming products to the branch stock 
	 * @param p The product that being inserted
	 * @param numProducts Number of new coming products
	 * @return True if the product is exist at the branch and its new stock is updated
	 */
	public boolean addProduct(Product p, int numProducts) {
		var products = branch.getProducts();
		// make sure the product is available in the branch
		if (numProducts <= 0 && products.contains(p)) {
			products.remove(p);
			// update the stock
			p.setStock(p.getStock() + numProducts);
			products.add(p);
			return true;
		}
		return false;
	}

	/**
	 * Removes/sells all the stock of given product  
	 * @param p The product that being removed
	 * @return True if the given product is contained in the branch stock
	 */
	public boolean removeProduct(Product p) {
		return branch.getProducts().remove(p);
	}

	/**
	 * Removes/sells given number of products 
	 * @param p The product 
	 * @param numProducts The number of selling product
	 * @return True if there is enough stock exist at the branch for salling
	 */
	public boolean removeProduct(Product p, int numProducts) {
		var products = branch.getProducts();
		// make sure the product is available in the branch
		if (products.contains(p)) {
			products.remove(p);
			// update the stock
			boolean succesfullSalling =  p.setStock(p.getStock() - numProducts);
			products.add(p);
			return succesfullSalling;
		}
		return false;		
	}

	/**
	 * Removes/sells all the stock of given product according to the product name  
	 * @param productName Product name
	 * @param p The product 
	 * @param numProducts The number of selling product
	 */ 
	public boolean removeProduct(String productName) {
		Product p = findProductByName(productName);
		return p == null ? false : removeProduct(p);
	}

	/**
	 * Removes/sells the given product according to the product name  
	 * @param productName Product name
	 * @param p The product 
	 * @param numProducts The number of selling product
	 */ 
	public boolean removeProduct(String productName, int numProducts) {
		Product p = findProductByName(productName);
		return p == null ? false : removeProduct(p, numProducts);
	}

	/**
	 * Finds the product according to its name
	 * @param productName Name of the product
	 * @return If the product is found, returns a reference for the product
	 *  	associated with the given product name. Otherwise returns null.
	 */
	public Product findProductByName(String productName) {
		TreeSet<Product> products = branch.getProducts();
		// iterate through products
		Iterator<Product> itr = products.iterator();
		while ( itr.hasNext() ) {
			Product p = itr.next();
			if (p.getName().equals(productName))
				return p;
		}	
		return null;
	}

	/**
	 * Adds requested products
	 * @return True if the products are inserted
	 */
	public boolean addRequestedProducts() {
		PriorityQueue<Product> requestedProducts = branch.getRequestedProducts();
		Product product = requestedProducts.peek();
		if(product == null)
			return false;
		return addProduct(product);
	}

	/**
	 * Displays all the products that branch has
	 */
	public void displayProducts() {
		System.out.println(branch.getStringProducts());
	}

	/**
	 * Prints requested products.
	 */ 
	public void displayRequestedProducts() {
		System.out.println(" Requested Products:");
		PriorityQueue<Product> products = branch.getRequestedProducts();
		
		System.out.printf(" %-20s %-15s\n", "Product Name", "Entry Price");
		for(Product product: products)	
			System.out.printf(" %-20s %f\n", product.getName(), product.getEntryPrice());
	}

	/**
	 * Returns employee's information as string.
	 * @return string
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n User Type: Branch Employee\n");
		sb.append(" Branch: " + branch.getBranchName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

	/**
	 * Checks if both employees are equal or not.
	 * @return true if both employees are equal.
	 */ 
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof BranchEmployee) {
			if (this == other)
				return true;
			BranchEmployee otherEmployee = (BranchEmployee) other;

			if (((User) otherEmployee).equals((User) this) == false)
				return false;

			Branch otherBranch = otherEmployee.getBranch();
			if (branch != null && otherBranch != null) {
				if (!branch.getBranchName().equals(otherBranch.getBranchName()))
					return false;
			} 
			else if (branch != null || otherBranch != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Hashcode of Branch Employee.
	 * @return hashcode
	 */ 
	@Override
	public int hashCode() {
		return 13*super.hashCode() + 3*branch.hashCode();
	}

	/**
	 * Compares Employees by their name.
	 * @return this object calls compareTo method, and returns its result.
	 */ 
	@Override
	public int compareTo(BranchEmployee other) {
		return getName().compareTo(other.getName());
	}	
}