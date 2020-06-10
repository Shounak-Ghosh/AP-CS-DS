import java.awt.Color;
import java.util.ArrayList;

/**
 * Creates Pawn objects, which mimics the pawn piece in a game of chess
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Pawn extends Piece
{
    /**
     * Constructor: Creates Pawn objects
     * 
     * @param col      The color of this
     * @param fileName The name of the file in which the king's image is stored
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * Retrieves all the valid destinations to which this can be moved
     * 
     * @return An ArrayList of the valid Locations to which this can be moved
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> validDestinations = new ArrayList<Location>();
        Location current = getLocation();
        Location inFront;
        Location diagOne;
        Location diagTwo;
        Location twoAhead;

        if (getColor().equals(Color.WHITE))
        {
            inFront = new Location(current.getRow() - 1, current.getCol());
            diagOne = new Location(current.getRow() - 1, current.getCol() - 1);
            diagTwo = new Location(current.getRow() - 1, current.getCol() + 1);

            if (isValidDestination(inFront))
            {
                if (getBoard().get(inFront) == null)
                {
                    validDestinations.add(inFront);
                }
            }

            if (isValidDestination(diagOne))
            {
                if (getBoard().get(diagOne) != null)
                {
                    validDestinations.add(diagOne);
                }
            }

            if (isValidDestination(diagTwo))
            {
                if (getBoard().get(diagTwo) != null)
                {
                    validDestinations.add(diagTwo);
                }
            }

            twoAhead = new Location(current.getRow() - 2, current.getCol());
            if (current.getRow() == 6)
            {
                if (getBoard().get(twoAhead) == null 
                    && getBoard().get(inFront) == null)
                {
                    validDestinations.add(twoAhead);
                }
            }
            return validDestinations;
        }
        // black pawn, can only move down
        inFront = new Location(current.getRow() + 1, current.getCol());
        diagOne = new Location(current.getRow() + 1, current.getCol() - 1);
        diagTwo = new Location(current.getRow() + 1, current.getCol() + 1);

        if (isValidDestination(inFront))
        {
            if (getBoard().get(inFront) == null)
            {
                validDestinations.add(inFront);
            }
        }

        if (isValidDestination(diagOne))
        {
            if (getBoard().get(diagOne) != null)
            {
                validDestinations.add(diagOne);
            }
        }

        if (isValidDestination(diagTwo))
        {
            if (getBoard().get(diagTwo) != null)
            {
                validDestinations.add(diagTwo);
            }
        }

        twoAhead = new Location(current.getRow() + 2, current.getCol());
        if (current.getRow() == 1) 
        {
            if (getBoard().get(twoAhead) == null 
                && getBoard().get(inFront) == null)
            {
                validDestinations.add(twoAhead);
            }
        }

        return validDestinations;
    }

}
