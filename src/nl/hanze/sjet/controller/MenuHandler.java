package nl.hanze.sjet.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import nl.hanze.sjet.model.Fox;
import nl.hanze.sjet.model.Rabbit;
import nl.hanze.sjet.view.AboutFrame;
import nl.hanze.sjet.view.EditingFrame;
import nl.hanze.sjet.view.SimulatorView;

@SuppressWarnings("serial")
public class MenuHandler extends JMenuBar {
	private static final Color MENU_COLOR = new Color(220, 220, 220);
	private static final String VERSION = "Version 0.0";
	private SimulatorView simulatorView;
	private JMenu animals;
	private JMenuItem aRabbit;
	private JMenuItem aFox;
	private JMenu about;
	private JMenuItem showAbout;
	
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
		
		about = new JMenu("About");
		about.setBackground(MENU_COLOR);
		
		showAbout = new JMenuItem("Show About");
		showAbout.setBackground(MENU_COLOR);
		showAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(simulatorView, "Sjet Simulator\n\n" + VERSION, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.add(showAbout);
		
		this.add(animals);
		this.add(about);
	}
}
