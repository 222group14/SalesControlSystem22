package SalesControlSystem.user;

import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.structure.Branch;
import SalesControlSystem.structure.Company;

public class Administrator extends User {

	public Administrator(String name, int age, Gender gender) {
		super(name, age, gender);
	}

	public void addBranch(Company company, Branch branch) {
		List<Branch> branches = company.getBranches();

		if (this.equals(company.getAdministrator()))
			branches.add(branch);

		// if elements are unique, insert search operation
	}

	public Branch removeBranch(Company company, Branch branch) {
		List<Branch> branches = company.getBranches();

		if (this.equals(company.getAdministrator()))		
			branches.remove(branch);

		// insert: return value.
		// return deleted, if remove successful, otherwise null	
		return null;
	}

	public BranchManager setBranchManager(Company company, Branch branch, BranchManager branchManager) {
		
		// insert: search and set operation
		// insert: return value
		// return deleted, if remove successful, otherwise null		
		return null;
	}

	public void printBranches(Company company) {
		List<Branch> branches = company.getBranches();
		
		// insert: check admin
		// insert: search and print branches
	}

	public void printBranchManagers(Company company) {
		List<Branch> branches = company.getBranches();
		// insert: check admin
		// insert: search and print branchManagers for each branch
	}

	public void printCustomers(Company company) {
		List<Branch> branches = company.getBranches();
		// insert: check admin
		// insert: get all customers for each branch 
		// List<Customer> customers = company.getCustomers();

		// insert: search and print customers
	}

	public void printEmployees(Company company) {
		List <Branch> branches = company.getBranches();
		// insert: check admin
		// insert: search and print employees for each brand
	}

	public String toString() {

		// insert
		return null;
	}
}