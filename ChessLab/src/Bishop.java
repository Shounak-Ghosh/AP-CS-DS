import java.awt.Color;
import java.util.ArrayList;

/**
 * Creates a Bishop object that mimics the bishop piece in the game of chess
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Bishop extends Piece
{
    /**
     * Constructor: Creates Bishop objects
     * @param col The color of this
     * @param fileName The filename for the bishop's image
     */
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 3);
        
    }

    /**
     * Retrieves all the valid destinations to which the bishop can be moved
     * @return An ArrayList of all valid Locations 
     *         to which the bishop can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> validDestinations = new ArrayList<Location>();
        sweepRay(validDestinations, Location.NORTHEAST);
        sweepRay(validDestinations, Location.SOUTHEAST);   
        sweepRay(validDestinations, Location.SOUTHWEST);
        sweepRay(validDestinations, Location.NORTHWEST);
        return validDestinations;
    }
    
}
