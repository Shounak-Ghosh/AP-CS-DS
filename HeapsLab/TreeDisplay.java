import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/**
 * A graphical component for displaying the contents of a binary tree.
 * The following methods are designed for interacting with the display:
 *      Default constructor TreeDisplay()
 *      displayTree(TreeNode someTree)
 *      visit(TreeNode someNode)
 * The displayTree method creates the initial display of the tree.  It clears the component
 * and paints the tree
 * The visit method changes the background color of the node defined in the parameter
 * sample Useage:
 * TreeDisplay display = new TreeDisplay();
 * display.displayTree(someTree);
 * display.visit(someNode);
 * 
 * @author DaveF
 * @author RichardP
 * @version 102613
 *
 */
public class TreeDisplay extends JComponent
{
    //number of pixels between text and edge
    private static final int ARC_PAD = 2;

    //the tree being displayed
    private TreeNode root = null;

    //the node last visited
    private TreeNode visiting = null;

    //the set of all nodes visited so far
    private Set<TreeNode> visited = new HashSet<TreeNode>();

    //number of milliseconds to pause when visiting a node
    private int delay = 500;
    
    private BinaryTreeTester tester;

    //creates a frame with a new TreeDisplay component.
    //(constructor returns the TreeDisplay component--not the frame).
    public TreeDisplay()
    {
        tester = null;
        //create surrounding frame
        JFrame frame = new JFrame("Tree Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add the TreeDisplay component to the frame
        frame.getContentPane().add(this);

        //show frame
        frame.pack();
        frame.setVisible(true);

        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                TreeDisplay.this.repaint();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    //tells the frame the default size of the tree
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 300);
    }

    //called whenever the TreeDisplay must be drawn on the screen
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        Dimension d = getSize();

        //draw white background
        g2.setPaint(Color.darkGray);
        g2.fill(new Rectangle2D.Double(0, 0, d.width, d.height));

        int depth = TreeUtil.maxDepth(root);

        if (depth == 0)
            //no tree to draw
            return;

        //hack to avoid division by zero, if only one level in tree
        if (depth == 1)
            depth = 2;

        //compute the size of the text
        FontMetrics font = g2.getFontMetrics();
        int leftPad = font.stringWidth(
            TreeUtil.leftmost(root) + "") / 2;
        int rightPad = font.stringWidth(
            TreeUtil.rightmost(root) + "") / 2;
        int textHeight = font.getHeight();

        //draw the actual tree
        drawTree(g2, root, leftPad + ARC_PAD,
                    d.width - rightPad - ARC_PAD,
                    textHeight / 2 + ARC_PAD,
                    (d.height - textHeight - 2 * ARC_PAD) / (depth - 1));
    }

    //draws the tree, starting from the given node, in the region with x values ranging
    //from minX to maxX, with y value beginning at y, and next level at y + yIncr.
    private void drawTree(Graphics2D g2, TreeNode t, int minX, int maxX, int y, int yIncr)
    {
        //skip if empty
        if (t == null)
            return;

        //compute useful coordinates
        int x = (minX + maxX) / 2;
        int nextY = y + yIncr;

        //draw black lines
        g2.setPaint(Color.black);
        if (t.getLeft() != null)
        {
            int nextX = (minX + x) / 2;
            g2.draw(new Line2D.Double(x, y, nextX, nextY));
        }
        if (t.getRight() != null)
        {
            int nextX = (x + maxX) / 2;
            g2.draw(new Line2D.Double(x, y, nextX, nextY));
        }

        //measure text
        FontMetrics font = g2.getFontMetrics();
        String text = t.getValue() + "";
        int textHeight = font.getHeight();
        int textWidth = font.stringWidth(text);

        //draw the box around the node
        Rectangle2D.Double box = new Rectangle2D.Double(
            x - textWidth / 2 - ARC_PAD, y - textHeight / 2 - ARC_PAD,
            textWidth + 2 * ARC_PAD, textHeight + 2 * ARC_PAD);//, ARC_PAD, ARC_PAD);
        Color c;
        //color depends on whether we haven't visited, are visiting, or have visited.
        if (t == visiting)
            c = Color.RED;
            else if (visited.contains(t))
            c = Color.GREEN;
        else
            c = Color.CYAN;  //c = new Color(187, 224, 227);
       
        g2.setPaint(c);
        g2.fill(box);
        //draw black border
        g2.setPaint(Color.black);
        g2.draw(box);

        //draw text
        g2.drawString(text, x - textWidth / 2, y + textHeight / 2);

        //draw children
        drawTree(g2, t.getLeft(), minX, x, nextY, yIncr);
        drawTree(g2, t.getRight(), x, maxX, nextY, yIncr);
    }
    /**
     * displayTree tells the component to switch to displaying the given tree
     * @param root is the root of the tree to display
     * postcondition: the component displays the tree defined by root
     */
    public  void displayTree(TreeNode root)
    {
        this.root = root;

        //signal that the display needs to be redrawn
        repaint();
    }
    /**
     * visit changes the background color of the node given in the parameter to yellow,
     * lighting up the node to indicate we are visiting it.
     * @param t the node to light up
     * postcondition: the background color on the display for the selected node is changed to 
     *                yellow
     */
    public void visit(TreeNode t)
    {
        //if we've already visited it, we assume this is a new traversal,
        //and reset the set of visited nodes.
        if (visited.contains(t))
            visited = new HashSet<TreeNode>();

        //update visiting and visited
        visiting = t;
        visited.add(t);

        //signal that the display needs to be redrawn
        repaint();

        //pause, so you can see the traversal
        try
        {
            Thread.sleep(delay);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        // check to see if the tester is present
        if(tester != null)
            tester.sendValue(t.getValue());
    }

    //change the length of time in milliseconds
    //to pause when visiting a node
    public void setDelay(int delay)
    {
        this.delay = delay;
    }
    
    public void setTester(BinaryTreeTester tester)
    {
        this.tester = tester;
    }
}