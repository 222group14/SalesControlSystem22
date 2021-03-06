package src.test;

import src.product.*;
import src.structure.*;
import src.user.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void useOfSorting() {
		// A branch that contains products (not related to algorithm)
		Branch branch = new Branch("branchName");
		// get array of specific type of products  
		Drink[] drinks = (Drink[]) branch.getProducts(ProductType.DRINK).toArray();
		// sort the products by the comparator
		Arrays.sort(drinks, new Drink.compareByExpDate());
	}

    /**
	 * Tests all the public methods of classes
	 */
	public static void test0() {

    	System.out.println("Creating company");
		Company company1 = new Company("company1");
		System.out.println("\n" + company1 + "\n----------");
    	
    	System.out.println("Creating an administrator");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company1);    	
    	System.out.println("\n" + admin1 + "\n----------");

    	System.out.println("Company after the administrator creation.");    	
		System.out.println("\n" + company1 + "\n----------");

    	System.out.println("Creating a branch");		
		Branch branch1 = new Branch("branch1");
		System.out.println("\n" + branch1 + "\n----------");

    	System.out.println("Creating a branch manager");
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE, "mngr_1" , "m4n4g3r1" , branch1);
		System.out.println("\n" + manager1 + "\n-----------");		

    	System.out.println("Branch after the manager creation."); 
		System.out.println("\n" + branch1 + "-----------");

    	System.out.println("Creating an employee and customer.");		
		BranchEmployee employee1 = new BranchEmployee("elif", 30, Gender.FEMALE, "Elifabla" , "Elif2001." , branch1);
		Customer customer1 = new Customer("ayse", 25, Gender.FEMALE, "a.ayse58" , "58A_ysesivas" ,branch1);
		System.out.println("\n" + employee1 + "\n-----------");
		System.out.println("\n" + customer1 + "\n-----------");

    	System.out.println("Branch Manager adds employee and customer to the branch. Branch, after these creations."); 
		System.out.println("\n" + branch1 + "\n-----------");

    	System.out.println("Admin adds branch to company. Company after adding branch."); 		
		admin1.addBranch(branch1);
		System.out.println("\n" + company1 + "\n----------");

    	System.out.println("Creating a new product."); 		
		Product product1 = new Clothes("clothes1", "brand1", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE);
		System.out.println("\n" + product1 + "\n-----------");

    	System.out.println("Employee adds product to the branch. Branch after adding product."); 			
		employee1.addProduct(product1);
		System.out.println("\n" + branch1 + "\n-----------");

		System.out.println("\nBranch Manager adds new employees.");
		BranchEmployee employee6 = new BranchEmployee("ahmet", 24, Gender.MALE, "Ahmetinho" , "ronalda07." , branch1);		
		BranchEmployee employee2 = new BranchEmployee("emre", 44, Gender.MALE, "Mr.shutterspeed", "d21esa2." , branch1);
		BranchEmployee employee3 = new BranchEmployee("irem", 31, Gender.FEMALE, "Irem_y", "irem1905." , branch1);
		BranchEmployee employee4 = new BranchEmployee("burak", 42, Gender.MALE, "burraaook", "Kocausta.41" , branch1);
		BranchEmployee employee5 = new BranchEmployee("ayse", 37, Gender.FEMALE, "ayseee", "A_yse1235." , branch1);				
		
		System.out.println("\n" + branch1 + "\n-----------");


		System.out.println("\nEmployees add new products.");
		Product product2 = new Clothes("clothes2", "brand2", 111.8, Size.L, "matType1", "green", true, Gender.MALE);		
		Product product3 = new Drink("tea", "brand3", 12.6 , "11/02/2023", 1.5);
		Product product4 = new Electronic("phone", "samsung", 1000.0, 200, false, 12.1, 21.0);
		Product product5 = new Food("crisps", "ruffle", 132.6, "13/07/2023", 0.250, 0);
		Product product6 = new Furniture("furniture1", "ikea", 131.9, 142.1, 1.92, "black");
		Product product7 = new PersonalCare("shampoo", "blendax", 76.1, "For Dandruff Hair", "14/06/2024");

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
		Customer customer2 = new Customer("asli", 25, Gender.FEMALE, "asliii", "A_sli3106." , branch1);
		// Customer customer3 = new Customer("osman", 55, Gender.MALE, "0smanK.", "O.smancik2001p" , branch1);
		// Customer customer4 = new Customer("ali", 29, Gender.MALE, "mali53", "rizeliA_li53" , branch1);
		// Customer customer5 = new Customer("avni", 25, Gender.MALE, "avni.celik", "avn_1234" , branch1);
		// Customer customer6 = new Customer("emir", 25, Gender.MALE, "emir.efe34", "E_efe1903" , branch1);
		System.out.println("\n" + branch1 + "\n-----------");

		employee1.removeProduct(product2);
		System.out.println("\nEmployee removes product.");
		System.out.println("\n" + branch1 + "\n-----------");

		customer1.requestProduct(new Clothes("tshirt", "brand3", 113.9, Size.XL, "matType2", "gray", true, Gender.FEMALE));
		customer2.requestProduct(new PersonalCare("cream", "nivea", 13.8, "For dry skins", "22/03/2025"));
		customer2.requestProduct(new Food("noodles", "brand5", 7.6, "15/02/2023", 0.4, 0));
		System.out.println("\nSome customers requested products. One of the employees display requested products.\n");

		employee1.displayRequestedProducts();

		System.out.println("\nEmployee adds requested product according to the priority.");		
		employee1.addRequestedProducts(10);

		System.out.println("\nManager displays products.\n");
		manager1.displayProducts();

		System.out.println("\nManager removes an employee, and displays employees.\n");
		manager1.removeBranchEmployee(employee4);
		manager1.displayBranchEmployees();

		Branch branch2 = new Branch("branch2");
		BranchManager manager2 = new BranchManager("manager2", 30, Gender.FEMALE, "mngr_2" , "m4n4g3r2." ,branch2);
		manager2.addBranchEmployee(new BranchEmployee("osman", 24, Gender.MALE, "Osmanpzr" , " Osman3002." ,branch2));

		Branch branch3 = new Branch("branch3");
		BranchManager manager3 = new BranchManager("manager3", 30, Gender.FEMALE, "mngr_3" , "m4n4g3r3" , branch3);
		manager3.addBranchEmployee(new BranchEmployee("sevim", 24, Gender.FEMALE, " Sevim7 " ,  "Sevim2001." , branch3));

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

	/**
	 * Testing Graph implementation.
	 * Test Case: inserting 15 branch to one company, and customer suggests item which is not in the that customer's branch
	 */
	public static void test1() {
		Company company1 = new Company("company1");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company1); 
		ArrayList<Branch> branches = new ArrayList<Branch>();
		ArrayList<BranchManager> managers = new ArrayList<BranchManager>();
		ArrayList<BranchEmployee> employees = new ArrayList<BranchEmployee>();

		for ( int i = 0; i < 15; ++i ) {
			branches.add(new Branch(String.format("branch%d", i+1)));
			managers.add(new BranchManager(String.format("manager%d", i+1), i+30, Gender.MALE,
							 String.format("mngr_%d", i+1) , String.format("m4n4g3r%d", i+1), branches.get(i)));
			employees.add(new BranchEmployee(String.format("employee%d", i+1), 30, Gender.FEMALE,
							 String.format("emp_nick%d", i+1), String.format("emp_pass%d", i+1), branches.get(i)));
			admin1.addBranch(branches.get(i));
		}
		Customer customer1 = new Customer("ayse", 25, Gender.FEMALE, "a.ayse58" , "58A_ysesivas", branches.get(0));
		
		employees.get(0).addProduct(new Clothes("clothes1", "brand1", 132.6, Size.XL,
									 "matType1", "blue", false, Gender.FEMALE));
		employees.get(0).addProduct(new PersonalCare("cream", "nivea", 13.8, "For dry skins", "22/03/2025"));
		employees.get(0).addProduct(new Furniture("furniture1", "ikea", 131.9, 142.1, 1.92, "black"));

		for(int i = 5; i < 15; ++i) {
			employees.get(i).addProduct(new Electronic("phone", "samsung", 1000.0, 200, false, 12.1, 21.0));			
		}


		admin1.displayBranches();

		System.out.println("\nBefore inserting edges:\n");
		admin1.displayBranchGraph();

		for ( int i = 2; i < 16; ++i ) {
			admin1.connectBranches(1, i, i*20 + 30.0);
		}

		admin1.connectBranches(2, 3, 5.0);
		admin1.connectBranches(2, 5, 10.0);
		admin1.connectBranches(3, 6, 13.0);
		admin1.connectBranches(5, 6, 5.0);
		admin1.connectBranches(6, 9, 25.0);
		admin1.connectBranches(7, 3, 12.0);
		admin1.connectBranches(15, 2, 80.0);
		admin1.connectBranches(4, 11, 28.0);
		admin1.connectBranches(11, 12, 6.0);
		admin1.connectBranches(11, 14, 9.0);														
		admin1.connectBranches(15, 14, 7.0);
		admin1.connectBranches(12, 15, 16.0);
		admin1.connectBranches(8, 13, 20.0);
		admin1.connectBranches(13, 9, 14.0);

		System.out.println("\nAfter inserting edges:\n");
		admin1.displayBranchGraph();

		System.out.println("\nAdmin displays customers.\n");
		admin1.displayCustomers();

		System.out.println("\nCustomer displays products.\n");
		customer1.displayProducts();

		System.out.println("\nEvery employee of each branch displays products.\n");
		for ( int i = 0; i < 15; ++i ) {
			System.out.println("Branch: " + branches.get(i).getBranchName());
			employees.get(i).displayProducts();
			System.out.println();
		}

		System.out.println("\nCustomer requests an unexisted product of current branch which is electronic device, and gets branch suggestion\n");

		Branch b = customer1.getBranchSuggestion(company1, new Electronic("phone", "samsung", 1000.0, 200, false, 12.1, 21.0));
		System.out.println("Suggested(closest) branch according to the dijkstra is: " + b.getBranchName());
	}

	/**
	 * Testing buying system for the customer
	 * Test Case: 
	 */
	public static void test2() {
		//Creating company
		Company company1 = new Company("company1");

    	//Creating an administrator
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company1); 

    	// Creating a branch	
		Branch branch1 = new Branch("branch1");

		//Creating a branch manager
		BranchManager manager1 = new BranchManager("manager1", 30, Gender.MALE, "mngr_1" , "m4n4g3r1" , branch1);	
		admin1.setBranchManager(branch1, manager1);
    	//Creating an employee and customer.	
		BranchEmployee employee1 = new BranchEmployee("elif", 30, Gender.FEMALE, "Elifabla" , "Elif2001." , branch1);
		Customer customer1 = new Customer("ayse", 25, Gender.FEMALE, "a.ayse58" , "58A_ysesivas" ,branch1);

    	// Admin adds branch to company. Company after adding branch.		
		admin1.addBranch(branch1);

		// Employees add new products.
		Product product2 = new Clothes("tshirt", "zara", 111.8, Size.L, "matType1", "green", true, Gender.MALE,7);		
		Product product5 = new Clothes("blouse", "mavi", 74, Size.M, "matType1", "white", true, Gender.FEMALE,6);		
		Product product3 = new Drink("tea", "dogus", 12.6 , "11/02/2023", 1.5,10);
		Product product4 = new Electronic("phone", "samsung", 1000.0, 200, false, 12.1, 21.0);

		employee1.addProduct(product2);
		employee1.addProduct(product5);
		employee1.addProduct(product3);
		employee1.addProduct(product4);

		System.out.println("\nBranch after new products, employee and customer added.");
		System.out.println("\n" + branch1 + "\n-----------");


		System.out.println("Customer display products, then adds product to basket and displays basket.\n");
		customer1.displayProducts();
		customer1.addProductToBasket(product4);
		customer1.addProductToBasket(product5);
		customer1.addProductToBasket(product2);
		customer1.displayBasket(); 

		System.out.println("\n\n-----------");
		System.out.println("Customer purchases products in the basket.\n");
		customer1.purchaseBasket();

		System.out.println("\n\n-----------");
		System.out.println("Product stocks after purchase.\n");
		customer1.displayProducts();


	}

	/**
	 * Tests the performance of the system
	 */
	public static void test3() {
		performanceTest(100);
		System.out.println("---------------------------------------------");
		performanceTest(1000);
		System.out.println("---------------------------------------------");		
		performanceTest(10000);
		System.out.println("---------------------------------------------");		
	}


	/**
	 * Tests the performance of the system
	 * @param size input size
	 */
	private static void performanceTest(int size) {

		System.out.println("Performance test for " + size + " dataset.\n");

		// Company comp1 = new Company("comp");
		// Administrator admin1 = new Administrator("adm1", 22, Gender.FEMALE, "", "", comp1);
		Branch br1 = new Branch("br1");
		BranchManager bm1 = new BranchManager("bm1", 33, Gender.MALE, "","",br1);

		ArrayList<BranchEmployee> employees = new ArrayList<BranchEmployee>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Product> products = new ArrayList<Product>();

		Random rand = new Random();
		long startTime, endTime;

		System.out.print("average of inserting " + size +  " employee = ");
		startTime = System.nanoTime();
		// test bm's add employee
		for(int i = 0; i < size; ++i) {
			employees.add(new BranchEmployee(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER,"","", br1));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("average of inserting " + size +  " customer = ");
		startTime = System.nanoTime();
		// test bm's add customer
		for(int i = 0; i < size; ++i) {

			customers.add(new Customer(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER,"","", br1));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");


		for(int i = 0; i < size; ++i) {

			products.add(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", ProductType.OTHER, (double) Integer.valueOf(rand.nextInt(99999))));
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

			customers.get(i).requestProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", ProductType.OTHER, (double) Integer.valueOf(rand.nextInt(99999))));
		}
		endTime = System.nanoTime();

		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("addRequestedProducts() = ");
		startTime = System.nanoTime();			
		// test add requested products
		employees.get(0).addRequestedProducts(10);
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
		bm1.addBranchEmployee(new BranchEmployee(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER, "","",br1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("inserting one employee to full = ");
		startTime = System.nanoTime();			
		bm1.addCustomer(new Customer(Integer.valueOf(rand.nextInt(99999)).toString(), rand.nextInt(99), Gender.OTHER,"","", br1));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("inserting one product to full = ");
		startTime = System.nanoTime();			
		employees.get(2).addProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", ProductType.OTHER, (double) Integer.valueOf(rand.nextInt(99999))));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

		System.out.print("requesting one product to full = ");
		startTime = System.nanoTime();			
		customers.get(2).requestProduct(new Product(Integer.valueOf(rand.nextInt(99999)).toString(), "brand", ProductType.OTHER, (double) Integer.valueOf(rand.nextInt(99999))));
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) / size + "ns\n");

	}

	/**
	 * Test Case: Tests the GUI
	 */
	public static void test4(SCSystem system) {
		system.menu();
	}
}