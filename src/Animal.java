import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling and Jan A. Germeraad
 * @version 20-1-2015
 */
public abstract class Animal
{
	// The age from which on the prey can breed.
	private int breedingAge;
	// The probability of the prey breeding.
	private double breedingProbability;
	// The maximum amount of prey this prey can produce.
	private int maxLitterSize;
	// The animal's age.
	private int age;
	// The animal's maximum age.
	private int maxAge;
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
	// The Random object used in subclasses.
	protected static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
    	setAge(0);
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return age >= breedingAge;
    }
    
	/**
	 * Generate a number representing the number of births,
	 * if it can breed.
	 * @return The number of births (may be zero).
	 */
	protected int breed()
	{
	    int births = 0;
	    if(canBreed() && rand.nextDouble() <= breedingProbability) {
	        births = rand.nextInt(maxLitterSize) + 1;
	    }
	    return births;
	}
    
    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > maxAge) {
            setDead();
        }
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
	 * @return the maxAge
	 */
	protected int getMaxAge() {
		return maxAge;
	}

	/**
	 * @param maxAge the maxAge to set
	 */
	protected void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * @return the age
	 */
	protected int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	protected void setAge(int age) {
		this.age = age;
	}
    
	/**
	 * @return the breedingAge
	 */
	protected int getBreedingAge() {
		return breedingAge;
	}

	/**
	 * @param breedingAge the breedingAge to set
	 */
	protected void setBreedingAge(int breedingAge) {
		this.breedingAge = breedingAge;
	}

	/**
	 * @return the breedingProbability
	 */
	protected double getBreedingProbability() {
		return breedingProbability;
	}

	/**
	 * @param breedingProbability the breedingProbability to set
	 */
	protected void setBreedingProbability(double breedingProbability) {
		this.breedingProbability = breedingProbability;
	}

	/**
	 * @return the maxLitterSize
	 */
	protected int getMaxLitterSize() {
		return maxLitterSize;
	}

	/**
	 * @param maxLitterSize the maxLitterSize to set
	 */
	protected void setMaxLitterSize(int maxLitterSize) {
		this.maxLitterSize = maxLitterSize;
	}
}
