package src.user;

import src.graph.*;
import src.structure.Branch;
import src.structure.Company;
import src.bst.*;
import src.incommon.Gender;


public class Administrator extends User {

	private Company company;

	public Administrator(String name, int age, Gender gender, String username, String password, Company company) {
		super(name, age, gender, username, password);
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
		company.getBranches().add(branch);
	}

	public boolean removeBranch(Branch branch) {
		DynamicBranchGraph branches = company.getBranches();
		if ( branches == null )
			return false;
		return branches.remove(branch);
	}

	public BranchManager setBranchManager(Branch branch, BranchManager branchManager) {
		
		DynamicBranchGraph branches = company.getBranches();

		for (Branch branchRef: branches) {
			if(branchRef.equals(branch)) {
				branchRef.setBranchManager(branchManager);
				return branchManager;
			}
		}
	
		return null;
	}

	/**
	 * Connects already existed branches according to the id's. If it is undirected, connects also connects other direction too.
	 * @param branch1 source
	 * @param branch2 dest
	 * @param weight weight of the edge
	 * @return true if it is connected
	 */ 
	public boolean connectBranches(Branch branch1, Branch branch2, double weight) {
		DynamicBranchGraph branches = company.getBranches();
		return branches.addEdge(branch1, branch2, weight);		
	}

	/**
	 * Connects already existed branches according to the id's. If it is undirected, connects also connects other direction too.
	 * @param id1 source id
	 * @param id2 dest id
	 * @param weight weight of the edge
	 * @return true if it is connected
	 */ 
	public boolean connectBranches(int id1, int id2, double weight) {
		DynamicBranchGraph branches = company.getBranches();
		return branches.addEdge(id1, id2, weight);
	}

	/**
	 * Removes edges according to the id's. If it is undirected, removes other direction too.
	 * @param id1 source id
	 * @param id2 destination id
	 * @return true if it is removed.
	 */ 
	public boolean removeConnection(int id1, int id2) {
		DynamicBranchGraph branches = company.getBranches();
		return branches.removeEdge(id1, id2);		
	}

	/**
	 * Prints the graph(Map) of the branches with weights.
	 */ 
	public void displayBranchGraph ( ) {
		DynamicBranchGraph branches = company.getBranches();
		System.out.println(branches);
	}

	public void displayBranches() {
		DynamicBranchGraph branches = company.getBranches();

		for(Branch branch: branches) {
			System.out.print("Branch Name:" + branch.getBranchName() + ", Branch Manager: ");
			if (branch.getBranchManager() != null) 
				System.out.println(branch.getBranchManager().getName());
			
			else
				System.out.println("none");
		}
	}

	public void displayBranchManagers() {
		DynamicBranchGraph branches = company.getBranches();
		
		for(Branch branch: branches) {
			System.out.println(branch.getBranchManager());
		}
	}

	public void displayCustomers() {
		DynamicBranchGraph branches = company.getBranches();
		System.out.println("\nCustomers of " + company.getCompanyName());
		for(Branch branch: branches) {
			System.out.print(branch.getBranchName() + ": \n");
			var customers = branch.getCustomers();

			int i = 0;
			for(Customer customer: customers) {
				System.out.println(++i + "- " + customer.getName());
			}
		}
	}

	public void displayEmployees() {
		DynamicBranchGraph branches = company.getBranches();
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
		sb.append("User Type: Administrator\n");
		sb.append("Company: " + company.getCompanyName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
	if(other != null && other instanceof Administrator) {
		if (this == other)
			return true;
		Administrator otherAdmin = (Administrator) other;
		
		if (((User) otherAdmin).equals((User) this) == false)
			return false;

		Company otherCompany = otherAdmin.getCompany();
		if (company != null && otherCompany != null) {
			if (!company.getCompanyName().equals(otherCompany.getCompanyName()))
				return false;
		} 
		else if (company != null || otherCompany != null)
			return false;
		return true;
	}
		return false;
	}

	@Override
	public int hashCode() {
		return 7*super.hashCode() + 3 * company.hashCode();
	}	
}