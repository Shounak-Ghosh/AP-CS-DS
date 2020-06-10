import java.awt.Color;
import java.util.concurrent.Semaphore;

/**
 * Tetrad objects consist of four Block objects in one of the seven Tetrad
 * shapes
 * 
 * @author Shounak Ghosh
 * @version 3.16.2019
 */
public class Tetrad
{
    // global variables
    private Block[] tetrad =
    { new Block(), new Block(), new Block(), new Block() };
    private MyBoundedGrid<Block> grid;
    private Semaphore lock;
    private Location[] locations;
    private Color c;
    private boolean flag;

    /**
     * Constructor: Creates randomly shaped Tetrad objects and places them into a
     * given grid
     * 
     * @param gr the grid the tetrad will be put in
     */
    public Tetrad(MyBoundedGrid<Block> gr)
    {
        grid = gr;
        int choice = (int) (Math.random() * 7) + 1;
        c = Color.BLACK;
        lock = new Semaphore(1, true);
        if (choice == 1)
        {
            c = Color.RED;
            Location[] locs =
            { new Location(2, 5), new Location(1, 5), new Location(3, 5), new Location(4, 5) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 2)
        {
            c = Color.LIGHT_GRAY;
            Location[] locs =
            { new Location(1, 5), new Location(1, 4), new Location(1, 6), new Location(2, 5) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 3)
        {
            c = Color.CYAN;
            Location[] locs =
            { new Location(1, 5), new Location(1, 6), new Location(2, 6), new Location(2, 5) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 4)
        {
            c = Color.YELLOW;
            Location[] locs =
            { new Location(2, 5), new Location(1, 5), new Location(3, 5), new Location(3, 6) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 5)
        {
            c = Color.MAGENTA;
            Location[] locs =
            { new Location(2, 6), new Location(1, 6), new Location(3, 6), new Location(3, 5) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 6)
        {
            c = Color.BLUE;
            Location[] locs =
            { new Location(1, 5), new Location(1, 6), new Location(2, 5), new Location(2, 4) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
        else if (choice == 7)
        {
            c = Color.GREEN;
            Location[] locs =
            { new Location(1, 6), new Location(1, 5), new Location(2, 6), new Location(2, 7) };
            if (areEmpty(grid, locs))
            {
                this.addToLocations(grid, locs);
            }
            else
            {
                flag = true;
            }
            for (int i = 0; i < 4; i++)
            {
                tetrad[i].setColor(c);
            }
        }
    }

    /**
     * Checks if this spawns in four empty locations
     * 
     * @return true if the tetrad has been created in four empty locations; false
     *         otherwise
     */
    public boolean getFlag()
    {
        return flag;
    }

    /**
     * Retrieves the color of this
     * 
     * @return The color of this
     */
    public Color getColor()
    {
        return c;
    }

    /**
     * Add this to the given locations in the given grid
     * 
     * @param gr   the grid
     * @param locs
     */
    private void addToLocations(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for (int i = 0; i < locs.length; i++)
        {
            tetrad[i].putSelfInGrid(gr, locs[i]);
        }
    }

    /**
     * Removes the the Blocks that form the tetrad from the grid
     * 
     * @return An array of Location objects from which the blocks were removed
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for (int i = 0; i < 4; i++)
        {
            if (tetrad[i].getLocation() != null)
            {
                locs[i] = tetrad[i].getLocation();
            }
            tetrad[i].removeSelfFromGrid();
        }
        return locs;
    }

    /**
     * Checks if a the given Locations in a given grid are occupied
     * 
     * @param gr   the grid
     * @param locs Location[] to be checked
     * @return true if all given Locations are empty; false otherwise
     */
    private boolean areEmpty(MyBoundedGrid<Block> gr, Location[] locs)
    {

        for (int i = 0; i < 4; i++)
        {
            if (!gr.isValid(locs[i]) || grid.get(locs[i]) != null)
            {
                return false;
            }
        }
        return true;

    }

    /**
     * Moves the tetrad horizontally and/or vertically in the grid
     * 
     * @param deltaRow The number of rows to be shifted
     * @param deltaCol The number of columns to be shifted
     * @return true if this was moved; false otherwise
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            Location[] prev = removeBlocks();
            Location[] next = new Location[4];

            for (int i = 0; i < 4; i++)
            {
                if (prev[i] != null)
                {
                    next[i] = new Location(prev[i].getRow() + deltaRow, prev[i].getCol() + deltaCol);
                    // System.out.println(next[i]);
                }

            }
            if (areEmpty(grid, next))
            {
                addToLocations(grid, next);
            }
            else
            {
                addToLocations(grid, prev);
                return false;
            }
            return true;
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        }
    }

    /**
     * Rotates the tetrad
     * 
     * @return true if this was rotated; false otherwise
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            Location[] prev = removeBlocks();
            Location[] next = new Location[4];

            for (int i = 0; i < 4; i++)
            {
                next[i] = new Location(prev[0].getRow() - prev[0].getCol() + prev[i].getCol(),
                        prev[0].getRow() + prev[0].getCol() - prev[i].getRow());
            }

            if (tetrad[0].getColor() != Color.CYAN && areEmpty(grid, next))
            {
                addToLocations(grid, next);
            }
            else
            {
                addToLocations(grid, prev);
                return false;
            }
            return true;
        }
        catch (InterruptedException e)
        {
            // did not modify the tetrad
            return false;
        }
        finally
        {
            lock.release();
        }
    }

    /**
     * Retrieves all the Locations of the tetrad in the current grid
     * 
     * @return An array of Location Objects
     */
    public Location[] getLocations()
    {
        return locations;
    }

}
