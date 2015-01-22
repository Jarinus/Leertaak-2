import java.util.Iterator;
import java.util.List;


public class Wolf extends Predator {

	protected Wolf(boolean randomAge, Field field, Location location) {
		super(field, location);
		setChanceOfKillingHunter(0.10);
        setBreedingAge(30);
        setMaxAge(90);
        setBreedingProbability(0.06);
        setMaxLitterSize(4);
		if(randomAge) {
			setAge(rand.nextInt(getMaxAge()));
		}
	}

	@Override
	public boolean canEat(Object object) {
		if(object instanceof Rabbit) {
			return true;
		}
		return false;
	}
    
    /**
     * This is what the wolf does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWolves A list to return newly born wolves.
     */
    public void act(List<Animal> newWolves)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newWolves);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
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
            if(canEat(animal)) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    setFoodLevel(rabbit.getFoodValue());
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolves A list to return newly born wolves.
     */
    private void giveBirth(List<Animal> newWolves)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc);
            newWolves.add(young);
        }
    }
}
