package nl.hanze.sjet.model;

import java.util.List;

public interface Actor {
	abstract public void act(List<Actor> newActors);
}
