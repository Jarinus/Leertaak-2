package nl.hanze.sjet.model;

import java.util.List;

public class Hawk extends Animal {
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 8, maxAge = 80, maxLitterSize = 2;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	private static int foodValue = 16;
	private int foodLevel;

	public Hawk(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		foodLevel = 6;
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    public void act(List<Actor> newHawks)
    {
        incrementAge();
        if(isAlive()) {
        	checkDefecate();
        	if(isDiseased() && !isImmune()) {
        		setDead();
        	}
            giveBirth(newHawks);  
            Location newLocation = findFood();
            if(newLocation != null) {
            	setLocation(newLocation);
            	return;
            }
            // Try to move into a free location.
            newLocation = getField().freeAdjacentLocation(getLocation());
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
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Actor> newHawks)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Hawk young = new Hawk(false, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
            newHawks.add(young);
        }
    }
    
    private Location findFood() {
    	Field field = getField();
    	List<Location> adjacent = field.adjacentLocations(getLocation());
    	for(Location location : adjacent) {
    		Object obj = field.getObjectAt(location);
    		if(obj instanceof Ferret) {
    			Ferret ferret = (Ferret) obj;
    			eat(ferret, ferret.getFoodValue());
    			return location;
    		} else if(obj instanceof Rabbit) {
    			Rabbit rabbit = (Rabbit) obj;
    			eat(rabbit, rabbit.getFoodValue());
    			return location;
    		} else if(obj instanceof Snake) {
    			Snake snake = (Snake) obj;
    			eat(snake, snake.getFoodValue());
    			return location;
    		}
    	}
    	return null;
    }
    
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
     * This could result in the rabbit's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > maxAge) {
            setDead();
        }
        if(foodLevel <= 0) {
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
		Hawk.breedingAge = breedingAge;
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
		Hawk.maxAge = maxAge;
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
		Hawk.maxLitterSize = maxLitterSize;
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
		Hawk.breedingProbability = breedingProbability;
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
		Hawk.foodValue = foodValue;
	}
}
