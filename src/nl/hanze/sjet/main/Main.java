package nl.hanze.sjet.main;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.SimulatorView;

public class Main {
	public static void main(String[] args) {
		new Simulator(new SimulatorView(120, 80));
	}
}
