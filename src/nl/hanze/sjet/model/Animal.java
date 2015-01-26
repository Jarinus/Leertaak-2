package nl.hanze.sjet.model;
import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author Jan A. Germeraad
 * @version 22-01-2015
 */
public abstract class Animal
{
	public static final Random rand = Randomizer.getRandom();
	public static final int WOLF_INDEX = 0;
	public static final int HAWK_INDEX = 1;
	public static final int SNAKE_INDEX = 2;
	public static final int RABBIT_INDEX = 3;
	public static final int RAT_INDEX = 4;
	public static final int DEFAULT_STEPS_UNTIL_DEFECATING = 3;;
	private int stepsUntilDefecating;
	// The animal's minimum age before it can breed, the animal's maximum
	// achievable age, the maximum litter size and its age.
	private int breedingAge, maxAge, maxLitterSize, age;
	// The variable which signifies how big the chance is that the animal will breed.
	private double breedingProbability;
    // Whether the animal is alive or not.
    private boolean alive;
    // Whether the animal is diseased or not.
    private boolean diseased;
    // Whether the animal is immune or not.
    private boolean immune;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
    	setImmunity(rand.nextDouble() > 0.9);
    	stepsUntilDefecating = DEFAULT_STEPS_UNTIL_DEFECATING;
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
    public boolean isAlive()
    {
        return alive;
    }
    
    /**
     * Sets the values of the given parameters. This is used to
     * edit the default values for all animals. This method will
     * be called after creation of an animal.
     * 
     * @param maxAge The maximum age achievable for the animal.
     * @param breedingAge The age from which on the animal breeds.
     * @param breedingProbability The chance that the animal will
     * 			breed.
     * @param maxLitterSize The maximum amount of new animals can
     * 			come from this one.
     */
    public void setValues(	int maxAge,
    						int breedingAge,
    						double breedingProbability,
    						int maxLitterSize) {
    	setMaxAge(maxAge);
    	setBreedingAge(breedingAge);
    	setBreedingProbability(breedingProbability);
    	setMaxLitterSize(maxLitterSize);
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
    
    protected boolean checkDefecate() {
    	if(stepsUntilDefecating < 0) {
    		stepsUntilDefecating = DEFAULT_STEPS_UNTIL_DEFECATING;
    		return false;
    	}
    	if(stepsUntilDefecating == 0) {
    		location.incrementFoodValue();
    		stepsUntilDefecating = DEFAULT_STEPS_UNTIL_DEFECATING;
    		return true;
    	}
    	stepsUntilDefecating--;
    	return false;
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
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return age >= breedingAge;
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
	 * @return the breedingAge
	 */
	public int getBreedingAge() {
		return breedingAge;
	}

	/**
	 * @param breedingAge the breedingAge to set
	 */
	public void setBreedingAge(int breedingAge) {
		this.breedingAge = breedingAge;
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
		this.maxAge = maxAge;
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
		this.maxLitterSize = maxLitterSize;
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
		this.breedingProbability = breedingProbability;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	protected void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @param immune the immunity to set
	 */
	protected void setImmunity(boolean immune) {
		this.immune = immune;
	}
	
	/**
	 * @return immune whether the animal is immune
	 */
	public boolean isImmune() {
		return immune;
	}
	
	/**
	 * @param diseased the diseased boolean to set
	 */
	protected void setDiseased(boolean diseased) {
		this.diseased = diseased;
	}
	
	/**
	 * @return diseased whether the animal is diseased
	 */
	public boolean isDiseased() {
		return diseased;
	}
}
