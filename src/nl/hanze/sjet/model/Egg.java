package nl.hanze.sjet.model;

import java.util.List;

public class Egg extends Animal {
	Animal animal;
	
	public Egg(Animal animal, Field field, Location location) {
		super(field, location);
		if(!(/*animal instanceof Hawk || */animal instanceof Snake)) {
			return;
		}
		this.animal = animal;
		setAge(10);
	}

	@Override
	public void act(List<Animal> newAnimals) {
		if(getAge() > 0) {
			setAge(getAge() - 1);
		} else if(getAge() == 0) {
			//TODO: Add the hawk if the egg is of type Hawk.
			/*if(animal instanceof Hawk) {
				newAnimals.add(new Hawk(false, getField(), getLocation()));
			} else*/ if(animal instanceof Snake) {
				newAnimals.add(new Snake(false, getField(), getLocation()));
			}
			newAnimals.remove(this);
		}
	}
}
