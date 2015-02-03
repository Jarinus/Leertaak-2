package nl.hanze.sjet.model;

import java.util.List;

/**
 * Interface for Actor, signifies all acting entities within the Simulator.
 * 
 * @author Jari
 * @version 03-02-2015
 */
public interface Actor {
	// The construct of act, used for all acting entities in Simulator.
	public void act(List<Actor> newActors);
}
