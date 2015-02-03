package nl.hanze.sjet.model;
import java.util.List;

public class Ferret extends Animal {
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 2, maxAge = 24, maxLitterSize = 2;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	// The amount of foodLevel restored when this ferret is killed.
	private static int foodValue = 12;
	
	/**
	 * The constructor for the Ferret object.
	 * @param randomAge Whether the ferret should have a random age.
	 * @param field The field in which the ferret is created.
	 * @param location The location occupied by the ferret.
	 */
	public Ferret(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}
	
	/**
	 * Lets the ferret act.
	 * @param newFerrets The list in which new Ferrets may be added.
	 */
	@Override
	public void act(List<Actor> newFerrets) {
		incrementAge();
		Field field = getField();
		if(isAlive()) {
			checkDefecate();
			giveBirth(newFerrets);
			Location newLocation = null;
			newLocation = findFood();
			if(newLocation == null) {
				newLocation = field.freeAdjacentLocation(getLocation());
			}
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				setDead();
				return;
			}
		}
	}
	
	/**
	 * Tries to find killable animals in adjacent locations.
	 * @return The location of a killable animal or null.
	 */
	private Location findFood() {
		Field field = getField();
		Location location = null;
		List<Location> adjacent = field.adjacentLocations(getLocation());
		for(Location loc : adjacent) {
			Animal animal = null;
			if(field.getObjectAt(loc) instanceof Animal) {
				animal = (Animal) field.getObjectAt(loc);
				if(	animal instanceof Rabbit) {
					location = loc;
					animal.setDead();
					break;
				} else if(animal instanceof Snake) {
					if(rand.nextDouble() > 0.1) {
						location = loc;
						animal.setDead();
						break;
					} else {
						setDead();
					}
				}
			}
		}
		return location;
	}

	/**
	 * Tries to give birth to new Ferrets.
	 * @param newFerrets The list to which new Ferrets are added.
	 */
	private void giveBirth(List<Actor> newFerrets) {
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Ferret young = new Ferret(false, field, loc);
            if(isImmune()) {
            	young.setImmunity(true);
            }
			newFerrets.add(young);
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
     * This could result in the ferret's death.
     */
    protected void incrementAge()
    {
        setAge(getAge() + 1);
        if(getAge() > maxAge) {
            setDead();
        }
    }

    /**
     * A ferret can breed if it has reached the breeding age.
     * @return true if the ferret can breed, false otherwise.
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
		Ferret.breedingAge = breedingAge;
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
		Ferret.maxAge = maxAge;
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
		Ferret.maxLitterSize = maxLitterSize;
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
		Ferret.breedingProbability = breedingProbability;
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
		Ferret.foodValue = foodValue;
	}
}
