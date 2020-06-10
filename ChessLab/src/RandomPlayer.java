import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class RandomPlayer extends Player
{
    /**
     * Constructor: Creates a RandomPlayer object
     * @param b The board being played on
     * @param n The name of the player
     * @param c The color of the player
     */
    public RandomPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
        
    }

    /**
     * Retrieves the next Move of this
     * 
     * @return The next Move of this
     */
    public Move nextMove()
    {
        ArrayList<Move> possMoves = getBoard().allMoves(getColor());
        int randIndex = (int) (Math.random() * possMoves.size());
        
        if (possMoves.size() == 0) 
        {
            return null;
        }
        return possMoves.get(randIndex);
    }
    
    
    
}
