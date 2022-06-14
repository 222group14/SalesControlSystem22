package src.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import src.product.Book;
import src.product.Clothes;
import src.product.Drink;
import src.product.Electronic;
import src.product.Food;
import src.product.Furniture;
import src.product.Gender;
import src.product.PersonalCare;
import src.product.Product;
import src.product.ProductType;
import src.product.Size;
import src.product.Toy;
import src.user.User;
import src.graph.DynamicBranchGraph;
import src.user.Administrator;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

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
    	initialCompany();
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
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------- SYSTEM TERMINATED... -------------------------");
        System.out.println("----------------------------------------------------------------------");
    }

    /**
     * It creates an user to register the system.
     * @return A User object.
     */
    private User signUp(){
        System.out.print("\033[H\033[2J");
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("---------------------------- USER SIGN UP ----------------------------");
        System.out.println("----------------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
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

        Branch branch = chooseBranch();
        User newUser = null;
        if(userType.equals("E"))
            newUser = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
        else if(userType.equals("M"))
            newUser = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), new Branch(branch.getBranchName()));
        else if(userType.equals("C"))
            newUser = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);

        System.out.print("\033[H\033[2J");
        System.out.print("----------------------------------------------------------------------");
        System.out.print(newUser);
        System.out.println(" REGISTRATION REQUEST RECEIVED SUCCESSFULLY.");
        System.out.println("----------------------------------------------------------------------");
        return newUser;
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
        Scanner input = new Scanner(System.in);
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

    /**
     * It prints a menu, takes user input, and then 
     * calls the appropriate function based on the input.
     * to perform administrator operations.
     * @param administrator administrator of company
     */
    private void administratorMenu(Administrator administrator){
        Scanner input = new Scanner(System.in);
        boolean flag;

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
                            Iterator<User> itr1 = registeredUsers.values().iterator();
                            while(itr1.hasNext()){
                                User u = (User)itr1.next();
                                if(u instanceof BranchManager){
                                    BranchManager bm = (BranchManager)u;
                                    Iterator<Branch> itr2 = company.getBranches().branchIterator();
                                    while(itr2.hasNext()){
                                        Branch b = (Branch)itr2.next();
                                        if(b.getBranchName().equals(bm.getBranch().getBranchName()))
                                            administrator.setBranchManager(b, bm);
                                    }
                                }
                                users.put(u.getUserName(), u);
                            }
                            registeredUsers.clear();
                            System.out.println(" ALL REGISTRATION REQUESTS ARE APPROVED SUCCESSFULLY!");
                        }
                        else if(inp2.equals("2")){
                            Set<String> usernames = registeredUsers.keySet();
                            ArrayList<String> approvedList = new ArrayList<String>();
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
                                        if(user instanceof BranchManager){
                                            BranchManager bm = (BranchManager)user;
                                            Iterator<Branch> itr2 = company.getBranches().branchIterator();
                                            while(itr2.hasNext()){
                                                Branch b = (Branch)itr2.next();
                                                if(b.getBranchName().equals(bm.getBranch().getBranchName()))
                                                    bm = administrator.setBranchManager(b, bm);
                                            }
                                        }
                                        users.put(user.getUserName(), user);
                                        approvedList.add(user.getUserName());
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
                            for (int i = 0; i < approvedList.size(); i++)
                                registeredUsers.remove(approvedList.get(i));
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
                flag = false;
                for (Branch branch : company.getBranches()) {
                    if(branch.getBranchName().equals(inp)){
                        System.out.println(" BRANCH IS ALREADY ADDED!");
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    Branch newBranch = new Branch(inp);
                    System.out.println("\n Enter Informations Of Branch Manager:");
                    User usr = createUser();
                    BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), newBranch);
                    administrator.setBranchManager(newBranch, bManager);
                    administrator.addBranch(newBranch);
                    users.put(bManager.getUserName(), bManager);
                    System.out.println(" BRANCH IS ADDED SUCCESSFULLY!");
                }
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
                Branch branch = chooseBranch();        
                if(branch != null){
                    System.out.println("\n Enter Informations Of Branch Manager:");
                    User usr = createUser();
                    BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
                    users.remove(branch.getBranchManager().getUserName());
                    administrator.setBranchManager(branch, bManager);
                    users.put(bManager.getUserName(), bManager);
                    System.out.println(" BRANCH MANAGER " + bManager.getName() + " IS SET SUCCESSFULLY!");
                }
                else
                    System.err.println(" THERE IS NO BRANCH TO ADD BRANCH MANAGER!");                
            }
            else if(inp.equals("5")){
                administrator.displayBranchGraph();
                System.out.print("\n Choose Source Branch: ");
                Branch source = chooseBranch();
                System.out.print("\n Choose Destination Branch: ");
                Branch destination = chooseBranch();

                double weight;
                do {
                    System.out.print("\n Enter Weight: ");
                    String d = input.nextLine();
                    try {        
                        weight = Double.parseDouble(d);
                        if(weight < 0.0)            
                            System.err.println(" WEIGHT MUST BE POSITIVE!");
                        else
                            break;        
                    } catch (Exception e) {
                        System.err.println(" INVALID WEIGHT FORMAT!");
                    }
                } while (true);

                if(administrator.connectBranches(source, destination, weight))
                    System.out.println(" BRANCHES ARE CONNECTED SUCCESSFULLY!");
                else
                    System.out.println(" BRANCHES COULD NOT CONNECTED!");

                System.out.println();
                administrator.displayBranchGraph();
                
            }
            else if(inp.equals("6")) 
                administrator.displayBranches();
            else if(inp.equals("7"))
                administrator.displayBranchManagers();
            else if(inp.equals("8"))
                administrator.displayEmployees();
            else if(inp.equals("9"))
                administrator.displayCustomers();
            else if(inp.equals("10"))
                administrator.displayTotalSalesPrice();
            else
                System.out.println(" INVALID OPERATION!");

            printMenuHeader("admin");
            System.out.print(" Choice: ");
            inp = input.nextLine();
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
        Scanner input = new Scanner(System.in);

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
                    System.err.println(" NONEXISTING PRODUCT!");
            }
            else if(inp.equals("3")){
                currEmployee.addRequestedProducts(inputEntryPrice());
            }else if(inp.equals("4"))
                currEmployee.displayProducts();
            else if(inp.equals("5"))
                currEmployee.displayRequestedProducts();
            else
                System.out.println(" INVALID OPERATION!");
            printMenuHeader("bemployee");
            System.out.print(" Choice: ");
            inp = input.nextLine();
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
        Scanner input = new Scanner(System.in);
        boolean flag;

        printMenuHeader("bmanager");
        System.out.print(" Choice: ");
        String inp = input.nextLine();
        
        boolean success;
        while(!inp.equals("0")){
            System.out.print("\033[H\033[2J");
            if(inp.equals("1")){
                System.out.println("\n Enter Branch Employee Name To Add: ");
                User usr = createUser();
                BranchEmployee bEmployee = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                success = currManager.addBranchEmployee(bEmployee);
                users.put(bEmployee.getUserName(), bEmployee);
                if(success) 
                    System.out.println(" BRANCH EMPLOYEE IS ADDED SUCCESSFULLY.");
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
                        if(!flag){
                            System.out.println(" SELECTED BRANCH EMPLOYEE IS REMOVED SUCCESSFULLY!");
                            currManager.displayBranchEmployees();
                        }
                        else 
                            System.err.println(" ENTER EXISTING BRANCH EMPLOYEE!");
                    } while(flag);
                }
            }
            else if(inp.equals("3")){
                System.out.println("\n Enter Customer Name To Add: ");
                User usr = createUser();
                Customer newCustomer = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                success = currManager.addCustomer(newCustomer);
                users.put(newCustomer.getUserName(), newCustomer);
                if(success) {
                    System.out.println(" CUSTOMER IS ADDED SUCCESSFULLY.");
                    System.out.println(currManager.getBranch().getStringCustomers());
                }
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
                        if(!flag){
                            System.out.println(" SELECTED CUSTOMER IS REMOVED SUCCESSFULLY!");
                            System.out.println(currManager.getBranch().getStringCustomers());
                        }
                        else
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
        System.out.print("\033[H\033[2J");
    }

    /**
     * It prints a menu, takes user input, and then calls 
     * the appropriate function based on the input
     * to perform customer operations.
     * @param currCustomer customer
     */
    private void customerMenu(Customer currCustomer){
        Scanner input = new Scanner(System.in);
        boolean flag;
        String inp, inp2;

        printMenuHeader("customer");
        System.out.print(" Choice: ");
        inp = input.nextLine();
        
        while(!inp.equals("0")){
            System.out.print("\033[H\033[2J");
            if(inp.equals("1")){
                printMenuHeader("products");
                System.out.println(" 0 - Back To Menu");
                ProductType selected = null;
                do {
                    flag = false;
                    System.out.print(" Choice: ");
                    inp2 = input.nextLine();    
                    if(inp2.equals("0")){
                        flag = true;
                        break;
                    }
                    else if(inp2.equals("1")){
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.BOOK);
                        selected = ProductType.BOOK;
                    }
                    else if(inp2.equals("2")){
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.CLOTHES);
                        selected = ProductType.CLOTHES;
                    }
                    else if(inp2.equals("3")){
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.DRINK);
                        selected = ProductType.DRINK;
                    }
                    else if(inp2.equals("4")){                    
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.ELECTRONIC);
                        selected = ProductType.ELECTRONIC;
                    }
                    else if(inp2.equals("5")){                    
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.FOOD);
                        selected = ProductType.FOOD;
                    }
                    else if(inp2.equals("6")){                    
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.FURNITURE);
                        selected = ProductType.FURNITURE;
                    }
                    else if(inp2.equals("7")){                    
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.PERSONALCARE);
                        selected = ProductType.ELECTRONIC;
                    }
                    else if(inp2.equals("8")){                    
                        flag = printProducts(currCustomer.getShoppingBranch(), ProductType.TOY);
                        selected = ProductType.TOY;
                    }
                    else{
                        System.out.println(" INVALID INPUT!");
                        flag = true;
                    }
                } while (flag);
                
                if(!flag){
                    do{ 
                        System.out.println("\n 1 - Add Product To Basket");
                        System.out.println(" 2 - Request Product");
                        System.out.println(" 3 - Branch Suggestion For Product That Not Available");
                        System.out.println(" 4 - Display Sorted");
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
                        else if(inp.equals("4")){
                            sortBy(currCustomer.getShoppingBranch().getProducts(selected).toArray(), selected);
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
                        System.out.print(" Choice: ");
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
        System.out.print("\033[H\033[2J");    
    }
    
    private void sortBy(Object[] array, ProductType type){
        Scanner input = new Scanner(System.in);
        String inp;
        boolean flag;
        if(type.equals(ProductType.BOOK)){
            Book[] arr = Arrays.copyOf(array, array.length, Book[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Author");
                System.out.println(" 6 - Compare by Kind");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Book.CompareByAuthor());
                else if(inp.equals("6"))
                    Arrays.sort(arr, new Book.CompareByKind());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.CLOTHES)){
            Clothes[] arr = Arrays.copyOf(array, array.length, Clothes[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Gender");
                System.out.println(" 6 - Compare by Kind");
                System.out.println(" 7 - Compare by Material Type");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Clothes.CompareByGender());
                else if(inp.equals("6"))
                    Arrays.sort(arr, new Clothes.CompareByKind());
                else if(inp.equals("7"))
                    Arrays.sort(arr, new Clothes.CompareByMaterialType());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.DRINK)){
            Drink[] arr = Arrays.copyOf(array, array.length, Drink[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Expire Date");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Drink.compareByExpDate());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.ELECTRONIC)){
            Electronic[] arr = Arrays.copyOf(array, array.length, Electronic[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Height");
                System.out.println(" 6 - Compare by Widhth");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Electronic.compareByHeight());
                else if(inp.equals("6"))
                    Arrays.sort(arr, new Electronic.compareByWidth());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.FOOD)){
            Food[] arr = Arrays.copyOf(array, array.length, Food[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Expire Date");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Food.compareByExpDate());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.FURNITURE)){
            Furniture[] arr = Arrays.copyOf(array, array.length, Furniture[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Color");
                System.out.println(" 6 - Compare by Height");
                System.out.println(" 7 - Compare by Widhth");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Furniture.compareByColor());
                else if(inp.equals("6"))
                    Arrays.sort(arr, new Furniture.compareByHeight());
                else if(inp.equals("7"))
                    Arrays.sort(arr, new Furniture.compareByWidth());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.PERSONALCARE)){
            PersonalCare[] arr = Arrays.copyOf(array, array.length, PersonalCare[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Expire Date");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new PersonalCare.compareByExpDate());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
        else if(type.equals(ProductType.TOY)){
            Toy[] arr = Arrays.copyOf(array, array.length, Toy[].class);
            do {
                flag = false;
                printMenuHeader("compare");
                System.out.println(" 5 - Compare by Author");
                System.out.println(" 6 - Compare by Width");
                System.out.print(" Choice: ");
                inp = input.nextLine();
                if(inp.equals("1"))
                    Arrays.sort(arr, new Product.compareByBrand());
                else if(inp.equals("2"))
                    Arrays.sort(arr, new Product.compareByName());
                else if(inp.equals("3"))
                    Arrays.sort(arr, new Product.compareByPrice());
                else if(inp.equals("4"))
                    Arrays.sort(arr, new Product.compareByType());
                else if(inp.equals("5"))
                    Arrays.sort(arr, new Toy.CompareByAuthor());
                else if(inp.equals("6"))
                    Arrays.sort(arr, new Toy.compareByWidth());
                else
                    System.out.println(" INVALID OPERATION!");
            } while (flag);
            printProducts(arr);
        }
    }

    private void printProducts(Product[] arr){
        System.out.println("\n------------------------------ Products ------------------------------\n");
        System.out.println("----------------------------------------------------------------------\n");
		for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
        System.out.println("----------------------------------------------------------------------\n");
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
            System.out.println("-  0 - Exit  ---------------------------------------------------------");
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
            System.out.printf("-  %-66s-\n", "5 - Connect Branches");
            System.out.printf("-  %-66s-\n", "6 - Display Branches");
            System.out.printf("-  %-66s-\n", "7 - Display Branch Managers");
            System.out.printf("-  %-66s-\n", "8 - Display Branch Employees");
            System.out.printf("-  %-66s-\n", "9 - Display Customers");
            System.out.printf("-  %-66s-\n", "10 - Display Company Sales Price");
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
        else if(menu.equals("compare")){
            System.out.println("\n 1 - Compare by Brand");
            System.out.println(" 2 - Compare by Name");
            System.out.println(" 3 - Compare by Price");
            System.out.println(" 4 - Compare by Type");
        }
    }

    /**
     * It creates a User object according to user inputs.
     * @return A new User object
     */
    private User createUser(){
        Scanner input = new Scanner(System.in);
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
  
    /**
     * It takes user input and creates a new product object
     * @return A new product
     */
    private Product inProduct(String usageAim){
        Scanner input = new Scanner(System.in);
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
                    pType = pTypes[t-1];
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

    /**
     * It takes a user input, checks if it's a valid double, and if it is, it checks if it's positive,
     * and if it is, it returns it
     * 
     * @return The entry price.
     */
    private double inputEntryPrice(){
        Scanner input = new Scanner(System.in);
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
    
   /**
    * It creates a product object and sets the stock of the product from the user input
    * @return A Product object.
    */
    private Product createProduct(){
        Scanner input = new Scanner(System.in);
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

    /**
     * It takes a branch name from the user and returns the 
     * branch object with that name from branches at company
     * @return a Branch object.
     */
    private Branch chooseBranch(){
        Scanner input = new Scanner(System.in);
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
        return null;
    }
    private void initialCompany(){
        Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminscs." , company);    	
    	System.out.println("\n" + admin1 + "\n");

		Branch branch1 = new Branch("branch1");
        admin1.addBranch(branch1);

        Branch branch2 = new Branch("branch2");
        admin1.addBranch(branch2);
        
        Branch branch3 = new Branch("branch3");
        admin1.addBranch(branch3);
        
        Branch branch4 = new Branch("branch4");
        admin1.addBranch(branch4);
	

		BranchManager branchmng1 = new BranchManager("tarık", 31, Gender.MALE, "tarik.mng", "12345679", branch1);
		admin1.setBranchManager(branch1, branchmng1);
        
		BranchManager branchmng2 = new BranchManager("osman", 45, Gender.MALE, "mngrOsmn", "ew1221we", branch2);
		admin1.setBranchManager(branch2, branchmng2);

        BranchManager branchmng3 = new BranchManager("irem", 39, Gender.FEMALE, "mngrIrem", "qwer1234", branch3);
		admin1.setBranchManager(branch3, branchmng3);

        BranchManager branchmng4 = new BranchManager("arda", 43, Gender.MALE, "mngrArda", "1903arda.", branch4);
		admin1.setBranchManager(branch4, branchmng4);
        
        BranchEmployee bemp1 = new BranchEmployee("sevim", 24, Gender.FEMALE, "Sevim7" ,  "Sevim2001." , branch1);
        branchmng1.addBranchEmployee(bemp1);

        BranchEmployee bemp2 = new BranchEmployee("osman", 24, Gender.MALE, "Osmanpzr" , " Osman3002." ,branch1);
        branchmng1.addBranchEmployee(bemp2);

        BranchEmployee bemp3 = new BranchEmployee("asli", 25, Gender.FEMALE, "asliii", "A_sli3106." , branch1);
        branchmng1.addBranchEmployee(bemp3);


        BranchEmployee bemp4 = new BranchEmployee("sema", 26, Gender.FEMALE, "semaa" ,  "s_ema2121." , branch2);
        branchmng2.addBranchEmployee(bemp4);

        BranchEmployee bemp5 = new BranchEmployee("şevval", 27, Gender.FEMALE, "Sevval" , "DQWeb21s." ,branch2);
        branchmng2.addBranchEmployee(bemp5);

        BranchEmployee bemp6 = new BranchEmployee("burak", 29, Gender.MALE, "burakk", "B_urak3106." , branch2);
        branchmng2.addBranchEmployee(bemp6);


        BranchEmployee bemp7 = new BranchEmployee("eray", 24, Gender.MALE, "Eray7" ,  "eray2001." , branch3);
        branchmng3.addBranchEmployee(bemp7);

        BranchEmployee bemp8 = new BranchEmployee("M.emre", 24, Gender.MALE, "emre07" , "Emre0707." ,branch3);
        branchmng3.addBranchEmployee(bemp8);

        BranchEmployee bemp9 = new BranchEmployee("esra", 25, Gender.FEMALE, "Esra", "eWQEwq212." , branch3);
        branchmng3.addBranchEmployee(bemp9);


        BranchEmployee bemp10 = new BranchEmployee("furkan", 24, Gender.MALE, "furki" ,  "Furki2001." , branch4);
        branchmng4.addBranchEmployee(bemp10);

        BranchEmployee bemp11 = new BranchEmployee("eray", 24, Gender.MALE, "erayoz" , "E_ray1515." ,branch4);
        branchmng4.addBranchEmployee(bemp11);

        BranchEmployee bemp12 = new BranchEmployee("mehmet", 25, Gender.MALE, "memo", "dsa21A41." , branch4);
        branchmng4.addBranchEmployee(bemp12);

        Product product1 = new Clothes("T_shirt", "mavi", 100, Size.XL, "matType1", "blue", true, Gender.FEMALE, 1);
        Product product2 = new Clothes("Short", "mavi", 111.8, Size.L, "matType1", "green", true, Gender.MALE, 4);		
		Product product3 = new Drink("tea", "çaykur", 12.6 , "11/02/2023", 1.5, 45);
		Product product4 = new Electronic("phone", "samsung", 4000, 200, false, 12.1, 21.0, 42);
		Product product5 = new Food("crisps", "ruffle", 8, "13/07/2023", 0.250, 4);
		Product product6 = new Furniture("table", "ikea",  200, 142.1, 1.30, "black", 4);
		Product product7 = new PersonalCare("shampoo", "blendax", 40, "For Dandruff Hair", "14/06/2024",3);
        Product product8 = new Clothes("Dress", "mavi", 250, Size.S, "matType1", "red", true, Gender.FEMALE, 10);
        Product product9 = new Clothes("Jeans", "mavi", 200, Size.M, "matType1", "blue", false, Gender.MALE, 13);	
        Product product10 = new Clothes("Jeans", "mavi", 200, Size.L, "matType1", "blue", false, Gender.MALE, 9);	
        Product product11 = new Clothes("Jeans", "mavi", 200, Size.S, "matType1", "blue", false, Gender.MALE, 14);	
        Product product12 = new Clothes("T_shirt", "mavi", 100, Size.L, "matType1", "blue", true, Gender.FEMALE, 15);
        Product product13 = new Clothes("T_shirt", "mavi", 100, Size.S, "matType1", "blue", true, Gender.FEMALE, 6);
        Product product14 = new PersonalCare("shampoo", "clear", 45, "For Dandruff Hair", "16/08/2024",15);
        Product product15 = new PersonalCare("shampoo", "haci sakir", 30, "For Dandruff Hair", "15/05/2024",20);
        Product product16 = new Furniture("wardrobe", "ikea",  1200, 90, 2, "black", 6);
        Product product17 = new Furniture("sofa", "ikea",  2000, 90, 1, "grey", 11);
        Product product18 = new Food("crisps", "lays", 9, "13/05/2023", 0.250, 7);
        Product product19 = new Food("ice-cream", "algida", 8.5, "13/07/2023", 0.190, 16);
        Product product20 = new Drink("coke", "coca cola", 11 , "16/08/2023", 1.5, 45);
        Product product21 = new Drink("water", "erikli", 1.5 , "19/03/2023", 0.5, 50);
        Product product22 = new Electronic("phone", "apple", 6000, 200, false, 5.1, 14.0, 11);


        bemp1.addProduct(product2);
        bemp1.addProduct(product3);
        bemp1.addProduct(product4);
        bemp1.addProduct(product5);
        bemp1.addProduct(product6);
        bemp1.addProduct(product7);
        bemp1.addProduct(product1);
        bemp1.addProduct(new Drink("coke", "pepsi", 10 , "17/09/2023", 2.5, 50));
        bemp1.addProduct(new Drink("soda", "scwhepps", 9 , "25/12/2023", 1.5, 30));
        bemp1.addProduct(new Drink("soda", "beypazari", 15 , "29/11/2022", 0.75, 40));
        bemp1.addProduct(new Drink("coffee", "nescafe", 17 , "24/07/2024", 2.5, 150));
        bemp1.addProduct(new Drink("soda", "sprite", 10 , "11/02/2023", 1.5, 70));
        bemp1.addProduct(new Drink("water", "pinar", 11 , "17/12/2023", 1.5, 100));


        bemp4.addProduct(product8);
        bemp4.addProduct(product9);
        bemp4.addProduct(product10);
        bemp4.addProduct(product14);
        bemp4.addProduct(product16);
        bemp4.addProduct(product17);
        bemp4.addProduct(product18);
        bemp4.addProduct(new Electronic("computer", "lenovo", 10000, 420, false, 14.2, 21.0, 10));
        bemp4.addProduct(new Electronic("computer", "dell", 11000, 430, false, 14.4, 21.1, 14));
        bemp4.addProduct(new Electronic("television", "phillips", 13000, 360, false, 25.1, 36.0, 19));
        bemp4.addProduct(new Electronic("phone", "xiaomi", 7300, 215, false, 5.2, 13.0, 11)); 
        bemp4.addProduct(new Electronic("console", "ps5", 17000, 440, false, 17.5, 22.0, 15));   
        bemp4.addProduct(new Electronic("console", "xboxs", 7000, 360, false, 11.2, 15.0, 60)); 
        bemp4.addProduct(new Electronic("phone", "lg", 4300, 230, false, 4.2, 11.0, 40));

        bemp7.addProduct(product19);
        bemp7.addProduct(product20);
        bemp7.addProduct(product11);
        bemp7.addProduct(product12);
        bemp7.addProduct(product6);
        bemp7.addProduct(product7);
        bemp7.addProduct(product1);
        bemp7.addProduct(new Clothes("Jeans", "levi's", 130, Size.M, "matType3", "green", false, Gender.FEMALE, 19));
        bemp7.addProduct(new Clothes("Jeans", "levi's", 140, Size.L, "matType3", "green", false, Gender.FEMALE, 29));
        bemp7.addProduct(new Clothes("Jacket", "wolfskin", 200, Size.M, "matType1", "black", false, Gender.MALE, 19));
        bemp7.addProduct(new Clothes("Jacket", "bershka", 230, Size.S, "matType2", "blue", false, Gender.MALE, 25));
        bemp7.addProduct(new Clothes("T_shirt", "wolfskin", 140, Size.L, "matType2", "gray", false, Gender.MALE, 30));
        bemp7.addProduct(new Clothes("T_shirt", "mango", 200, Size.M, "matType1", "yellow", false, Gender.FEMALE, 40));
        bemp7.addProduct(new Clothes("Dress", "bershka", 300, Size.M, "matType3", "blue", false, Gender.FEMALE, 35));


        bemp10.addProduct(product21);
        bemp10.addProduct(product22);
        bemp10.addProduct(product13);
        bemp10.addProduct(product15);
        bemp10.addProduct(product6);
        bemp10.addProduct(product7);
        bemp10.addProduct(product1);
        

        company.getAdministrator().connectBranches(branch1, branch2, 12.2);
        company.getAdministrator().connectBranches(branch2, branch3, 10.9);
        company.getAdministrator().connectBranches(branch2, branch4, 8.7);
        company.getAdministrator().connectBranches(branch1, branch4, 15.6);

		Customer customer1 = new Customer("ali", 29, Gender.MALE, "mali53", "rizeliA_li53" , branch1);
        Customer customer2 = new Customer("avni", 25, Gender.MALE, "avni.celik", "avn_1234" , branch1);
        Customer customer3 = new Customer("emir", 25, Gender.MALE, "emir.efe34", "E_efe1903" , branch1);
        branchmng1.addCustomer(customer1);
        branchmng1.addCustomer(customer2);
        branchmng1.addCustomer(customer3);

        Customer customer4 = new Customer("alper", 21, Gender.MALE, "alper", "Alper1234" , branch2);
        Customer customer5 = new Customer("tümay", 25, Gender.FEMALE, "tümay", "d21rgaw31." , branch2);
        Customer customer6 = new Customer("fatma", 22, Gender.FEMALE, "fatma", "dwar1231.q" , branch2);
        branchmng2.addCustomer(customer4);
        branchmng2.addCustomer(customer5);
        branchmng2.addCustomer(customer6);

        Customer customer7 = new Customer("necip", 19, Gender.MALE, "neco", "dsarr21wqe." , branch3);
        Customer customer8 = new Customer("oğuzhan", 30, Gender.MALE, "ozi", "mk21mw21." , branch3);
        Customer customer9 = new Customer("elif", 25, Gender.FEMALE, "Elif", "E_lif2431." , branch3);
        branchmng3.addCustomer(customer7);
        branchmng3.addCustomer(customer8);
        branchmng3.addCustomer(customer9);

        Customer customer10 = new Customer("Umut", 28, Gender.MALE, "umut", "umut2001." , branch4);
        Customer customer11 = new Customer("kerem", 31, Gender.MALE, "kerem", "13kere_m." , branch4);
        Customer customer12 = new Customer("burcu", 23, Gender.FEMALE, "burcu", "weqe1wda." , branch4);
        branchmng4.addCustomer(customer10);
        branchmng4.addCustomer(customer11);
        branchmng4.addCustomer(customer12);

        users.put(admin1.getUserName(), admin1);
        users.put(branchmng1.getUserName(), branchmng1);
        users.put(branchmng2.getUserName(), branchmng2);
        users.put(branchmng3.getUserName(), branchmng3);
        users.put(branchmng4.getUserName(), branchmng4);
        users.put(customer1.getUserName(), customer1);
        users.put(customer2.getUserName(), customer2);
        users.put(customer3.getUserName(), customer3);
        users.put(customer4.getUserName(), customer4);
        users.put(customer5.getUserName(), customer5);
        users.put(customer6.getUserName(), customer6);
        users.put(customer7.getUserName(), customer7);
        users.put(customer8.getUserName(), customer8);
        users.put(customer9.getUserName(), customer9);
        users.put(customer10.getUserName(), customer10);
        users.put(customer11.getUserName(), customer11);
        users.put(customer12.getUserName(), customer12);
        users.put(bemp1.getUserName(), bemp1);
        users.put(bemp2.getUserName(), bemp2);
        users.put(bemp3.getUserName(), bemp3);
        users.put(bemp4.getUserName(), bemp4);
        users.put(bemp5.getUserName(), bemp5);
        users.put(bemp6.getUserName(), bemp6);
        users.put(bemp7.getUserName(), bemp7);
        users.put(bemp8.getUserName(), bemp8);
        users.put(bemp9.getUserName(), bemp9);
        users.put(bemp10.getUserName(), bemp10);
        users.put(bemp11.getUserName(), bemp11);
        users.put(bemp12.getUserName(), bemp12);
    }
}