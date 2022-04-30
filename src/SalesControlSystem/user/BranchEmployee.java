package SalesControlSystem.user;

import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.product.Product;
import SalesControlSystem.structure.Branch;

public class BranchEmployee extends User {

	public BranchEmployee(String name, int age, Gender gender) {
		super(name, age, gender);
	}

	public void addProduct(Branch branch, Product product ) {
		// insert: check if this employee is part of the branch.

		List<Product> products = branch.getProducts();
		products.add(product);

		// if elements are unique, insert search operation		 
	}

	public Product removeProduct(Branch branch, Product product) {
		// insert: check if this employee is part of the branch.

		List<Product> products = branch.getProducts();
		products.remove(product);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;
	}

	public void printProducts(Branch branch) {
		// insert: check if this employee is part of the branch.
		List<Product> products = branch.getProducts();
		// insert: print products				
	}
}