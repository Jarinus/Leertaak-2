package nl.hanze.sjet.model;
import java.util.List;

public class Snake extends Animal {
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 11, maxAge = 68, maxLitterSize = 3;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	// The amount of foodLevel the snake restores when killed.
	private static int foodValue = 12;
	// The snake's foodLevel.
	private int foodLevel;
	
	/**
	 * Constructor for Snake.
	 * @param randomAge Signifies if the animal should be of random age or not.
	 * @param field The field in which this Snake is created.
	 * @param location The location occupied by the Snake.
	 */
	public Snake(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		foodLevel = 10;
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	/**
	 * Lets the Snake act: age, get hungry, defecate, give birth and try to hunt.
	 * @param newSnakes The list of Snakes to which new Snakes may be added.
	 */
	@Override
	public void act(List<Actor> newSnakes) {
		incrementAge();
		incrementHunger();
		Field field = getField();
		if(isAlive()) {
			checkDefecate();
			giveBirth(newSnakes);
			// Try to move into a free location.
			Location newLocation = findFood();
			if(newLocation == null) {
				// No food found - try to move.
				newLocation = field.freeAdjacentLocation(getLocation());
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
	
	/**
	 * Tries to find food, returns null if no food was found or the location of the animal if food was found.
	 * @return Location of eaten animal or null.
	 */
	private Location findFood() {
		Field field = getField();
		List<Location> adjacent = field.adjacentLocations(getLocation());
		for(Location location : adjacent) {
			Object obj = field.getObjectAt(location);
			if(obj instanceof Animal) {
				Animal animal = (Animal) obj;
				if(animal instanceof Egg) {
					Egg egg = (Egg) animal;
					if(egg.animal instanceof Hawk) {
						eat(egg, egg.getFoodValue());
						return location;
					}
				} else if(animal instanceof Rabbit) {
					eat(animal, ((Rabbit) animal).getFoodValue());
					return location;
				} else if(animal instanceof Ferret) {
					if(rand.nextDouble() > 0.9) {
						eat(animal, ((Ferret) animal).getFoodValue());
						return location;
					} else {
						setDead();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Eats given animal.
	 * @param actor The actor, when an instance of Animal, to be removed.
	 * @param foodValue The amount with which the Snake's foodLevel should be increased.
	 */
	private void eat(Actor actor, int foodValue) {
		if(actor instanceof Animal) {
			Animal animal = (Animal) actor;
			foodLevel += foodValue;
			animal.setDead();
		}
	}
	
	/**
	 * Creates new Snakes if the snake can breed.
	 * @param newSnakes The list of Actors to which new Snakes may be added.
	 */
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
	 * Reduces the snake's foodLevel, so that it may either die or simply grow hungrier.
	 */
    private void incrementHunger() {
    	foodLevel--;
    	if(foodLevel <= 0) {
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
     * This could result in the snake's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > maxAge) {
            setDead();
        }
    }

    /**
     * A snake can breed if it has reached the breeding age.
     * @return true if the snake can breed, false otherwise.
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
