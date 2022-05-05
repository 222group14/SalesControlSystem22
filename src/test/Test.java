package src.test;

import src.enums.*;
import src.product.Clothes;
import src.product.Product;
import src.structure.Branch;
import src.structure.Company;
import src.structure.SCSystem;
import src.user.Administrator;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

public class Test {
    public static void test0() {
		Company company1 = new Company("company1");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, company1);
		Branch branch1 = new Branch("branch1");
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE, branch1);
		BranchEmployee employee1 = new BranchEmployee("employee1", 30, Gender.FEMALE, branch1);
		Customer customer1 = new Customer("customer1", 25, Gender.MALE, branch1);
		Product product1 = new Clothes("clothes1", "brand1", "clothes", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE);
		System.out.println(company1);
	}

	/**
	 * Test Case: 
	 */
	public static void test1() {
		//! NOT IMPLEMENTEED YET
	}

	/**
	 * Test Case: 
	 */
	public static void test2() {
		//! NOT IMPLEMENTEED YET
	}

	/**
	 * Test Case: Tests the GUI
	 */
	public static void testN() {
		SCSystem sys = new SCSystem();
	}
}