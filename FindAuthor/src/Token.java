

/**
 * The Token class creates Token objects of different token types
 * @author Shounak Ghosh
 * @version 2.20.2019
 */
final class Token 
{
    private Scanner.TOKEN_TYPE type;
    private String token;

    /**
     * Constructor: Creates Token objects
     * @param obj the type of token to be created
     * @param token the actual token itself
     */
    public Token(Scanner.TOKEN_TYPE obj, String token) 
    {
        type = obj;
        this.token = token;
    }

    /**
     * Retrieves the token 
     * @return the String stored in the token variable
     */
    public String getToken() 
    {
        return token;
    }
    
    /**
     * Retrieves the token type
     * @return the type of the token
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }

    /**
     * Retrieves a String representation of the Token object
     * @return a String representation of the Token object
     */
    public String toString() 
    {
        return "Token: " + token + "\t Type: " + type;
    }

    /**
     * Checks if a given object is equal to this
     * @param other the Object that is checked for equality
     * @return true if this and other have the same type 
     *         and their tokens are equal; false otherwise
     */
    public boolean equals(Object other) 
    {
        if (!(other instanceof Token)) 
        {
            return false;
        }

        Token t = (Token) other;

        return token.equals(t.getToken());
    }

    /**
     * Retrieves the hashValue of the Token Object
     * @return an integer representation of the token Object
     */
    public int hashCode() 
    {
        return token.hashCode();
    }

}