
import java.util.Scanner;
/**
This program demonstrates how numeric types and
operators behave in Java.
*/
public class NumericTypes
{
	public static void main (String [] args)
	{
		// TASK #2 Create a Scanner object here
		// (not used for alternate)
		// Identifier declarations
		final int NUMBER = 2 ; // Number of scores
		final int SCORE1 = 100; // First test score
		final int SCORE2 = 95; // Second test score
		final int BOILING_IN_F = 212; // Boiling temperature
		double fToC; // Temperature Celsius
		double average; // Arithmetic average
		String output; // Line of output

		// TASK #2 declare variables used here
		Scanner myObj = new Scanner(System.in);
		String firstName;
		String lastName;
		String fullName;

		// TASK #3 declare variables used here
		char firstInitial;

		// TASK #4 declare variables used here
		double diameter;
		double radius;
		double volume;

		// Find an arithmetic average.
		average = (SCORE1 + SCORE2) / NUMBER;
		output = SCORE1 + " and " + SCORE2 +
		" have an average of " + average;
		System.out.println(output);

		// Convert Fahrenheit temperature to Celsius.
		fToC = (BOILING_IN_F - 32) / 1.8;
		output = BOILING_IN_F + " in Fahrenheit is " +
		fToC + " in Celsius";
		System.out.println(output);
		System.out.println(); // To leave a blank line
		
		// ADD LINES FOR TASK #2 HERE
		// Prompt the user for first name
		// Read the user's first name
		System.out.println("Enter firstname");
		firstName = myObj.nextLine();
		
		// Prompt the user for last name
		// Read the user's last name
		System.out.println("Enter lastname");
		lastName = myObj.nextLine();  
		
		// Concatenate the user's first and last names
		// Print out the user's full name
		fullName = firstName + ' ' + lastName;
		System.out.println("User name is " + fullName);
		System.out.println(); // To leave a blank line

		// ADD LINES FOR TASK #3 HERE
		// Get the first character from the user's first name
		// Print out the user's first initial
		firstInitial = firstName.charAt(0);
		System.out.println("First iniftial is " + firstInitial);

		// Convert the user's full name to uppercase
		// Print out the user's full name in uppercase
		fullName = fullName.toUpperCase();
		System.out.println(fullName + " length is " + fullName.length()); 
		System.out.println(); // To leave a blank line

		// ADD LINES FOR TASK #4 HERE
		// Prompt the user for a diameter of a sphere
		// Read the diameter
		System.out.println("Enter diameter");
		diameter = Double.parseDouble(myObj.nextLine()); 

		// Calculate the radius
		radius = diameter / 2;

		// Calculate the volume
		volume = (4 / 3) * Math.PI * Math.pow(radius, 3);

		// Print out the volume
		System.out.println("volume is " + volume);

		myObj.close();
	}
}
