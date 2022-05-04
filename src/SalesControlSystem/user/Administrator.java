package SalesControlSystem.user;

import java.util.List;

import SalesControlSystem.enums.Gender;
import SalesControlSystem.structure.Branch;
import SalesControlSystem.structure.Company;

public class Administrator extends User {

	private Company company;

	public Administrator(String name, int age, Gender gender, Company company) {
		super(name, age, gender);
		this.company = company;
		this.company.setAdministrator(this);
	}

	/**
	 * Sets this admin to administrator position of the given company
	 * @param company The company
	 * @return Returns false if the company has already an another administrator,
	 * 			otherwise true.   
	 */
	public boolean setCompany(Company company) {
		// make sure company has not any admin
		Administrator companyOwner = company.getAdministrator();
		if (companyOwner != null && !companyOwner.equals(this)) 
			return false;
		// set admin to administrator position of the given company 	
		company.setAdministrator(this);
		this.company = company;
		return true;
	}

	public Company getCompany() {
		return company;
	}

	public void addBranch(Branch branch) {
		List<Branch> branches = company.getBranches();
		branches.add(branch);

		// if elements are unique, insert search operation
	}

	public Branch removeBranch(Branch branch) {
		List<Branch> branches = company.getBranches();
		branches.remove(branch);

		// insert: return value.
		// return deleted, if remove successful, otherwise null	
		return null;
	}

	public BranchManager setBranchManager(Branch branch, BranchManager branchManager) {
		
		// insert: search and set operation
		// insert: return value
		// return deleted, if remove successful, otherwise null		
		return null;
	}

	public void printBranches() {
		List<Branch> branches = company.getBranches();
		
		// insert: check admin
		// insert: search and print branches
	}

	public void printBranchManagers() {
		List<Branch> branches = company.getBranches();
		// insert: check admin
		// insert: search and print branchManagers for each branch
	}

	public void printCustomers() {
		List<Branch> branches = company.getBranches();
		// insert: check admin
		// insert: get all customers for each branch 
		// List<Customer> customers = company.getCustomers();

		// insert: search and print customers
	}

	public void printEmployees() {
		List <Branch> branches = company.getBranches();
		// insert: search and print employees for each brand
	}

	public String toString() {

		// insert
		return null;
	}
}