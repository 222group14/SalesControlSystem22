package src.test;

import java.util.Scanner;

public class Driver {
	public static void main (String args[]) {
		Scanner input = new Scanner(System.in);
		String inp, inp2;
		do {
			System.out.print("\033[H\033[2J");
	        System.out.println("\n----------------------------------------------------------------------");
    	    System.out.println("-------------------------- SYSTEM DRIVER -----------------------------");
			System.out.println("----------------------------------------------------------------------");
            System.out.println("-  1 - TEST SYSTEM WITH READY CASES  ---------------------------------");
            System.out.println("-  2 - MENU  ---------------------------------------------------------");
            System.out.println("-  0 - EXIT  ---------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------");
			System.out.print(" CHOICE: ");
			inp = input.nextLine();
			if(inp.equals("1")){			
				do {
					System.out.println("\n----------------------------------------------------------------------");
					System.out.println("-  1 - TEST ALL METHODS            -----------------------------------");
					System.out.println("-  2 - TEST GRAPH IMPLEMENTATIONS  -----------------------------------");
					System.out.println("-  3 - TEST BUYING SYSTEM          -----------------------------------");
					System.out.println("-  4 - PERFORMANCE TESTS           -----------------------------------");
					System.out.println("-  0 - EXIT                        -----------------------------------");
					System.out.println("----------------------------------------------------------------------");
					System.out.print(" CHOICE: ");
					inp2 = input.nextLine();
					if(inp2.equals("1")){
						System.out.println("----------------------------- TEST 1 ---------------------------------");
						Test.test0();
						System.out.println("------------------------------ DONE ----------------------------------");
					}
					else if(inp2.equals("2")){
						System.out.println("----------------------------- TEST 2 ---------------------------------");
						Test.test1();
						System.out.println("------------------------------ DONE ----------------------------------");
					}
					else if(inp2.equals("3")){
						System.out.println("----------------------------- TEST 3 ---------------------------------");
						Test.test2();
						System.out.println("------------------------------ DONE ----------------------------------");
					}
					else if(inp2.equals("4")){
						System.out.println("----------------------------- TEST 4 ---------------------------------");
						Test.test3();
						System.out.println("------------------------------ DONE ----------------------------------");
					}
					else if(!inp2.equals("0")){			
						System.out.print("\033[H\033[2J");
						System.out.println(" INVALID OPERATION!");
					}
					else
						System.out.print("\033[H\033[2J");
				} while (!inp2.equals("0"));
			}
			else if(inp.equals("2")){
				Test.test4();
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