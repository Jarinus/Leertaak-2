package nl.hanze.sjet.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import nl.hanze.sjet.model.Entity;
import nl.hanze.sjet.model.Field;
import nl.hanze.sjet.model.Location;
import nl.hanze.sjet.model.Rabbit;
import nl.hanze.sjet.model.Fox;
import nl.hanze.sjet.model.Simulator;


public class Test {

    private Simulator simulator;
    private Field field;
    private Location loc;
    private Location loc2;
    private Location loc3;
    private Location loc4;
    
    private Rabbit rabbit;
    private Rabbit rabbit2;
    private Fox fox;
    private Fox fox2; 

    @Before
    public void setUp() {
            simulator = new Simulator();
             field = new Field(3,3);
             
             loc = new Location(1,1);
             loc2 = new Location(2,2);
             loc3 = new Location(1,2);
             loc4 = new Location(2,1);
             rabbit = new Rabbit(true, field, loc);
             rabbit2 = new Rabbit(true, field, loc2);
             fox = new Fox(true, field, loc3);
             fox2 = new Fox(true, field, loc4);
    }
    
    @org.junit.Test
    public void testSimulator() {
            simulator = new Simulator();
    }

    @org.junit.Test
    public void testAnimalsCopy() {
            Rabbit r = rabbit;
            assertSame(r,rabbit);
            Fox f = new Fox(true,field,loc3);
            assertNotSame(f,fox);
    }
    
    @org.junit.Test
    public void testTwoRabbits() {
            assertNotSame(rabbit,rabbit2);
    }

	@org.junit.Test
	public void test() {
		
		for (int i = 0; i < 20; i++) {
			simulator.simulateOneStep();
			rabbit2.incrementAgeTester();
			assertTrue(rabbit2.getCanBreed());
		}
		
		if (field.getObjectAt(1,1) == null){
			assertEquals(field.getObjectAt(1,1), rabbit);
			assertFalse(field.getObjectAt(1,1) instanceof Entity);
		}
	}
}