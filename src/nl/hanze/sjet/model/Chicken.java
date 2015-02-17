package nl.hanze.sjet.model;

import java.util.List;
import java.util.Random;

public class Chicken extends Entity {
    // Characteristics shared by all chickens (class variables).

    // The age at which a chicken can lay eggs.
    private static int BREEDING_AGE = 5;
    // The age to which a chicken can live.
    private static int MAX_AGE = 40;
    // The likelihood of a chicken laying eggs.
    private static double BREEDING_PROBABILITY = 0.12;
    // The maximum number of eggs.
    private static int MAX_LITTER_SIZE = 2;
    // A shared random number generator to control egg-laying.
    private static final Random rand = Randomizer.getRandom();
    
    
    // The chicken's age.
    private int age;
    
	public Chicken(boolean randomAge, Field field, Location location) {
		super(field, location);
		age = 0;
		if(randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
	}

	public void act(List<Actor> newChickens) {
		incrementAge();
		if(isAlive()) {
			Location newLocation = null;
			List<Location> free = getField().getFreeAdjacentLocations(getLocation());
			if(free.size() > 0) {
				newLocation = free.get(0);
			}
			if(newLocation == null) {
				setDead();
			} else {
				setLocation(newLocation);
			}
		}
	}
	
	private void incrementAge() {
		age++;
		if(age > MAX_AGE) {
			setDead();
		}
	}
}
