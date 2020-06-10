import java.awt.Color;

/**
 * class BLock encapsulates a Block abstraction which can be placed into a
 * Gridworld style grid You are expected to comment this class according to the
 * style guide.
 * 
 * @author Shounak Ghosh
 * @version 3.04.2019
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;

    /**
     * Constructs a blue block, because blue is the greatest color ever!
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    /**
     * Retrieves the color of the block
     * 
     * @return The color of the block
     */
    public Color getColor()
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        return color;
    }

    /**
     * Mutates the color of the block
     * 
     * @param newColor the new color of the block
     */
    public void setColor(Color newColor)
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        color = newColor;
    }

    /**
     * Retrieves the grid in which the block is stored in
     * 
     * @return The grid in which the block is stored in (can be null)
     */
    public MyBoundedGrid<Block> getGrid()
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        return grid;
    }

    /**
     * Retrieves the location of the block in the grid it is stored in
     * 
     * @return The location of the block in the grid
     */
    public Location getLocation()
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        return location;
    }

    /**
     * Removes the block from the grid it is stored in
     */
    public void removeSelfFromGrid()
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        if (grid != null)
        {
            grid.remove(location);
        }
        grid = null;
        location = null;

    }

    /**
     * Inserts the block into the given grid at the given location
     * 
     * @param gr  the grid to be inserted into
     * @param loc the location to be inserted at
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        grid = gr;
        location = loc;
        Block removed = grid.remove(loc);
        
        if (removed != null)
        {
            removed.removeSelfFromGrid();
        }
        grid.put(loc, this);
    }

    /**
     * Moves the Block to a new location in the grid
     * 
     * @param newLocation the new location of the block in the grid
     */
    public void moveTo(Location newLocation)
    {
        // throw new RuntimeException("INSERT MISSING CODE HERE");
        MyBoundedGrid<Block> theGrid = grid;
        removeSelfFromGrid();
        putSelfInGrid(theGrid, newLocation);
    }

    /**
     * Retrieves a String representation of the Block
     * 
     * @return A String representation of the Block
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}