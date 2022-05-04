package SalesControlSystem.user;

import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.structure.Branch;
import SalesControlSystem.structure.Company;

public class BranchManager extends User {

	private Branch branch;

	public BranchManager(String name, int age, Gender gender, Branch branch) {
		super(name, age, gender);
		this.branch = branch;
		this.branch.setBranchManager(this);
	}

	public void printBranches(Company company) {
		List<Branch> branches = company.getBranches();

		// insert: check if this company has this branch manager
		// insert: if true, print all branches.
	}

	public void printBranchEmployees() {
		List<BranchEmployee> employees = branch.getBranchEmployees();

		// insert: if true, print employees
	}

	public void printProducts() {
		// insert: print products	
	}

	public void addCustomer(Customer customer) {
		List<Customer> customers = branch.getCustomers();

		customers.add(customer);

		// if elements are unique, insert search operation
	}

	public Customer removeCustomer(Customer customer) {
		List<Customer> customers = branch.getCustomers();
		customers.remove(customer);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;
	}

	public void addBranchEmployee(BranchEmployee employee) {
		List<BranchEmployee> employees = branch.getBranchEmployees();
		employees.add(employee);
	}

	public BranchEmployee removeBranchEmployee(BranchEmployee employee) {
		List<BranchEmployee> employees = branch.getBranchEmployees();
		employees.remove(employee);

		// insert: return value.
		// return deleted, if remove successful, otherwise null
		return null;				
	}
}