package nl.hanze.sjet.main;

import nl.hanze.sjet.controller.ButtonFunctions;
import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.ButtonView;
import nl.hanze.sjet.view.SimulatorView;

public class Main {
	public static void main(String[] args) {
		SimulatorView simulatorView = new SimulatorView(200, 200);
		Simulator simulator = new Simulator(simulatorView);
		ButtonView buttonView = new ButtonView(new ButtonFunctions(simulator.getSimulator()));
		simulatorView.addButtonView(buttonView);
	}
}
