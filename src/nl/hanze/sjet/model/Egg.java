package nl.hanze.sjet.model;

import java.util.List;

public class Egg extends Entity {
	// The steps for the egg to hatch
	private int stepsUntilHatch;
	
	/**
	 * The constructor for Egg
	 * @param field
	 * @param location
	 */
	public Egg(Field field, Location location) {
		super(field, location);
		stepsUntilHatch = 10;
	}

	/**
	 * The act method for Egg
	 * @param newAnimals
	 */
	@Override
	public void act(List<Actor> newAnimals) {
		if(stepsUntilHatch > 0) {
			stepsUntilHatch--;
		} else {
			Location loc = getLocation();
			Field field = getField();
			setDead();
			if(field != null && loc != null) {
			Chicken chicken = new Chicken(false, field, loc);
			if(isSick()) {
				chicken.makeSick();
			}
			newAnimals.add(chicken);
			}
			newAnimals.remove(this);
		}
	}

}
