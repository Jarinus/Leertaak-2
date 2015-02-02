package nl.hanze.sjet.main;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.MenuView;
import nl.hanze.sjet.view.SimulatorView;

public class Main {
	public static void main(String[] args) {
		Simulator simulator = new Simulator(80, 120, new SimulatorView(80, 120));
		simulator.getSimulatorView().setButtons(simulator);
		simulator.getSimulatorView().setMenuView(new MenuView(simulator));
	}
}
