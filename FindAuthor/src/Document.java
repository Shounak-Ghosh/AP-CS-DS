import java.util.*;

/**
 * Stores information about a given document
 * 
 * @author Shounak Ghosh
 * @version 3.30.2019
 *
 */
public class Document
{
    private Scanner stdin;
    private ArrayList<Sentence> sentences;
    private ArrayList<Token> wordList;
    private HashSet<Token> wordSet;
    private boolean endOfFile;
    private Token currentToken;

    private int numWords;
    private int numPhrases;
    private int numSentences;
    private int totWordLen;
    //private int numUniqueWords;

    /**
     * Constructor: Creates a Document object
     * 
     * @param sc Scanner object used to read in Tokens
     */
    public Document(Scanner sc)
    {
        stdin = sc;
        sentences = new ArrayList<Sentence>();
        wordList = new ArrayList<Token>();
        wordSet = new HashSet<Token>();
        numWords = 0;
        numPhrases = 0;
        numSentences = 0;
        totWordLen = 0;
        //numUniqueWords = 0;
        getNextToken();
    }

    /**
     * Retrieves the next Token object from the Scanner
     */
    private void getNextToken()
    {
        currentToken = stdin.nextToken();
    }

    /**
     * Checks whether the given Token is equal to currentToken
     * 
     * @param obj The Token to be checked
     */
    private void eat(Token obj)
    {
        if (!obj.equals(currentToken))
        {
            throw new IllegalArgumentException("Illegal Token");
        }
        getNextToken();
    }

    /**
     * Parses a Phrase until a phrase terminator, 
     * sentence terminator, or the end of the file is reached
     * 
     * @return The Phrase parsed
     */
    private Phrase parsePhrase()
    {
        Phrase p = new Phrase();
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE
                && currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_PHRASE
                && currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            if (currentToken.getType() == Scanner.TOKEN_TYPE.WORD)
            {
                //System.out.println(currentToken);
                p.addToken(currentToken);
                wordList.add(currentToken);
                wordSet.add(currentToken);
                numWords++;
                totWordLen += currentToken.getToken().length();
            }
            eat(currentToken);
        }

        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_PHRASE)
            eat(currentToken);
        numPhrases++;
        return p;
    }

    /**
     * Parses a Sentence until a sentence terminator or the end of the file is
     * reached
     * 
     * @return The Sentence parsed
     */
    private Sentence parseSentence()
    {
        Sentence s = new Sentence();
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE
                && currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            s.addPhrase(parsePhrase());
        }
        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_SENTENCE)
            eat(currentToken);
        return s;
    }

    /**
     * Parses an entire document and stores each Sentences as a collection of
     * Phrases, which are composed of a collection of Tokens
     */
    public void parseDocument()
    {
        while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {

            sentences.add(parseSentence());
            numSentences++;
        }
        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_FILE)
            eat(currentToken);

    }

    /**
     * Retrieves a shallow copy of the document
     * 
     * @return An ArrayList of all the Sentences in the document
     */
    public ArrayList<Sentence> getCopy()
    {
        return sentences;
    }

    /**
     * Retrieves a list of all the words in the document
     * 
     * @return An ArrayList of all the words in the document
     */
    public ArrayList<Token> getWordList()
    {
        return wordList;
    }

    /**
     * Retrieves a set of all the words in the document
     * 
     * @return An HashSet of all words in the document
     */
    public HashSet<Token> getWordSet()
    {
        return wordSet;
    }

    /**
     * Retrieves the number of words in the document
     * 
     * @return The number of words in the document
     */
    public int getNumWords()
    {
        return numWords;
    }

    /**
     * Retrieves the number of phrases in the document
     * 
     * @return The number of phrases in the document
     */
    public int getNumPhrases()
    {
        return numPhrases;
    }

    /**
     * Retrieves the number of sentences in the document
     * 
     * @return The number of sentences in the document
     */
    public int getNumSentences()
    {
        return numSentences;
    }
    
    /**
     * Retrieves the total length of all the words
     * @return The total length of all the words
     */
    public int totalWordLength() 
    {
        return totWordLen;
    }

}
