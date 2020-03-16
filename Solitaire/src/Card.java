/**
 * Creates Card objects that stores information about rank, suit, and state 
 * @author Shounak Ghosh 
 * @version 10.29.18
 */
public class Card
{
    private int rank;
    private String suit;
    private boolean isFaceUp; //automatically initialized to false;
    /**
     * Creates a Card object
     * @param r rank of the card, from 1 to 13
     * @param s suit of the card
     */
    public Card(int r, String s)
    {
        rank = r;
        suit = s;
    }
    /**
     * Acesses the rank of the card
     * @return number value of the rank (1 for ace, 13 for king)
     */
    public int getRank()
    {
        return rank;
    }
    /**
     * Acesses the suit of the card
     * @return String containing the first letter of the suit
     */
    public String getSuit()
    {
        return suit;
    }
    /**
     * Checks if a card is red
     * @return true if red, false otherwise
     */
    public boolean isRed()
    {
        return suit.equals("d") || suit.equals("h");
    }
    /**
     * checks if the card is face up
     * @return true if the card is face up, false otherwise
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    /**
     * turns the card face up
     */
    public void turnUp()
    { 
        isFaceUp = true;
    }
    /**
     * turns the card face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    /**
     * Retrieves the file name of the image of the card
     * @precondition the cards are stored in a subdirector "cards"
     * @return a String containing the file name of the card
     */
    public String getFileName()
    {
        if (!isFaceUp)
        {
            return "cards/back8.gif";
            //return "cards/backapcsds.gif";
        }
        else if (rank == 1)
        {
            return "cards/a" + suit + ".gif";
        }
        else if (rank == 10)
        {
            return "cards/t" + suit + ".gif";
        }
        else if (rank == 11)
        {
            return "cards/j" + suit + ".gif";
        }
        else if (rank == 12)
        {
            return "cards/q" + suit + ".gif";
        }
        else if (rank == 13)
        {
            return "cards/k" + suit + ".gif";
        }
        else
        {
            return "cards/" + rank + suit + ".gif";
        }
    }
}
