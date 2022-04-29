package SalesControlSystem;

import java.util.ArrayList;
import java.util.List;

public class Company {

	private String companyName;
	private Administrator admin;
	private List<Branch> branches = new ArrayList<Branch>(); 			// list?

	public Company(Administrator admin, String companyName) {
		this.admin = admin;
		this.companyName = companyName;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public Administrator getAdministrator() {
		return admin;
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