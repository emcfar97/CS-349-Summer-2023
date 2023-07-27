import java.util.Scanner; // Needed for the Scanner class
/**
This program allows the user to order a pizza.
*/
public class PizzaOrder
{
    public static void main (String[] args)
    {
        // Create a Scanner object to read input.
        Scanner keyboard = new Scanner (System.in);
        String firstName; // User's first name
        boolean discount = false; // Flag for discount
        int inches = 12; // Size of the pizza
        char crustType; // For type of crust
        String crust = "Hand-tossed"; // Name of crust
        double cost = 12.99; // Cost of the pizza
        final double TAX_RATE = .08; // Sales tax rate
        double tax; // Amount of tax
        char choice; // User's choice
        String input; // User input
        String toppings = "Cheese "; // List of toppings
        int numberOfToppings = 0; // Number of toppings
        
        // Prompt user and get first name.
        System.out.println("Welcome to Mike and " +
        "Diane's Pizza");
        System.out.print("Enter your first name: ");
        firstName = keyboard.nextLine();

        // Determine if user is eligible for discount by
        // having the same first name as one of the owners.
        // ADD LINES HERE FOR TASK #1
        if (firstName.equalsIgnoreCase("mike") || firstName.equalsIgnoreCase("diane")) {
            discount = true;
        }

        // Prompt user and get pizza size choice.
        System.out.println("Pizza Size (inches) Cost");
        System.out.println(" 10 $10.99");
        System.out.println(" 12 $12.99");
        System.out.println(" 14 $14.99");
        System.out.println(" 16 $16.99");
        System.out.println("What size pizza " +
        "would you like?");
        System.out.print("10, 12, 14, or 16 " +
        "(enter the number only): ");
        inches = keyboard.nextInt();

        // Set price and size of pizza ordered.
        // ADD LINES HERE FOR TASK #2
        if (inches == 10) {
            cost = 10.99;
            System.out.print("You chose a " + inches + " pizza.\n");
        }
        else if (inches == 12) {
            cost = 12.99;
            System.out.print("You chose a " + inches + " pizza.\n");
        }
        else if (inches == 14) {
            cost = 14.99;
            System.out.print("You chose a " + inches + " pizza.\n");
        }
        else if (inches == 16) {
            cost = 16.99;
            System.out.print("You chose a " + inches + " pizza.\n");
        }
        else {
            System.out.print(inches + " is not an available option, so a 12 inch pizza will selected as the default.\n");
        }

        // Consume the remaining newline character.
        keyboard.nextLine();
        // Prompt user and get crust choice.
        System.out.println("What type of crust " +
        "do you want? ");
        System.out.print("(H)Hand-tossed, " +
        "(T) Thin-crust, or " +
        "(D) Deep-dish " +
        "(enter H, T, or D): ");
        input = keyboard.nextLine();
        crustType = input.charAt(0);

        // Set user's crust choice on pizza ordered.
        // ADD LINES FOR TASK #3
        switch (Character.toUpperCase(crustType)) {
            case 'H':
                crust = "Hand-tossed";
                System.out.println("You chose the " + crust + " option.\n");
                break;
            
            case 'T':
                crust = "Thin-crust";
                System.out.println("You chose the " + crust + " option.\n");
                break;
            
            case 'D':
                crust = "Deep-dish";
                System.out.println("You chose the " + crust + " option.\n");
                break;
            
            default:
                System.out.println(crustType + " is not an available option, so Hand-tossed will be selected as the default.\n");
        }
        
        // Prompt user and get topping choices one at a time.
        System.out.println("All pizzas come with cheese.");
        System.out.println("Additional toppings are " +
        "$1.25 each, choose from:");
        System.out.println("Pepperoni, Sausage, " +
        "Onion, Mushroom");

        // If topping is desired,
        // add to topping list and number of toppings
        System.out.print("Do you want Pepperoni? (Y/N): ");
        input = keyboard.nextLine();
        choice = input.charAt(0);

        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + "Pepperoni ";
            }
        System.out.print("Do you want Sausage? (Y/N): ");
        input = keyboard.nextLine();
        choice = input.charAt(0);

        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + "Sausage ";
            }
        System.out.print("Do you want Onion? (Y/N): ");
        input = keyboard.nextLine();
        choice = input.charAt(0);

        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + "Onion ";
            }
        System.out.print("Do you want Mushroom? (Y/N): ");
        input = keyboard.nextLine();
        choice = input.charAt(0);

        if (choice == 'Y' || choice == 'y')
        {
            numberOfToppings += 1;
            toppings = toppings + "Mushroom ";
            }
        // Add additional toppings cost to cost of pizza.
        cost = cost + (1.25 * numberOfToppings);

        // Display order confirmation.
        System.out.println();
        System.out.println("Your order is as follows: ");
        System.out.println(inches + " inch pizza");
        System.out.println(crust + " crust");
        System.out.println(toppings);

        // Apply discount if user is eligible.
        // ADD LINES FOR TASK #4 HERE
        if (discount) {
            System.out.println("\nYou are eligble for a $2.00 discount!");
            cost -= 2;
        }

        // EDIT PROGRAM FOR TASK #5
        // SO ALL MONEY OUTPUT APPEARS WITH 2 DECIMAL PLACES
        System.out.printf("The cost of your order " +
        "is: $%.2f\n", cost);
        // Calculate and display tax and total cost.
        tax = cost * TAX_RATE;
        System.out.printf("The tax is: $%.2f\n", tax);
        System.out.printf("The total due is: $%.2f\n",
        (tax + cost));
        System.out.println("Your order will be ready " +
        "for pickup in 30 minutes.");

        keyboard.close();
    }
}