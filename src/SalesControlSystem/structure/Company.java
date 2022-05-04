package SalesControlSystem.structure;

import java.util.ArrayList;
import java.util.List;

import SalesControlSystem.user.Administrator;

public class Company {

	private String companyName;
	private Administrator admin;
	private List<Branch> branches = new ArrayList<Branch>(); 			// list?

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
		Company adminCompany = admin.getCompany();
		// make sure admin is not owner of another company
		if (adminCompany != null && !adminCompany.equals(this))
			return false;
		admin.setCompany(this);
		this.admin = admin;
		return true;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		// insert
		return null;
	}
}