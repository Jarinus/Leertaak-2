package nl.hanze.sjet.main;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.MenuView;
import nl.hanze.sjet.view.SimulatorView;
/**
 * Creates instances of the main classes and runs the program.
 * 
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */
public class Main {
	/**
	 * Runs the simulation, creates a new window and adds functionality.
	 * @param args Potential arguments.
	 */
	public static void main(String[] args) {
		Simulator simulator = new Simulator(80, 120, new SimulatorView(80, 120));
		simulator.getSimulatorView().setButtons(simulator);
		simulator.getSimulatorView().setMenuView(new MenuView(simulator));
	}
}
