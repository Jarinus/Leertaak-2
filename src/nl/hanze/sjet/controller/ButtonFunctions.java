package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.hanze.sjet.model.Simulator;;

public class ButtonFunctions {
	Simulator sim;
	
	public void addFunctionOneStep(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getSuspended()) {
					sim.simulateOneStep();
					System.err.println("One step button was pressed and should work.");
				}
			}
		});
	}
	
	public void addFunctionRun(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.getStarted()) {
					sim.start();
					return;
				}
				if(sim.getSuspended()) {
					sim.setSuspended(false);
				}
				System.err.println("Run button was pressed and something should have happened.");
			}
		});
	}
	
	public void addFunctionPause(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.getSuspended()) {
					sim.setSuspended(true);
				}
				System.err.println("The pause button was pressed.");
			}
		});
	}
	
	public void addFunctionReset(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getSuspended()) {
					sim.reset();
				}
				System.err.println("The program should be reset.");
			}
		});
	}
}
