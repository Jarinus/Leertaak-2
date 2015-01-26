package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.SimulatorView;

public class ButtonFunctions {
	Simulator simulator;
	SimulatorView view;
	
	public ButtonFunctions(Simulator simulator, JButton[] buttons) {
		this.simulator = simulator;
		view = simulator.getSimulatorView();
		
	    // Add functionality to buttons.
	    view.getButtonList()[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(simulator.getSuspended()) {
					simulator.simulateOneStep();
				} else {
					return;
				}
			}
	    });
	    view.getButtonList()[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!simulator.getStarted()) {
					simulator.start();
					return;
				}
				if(simulator.getSuspended()) {
					simulator.setSuspended(false);
					synchronized(simulator.lock) {
						simulator.lock.notifyAll();
					}
				} else {
					return;
				}
			}
	    });
	    view.getButtonList()[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!simulator.getSuspended()) {
					simulator.setSuspended(true);
				}
			}
	    });
	    view.getButtonList()[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(simulator.getSuspended()) {
					simulator.reset();
				} else {
					return;
				}
			}
	    });
	}
}
