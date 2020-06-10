

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Tests the Document and DocumentStatistics classes
 * @author Shounak Ghosh
 * @version 3.30.2019
 *
 */
public class DocumentTester
{
    /**
     * Main tester method
     *
     * @param args Command line argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        findAuthor("mystery1.txt");
        findAuthor("mystery2.txt");
        findAuthor("mystery3.txt");
        findAuthor("mystery4.txt");
        findAuthor("mystery5.txt");
        

    }
    
    /**
     * @param filename The name of the file for which the author will be 
     *                 predicted
     * @throws IOException Thrown if there is a file input/output error
     */
    public static void findAuthor(String filename) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Scanner scanner = new Scanner(reader);

        Document d = new Document(scanner);
        d.parseDocument();
        System.out.println();
        System.out.println("DOCUMENT STATISTICS");
        System.out.println();
        System.out.println("Total number of words: " + d.getNumWords());
        System.out.println("Total number of phrases: " + d.getNumPhrases());
        System.out.println("Total number of sentences: " + d.getNumSentences());
        System.out.println();
       
        System.out.println("Computing more statistics..." + 
                           "Predicting author..."); 
        System.out.println();
        
        DocumentStatistics dStats = new DocumentStatistics(d);
        
        double[] stats = dStats.getDocumentStatistics();
        
        System.out.println("Average word length: " + stats[0]);
        System.out.println("Type-Token Ratio: " + stats[1]);
        System.out.println("Hapax Legomana Ratio: " + stats[2]);
        System.out.println("Average words per sentence: " + stats[3]);
        System.out.println("Sentence complexity: " + stats[4]);
        System.out.println();

        System.out.println(dStats.findAuthor().toUpperCase());
    }
}
