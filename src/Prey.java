/**
 * The interface Prey, containing the structure of a Prey's
 * behavior.
 * 
 * @author Jan A. Germeraad
 * @version 23-01-2015
 */

public abstract class Prey extends Animal {
	private int foodValue;
	
	public Prey(Field field, Location location) {
		super(field, location);
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
     * @param foodValue The amount of hunger this animal stills
     * 			when eaten.
     */
	public void setValues(	int maxAge,
							int breedingAge,
							double breedingProbability,
							int maxLitterSize,
							int foodValue) {
		super.setValues(maxAge, breedingAge, breedingProbability, maxLitterSize);
		setFoodValue(foodValue);
	}

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
