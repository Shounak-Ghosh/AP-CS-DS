import java.util.*;
/**
 * Creates Solitaire objects, 
 * which create an implementation of the Klondike game Solitaire
 * @author Anu Datar
 * @author Shounak Ghosh
 * @version 11.7.2018
 */
public class Solitaire
{
    /**
     * Instantiates new Solitaire object
     * @param args command line arguments
     */
    public static void main(String[] args)
    {  
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations; // for foundations 0-3
    private Stack<Card>[] piles; // for piles 0-6
    private SolitaireDisplay display;
    private int moveCounter;

    /**
     * Creates a new Solitaire object
     * 
     */
    public Solitaire ()
    {
        foundations = new Stack[4];
        piles = new Stack[7];
        for (int i = 0; i < 4; i++)
        {
            foundations[i] = new Stack<Card>();
        }
        for (int i = 0; i < 7; i++)
        {
            piles[i] = new Stack<Card>();
        }

        //INSERT CODE HERE
        stock = new Stack<Card>();

        waste = new Stack<Card>();

        this.createStock();
        this.deal();

        display = new SolitaireDisplay(this);
    }

    //returns the card on top of the stock,
    //or null if the stock is empty
    /**
     * retrieves the card on the top of the stock, 
     * or returns null if stock is empty
     * @return Card the card on the top of the stock
     * 
     */
    public Card getStockCard()
    {
        //throw new RuntimeException("IMPLEMENT ME");
        if (stock.isEmpty())
        {
            return null;
        }
        return stock.peek();
    }

    //returns the card on top of the waste,
    //or null if the waste is empty
    /**
     * retrieves the card on the top of the waste
     * or null if the waste is empty
     * @return the card on the top of the waste
     */
    public Card getWasteCard()
    {
        //throw new RuntimeException("IMPLEMENT ME");
        if (waste.isEmpty())
        {
            return null;
        }
        return waste.peek();
    }

    //precondition:  0 <= index < 4
    //postcondition: returns the card on top of the given
    //               foundation, or null if the foundation
    //               is empty
    /**
     * retrieves the card at the top of the given foundation
     * @precondition 0 <= index < 4
     * @param index the index of the foundation
     * @return the card on the top of the given foundation
     * or null if the foundation is empty
     * 
     */
    public Card getFoundationCard(int index)
    {
        //throw new RuntimeException("IMPLEMENT ME");
        if (foundations[index].isEmpty())
        {
            return null;
        }        
        return foundations[index].peek();
    }

    //precondition:  0 <= index < 7
    //postcondition: returns a reference to the given pile
    /**
     * retrieves the pile at a given index
     * @param index the index of the stack
     * @return the Stack of cards at position index
     */
    public Stack<Card> getPile(int index)
    {
        //throw new RuntimeException("IMPLEMENT ME");
        return piles[index];
    }

    //called when the stock is clicked
     /**
     * Called whenever the stock is clicked
     */
    public void stockClicked()
    {
        //IMPLEMENT ME
        if (!display.isPileSelected() && !display.isWasteSelected())
        {     
            if (!stock.isEmpty())
            {
                this.dealThreeCards();
            }
            else
            {
                this.resetStock();
            }
            moveCounter++;
        }
        
        System.out.println("stock clicked");
    }

    //called when the waste is clicked
    /**
     * Called whenever the waste is clicked
     */
    public void wasteClicked()
    {
        //IMPLEMENT ME
        if (!waste.isEmpty() 
            && !display.isWasteSelected() 
            && !display.isPileSelected())
        {
            display.selectWaste();
        }
        else if (display.isWasteSelected())
        {
            display.unselect();
        }
        System.out.println("waste clicked");
    }

    //precondition:  0 <= index < 4
    //called when given foundation is clicked
    /**
     * Called whenever a foundation is clicked
     * @param index the value of which foundation is clicked
     */
    public void foundationClicked(int index)
    {
        //IMPLEMENT ME
        if (display.isWasteSelected() 
            && canAddToFoundation(getWasteCard(), index))
        {
            foundations[index].push(waste.pop());
            moveCounter++;
            display.unselect();
        }
        else if (display.isPileSelected() && 
                 canAddToFoundation(getPile(display.selectedPile()).peek(), index)) 
        {
            foundations[index].push(getPile(display.selectedPile()).pop());
            moveCounter++;
            display.unselect();
        }
        System.out.println("foundation #" + index + " clicked");
    }

    //precondition:  0 <= index < 7
    //called when given pile is clicked
    /**
     * Called whenever a pile is clicked
     * @param index the index of the pile clicked
     */
    public void pileClicked(int index)
    {
        //IMPLEMENT ME
        if (!getPile(index).isEmpty())
        {
            if (display.isWasteSelected()
                && getWasteCard() != null
                && canAddToPile(waste.peek(), index))
            {
                getPile(index).push(waste.pop());
                moveCounter++;
                display.unselect();
            }
            if (!display.isWasteSelected() 
                && !display.isPileSelected()
                && getPile(index) != null
                && getPile(index).peek().isFaceUp())
            {
                display.selectPile(index);
            }
            else if (index == display.selectedPile())
            {
                display.unselect();
            }
            if (display.isPileSelected() && index != display.selectedPile())
            {
                Stack<Card> temp = removeFaceUpCards(display.selectedPile());
                if (canAddToPile(temp.peek(), index))
                {
                    addToPile(temp, index);
                    moveCounter++;
                    display.unselect();
                }
                else // not able to add to pile
                {
                    addToPile(temp, display.selectedPile());
                }
            }
            if (!display.isWasteSelected() && !display.isPileSelected() 
                && !getPile(index).peek().isFaceUp())
            {
                getPile(index).peek().turnUp();
                //moveCounter++;
                // is flipping a card over a move?
            }
        }
        else
        {
            if (display.isPileSelected() && index != display.selectedPile())
            {
                Stack<Card> temp = removeFaceUpCards(display.selectedPile());
                if (canAddToPile(temp.peek(), index))
                {
                    addToPile(temp, index);
                    moveCounter++;
                    display.unselect();
                }
                else // not able to add to pile
                {
                    addToPile(temp, display.selectedPile());
                }
            }
        }
        System.out.println("pile #" + index + " clicked");
    }

    /**
     * Creates a shuffled stock of cards
     */
    public void createStock()
    {
        ArrayList<Card> deck = new ArrayList<Card>();
        for (int i = 1; i <= 13; i++) 
        {
            deck.add(new Card(i, "c"));
        }
        for (int i = 1; i <= 13; i++)
        {
            deck.add(new Card(i, "d"));
        }
        for (int i = 1; i <= 13; i++)
        {
            deck.add(new Card(i, "s"));
        }
        for (int i = 1; i <= 13; i++)
        {
            deck.add(new Card(i, "h"));
        }
        int cardIndex = 0;
        while (deck.size() != 0)
        {
            cardIndex = (int) (Math.random() * deck.size());
            stock.push(deck.remove(cardIndex));
        }
    }

    /**
     * deals a hand of cards from the stock
     */
    public void deal()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                piles[i].push(stock.pop());
            }
            piles[i].peek().turnUp();
        }
    }

    /**
     * Deals three cards from the stock to the waste
     */
    public void dealThreeCards()
    {
        int counter = 0;
        while (!stock.isEmpty() && counter != 3)
        {
            waste.push(stock.pop());
            waste.peek().turnUp();
            counter++;
        }
    }

    /**
     * Resets the stock by filling the cards from the waste
     */
    public void resetStock()
    {
        while (!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }

    /**
     * checks if a card can legally be added to a pile
     * @param card Card object to be checked
     * @param index position of the pile 
     * @return true if card can be added, false otherwise
     */
    public boolean canAddToPile(Card card, int index)
    {
        // hearts and diamonds are red
        if (getPile(index).isEmpty())// the pile is
        {
            return card.getRank() == 13;
        }
        Card currTopCard = piles[index].peek();
        if (currTopCard.isFaceUp())
        {   
            if (currTopCard.isRed() != card.isRed()) 
            // checks if they are of matching color
            {
                if (currTopCard.getRank() - 1 == card.getRank())
                {
                    return true;
                }
            }
        } // this code block can be simplified
        return false;
    }

    /**
     * removes the face up cards of a pile
     * @param index of the pile
     * @return A stack of face up cards
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> faceUpCards = new Stack<Card>();
        while (!getPile(index).isEmpty() && getPile(index).peek().isFaceUp())
        {
            faceUpCards.push(getPile(index).pop());
        }
        return faceUpCards;
    }

    /**
     * Adds a stack of cards to a pile
     * @param cards the Cards to be added to the pile
     * @param index the position of the pile
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.isEmpty())
        {
            getPile(index).push(cards.pop());
        }
    }

    /**
     * checks if a card can be added to a foundation
     * @param card the card to be checked
     * @param index the position of the foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        if (getFoundationCard(index) == null)
        {
            return card.getRank() == 1;
        }
        else if (getFoundationCard(index).getSuit().equals(card.getSuit()) 
                 && getFoundationCard(index).getRank() + 1 == card.getRank())
        {
            return true;
        }
        return false;
    }

    /**
     * Retrieves the number of moves that have occured
     * @return the moveCounter variables value
     */
    public int getMoveCounter()
    {
        return moveCounter;
    }
    /**
     * Checks if a the player has won the game
     * @return true if player has won, false 
     */
    public boolean checkForWin()
    {
        for (int i = 0; i < 4; i++)
        {
            if (getFoundationCard(i) == null)
            {
                return false;
            }
        }
        for (int i = 0; i < 4; i++)
        {
            if (getFoundationCard(i) != null 
                && getFoundationCard(i).getRank() != 13)
            {
                return false;
            }
        }
        return true;
    }
}