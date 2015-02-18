package nl.hanze.sjet.model;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Rabbit extends Entity
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    private static int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static int MAX_AGE = 20;
    // The likelihood of a rabbit breeding.
    private static double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    //The default variables of Rabbit
    public final static int DEF_BREEDING_AGE = 5;
    public final static int DEF_MAX_AGE = 20;
    public final static double DEF_BREEDING_PROBABILITY = 0.08;
    public final static int DEF_MAX_LITTER_SIZE = 4;
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Actor> newRabbits)
    {
    	Field field = getField();
        incrementAge();
        incrementSickness();
        if(isAlive()) {
        	if(isSick()) {
        		spreadSickness();
        		setDead();
        		return;
        	}
        	int row = getLocation().getRow(),
        			col = getLocation().getCol();
        	if(field.eatGrass(row, col)) {
        		defecate(row, col);
	            giveBirth(newRabbits);            
	            // Try to move into a free location.
	            Location newLocation = getField().freeAdjacentLocation(getLocation());
	            if(newLocation != null) {
	                setLocation(newLocation);
	            }
	            else {
	                // Overcrowding.
	                setDead();
	            }
            } else {
            	setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Actor> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    /**
     * 
     * @return returns boolean that tests if age is bigger than breeding age
     */
    public boolean getCanBreed(){
    	 return age >= BREEDING_AGE;
    }
    /**
     * is a public duplicate of increment age
     */
    public void incrementAgeTester(){
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

	/**
	 * @return the breedingAge
	 */
	public static int getBreedingAge() {
		return BREEDING_AGE;
	}

	/**
	 * @return the maxAge
	 */
	public static int getMaxAge() {
		return MAX_AGE;
	}

	/**
	 * @return the breedingProbability
	 */
	public static double getBreedingProbability() {
		return BREEDING_PROBABILITY;
	}

	/**
	 * @return the maxLitterSize
	 */
	public static int getMaxLitterSize() {
		return MAX_LITTER_SIZE;
	}

	/**
	 * @param bREEDING_AGE the bREEDING_AGE to set
	 */
	public static void setBREEDING_AGE(int bREEDING_AGE) {
		BREEDING_AGE = bREEDING_AGE;
	}

	/**
	 * @param mAX_AGE the mAX_AGE to set
	 */
	public static void setMAX_AGE(int mAX_AGE) {
		MAX_AGE = mAX_AGE;
	}

	/**
	 * @param bREEDING_PROBABILITY the bREEDING_PROBABILITY to set
	 */
	public static void setBREEDING_PROBABILITY(double bREEDING_PROBABILITY) {
		BREEDING_PROBABILITY = bREEDING_PROBABILITY;
	}

	/**
	 * @param mAX_LITTER_SIZE the mAX_LITTER_SIZE to set
	 */
	public static void setMAX_LITTER_SIZE(int mAX_LITTER_SIZE) {
		MAX_LITTER_SIZE = mAX_LITTER_SIZE;
	}
}
