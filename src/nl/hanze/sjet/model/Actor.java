package nl.hanze.sjet.model;

import java.util.List;

public interface Actor {
	/**
	 * abstract act
	 * @param newActors
	 */
	abstract public void act(List<Actor> newActors);
}
