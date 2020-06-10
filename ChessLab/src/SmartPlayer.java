import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class SmartPlayer extends Player
{
    BoardDisplay display;
    
    /**
     * Constructor: Creates a SmartPlayer object
     * @param b The board being played on
     * @param n The name of the this
     * @param c The color of the this
     * @param display The BoardDisplay object used to display the board of this
     */
    public SmartPlayer(Board b, String n, Color c, BoardDisplay display)
    {
        super(b, n, c);

        this.display = display;

    }

    /**
     * Retrieves the score of the board in its current state
     * The score is computed by subtracting the total value of 
     * the piece of this from the opponent's piece total value
     * @return The score of the current board
     */
    private int score()
    {
        int myPieces = 0;
        int oppPieces = 0;

        Location loc = new Location(0, 0);

        for (int r = 0; r < getBoard().getNumRows(); r++)
        {
            for (int c = 0; c < getBoard().getNumCols(); c++)
            {
                loc = new Location(r, c);

                if (getBoard().get(loc) != null)
                {
                    if (getBoard().get(loc).getColor().equals(getColor()))
                    {
                        myPieces += getBoard().get(loc).getValue();
                    }
                    else
                    {
                        oppPieces += getBoard().get(loc).getValue();
                    }
                }
            }
        }

        return myPieces - oppPieces;

    }

    /**
     * Retrieves the value of the board after the opponent plays the best move
     * (for it) possible
     * @return the score of the board after the opponent plays its best move
     */
    private int valueOfMeanestResponse()
    {
        Color oppColor = null;
        if (getColor().equals(Color.BLACK))
        {
            oppColor = Color.WHITE;
        }
        else
        {
            oppColor = Color.BLACK;
        }

        int minScore = 100000;
        int currentScore = 0;

        ArrayList<Move> possOppMoves = getBoard().allMoves(oppColor);
        // System.out.println(oppColor);
        for (Move m : possOppMoves)
        {
            getBoard().executeMove(m);
            currentScore = score();
            System.out.println("Current Score: " + currentScore);
            /*
             * display.setColor(m.getSource(), new Color(153,0,0));
             * display.setColor(m.getDestination(), new Color(255,102,102));
             * 
             * try { Thread.sleep(5); } catch (InterruptedException e) {
             * e.printStackTrace(); }
             * 
             * display.clearColors();
             */

            if (currentScore < minScore)
            {
                minScore = currentScore;
            }

            getBoard().undoMove(m);
        }

        // System.out.println("Meanest Response Value: " + maxScore);

        return minScore;
    }

    // of the possible goodMoves
    // find the best move
    // for each good move, compute the meanest response and execute it
    // after executing it, return the score of the board after that move

    /**
     * Retrieves the next Move of this
     * @return The next Move of this
     */
    public Move nextMove()
    {
        ArrayList<Move> possMoves = getBoard().allMoves(getColor());
        Move best = null;
        int maxScore = -100;
        // this stops the game when the smart player's king is captured
        int currentScore = 0;

        for (Move m : possMoves)
        {
            getBoard().executeMove(m);
            currentScore = valueOfMeanestResponse();
            if (currentScore > maxScore)
            {
                best = m;
                maxScore = currentScore;
            }
            getBoard().undoMove(m);
        }

        ArrayList<Move> goodMoves = new ArrayList<Move>();
        for (Move m : possMoves)
        {
            getBoard().executeMove(m);
            if (valueOfMeanestResponse() == maxScore)
            {
                goodMoves.add(m);
            }
            getBoard().undoMove(m);
        }

        int randIndex = (int) (Math.random() * goodMoves.size());

        if (goodMoves.size() == 0)
        {
            return null;
        }

        // System.out.println("Max score: " + maxScore);
        // System.out.println("Number of good moves: " + goodMoves.size());

        return goodMoves.get(randIndex);
    }

}
