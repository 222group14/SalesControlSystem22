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
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		employees.add(this);
	}

	public boolean addProduct(Product product) {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if(products.get(i) != null 
				&& products.get(i).get(0).getClass().equals(product.getClass())) {
				return products.get(i).add(product);
			}
		}
		products.add(new LinkedList<Product>());
		return products.get(products.size() - 1).add(product);
	}

	public boolean removeProduct(Product product) {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if(products.get(i) != null 
				&& products.get(i).get(0).getClass().equals(product.getClass())) {
				return products.get(i).remove(product);
			}
		}
		return false; 
	}

	public boolean addRequestedProducts() {
		PriorityQueue<Product> requestedProducts = branch.getRequestedProducts();
		Product product = requestedProducts.poll();
		if(product == null)
			return false;
		return addProduct(product);
	}

	public void displayProducts() {
		ArrayList<LinkedList<Product>> products = branch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			System.out.println("Product Type" + (i+1) + ": " + products.get(i).get(0).getClass());
			int j = 0;
			for(Product product: products.get(i))
				System.out.println( (++j) + ": " + product);
		}			
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
		// implement equals
		return true;
	}

	@Override
	public int hashCode() {
		//implement hashcode
		return 0;
	}

	@Override
	public int compareTo(BranchEmployee other) {
		return getName().compareTo(other.getName());
	}	
}