/**
 * class:  Circle
 * 
 * This program constructs a circle and tests user mouse clicks to see if they 
 * clicked inside, on the edge, or outside the circle
 * 
 * @author Erica Eddy / Stu Hansen
 */

public class Circle
{
    // Instance variables
    private int radius;     // the length of the radius of the GraphicCircle
    private int centerX;    // the X value at the center of the circle
    private int centerY;    // the Y value at the center of the circle
    
    /**
     * Constructor for objects of class GraphicCircle including 
     * position placement
     */
    public Circle(int x, int y, int radius)
    {
        centerX = x;
        centerY = y;
        this.radius = radius;
    }

    // return the center X value
    public int getCenterX()
    {
        return centerX;
    }
    
    // return the center Y value
    public int getCenterY()
    {
       return centerY;
    }
    
    // return the circle's radius, in pixels
    public int getRadius()
    {
       return radius;
    }
}