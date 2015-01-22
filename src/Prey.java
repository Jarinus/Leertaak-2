import java.util.List;

/**
 * A superclass construction for all Prey. All prey
 * have an age from which on they breed, a maximum
 * age and litter size and a probability of breeding
 * 
 * @author Jan A. Germeraad
 * @version 20-1-2015
 */

public abstract class Prey extends Animal {
	// The amount of steps a predator can take after eating this prey.
	private int foodValue;
	/**
	 * Constructor for Prey, relays the Field and Location
	 * to the Animal Superclass.
	 * 
	 * @param field The field currently occupied.
	 * @param location The location within the field.
	 */
	protected Prey(Field field, Location location) {
		super(field, location);
	}
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newRabbits A list to return newly born rabbits.
     */
    abstract public void act(List<Animal> newRabbits);

	/**
	 * @return the foodValue
	 */
	public int getFoodValue() {
		return foodValue;
	}

	/**
	 * @param foodValue the foodValue to set
	 */
	protected void setFoodValue(int foodValue) {
		this.foodValue = foodValue;
	}
}
