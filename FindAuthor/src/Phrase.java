
import java.util.ArrayList;

/**
 * Stores information about the Token objects  which make up the Phrase
 * 
 * @author Shounak Ghosh
 * @version 3.30.2019
 *
 */
public class Phrase
{
    private ArrayList<Token> tokens;
    private int numWords;

    /**
     * Constructor: Creates Phrase objects
     */
    public Phrase()
    {
        tokens = new ArrayList<Token>();
        numWords = 0;
    }

    /**
     * Adds a Token object to this
     * 
     * @param t The Token object to be added
     */
    public void addToken(Token t)
    {
        tokens.add(t);
        numWords++;
    }

    /**
     * Retrieves a deep copy of this
     * 
     * @return An ArrayList of all the Token objects in this
     */
    public ArrayList<Token> getCopy()
    {
        ArrayList<Token> copy = new ArrayList<Token>();

        for (Token tok : tokens)
        {
            copy.add(tok);
        }
        return copy;
    }

    /**
     * Retrieves the number of words in this
     * 
     * @return The number of words in this
     */
    public int getNumWords()
    {
        return numWords;
    }

    /**
     * Retrieves a String representation of this
     * 
     * @return A String representation of this
     */
    public String toString()
    {
        return tokens.toString();
    }

}