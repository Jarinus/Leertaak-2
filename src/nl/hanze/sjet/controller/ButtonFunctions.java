package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.hanze.sjet.model.Simulator;;

/**
 * Handles the button functions for the SimulatorView.
 * 
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */
public class ButtonFunctions {
	// The simulator in which the button functions operate.
	Simulator sim;
	
	/**
	 * The constructor for the ButtonFunctions.
	 * @param simulator The simulator in which the button functions operate.
	 */
	public ButtonFunctions(Simulator simulator){
		sim = simulator;
	}
	
	/**
	 * Adds the function to do one step to a specific button. The button won't do anything if the
	 * Simulator's suspended boolean is false.
	 * @param button The button which uses the function.
	 */
	public void addFunctionOneStep(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getSuspended()) {
					sim.simulateOneStep();
				}
			}
		});
	}

	/**
	 * Add the 'run' function to the specified button. The button starts the Simulator thread if
	 * the Simulator's started boolean is set to false and resumes the thread when the started boolean
	 * is set to true and the suspended boolean is set to true.
	 * @param button The button which uses the function.
	 */
	public void addFunctionRun(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.getStarted()) {
					sim.start();
					return;
				}
				if(sim.getSuspended()) {
					sim.switchSuspended();;
				}
			}
		});
	}

	/**
	 * Switches the Simulator's suspended boolean to true if the boolean was false.
	 * @param button The button which uses the function.
	 */
	public void addFunctionPause(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.getSuspended()) {
					sim.switchSuspended();
				}
			}
		});
	}

	/**
	 * Adds the 'reset' function to specified button. If the thread was paused, the Simulator is reset.
	 * Otherwise this button should do nothing.
	 * @param button The button which uses the function.
	 */
	public void addFunctionReset(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getSuspended()) {
					sim.reset();
				}
			}
		});
	}
}
