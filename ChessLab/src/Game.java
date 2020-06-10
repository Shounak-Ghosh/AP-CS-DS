import java.awt.Color;
import java.util.ArrayList;

/**
 * Driver class that plays a game of chess between different types of Players
 * 
 * @author Shounak Ghosh
 * @version 4.30.2019
 *
 */
public class Game
{
    private static boolean isGameOver;

    /**
     * Driver method; Sets up the board for play
     * 
     * @param args command-line argument
     */
    public static void main(String[] args)
    {
        Board board = new Board();
        isGameOver = false;

        // place black king
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));

        // place white king
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        // place the black queen
        Piece blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));

        // place white queen
        Piece whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));

        // place the black rooks
        Piece alphaBlackRook = new Rook(Color.BLACK, "black_rook.gif");
        alphaBlackRook.putSelfInGrid(board, new Location(0, 0));
        Piece betaBlackRook = new Rook(Color.BLACK, "black_rook.gif");
        betaBlackRook.putSelfInGrid(board, new Location(0, 7));

        // place the white rooks
        Piece alphaWhiteRook = new Rook(Color.WHITE, "white_rook.gif");
        alphaWhiteRook.putSelfInGrid(board, new Location(7, 0));
        Piece betaWhiteRook = new Rook(Color.WHITE, "white_rook.gif");
        betaWhiteRook.putSelfInGrid(board, new Location(7, 7));

        // place the black bishops
        Piece alphaBlackBishop = new Bishop(Color.BLACK, "black_bishop.gif");
        alphaBlackBishop.putSelfInGrid(board, new Location(0, 5));

        Piece betaBlackBishop = new Bishop(Color.BLACK, "black_bishop.gif");
        betaBlackBishop.putSelfInGrid(board, new Location(0, 2));

        // place the white bishops
        Piece alphaWhiteBishop = new Bishop(Color.WHITE, "white_bishop.gif");
        alphaWhiteBishop.putSelfInGrid(board, new Location(7, 5));

        Piece betaWhiteBishop = new Bishop(Color.WHITE, "white_bishop.gif");
        betaWhiteBishop.putSelfInGrid(board, new Location(7, 2));

        // place the black knights
        Piece alphaBlackKnight = new Knight(Color.BLACK, "black_knight.gif");
        alphaBlackKnight.putSelfInGrid(board, new Location(0, 1));

        Piece betaBlackKnight = new Knight(Color.BLACK, "black_knight.gif");
        betaBlackKnight.putSelfInGrid(board, new Location(0, 6));

        // place the white knights
        Piece alphaWhiteKnight = new Knight(Color.WHITE, "white_knight.gif");
        alphaWhiteKnight.putSelfInGrid(board, new Location(7, 1));

        Piece betaWhitekKnight = new Knight(Color.WHITE, "white_knight.gif");
        betaWhitekKnight.putSelfInGrid(board, new Location(7, 6));

        // place the black pawns
        Piece blackPawnOne = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnOne.putSelfInGrid(board, new Location(1, 0));

        Piece blackPawnTwo = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnTwo.putSelfInGrid(board, new Location(1, 1));

        Piece blackPawnThree = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnThree.putSelfInGrid(board, new Location(1, 2));

        Piece blackPawnFour = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnFour.putSelfInGrid(board, new Location(1, 3));

        Piece blackPawnFive = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnFive.putSelfInGrid(board, new Location(1, 4));

        Piece blackPawnSix = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnSix.putSelfInGrid(board, new Location(1, 5));

        Piece blackPawnSeven = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnSeven.putSelfInGrid(board, new Location(1, 6));

        Piece blackPawnEight = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawnEight.putSelfInGrid(board, new Location(1, 7));

        // place the white pawns
        Piece whitePawnOne = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnOne.putSelfInGrid(board, new Location(6, 0));

        Piece whitePawnTwo = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnTwo.putSelfInGrid(board, new Location(6, 1));

        Piece whitePawnThree = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnThree.putSelfInGrid(board, new Location(6, 2));

        Piece whitePawnFour = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnFour.putSelfInGrid(board, new Location(6, 3));

        Piece whitePawnFive = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnFive.putSelfInGrid(board, new Location(6, 4));

        Piece whitePawnSix = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnSix.putSelfInGrid(board, new Location(6, 5));

        Piece whitePawnSeven = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnSeven.putSelfInGrid(board, new Location(6, 6));

        Piece whitePawnEight = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawnEight.putSelfInGrid(board, new Location(6, 7));

        // display the board
        BoardDisplay display = new BoardDisplay(board);

        RandomPlayer randPlayer = new RandomPlayer(board, "Randolph",
                                                   Color.BLACK);
//        Move randMove = randPlayer.nextMove();
//        display.setColor(randMove.getSource(), Color.GREEN);
//        display.setColor(randMove.getDestination(), Color.GREEN);
//        
//        nextTurn(board, display, randPlayer);
//        
        RandomPlayer randomPlayer = new RandomPlayer(board, "Blitzen",
                                                     Color.WHITE);

        HumanPlayer humanPlayerOne = new HumanPlayer(board, "Player White",
                                                     Color.WHITE, display);
        HumanPlayer humanPlayerTwo = new HumanPlayer(board, "Player Black",
                                                     Color.BLACK, display);
        SmartPlayer smartPlayer = new SmartPlayer(board, "Smart Player",
                                                  Color.BLACK, display);

        // play(board, display, randomPlayer, randPlayer);

        // play(board, display, humanPlayerOne, randPlayer);

        play(board, display, humanPlayerOne, smartPlayer);

        display.showBoard();
    }

    /**
     * Plays a game between two players
     * 
     * @param board   The board to be played on
     * @param display The BoardDisplay object used to display/update the board
     * @param white   The white player; goes first
     * @param black   The black player
     */
    public static void play(Board board, BoardDisplay display, 
                            Player white, Player black)
    {
        while (!isGameOver) // remove later
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
        System.out.println("Game over.");
    }

    /**
     * Executes the next turn of a player
     * 
     * @param board   The board being played on
     * @param display The BoardDisplay object used to display/update the board
     * @param player  The player whose turn is being played out
     */
    private static void nextTurn(Board board,
                                 BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        Move currentMove = player.nextMove();
        board.executeMove(currentMove);
        display.clearColors();

        if (currentMove == null)
        {
            isGameOver = true;
            return;
        }

        display.setColor(currentMove.getSource(), Color.CYAN);
        display.setColor(currentMove.getDestination(), Color.GREEN); 

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        display.clearColors();

    }
}
