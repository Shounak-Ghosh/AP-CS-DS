import java.awt.Color;
import java.util.ArrayList;

/**
 * Creates Rook objects that mimics the rook piece in a game of chess
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Rook extends Piece
{
    /**
     * Constructor: Creates Rook objects
     * 
     * @param col      The color of this
     * @param fileName The name of the file in which the rook's image is stored
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5);
       
    }
    
    /**
     * Retrieves all the valid destinations to which this can be moved
     * @return An ArrayList of the valid Locations to which this can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> validDestinations = new ArrayList<Location>();
        sweep(validDestinations, Location.NORTH);
        sweep(validDestinations, Location.EAST);
        sweep(validDestinations, Location.SOUTH);
        sweep(validDestinations, Location.WEST);
        return validDestinations;
    }

}
