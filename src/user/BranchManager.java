package src.user;

import src.bst.BinarySearchTree;
import src.incommon.Gender;
import src.structure.Branch;
import src.structure.Company;
import src.graph.DynamicBranchGraph;

public class BranchManager extends User {

	private Branch branch;

	public BranchManager(String name, int age, Gender gender, String username, String password, Branch branch) {
		super(name, age, gender, username, password);
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

	public void displayBranches(Company company) {
		DynamicBranchGraph branches = company.getBranches();

		boolean flag = false;
		for (Branch branch: branches) {
			if(branch.getBranchManager().equals(this)) {
				flag = true;
				break;
			}
		}

		if (!flag)
			return;

		for(Branch branch: branches) {
			System.out.print("Branch Name:" + branch.getBranchName() + ", Branch Manager: ");
			if (branch.getBranchManager() != null) 
				System.out.println(branch.getBranchManager().getName());
			
			else
				System.out.println("none");
		}		
	}

	public void displayBranchEmployees() {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		System.out.println("Employees of " + branch.getBranchName());
		int i = 0;
		for (BranchEmployee employee: employees) {
			System.out.println(++i + "- " + employee.getName());
		}
	}

	public void displayProducts() {
		System.out.println(branch.getStringProducts());
	}

	public boolean addCustomer(Customer customer) {
	 	return	branch.getCustomers().add(customer);
	}

	public boolean removeCustomer(Customer customer) {
		return branch.getCustomers().remove(customer);
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
		sb.append(" User Type: Branch Manager\n");
		sb.append(" Branch: " + branch.getBranchName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof BranchManager) {
			if (this == other)
				return true;
			BranchManager otherManager = (BranchManager) other;

			if(!super.equals(otherManager))
				return false;

			Branch otherBranch = otherManager.getBranch();
			if (branch != null && otherBranch != null) {
				if (!branch.getBranchName().equals(otherBranch.getBranchName()))
					return false;
			} 
			else if (branch != null || otherBranch != null)
				return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {

		return 11*super.hashCode() + 3*branch.hashCode();
	}		
}