 package src.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

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
        
        BranchEmployee bemp1 = new BranchEmployee("sevim", 24, Gender.FEMALE, " Sevim7 " ,  "Sevim2001." , branch1);
        branchmng1.addBranchEmployee(bemp1);

        BranchEmployee bemp2 = new BranchEmployee("osman", 24, Gender.MALE, "Osmanpzr" , " Osman3002." ,branch2);
        branchmng2.addBranchEmployee(bemp2);

        BranchEmployee bemp3 = new BranchEmployee("asli", 25, Gender.FEMALE, "asliii", "A_sli3106." , branch2);
        branchmng2.addBranchEmployee(bemp3);

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
        input.close();
        System.out.print("\033[H\033[2J");
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------ SYSTEM TERMINATED... --------------------");
        System.out.println("------------------------------------------------------------");
    }

    private User signUp(){
        System.out.print("\033[H\033[2J");
        System.out.println("\n------------------------------------------------------------");
        System.out.println("----------------------- USER SIGN UP -----------------------");
        System.out.println("------------------------------------------------------------");
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
                System.err.println(" Invalid user type.");
                flag = true;
            }
        } while (flag);

        User newUser = null;
        Branch branch = chooseBranch();
        if(userType.equals("E"))
            newUser = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
        else if(userType.equals("M"))
            newUser = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
        else if(userType.equals("C"))
            newUser = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);

        System.out.print("\033[H\033[2J");
        System.out.print("------------------------------------------------------------");
        System.out.print(newUser);
        System.out.println(" Registration request received successfully.");
        System.out.println("------------------------------------------------------------");
        return newUser;
    }

    private void signIn(){
        System.out.print("\033[H\033[2J");
        System.out.println("\n------------------------------------------------------------");
        System.out.println("----------------------- USER SIGN IN -----------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("-  0 - Exit  -----------------------------------------------");
        System.out.println("------------------------------------------------------------");
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
                    System.out.print("\033[H\033[2J");
                    flag = true;
                    break;
                }
                if(users.containsKey(username)){
                    if(!users.get(username).getPassword().equals(password)){
                        System.err.println(" Wrong password!");
                        flag = true;
                    }
                }
                else{
                    if(registeredUsers.containsKey(username)){
                        flag = true;
                        if(!registeredUsers.get(username).getPassword().equals(password))
                            System.err.println(" Wrong password!");
                        else{
                            System.out.print("\033[H\033[2J");
                            System.out.println("------------------------------------------------------------");
                            System.err.println(" Your registration must be approved by Administrator! ");
                            System.out.println("------------------------------------------------------------");
                            break;
                        }
                    }
                }
            } while (flag);    
        }

        if(!flag){
            System.out.print("\033[H\033[2J");
            User currUser = users.get(username);
            System.out.println("------------------------------------------------------------");
            System.out.printf(" %s signed in successfully.\n", username);
            System.out.println("------------------------------------------------------------");

            if(currUser instanceof Administrator)
                administratorMenu(company.getAdministrator());
            else if(currUser instanceof BranchEmployee)
                branchEmployeeMenu((BranchEmployee)currUser);
            else if(currUser instanceof BranchManager)
                branchManagerMenu((BranchManager)currUser);
            else if(currUser instanceof Customer)
                customerMenu();
        }
    }

    private void administratorMenu(Administrator administrator){
        Scanner input = new Scanner(System.in);
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
                    System.out.println(" There is no registration request.");
                else{
                    do {
                        flag = false;
                        System.out.println("\n 1 - Approve All\n 2 - Approve Individually\n 3 - Reject All");
                        System.out.print(" Choice: ");
                        inp2 = input.nextLine();
                        if(inp2.equals("1")){
                            users.putAll(registeredUsers);
                            registeredUsers.clear();
                            System.out.println(" All registration requests are approved successfully.");
                        }
                        else if(inp2.equals("2")){
                            Set<String> usernames = registeredUsers.keySet();
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
                                        System.out.println(" Registration is approved.");
                                    }
                                    else if(inp3.equals("2")){
                                        registeredUsers.remove(user.getUserName());
                                        System.out.println(" Registration is rejected.");
                                    }else if(inp3.equals("3"))
                                        System.out.println(" User will be examined later.");
                                    else{
                                        System.err.println(" INVALID OPERATION!");
                                        flag2 = true;
                                    }
                                } while (flag2);
                            }
                        }
                        else if(inp2.equals("3")){
                            registeredUsers.clear();
                            System.out.println(" All registration requests are rejected successfully.");
                        }else{
                            System.out.print("\033[H\033[2J");
                            System.err.println(" INVALID OPERATION!");
                            flag = true;
                        }
                    } while (flag);             
                    System.out.print("\033[H\033[2J");
                }
            }
            else if(inp.equals("2")){
                System.out.print("\n Enter branch name: ");
                inp = input.nextLine();                    
                Branch newBranch = new Branch(inp);
                System.out.println("\n Enter informations of branch manager:");
                User usr = createUser();
                BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), newBranch);
                administrator.setBranchManager(newBranch, bManager);
                administrator.addBranch(newBranch);
                users.put(bManager.getUserName(), bManager);
                System.out.println(" Branch added successfully.");
            }
            else if(inp.equals("3")){
                DynamicBranchGraph branches = company.getBranches();
                if(branches.getNumV() == 0)
                    System.out.println(" There is no branch at company to remove!");
                else{
                    Branch choosenBranch = chooseBranch();
                    if(branches.removeBranch(branches.getID(choosenBranch))){        
                        System.out.println(" Branch removed successfully.");
                    }
                }
            }
            else if(inp.equals("4")){
                Branch branch = chooseBranch();
                if(branch != null){
                    System.out.println("\n Enter informations of branch manager:");
                    User usr = createUser();
                    BranchManager bManager = new BranchManager(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), branch);
                    users.remove(branch.getBranchManager().getUserName());
                    administrator.setBranchManager(branch, bManager);
                    users.put(bManager.getUserName(), bManager);
                    System.out.println(" Branch Manager " + bManager.getName() + " is set successfully. ");
                }
                else
                    System.err.println(" There is no branch to add branch manager!");
            }
            else if(inp.equals("5"))
                administrator.displayBranches();
            else if(inp.equals("6"))
                administrator.displayBranchManagers();
            else if(inp.equals("7"))
                administrator.displayEmployees();
            else if(inp.equals("8"))
                administrator.displayCustomers();
            else{
                System.out.println(" INVALID OPERATION!");
            }

            printMenuHeader("admin");
            System.out.print(" Choice: ");
            inp = input.nextLine();
        }
        System.out.print("\033[H\033[2J");
    }

    private void branchEmployeeMenu(BranchEmployee currEmployee){
        Scanner input = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        printMenuHeader("bemployee");
        System.out.print(" Choice: ");
        String inp = input.nextLine();
        
        while(!inp.equals("0")){
            System.out.print("\033[H\033[2J");
            if(inp.equals("1"))
                currEmployee.addProduct(createProduct());
            else if(inp.equals("2")){
                System.out.print(" Enter the product name to be removed: ");
                currEmployee.removeProduct(input.nextLine());
            }
            else if(inp.equals("3"))
                currEmployee.addRequestedProducts();
            else if(inp.equals("4"))
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
        System.out.print("\033[H\033[2J");
    }

    private void branchManagerMenu(BranchManager currManager){
        Scanner input = new Scanner(System.in);
        boolean flag;

        System.out.print("\033[H\033[2J");
        printMenuHeader("bmanager");
        System.out.print(" Choice: ");
        String inp = input.nextLine();
        
        while(!inp.equals("0")){
            System.out.print("\033[H\033[2J");
            if(inp.equals("1")){
                System.out.println("\n Enter name of being added branch employee: ");
                User usr = createUser();
                BranchEmployee bEmployee = new BranchEmployee(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                currManager.addBranchEmployee(bEmployee);
                users.put(bEmployee.getUserName(), bEmployee);
            }
            else if(inp.equals("2")){
                currManager.displayBranchEmployees();
                var branchEmployees = currManager.getBranch().getBranchEmployees();
                if(branchEmployees.isEmpty())
                    System.out.println("\n There is no branch employee to remove!");
                else{
                    String inp2;
                    do{
                        flag = true;
                        System.out.print("\n Enter name of being removed branch employee: ");
                        inp2 = input.nextLine();
                        for (BranchEmployee employee : branchEmployees) {
                            if(employee.getName().equals(inp2)){
                                currManager.removeBranchEmployee(employee);
                                users.remove(employee.getUserName());
                                flag = false;
                                break;
                            }
                        }
                        System.err.println(" Enter existing branch employee!");  
                    } while(flag);
                }
            }
            else if(inp.equals("3")){
                System.out.println("\n Enter name of being added customer: ");
                User usr = createUser();
                Customer newCustomer = new Customer(usr.getName(), usr.getAge(), usr.getGender(), usr.getUserName(), usr.getPassword(), currManager.getBranch());
                currManager.addCustomer(newCustomer);
                users.put(newCustomer.getUserName(), newCustomer);
            }
            else if(inp.equals("4")){
                System.out.println(currManager.getBranch().getStringCustomers());
                var customers = currManager.getBranch().getCustomers();
                if(customers.size() == 0)
                    System.out.println(" There is no customer to remove!");
                else{
                    String inp2;
                    do{
                        flag = true;
                        System.out.print("\n Enter name of being removed customer: ");
                        inp2 = input.nextLine(); 
                        for (Customer customer : customers) {
                            if(customer.getName().equals(inp2)){
                                currManager.removeCustomer(customer);
                                users.remove(customer.getUserName());
                                flag = false;
                                break;
                            }
                        }
                        System.err.println(" Enter existing customer!");  
                    }while(flag);
                }
            }
            else if(inp.equals("5"))
                currManager.displayBranchEmployees();
            else if(inp.equals("6"))
                currManager.displayProducts();
            else{
                System.out.println(" INVALID OPERATION!");
            }
            printMenuHeader("bmanager");
            System.out.print(" Choice: ");
            inp = input.nextLine();
        }
        System.out.print("\033[H\033[2J");
    }

    private void customerMenu(){

    }
    
    private void printMenuHeader(String menu){
        if(menu.equals("main")){
            System.out.println("\n------------------------------------------------------------");
            System.out.println("------------------- SALES CONTROL SYSTEM -------------------");
            System.out.println("------------------------------------------------------------");
            System.out.printf("-  COMPANY : %-44s  -\n", company.getCompanyName());
            System.out.println("------------------------------------------------------------");
            System.out.println("-  1 - Sign Up  --------------------------------------------");
            System.out.println("-  2 - Sign In  --------------------------------------------");
            System.out.println("-  0 - Exit     --------------------------------------------");
            System.out.println("------------------------------------------------------------");
        }
        else if(menu.equals("admin")){
            System.out.println("\n------------------------------------------------------------");
            System.out.println("-------------------- ADMINISTRATOR MENU --------------------");
            System.out.println("------------------------------------------------------------");
            System.out.printf("-  %-56s-\n", "1 - Approve Waiting Registrations");
            System.out.printf("-  %-56s-\n", "2 - Add Branch");
            System.out.printf("-  %-56s-\n", "3 - Remove Branch");
            System.out.printf("-  %-56s-\n", "4 - Set Branch Manager");
            System.out.printf("-  %-56s-\n", "5 - Display Branches");
            System.out.printf("-  %-56s-\n", "6 - Display Branch Managers");
            System.out.printf("-  %-56s-\n", "7 - Display Branch Employees");
            System.out.printf("-  %-56s-\n", "8 - Display Customers");
            System.out.printf("-  %-56s-\n", "0 - Exit");
            System.out.println("------------------------------------------------------------");
        }
        else if(menu.equals("bemployee")){
            System.out.println("\n------------------------------------------------------------");
            System.out.println("------------------- BRANCH EMPLOYEE MENU -------------------");
            System.out.println("------------------------------------------------------------");
            System.out.printf("-  %-56s-\n", "1 - Add Product");
            System.out.printf("-  %-56s-\n", "2 - Remove Product");
            System.out.printf("-  %-56s-\n", "3 - Add Requested Products");
            System.out.printf("-  %-56s-\n", "4 - Display Products");
            System.out.printf("-  %-56s-\n", "5 - Display Requested Products");
            System.out.printf("-  %-56s-\n", "0 - Exit");
            System.out.println("------------------------------------------------------------");
        }
        else if(menu.equals("bmanager")){
            System.out.println("\n------------------------------------------------------------");
            System.out.println("-------------------- BRANCH MANAGER MENU -------------------");
            System.out.println("------------------------------------------------------------");
            System.out.printf("-  %-56s-\n", "1 - Add Branch Employee");
            System.out.printf("-  %-56s-\n", "2 - Remove Branch Employee");
            System.out.printf("-  %-56s-\n", "3 - Add Customer");
            System.out.printf("-  %-56s-\n", "4 - Remove Customer");
            System.out.printf("-  %-56s-\n", "5 - Display Branch Employees");
            System.out.printf("-  %-56s-\n", "6 - Display Products");
            System.out.printf("-  %-56s-\n", "0 - Exit");
            System.out.println("------------------------------------------------------------");
        }
        else if(menu.equals("customer")){
            System.out.println("\n------------------------------------------------------------");
            System.out.println("----------------------- CUSTOMER MENU ----------------------");
            System.out.println("------------------------------------------------------------");
            System.out.printf("-  %-56s-\n", "1 - Operation");
            System.out.printf("-  %-56s-\n", "0 - Exit");
            System.out.println("------------------------------------------------------------");
        }
    }

    private User createUser(){
        Scanner input = new Scanner(System.in);
        String name, username, password;
        Gender gender = null;
        int age;        
        boolean flag;

        System.out.print(" Enter name: ");
        name = input.nextLine();

        do {
            System.out.print("\n Enter age: ");
            String a = input.nextLine();
            try {        
                age = Integer.parseInt(a);
                if(age < 1)            
                    System.err.println(" Age must be positive value!");
                else
                    break;        
            } catch (Exception e) {
                System.err.println(" Invalid age format. Enter age in valid format!");
            }    
        } while (true);
        
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
        
        do {
            flag = false;        
            System.out.print("\n Enter username: ");
            username = input.nextLine();
            if(users.containsKey(username)){
                System.err.println(" This username is used. Enter a new username.");
                flag = true;
            }  
        } while (flag);
        
        do {
            flag = false;        
            System.out.print("\n Enter password(Min 8 charachter): ");
            password = input.nextLine(); 
            if(password.length() < 8){
                System.err.println(" Weak password in length. Enter a new password.");
                flag = true;
            }  
        } while (flag);
        return new User(name, age, gender, username, password);
    }
  
    private Product createProduct(){
        Scanner input = new Scanner(System.in);
        String name, brand, type;
        double entryPrice;
        boolean flag;

        System.out.print("\n Enter name: ");
        name = input.nextLine();

        System.out.print("\n Enter brand: ");
        brand = input.nextLine();

        ArrayList<String> pTypes = company.getProductTypes();
        for (int i = 0; i < pTypes.size(); i++)
            System.out.println(" " + pTypes.get(i));
        do {        
            flag = false; 
            System.out.print("\n Enter type: ");
            type = input.nextLine();
            if(!pTypes.contains(type)){
                System.err.println(" Invalid type.");
                flag = true;
            }
        } while (flag);
        
        do {
            System.out.print("\n Enter entry price: ");
            String d = input.nextLine();
            try {        
                entryPrice = Double.parseDouble(d);
                if(entryPrice < 0.0)            
                    System.err.println(" Entry price must be positive value!");
                else
                    break;        
            } catch (Exception e) {
                System.err.println(" Invalid price format. Enter price in valid format!");
            }    
        } while (true);
        return new Product(name, brand, type, entryPrice);
    }

    private Branch chooseBranch(){
        Scanner input = new Scanner(System.in);
        boolean flag;
        String branchName;

        System.out.println("\n Existing Branches");
        company.getAdministrator().displayBranches();

        DynamicBranchGraph branches = company.getBranches();
        if(branches.getNumV() == 0)
            System.out.println(" There is no branch at company.");
        else{    
            do {
                flag = true;        
                System.out.print("\n Enter branch name: ");
                branchName = input.nextLine();                    
                for (Branch branch : branches) {
                    if(branchName.equals(branch.getBranchName()))
                        return branch;
                }
                if(flag)
                    System.err.println(" Enter existent branch name!");
            } while (flag);
        }
        return null;
    }
}