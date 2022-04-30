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

public class Driver {

	public static void main (String args[]) {
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE);
		Company company1 = new Company(admin1, "company1" );
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE);
		Branch branch1 = new Branch(manager1, "branch1");
		BranchEmployee employee1 = new BranchEmployee("employee1", 30, Gender.FEMALE);
		Customer customer1 = new Customer("customer1", 25, Gender.MALE);
		Product product1 = new Clothes("clothes1", "brand1", "clothes", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE);

		
	}

}