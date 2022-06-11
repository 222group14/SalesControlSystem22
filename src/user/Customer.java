package src.user;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import src.incommon.Gender;
import src.incommon.Location;
import src.product.Product;
import src.structure.Branch;
import src.structure.Company;

public class Customer extends User implements Comparable<Customer> {
	private Branch shoppingBranch;
	/** Order location of customer */
	private Location loc;
	/** Stack are used to keep customer order history */
	private ArrayList<LinkedList<Product>> basket = new ArrayList<LinkedList<Product>>(); //!! change 
	/** Added items into the basket */
	private ArrayDeque<Product> lastAdded = new ArrayDeque<Product>(); 		//!! change 
	/** Removed items into the basket */
	private ArrayDeque<Product> lastRemoved = new ArrayDeque<Product>(); 	// use it as a stack

	public Customer(String name, int age, Gender gender, Location loc, Branch shoppingBranch) {
		super(name, age, gender);
		this.shoppingBranch = shoppingBranch;
		this.loc = loc;
		shoppingBranch.getBranchManager().addCustomer(this);
	}

	public Branch getShoppingBranch() {
		return shoppingBranch;
	}
	
	/**
	 * Finds all the branches which has stock for given product p
	 * as ordered list according to distance between customer and branch location 
	 * @param company The company that is shopping
	 * @param p The requested product
	 * @return Array of branches as ordered from the closest to the furthest distance
	 */
	public Branch[] suggestBranchs(Company company, Product p) {
		// get the branches that has product p
		var branches = company.findProduct(p);
		// sort the branches according to euclidian distance 
		// between customer location and company 
		return branches;
	}

	public boolean addProductToBasket(Product product) {
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

	public boolean removeProductFromBasket(Product product) {
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i).get(0).getClass().equals(product.getClass())) {
				lastRemoved.push(product);
				return basket.get(i).remove(product);
			}
		}
		return false; 			
	}

	public void requestProduct(Product product) {
		PriorityQueue<Product> requestedProducts = shoppingBranch.getRequestedProducts();
		requestedProducts.offer(product);
	}

	public void displayProducts() {
		ArrayList<LinkedList<Product>> products = shoppingBranch.getProducts();
		for(int i = 0; i < products.size(); ++i) {
			if(products.get(i) == null || products.get(i).size() == 0)
				continue;
			System.out.println("Product Type" + (i+1) + ": " + products.get(i).get(0).getType());
			int j = 0;
			for(Product product: products.get(i))
				System.out.println( (++j) + ": " + product.getName());
		}
	}

	public void displayBasket() {
		System.out.println("Basket of customer: " + getName());
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i) == null || basket.get(i).size() == 0)
				continue;
			System.out.println("Product Type" + " " + (i+1) + ": " + basket.get(i).get(0).getType());
			int j = 0;
			for(Product product: basket.get(i))
				System.out.println( (++j) + ": " + product.getName());
		}		
	}

	public void printOrderHistory() {
		System.out.print("Last Removed Product to Basket: ");

		if(lastRemoved.isEmpty())
			System.out.println("none");
		else
			System.out.println(lastRemoved.peek().getName());

		System.out.print("Last Added Product to Basket: ");
		if(lastAdded.isEmpty())
			System.out.println("none");
		else
			System.out.println(lastAdded.peek().getName());		
	}

	@Override
	public int compareTo(Customer other) {
		return getName().compareTo(other.getName());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Customer" + "\nBranch: " + shoppingBranch.getBranchName());
		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Customer) {
			if (this == other)
				return true;
			Customer otherCustomer = (Customer) other;

			if (((User) otherCustomer).equals((User) this) == false)
				return false;

			Branch otherBranch = otherCustomer.getShoppingBranch();
			if (shoppingBranch != null && otherBranch != null) {
				if (!shoppingBranch.getBranchName().equals(otherBranch.getBranchName()))
					return false;
			} 
			else if (shoppingBranch != null || otherBranch != null)
				return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 17*super.hashCode() + 3*shoppingBranch.hashCode();
	}	
}