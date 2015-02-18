package nl.hanze.sjet.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import nl.hanze.sjet.model.Chicken;
import nl.hanze.sjet.model.Fox;
import nl.hanze.sjet.model.Rabbit;
import nl.hanze.sjet.view.EditingFrame;
import nl.hanze.sjet.view.SimulatorView;

@SuppressWarnings("serial")
public class MenuHandler extends JMenuBar {
	//The color for the Menu
	private static final Color MENU_COLOR = new Color(220, 220, 220);
	//The version number of the simulator
	private static final String VERSION = "Version 1.5";
	//the object SimulatorView
	private SimulatorView simulatorView;
	// The JMenu 
	private JMenu animals;
	//The menu items for JMenu animals
	private JMenuItem aRabbit;
	private JMenuItem aFox;
	private JMenuItem aChicken;
	//The JMenu about
	private JMenu about;
	//JMenuItem for about
	private JMenuItem showAbout;
	
	/**
	 * MenuHandler constructor
	 */
	public MenuHandler() {
		setBackground(MENU_COLOR);
		animals = new JMenu("Animals");
		
		aRabbit = new JMenuItem("Rabbit");
		aRabbit.setBackground(MENU_COLOR);
		aRabbit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditingFrame(Rabbit.class);
			}
		});
		animals.add(aRabbit);
		
		aFox = new JMenuItem("Fox");
		aFox.setBackground(MENU_COLOR);
		aFox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditingFrame(Fox.class);
			}
		});
		animals.add(aFox);
		
		aChicken = new JMenuItem("Chicken");
		aChicken.setBackground(MENU_COLOR);
		aChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				new EditingFrame(Chicken.class);
			}
		});
		animals.add(aChicken);
		
		about = new JMenu("About");
		about.setBackground(MENU_COLOR);
		
		showAbout = new JMenuItem("Show About");
		showAbout.setBackground(MENU_COLOR);
		showAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(simulatorView, "SJET Simulator\n\n" + VERSION, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.add(showAbout);
		
		this.add(animals);
		this.add(about);
	}
}
