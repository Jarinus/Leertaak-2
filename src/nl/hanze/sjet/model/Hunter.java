package nl.hanze.sjet.model;

import java.util.List;

/**
 * The Hunter object. The Hunter hunts for animals like Wolves and Hawks.
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */

public class Hunter implements Actor {
	private Field field;
	private Location location;
	private boolean alive;
	
	/**
	 * Constructor for Hunter.
	 * 
	 * @param field The field in which the Hunter is created.
	 * @param location The location in the field for the Hunter.
	 */
	public Hunter(Field field, Location location) {
		this.field = field;
		this.location = location;
		alive = true;
	}

	/**
	 * Primary behavior of the Hunter. It hunts and then it tries to move.
	 */
	@Override
	public void act(List<Actor> newActors) {
		if(alive) {
			Location newLocation = null;
			List<Location> surroundings = field.adjacentLocations(location);
			for(Location loc : surroundings) {
				Object actor = (Actor) field.getObjectAt(loc);
				if(actor instanceof Animal) {
					Animal animal = (Animal) actor;
					if(animal instanceof Hawk || animal instanceof Wolf) {
						newLocation = animal.getLocation();
						animal.setDead();
					}
				}
			}
			if(newLocation == null) {
				List<Location> free = field.getFreeAdjacentLocations(location);
				if(free != null) {
					newLocation = free.get(0);
				} else {
					// Overcrowding
					setDead();
					return;
				}
			}
			location = newLocation;
		}
	}
	
	/**
	 * Kills the Hunter and clears the location.
	 */
	public void setDead() {
		alive = false;
		field.clear(location);
	}

	/**
	 * Returns the alive boolean.
	 * @return alive
	 */
	public boolean isAlive() {
		return alive;
	}

}
