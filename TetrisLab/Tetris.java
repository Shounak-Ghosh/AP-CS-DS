import java.awt.Color;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

/**
 * Creates a Tetris game
 * 
 * @author Shounak Ghosh
 * @version 3.16.2019
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad current;
    private boolean lock;
    private boolean gameOver;
    private boolean paused;
    private int rowsCleared;
    private int currentLevel;
    private int score;
    private int totalRowsCleared;
    private double sleep;
    private boolean restart;
    private boolean muted;
    private Clip clip;
    private Clip endGame; // AVENGERS!

    /**
     * Constructor: Creates Tetris objects
     * 
     * @throws LineUnavailableException      Thrown if there is an error with the
     *                                       audio
     * @throws IOException                   Thrown if the file cannot be found
     * @throws UnsupportedAudioFileException Thrown if the given file cannot be
     *                                       played
     */
    public Tetris() throws LineUnavailableException, IOException, UnsupportedAudioFileException
    {
        paused = false;
        gameOver = false;
        restart = false;
        muted = false;
        currentLevel = 1;
        rowsCleared = 0;
        sleep = 1000.0;

        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("TetrisSong.wav")));
        clip.start();

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        endGame = AudioSystem.getClip();
        endGame.open(AudioSystem.getAudioInputStream(new File("Airhorn.wav")));

        grid = new MyBoundedGrid(22, 12);
        Location loc;
        Block b;

        Color border = Color.WHITE;
        for (int r = 0; r < 22; r++)
        {
            loc = new Location(r, 0);
            b = new Block();
            b.putSelfInGrid(grid, loc);
            grid.get(loc).setColor(border);
        }

        for (int c = 0; c < 12; c++)
        {
            loc = new Location(0, c);
            b = new Block();
            b.putSelfInGrid(grid, loc);
            grid.get(loc).setColor(border);
        }

        for (int c = 0; c < 12; c++)
        {
            loc = new Location(21, c);
            b = new Block();
            b.putSelfInGrid(grid, loc);
            grid.get(loc).setColor(border);
        }

        for (int r = 0; r < 22; r++)
        {
            loc = new Location(r, 11);
            b = new Block();
            b.putSelfInGrid(grid, loc);
            grid.get(loc).setColor(border);
        }

        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        current = new Tetrad(grid);
        display.showBlocks();
    }

    /**
     * Response when the up arrow key or the 'W' key has been pressed
     */
    public void upPressed()
    {
        // current.translate(-1,0);
        if (!paused)
        {
            current.rotate();
            display.showBlocks();
        }
    }

    /**
     * Response when the down arrow key or the 'S' key has been pressed
     */
    public void downPressed()
    {
        lock = false;
        if (!paused)
        {
            current.translate(1, 0);
            display.showBlocks();
        }
    }

    /**
     * Response when the right arrow key or the 'D' key has been pressed
     */
    public void rightPressed()
    {
        lock = false;
        if (!paused)
        {
            current.translate(0, 1);
            display.showBlocks();
        }
    }

    /**
     * Response when the left arrow key or the 'A' key has been pressed
     */
    public void leftPressed()
    {
        lock = false;
        if (!paused)
        {
            current.translate(0, -1);
            display.showBlocks();
        }
    }

    /**
     * Response when the space bar has been pressed
     */
    public void spacePressed()
    {
        lock = false;
        if (!paused)
        {
            boolean bottomReached = false;
            while (!bottomReached)
            {
                if (!current.translate(1, 0))
                {
                    bottomReached = true;
                }
            }
            display.showBlocks();
        }
    }

    /**
     * Response when the 'P' key has been pressed
     */
    public void pPressed()
    {
        if (paused)
        {
            paused = false;
            if (!muted)
            {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            System.out.println("Game unpaused. Press P to pause.");
        }
        else
        {
            paused = true;
            clip.stop();
            System.out.println("Game paused. Press P to resume.");
        }
        lock = false;
        display.showBlocks();
    }

    /**
     * Response when the 'R' key has bee pressed
     */
    public void rPressed()
    {
        restart = true;
    }

    /**
     * Response when the 'M' key has been pressed
     */
    public void mPressed()
    {
        muted = !muted;
        lock = false;
        if (muted)
        {
            clip.stop();
        }
        else
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }

    public void qPressed()
    {
        if (!lock)
        {
            System.out.println("Confirm quit by pressing 'Q' again. Press 'P' to resume.");
            paused = true;
            clip.stop();
            lock = true;
        }
        else
        {
            System.out.println("Exited Succesfully.");
            System.exit(0);
        }
    }

    /**
     * Clears the game board and stops the music
     */
    public void resetGame()
    {
        if (restart)
        {
            System.out.print("Game restarting...");
            clearGameBoard();
            System.out.print(" Restarted.");
            System.out.println();
            clip.stop();
            restart = false;
            // TODO: Figure out how to close game window upon resetting
        }
    }

    /**
     * Retrieves the current score
     * 
     * @return The current score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Retrieves restart
     * 
     * @return true if restart is true; false otherwise
     */
    public boolean getRestart()
    {
        return restart;
    }

    /**
     * Compute the number of points to be added to the score
     * 
     * @return The number of points to be added to the score
     */
    private int computeCurrentScore()
    {
        if (totalRowsCleared > 0 && totalRowsCleared % 10 == 0)
        {
            currentLevel++;
            rowsCleared = 0;
        }
        int num = 40 * rowsCleared * rowsCleared; // TODO: Find a better formula
        return num * currentLevel;
    }

    /**
     * Plays one turn of the game
     */
    public void play()
    {

        try
        {
            // Pause for sleep number of milliseconds
            Thread.sleep((int) sleep);
        }
        catch (InterruptedException e)
        {
            // ignore
        }
        if (!paused)
        {
            if (!current.translate(1, 0))
            {
                current = new Tetrad(grid);
            }
            if (current.getFlag())
            {
                gameOver = true;
                paused = true;
                clip.stop();
                endGame.start();
                System.out.println("Game over. :( ");
            }
            if (clearCompletedRows() > 0)
            {
                int num = computeCurrentScore();
                // System.out.println("Tot Rows Cleared: " + totalRowsCleared);
                // System.out.println("Current Level: " + currentLevel);
                // System.out.println("Current Rows: " + rowsCleared);
                // System.out.println("Computed: " + num);
                score += num;
                // System.out.println("Score: " + score);
                // System.out.println();
                if (sleep > 150.0)
                {
                    sleep = sleep * .9;
                }
                // System.out.println("Sleep time: " + sleep);
            }
            display.showBlocks();
        }

    }

    /**
     * Checks if a row is complete, not including the boundaries
     * 
     * @param row the row to be check
     * @return true if the row is complete
     */
    private boolean isCompletedRow(int row)
    {
        Location loc;

        for (int col = 1; col < grid.getNumCols() - 1; col++)
        {
            loc = new Location(row, col);
            if (grid.get(loc) == null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Clears the given row of Blocks and moves all the Blocks above it down one
     * Location
     * 
     * @precondition The given row is complete
     * @param row The row to be cleared
     */
    private void clearRow(int row)
    {
        int len = grid.getNumCols();
        Location temp;
        for (int i = 1; i < len - 1; i++)
        {
            temp = new Location(row, i);
            if (temp != null)
            {
                grid.get(temp).removeSelfFromGrid();
            }
        }

        for (int r = row - 1; r >= 1; r--)
        {
            for (int c = 1; c < len - 1; c++)
            {
                temp = new Location(r, c);
                // System.out.println(grid + " " + temp + " " + grid.get(temp));
                if (grid.get(temp) != null)
                {
                    // System.out.println("reached: " + temp);
                    int index = 0;
                    while (grid.get(new Location(r + index, c)) == null)
                    {
                        index++;
                    }
                    // System.out.println("new Row: " + (r + index));
                    grid.get(temp).moveTo(new Location(temp.getRow() + index + 1, c));
                }
            }
        }
    }

    private int clearCompletedRows()
    {
        int num = 0;
        for (int r = grid.getNumRows() - 2; r >= 1; r--)
        {
            if (isCompletedRow(r))
            {
                clearRow(r);
                num++;
                rowsCleared++;
                totalRowsCleared++;
            }
        }
        return num;
    }

    /**
     * Checks if the game is over
     * 
     * @return true if the game is over; false otherwise
     */
    public boolean gameOver()
    {
        return gameOver;
    }

    /**
     * Clears the game board
     */
    public void clearGameBoard()
    {
        Location loc = new Location(0, 0);
        for (int r = 0; r < grid.getNumRows(); r++)
        {
            for (int c = 0; c < grid.getNumCols(); c++)
            {
                loc = new Location(r, c);
                if (grid.get(loc) != null)
                {
                    grid.get(loc).removeSelfFromGrid();
                    display.showBlocks();
                    try
                    {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e)
                    {
                        // ignore
                    }
                }
            }
        }
        clip.stop();
    }
}
