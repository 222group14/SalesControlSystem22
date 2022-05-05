package SalesControlSystem.structure;

import java.util.ArrayList;
import java.util.List;

import SalesControlSystem.user.Administrator;

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
		Company company = admin.getCompany();
		// make sure admin is not owner of another company
		if (company != null && !company.equals(this))
			return false;
		admin.setCompany(this);
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
		sb.append("\nAdministrator: " + admin + "\nBranches: \n");
		
		if (branches.size() == 0) 
			sb.append("none\n");
		for(int i = 0; i < branches.size(); ++i)
			sb.append(i+1 + ": " + branches.get(i) + "\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if(other != null && other instanceof Company) {
			Company otherCompany = (Company) other;
			if(companyName.equals(otherCompany.companyName) 
				&& admin.equals(otherCompany.admin) && (branches.size() == otherCompany.branches.size()) ) {

				for(int i = 0; i < branches.size(); ++i) 
					if(!branches.get(i).equals(otherCompany.branches.get(i)))
						return false;
				return true;
			}
			return false;
		}
		return false;
	}

	@Override 
	public int hashCode() {
		int hcode = companyName.hashCode()* 7 + 5;
		hcode += admin.hashCode()*31 + 3;
		hcode += branches.hashCode() * 11;
		return hcode;
	}
}