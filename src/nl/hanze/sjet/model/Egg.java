package nl.hanze.sjet.model;

import java.util.List;

public class Egg extends Animal {
	private static int foodValue = 5;
	Animal animal;
	
	public Egg(Animal animal, Field field, Location location) {
		super(field, location);
		if(!(animal instanceof Hawk || animal instanceof Snake)) {
			return;
		}
		this.animal = animal;
		setAge(10);
	}

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
