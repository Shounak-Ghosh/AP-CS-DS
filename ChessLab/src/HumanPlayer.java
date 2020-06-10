import java.awt.Color;

/**
 * HumanPlayer objects allows the user select moves when playing a game of chess
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class HumanPlayer extends Player
{
    BoardDisplay display;

    /**
     * Constructor: Creates a HumanPlayer object
     * 
     * @param b The board being played on
     * @param n The name of the player
     * @param c The color of the player
     * @param d The BoardDisplay object used to display/update the board
     */
    public HumanPlayer(Board b, String n, Color c, BoardDisplay d)
    {
        super(b, n, c);
        display = d;

    }

    /**
     * Retrieves the next Move of this
     * 
     * @return The next Move of this
     */
    public Move nextMove()
    {
        Move userInput = display.selectMove();
        while (!getBoard().allMoves(getColor()).contains(userInput))
        {
            userInput = display.selectMove();
        }
        return userInput;
    }

}
