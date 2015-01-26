package nl.hanze.sjet.main;

import nl.hanze.sjet.controller.ButtonFunctions;
import nl.hanze.sjet.model.Simulator;

public class Main {
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		new ButtonFunctions(simulator, simulator.getSimulatorView().getButtonList());
	}
}
