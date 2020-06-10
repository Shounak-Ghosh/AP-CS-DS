import java.awt.Color;
import java.util.ArrayList;

/**
 * Creates King objects the mimics the king piece in a game of chess
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 */
public class King extends Piece
{

    /**
     * Constructor: Creates King objects
     * 
     * @param col      The color of this
     * @param fileName The name of the file in which the king's image is stored
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Retrieves all the valid destinations to which this can be moved
     * 
     * @return An ArrayList of the valid Locations to which this can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList validDestinations = new ArrayList<Location>();
        Location current = getLocation();
        Location temp;
        int rowCounter = -1;
        int colCounter = -1;

        for (int r = 0; r < 3; r++)
        {
            colCounter = -1;
            for (int c = 0; c < 3; c++)
            {
                if (!(rowCounter == 0 && colCounter == 0))
                {
                    temp = new Location(current.getRow() + rowCounter, 
                                        current.getCol() + colCounter);
                    if (isValidDestination(temp))
                    {
                        validDestinations.add(temp);
                    }
                }
                colCounter++;
            }
            rowCounter++;
        }

        return validDestinations;
    }
}
