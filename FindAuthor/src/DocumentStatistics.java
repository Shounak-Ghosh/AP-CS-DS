import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Gathers statistics about a given document and predicts the author 
 * based on signature statistics
 * 
 * @author Shounak Ghosh
 * @version 3.30.2019
 *
 */
public class DocumentStatistics
{
    private Document document;
    // maybe use HashMap of String,double[] for author statistics
    private ArrayList<AuthorStats> sigStats; 
    // the author stats are already weighted
    private ArrayList<Token> words;
    private HashSet<Token> wordSet;
    private double[] weights = { 0.0, 11.0, 33.0, 50.0, 0.4, 4.0 };
    private double[] docStats = new double[5];

    private BufferedReader f;
    private Scanner sc;
    private String name;
    private double avgWordLen;
    private double typeTokenRatio;
    private double hapLegoRatio;
    private double avgSentenceLen;
    private double avgSentenceComplexity;

    /**
     * Constructor: Creates a DocumentStatistic object and reads in all relevant
     * data
     * 
     * @param d The document to be analyzed
     * @throws IOException Thrown if there is a file input/output error
     */
    public DocumentStatistics(Document d) throws IOException
    {
        document = d;
        document.parseDocument();
        //sentences = d.getCopy();
        sigStats = new ArrayList<AuthorStats>();
        
        words = document.getWordList();
        wordSet = document.getWordSet();

        name = "";
        avgWordLen = 0.0;
        typeTokenRatio = 0.0;
        hapLegoRatio = 0.0;
        avgSentenceLen = 0.0;
        avgSentenceComplexity = 0.0;

        readAuthorInfo("agatha.christie.stats");
        readAuthorInfo("alexandre.dumas.stats");
        readAuthorInfo("brothers.grim.stats");
        readAuthorInfo("charles.dickens.stats");
        readAuthorInfo("douglas.adams.stats");
        readAuthorInfo("emily.bronte.stats");
        readAuthorInfo("fyodor.dostoevsky.stats");
        readAuthorInfo("james.joyce.stats");
        readAuthorInfo("jane.austen.stats");
        readAuthorInfo("lewis.caroll.stats");
        readAuthorInfo("mark.twain.stats");

        avgWordLen = getAverageWordLength();
        typeTokenRatio = getTypeTokenRatio();
        hapLegoRatio = getHapaxLegomanaRatio();
        avgSentenceLen = getAverageWordsPerSentence();
        avgSentenceComplexity = getAverageSentenceComplexity();
        
        docStats[0] = avgWordLen;
        docStats[1] = typeTokenRatio;
        docStats[2] = hapLegoRatio;
        docStats[3] = avgSentenceLen;
        docStats[4] = avgSentenceComplexity;
        

    }
    
    /**
     * Retrieves a shallow copy of the document statistics computed
     * @return An array of doubles consisting of the computed statistics
     */
    public double[] getDocumentStatistics() 
    {
        return docStats;
    }
    
    /**
     * Reads in the signature statistics from a given file
     * 
     * @param filename The name of the file to be read from
     * @throws IOException Thrown if there is a file input/output error
     */
    private void readAuthorInfo(String filename) throws IOException
    {
        f = new BufferedReader(new FileReader(filename));
        sc = new Scanner(f);
        name = sc.nextLine();
        avgWordLen = sc.nextDouble();
        typeTokenRatio = sc.nextDouble();
        hapLegoRatio = sc.nextDouble();
        avgSentenceLen = sc.nextDouble();
        avgSentenceComplexity = sc.nextDouble();

        sigStats.add(new AuthorStats(name, avgWordLen,
                                     typeTokenRatio,
                                     hapLegoRatio, 
                                     avgSentenceLen, 
                                     avgSentenceComplexity));
    }

    /**
     * Retrieves the average word length of all words in the document;
     * hyphens and underscores are included in these calculations
     * 
     * @return The average word length of all the words in the document
     */
    public double getAverageWordLength()
    {
        return document.totalWordLength() / (double) document.getNumWords();
    }

    /**
     * Retrieves the ratio between the number of different words in 
     * the document to the total number of words in the document
     * 
     * @return the number of different words to total number of words ratio
     */
    public double getTypeTokenRatio()
    {
        return ((double) wordSet.size()) / document.getNumWords();
    }

    /**
     * Retrieves the ratio between the number of unique words
     * in the document to the total number of words in the document
     * 
     * @return The unique words to total words ratio
     */
    public double getHapaxLegomanaRatio()
    {
        double uniqueWords = 0.0;

        for (Token word : wordSet)
        {
            if (words.indexOf(word) == words.lastIndexOf(word))
            {
                uniqueWords++;
            }
        }

        return uniqueWords / document.getNumWords();
    }

    /**
     * Retrieves the average number of words per Sentence
     * 
     * @return The average number of words per Sentence
     */
    public double getAverageWordsPerSentence()
    {
        return document.getNumWords() / (double) document.getNumSentences();
    }

    /**
     * Retrieves the average number of Phrases per Sentence
     * 
     * @return the average number of Phrases per Sentence
     */
    public double getAverageSentenceComplexity()
    {
        return document.getNumPhrases() / (double) document.getNumSentences();
    }

    /**
     * Calculates the author prediction for the document
     * 
     * @return The author prediction for the document
     */
    public String findAuthor()
    {
        String currentAuthor = "";
        double minScore = Double.MAX_VALUE;
        double currentScore = 0.0;

        for (int i = 0; i < sigStats.size(); i++)
        {
            currentScore = calculateScore(i);
            if (currentScore < minScore)
            {
                currentAuthor = sigStats.get(i).getName();
                minScore = currentScore;
            }
        }
        return "AUTHOR PREDICTION: " + currentAuthor;
    }

    /**
     * Scores the author based on comparisons between the 
     * document statistics and the author's signature statistics
     * 
     * @param authorIndex the index of the author to be compared to
     * @return The score of the author; lower scores mean a higher likelihood of
     *         authorship of the document
     */
    private double calculateScore(int authorIndex)
    {
        double score = 0.0;
        score += Math.abs(avgWordLen * weights[1] 
                          - sigStats.get(authorIndex).getAverageWordLength());
        score += Math.abs(typeTokenRatio * weights[2]
                          - sigStats.get(authorIndex).getTypeTokenRatio());
        score += Math.abs(hapLegoRatio * weights[3] 
                          - sigStats.get(authorIndex).getHapaxLegomannaRatio());
        score += Math.abs(avgSentenceLen * weights[4] 
                          - sigStats.get(authorIndex)
                          .getAverageSentenceLength());
        score += Math.abs(avgSentenceComplexity * weights[5] 
                          - sigStats.get(authorIndex)
                          .getAverageSentenceComplexity());
        return score;

    }

}
