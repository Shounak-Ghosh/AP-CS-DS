import java.awt.Color;

/**
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 */
public abstract class Player
{
    private Board board;
    private String name;
    private Color color;

    /**
     * 
     * @param b The board that this is playing with
     * @param n The name of the this
     * @param c The color of this
     */
    public Player(Board b, String n, Color c)
    {
        board = b;
        name = n;
        color = c;
    }

    /**
     * Retrieves the board that this is playing with
     * @return The Board object this is playing with
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Retrieves the name of this
     * @return A String representation of the name of this
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the color of the pieces of this
     * @return A Color object that represents this
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Retrives the next move of this
     * @return A Move object that represents the next move of this
     */
    public abstract Move nextMove();
}
