package src.test;

import java.util.Scanner;

public class Driver {
	public static void main (String args[]) {
		Scanner input = new Scanner(System.in);
		String inp;
		do {
	        System.out.println("----------------------------------------------------------------------");
    	    System.out.println("-------------------------- SYSTEM DRIVER -----------------------------");
			System.out.println("----------------------------------------------------------------------");
            System.out.println("-  1 - TEST SYSTEM WITH READY CASES  ---------------------------------");
            System.out.println("-  2 - MENU  ---------------------------------------------------------");
            System.out.println("-  0 - EXIT  ---------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
			System.out.print(" CHOICE: ");
			inp = input.nextLine();
			if(inp.equals("1")){			
				System.out.print("\033[H\033[2J");
				System.out.println("--------------------------- TEST0 ---------------------------");
				Test.test0();
				System.out.println("--------------------------- DONE ---------------------------");
			
				System.out.println("--------------------------- TEST1 ---------------------------");
				Test.test1();
				System.out.println("--------------------------- DONE ---------------------------");
			
				System.out.println("--------------------------- TEST2 ---------------------------");
				Test.test2();
				System.out.println("--------------------------- DONE ---------------------------");

				System.out.println("--------------------------- TEST3 ---------------------------");
				Test.test2();
				System.out.println("--------------------------- DONE ---------------------------");
			}
			else if(inp.equals("2")){
				System.out.println("--------------------------- TEST4 ---------------------------\n\n");
				Test.test4();
				System.out.println("\n\n--------------------------- DONE ---------------------------");		
			}
			else if(!inp.equals("0"))
				System.out.println(" INVALID OPERATION!");
		} while (!inp.equals("0"));
        input.close();
		
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------- SYSTEM TERMINATED... -------------------------");
        System.out.println("----------------------------------------------------------------------");
	}
	
}