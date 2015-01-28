package nl.hanze.sjet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import nl.hanze.sjet.view.AnimalEditingFrame;

public class MenuFunctions {
	public JMenuItem makeNewEditable(String string) {
		JMenuItem item = new JMenuItem(string);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AnimalEditingFrame(string);
			}
		});
		return item;
	}
}
