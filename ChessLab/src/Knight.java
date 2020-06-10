import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Knight extends Piece
{

    /**
     * Constructor: Creates Knight objects
     * @param col The color of this
     * @param fileName The name of the file in which the king's image is stored
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Retrieves all the valid destinations to which this can be moved
     * @return An ArrayList of the valid Locations to which this can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> validDestinations = new ArrayList<Location>();
        Location current = getLocation();

        Location temp = new Location(current.getRow() - 2,
                                     current.getCol() - 1);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() - 2, current.getCol() + 1);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() - 1, current.getCol() - 2);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() - 1, current.getCol() + 2);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() + 1, current.getCol() - 2);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() + 1, current.getCol() + 2);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() + 2, current.getCol() - 1);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() + 2, current.getCol() - 1);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        temp = new Location(current.getRow() + 2, current.getCol() + 1);
        if (isValidDestination(temp))
        {
            validDestinations.add(temp);
        }

        return validDestinations;
    }

}
