import javax.swing.JOptionPane;

public class Circle {

    double radius;
    double PI = 3.14159;

    public static void main(String[] args) {
        
        String input; 
        double radius;

        // get radius from user
        input = JOptionPane.showInputDialog("Enter the radius");
        radius = Double.parseDouble(input);

        // create an object of Circle class
        Circle circle = new Circle(radius);

        // show circle computations
        JOptionPane.showMessageDialog(
            null, 
            "Area: " + circle.getArea() + 
            "\nDiameter: " + circle.getDiameter() + 
            "\nCircumference: " + circle.getCircumference()
            );
    }
    /** Contstructor with no parameters */
    public Circle() {
        super();        
    }
    /** Contstructor with parameters */
    public Circle(double r) {
        super();
        this.radius = r;
    }
    /** mutator or setter for radius field */
    public void setRadius(double r) {
        this.radius = r;        
    }
    public double getRadius() {
        return this.radius;
    }
    public double getArea() {
        return this.PI * this.radius * this.radius;
    }
    public double getDiameter() {
        return radius * 2;
    }
    public double getCircumference() {
        return 2 * this.PI * this.radius;
    }
}
