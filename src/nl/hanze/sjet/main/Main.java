package nl.hanze.sjet.main;

import nl.hanze.sjet.controller.ButtonFunctions;
import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.ButtonView;
import nl.hanze.sjet.view.SimulatorView;

public class Main {
	public static void main(String[] args) {
		SimulatorView simulatorView = new SimulatorView(100, 120);
		Simulator simulator = new Simulator(100,125,simulatorView);
		ButtonView buttonView = new ButtonView(new ButtonFunctions(simulator.getSimulator()));
		simulatorView.addButtonView(buttonView);
	}
}
