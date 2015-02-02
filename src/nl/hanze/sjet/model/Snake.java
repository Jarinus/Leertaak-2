package nl.hanze.sjet.model;
import java.util.Iterator;
import java.util.List;

public class Snake extends Animal {
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 8, maxAge = 20, maxLitterSize = 3;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	private static int foodValue;
	private int foodLevel;
	
	public Snake(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		setPrey(true);
		foodLevel = 10;
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	@Override
	public void act(List<Actor> newSnakes) {
		incrementAge();
		incrementHunger();
		if(isAlive()) {
			checkDefecate();
        	if(isDiseased() && !isImmune()) {
        		setDead();
        	}
			giveBirth(newSnakes);
			// Try to move into a free location.
			Location newLocation = findFood();
			if(newLocation == null) {
				// No food found - try to move.
				newLocation = getField().freeAdjacentLocation(getLocation());
			}
			// Check if it could move.
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				// Overcrowding
				setDead();
			}
		}
	}
	
	private void giveBirth(List<Actor> newSnakes) {
		// New snakes are born into adjacent locations.
		// Get a list of adjacent free locations.
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Egg young = new Egg(this, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
			newSnakes.add(young);
		}
	}
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel += rabbit.getFoodValue();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    private void incrementHunger() {
    	foodLevel--;
    	if(foodLevel == 0) {
    		setDead();
    	}
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
     * This could result in the rabbit's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > maxAge) {
            setDead();
        }
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
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
		Snake.breedingAge = breedingAge;
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
		Snake.maxAge = maxAge;
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
		Snake.maxLitterSize = maxLitterSize;
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
		Snake.breedingProbability = breedingProbability;
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
		Snake.foodValue = foodValue;
	}
}
