package nl.hanze.sjet.model;

import java.util.List;
import java.util.Random;

public class Chicken extends Entity {
    // Characteristics shared by all chickens (class variables).

    // The age at which a chicken can lay eggs.
    private static int BREEDING_AGE = 5;
    // The age to which a chicken can live.
    private static int MAX_AGE = 25;
    // The likelihood of a chicken laying eggs.
    private static double BREEDING_PROBABILITY = 0.27;
    // The maximum number of eggs.
    private static int MAX_LITTER_SIZE = 2;
    // A shared random number generator to control egg-laying.
    private static final Random rand = Randomizer.getRandom();
    
    //The Default variables for chicken 
    public static final int DEF_BREEDING_AGE = 5;
    public static final int DEF_MAX_AGE = 25;
    public static final double DEF_BREEDING_PROBABILITY = 0.27;
    public static final int DEF_MAX_LITTER_SIZE = 2;
    
    // The chicken's age.
    private int age;
    
    /**
     * Constructor Chicken
     * @param randomAge
     * @param field
     * @param location
     */
	public Chicken(boolean randomAge, Field field, Location location) {
		super(field, location);
		age = 0;
		if(randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
	}

	/**
	 * act method
	 * @param List<Actor> newChicken
	 */
	public void act(List<Actor> newChickens) {
		incrementAge();
        incrementSickness();
		if(isAlive()) {
        	if(isSick()) {
        		spreadSickness();
        		setDead();
        		return;
        	}
        	int row = getLocation().getRow(), col = getLocation().getCol();
        	defecate(row, col);
			if(rand.nextDouble() <= BREEDING_PROBABILITY) {
				layEggs(newChickens);
			}
			Location newLocation = null;
			List<Location> free = getField().getFreeAdjacentLocations(getLocation());
			if(free.size() > 0) {
				newLocation = free.get(0);
			}
			if(newLocation == null) {
				setDead();
			} else {
				setLocation(newLocation);
			}
		}
	}
	
	/**
	 * Method that lets the chickens lay eggs
	 * @param newChickens
	 */
	private void layEggs(List<Actor> newChickens) {
        int births = 0;
        if(canLayEggs() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        for(int i = 0; i < births && free.size() > 0; i++) {
        	newChickens.add(new Egg(getField(), free.remove(0)));
        }
	}
	
	/**
	 * Check if the Chicken can lay eggs 
	 * @return Whether the age is above the breeding age.
	 */
	private boolean canLayEggs() {
		return age >= BREEDING_AGE;
	}
	/**
	 * Increment age of Chicken.
	 */
	private void incrementAge() {
		age++;
		if(age > MAX_AGE) {
			setDead();
		}
	}

	/**
	 * @return the bREEDING_AGE
	 */
	public static int getBREEDING_AGE() {
		return BREEDING_AGE;
	}

	/**
	 * @param bREEDING_AGE the bREEDING_AGE to set
	 */
	public static void setBREEDING_AGE(int bREEDING_AGE) {
		BREEDING_AGE = bREEDING_AGE;
	}

	/**
	 * @return the mAX_AGE
	 */
	public static int getMAX_AGE() {
		return MAX_AGE;
	}

	/**
	 * @param mAX_AGE the mAX_AGE to set
	 */
	public static void setMAX_AGE(int mAX_AGE) {
		MAX_AGE = mAX_AGE;
	}

	/**
	 * @return the bREEDING_PROBABILITY
	 */
	public static double getBREEDING_PROBABILITY() {
		return BREEDING_PROBABILITY;
	}

	/**
	 * @param bREEDING_PROBABILITY the bREEDING_PROBABILITY to set
	 */
	public static void setBREEDING_PROBABILITY(double bREEDING_PROBABILITY) {
		BREEDING_PROBABILITY = bREEDING_PROBABILITY;
	}

	/**
	 * @return the mAX_LITTER_SIZE
	 */
	public static int getMAX_LITTER_SIZE() {
		return MAX_LITTER_SIZE;
	}

	/**
	 * @param mAX_LITTER_SIZE the mAX_LITTER_SIZE to set
	 */
	public static void setMAX_LITTER_SIZE(int mAX_LITTER_SIZE) {
		MAX_LITTER_SIZE = mAX_LITTER_SIZE;
	}
}
