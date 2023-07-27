import java.util.Scanner;
/**
This program calculates gas mileage in miles per
gallon.
Ethan McFarland
May 5, 2023
*/
public class Mileage {
    public static void main(String[] args)
    {
    // Add your declaration and code here.
    Scanner input = new Scanner(System.in); // reads user input
    double miles; // miles
    double gallons; // gallons
    double MPG; // miles per gallon

    System.out.println("This program calculates gas mileage in miles per gallon.");

    // Prompt the user for miles
    // Read the user's miles
    System.out.println("Please enter your miles driven.");
    miles = Double.parseDouble(input.nextLine());

    // Prompt the user for gallons
    // Read the user's gallons
    System.out.println("Please enter your gallons used.");
    gallons = Double.parseDouble(input.nextLine());
    
    // Calculate miles per gallon
    // Print miles per gallon
    MPG = miles / gallons;
    System.out.println("Your miles per gallons is " + MPG  + " MPG.");
    input.close();
    }
}
