import java.util.Scanner;

public class Average {
    
    private int[] data = new int[2];
    private double mean;

    public Average() {
        
        // Create a Scanner object to read from the keyboard
        Scanner keyboard = new Scanner (System.in);

        for (int i = 0; i < 2; i++) {
            System.out.println("Enter number " + (i+1));
            this.data[i] = keyboard.nextInt();
        }
        selectionSort();
        calculateMean();
        keyboard.close();
    }
    public void calculateMean() {
        
        int sum = 0;
        
        for (int i = 0; i < this.data.length; i++) {
            sum += this.data[i];
        }
        this.mean = sum / this.data.length;
        
    }
    public String toString() {
        
        return "[" + this.data[0] + ", " + this.data[1] + "]\nMean: " + this.mean;
    }
    public void selectionSort() {

        int n = this.data.length;
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (this.data[j] < this.data[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = this.data[min_idx];
            this.data[min_idx] = this.data[i];
            this.data[i] = temp;
        }
    }
}