import java.awt.*;
import java.util.*;

// Represesents a rectangular game board, containing Piece objects.
public class Board extends BoundedGrid<Piece>
{
    // Constructs a new Board with the given dimensions
    public Board()
    {
        super(8, 8);
    }

    // Precondition: move has already been made on the board
    // Postcondition: piece has moved back to its source,
    // and any captured piece is returned to its location
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);

        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }

    /**
     * Executes a given move
     * 
     * @param move The move to be executed
     */
    public void executeMove(Move move)
    {
        if (move == null)
        {
            return;
        }

        Piece current = get(move.getSource());

        current.moveTo(move.getDestination());

    }

    public ArrayList<Piece> getPieces(Color color)
    {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        
        
        return pieces;
        
    }

    /**
     * Retrieves a list of all the possible moves for a given color
     * 
     * @param color The color of the player
     * @return An ArrayList of all moves possible for that player
     */
    public ArrayList<Move> allMoves(Color color)
    {
        Location currentLoc;
        Move currentMove;
        Piece currentPiece;

        ArrayList<Move> allPossibleMoves = new ArrayList<>();

        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                currentLoc = new Location(r, c);

                if (get(currentLoc) != null && get(currentLoc).getColor().equals(color))
                {
                    currentPiece = get(currentLoc);

                    for (Location loc : currentPiece.destinations())
                    {
                        if (isValid(loc))
                        {
                            currentMove = new Move(currentPiece, loc);
                            allPossibleMoves.add(currentMove);
                        }

                    }
                }
            }
        }
        return allPossibleMoves;

    }
}