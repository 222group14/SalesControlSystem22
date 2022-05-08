package src.user;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;

import src.bst.BinarySearchTree;
import src.enums.Gender;
import src.product.Product;
import src.structure.Branch;

public class BranchEmployee extends User implements Comparable<BranchEmployee> {

	private Branch branch;

	public BranchEmployee(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		branch.getBranchManager().addBranchEmployee(this);
	}

	public Branch getBranch() {
		return branch;
	}
	public boolean addProduct(Product product) {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if((products.get(i) != null && products.get(i).size() > 0)
				&& products.get(i).get(0).getClass().equals(product.getClass())){
				return products.get(i).add(product);
			}
		}
		products.add(new LinkedList<Product>());
		return products.get(products.size() - 1).add(product);
	}

	public boolean removeProduct(Product product) {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if((products.get(i) != null && products.get(i).size() > 0)
				&& products.get(i).get(0).getClass().equals(product.getClass())) {
				return products.get(i).remove(product);
			}
		}
		return false; 
	}

	public boolean addRequestedProducts() {
		PriorityQueue<Product> requestedProducts = branch.getRequestedProducts();
		Product product = requestedProducts.peek();
		if(product == null)
			return false;
		return addProduct(product);
	}

	public void displayProducts() {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if(products.get(i) == null || products.get(i).size() == 0)
				continue;
			System.out.println("Product Type" + (i+1) + ": " + products.get(i).get(0).getType());
			int j = 0;
			for(Product product: products.get(i))
				System.out.println( (++j) + ": " + product.getName());
		}
	}

	public void displayRequestedProducts() {
		System.out.println("Requested Products:\n");
		PriorityQueue<Product> products = branch.getRequestedProducts();
		
		int i = 0;
		for(Product product: products)
			System.out.println("product name: " + product.getName() + ", entry price: " + product.getEntryPrice());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Branch Employee" + "\nBranch: " + branch.getBranchName());
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