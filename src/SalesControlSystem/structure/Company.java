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

	public void setAdministrator(Administrator admin) {
		this.admin = admin;
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