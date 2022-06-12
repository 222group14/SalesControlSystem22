package src.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.product.Product;
import src.user.User;
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
		Company company = new Company("company1");
		System.out.println("\n" + company + "\n----------");
    	
    	System.out.println("Creating an administrator");
		Administrator admin1 = new Administrator("admin1", 30, Gender.FEMALE, "ADMIN" , "adminsc." , company);    	
    	System.out.println("\n" + admin1 + "\n----------");

    	System.out.println("Creating a branch");		
		Branch branch1 = new Branch("branch1");
		System.out.println("\n" + branch1 + "\n----------");

        admin1.addBranch(branch1);

    	System.out.println("Company after the administrator creation.");    	
		System.out.println("\n" + company + "\n----------");

        System.out.println("------------------------------------------");
        List<Branch> branches = company.getBranches();
        System.out.println(branches.get(0));
        System.out.println("------------------------------------------");

        menu();
    }

    public void menu(){
        System.out.println("1 - Sign Up");
        System.out.println("2 - Sign In");
        System.out.println("0 - Exit");

        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        while(!in.equals("0")){
            if(in.equals("1")){
                Customer customer = signup();
                users.put(customer.getUserName(), customer);
            }
            else if(in.equals("2"))
                signIn();
            else{
                System.out.println("Invalid option.");
            }
            in = input.nextLine();
        }
        System.out.println("Terminated...");
        input.close();
    }

    public Customer signup(){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = input.nextLine();

        System.out.println("Enter age: ");
        String a = input.nextLine();
        int age = Integer.parseInt(a);

        //LOOP NEEDED
        System.out.println("Male: M\nFemale: F\nOther: O");
        System.out.println("Enter gender: ");
        Gender gender = null;
        String gen = input.nextLine();
        if(gen.equals("M"))
            gender = Gender.MALE;
        else if(gen.equals("F"))
            gender = Gender.FEMALE;
        else if(gen.equals("O"))
            gender = Gender.OTHER;
        else
            System.out.println("Invalid gender.");
        
        //LOOP NEEDED
        System.out.println("Enter username: ");
        String username = input.nextLine();
        if(!users.containsKey(username))
            ;
        else
            username = " ";

        System.out.println("Enter password: ");
        String password = input.nextLine();        

        System.out.println("Available branches");
        for (Branch branch : company.getBranches()) {
            System.out.println(branch.getBranchName() + "\n");            
        }

        do {
            
            System.out.println("Enter : ");        
            String branchName = input.nextLine();

            for (Branch branch : company.getBranches()) {
                if(branch.getBranchName().equals(branchName)){        
                    input.close();
                    return new Customer(name, age, gender, username, password, branch);
                }
            }    
        } while (true);
    }

    public void signIn(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = input.nextLine();

        System.out.println("Enter password: ");
        String password = input.nextLine();
 
        input.close();
        User currUser = users.get(username);
        if(currUser.getPassword().equals(password)){
            if(currUser instanceof Administrator)
                administratorMenu();
            else if(currUser instanceof BranchEmployee)
                branchEmployeeMenu((BranchEmployee)currUser);
            else if(currUser instanceof BranchManager)
                branchManagerMenu((BranchManager)currUser);
            else if(currUser instanceof Customer)
                customerMenu();
        }
        else
            System.out.println("Invalid user.");
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