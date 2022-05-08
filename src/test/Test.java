package src.test;

import src.product.Clothes;
import src.incommon.*;
import src.product.*;
import src.structure.Branch;
import src.structure.Company;
import src.structure.SCSystem;
import src.user.Administrator;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;
import java.util.Random;
import java.util.ArrayList;

public class Test {
    public static void test0() {

    	System.out.println("Creating company");
		Company company1 = new Company("company1");
		System.out.println("\n" + company1 + "\n----------");
    	
    	System.out.println("Creating an administrator");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, company1);    	
    	System.out.println("\n" + admin1 + "\n----------");

    	System.out.println("Company after the administrator creation.");    	
		System.out.println("\n" + company1 + "\n----------");

    	System.out.println("Creating a branch");		
		Branch branch1 = new Branch("branch1");
		System.out.println("\n" + branch1 + "\n----------");

    	System.out.println("Creating a branch manager");
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE, branch1);
		System.out.println("\n" + manager1 + "\n-----------");		

    	System.out.println("Branch after the manager creation."); 
		System.out.println("\n" + branch1 + "-----------");

    	System.out.println("Creating an employee and customer.");		
		BranchEmployee employee1 = new BranchEmployee("elif", 30, Gender.FEMALE, branch1);
		Customer customer1 = new Customer("ayse", 25, Gender.FEMALE, branch1);
		System.out.println("\n" + employee1 + "\n-----------");
		System.out.println("\n" + customer1 + "\n-----------");

    	System.out.println("Branch Manager adds employee and customer to the branch. Branch, after these creations."); 
		System.out.println("\n" + branch1 + "\n-----------");

    	System.out.println("Admin adds branch to company. Company after adding branch."); 		
		admin1.addBranch(branch1);
		System.out.println("\n" + company1 + "\n----------");

    	System.out.println("Creating a new product."); 		
		Product product1 = new Clothes("clothes1", "brand1", "Clothes", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE);
		System.out.println("\n" + product1 + "\n-----------");

    	System.out.println("Employee adds product to the branch. Branch after adding product."); 			
		employee1.addProduct(product1);
		System.out.println("\n" + branch1 + "\n-----------");

		System.out.println("\nBranch Manager adds new employees.");
		BranchEmployee employee6 = new BranchEmployee("ahmet", 24, Gender.MALE, branch1);		
		BranchEmployee employee2 = new BranchEmployee("emre", 44, Gender.MALE, branch1);
		BranchEmployee employee3 = new BranchEmployee("irem", 31, Gender.FEMALE, branch1);
		BranchEmployee employee4 = new BranchEmployee("burak", 42, Gender.MALE, branch1);
		BranchEmployee employee5 = new BranchEmployee("ayse", 37, Gender.FEMALE, branch1);				
		
		System.out.println("\n" + branch1 + "\n-----------");


		System.out.println("\nEmployees add new products.");
		Product product2 = new Clothes("clothes2", "brand2", "Clothes", 111.8, Size.L, "matType1", "green", true, Gender.MALE);		
		Product product3 = new Drink("tea", "brand3", "Drink", 12.6 , "11/02/2023", 1.5);
		Product product4 = new Electronic("phone", "samsung", "Electronic", 1000.0, 200, false, 12.1, 21.0);
		Product product5 = new Food("crisps", "ruffle", "Food", 132.6, "13/07/2023", 0.250);
		Product product6 = new Furniture("furniture1", "ikea", "Furniture", 131.9, 142.1, 1.92, "black");
		Product product7 = new PersonalCare("shampoo", "blendax", "Personal Care", 76.1, "For Dandruff Hair", "14/06/2024");

		employee5.addProduct(product2);
		employee6.addProduct(product3);
		employee3.addProduct(product4);
		employee5.addProduct(product5);
		employee2.addProduct(product6);
		employee4.addProduct(product7);

		System.out.println("Customer display products, then adds and removes product to(from) basket and displays basket.\n");
		customer1.displayProducts();
		customer1.addProductToBasket(product1);
		customer1.addProductToBasket(product2);
		customer1.addProductToBasket(product3);
		customer1.removeProductFromBasket(product3);
		customer1.displayBasket();

		System.out.println("Customer prints order history.\n");		
		customer1.printOrderHistory();


		System.out.println("\nBranch Manager adds new customers.");
		Customer customer2 = new Customer("asli", 25, Gender.FEMALE, branch1);
		Customer customer3 = new Customer("osman", 55, Gender.MALE, branch1);
		Customer customer4 = new Customer("ali", 29, Gender.MALE, branch1);
		Customer customer5 = new Customer("avni", 25, Gender.MALE, branch1);
		Customer customer6 = new Customer("emir", 25, Gender.MALE, branch1);
		System.out.println("\n" + branch1 + "\n-----------");

		employee1.removeProduct(product2);
		System.out.println("\nEmployee removes product.");
		System.out.println("\n" + branch1 + "\n-----------");

		customer1.requestProduct(new Clothes("tshirt", "brand3", "Clothes", 113.9, Size.XL, "matType2", "gray", true, Gender.FEMALE));
		customer2.requestProduct(new PersonalCare("cream", "nivea", "Personal Care", 13.8, "For dry skins", "22/03/2025"));
		customer2.requestProduct(new Food("noodles", "brand5", "Food", 7.6, "15/02/2023", 0.4));
		System.out.println("\nSome customers requested products. One of the employees display requested products.\n");

		employee1.displayRequestedProducts();

		System.out.println("\nEmployee adds requested product according to the priority.");		
		employee1.addRequestedProducts();

		System.out.println("\nManager displays products.\n");
		manager1.displayProducts();

		System.out.println("\nManager removes an employee, and displays employees.\n");
		manager1.removeBranchEmployee(employee4);
		manager1.displayBranchEmployees();

		Branch branch2 = new Branch("branch2");
		BranchManager manager2 = new BranchManager("manager2", 30, Gender.FEMALE, branch2);
		manager2.addBranchEmployee(new BranchEmployee("osman", 24, Gender.MALE, branch2));

		Branch branch3 = new Branch("branch3");
		BranchManager manager3 = new BranchManager("manager3", 30, Gender.FEMALE, branch3);
		manager3.addBranchEmployee(new BranchEmployee("sevim", 24, Gender.FEMALE, branch3));

		System.out.println("\nAdmin adds new branches, and displays branches.\n");
		admin1.addBranch(branch2);
		admin1.addBranch(branch3);
		admin1.displayBranches();

		System.out.println("\nAdmin displays employees, and customers.");
		admin1.displayEmployees();
		admin1.displayCustomers();

		System.out.println("\nAdmin removes one of the branches, and displays.\n");
		admin1.removeBranch(branch2);
		admin1.displayBranches();

	}


	public static void performanceTest() {
		performanceTest(100);
		System.out.println("---------------------------------------------");
		performanceTest(1000);
		System.out.println("---------------------------------------------");		
		performanceTest(10000);
		System.out.println("---------------------------------------------");		
	}

	public static void performanceTest(int size) {

		System.out.println("Performance test for " + size + " dataset.\n");

		Company comp1 = new Company("comp");
		Administrator admin1 = new Administrator("adm1", 22, Gender.FEMALE, comp1);
		Branch br1 = new Branch("br1");
		BranchManager bm1 = new BranchManager("bm1", 33, Gender.MALE, br1);

		ArrayList<BranchEmployee> employees = new ArrayList<BranchEmployee>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Product> products = new ArrayList<Product>();

		Random rand = new Random();
		long startTime, endTime;

		System.out.print("average of inserting " + size +  " employee = ");
		startTime = System.nanoTime();
		// test bm's add employee
		for(int i = 0; i < size; ++i) {

			employees.add(new BranchEmployee(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER, br1));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("average of inserting " + size +  " customer = ");
		startTime = System.nanoTime();
		// test bm's add customer
		for(int i = 0; i < size; ++i) {

			customers.add(new Customer(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER, br1));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");


		for(int i = 0; i < size; ++i) {

			products.add(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", "type", (double) Integer.valueOf(rand.nextInt(99999))));
		}

		System.out.print("average of inserting " + size +  " product = ");
		startTime = System.nanoTime();
		// test employee's add product
		for(int i = 0; i < size; ++i) {

			employees.get(i).addProduct(products.get(i));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("average of requesting " + size +  " product = ");
		startTime = System.nanoTime();		
		// test request product(priority queue)
		for(int i = 0; i < size; ++i) {

			customers.get(i).requestProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", "type", (double) Integer.valueOf(rand.nextInt(99999))));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("addRequestedProducts() = ");
		startTime = System.nanoTime();			
		// test add requested products
		employees.get(0).addRequestedProducts();
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");


		// test remove for middle element

		System.out.print("remove middle employee = ");
		startTime = System.nanoTime();	
		bm1.removeBranchEmployee(employees.get(size/2));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("remove middle customer = ");
		startTime = System.nanoTime();	
		bm1.removeCustomer(customers.get(size/2));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");


		// test remove for initial element

		System.out.print("remove first employee = ");
		startTime = System.nanoTime();	
		bm1.removeBranchEmployee(employees.get(0));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("remove first customer = ");
		startTime = System.nanoTime();	
		bm1.removeCustomer(customers.get(0));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		// test remove for last element
		System.out.print("remove last employee = ");
		startTime = System.nanoTime();	
		bm1.removeBranchEmployee(employees.get(employees.size() - 1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("remove last customer = ");
		startTime = System.nanoTime();	
		bm1.removeCustomer(customers.get(customers.size() - 1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		// test insertion to the full

		System.out.print("inserting one employee to full = ");
		startTime = System.nanoTime();			
		bm1.addBranchEmployee(new BranchEmployee(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER, br1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("inserting one employee to full = ");
		startTime = System.nanoTime();			
		bm1.addCustomer(new Customer(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER, br1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("inserting one product to full = ");
		startTime = System.nanoTime();			
		employees.get(2).addProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", "type", (double) Integer.valueOf(rand.nextInt(99999))));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("requesting one product to full = ");
		startTime = System.nanoTime();			
		customers.get(2).requestProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", "type", (double) Integer.valueOf(rand.nextInt(99999))));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

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