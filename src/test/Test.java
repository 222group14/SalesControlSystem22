package test;

import SalesControlSystem.enums.*;
import SalesControlSystem.product.Clothes;
import SalesControlSystem.product.Product;
import SalesControlSystem.structure.Branch;
import SalesControlSystem.structure.Company;
import SalesControlSystem.user.Administrator;
import SalesControlSystem.user.BranchEmployee;
import SalesControlSystem.user.BranchManager;
import SalesControlSystem.user.Customer;

public class Test {
    public static void test0() {
		Company company1 = new Company("company1");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, company1);
		Branch branch1 = new Branch("branch1");
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE, branch1);
		BranchEmployee employee1 = new BranchEmployee("employee1", 30, Gender.FEMALE, branch1);
		Customer customer1 = new Customer("customer1", 25, Gender.MALE, branch1);
		Product product1 = new Clothes("clothes1", "brand1", "clothes", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE);
    }
}