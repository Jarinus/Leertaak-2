package nl.hanze.sjet.model;

import java.util.Iterator;
import java.util.List;

public class Hunter extends Entity {
	
	/**
	 * The constructor for Hunter.
	 * @param field
	 * @param location
	 */
	public Hunter(Field field, Location location){
		super(field, location);
		
	}
	
	/**
	 * The act method for Hunter.
	 */
	@Override
	public void act(List<Actor> newAnimals) {
		if(isAlive()){
			Location newLocation = findPrey();
			if(newLocation == null){
				newLocation = getField().freeAdjacentLocation(getLocation());
			}
			else {
				Location loc = getField().freeAdjacentLocation(getLocation());
				Object obj = getField().getObjectAt(loc);
				if(obj instanceof Entity) {
					Entity entity = (Entity) obj;
					entity.setDead();
				} else {
					obj = null;
				}
				newLocation = loc;
			}
			if(newLocation != null){
				setLocation(newLocation);
			}
		}
	}
	
	/**
	 * The method that finds a prey for the hunter and kill it.
	 * @return
	 */
	public Location findPrey(){
		Field field = getField();
		List<Location> adjacent = field.adjacentLocations(getLocation());
		Iterator<Location> it = adjacent.iterator();
		while(it.hasNext()){
			Location where = it.next();
			Object animal = field.getObjectAt(where);
			if(animal instanceof Fox){
				Fox fox = (Fox) animal;
				if(fox.isAlive()){
					fox.setDead();
					//set foodLevel
					return where;
				}
			}
		}
         return null;   
	}
}
