package src.user;

import src.bst.BinarySearchTree;
import src.incommon.Gender;
import src.structure.Branch;
import src.structure.Company;
import src.graph.DynamicBranchGraph;

public class BranchManager extends User {

	private Branch branch;

	/**
	 * Constructs a branch manager with given properties
	 * @param name Name of the manager
	 * @param age Age of the manager
	 * @param gender Gender of the manager
	 * @param username Username of the manager
	 * @param password Password of the manager
	 * @param branch Branch where the manager works
	 */
	public BranchManager(String name, int age, Gender gender, String username, String password, Branch branch) {
		super(name, age, gender, username, password);
		this.branch = branch;
		this.branch.setBranchManager(this);
	}

	/**
	 * Returns Branch Manager's current branch.
	 * @return Branch
	 */ 
	public Branch getBranch() {
		return branch;
	}

	/**
	 * Sets the given branch to manager's current branch.
	 * If branch has another branch manager, set operation fails.
	 * @param branch manager's new branch
	 * @return true if set operation is successful.
	 */ 
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

	/**
	 * Prints branches of given company(Manager's company).
	 * @param company manager's current company.
	 */ 
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

		
		System.out.printf(" %-20s %-20s\n", "Branch Name", "Branch Manager");
		for(Branch branch: branches) {
			System.out.printf(" %-20s ", branch.getBranchName());
			if (branch.getBranchManager() != null) 
				System.out.println(branch.getBranchManager().getName());
			
			else
				System.out.println("None");
		}		
	}

	/**
	 * Prints employees of manager's current branch.
	 */ 
	public void displayBranchEmployees() {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		System.out.println("\n Employees of " + branch.getBranchName() + " : ");
		int i = 0;
		for (BranchEmployee employee: employees) {
			System.out.printf(" %d - %s", ++i, employee.getName());
		}
	}

	/**
	 * Prints products.
	 */ 
	public void displayProducts() {
		System.out.println(branch.getStringProducts());
	}

	/**
	 * Inserts customer to the branch.
	 * @param customer customer to be added.
	 * @return true if insertion is successful.
	 */ 
	public boolean addCustomer(Customer customer) {
	 	return	branch.getCustomers().add(customer);
	}

	/**
	 * Removes customer from branch.
	 * @param customer customer to be removed
	 * @return true if removal is successful.
	 */ 
	public boolean removeCustomer(Customer customer) {
		return branch.getCustomers().remove(customer);
	}

	/**
	 * Inserts an employee to the branch.
	 * @param employee employee to be inserted.
	 * @return true if insertion is successful.
	 */ 
	public boolean addBranchEmployee(BranchEmployee employee) {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		return employees.add(employee);
	}

	/**
	 * Removes an employee from branch.
	 * @param employee employee to be removed.
	 * @return true if removal is successful.
	 */ 
	public BranchEmployee removeBranchEmployee(BranchEmployee employee) {
		BinarySearchTree<BranchEmployee> employees = branch.getBranchEmployees();
		return employees.delete(employee);		
	}

	/**
	 * Returns manager's information as string.
	 * @return string
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n User Type: Branch Manager\n");
		sb.append(" Branch: " + branch.getBranchName() + "\n");
		sb.append(super.toString());
		return sb.toString();
	}

	/**
	 * Checks if both managers are equal or not.
	 * @return true if both managers are equal.
	 */ 
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

	/**
	 * Hashcode of Branch Manager.
	 * @return hashcode
	 */ 
	@Override
	public int hashCode() {

		return 11*super.hashCode() + 3*branch.hashCode();
	}		
}