package nl.hanze.sjet.model;
import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public abstract class Entity implements Actor
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // Whether the entity is sick.
    private boolean sick;
    
    private static final Random rand = Randomizer.getRandom();
    
    // The chance for an animal to get sick
    private static final double SICKNESS_CHANCE = 0.025;
    // The amount of steps that an animal makes until his dead
    private int stepsUntilDead;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Entity(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        stepsUntilDead = -1;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Actor> newAnimals);

    /**
     * Adds value to the grass at given row and column.
     * @param row The row.
     * @param col The column.
     */
    public void defecate(int row, int col) {
    	field.addGrass(row, col);
    }
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Method that cures the Sickness
     */
    public void cure() {
    	stepsUntilDead = -1;
    	sick = false;
    }
    
    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    /**
     *  Method that spreads the sickness if an animal is sick
     */
    protected void spreadSickness() {
    	List<Location> adjacent = field.adjacentLocations(location);
    	for(Location loc : adjacent) {
    		Object obj = field.getObjectAt(loc);
    		if(obj instanceof Entity) {
    			Entity entity = (Entity) obj;
    			entity.makeSick();
    		}
    	}
    }
    /**
     * The steps until dead decreases with this method
     * to make the animal die faster
     */
    protected void incrementSickness() {
	   if(sick) {
		   stepsUntilDead--;
		   if(stepsUntilDead == 0) {
			   setDead();
		   }
	   }
   }
   /**
    * This method tries to make the animal sick
    */
    public void tryToMakeSick() {
	   if(rand.nextDouble() < SICKNESS_CHANCE) {
		   makeSick();
	   }
   }
    
    /**
     * Makes the entity sick if the entity was not sick already.
     */
    public void makeSick() {
    	if(!sick) {
    		stepsUntilDead = 10;
    		sick = true;
    	}
    }
    
    /**
     * Return whether the entity is sick.
     * @return Whether the entity is sick.
     */
    public boolean isSick() {
    	return sick;
    }
}
