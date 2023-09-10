/**
 * Name: Adam Zieman (zieman)
 * Course: CSCI 241 - Computer Science I
 * Assignment: 4
 * 
 * Project/Class Description:
 * This program draws 3 circles in a window and
 * allows the user to click there.  It will 
 * report the click location each time, plus
 * draw an appropriately-colored circle depending
 * on how many circles intersect there.
 * 
 * Known bugs:
 * none
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CircleGUI extends GraphicsProgram
{
    private static Circle leftCircle;  // the left-most circle
    private static Circle topCircle;   // to top circle
    private static Circle rightCircle; // the right-most circle
    /**
     * Creates and displays 3 circles in a clickable frame.
     * It also registers a mouse handler.
     */
    public void startGUI()
    {      
        // Get leftCircle's information for drawing
        int radius = leftCircle.getRadius();
        int xUpperLeft = leftCircle.getCenterX() - radius;
        int yUpperLeft = leftCircle.getCenterY() - radius;
        int diameter = radius * 2;

        // Draw the first circle with thick lines
        GOval left = new GOval(xUpperLeft-1, yUpperLeft-1, 
                diameter+1,diameter+1);
        add(left);
        left = new GOval(xUpperLeft, yUpperLeft, 
            diameter,diameter);
        add(left);
        left = new GOval(xUpperLeft+1, yUpperLeft+1, 
            diameter-1,diameter-1);
        add(left);

        // Get topCircle's information for drawing
        radius = topCircle.getRadius();
        xUpperLeft = topCircle.getCenterX() - radius;
        yUpperLeft = topCircle.getCenterY() - radius;
        diameter = radius * 2;

        // Draw the second circle with thick lines
        GOval top = new GOval(xUpperLeft-1, yUpperLeft-1, 
                diameter,diameter);
        add(top);
        top = new GOval(xUpperLeft, yUpperLeft, 
            diameter,diameter);
        add(top);
        top = new GOval(xUpperLeft+1, yUpperLeft+1, 
            diameter,diameter);
        add(top);

        // Get rightCircle's information for drawing
        radius = rightCircle.getRadius();
        xUpperLeft = rightCircle.getCenterX() - radius;
        yUpperLeft = rightCircle.getCenterY() - radius;
        diameter = radius * 2;

        // Draw the third circle with thick lines
        GOval right = new GOval(xUpperLeft-1, yUpperLeft-1, 
                diameter,diameter);
        add(right);
        right = new GOval(xUpperLeft, yUpperLeft, 
            diameter,diameter);
        add(right);
        right = new GOval(xUpperLeft+1, yUpperLeft+1, 
            diameter,diameter);
        add(right);

        addMouseListeners();
    }     

    /**
     * the MouseHandler class "listens" for mouse clicks from the user.
     */    
    public void mouseClicked (MouseEvent e)
    {
        // Find the location where the mouse was clicked
        int x = e.getX();
        int y = e.getY();

        // new variables to hold radius of each circle
        int leftRadius = leftCircle.getRadius();
        int rightRadius = rightCircle.getRadius();
        int topRadius = topCircle.getRadius();

        // new variables to set if inside each circle
        boolean insideLeft = false;
        boolean insideRight = false;
        boolean insideTop = false;

        // retrieve the center (x,y) locations
        int centerLeftX = leftCircle.getCenterX();
        int centerLeftY = leftCircle.getCenterY();
        int centerRightX = rightCircle.getCenterX();
        int centerRightY = rightCircle.getCenterY();
        int centerTopX = topCircle.getCenterX();
        int centerTopY = topCircle.getCenterY();

        /*** Use distance formula to find distance from mouse click to 
         * center of left circle and save in a new variable
         ***/
        // d = sqrt(  Math.pow((x2-x1), 2)  +   Math.pow((y2-y1), 2)  )
        double distanceLeftCenter = Math.sqrt(Math.pow((x - centerLeftX), 2) + Math.pow((y - centerLeftY), 2));

        /*** Find distance from mouse click to center of right circle
         * and save in a new variable
         ***/
        double distanceRightCenter = Math.sqrt(Math.pow((x - centerRightX), 2) + Math.pow((y - centerRightY), 2));

        /*** Find distance from mouse click to center of top circle
         * and save in a new variable
         ***/
        double distanceTopCenter = Math.sqrt(Math.pow((x - centerTopX), 2) + Math.pow((y - centerTopY), 2));

        /*** Set the three boolean variables
         * (insideLeft, insideTop and insideRight,
         * already declared above)
         * depending on the distance between the
         * click location and each circle's center.
         ***/
        if (leftRadius > distanceLeftCenter) {
            insideLeft = true;
        }

        if (rightRadius > distanceRightCenter) {
            insideRight = true;
        }

        if (topRadius > distanceTopCenter) {
            insideTop = true;
        }

        /*** these lines run when the click location
         * is inside all 3 circles
         ***/  
        if (insideLeft == true && insideRight == true && insideTop == true) {
            System.out.println("(" + x + "," + y + 
                ") is inside all three circles");
            GOval red = new GOval(x-2,y-2,5,5);
            red.setFillColor(Color.RED);
            red.setFilled(true);
            add(red);
        }

        /*** these lines run when the click location
         * is inside exactly 2 of the circles
         ***/
        else if (
        insideLeft == true && insideRight == true && insideTop != true ||
        insideLeft == true && insideRight != true && insideTop == true ||
        insideLeft != true && insideRight == true && insideTop == true) {
            System.out.println("(" + x + "," + y + 
                ") is inside exactly two circles");
            GOval blue = new GOval(x-2,y-2,5,5);
            blue.setFillColor(Color.YELLOW);
            blue.setFilled(true);
            add(blue);
        }

        /*** these lines run when the click location
         * is inside exactly 1 of the circles
         ***/
        else if (
        insideLeft == true && insideRight != true && insideTop != true ||
        insideLeft != true && insideRight == true && insideTop != true ||
        insideLeft != true && insideRight != true && insideTop == true) {
            System.out.println("(" + x + "," + y + 
                ") is inside exactly one circle");
            GOval green = new GOval(x-2,y-2,5,5);
            green.setFillColor(Color.GREEN);
            green.setFilled(true);
            add(green);
        }

        /*** these lines run when the click location
         * is outside all of the circles
         ***/
        else {
            System.out.println("(" + x + "," + y + 
                ") is outside all circles");
            GOval black = new GOval(x-2,y-2,5,5);
            black.setFillColor(Color.BLUE);
            black.setFilled(true);
            add(black);
        }

    }

    // Create a CircleGUI object on which to test mouse clicks
    public static void main (String [] args)
    {
        int centerX = 0; // holds center X position of first circle
        int centerY = 0; // holds center Y position of first circle
        int radius = 0;  // holds radius of circle, in pixels

        /*** Use println() to explain program ***/
        System.out.println("This program will draw 3 circles in a window.");
        System.out.println("When you click the mouse in the window,");
        System.out.println("the program will tell you the position where you clicked");
        System.out.println("and how many circles overlap that position.");
        System.out.println();
        System.out.println("Begin by entering the dimensions of the lower left circle.");
        System.out.println("The other circles will be the same size, but with");
        System.out.println("their locationsadjusted accordingly.");
        System.out.println();
        System.out.println("The circle's center X and Y values must be");
        System.out.println("between 75 and 500, inclusive.");
        System.out.println("The circle's radius must be at least 50.");
        System.out.println("It can't exceed 200.");
        System.out.println();

        /*** Set up a Scanner for keyboard input ***/
        Scanner keyboard = new Scanner(System.in);

        /*** Make sure all input values are in their ranges ***/
        /*** If any number is out of range, fix it ***/
        /*** By the time you finish, centerX, centerY and ***/
        /*** radius should have values that are in range. ***/
        System.out.print("Enter center X value of lower left circle: ");
        centerX = keyboard.nextInt();
        if (centerX < 75 || centerX > 500) {
            System.out.println("Error: the X coordinate is out of range.");
            System.out.println("x-value changed to 75.");
            centerX = 75;
        }

        System.out.print("Enter center Y value of lower left circle: ");
        centerY = keyboard.nextInt();
        if (centerY < 75 || centerY > 500) {
            System.out.println("Error: the Y coordinate is out of range.");
            System.out.println("y-value changed to 75.");
            centerY = 75;
        }

        System.out.print("Enter radius to use for all circles: ");
        radius = keyboard.nextInt();
        if (radius < 50) {
            System.out.println("Requested radius too small; changed to 50");
            radius = 50;
        }
        else if (radius > 200) {
            System.out.println("Requested radius too large; changed to 200");
            radius = 200;
        }

        /*** DO NOT CHANGE THESE LINES, AND KEEP THEM AT THE
         * END OF main() ***/ 
        int cornerX = centerX - radius;
        int cornerY = centerY - radius;
        leftCircle = new Circle(cornerX, cornerY, radius);
        rightCircle = new Circle(cornerX + 100, cornerY, radius);
        topCircle = new Circle(cornerX + 50, cornerY - 50, radius);      

        CircleGUI object = new CircleGUI();
        object.start();
        object.startGUI();
    }
}