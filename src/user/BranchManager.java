package src.user;

import java.util.ArrayList;
import java.util.LinkedList;

import src.bst.BinarySearchTree;
import src.enums.Gender;
import src.product.Product;
import src.structure.Branch;

public class BranchManager extends User {

	private Branch branch;

	public BranchManager(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		this.branch.setBranchManager(this);
	}

	public Branch getBranch() {
		return branch;
	}

	public boolean setBranch(Branch branch) {
		// make sure branch has not any other branch manager
		BranchManager newBranchManager = branch.getBranchManager();
		if (newBranchManager == null) 
			branch.setBranchManager(this);
		else if (!newBranchManager.equals(this))
			return false;
		this.branch = branch;
		return true;
	}

	//public void displayBranches(Company company) {
	//	List<Branch> branches = company.getBranches();

		// insert: check if this company has this branch manager
		// insert: if true, print all branches.
	//}

	public void displayBranchEmployees() {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		System.out.println(employees);
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

	public boolean addCustomer(Customer customer) {
		BinarySearchTree<Customer> customers = branch.getCustomers();
		return customers.add(customer);
	}

	public Customer removeCustomer(Customer customer) {
		BinarySearchTree<Customer> customers = branch.getCustomers();
		return customers.delete(customer);
	}

	public boolean addBranchEmployee(BranchEmployee employee) {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		return employees.add(employee);
	}

	public BranchEmployee removeBranchEmployee(BranchEmployee employee) {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		return employees.delete(employee);		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Branch Manager" + "\nBranch: " + branch.getBranchName());
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