package nl.hanze.sjet.model;
import java.util.List;

public class Ferret extends Prey {
	public Ferret(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		setValues(20, 8, 0.06, 3, 10);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	@Override
	public void act(List<Animal> newFerrets) {
		incrementAge();
		if(isAlive()) {
			checkDefecate();
			if(!isDiseased()) {
				setDiseased(rand.nextDouble() > 0.99);
			}/*
			if(isDiseased()) {
				List<Location> nearby = getField().adjacentLocations(getLocation());
				for(Location loc : nearby) {
					Animal animal = (Animal) getField().getObjectAt(loc);
					if(animal != null) {
						animal.setDiseased(true);
					}
				}
			}*/
			giveBirth(newFerrets);
			Location newLocation = getField().freeAdjacentLocation(getLocation());
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				setDead();
			}
		}
	}

	private void giveBirth(List<Animal> newFerrets) {
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Ferret young = new Ferret(false, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
			newFerrets.add(young);
		}
	}
}
