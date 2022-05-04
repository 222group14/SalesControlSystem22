package SalesControlSystem.user;

import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.product.Product;
import SalesControlSystem.structure.Branch;

public class BranchEmployee extends User {

	private Branch branch;

	public BranchEmployee(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		List<BranchEmployee> employees = branch.getBranchEmployees();
		employees.add(this);
	}

	public void addProduct(Product product) {

		List<Product> products = branch.getProducts();
		products.add(product);

		// if elements are unique, insert search operation		 
	}

	public Product removeProduct(Product product) {

		List<Product> products = branch.getProducts();
		products.remove(product);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;
	}

	public void printProducts() {
		// insert: check if this employee is part of the branch.
		List<Product> products = branch.getProducts();
		// insert: print products				
	}
}