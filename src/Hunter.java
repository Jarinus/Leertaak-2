import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Hunter {
	// The maximum amount of hunters there can be at any given time.
	private static final int MAX_AMOUNT_OF_HUNTERS = 10;
	// An array with first names.
	private static final String[] firstNames = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles"};
	// An array with last names.
	private static final String[] lastNames = {"McBride", "O'Connor", "Neeson", "Smith", "Johnson", "Jones", "Brown", "Williams"};
	// The hunter's name.
	private String name;
	// The maximum age a hunter can achieve.
	private static final int MAX_AGE = 100;
	// The starting age for all hunters.
	private static final int STARTING_AGE = 30;
	// The randomizer for all randomized purposes.
	private static final Random rand = Randomizer.getRandom();
	// The variable age of the hunter.
	private int age;
	// The boolean to signify whether the hunter is alive.
	private boolean alive;
	// The field occupied by the hunter.
	private Field field;
	// The hunter's location within the field.
	private Location location;
	
	/**
	 * Creates new Hunter at given field and location. Sets his age to the
	 * starting age and makes the Hunter alive.
	 * 
	 * @param field
	 * @param location
	 */
	public Hunter(Field field, Location location) {
		generateName();
		age = STARTING_AGE;
		alive = true;
		this.field = field;
		this.location = location;
	}
	
	private void generateName() {
		String temp = "";
		int randomIndex = rand.nextInt(firstNames.length + 1);
		name = firstNames[randomIndex] + " " + lastNames[randomIndex];
	}
	
	/**
	 * Make the Hunter act. The hunter will primarily age, look for
	 * predators and die of old age.
	 * 
	 * @param newHunters
	 */
	public void act(List<Hunter> newHunters) {
		incrementAge();
		if(alive) {
			Location newLocation = huntPredator();
			if(newLocation == null) {
				newLocation = field.freeAdjacentLocation(location);
			}
			if(newLocation != null){
				location = newLocation;
			} else {
				// Overcrowding
				setDead();
			}
		}
		if(newHunters.size() < MAX_AMOUNT_OF_HUNTERS) {
			List<Location> free = field.getFreeAdjacentLocations(location);
			if(free.size() > 0) {
				Location loc = free.remove(0);
				newHunters.add(new Hunter(field, loc));
				System.out.println("A new hunter was hired: " + name);
				free = null;
			}
		}
	}
	
	private Location huntPredator() {
		List<Location> adjacent = field.adjacentLocations(location);
		Iterator<Location> iterator = adjacent.iterator();
		while(iterator.hasNext()) {
			Location where = iterator.next();
			Object animal = field.getObjectAt(where);
			if(animal instanceof Wolf) {
				Wolf wolf = (Wolf) animal;
				if(wolf.isAlive()) {
					if(huntComplete(wolf)) {
						wolf.setDead();
						return where;
					} else {
						System.out.println("The hunter " + name + " was slain by a wolf.");
						this.setDead();
					}
				}
			} else if(animal instanceof Fox) {
				Fox fox = (Fox) animal;
				if(fox.isAlive()) {
					if(huntComplete(fox)) {
						fox.setDead();
						return where;
					} else {
						System.out.println("The hunter " + name + " was slain by a fox.");
						this.setDead();
					}
				}
			}
		}
		return null;
	}
	
	private boolean huntComplete(Predator predator) {
		if(rand.nextDouble() > predator.getChanceOfKillingHunter()) {
			return true;
		}
		return false;
	}
	
	private void incrementAge() {
		age++;
		if(age > MAX_AGE) {
			setDead();
		}
	}
	
	private void setDead() {
		alive = false;
		if(location != null) {
			field.clear(location);
			location = null;
			field = null;
		}
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxAmountOfHunters() {
		return MAX_AMOUNT_OF_HUNTERS;
	}
}
