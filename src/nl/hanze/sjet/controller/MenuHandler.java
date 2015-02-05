package nl.hanze.sjet.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nl.hanze.sjet.model.Fox;
import nl.hanze.sjet.model.Rabbit;
import nl.hanze.sjet.view.AboutFrame;
import nl.hanze.sjet.view.EditingFrame;

@SuppressWarnings("serial")
public class MenuHandler extends JMenuBar {
	private static final Color MENU_COLOR = new Color(220, 220, 220);
	
	private JMenu animals;
	private JMenuItem aRabbit;
	private JMenuItem aFox;
	private JMenuItem about;
	
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
		
		about = new JMenuItem("About");
		about.setBackground(MENU_COLOR);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutFrame();
			}
		});
		
		this.add(animals);
		this.add(about);
	}
}
