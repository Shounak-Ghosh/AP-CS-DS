import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens. A token is defined as: 1. A
 * 'word' which is defined as a non-empty sequence of characters that begins
 * with an alpha character and then consists of alpha characters, numbers, the
 * single quote character "'", or the hyphen character "-". 2. An
 * 'end-of-sentence' delimiter defined as any one of the characters ".", "?",
 * "!". 3. An end-of-file token which is returned when the scanner is asked for
 * a token and the input is at the end-of-file. 4. A phrase separator which
 * consists of one of the characters ",",":" or ";". 5. A digit. 6. Any other
 * character not defined above.
 * 
 * @author Mr. Page
 * 
 * @author Shounak Ghosh
 * @version 02.07.2019
 *
 */
public class Scanner
{
    private Reader in; // reads in the characters from the file
    private String currentChar; // the current character (String len 1)
    private boolean endOfFile; // checks if end of file is reached

    /**
     * defines symbolic constants for each type of token
     */
    public static enum TOKEN_TYPE
    {
        WORD, END_OF_SENTENCE, END_OF_FILE, END_OF_PHRASE, DIGIT, UNKNOWN
    };

    /**
     * Constructor for Scanner objects. The Reader object should be one of 1. A
     * StringReader 2. A BufferedReader wrapped around an InputStream 3. A
     * BufferedReader wrapped around a FileReader The instance field for the Reader
     * is initialized to the input parameter, and the endOfFile indicator is set to
     * false. The currentChar field is initialized by the getNextChar method.
     * 
     * @param in is the reader object supplied by the program constructing this
     *           Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream. It sets the endOfFile flag true if the end of file is reached on the
     * input stream. Otherwise, it reads the next character from the stream and
     * converts it to a Java String object. postcondition: The input stream is
     * advanced one character if it is not at end of file and the currentChar
     * instance field is set to the String representation of the character read from
     * the input stream. The flag endOfFile is set true if the input stream is
     * exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1)
                endOfFile = true;
            else currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    // if the parameter and currentChar are equal,
    // then get the next character
    /**
     * Checks whether currentChar and the parameter are equal
     * 
     * @param object The String to be checked
     */
    private void eat(String object)
    {
        if (!object.equals(currentChar))
        {
            throw new IllegalArgumentException("Error: CurrentCharacter does not hold the correct character.");
        }
        getNextChar();
    }

    /**
     * Checks whether the parameter is a letter
     * 
     * @param s the String to be checked
     * @return returns true if s is a letter; false otherwise
     */
    private boolean isLetter(String s)
    {
        return new String("qwertyuiopasdfghjklzxcvbnm").indexOf(s.toLowerCase()) != -1;
    }

    /**
     * Checks whether the parameter is a digit
     * 
     * @param s the String to be checked
     * @return returns true if s is a digit; false otherwise
     */
    private boolean isDigit(String s)
    {
        return new String("1234567890").indexOf(s) != -1;
    }

    /**
     * Checks whether the parameter is a special character
     * 
     * @param s the String to be checked
     * @return returns true if s is a special character; false otherwise
     */
    private boolean isSpecialChar(String s)
    {
        return new String("-'_").indexOf(s) != -1;
    }

    /**
     * Checks whether the parameter is a phrase terminator
     * 
     * @param s the String to be checked
     * @return returns true if s is a phrase terminator; false otherwise
     */
    private boolean isPhraseTerminator(String s)
    {
        return new String(":,;").indexOf(s) != -1;
    }

    /**
     * Checks whether the parameter is a sentence terminator
     * 
     * @param s the String to be checked
     * @return returns true if s is a sentence terminator; false otherwise
     */
    private boolean isSentenceTerminator(String s)
    {
        return new String(".!?").indexOf(s) != -1;
    }

    /**
     * Checks whether the parameter is a whitespace
     * 
     * @param s the String to be checked
     * @return returns true if s is a whitespace; false otherwise
     */
    private boolean isWhiteSpace(String s)
    {
        // return whiteSpace.indexOf(s) != -1;
        return new String("\t \r \n").indexOf(s.toLowerCase()) != -1;
    }

    /**
     * Checks whether any more tokens exist within the file
     * 
     * @return true if end-of-file has not been reached; false otherwise
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }

    /**
     * Retrieves the next Token Object in the file
     * 
     * @return the next Token Object in the file
     */
    public Token nextToken()
    {
        // if there are white spaces, ignore them
        while (hasNextToken() && isWhiteSpace(currentChar))
        {
            eat(currentChar);

        }
        String currChar = currentChar.toLowerCase(); // don't want to modify original variable
        TOKEN_TYPE t = null; // type of token to be determined

        if (isDigit(currChar))
        {
            t = TOKEN_TYPE.DIGIT;
            eat(currentChar);
            return new Token(t, currChar); 
        }
        else if (isPhraseTerminator(currChar))
        {
            t = TOKEN_TYPE.END_OF_PHRASE;
            eat(currentChar);
            return new Token(t, currChar); 
        }
        else if (isSentenceTerminator(currChar))
        {
            t = TOKEN_TYPE.END_OF_SENTENCE;
            eat(currentChar);
            return new Token(t, currChar); 
        }
        else if (isLetter(currChar)) 
        {
            currChar = "";
            t = TOKEN_TYPE.WORD;
            while (hasNextToken() && (isLetter(currentChar) || isDigit(currentChar) || isSpecialChar(currentChar)))
            {
                currChar += currentChar.toLowerCase();
                eat(currentChar);
            }

            return new Token(t, currChar); 
        }
        else if (hasNextToken())
        {
            t = TOKEN_TYPE.UNKNOWN;
            eat(currentChar);
            return new Token(t, currChar); 
        }
        else
        {
            t = TOKEN_TYPE.END_OF_FILE;
            eat(currentChar);
            return new Token(t, currChar);          
        }

    }
}
