import java.util.Iterator;
import java.util.List;

public class Snake extends Prey {
	private int foodLevel;
	
	public Snake(boolean randomAge, Field field, Location location) {
		super(field, location);
		setAge(0);
		foodLevel = 10;
		setValues(100, 20, 0.08, 4, 4);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	@Override
	public void act(List<Animal> newSnakes) {
		incrementAge();
		incrementHunger();
		if(isAlive()) {
        	if(isDiseased() && !isImmune()) {
        		setDead();
        	}
			giveBirth(newSnakes);
			// Try to move into a free location.
			Location newLocation = findFood();
			if(newLocation == null) {
				// No food found - try to move.
				newLocation = getField().freeAdjacentLocation(getLocation());
			}
			// Check if it could move.
			if(newLocation != null) {
				setLocation(newLocation);
			} else {
				// Overcrowding
				setDead();
			}
		}
	}
	
	private void giveBirth(List<Animal> newSnakes) {
		// New snakes are born into adjacent locations.
		// Get a list of adjacent free locations.
		Field field = getField();
		List<Location> free = field.getFreeAdjacentLocations(getLocation());
		int births = breed();
		for(int b = 0; b < births && free.size() > 0; b++) {
			Location loc = free.remove(0);
			Snake young = new Snake(false, field, loc);
            if(this.isImmune()) {
            	young.setImmunity(true);
            }
			newSnakes.add(young);
		}
	}
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel += rabbit.getFoodValue();
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    private void incrementHunger() {
    	foodLevel--;
    	if(foodLevel == 0) {
    		setDead();
    	}
    }
}
