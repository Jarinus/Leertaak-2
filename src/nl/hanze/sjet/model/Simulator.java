package nl.hanze.sjet.model;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import javax.swing.*;

import nl.hanze.sjet.view.SimulatorView;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and wolves.
 * 
 * @author Jan A. Germeraad
 * @version 22-01-2015
 */
public class Simulator extends Thread
{
    // Constants representing configuration information for the simulation.
	public Object lock = new Object();
	// Whether the thread was suspended or not.
	private boolean suspended;
	// Whether the thread was started or not.
	private boolean started;
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a wolf will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.002;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.01;
    // The probability that a snake will be created in any given grid position.
    private static final double SNAKE_CREATION_PROBABILITY = 0.004;
    // The probability that a hawk will be created in any given grid position.
    // private static final double HAWK_CREATION_PROBABILITY = 0.005;
    // TODO: Add Hawk
    // The probability that a rat will be created in any given grid position.
    private static final double RAT_CREATION_PROBABILITY = 0.006;

    // List of animals in the field.
    private List<Animal> animals;
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
        suspended = true;
        
        animals = new ArrayList<Animal>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Egg.class, Color.darkGray);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Wolf.class, Color.blue);
        view.setColor(Snake.class, Color.green);
        view.setColor(Ferret.class, Color.yellow);
        

        // Setup a valid starting point.
        reset();
    }
    
    public SimulatorView getSimulatorView() {
    	return view;
    }
    
    public boolean getSuspended() {
    	return suspended;
    }
    
    public boolean getStarted() {
    	return started;
    }
    
    public void setSuspended(boolean suspended) {
    	this.suspended = suspended;
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void run()
    {
    	started = true;
    	suspended = false;
    	try{
	    	while(!interrupted()) {
	    		while(suspended) {
	    			synchronized(lock) {
	    				lock.wait();
	    			}
	    		}
	    		simulateOneStep();
	    		sleep(20);
	    	}
    	} catch(InterruptedException interruptedException) {
    		System.err.println(interruptedException);
    	}
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * wolf and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<Animal>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born wolves and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with wolves and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                else if(rand.nextDouble() <= RAT_CREATION_PROBABILITY) {
                	Location location = new Location(row, col);
                	Ferret ferret = new Ferret(true, field, location);
                	animals.add(ferret);
                }
                else if(rand.nextDouble() <= SNAKE_CREATION_PROBABILITY) {
                	Location location = new Location(row, col);
                	Snake snake = new Snake(true, field, location);
                	animals.add(snake);
                }
                else if(rand.nextDouble() <= WOLF_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Wolf wolf = new Wolf(true, field, location);
                    animals.add(wolf);
                }
                // else leave the location empty.
            }
        }
    }
    
    public void addAnimalsToMenu() {
        ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();
        
        if(menuItems != null && menuItems.size() > 0) {
        	view.getMenuList()[2].removeAll();
            for(int i = 0; i < menuItems.size(); i++) {
                view.getMenuList()[2].add(menuItems.get(i));
            }
        }
    }
}
