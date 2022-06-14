 package src.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import src.product.*;
import src.user.*;
import src.graph.DynamicBranchGraph;

/**
 * Sales Constrol System
 */
public class SCSystem {
    /** Company of system */
    private Company company; 

    /** Register requested users (key:username value:user).*/
    private HashMap<String, User> registeredUsers = new HashMap<String, User>();  
    /** All users of system (key:username value:user). */
    private HashMap<String, User> users = new HashMap<String, User>();  

    public SCSystem () {
        System.out.println("Creating company");
		this.company = new Company("BIG SUPERMARKET");
    	initialUsers();
        menu();
    }

    private void initialUsers(){
        Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company);    	
    	System.out.println("\n" + admin1 + "\n");

		Branch branch1 = new Branch("branch1");
        admin1.addBranch(branch1);

		BranchManager branchmng1 = new BranchManager("tarık", 10, Gender.MALE, "tarik.mng", "12345679", branch1);
		admin1.setBranchManager(branch1, branchmng1);
        
		Branch branch2 = new Branch("branch2");
        admin1.addBranch(branch2);
	
		BranchManager branchmng2 = new BranchManager("osman", 10, Gender.MALE, "mngrOsmn", "ew1221we", branch2);
		admin1.setBranchManager(branch2, branchmng2);
        
        BranchEmployee bemp1 = new BranchEmployee("sevim", 24, Gender.FEMALE, "Sevim7" ,  "Sevim2001." , branch1);
        branchmng1.addBranchEmployee(bemp1);

        BranchEmployee bemp2 = new BranchEmployee("osman", 24, Gender.MALE, "Osmanpzr" , " Osman3002." ,branch2);
        branchmng2.addBranchEmployee(bemp2);

        BranchEmployee bemp3 = new BranchEmployee("asli", 25, Gender.FEMALE, "asliii", "A_sli3106." , branch2);
        branchmng2.addBranchEmployee(bemp3);

        Product product1 = new Clothes("clothes1", "brand1", 132.6, Size.XL, "matType1", "blue", false, Gender.FEMALE, 1);
        Product product2 = new Clothes("clothes2", "brand2", 111.8, Size.L, "matType1", "green", true, Gender.MALE, 4);		
		Product product3 = new Drink("tea", "brand3", 12.6 , "11/02/2023", 1.5, 45);
		Product product4 = new Electronic("phone", "samsung", 1000.0, 200, false, 12.1, 21.0, 42);
		Product product5 = new Food("crisps", "ruffle", 132.6, "13/07/2023", 0.250, 4);
		Product product6 = new Furniture("furniture1", "ikea",  131.9, 142.1, 1.92, "black", 4);
		Product product7 = new PersonalCare("shampoo", "blendax", 76.1, "For Dandruff Hair", "14/06/2024",3);

        bemp1.addProduct(product2);
        bemp1.addProduct(product3);
        bemp1.addProduct(product4);
        bemp1.addProduct(product5);
        bemp1.addProduct(product6);
        bemp1.addProduct(product7);
        bemp3.addProduct(product1);

        company.getAdministrator().connectBranches(branch1, branch2, 12.2);

		Customer customer1 = new Customer("ali", 29, Gender.MALE, "mali53", "rizeliA_li53" , branch1);
		Customer customer2 = new Customer("avni", 25, Gender.MALE, "avni.celik", "avn_1234" , branch1);
		Customer customer3 = new Customer("emir", 25, Gender.MALE, "emir.efe34", "E_efe1903" , branch2);
        branchmng1.addCustomer(customer1);
        branchmng1.addCustomer(customer2);
        branchmng2.addCustomer(customer3);

        users.put(admin1.getUserName(), admin1);
        users.put(branchmng1.getUserName(), branchmng1);
        users.put(branchmng2.getUserName(), branchmng2);
        users.put(customer1.getUserName(), customer1);
        users.put(customer2.getUserName(), customer2);
        users.put(customer3.getUserName(), customer3);
        users.put(bemp1.getUserName(), bemp1);
        users.put(bemp2.getUserName(), bemp2);
        users.put(bemp3.getUserName(), bemp3);
    }

    /**
     * It prints a menu, takes user input, 
     * and then calls the appropriate function based on the input.
     */
    public void menu(){
        Scanner input = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        printMenuHeader("main");
        System.out.print(" Choice: ");
        String inp = input.nextLine();

        while(!inp.equals("0")){
            if(inp.equals("1")){
                User newUser = signUp();
                registeredUsers.put(newUser.getUserName(), newUser);
            }
            else if(inp.equals("2"))
                signIn();
            else{
                System.out.print("\033[H\033[2J");
                System.out.println(" INVALID OPERATION!");
            }
            printMenuHeader("main");
            System.out.print(" Choice: ");
            inp = input.nextLine();
        }
        System.out.print("\033[H\033[2J");
    }

    /** ArrayList of noworking branch managers */
    private ArrayList<BranchManager> noworkingBranchManagers = new ArrayList<BranchManager>();

    /**
     * It creates an user to register the system.
     * @return A User object.
     */
    private User signUp(){
        System.out.print("\033[H\033[2J");
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("---------------------------- USER SIGN UP ----------------------------");
        System.out.println("----------------------------------------------------------------------");
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;
            
            User usr = createUser();
            String userType = "";
            do {
                flag = false;        
                System.out.printf("\n %-18s: M-m", "Branch Manager");
                System.out.printf("\n %-18s: E-e", "Branch Employee");
                System.out.printf("\n %-18s: C-c", "Customer");
                System.out.print("\n Enter user type: ");
                
                String inp = input.nextLine();
                if(inp.equals("M") || inp.equals("m")
                || inp.equals("E") || inp.equals("e")
                || inp.equals("C") || inp.equals("c"))
                    userType = inp.toUpperCase();
                else{
                    System.err.println(" INVALID USER TYPE!");
                    flag = true;
                }
            } while (flag);

            User newUser = null;
            if(userType.equals("E"))
                newUser = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), chooseBranch());
            else if(userType.equals("M")){
                newUser = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), new Branch("None"));
                noworkingBranchManagers.add((BranchManager)newUser);
            }
            else if(userType.equals("C"))
                newUser = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), chooseBranch());

            System.out.print("\033[H\033[2J");
            System.out.print("----------------------------------------------------------------------");
            System.out.print(newUser);
            System.out.println(" REGISTRATION REQUEST RECEIVED SUCCESSFULLY.");
            System.out.println("----------------------------------------------------------------------");
            return newUser;
        }
    }

    /**
     * Method that allows a user to sign in to the system.
     */
    private void signIn(){
        System.out.print("\033[H\033[2J");
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("---------------------------- USER SIGN IN ----------------------------");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("-  0 - Exit  ---------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------");
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;
            String username;
            do {
                flag = false;        
                System.out.print("\n Enter username: ");
                username = input.nextLine();
                if(username.equals("0")){
                    System.out.print("\033[H\033[2J");
                    flag = true;
                    break;
                }
                if(!users.containsKey(username)){
                    if(registeredUsers.containsKey(username))
                        break;
                    System.err.println(" THERE IS NO USER IN SYSTEM WITH THIS USERNAME!");
                    flag = true;
                }
            } while (flag);
            
            String password;
            if(!flag){
                do {
                    flag = false;        
                    System.out.print("\n Enter password: ");
                    password = input.nextLine();
                    if(password.equals("0")){
                        System.out.print("\033[H\033[2J");
                        flag = true;
                        break;
                    }
                    if(users.containsKey(username)){
                        if(!users.get(username).getPassword().equals(password)){
                            System.err.println(" WRONG PASSWORD!");
                            flag = true;
                        }
                    }
                    else{
                        if(registeredUsers.containsKey(username)){
                            flag = true;
                            if(!registeredUsers.get(username).getPassword().equals(password))
                                System.err.println(" WRONG PASSWORD!");
                            else{
                                System.out.print("\033[H\033[2J");
                                System.out.println("----------------------------------------------------------------------");
                                System.err.println(" YOUR REGISTRATION MUST BE APPROVED BY ADMINISTRATOR! ");
                                System.out.println("----------------------------------------------------------------------");
                                break;
                            }
                        }
                    }
                } while (flag);    
            }

            if(!flag){
                System.out.print("\033[H\033[2J");
                User currUser = users.get(username);
                System.out.println("----------------------------------------------------------------------");
                System.out.printf(" %s SIGNED IN SUCCESSFULLY.\n", username);
                System.out.println("----------------------------------------------------------------------");

                if(currUser instanceof Administrator)
                    administratorMenu(company.getAdministrator());
                else if(currUser instanceof BranchEmployee)
                    branchEmployeeMenu((BranchEmployee)currUser);
                else if(currUser instanceof BranchManager)
                    branchManagerMenu((BranchManager)currUser);
                else if(currUser instanceof Customer)
                    customerMenu((Customer) currUser);
            }
        }
    }

    /**
     * It prints a menu, takes user input, and then 
     * calls the appropriate function based on the input.
     * to perform administrator operations.
     * @param administrator administrator of company
     */
    private void administratorMenu(Administrator administrator){
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;

            System.out.print("\033[H\033[2J");
            printMenuHeader("admin");
            System.out.print(" Choice: ");
            String inp = input.nextLine();

            while(!inp.equals("0")){
                System.out.print("\033[H\033[2J");
                if(inp.equals("1")){
                    String inp2;
                    if(registeredUsers.size() == 0)
                        System.out.println(" THERE IS NO REGISTRATION REQUEST!");
                    else{
                        do {
                            flag = false;
                            System.out.println("\n 1 - Approve All\n 2 - Approve Individually\n 3 - Reject All");
                            System.out.print(" Choice: ");
                            inp2 = input.nextLine();
                            if(inp2.equals("1")){
                                users.putAll(registeredUsers);
                                registeredUsers.clear();
                                System.out.println(" ALL REGISTRATION REQUESTS ARE APPROVED SUCCESSFULLY!");
                            }
                            else if(inp2.equals("2")){
                                Set<String> usernames = registeredUsers.keySet();
                                ArrayList<String> rejectedList = new ArrayList<String>();
                                for (Object username : usernames) {
                                    User user = registeredUsers.get(username);
                                    System.out.println(user);
                                    boolean flag2;
                                    String inp3;
                                    do {
                                        flag2 = false;
                                        System.out.println("\n 1 - Approve\n 2 - Reject\n 3 - Pass");
                                        System.out.print(" Choice: ");
                                        inp3 = input.nextLine(); 
                                        if(inp3.equals("1")){
                                            users.put(user.getUserName(), user);
                                            registeredUsers.remove(user.getUserName());
                                            System.out.println(" REGISTRATION IS APPROVED!");
                                        }
                                        else if(inp3.equals("2")){
                                            rejectedList.add(user.getUserName());
                                            System.out.println(" REGISTRATION IS REJECTED!");
                                        }
                                        else if(inp3.equals("3"))
                                            System.out.println(" USER WILL BE EXAMINED LATER!");
                                        else{            
                                            System.err.println(" INVALID OPERATION!");
                                            flag2 = true;
                                        }
                                    } while (flag2);
                                }
                                for (int i = 0; i < rejectedList.size(); i++)
                                registeredUsers.remove(rejectedList.get(i));
                            }
                            else if(inp2.equals("3")){
                                registeredUsers.clear();
                                System.out.println("  ALL REGISTRATION REQUESTS ARE REJECTED SUCCESSFULLY!");
                            }else{
                                System.out.print("\033[H\033[2J");
                                System.err.println(" INVALID OPERATION!");
                                flag = true;
                            }
                        } while (flag);             
                    }
                }
                else if(inp.equals("2")){
                    System.out.print("\n Enter Branch Name: ");
                    inp = input.nextLine();                    
                    Branch newBranch = new Branch(inp);
                    System.out.println("\n Enter Informations Of Branch Manager:");
                    User usr = createUser();
                    BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), newBranch);
                    administrator.setBranchManager(newBranch, bManager);
                    administrator.addBranch(newBranch);
                    users.put(bManager.getUserName(), bManager);
                    System.out.println(" BRANCH IS ADDED SUCCESSFULLY!");
                }
                else if(inp.equals("3")){
                    DynamicBranchGraph branches = company.getBranches();
                    if(branches.getNumV() == 0)
                        System.out.println(" THERE IS NO BRANCH AT COMPANY TO REMOVE!");
                    else{
                        Branch choosenBranch = chooseBranch();
                        if(branches.removeBranch(branches.getID(choosenBranch)))       
                            System.out.println(" BRANCH IS REMOVED SUCCESSFULLY!");
                    }
                }
                else if(inp.equals("4")){
                    /* do{
                        flag = false;
                        System.out.println("\n 1 - Add New Branch Manager");
                        System.out.println(" 2 - Add Noworking Branch Manager");
                        System.out.print(" Choice: ");
                        inp = input.nextLine();
                        if(inp.equals("1")){
                            Branch branch = chooseBranch();
                            if(branch != null){
                                System.out.println("\n Enter Informations Of Branch Manager:");
                                User usr = createUser();
                                BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
                                noworkingBranchManagers.add(branch.getBranchManager());
                                administrator.setBranchManager(branch, bManager);
                                users.put(bManager.getUserName(), bManager);
                                System.out.println(" BRANCH MANAGER " + bManager.getName() + " IS SET SUCCESSFULLY!");
                            }
                            else
                                System.err.println(" THERE IS NO BRANCH TO ADD BRANCH MANAGER!");
                        }
                        else if(inp.equals("2")){
                            if(!noworkingBranchManagers.isEmpty()){
                                int j = 0, ind = 1;
                        		boolean flag2 = false; 
                                System.err.println("\n--------------------- Noworking Branch Managers ----------------------");
                        		System.out.println("----------------------------------------------------------------------");
                                for (int i = 0; i < noworkingBranchManagers.size(); i++){ 
                                    if(users.containsKey(noworkingBranchManagers.get(i).getUserName())){
                                        System.out.printf(" %d - %s\n", ++j, noworkingBranchManagers.get(i).getName());
                                        flag2 = true;
                                    }
                                }    
                                if(flag2){
                                    do {
                                        System.out.print(" Enter A Branch Manager: ");
                                        String s = input.nextLine();
                                        try {        
                                            ind = Integer.parseInt(s);
                                            if(ind < 1 || ind > j)            
                                                System.err.println(" INVALID BRANCH MANAGER!");
                                            else
                                                break;     
                                        } catch (Exception e) {
                                            System.err.println(" INVALID INPUT!");
                                            continue;
                                        } 
                                    } while (true);
                                    Branch branch = chooseBranch();
                                    if(branch != null){
                                        System.out.println(ind);
                                        BranchManager bManager = noworkingBranchManagers.remove(ind-1);
                                        administrator.setBranchManager(branch, bManager);
                                    }
                                    else
                                        System.err.println(" THERE IS NO BRANCH TO ADD BRANCH MANAGER!");
                                }
                                else                            
                                    System.out.println(" THERE IS NO REGISTERED NOWORKING BRANCH MANAGER!"); 
                            }
                            else
                                System.out.println(" THERE IS NO NOWORKING BRANCH MANAGER!");        
                    		System.out.println("----------------------------------------------------------------------");
                        }
                        else{
                            System.out.println(" INVALID OPERATION!");
                            flag = true;
                        }
                    }while(flag); */                
                }
                else if(inp.equals("5"))
                    administrator.displayBranches();
                else if(inp.equals("6"))
                    administrator.displayBranchManagers();
                else if(inp.equals("7"))
                    administrator.displayEmployees();
                else if(inp.equals("8"))
                    administrator.displayCustomers();
                else if(inp.equals("9"))
                    administrator.displayTotalSalesPrice();
                else
                    System.out.println(" INVALID OPERATION!");

                printMenuHeader("admin");
                System.out.print(" Choice: ");
                inp = input.nextLine();
            }
        }
        System.out.print("\033[H\033[2J");
    }

    /**
     * It prints a menu, takes user input, and then calls 
     * the appropriate function based on the input
     * to perform branch employee operations.
     * @param currEmployee current employee
     */
    private void branchEmployeeMenu(BranchEmployee currEmployee){
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("\033[H\033[2J");
            printMenuHeader("bemployee");
            System.out.print(" Choice: ");
            String inp = input.nextLine();
            
            while(!inp.equals("0")){
                System.out.print("\033[H\033[2J");
                if(inp.equals("1"))
                    currEmployee.addProduct(createProduct());
                else if(inp.equals("2")){
                    currEmployee.displayProducts();
                    System.out.print(" Enter The Product Name To Remove: ");
                    if(!currEmployee.removeProduct(input.nextLine()))
                        System.err.println(" NONEXISTING PRODUCT!");;
                }
                else if(inp.equals("3")){
                    currEmployee.addRequestedProducts(inputEntryPrice());
                }else if(inp.equals("4"))
                    currEmployee.displayProducts();
                else if(inp.equals("5"))
                    currEmployee.displayRequestedProducts();
                else{
                    System.out.println(" INVALID OPERATION!");
                }
                printMenuHeader("bemployee");
                System.out.print(" Choice: ");
                inp = input.nextLine();
            }
        }

        System.out.print("\033[H\033[2J");
    }

    /**
     * It prints a menu, takes user input, and then calls 
     * the appropriate function based on the input
     * to perform branch manager operations.
     * @param currManager current manager
     */
    private void branchManagerMenu(BranchManager currManager){
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;

            System.out.print("\033[H\033[2J");
            printMenuHeader("bmanager");
            System.out.print(" Choice: ");
            String inp = input.nextLine();
            
            while(!inp.equals("0")){
                System.out.print("\033[H\033[2J");
                if(inp.equals("1")){
                    System.out.println("\n Enter Branch Employee Name To Add: ");
                    User usr = createUser();
                    BranchEmployee bEmployee = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                    currManager.addBranchEmployee(bEmployee);
                    users.put(bEmployee.getUserName(), bEmployee);
                }
                else if(inp.equals("2")){
                    currManager.displayBranchEmployees();
                    var branchEmployees = currManager.getBranch().getBranchEmployees();
                    if(branchEmployees.isEmpty())
                        System.out.println("\n THERE IS NO BRANCH EMPLOYEE TO REMOVE!");
                    else{
                        String inp2;
                        do{
                            flag = true;
                            System.out.print("\n Enter Branch Employee Name To Remove: ");
                            inp2 = input.nextLine();
                            Iterator<BranchEmployee> itr = branchEmployees.iterator();
                            while ( itr.hasNext() ) {
                                BranchEmployee employee = itr.next();
                                if (employee.getName().equals(inp2)){
                                    currManager.removeBranchEmployee(employee);
                                    users.remove(employee.getUserName());
                                    flag = false;
                                    break;
                                }
            	            }
                            System.err.println(" ENTER EXISTING BRANCH EMPLOYEE!");  
                        } while(flag);
                    }
                }
                else if(inp.equals("3")){
                    System.out.println("\n Enter Customer Name To Add: ");
                    User usr = createUser();
                    Customer newCustomer = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                    currManager.addCustomer(newCustomer);
                    users.put(newCustomer.getUserName(), newCustomer);
                }
                else if(inp.equals("4")){
                    System.out.println(currManager.getBranch().getStringCustomers());
                    var customers = currManager.getBranch().getCustomers();
                    if(customers.size() == 0)
                        System.out.println(" THERE IS NO CUSTOMER TO REMOVE!");
                    else{
                        String inp2;
                        do{
                            flag = true;
                            System.out.print("\n Enter Customer Name To Remove: ");
                            inp2 = input.nextLine();
                            Iterator<Customer> itr = customers.iterator();
                            while ( itr.hasNext() ) {
                                Customer customer = itr.next();
                                if (customer.getName().equals(inp2)){
                                    currManager.removeCustomer(customer);
                                    users.remove(customer.getUserName());
                                    flag = false;
                                    break;
                                }
            	            } 
                            System.err.println(" ENTER EXISTING CUSTOMER!");  
                        }while(flag);
                    }
                }
                else if(inp.equals("5"))
                    currManager.displayBranchEmployees();
                else if(inp.equals("6"))
                    currManager.displayProducts();
                else if(inp.equals("7"))
                    currManager.displaySalesPrice();
                else
                    System.out.println(" INVALID OPERATION!");

                printMenuHeader("bmanager");
                System.out.print(" Choice: ");
                inp = input.nextLine();
            }
        }
        System.out.print("\033[H\033[2J");
    }

    /**
     * It prints a menu, takes user input, and then calls 
     * the appropriate function based on the input
     * to perform customer operations.
     * @param currCustomer customer
     */
    private void customerMenu(Customer currCustomer){
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;
            String inp, inp2;

            System.out.print("\033[H\033[2J");
            printMenuHeader("customer");
            System.out.print(" Choice: ");
            inp = input.nextLine();
            
            while(!inp.equals("0")){
                System.out.print("\033[H\033[2J");
                if(inp.equals("1")){
                    printMenuHeader("products");
                    System.out.println(" 0 - Back To Menu");
                    do {
                        flag = false;                
                        System.out.print("\n Choice: ");
                        inp2 = input.nextLine();    
                        if(inp2.equals("0")){
                            flag = true;
                            break;
                        }
                        else if(inp2.equals("1"))
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.BOOK);
                        else if(inp2.equals("2"))
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.CLOTHES);
                        else if(inp2.equals("3"))
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.DRINK);
                        else if(inp2.equals("4"))                    
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.ELECTRONIC);
                        else if(inp2.equals("5"))
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.FOOD);
                        else if(inp2.equals("6"))        
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.FURNITURE);
                        else if(inp2.equals("7")) 
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.PERSONALCARE);
                        else if(inp2.equals("8")) 
                            flag = printProducts(currCustomer.getShoppingBranch(), ProductType.TOY);
                        else{
                            System.out.println(" INVALID INPUT!");
                            flag = true;
                        }
                    } while (flag);
                    
                    if(!flag){
                        do{ 
                            System.out.println("\n 1 - Add Product To Basket");
                            System.out.println(" 2 - Request Product");
                            System.out.println(" 3 - Branch Suggestion For Product That Not Available ");
                            System.out.println(" 0 - Back To Menu");
                            System.out.print(" Choice: ");
                            inp = input.nextLine();
                            if(inp.equals("1")){
                                System.out.print("\n Enter Product Name: ");
                                Product product = currCustomer.findProductByName(input.nextLine());
                                if(product != null){
                                    if(!currCustomer.addProductToBasket(product))
                                        System.out.println(" INVALID PRODUCT!");
                                }
                                else
                                    System.out.println(" INVALID PRODUCT!");
                            }
                            else if(inp.equals("2")){
                                Product p = inProduct("");
                                currCustomer.requestProduct(p);
                                System.out.println(" PRODUCT IS REQUESTED FROM BRANCH!");
                            }
                            else if(inp.equals("3")){
                                Branch branch = currCustomer.getBranchSuggestion(company, inProduct("create"));
                                if(branch == null)
                                    System.out.println(" PRODUCT COULD NOT BE FOUND IN ANY BRANCH!");
                                else
                                    System.out.printf(" The Closest Branch is %s.\n", branch.getBranchName());
                            }
                            else if(!inp.equals("0"))
                                System.out.println(" INVALID OPERATION!");
                        }while(!inp.equals("0"));
                    }
                }
                else if(inp.equals("2")){
                    currCustomer.displayBasket();
                    if(currCustomer.isBasketEmpty()){
                        System.err.println("\n THERE IS NO PRODUCT AT BASKET!");
                    }
                    else{
                        do {
                            System.out.println("\n 1 - Buy Products"); 
                            System.out.println(" 2 - Remove Product From Basket");
                            System.out.println(" 0 - Back To Menu");
                            System.out.print("\n Choice: ");
                            inp = input.nextLine();
                            if(inp.equals("1"))
                                currCustomer.purchaseBasket();
                            else if(inp.equals("2")){
                                int number;
                                do {
                                    System.out.print("\n Enter Product Number: ");
                                    String n = input.nextLine();
                                    try {
                                        number = Integer.parseInt(n);
                                        if(number < 1)            
                                            System.err.println(" PRODUCT MUST BE POSITIVE!");
                                        else
                                            break;        
                                    } catch (Exception e) {
                                        System.err.println(" INVALID PRODUCT NUMBER!");
                                    }    
                                } while (true);
                                Product product = currCustomer.findProductFromBasketByOrder(number);
                                if(product != null){
                                    if(!currCustomer.removeProductFromBasket(product))
                                        System.out.println(" INVALID PRODUCT!");
                                }
                                else
                                    System.out.println(" INVALID PRODUCT!");
                            }
                            else if(!inp.equals("0"))
                                System.out.println(" INVALID OPERATION!");
                        } while (!inp.equals("0"));
                    }
                }
                else if(inp.equals("3"))
                    currCustomer.printOrderHistory();
                else if(!inp.equals("0"))
                    System.out.println("\n INVALID OPERATION!");
                
                printMenuHeader("customer");
                System.out.print(" Choice: ");
                inp = input.nextLine();
            }
        }
        System.out.print("\033[H\033[2J");    
    }
    
    /**
     * This function prints all the products of a given type in a given branch
     * 
     * @param branch The branch to print the products from.
     * @param type the type of the product (e.g. "drink")
     * @return false if print is succussfull, true otherwise
     */
    private boolean printProducts(Branch branch,ProductType type){
        TreeSet<Product> treeSet = branch.getProducts(type);
        if(treeSet != null){
            for(var prod : branch.getProducts(type))
                System.out.println(prod);
            return false;
        }
        System.err.printf("\n THERE IS NO PRODUCT AT BRANCH %s IN TYPE %s!\n",branch.getBranchName(),type);
        return true;
    }
    
    /**
     * It prints the menu header for the main menu, admin menu, branch employee menu, branch manager
     * menu, customer menu and product menu.
     * @param menu type of menu
     */
    private void printMenuHeader(String menu){
        if(menu.equals("main")){
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("------------------------ SALES CONTROL SYSTEM ------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("-  COMPANY : %-54s  -\n", company.getCompanyName());
            System.out.println("----------------------------------------------------------------------");
            System.out.println("-  1 - Sign Up  ------------------------------------------------------");
            System.out.println("-  2 - Sign In  ------------------------------------------------------");
            System.out.println("-  0 - Exit     ------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(menu.equals("admin")){
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("------------------------- ADMINISTRATOR MENU -------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("-  %-66s-\n", "1 - Approve Waiting Registrations");
            System.out.printf("-  %-66s-\n", "2 - Add Branch");
            System.out.printf("-  %-66s-\n", "3 - Remove Branch");
            System.out.printf("-  %-66s-\n", "4 - Set Branch Manager");
            System.out.printf("-  %-66s-\n", "5 - Display Branches");
            System.out.printf("-  %-66s-\n", "6 - Display Branch Managers");
            System.out.printf("-  %-66s-\n", "7 - Display Branch Employees");
            System.out.printf("-  %-66s-\n", "8 - Display Customers");
            System.out.printf("-  %-66s-\n", "9 - Display Company Sales Price");
            System.out.printf("-  %-66s-\n", "0 - Exit");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(menu.equals("bemployee")){
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("------------------------ BRANCH EMPLOYEE MENU ------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("-  %-66s-\n", "1 - Add Product");
            System.out.printf("-  %-66s-\n", "2 - Remove Product");
            System.out.printf("-  %-66s-\n", "3 - Add Requested Products");
            System.out.printf("-  %-66s-\n", "4 - Display Products");
            System.out.printf("-  %-66s-\n", "5 - Display Requested Products");
            System.out.printf("-  %-66s-\n", "0 - Exit");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(menu.equals("bmanager")){
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("------------------------- BRANCH MANAGER MENU ------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("-  %-66s-\n", "1 - Add Branch Employee");
            System.out.printf("-  %-66s-\n", "2 - Remove Branch Employee");
            System.out.printf("-  %-66s-\n", "3 - Add Customer");
            System.out.printf("-  %-66s-\n", "4 - Remove Customer");
            System.out.printf("-  %-66s-\n", "5 - Display Branch Employees");
            System.out.printf("-  %-66s-\n", "6 - Display Products");
            System.out.printf("-  %-66s-\n", "7 - Display Branch Sales Price");
            System.out.printf("-  %-66s-\n", "0 - Exit");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(menu.equals("customer")){
            System.out.println("\n----------------------------------------------------------------------");
            System.out.println("---------------------------- CUSTOMER MENU ---------------------------");
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("-  %-66s-\n", "1 - Display Products");
            System.out.printf("-  %-66s-\n", "2 - Display Basket");
            System.out.printf("-  %-66s-\n", "3 - Display Order History");
            System.out.printf("-  %-66s-\n", "0 - Exit");
            System.out.println("----------------------------------------------------------------------");
        }
        else if(menu.equals("products")){
            System.out.println("\n 1 - Book");
            System.out.println(" 2 - Clothes");
            System.out.println(" 3 - Drink");
            System.out.println(" 4 - Electronic");
            System.out.println(" 5 - Food");
            System.out.println(" 6 - Furniture");
            System.out.println(" 7 - Personal Care");
            System.out.println(" 8 - Toy");
        }
    }

    /**
     * It creates a User object according to user inputs.
     * @return A new User object
     */
    private User createUser(){
        try (Scanner input = new Scanner(System.in)) {
            String name, username, password;
            Gender gender = null;
            int age;        
            boolean flag;

            System.out.print(" Enter Name: ");
            name = input.nextLine();

            do {
                System.out.print("\n Enter Age: ");
                String a = input.nextLine();
                try {        
                    age = Integer.parseInt(a);
                    if(age < 1)            
                        System.err.println(" AGE MUST BE POSITIVE VALUE!");
                    else
                        break;        
                } catch (Exception e) {
                    System.err.println(" INVALID AGE FORMAT!");
                }    
            } while (true);
            
            do {        
                flag = false;
                System.out.println("\n Male: M-m   Female: F-f   Other: O-o");
                System.out.print(" Enter Gender: ");
                String gen = input.nextLine();

                if(gen.equals("M") || gen.equals("m"))
                    gender = Gender.MALE;
                else if(gen.equals("F") || gen.equals("f"))
                    gender = Gender.FEMALE;
                else if(gen.equals("O") || gen.equals("o"))
                    gender = Gender.OTHER;
                else{
                    System.err.println(" INVALID GENDER TYPE!");
                    flag = true;
                }
            } while (flag);
            
            do {
                flag = false;        
                System.out.print("\n Enter Username: ");
                username = input.nextLine();
                if(users.containsKey(username)){
                    System.err.println(" THIS USERNAME IS TAKEN ALREADY!");
                    flag = true;
                }  
            } while (flag);
            
            do {
                flag = false;        
                System.out.print("\n Enter Password(Min 8 Charachters): ");
                password = input.nextLine(); 
                if(password.length() < 8){
                    System.err.println(" WEAK PASSWORD IN LENGTH!");
                    flag = true;
                }  
            } while (flag);
            return new User(name, age, gender, username, password);
        }
    }
  
    /**
     * It takes user input and creates a new product object
     * @return A new product
     */
    private Product inProduct(String usageAim){
        try (Scanner input = new Scanner(System.in)) {
            String name, brand, type;
            double entryPrice;
            ProductType pType = null;

            System.out.print("\n Enter Product Name: ");
            name = input.nextLine();

            System.out.print("\n Enter Brand Name: ");
            brand = input.nextLine();

      
            printMenuHeader("products");
            do {
                System.out.print("\n Enter Type: ");
                type = input.nextLine();
                try {        
                    int t = Integer.parseInt(type);
                    if(t < 1 || t > 8)            
                        System.err.println(" INVALID CATEGORY!");
                    else{
                        ProductType[] pTypes = ProductType.values(); 
                        pType = ProductType.valueOf(pTypes[t-1].toString());
                        break;
                    }        
                } catch (Exception e) {
                    System.err.println(" INVALID INPUT!");
                } 
            } while (true);

            if(usageAim.equals("create"))
                entryPrice = inputEntryPrice();
            else
                entryPrice = 0.0;
            return new Product(name, brand, pType, entryPrice);
        }
    }

    /**
     * It takes a user input, checks if it's a valid double, and if it is, it checks if it's positive,
     * and if it is, it returns it
     * 
     * @return The entry price.
     */
    private double inputEntryPrice(){
        try (Scanner input = new Scanner(System.in)) {
            double entryPrice;
            do {
                System.out.print("\n Enter Entry Price: ");
                String d = input.nextLine();
                try {        
                    entryPrice = Double.parseDouble(d);
                    if(entryPrice < 0.0)            
                        System.err.println(" ENTRY PRICE MUST BE POSITIVE!");
                    else
                        return entryPrice;        
                } catch (Exception e) {
                    System.err.println(" INVALID PRICE FORMAT!");
                }    
            } while (true);
        }
    }
    
   /**
    * It creates a product object and sets the stock of the product from the user input
    * @return A Product object.
    */
    private Product createProduct(){
        try (Scanner input = new Scanner(System.in)) {
            int stock;

            Product p = inProduct("create");
            do {
                System.out.print("\n Enter Stock: ");
                String s = input.nextLine();
                try {
                    stock = Integer.parseInt(s);
                    if(stock < 1)            
                        System.err.println(" STOCK MUST BE POSITIVE!");
                    else
                        break;        
                } catch (Exception e) {
                    System.err.println(" INVALID STOCK!");
                }    
            } while (true);
            p.setStock(stock);
            return p;
        }
    }

    /**
     * It takes a branch name from the user and returns the 
     * branch object with that name from branches at company
     * @return a Branch object.
     */
    private Branch chooseBranch(){
        try (Scanner input = new Scanner(System.in)) {
            boolean flag;
            String branchName;

            System.out.println("\n Existing Branches");
            company.getAdministrator().displayBranches();

            DynamicBranchGraph branches = company.getBranches();
            if(branches.getNumV() == 0)
                System.out.println(" THERE IS NO BRANCH AT COMPANY!");
            else{    
                do {
                    flag = true;        
                    System.out.print("\n Enter Branch Name: ");
                    branchName = input.nextLine();           
                    Iterator<Branch> itr = branches.iterator();
            	    while ( itr.hasNext() ) {
            		    Branch branch = itr.next();
            		    if (branchName.equals(branch.getBranchName()))
            			    return branch;
            	    }	         
                    if(flag)
                        System.err.println(" ENTER EXISTING BRANCH NAME!");
                } while (flag);
            }
        }
        return null;
    }
}