
/**
 * 
 * @author Shounak Ghosh
 * @version 3.30.2019
 */
public class AuthorStats
{
    private String name;
    private double avgWordLen;
    private double ttr;
    private double hpr;
    private double avgSentLen;
    private double avgSentComp;
    private double[] weights = { 0.0, 11.0, 33.0, 50.0, 0.4, 4.0 };
    
    /**
     * @param n The name of the author
     * @param awl The average word length of the author
     * @param ttr The type-token ratio of the author
     * @param hpr The Hapax-Legomana ratio of the author
     * @param avgSenLen The average sentence length of the author
     * @param avgSenComp The average sentence complexity of the author
     */
    public AuthorStats(String n, double awl, double ttr, double hpr,
                       double avgSenLen, double avgSenComp)
    {
        name = n;
        avgWordLen = awl;
        this.ttr = ttr;
        this.hpr = hpr;
        avgSentLen = avgSenLen;
        avgSentComp = avgSenComp;
    }
    
    /**
     * Retrieves the name of the author
     * @return the name of the author
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * Retrieves the average word length of the author, 
     * mutiplied by the weights
     * @return the average word length of the author, 
     * times the multiplicative weights
     */
    public double getAverageWordLength() 
    {
        return avgWordLen * weights[1];
    }
    
    /**
     * Retrieves the type-token ratio of the author, 
     * mutiplied by the weights
     * @return the type-token ratio of the author, 
     * times the multiplicative weights
     */
    public double getTypeTokenRatio() 
    {
        return ttr * weights[2];
    }
    
    /**
     * Retrieves the Hapax-Legomana ratio of the author, 
     * mutiplied by the weights
     * @return the Hapax-Legomana ratio of the author, 
     * times the multiplicative weights
     */
    public double getHapaxLegomannaRatio() 
    {
        return hpr * weights[3];
    }
    
    /**
     * Retrieves the average sentence length of the author, 
     * mutiplied by the weights
     * @return the average sentence length of the author, 
     * times the multiplicative weights
     */
    public double getAverageSentenceLength() 
    {
        return avgSentLen * weights[4];
    }
    
    /**
     * Retrieves the average sentence complexity of the author, 
     * mutiplied by the weights
     * @return the average sentence complexity of the author, 
     * times the multiplicative weights
     */
    public double getAverageSentenceComplexity() 
    {
        return avgSentComp * weights[5];
    }
    
    /**
     * Retrieves a String represnetation of the object 
     * @return the author's name
     */
    public String toString() 
    {
        return name;
    }
}
