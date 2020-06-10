import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Driver class that runs the Tetris game
 * 
 * @author Shounak Ghosh
 * @version 3.16.2019
 *
 */
public class TetrisRunner
{
    /**
     * Driver method
     * 
     * @param args Command-line argument
     * @throws LineUnavailableException      Thrown if there is an error with the
     *                                       audio
     * @throws IOException                   Thrown if there is an error with file
     *                                       input/output
     * @throws UnsupportedAudioFileException Thrown if there is an error with the
     *                                       audio file
     */
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException
    {

        Tetris t = new Tetris();
        instructions();

        while (!t.gameOver())
        {
            t.play();
            if (t.getRestart())
            {
                t.resetGame();
                t = new Tetris();
            }
        }
        t.clearGameBoard();
        System.out.println("Your final score was " + t.getScore());
    }

    /**
     * Prints out the instructions for playing the game
     */
    public static void instructions()
    {
        System.out.println("Welcome to Tetris! (Shounak Edition) \n " + "\n INSTUCTIONS"
                + "\n Arrow keys or WASD for movement. " + "\n P \t Pause the game." + "\n R \t Restart the game."
                + "\n M \t Turn off Volume. " + "\n Q \t Quit game." + "\n Space \t Auto-Drop." + "\n");
    }
}
