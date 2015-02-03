package nl.hanze.sjet.model;
import java.util.List;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author Jan A. Germeraad
 * @version 22-01-2015
 */
public class Wolf extends Animal
{
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 3, maxAge = 70, maxLitterSize = 2;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	// The value of the foodLevel that this animal will increase when killed by another animal.
	private static int foodValue = 24;
    // The wolf's food level, which is increased by eating.
    private int foodLevel;

    /**
     * Create a wolf. A wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        setAge(0);
        foodLevel = 9;
        if(randomAge) {
            setAge(rand.nextInt(getMaxAge()));
            foodLevel = rand.nextInt(9);
        }
    }
    
    /**
     * Lets the wolf act. It ages, gets hungry, may give birth and tries to hunt.
     * @param field The field currently occupied.
     * @param newWolves A list to return newly born wolves.
     */
    public void act(List<Actor> newWolves)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
        	checkDefecate();
        	if(isDiseased() && !isImmune()) {
        		setDead();
        	}
            giveBirth(newWolves);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for prey in adjacent locations.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
    	Field field = getField();
    	List<Location> adjacent = field.adjacentLocations(getLocation());
    	for(Location location : adjacent) {
    		Object obj = field.getObjectAt(location);
    		if(obj instanceof Hunter) {
    			if(rand.nextDouble() > 0.95) {
    				Hunter hunter = (Hunter) obj;
    				hunter.setDead();
    				foodLevel += 20;
    				return location;
    			}
    		} else if(obj instanceof Ferret) {
    			Ferret ferret = (Ferret) obj;
    			eat(ferret, ferret.getFoodValue());
    			return location;
    		} else if(obj instanceof Rabbit) {
    			Rabbit rabbit = (Rabbit) obj;
    			eat(rabbit, rabbit.getFoodValue());
    			return location;
    		}
    	}
    	return null;
    }
    
    /**
     * Check whether or not this wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolves A list to return newly born wolves.
     */
    private void giveBirth(List<Actor> newWolves)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
            newWolves.add(young);
        }
    }
    
    /**
	 * Eats given animal object.
     * @param animal The animal to be removed.
     * @param foodValue The animal's foodValue, to be added to the Wolf's foodLevel.
     */
    private void eat(Animal animal, int foodValue) {
    	animal.setDead();
    	foodLevel += foodValue;
    }
    
	/**
	 * Generate a number representing the number of births,
	 * if it can breed.
	 * @return The number of births (may be zero).
	 */
	protected int breed()
	{
	    int births = 0;
	    if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
	        births = rand.nextInt(getMaxLitterSize()) + 1;
	    }
	    return births;
	}

    /**
     * Increase the age.
     * This could result in the wolf's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > maxAge) {
            setDead();
        }
    }

    /**
     * A wolf can breed if it has reached the breeding age.
     * @return true if the wolf can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return getAge() >= breedingAge;
    }

	/**
	 * @return the breedingAge
	 */
	public int getBreedingAge() {
		return breedingAge;
	}

	/**
	 * @param breedingAge the breedingAge to set
	 */
	public void setBreedingAge(int breedingAge) {
		Wolf.breedingAge = breedingAge;
	}

	/**
	 * @return the maxAge
	 */
	public int getMaxAge() {
		return maxAge;
	}

	/**
	 * @param maxAge the maxAge to set
	 */
	public void setMaxAge(int maxAge) {
		Wolf.maxAge = maxAge;
	}

	/**
	 * @return the maxLitterSize
	 */
	public int getMaxLitterSize() {
		return maxLitterSize;
	}

	/**
	 * @param maxLitterSize the maxLitterSize to set
	 */
	public void setMaxLitterSize(int maxLitterSize) {
		Wolf.maxLitterSize = maxLitterSize;
	}

	/**
	 * @return the breedingProbability
	 */
	public double getBreedingProbability() {
		return breedingProbability;
	}

	/**
	 * @param breedingProbability the breedingProbability to set
	 */
	public void setBreedingProbability(double breedingProbability) {
		Wolf.breedingProbability = breedingProbability;
	}
	
	/**
	 * Get the food value when this animal is killed.
	 * @return foodValue
	 */
	public int getFoodValue() {
		return foodValue;
	}
	
	/**
	 * Edit the current food value
	 * @param foodValue The amount of foodlevel the animal restores when killed.
	 */
	public void setFoodValue(int foodValue) {
		Wolf.foodValue = foodValue;
	}
}
