

import java.util.ArrayList;

/**
 * Stores information about the Phrase objects that make up the Sentence
 * 
 * @author Shounak Ghosh
 * @version 3.30.2019
 *
 */
public class Sentence
{
    private ArrayList<Phrase> phrases;
    private int numPhrases;
    private int numWords;

    /**
     * Constructor: Creates Sentences objects
     */
    public Sentence()
    {
        phrases = new ArrayList<Phrase>();
        numPhrases = 0;
        numWords = 0;
    }

    /**
     * Adds a Phrase to this
     * 
     * @param p The Phrase to be added to this
     */
    public void addPhrase(Phrase p)
    {
        phrases.add(p);
        numWords += p.getNumWords();
        numPhrases++;
    }

    /**
     * Retrieves a deep copy of this
     * 
     * @return An ArrayList of all the Phrases in this
     */
    public ArrayList<Phrase> getCopy()
    {
        ArrayList<Phrase> copy = new ArrayList<Phrase>();

        for (Phrase p : phrases)
        {
            copy.add(p);
        }
        return copy;
    }

    /**
     * Retrieves the number of words in the this
     * 
     * @return The number of words in this
     */
    public int getNumWords()
    {
        return numWords;
    }

    /**
     * Retrieves the number of phrases in this
     * 
     * @return The number of phrases in this
     */
    public int getNumPhrases()
    {
        return numPhrases;
    }

    /**
     * Retrieves a String representation of this
     * 
     * @return A String representation of this
     */
    public String toString()
    {
        return phrases.toString();

    }
}
