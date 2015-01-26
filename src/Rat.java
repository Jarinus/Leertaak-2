import java.util.List;

public class Rat extends Prey {
	public Rat(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		setValues(20, 8, 0.06, 3, 10);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	@Override
	public void act(List<Animal> newRats) {
		incrementAge();
		if(isAlive()) {
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
			giveBirth(newRats);
			Location newLocation = getField().freeAdjacentLocation(getLocation());
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				setDead();
			}
		}
	}

	private void giveBirth(List<Animal> newRats) {
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Rat young = new Rat(false, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
			newRats.add(young);
		}
	}
}
