package nl.hanze.sjet.model;

import java.util.List;

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
					if(/*animal instanceof Hawk || */animal instanceof Wolf) {// TODO: Add Hawk
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
	private void setDead() {
		alive = false;
		field.clear(location);
	}

}
