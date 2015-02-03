package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import nl.hanze.sjet.model.Simulator;
import nl.hanze.sjet.view.AnimalEditingFrame;

/**
 * Handles the menu actionlisteners for the MenuView.
 * 
 * @author Jan A. Germeraad
 * @version 03-02-2015
 */
public class MenuFunctions {
	/**
	 * Creates a new JMenuItem with a given String in the given Simulator.
	 * Also adds a new ActionListener to it.
	 * @param string The String specified for the button.
	 * @param simulator The simulator which uses the menu.
	 * @return item The created JMenuItem with the ActionListener.
	 */
	public JMenuItem makeNewEditable(String string, Simulator simulator) {
		JMenuItem item = new JMenuItem(string);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AnimalEditingFrame(string, simulator);
			}
		});
		return item;
	}
}
