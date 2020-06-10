import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Creates WordGame objects that can play various word games
 * @author Shounak Ghosh
 * @version 4.09.2019
 *
 */
public class WordGame
{
    private WordGameDisplay display;
    private ArrayList<String> dictionary;

    /**
     * Constructor: Creates WordGame objects
     */
    public WordGame()
    {
        display = new WordGameDisplay();
        dictionary = new ArrayList<String>();

        Iterator<String> iter = display.loadWords("words.txt");

        while (iter.hasNext())
        {
            dictionary.add(iter.next());
        }

        // System.out.println(dictionary);

    }

    
    /**
     * Retrieves the menu of games that can be played
     */
    public void getMenu()
    {
        String text = "1. echo \n2. jotto\n3. search";
        display.setTitle("Shall we play a game?");
        display.setText(text);
        String choice = display.getGuess().toLowerCase();

        if (choice.equals("echo") || choice.equals("1"))
        {
            echo();
            return;
        }

        if (choice.equals("jotto") || choice.equals("2"))
        {
            jotto();
            return;
        }
        
        if (choice.equals("search") || choice.equals("3"))
        {
            search();
            return;
        }

        while (!(choice.equals("echo") 
                || choice.equals("jotto") 
                || choice.equals("1") 
                || choice.equals("search")
                || choice.equals("3") 
                || choice.equals("2")))
        {
            // System.out.println("inside loop");

            text += "\nPlease enter a valid choice.";
            display.setText(text);

            choice = display.getGuess().toLowerCase();

            if (choice.equals("echo") || choice.equals("1"))
            {
                echo();
                return;
            }

            if (choice.equals("jotto") || choice.equals("2"))
            {
                jotto();
                return;
            }
            
            if (choice.equals("search") || choice.equals("3"))
            {
                search();
                return;
            }

        }
    }

    /**
     * Plays the search game, where an user searches for a randomly chosen
     * word from the dictionary
     */
    public void search() 
    {
        String randomWord = getRandomWord();
        System.out.println(randomWord);
        display.setTitle("Search");
        String windowText = "Guess a word.";
        display.setText(windowText);
        String guess = display.getGuess().toLowerCase();
        
        while (!guess.equals(randomWord)) 
        {
            if (dictionaryIndex(guess) == -1) 
            {
                windowText +=  "\n" + "\"" + guess + "\" is not a word.";
            }
            else if (guess.compareTo(randomWord) < 0) 
            {
                // later
                windowText += "\nThe secret word appears LATER"
                              + "in the dictionary.";
            }
            else if (guess.compareTo(randomWord) > 0) 
            {
                // earlier
                windowText += "\nThe secret word appears EARLIER"
                              + "in the dictionary.";
            }
            display.setText(windowText);
            guess = display.getGuess().toLowerCase();
        }
        
        display.setText("Congratulations! You found the word!");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // ignore
        }
        this.getMenu();
        
    }
    
    /**
     * Plays a game of Jotto
     * See https://en.wikipedia.org/wiki/Jotto 
     * for more information
     */
    public void jotto()
    {
        System.out.println("Reached jotto");
        int wordLen = 2;
        display.setTitle("Jotto! (" + wordLen + " Letters)");
        String randomWord = getRandomWord(wordLen);
        System.out.println(randomWord);
        String windowText = "Guess a 2-letter word!";
        display.setText(windowText);
        String guess = display.getGuess().toLowerCase();
        int counter = 1;

        while (!guess.equals("quit game"))
        {
            while (!guess.equals(randomWord))
            {
                if (guess.equals("show word"))
                {
                    windowText += "\n The word was \"" + randomWord + "\".";
                    break;
                }
                else if (dictionaryIndex(guess) == -1)
                {
                    windowText += "\n" 
                                 + counter 
                                 + ".    \"" 
                                 + guess 
                                 + "\"    is not a word.";
                    // counter++;
                }
                else if (guess.length() != wordLen)
                {
                    windowText += "\n" 
                                  + counter 
                                  + ".    \"" 
                                  + guess 
                                  + "\"    must be a " 
                                  + wordLen 
                                  + "-letter word.";
                    // counter++;
                }

                else
                {
                    windowText += "\n" + counter 
                                  + ".    \"" 
                                  + guess 
                                  + "\"   There are "
                                  + commonLetters(guess, randomWord) 
                                  + " letters in common.";
                }
                counter++;
                display.setText(windowText);
                guess = display.getGuess().toLowerCase();
            }

            if (!guess.equals("show word"))
            {
                windowText += "\n" 
                              + counter 
                              + ".   \"" 
                              + guess 
                              + "\" was the word!";
            }
            wordLen++;
            windowText += "\nGuess a " + wordLen + "-letter word!";
            display.setText(windowText);
            display.setTitle("Jotto! (" + wordLen + " Letters)");
            counter = 1;
            randomWord = getRandomWord(wordLen);
            System.out.println(randomWord);
            guess = display.getGuess().toLowerCase();

        }
        display.setText("Quitting game...");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // ignore
        }
        this.getMenu();
        
        
        System.out.println("Game ended.");
    }

    /**
     *  Echos the word typed in, if it is a valid word
     */
    public void echo()
    {
        System.out.println("Reached echo");
        String s = "";
        display.setTitle("The Echo Game");
        s = "Enter a word.";
        display.setText(s);
        String guess = "";
        guess = display.getGuess().toLowerCase();
        int index = dictionaryIndex(guess);

        while (!guess.toLowerCase().equals("quit game"))
        {
            // System.out.println(guess + dictionaryIndex(guess));

            if (index >= 0)
            {
                s += "\n" + "\"" + guess + "\" is word #" + (index + 1) + ".";
            }
            else
            {
                s += "\n" + "\"" + guess + "\" is not a word.";
            }
            s += "\n" + "Enter another word.";
            display.setText(s);
            guess = display.getGuess().toLowerCase();
            index = dictionaryIndex(guess);
            // System.out.print(s);
        }
        display.setText("Quitting game...");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // ignore
        }
        this.getMenu();

        // System.out.println("Method exited.");

    }

    /**
     * Finds the index of a given String in the dictionary, using binary search
     * @param key the String to be searched for
     * @return an index value greater than or equal to 0 if the key was found;
     *         -1 otherwise
     */
    public int dictionaryIndex(String key)
    {
        int left = 0;
        // System.out.println(dictionary);
        int right = dictionary.size() - 1;
        int middle = 0;

        while (left <= right)
        {
            middle = (left + right) / 2;
            if (dictionary.get(middle).equals(key))
            {
                return middle;
            }

            if (dictionary.get(middle).compareTo(key) < 0)
            {
                left = middle + 1;
            }
            else
            {
                right = middle - 1;
            }
            // System.out.println("end of loop");
        }

        return -1;
    }

    /**
     * Retrieves a random word (from the dictionary) of length i
     * @param i the length of the random word
     * @return an randomly picked word of length i
     */
    public String getRandomWord(int i)
    {
        String ret = getRandomWord();

        while (ret.length() != i)
        {
            ret = getRandomWord();
        }

        return ret;
    }

    /**
     * Retrieves a random word from the dictionary
     * @return A random word from the dictionary
     */
    public String getRandomWord()
    {
        int index = (int) (Math.random() * dictionary.size());
        return dictionary.get(index);
    }
    
    /**
     * Retrieves the number of letters in common in between two words
     * @param string The first word
     * @param string2 The second word
     * @return the number of letters in common in between the given words
     * 
     */
    public int commonLetters(String string, String string2)
    {
        HashSet<String> allLetters = new HashSet();

        for (int i = 0; i < string.length(); i++)
        {
            allLetters.add(string.substring(i, i + 1));
        }

        for (int i = 0; i < string2.length(); i++)
        {
            allLetters.add(string2.substring(i, i + 1));
        }

        int freqOne = 0;
        int freqTwo = 0;
        int numCommon = 0;

        // System.out.println(allLetters);

        for (String letter : allLetters)
        {
            for (int i = 0; i < string.length(); i++)
            {
                if (letter.equals(string.substring(i, i + 1)))
                {
                    freqOne++;
                }
            }

            for (int i = 0; i < string2.length(); i++)
            {
                if (letter.equals(string2.substring(i, i + 1)))
                {
                    freqTwo++;
                }
            }
            // System.out.println(Math.min(freqOne, freqTwo));
            numCommon += Math.min(freqOne, freqTwo);
            freqOne = 0;
            freqTwo = 0;
        }

        // System.out.println(string + " " + string2 + " >> " + numCommon);
        return numCommon;
    }

}
