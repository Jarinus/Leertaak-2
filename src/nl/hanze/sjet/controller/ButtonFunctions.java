package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.hanze.sjet.model.Simulator;;

public class ButtonFunctions {
	Simulator sim;
	
	public ButtonFunctions(Simulator simulator){
		sim = simulator;
	}
	
	public void addFunctionOneStep(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getSuspended()) {
					sim.simulateOneStep();
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
					sim.switchSuspended();;
				}
			}
		});
	}
	
	public void addFunctionPause(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.getSuspended()) {
					sim.switchSuspended();
				}
			}
		});
	}
	
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
