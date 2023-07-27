import java.util.Scanner;
// Quiz #2
public class quiz1 {
	public static void main (String [] args)
	{
		// Identifier declarations
		Scanner myObj = new Scanner(System.in);
        int[] scores = new int[5];
        int score;
        char grade;
        String input;
        
        // Prompt user for score 1
        System.out.print("Enter test score 1: ");
        input = myObj.nextLine();
        scores[0] = Integer.valueOf(input);

        // Prompt user for score 2
        System.out.print("Enter test score 2: ");
        input = myObj.nextLine();
        scores[1] = Integer.valueOf(input);

        // Prompt user for score 3
        System.out.print("Enter test score 3: ");
        input = myObj.nextLine();
        scores[2] = Integer.valueOf(input);

        // Prompt user for score 4
        System.out.print("Enter test score 4: ");
        input = myObj.nextLine();
        scores[3] = Integer.valueOf(input);

        // Prompt user for score 5
        System.out.print("Enter test score 5: ");
        input = myObj.nextLine();
        scores[4] = Integer.valueOf(input);

        // Calculate test values and then display them
        score = calcAverage(scores);
        grade = determineGrade(score);
        System.out.print(
            "Your average grade is " + score + " and your letter grade is " + grade
            );

        myObj.close();
    }

    public static int calcAverage(int[] scores) {

        int sum = 0;
        int count = 0;

        for(int i = 0; i < scores.length; i++ ) {
            sum += scores[i];
            count ++;
        }

        return sum / count;
    }
    public static char determineGrade(int score) {

        if (90 < score && score <= 100) {
            return 'A';
        }
        else if (80 < score && score <= 89) {
            return 'B';
        }
        else if (70 < score && score <= 79) {
            return 'C';
        }
        else if (60 < score && score <= 69) {
            return 'D';
        }
        else {
            return 'F';
        }
    }
}