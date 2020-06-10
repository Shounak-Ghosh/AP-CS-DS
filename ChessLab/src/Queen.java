import java.awt.Color;
import java.util.ArrayList;

/**
 * Creates Queen objects that mimics the queen piece in a game of chess
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Queen extends Piece
{
    /**
     * Constructor: Creates Queen objects
     * @param col the color of this
     * @param fileName 
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
       
    }

    /**
     * Retrieves all the valid destinations to which this can be moved
     * 
     * @return An ArrayList of the valid Locations to which this can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> validDestinations = new ArrayList<Location>();
        sweepRay(validDestinations, Location.NORTHEAST);
        sweepRay(validDestinations, Location.SOUTHEAST);
        sweepRay(validDestinations, Location.SOUTHWEST);
        sweepRay(validDestinations, Location.NORTHWEST);
        
        sweep(validDestinations, Location.NORTH);
        sweep(validDestinations, Location.EAST);
        sweep(validDestinations, Location.SOUTH);
        sweep(validDestinations, Location.WEST);
        
        
        return validDestinations;
        
    }
    
}
