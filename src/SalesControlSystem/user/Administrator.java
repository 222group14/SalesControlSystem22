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
		// make sure company has not any other admin
		Administrator admin = company.getAdministrator();
		if (admin != null && !admin.equals(this)) 
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
	}

	public boolean removeBranch(Branch branch) {
		List<Branch> branches = company.getBranches();
		return branches.remove(branch);
	}

	public BranchManager setBranchManager(Branch branch, BranchManager branchManager) {
		
		// implement
		return null;
	}

	public void displayBranches() {
		List<Branch> branches = company.getBranches();
		for(Branch branch: branches) {
			System.out.println(branch);
		}
	}

	public void displayBranchManagers() {
		List<Branch> branches = company.getBranches();
		
		for(Branch branch: branches) {
			System.out.println(branch.getBranchManager());
		}
	}

	public void displayCustomers() {
		List<Branch> branches = company.getBranches();

		for(Branch branch: branches) {
			System.out.println(branch.getBranchName() + ": \n");
			System.out.println(branch.getCustomers());
		}
	}

	public void displayEmployees() {
		List <Branch> branches = company.getBranches();
		for(Branch branch: branches) {
			System.out.println(branch.getBranchName() + ": \n");
			System.out.println(branch.getBranchEmployees());
		}		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User Type: Administrator" + "\nCompany: " + company.getCompanyName());
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