package src.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.product.Product;
import src.user.User;
import src.graph.DynamicBranchGraph;
import src.incommon.Gender;
import src.user.Administrator;
import src.user.BranchEmployee;
import src.user.BranchManager;
import src.user.Customer;

/**
 * Sales Constrol System
 */
public class SCSystem {
    /** All the companies that uses this system */
    private Company company; 

    /** All the registered users (key:username value:password) */
    // private HashMap<String, String> users;  
    private HashMap<String, User> registeredUsers = new HashMap<String, User>();  
    private HashMap<String, User> users = new HashMap<String, User>();  

    public SCSystem () {
        //! PSEUDO CODE FOR SCSYSTEM (update if necessary)

        // the system is kept in a binary file like database
        // upload the system informations (companies and users)
    
        // ----------- MENU ----------- (runs till user exit)
            // Take user enterance (username and password)
            
            // Check if the user exist (searching and password validation)
            // if it doesn't exist then provide a registeration 

            // Determine the related company that users use (another alternative can be find like taking the company name from the user)
            // Display the user information (Company name and username at the top)

            // Display action list (add/remove/display stuff)
                // three different list for admin, branch manager and employee

        // before exit, save the current status of system

        System.out.println("Creating company");
		this.company = new Company("BIG SUPERMARKET");
		System.out.println("\n" + company + "\n----------");
    	
    	System.out.println("Creating an administrator");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company);    	
    	System.out.println("\n" + admin1 + "\n----------");

    	System.out.println("Creating a branch");		
		Branch branch1 = new Branch("branch1");
		System.out.println("\n" + branch1 + "\n----------");
        admin1.addBranch(branch1);

        System.out.println("Creating a branch manager");		
		BranchManager branchmng1 = new BranchManager("tarık", 10, Gender.MALE, "tarık.mng", "1234567", branch1);
		System.out.println("\n" + branchmng1 + "\n----------");
        admin1.setBranchManager(branch1, branchmng1);

    	System.out.println("Company after the administrator creation.");    	
		System.out.println("\n" + company + "\n----------");

        System.out.println("------------------------------------------");
        DynamicBranchGraph branches = company.getBranches();
        for (Branch branch : branches) {
            System.out.println(branch);   
        }
        System.out.println("------------------------------------------");
        
        System.out.print("\033[H\033[2J");
        menu();
    }

    public void menu(){
        printMenuHeader();
        System.out.print(" Choice: ");

        Scanner input = new Scanner(System.in);
        String inp = input.nextLine();

        while(!inp.equals("0")){
            if(inp.equals("1")){
                User newUser = signup();
                registeredUsers.put(newUser.getUserName(), newUser);
            }
            else if(inp.equals("2"))
                signIn();
            else{
                System.out.print("\033[H\033[2J");
                System.out.println(" INVALID OPERATION!");
            }
            printMenuHeader();
            System.out.print(" Choice: ");
            inp = input.nextLine();
        }

        System.out.println("------------------------------------------------");
        System.out.println("------------ SYSTEM TERMINATED... --------------");
        System.out.println("------------------------------------------------");
        input.close();
    }

    private void printMenuHeader(){
        System.out.println("\n------------------------------------------------");
        System.out.println("------------- SALES CONTROL SYSTEM -------------");
        System.out.println("------------------------------------------------");
        System.out.printf("-  COMPANY : %-32s  -\n", company.getCompanyName());
        System.out.println("------------------------------------------------");
        System.out.printf("-  %-15s %27s -\n", "1 - Sign Up", " ");
        System.out.printf("-  %-15s %27s -\n", "2 - Sign In", " ");
        System.out.printf("-  %-15s %27s -\n", "0 - Exit", " ");
        System.out.println("------------------------------------------------");
    }

    public User signup(){
        System.out.println("\n------------------------------------------------");
        System.out.println("----------------- USER SIGN UP -----------------");
        System.out.println("------------------------------------------------");
        Scanner input = new Scanner(System.in);
        boolean flag;

        System.out.print(" Enter name: ");
        String name = input.nextLine();

        int age;
        do {
            System.out.print("\n Enter age: ");
            String a = input.nextLine();
            try {        
                age = Integer.parseInt(a);
                if(age < 1)            
                    System.err.println(" Invalid age format. Enter age in valid format!");
                else
                    break;        
            } catch (Exception e) {
                System.err.println(" Invalid age format. Enter age in valid format!");
                continue;
            }    
        } while (true);
        
        Gender gender = null;
        do {        
            flag = false;
            System.out.println("\n Male: M-m   Female: F-f   Other: O-o");
            System.out.print(" Enter gender: ");

            String gen = input.nextLine();
            if(gen.equals("M") || gen.equals("m"))
                gender = Gender.MALE;
            else if(gen.equals("F") || gen.equals("f"))
                gender = Gender.FEMALE;
            else if(gen.equals("O") || gen.equals("o"))
                gender = Gender.OTHER;
            else{
                System.err.println(" Invalid gender type.");
                flag = true;
            }
        } while (flag);
        
        String username;
        do {
            flag = false;        
            System.out.print("\n Enter username: ");
            username = input.nextLine();
            if(users.containsKey(username)){
                System.err.println(" This username is used. Enter a new username.");
                flag = true;
            }  
        } while (flag);
        
        String password;
        do {
            flag = false;        
            System.out.print("\n Enter password(Min 8 charachter): ");
            password = input.nextLine(); 
            if(password.length() < 8){
                System.err.println(" Weak password in length. Enter a new password.");
                flag = true;
            }  
        } while (flag);

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
                System.err.println(" Invalid user type.");
                flag = true;
            }
        } while (flag);

        System.out.println("\n Available branches: ");
        for (Branch branch : company.getBranches()) {
            System.out.printf(" %s\n", branch.getBranchName());            
        }

        User newUser = null;
        do {
            flag = true;
            System.out.print("\n Enter a branch name: ");        
            String branchName = input.nextLine();

            for (Branch branch : company.getBranches()) {
                if(branch.getBranchName().equals(branchName)){
                    if(userType.equals("E"))
                        newUser = new BranchEmployee(name, age, gender, username, password, branch);
                    else if(userType.equals("M"))
                        newUser = new BranchManager(name, age, gender, username, password, branch);
                    else if(userType.equals("C"))
                        newUser = new Customer(name, age, gender, username, password, branch);
                    flag = false;
                    break;
                }
            }
            System.err.println(" Nonexistent branch.");
        } while (flag);
        
        System.out.print("\033[H\033[2J");
        System.out.println("------------------------------------------------");
        System.out.print(newUser);
        System.out.println(" User is registered to system successfully.");
        System.out.println("------------------------------------------------");
        return newUser;
    }

    public void signIn(){
        System.out.println("\n------------------------------------------------");
        System.out.println("----------------- USER SIGN IN -----------------");
        System.out.println("------------------------------------------------");
        Scanner input = new Scanner(System.in);
        boolean flag;
        System.out.println("-  0 - Exit                                    -");
        System.out.println("------------------------------------------------");

        String username;
        do {
            flag = false;        
            System.out.print("\n Enter username: ");
            username = input.nextLine();
            if(username.equals("0")){
                flag = true;
                break;
            }
            if(!users.containsKey(username)){
                System.err.println(" There is no user in system with this username!");
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
                    flag = true;
                    break;
                }
                if(!users.get(username).getPassword().equals(password)){
                    System.err.println(" Wrong password!");
                    flag = true;
                }  
            } while (flag);    
        }

        System.out.print("\033[H\033[2J");
        if(!flag){
            User currUser = users.get(username);
            System.out.println("------------------------------------------------");
            System.out.printf(" %s signed in successfully.", username);
            System.out.println("------------------------------------------------");

            if(currUser instanceof Administrator)
                administratorMenu();
            else if(currUser instanceof BranchEmployee)
                branchEmployeeMenu((BranchEmployee)currUser);
            else if(currUser instanceof BranchManager)
                branchManagerMenu((BranchManager)currUser);
            else if(currUser instanceof Customer)
                customerMenu();
        }
    }

    public void administratorMenu(){

            // Display action list (add/remove/display stuff)
                // three different list for admin, branch manager and employee

        // before exit, save the current status of system
    }


    public void branchEmployeeMenu(BranchEmployee currUser){

        System.out.println("1 - Add Product");
        System.out.println("2 - Remove Product");
        System.out.println("3 - Add Requested Products");
        System.out.println("4 - Display Products");
        System.out.println("5 - Display Requested Products");
        System.out.println("0 - Exit");

        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        Scanner inputP = new Scanner(System.in);
        String name,brand,type,entryPrice;
        double entryPD;

        while(!in.equals("0")){

            if(in.equals("1")){
                System.out.println("Enter the product infos to be added.");

                System.out.println("Name:");
                name = inputP.nextLine();
                System.out.println("Brand:");
                brand = inputP.nextLine();
                System.out.println("Type:");
                type = inputP.nextLine();
                System.out.println("EntryPrice:");
                entryPrice = inputP.nextLine();
                entryPD = Double.parseDouble(entryPrice);

                currUser.addProduct(new Product(name,brand,type,entryPD));
            }
            else if(in.equals("2")){

                System.out.println("Enter the product name to be removed.");
                //isim ile silebilmemiz lazım
                // ama removeProductByName fonku yazmak lazım

            }
            else if(in.equals("3")){
                currUser.addRequestedProducts();
            }
            else if(in.equals("4")){
                currUser.displayProducts();
            }
            else if(in.equals("5")){
                currUser.displayRequestedProducts();
            }
            else{
                System.out.println("Invalid input.Try again.");
                in = input.nextLine();
            }
        }

    }

    public void branchManagerMenu(BranchManager currUser){

        System.out.println("1 - Set Branch");
        System.out.println("2 - Display Branches");
        System.out.println("3 - Display Branch Employees");
        System.out.println("4 - Display Products");
        System.out.println("5 - Add Customer");
        System.out.println("6 - Remove Customer");
        System.out.println("7 - Add Branch Employee");
        System.out.println("8 - Remove Branch Employee");
        System.out.println("0 - Exit");

        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        Scanner inputP = new Scanner(System.in);
        String inP ;

        while(!in.equals("0")){

            if(in.equals("1")){
                System.out.print("Enter the Branch Name to be set: ");
                inP = inputP.nextLine();
                currUser.setBranch(new Branch(inP));
            }
            else if(in.equals("2")){
                System.out.print("Enter the Company Name to display its branches. ");
                inP = inputP.nextLine();
                currUser.displayBranches(new Company(inP));
            }
            else if(in.equals("3")){
                currUser.displayBranchEmployees();
            }
            else if(in.equals("4")){
                currUser.displayProducts();
            } /*
            else if(in.equals("5")){

            }
            else if(in.equals("6")){

            }
            else if(in.equals("7")){

            }
            else if(in.equals("8")){

            }*/
            else{
                System.out.println("Invalid input.Try again.");
                in = input.nextLine();
            }
        }


    }


    public void customerMenu(){

    }
}