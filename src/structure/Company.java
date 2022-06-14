package src.structure;

import src.graph.*;
import src.product.Product;
import src.user.Administrator;

/**
 * 
 * A class that holds company data.
 */ 
public class Company {

	/**
	 * Name of the company
	 */ 
	private String companyName;

	/**
	 * Administrator of the company
	 */ 
	private Administrator admin;

	/**
	 * Branches of this company kept in an undirected graph
	 */ 
	private DynamicBranchGraph branches = new DynamicBranchGraph(false);

	/**
	 * Constructor which takes company name
	 * @param companyName company name
	 */ 
	public Company(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * Returns list of branches.
	 * @return List of branches.
	 */ 
	public DynamicBranchGraph getBranches() {
		return branches;
	}

	/**
	 * Returns Administrator.
	 * @return administrator.
	 */ 
	public Administrator getAdministrator() {
		return admin;
	}

	/**
	 * Sets the given admin to administrator position of thi company
	 * @param admin The admin
	 * @return False if the admin has already an another company, otherwise true
	 */
	public boolean setAdministrator(Administrator admin) {
		Company newAdminCompany = admin.getCompany();
		// make sure admin is not owner of another company
		if (newAdminCompany == null)
			admin.setCompany(this);
		else if (! newAdminCompany.equals(this))
			return false;
		this.admin = admin;
		return true;
	}

	/**
	 * Returns company name
	 * @return company name
	 */ 
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Determines all the branches that have stock for product p
	 * @param p The product
	 * @return The branches that has product p
	 */
	public Branch[] findProduct(Product p) {
		return null;
	}

	/**
	 * Returns String representation of company information
	 * @return String representation of company
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Company Name: " + companyName);
		sb.append("\n Administrator: ");
		if (admin == null)
			sb.append("None");
		else
			sb.append(admin.getName());
		sb.append("\n Branches: ");

		int i = 0;
		for (Branch branch: branches) 
			sb.append(String.format(" %d : %s\n", ++i, branch.getBranchName()));

		if (i == 0) 
			sb.append(" None\n");
	
		return sb.toString();
	}

	/**
	 * Compares two company
	 * @param other other company
	 * @return true if both companies are equal
	 */ 
	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Company) {
			if (this == other)
				return true;

			Company otherCompany = (Company) other;

			if (admin != null && otherCompany.admin != null)
				if (!admin.equals(otherCompany.admin))
					return false; 

			else if (admin != null || otherCompany.admin != null)
				return false;
			// compare branches
			if (!branches.equals(otherCompany.branches))
				return false;
		}
		return false;
	}

	/**
	 * Returns hash code of company
	 * @return hash code of company
	 */ 
	@Override 
	public int hashCode() {
		int hcode = companyName.hashCode()* 7 + 5;
		if(admin != null)
			hcode += admin.hashCode()*31 + 3;
		hcode += branches.hashCode() * 11;
		return hcode;
	}
}