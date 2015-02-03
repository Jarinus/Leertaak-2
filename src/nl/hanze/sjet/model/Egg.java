package nl.hanze.sjet.model;

import java.util.List;

public class Egg extends Animal {
	// The amount of foodLevel restored when killed.
	private static int foodValue = 5;
	// The Egg type depends on this: it's either of a Snake or a Hawk.
	Animal animal;
	
	/**
	 * The constructor for a new egg.
	 * @param animal The egg type.
	 * @param field The field in which the egg is created.
	 * @param location The location occupied by the egg.
	 */
	public Egg(Animal animal, Field field, Location location) {
		super(field, location);
		if(!(animal instanceof Hawk || animal instanceof Snake)) {
			return;
		}
		this.animal = animal;
		setAge(10);
	}

	/**
	 * Lets the egg act. The egg mainly ages and can hatch at some point.
	 * @param newAnimals The list to which a new Hawk or Snake is added when the egg hatches.
	 */
	@Override
	public void act(List<Actor> newAnimals) {
		if(getAge() > 0) {
			setAge(getAge() - 1);
		} else if(getAge() == 0) {
			if(animal instanceof Hawk) {
				newAnimals.add(new Hawk(false, getField(), getLocation()));
			} else if(animal instanceof Snake) {
				newAnimals.add(new Snake(false, getField(), getLocation()));
			}
			newAnimals.remove(this);
		}
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
		Egg.foodValue = foodValue;
	}
}
