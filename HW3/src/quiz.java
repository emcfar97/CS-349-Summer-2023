import java.util.Scanner;
// Quiz #1
public class quiz {
    
	public static void main (String [] args)
	{
        
		// Identifier declarations
		Scanner myObj = new Scanner(System.in);
        int[] integers = {};
        int input;
        // int sum = 0;
        
        while (true){
	    	System.out.print("Enter a series of positive integers (end with -1):");
            input = Integer.valueOf(myObj.nextLine());

            if (input == -1) {
                // for (int i : integers) {
                //     sum += i;                    
                // }
	    	    System.out.print("You entered " + integers + ".\n" +
                "Sum of all positive integers is " + integers.);
                break;
            }
            else if (input < -1) {
	    	    System.out.print("You must enter a positive integer or -1." );
            }
            else {
                integer + input;
            }

        }
        myObj.close();
    }
}
