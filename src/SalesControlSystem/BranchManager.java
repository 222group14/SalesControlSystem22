package SalesControlSystem;

import java.util.List;
import Enums.Gender;

public class BranchManager extends User {

	public BranchManager(String name, int age, Gender gender) {
		super(name, age, gender);
	}

	public void printBranches(Company company) {
		List<Branch> branches = company.getBranches();

		// insert: check if this company has this branch manager
		// insert: if true, print all branches.
	}

	public void printBranchEmployees(Branch branch) {
		List<BranchEmployee> employees = branch.getBranchEmployees();

		// insert: check branch manager -> this.equals(branch.getBranchManager())
		// insert: if true, print employees
	}

	public void printProducts(Branch branch) {
			// if ( this.equals(branch.getBranchManager()) )
			// insert: if true, print products	
	}

	public void addCustomer(Branch branch, Customer customer) {
		List<Customer> customers = branch.getCustomers();

		if ( this.equals(branch.getBranchManager()) )
			customers.add(customer);

		// if elements are unique, insert search operation
	}

	public Customer removeCustomer(Branch branch, Customer customer) {
		List<Customer> customers = branch.getCustomers();

		if ( this.equals(branch.getBranchManager()) )		
			customers.remove(customer);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;
	}

	public void addBranchEmployee(Branch branch, BranchEmployee employee) {
		List<BranchEmployee> employees = branch.getBranchEmployees();
		if(this.equals(branch.getBranchManager()))
			employees.add(employee);
	}

	public BranchEmployee removeBranchEmployee(Branch branch, BranchEmployee employee) {
		List<BranchEmployee> employees = branch.getBranchEmployees();
		if(this.equals(branch.getBranchManager()))
			employees.remove(employee);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;				
	}
}