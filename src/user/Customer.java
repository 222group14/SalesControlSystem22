package src.user;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.LinkedHashMap;

import src.graph.DynamicBranchGraph;
import src.graph.DijkstraAlgorithm;
import src.incommon.Gender;
import src.product.Product;
import src.structure.Branch;
import src.structure.Company;

/**
 * 
 * Customer class which extends User class, holds Branch that means customer's current branch.
 * Customer has the abilities which are ordering, requesting etc..
 */ 
public class Customer extends User implements Comparable<Customer> {

	/** Customer's current branch */
	private Branch shoppingBranch;
	/** Stack are used to keep customer order history */
	private ArrayList<LinkedList<Product>> basket = new ArrayList<LinkedList<Product>>(); //!! change 
	/** Added items into the basket */
	private ArrayDeque<Product> lastAdded = new ArrayDeque<Product>(); 		//!! change 
	/** Removed items into the basket */
	private ArrayDeque<Product> lastRemoved = new ArrayDeque<Product>(); 	// use it as a stack

	/**
	 * Constructor to create customer.
	 * @param name customer's name
	 * @param age customer's age
	 * @param gender customer's gender
	 * @param username customer's username
	 * @param password customer's password
	 * @param branch customer's current branch
	 */ 
	public Customer(String name, int age, Gender gender, String username, String password, Branch branch) {
		super(name, age, gender, username, password);
		this.shoppingBranch = branch;
		branch.getBranchManager().addCustomer(this);
	}

	/**
	 * Returns customer's branch
	 * @return branch of the customers
	 */ 
	public Branch getShoppingBranch() {
		return shoppingBranch;
	}
	
	/**
	 * Suggests a closest branch that has product p.
	 * post: check if it returns null
	 * @param company The company that is shopping
	 * @param p The requested product
	 * @return closest branch that has product p, returns null if there aren't any branch which have that product.
	 */
	public Branch getBranchSuggestion(Company company, Product p) {		
		if(shoppingBranch.hasProduct(p))
			return shoppingBranch;

		DynamicBranchGraph branches = company.getBranches();
		int startID = branches.getID(shoppingBranch);
		// initilization for dijkstra's algorithm
		Map<Integer, Integer> pred = new LinkedHashMap<Integer, Integer>();
		Map<Integer, Double> dist = new LinkedHashMap<Integer, Double>();
		// find the shortest paths from start vertex (shopping branch) to all the branches 
		DijkstraAlgorithm.dijkstraAlgorithm(branches, startID, pred, dist);
		
		Branch closestBranch = null;
		double minDist = Double.POSITIVE_INFINITY;
		for (Map.Entry<Integer, Double> e : dist.entrySet()) {
			int currID = e.getKey(); 
			double currDist = e.getValue();
			Branch currBranch = branches.getBranch(currID); 
			// make sure the branch has desired product
			if (currDist < minDist && currBranch.hasProduct(p)) {
				minDist = currDist;
				closestBranch = currBranch;
			}
		}
		// to return more than one branches, a priorty queue can be used
		return closestBranch;
	}

	/**
	 * Adds product to customer's basket.
	 * @param product product to added to basket.
	 * @return true if it is added, false otherwise.
	 */ 
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

	/**
	 * Removes product from customer's basket.
	 * @param product product to removed
	 * @return true if it is removed successfully.
	 */ 
	public boolean removeProductFromBasket(Product product) {
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i).get(0).getClass().equals(product.getClass())) {
				lastRemoved.push(product);
				return basket.get(i).remove(product);
			}
		}
		return false; 			
	}

	/**
	 * Customer requests product.
	 * @param product product to be requested
	 */ 
	public void requestProduct(Product product) {
		PriorityQueue<Product> requestedProducts = shoppingBranch.getRequestedProducts();
		requestedProducts.offer(product);
	}

	/**
	 * Customer displays products.
	 */ 
	public void displayProducts() {
		System.out.println(shoppingBranch.getStringProducts());
	}

	/**
	 * Customer displays basket.
	 */ 
	public void displayBasket() {
		System.out.println(" Basket of customer: " + getName());
		for(int i = 0; i < basket.size(); ++i) {
			if(basket.get(i) == null || basket.get(i).size() == 0)
				continue;
			System.out.printf(" Product Type %d : %s\n", (i+1), basket.get(i).get(0).getType());
			int j = 0;
			for(Product product: basket.get(i))
				System.out.printf(" %3d : %s\n", (++j), product.getName());
		}		
	}

	/**
	 * Customer purchases products that are in the basket.
	 */
	public void purchaseBasket(){
		double price = 0;
		int j = 0;
		System.out.printf("\n");
		System.out.printf(String.format("     %-15s %-15s %-18s %s\n", "Product Type", "Brand Name", "Product Name", "Price") );
		System.out.printf("------------------------------------------------------------\n");

		for(int i = 0; i < basket.size(); ++i){
			if(basket.get(i) == null || basket.get(i).size() == 0)
				continue;
		
			for(Product product: basket.get(i)){
				System.out.printf("%2d : %-15s %-15s %-18s %.2f\n", (++j), product.getType(), product.getBrand(), product.getName(), product.getSalePrice());
				price += product.getSalePrice();
				product.decreaseStock();
			}
		}

		System.out.printf("\nTotal Amount : %.2f\n", price);
	}

	/**
	 * Customer prints order history. It displays last removed and last added product to basket.
	 */ 
	public void printOrderHistory() {
		System.out.print(" Last Removed Product from Basket: ");

		if(lastRemoved.isEmpty())
			System.out.println("None");
		else
			System.out.println(lastRemoved.peek().getName());

		System.out.print(" Last Added Product to Basket: ");
		if(lastAdded.isEmpty())
			System.out.println("None");
		else
			System.out.println(lastAdded.peek().getName());		
	}

	/**
	 * Compares two customer according to their names.
	 * @param other other customer.
	 * @return 1 if calling customer's name is greater.
	 */ 
	@Override
	public int compareTo(Customer other) {
		return getName().compareTo(other.getName());
	}

	/**
	 * Returns Customer information as a string.
	 * @return string
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n User Type: Customer\n");
		sb.append(" Branch: " + shoppingBranch.getBranchName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

	/**
	 * Checks if two customers are equal or not.
	 * @param other other object
	 * @return true if items are equal.
	 */ 
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
			return true;
		}
		return false;
	}

	/**
	 * Hashcode for customer.
	 * @return hashcode
	 */ 
	@Override
	public int hashCode() {
		return 17*super.hashCode() + 3*shoppingBranch.hashCode();
	}	
}