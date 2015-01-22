import java.util.List;

/**
 * A superclass construction for all Predators. All predators
 * have an age from which on they breed, a maximum
 * age and litter size and a probability of breeding.
 * 
 * @author Jan A. Germeraad
 * @version 20-1-2015
 */
public abstract class Predator extends Animal {
	private double chanceOfKillingHunter;
	private int foodLevel;
	
	protected Predator(Field field, Location location) {
		super(field, location);
	}
    
    /**
     * This is what the predator does most of the time - it runs 
     * around. Sometimes it will eat, breed or die of old age.
     * @param newRabbits A list to return newly born predators.
     */
    abstract public void act(List<Animal> newPredators);
	
	abstract public boolean canEat(Object object);
	
	protected void incrementHunger() {
		foodLevel--;
		if(foodLevel <= 0) {
			setDead();
		}
	}

	/**
	 * @return the foodLevel
	 */
	public int getFoodLevel() {
		return foodLevel;
	}

	/**
	 * @param foodLevel the foodLevel to set
	 */
	protected void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	/**
	 * @return the chanceOfKillingHunter
	 */
	public double getChanceOfKillingHunter() {
		return chanceOfKillingHunter;
	}

	/**
	 * @param chanceOfKillingHunter the chanceOfKillingHunter to set
	 */
	protected void setChanceOfKillingHunter(double chanceOfKillingHunter) {
		this.chanceOfKillingHunter = chanceOfKillingHunter;
	}
	
}