import java.util.*;

/**
 * Creates MyBoundedGrid objects that stores objects in a bounded grid (2D
 * array)
 * 
 * @author Shounak Ghosh
 * @version 3.04.2019
 *
 * @param <E> parameter type placeholder
 */
public class MyBoundedGrid<E>
{
    private int numRows;
    private int numCols;
    private Object[][] grid;
    private ArrayList<Location> locations;

    /**
     * Constructor: Creates MyBoundedGrid objects
     * 
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     */
    public MyBoundedGrid(int rows, int cols)
    {
        numRows = rows;
        numCols = cols;
        grid = new Object[rows][cols];
        locations = new ArrayList<Location>();
    }

    /**
     * Retrieves the number of rows in the grid
     * 
     * @return The number of rows in the grid
     */
    public int getNumRows()
    {
        return numRows;
    }

    /**
     * Retrieves the number of columns in the grid
     * 
     * @return The number of columns in the grid
     */
    public int getNumCols()
    {
        return numCols;
    }

    /**
     * Checks if a given location is possible in the grid Possible locations are
     * (x,y) where 0 <= x < numRows and 0 <= y < numCols
     * 
     * @param loc the location to be checked
     * @return true if the given location is valid; false otherwise
     */
    public boolean isValid(Location loc)
    {
        return loc != null 
               && loc.getRow() >= 0 
               && loc.getCol() >= 0 
               && loc.getRow() < numRows 
               && loc.getCol() < numCols;
    }

    /**
     * Inserts an object at the given location and updates locations accordingly
     * 
     * @param loc the location to be inserted at
     * @param obj the object to be inserted
     * @return the object originally stored at that location (can be null)
     */
    public E put(Location loc, E obj)
    {
        if (isValid(loc))
        {
            Object original = grid[loc.getRow()][loc.getCol()];
            grid[loc.getRow()][loc.getCol()] = (E) obj;
            if (original == null)
            {
                locations.add(loc);
            }
            return (E) original;
        }
        return null;
    }

    /**
     * Removes the object at a given location and updates locations accordingly
     * 
     * @param loc the location to be removed from
     * @return the object that was stored at the location (can be null)
     */
    public E remove(Location loc)
    {
        if (isValid(loc))
        {
            Object val = grid[loc.getRow()][loc.getCol()];
            grid[loc.getRow()][loc.getCol()] = null;

            for (int i = 0; i < locations.size(); i++)
            {
                if (locations.get(i).equals(loc))
                {
                    locations.remove(i);
                    break;
                }
            }
            return (E) val;
        }
        return null;
    }

    /**
     * Retrieves the object at the given location if it is valid
     * 
     * @param loc the location of the object to be retrieved
     * @return the object at the given location
     */
    public E get(Location loc)
    {
        if (isValid(loc))
        {
            return (E) grid[loc.getRow()][loc.getCol()];
        }
        return null;
    }

    /**
     * Retrieves a list representation of all the occupied locations in the grid
     * 
     * @return An ArrayList of all the occupied locations in the grid
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        return locations;
    }

}
