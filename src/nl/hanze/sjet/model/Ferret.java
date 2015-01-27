package nl.hanze.sjet.model;
import java.util.List;

public class Ferret extends Animal {
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private static int breedingAge = 8, maxAge = 20, maxLitterSize = 3;
	// The variable which signifies how big the chance is that the animal will breed.
	private static double breedingProbability = 0.006;
	private static int foodValue;
	
	public Ferret(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		setPrey(true);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}
	

	@Override
	public void act(List<Animal> newFerrets) {
		incrementAge();
		if(isAlive()) {
			checkDefecate();
			if(!isDiseased()) {
				setDiseased(rand.nextDouble() > 0.99);
			}/*
			if(isDiseased()) {
				List<Location> nearby = getField().adjacentLocations(getLocation());
				for(Location loc : nearby) {
					Animal animal = (Animal) getField().getObjectAt(loc);
					if(animal != null) {
						animal.setDiseased(true);
					}
				}
			}*/
			giveBirth(newFerrets);
			Location newLocation = getField().freeAdjacentLocation(getLocation());
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				setDead();
			}
		}
	}

	private void giveBirth(List<Animal> newFerrets) {
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Ferret young = new Ferret(false, field, loc);
            if(this.isImmune()) {
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