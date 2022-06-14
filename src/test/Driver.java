package src.test;

public class Driver {
	public static void main (String args[]) {
		
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

		// Tests the menu
	 	System.out.println("--------------------------- TEST4 ---------------------------\n\n");
		Test.test4();
		System.out.println("\n\n--------------------------- DONE ---------------------------");
	}
}