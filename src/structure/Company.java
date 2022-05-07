package src.structure;

import java.util.ArrayList;
import java.util.List;

import src.user.Administrator;

public class Company {

	private String companyName;
	private Administrator admin;
	private List<Branch> branches = new ArrayList<Branch>();

	public Company(String companyName) {
		this.companyName = companyName;
	}

	public List<Branch> getBranches() {
		return branches;
	}

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

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Company Name: " + companyName);
		sb.append("\nAdministrator: ");
		if (admin == null)
			sb.append("none\n");
		else
			sb.append(admin.getName());
		sb.append("\nBranches: ");
		
		if (branches.size() == 0) 
			sb.append("none\n");
		else
			sb.append("\n");
		for(int i = 0; i < branches.size(); ++i)
			sb.append(i+1 + ": " + branches.get(i).getBranchName() + "\n");
		return sb.toString();
	}

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

	@Override 
	public int hashCode() {
		int hcode = companyName.hashCode()* 7 + 5;
		if(admin != null)
			hcode += admin.hashCode()*31 + 3;
		hcode += branches.hashCode() * 11;
		return hcode;
	}
}