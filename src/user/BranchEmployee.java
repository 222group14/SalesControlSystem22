package src.user;

import java.util.PriorityQueue;

import src.incommon.Gender;
import src.product.Product;
import src.structure.Branch;

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

	public Branch getBranch() {
		return branch;
	}

	/**
	 * Adds given product to the branch stock 
	 * @param p The product that being inserted
	 * @return True if the given product is not already contained in the branch stock
	 */
	public boolean addProduct(Product p) {
		return branch.getProducts().add(p);
	}

	/**
	 * Removes given product from the branch stock
	 * @param p The product that being removed
	 * @return True if the given product is contained in the branch stock
	 */
	public boolean removeProduct(Product p) {
		return branch.getProducts().remove(p);
	}

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

	public void displayRequestedProducts() {
		System.out.println("Requested Products:\n");
		PriorityQueue<Product> products = branch.getRequestedProducts();
		
		for(Product product: products)
			System.out.println("product name: " + product.getName() + ", entry price: " + product.getEntryPrice());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Branch Employee\n");
		sb.append("Branch: " + branch.getBranchName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

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

	@Override
	public int hashCode() {
		return 13*super.hashCode() + 3*branch.hashCode();
	}

	@Override
	public int compareTo(BranchEmployee other) {
		return getName().compareTo(other.getName());
	}	
}