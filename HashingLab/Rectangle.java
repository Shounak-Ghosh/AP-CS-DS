/**
 * Creates Rectangle objects have the dimensions length and width
 * which can be accessed but not modified
 *
 *@author Anu Datar
 *@author Shounak Ghosh
 *@version 1.23.2019
 */
public class Rectangle
{
    private int length;
    private int width;

    /**
     * Creates Rectangle objects with dimensions length by width 
     * @param len the length of the rectangle
     * @param w the width of the rectangle
     */
    public Rectangle(int len, int w)
    {
        length = len;
        width = w;
    }

    /**
     * Retrieves the length of the Rectangle
     * @return the value of the length 
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Retrieves the width of the Rectangle
     * @return the value of the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Retrieves a String representation of the Rectangle
     * @return a String representation of the Rectangle
     */
    public String toString() // A by B is NOT the same rectangle as B by A
    {
        return length + "x" + width;
    }

    /**
     * Returns a hash code value for the Rectangle.
     * @return an integer value that represents the Rectangle
     */
    public int hashCode() 
    {
        return toString().hashCode();
    }

    /**
     * Indicates whether some other Rectangle 
     * has the same dimensions as this Rectangle.
     * @param o the other Rectangle (The Object is typecasted to Rectangle)
     * @return true if the objects are equal; false otherwise
     */
    public boolean equals(Object o) 
    {
        Rectangle other = (Rectangle) o;

        if (this.length == other.length && this.width == other.width ) 
        {
            return true;
        }
        return false;

    }
}