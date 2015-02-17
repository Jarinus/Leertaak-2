package nl.hanze.sjet.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.Console;

/**
 * 
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */
public class ButtonHandler {
	private Simulator sim;
	private Console console;

	private static final String MESSAGE_RUN = "The simulation was started.",
								MESSAGE_PAUSE = "The simulation was paused.",
								MESSAGE_DEFECATION_ON = "Defecation was turned on.",
								MESSAGE_DEFECATION_OFF = "Defecation was turned off.",
								MESSAGE_SICKNESS = "A sickness was added to the simulation.",
								MESSAGE_RESUME = "The simulation was resumed.";
	
	private static final Color BUTTON_COLOR = new Color(220, 220, 220);
	
	/**
	 * The constructor for the ButtonHandler
	 * @param sim The Simulator used.
	 */
	public ButtonHandler(Simulator sim, Console console) {
		this.sim = sim;
		this.console = console;
	}
	
	public JButton makeNewSicknessButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sim.sickness = true;
				console.write(MESSAGE_SICKNESS);
			}
		});
		return newButton;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public JButton makeNewSwitchDefecationButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getField().isDefecating()) {
					sim.getField().setDefecating(false);
					console.write(MESSAGE_DEFECATION_OFF);
				} else {
					sim.getField().setDefecating(true);
					console.write(MESSAGE_DEFECATION_ON);
				}
				sim.getView().updateStatusText(!sim.suspended, sim.getField().isDefecating());
			}
		});
		return newButton;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public JButton makeNewOneStepButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.suspended || !sim.started) {
					sim.simulateOneStep();
				} else {
					return;
				}
			}
        });
		return newButton;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public JButton makeNewRunButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.started) {
					sim.start();
					sim.suspended = false;
					sim.getView().updateStatusText(!sim.suspended, sim.getField().isDefecating());
					console.write(MESSAGE_RUN);
					return;
				}
				if(sim.suspended) {
					sim.suspended = false;
					synchronized(sim.lock) {
						sim.lock.notifyAll();
					}
					console.write(MESSAGE_RESUME);
					sim.getView().updateStatusText(!sim.suspended, sim.getField().isDefecating());
				} else {
					return;
				}
			}
        });
		return newButton;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public JButton makeNewPauseButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.suspended) {
					sim.suspended = true;
					sim.getView().updateStatusText(!sim.suspended, sim.getField().isDefecating());
					console.write(MESSAGE_PAUSE);
				}
			}
        });
		return newButton;
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public JButton makeNewResetButton(JButton button) {
		JButton newButton = button;
		newButton.setBackground(BUTTON_COLOR);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.suspended) {
					sim.reset();
					console.clear();
				} else {
					return;
				}
			}
        });
		return newButton;
	}
}