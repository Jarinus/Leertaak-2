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
	public static final int DEFAULT_STEPS_UNTIL_DEFECATING = 3;
	// Whether the animal is edible.
	private boolean prey;
	// The animal's age.
	private int age;
	// The amount of steps until the animal defecates again.
	private int stepsUntilDefecating;
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
     * Check whether the animal is prey or not.
     * @return true if the animal is indeed prey.
     */
    public boolean isPrey() {
    	return prey;
    }
    
    /**
     * Sets if the animal is prey.
     * @param prey Boolean to check if animal is prey.
     */
    public void setPrey(boolean prey) {
    	this.prey = prey;
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