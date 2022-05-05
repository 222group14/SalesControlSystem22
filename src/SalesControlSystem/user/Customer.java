package SalesControlSystem.user;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

import SalesControlSystem.bst.*;
import SalesControlSystem.enums.Gender;
import SalesControlSystem.product.Product;
import SalesControlSystem.structure.Branch;
import SalesControlSystem.product.Product;

public class Customer extends User implements Comparable<Customer> {

	private Branch branch;
    private ArrayList<LinkedList<Product>> basket = new ArrayList<LinkedList<Product>>();
	private ArrayDeque<Product> lastAdded = new ArrayDeque<Product>(); 		// use it as a stack
	private ArrayDeque<Product> lastRemoved = new ArrayDeque<Product>(); 	// use it as a stack

	public Customer(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		BinarySearchTree<Customer> customers = this.branch.getCustomers();
		customers.add(this);
	}

	public boolean addProduct(Product product) {
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i).get(0).getClass().equals(product.getClass())) {
				lastAdded.push(product);

				// insert from last removed
				if(lastRemoved.peek() != null && lastRemoved.peek().equals(product))
					return basket.get(i).add(lastRemoved.pop());
				return basket.get(i).add(product);
			}
		}
		basket.add(new LinkedList<Product>());
		return basket.get(basket.size() - 1).add(product);		
	}

	public boolean removeProduct(Product product) {
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i).get(0).getClass().equals(product.getClass())) {
				lastRemoved.push(product);
				return basket.get(i).remove(product);
			}
		}
		return false; 			
	}

	public void requestProduct(Product product) {
		PriorityQueue<Product> requestedProducts = branch.getRequestedProducts();
		requestedProducts.offer(product);
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

	public void printOrderHistory() {
		System.out.print("Last Removed Product: ");
		if(lastRemoved.isEmpty())
			System.out.println("none");
		else
			System.out.println(lastRemoved.peek());

		System.out.print("Last Added Product: ");
		if(lastAdded.isEmpty())
			System.out.println("none");
		else
			System.out.println(lastAdded.peek());		
	}

	@Override
	public int compareTo(Customer other) {
		return getName().compareTo(other.getName());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Customer" + "\nBranch: " + branch.getBranchName());
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
}