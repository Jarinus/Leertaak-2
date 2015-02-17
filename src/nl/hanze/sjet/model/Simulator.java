package nl.hanze.sjet.model;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import nl.hanze.sjet.view.SimulatorView;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Jan A. Germeraad
 * @version 22-01-2015
 */
public class Simulator extends Thread
{
    // Constants representing configuration information for the simulation.
	// The synchronized object used for the thread.
	public Object lock = new Object();
	// Whether the thread was suspended or not.
	public boolean suspended;
	// Whether the thread was started or not.
	public boolean started;
	// Whether a sickness was started or not.
	public boolean sickness;
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 250;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02,
    							RABBIT_CREATION_PROBABILITY = 0.08,
    							HUNTER_CREATION_PROBABILITY = 0.015,
    							CHICKEN_CREATION_PROBABILITY = 0.05;
    // List of animals in the field.
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        started = false;
        sickness = false;
        suspended = true;
        
        actors = new ArrayList<Actor>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(this, depth, width);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Hunter.class, Color.red);
        view.setColor(Chicken.class, Color.magenta);
        view.setColor(Egg.class, Color.pink);
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void run()
    {
    	started = true;
    	try{
	    	while(!interrupted()) {
	    		while(suspended) {
	    			synchronized(lock) {
	    				lock.wait();
	    			}
	    		}
	    		simulateOneStep();
	    	}
    	} catch(InterruptedException interruptedException) {
    		System.err.println(interruptedException);
    	}
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;
        
        int amountOfSickAnimals = 0;
        if(sickness) {
        	amountOfSickAnimals += 20;
        }

        // Provide space for newborn animals.
        List<Actor> newActors = new ArrayList<Actor>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newActors);
            if(actor instanceof Entity) {
                Entity entity = (Entity) actor;
                if(amountOfSickAnimals > 0 && !(entity instanceof Hunter)) {
                	entity.makeSick();
                }
                if(!entity.isAlive()) {
                    it.remove();
                }
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        actors.addAll(newActors);

        view.showStatus(step, field);
        
        sickness = false;
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes, rabbits and hunters.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    actors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    actors.add(rabbit);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY){
                	Location location = new Location(row, col);
                	Hunter hunter = new Hunter(field, location);
                	actors.add(hunter);
                }
                else if(rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY){
                	Location location = new Location(row, col);
                	Chicken chicken = new Chicken(true, field, location);
                	actors.add(chicken);
                }
                // else leave the location empty.
            }
        }
    }
    
    public Field getField() {
    	return field;
    }
    
    public SimulatorView getView() {
    	return view;
    }
}
