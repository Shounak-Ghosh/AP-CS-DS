import java.util.*;
/**
 * Breaks up a String into individual String based on spaces
 * 
 * @author Shounak Ghosh
 * @version 11.02.2018
 */

public class Tokenizer
{
    // instance variables - replace the example below with your own
    private ArrayList<String> tokenized;

    /**
     * Creates a Tokenizer object and tokenizes the given expression
     * @param expression String to be tokenized
     */
    public Tokenizer(String expression)
    {
        // initialise instance variables
        tokenized = new ArrayList<String>();
        String temp = "";
        String charAtIndex = "";
        int counter = 0;
        for (int i = 0; i < expression.length(); i++)
        {
            charAtIndex = expression.substring(i , i + 1);
            if (charAtIndex.equals(" "))
            {
                tokenized.add(temp);
                temp = "";
            }
            else
            {
                if (!charAtIndex.equals(" "))
                {
                    temp = temp + charAtIndex;
                }
            }
        }
        tokenized.add(temp);
        int i = 0;
        while (i < tokenized.size())
        {
            if (tokenized.get(i).equals(" ") || tokenized.get(i).equals(""))
            {
                tokenized.remove(i);
            }
            else
            {
                i++;
            }
        }
        
        //System.out.println(tokenized);
        // The tokenizer works, the spaces are from the ArrayList toString
    }
    /**
     * checks if more tokens are available
     * @return true if more tokens exist
     *         false otherwise
     */
    public boolean hasMoreTokens()
    {
        return tokenized.size() > 0;
    }
    /**
     * gets the next token in the expression
     * @return the  next token
     */
    public String nextToken()
    {
        return tokenized.remove(0);
    }
    /**
     * retrieves the current token of the expression
     * @return the current token
     */
    public String getToken()
    {
        return tokenized.get(0);
    }
}
