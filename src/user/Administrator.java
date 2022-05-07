package src.user;

import java.util.List;

import src.enums.Gender;
import src.structure.Branch;
import src.structure.Company;
import src.bst.*;


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
		Administrator newCompanyAdmin = company.getAdministrator();
		if (newCompanyAdmin == null)
			company.setAdministrator(this);
		else if (!newCompanyAdmin.equals(this)) 
			return false;
		// set admin to administrator position of the given company 	
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
			System.out.print("Branch Name:" + branch.getBranchName() + ", Branch Manager: ");
			if (branch.getBranchManager() != null) 
				System.out.println(branch.getBranchManager().getName());
			
			else
				System.out.println("none");
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
		System.out.println("\nCustomers of " + company.getCompanyName());
		for(Branch branch: branches) {
			System.out.print(branch.getBranchName() + ": \n");
			BinarySearchTree<Customer> customers = branch.getCustomers();

			int i = 0;
			for(Customer customer: customers) {
				System.out.println(++i + "- " + customer.getName());
			}
		}
	}

	public void displayEmployees() {
		List<Branch> branches = company.getBranches();
		System.out.println("\nEmployees of " + company.getCompanyName());
		for(Branch branch: branches) {
			System.out.print(branch.getBranchName() + ": \n");
			BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();

			int i = 0;
			for(BranchEmployee employee: employees) {
				System.out.println(++i + "- " + employee.getName());
			}
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
	if(other != null && other instanceof Administrator) {
		if (this == other)
			return true;
		Administrator otherAdmin = (Administrator) other;

		if (!otherAdmin.getName().equals(this.getName()) || otherAdmin.getAge() != this.getAge()
			|| !otherAdmin.getGender().equals(this.getGender())) {

			return false;				
		}

		Company otherCompany = otherAdmin.getCompany();
		if (company != null && otherCompany != null) {
			if (!company.getCompanyName().equals(otherCompany.getCompanyName()))
				return false;
		} 
		else if (company != null || otherCompany != null)
			return false;
	}
		return false;
	}

	@Override
	public int hashCode() {
		return 7*super.hashCode() + 3 * company.hashCode();
	}	
}