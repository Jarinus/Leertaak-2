package nl.hanze.sjet.model;

import java.util.Random;

/**
 * Represent a location in a rectangular grid.
 * 
 * @author David J. Barnes, Michael Kölling and Jan A. Germeraad
 * @version 03-02-2015
 */
public class Location
{
    // Row and column positions.
    private int row;
    private int col;
    private static final Random rand = Randomizer.getRandom();
    private int foodValue;

    /**
     * Represent a row and column.
     * @param row The row.
     * @param col The column.
     */
    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
        foodValue = (10 + rand.nextInt(9));
    }
    
    /**
     * Implement content equality.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return row == other.getRow() && col == other.getCol();
        }
        else {
            return false;
        }
    }
    
    /**
     * Increases the foodValue for the Location.
     */
    public void incrementFoodValue() {
    	int temp = (int) (1 + (2 * rand.nextDouble()));
    	foodValue += temp;
    }
    
    /**
     * Signifies an Animal eating grass.
     * Thus, reducing the location's foodValue and return whether it succeeded
     * @return Whether the animal succeeded in eating from the location.
     */
    public boolean eatGrass() {
    	if(foodValue > 0) {
    		foodValue--;
    		return true;
    	}
    	return false;
    }
    
    /**
     * Return a string of the form row,column
     * @return A string representation of the location.
     */
    public String toString()
    {
        return row + ", " + col;
    }
    
    /**
     * Use the top 16 bits for the row value and the bottom for
     * the column. Except for very big grids, this should give a
     * unique hash code for each (row, col) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (row << 16) + col;
    }
    
    /**
     * @return The row.
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * @return The column.
     */
    public int getCol()
    {
        return col;
    }
}
