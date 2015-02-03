package nl.hanze.sjet.model;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
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
    // The probability that a wolf will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.01;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.05;
    // The probability that a snake will be created in any given grid position.
    private static final double SNAKE_CREATION_PROBABILITY = 0.02;
    // The probability that a hawk will be created in any given grid position.
    private static final double HAWK_CREATION_PROBABILITY = 0.025;
    // The probability that a ferret will be created in any given grid position.
    private static final double FERRET_CREATION_PROBABILITY = 0.03;
    // The probability that a hunter will be crated in any given grid position.
    private static final double HUNTER_CREATION_PROBABILITY = 0.005;

    // List of animals in the field.
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // The SimulatorView Object
    private SimulatorView view;
    // A rabbit object.
    private Rabbit rabbit;
    // A ferret object.
	private Ferret ferret;
	// A snake object.
    private Snake snake;
    // A wolf object.
    private Wolf wolf;
    // A hawk object.
    private Hawk hawk;
    // A hunter object.
    private Hunter hunter;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator(SimulatorView view)
    {
        this(100, 100, view);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width, SimulatorView view)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = 100;
            width = 100;
        }
        
        this.view = view;
        
        started = false;
        suspended = true;
        
        actors = new ArrayList<Actor>();
        field = new Field(depth, width);

        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Returns the suspended boolean.
     * @return Suspended boolean.
     */
    public boolean getSuspended() {
    	return suspended;
    }
    
    /**
     * Returns the Started boolean.
     * @return Started boolean.
     */
    public boolean getStarted() {
    	return started;
    }
    
    /**
     * Switches the Suspended boolean from false to true or from true to false.
     */
    public void switchSuspended() {
    	if(suspended) {
    		suspended = false;
    	} else {
    		suspended = true;
    	}
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
        List<Actor> newAnimals = new ArrayList<Actor>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = (Actor) it.next();
            actor.act(newAnimals);
            if(actor instanceof Hunter) {
            	Hunter hunter = (Hunter) actor;
            	if(!hunter.isAlive()) {
            		it.remove();
            	}
            } else if(actor instanceof Animal) {
            	Animal animal = (Animal) actor;
            	if(!animal.isAlive()) {
            		it.remove();
            	}
            }
        }
               
        // Add the newly born wolves and rabbits to the main lists.
        actors.addAll(newAnimals);

        view.showStatus(step, field);
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
                    rabbit = new Rabbit(true, field, location);
                    actors.add(rabbit);
                }
                else if(rand.nextDouble() <= FERRET_CREATION_PROBABILITY) {
                	Location location = new Location(row, col);
                	ferret = new Ferret(true, field, location);
                	actors.add(ferret);
                }
                else if(rand.nextDouble() <= SNAKE_CREATION_PROBABILITY) {
                	Location location = new Location(row, col);
                	snake = new Snake(true, field, location);
                	actors.add(snake);
                }
                else if(rand.nextDouble() <= WOLF_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    wolf = new Wolf(true, field, location);
                    actors.add(wolf);
                }
                else if(rand.nextDouble() <= HAWK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    hawk = new Hawk(true, field, location);
                    actors.add(hawk);
                }
                else if(rand.nextDouble() <= HUNTER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    hunter = new Hunter(field, location);
                    actors.add(hunter);
                }
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Returns The current simulator.
     * @return This simulator.
     */
    public Simulator getSimulator(){
    	return this;
    }
    
    /**
     * Returns the SimulatorView.
     * @return The SimulatorView in this Simulator.
     */
    public SimulatorView getSimulatorView() {
    	return view;
    }
    
    /**
     * Returns the Rabbit object.
     * @return The Rabbit object.
     */
    public Rabbit getRabbit() {
		return rabbit;
	}

    /**
     * Returns the Ferret object.
     * @return The Ferret object.
     */
	public Ferret getFerret() {
		return ferret;
	}

	/**
	 * Returns the Snake object.
	 * @return The Snake object.
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * Returns the Wolf object.
	 * @return The Wolf object.
	 */
	public Wolf getWolf() {
		return wolf;
	}

	/**
	 * Returns the Hawk object.
	 * @return The Hawk object.
	 */
	public Hawk getHawk() {
		return hawk;
	}

	/**
	 * Returns the Hunter object.
	 * @return The Hunter object.
	 */
	public Hunter getHunter() {
		return hunter;
	}
}
