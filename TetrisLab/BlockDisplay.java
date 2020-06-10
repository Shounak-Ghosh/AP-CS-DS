import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Anu Datar
 * 
 *         Changed block size and added a split panel display for next block and
 *         Score
 * 
 * @author Ryan Adolf
 * @version 1.0
 * 
 *          Fixed the lag issue with block rendering Removed the JPanel
 */
// Used to display the contents of a game board
public class BlockDisplay extends JComponent implements KeyListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BORDER = Color.BLACK;

    private static final int OUTLINE = 2;
    private static final int BLOCKSIZE = 20;

    private MyBoundedGrid<Block> board;
    private JFrame frame;
    private ArrowListener listener;

    // Constructs a new display for displaying the given board
    public BlockDisplay(MyBoundedGrid<Block> board)
    {
        this.board = board;

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });

        // Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        // Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);

        // Display the window.
        this.setPreferredSize(new Dimension(BLOCKSIZE * board.getNumCols(), BLOCKSIZE * board.getNumRows()));

        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(BORDER);
        g.fillRect(0, 0, BLOCKSIZE * board.getNumCols() + OUTLINE, BLOCKSIZE * board.getNumRows());
        for (int row = 0; row < board.getNumRows(); row++)
            for (int col = 0; col < board.getNumCols(); col++)
            {
                Location loc = new Location(row, col);

                Block square = board.get(loc);

                if (square == null)
                    g.setColor(BACKGROUND);
                else if (square.getColor() == Color.BLACK || square.getColor() == Color.WHITE)
                    g.setColor(square.getColor().darker());
                else if (square.getColor() == Color.LIGHT_GRAY)
                {
                    g.setColor(square.getColor());
                }
                else
                {
                    g.setColor(square.getColor().brighter());
                }

                // Image image = new ImageIcon("blue.gif").getImage();

                // g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);

                g.fill3DRect(col * BLOCKSIZE + OUTLINE / 2, row * BLOCKSIZE + OUTLINE / 2, BLOCKSIZE - OUTLINE,
                        BLOCKSIZE - OUTLINE, true);
                if (square != null && square.getColor() != Color.BLACK && square.getColor() != Color.WHITE)
                {
                    g.setColor(Color.DARK_GRAY);

                    g.draw3DRect(col * BLOCKSIZE + OUTLINE / 2, row * BLOCKSIZE + OUTLINE / 2, BLOCKSIZE - OUTLINE,
                            BLOCKSIZE - OUTLINE, true);
                }

                // fill3DRect(int x, int y, int width, int height, boolean raised)

                // fillRect(int x, int y, int width, int height)
            }

    }

    // Redraws the board to include the pieces and border colors.
    public void showBlocks()
    {
        repaint();
    }

    // Sets the title of the window.
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if (listener == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
            listener.leftPressed();
        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
            listener.rightPressed();
        else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
            listener.downPressed();
        else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
            listener.upPressed();
        else if (code == KeyEvent.VK_SPACE)
            listener.spacePressed();
        else if (code == KeyEvent.VK_P)
            listener.pPressed();
        else if (code == KeyEvent.VK_R)
            listener.rPressed();
        else if (code == KeyEvent.VK_M)
            listener.mPressed();
        else if (code == KeyEvent.VK_Q)
            listener.qPressed();

    }

    public void setArrowListener(ArrowListener listener)
    {
        this.listener = listener;
    }
}
