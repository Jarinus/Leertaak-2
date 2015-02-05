package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import nl.hanze.sjet.model.Simulator;

/**
 * 
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */
public class ButtonHandler {
	private Simulator sim;
	
	/**
	 * The constructor for the ButtonHandler
	 * @param sim The Simulator used.
	 */
	public ButtonHandler(Simulator sim) {
		this.sim = sim;
	}
	
	public JButton makeNewSwitchDefecationButton(JButton button) {
		JButton newButton = button;
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.getField().isDefecating()) {
					sim.getField().setDefecating(false);
				} else {
					sim.getField().setDefecating(true);
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
	public JButton makeNewOneStepButton(JButton button) {
		JButton newButton = button;
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
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.started) {
					sim.start();
					return;
				}
				if(sim.suspended) {
					sim.suspended = false;
					synchronized(sim.lock) {
						sim.lock.notifyAll();
					}
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
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sim.suspended) {
					sim.suspended = true;
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
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sim.suspended) {
					sim.reset();
				} else {
					return;
				}
			}
        });
		return newButton;
	}
}