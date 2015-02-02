package nl.hanze.sjet.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nl.hanze.sjet.controller.MenuFunctions;
import nl.hanze.sjet.model.Simulator;

@SuppressWarnings("serial")
public class MenuView extends JMenuBar {
	JMenu animals, legend, about;
	JMenuItem 	animalsFerret, animalsRabbit, animalsSnake, animalsHawk, animalsWolf,
				legendFerret, legendRabbit, legendSnake, legendHawk, legendWolf;
	
	public MenuView(Simulator simulator) {
		MenuFunctions menuFunctions = new MenuFunctions();
		
		animals = new JMenu("Animals");
		legend = new JMenu("Legend");
		about = new JMenu("About");

		animals.add(menuFunctions.makeNewEditable("Ferret", simulator));
		animals.add(menuFunctions.makeNewEditable("Rabbit", simulator));
		animals.add(menuFunctions.makeNewEditable("Snake", simulator));
		animals.add(menuFunctions.makeNewEditable("Hawk", simulator));
		animals.add(menuFunctions.makeNewEditable("Wolf", simulator));
		
		legendFerret = new JMenuItem("Ferret");
		legendRabbit = new JMenuItem("Rabbit");
		legendSnake = new JMenuItem("Snake");
		legendHawk = new JMenuItem("Hawk");
		legendWolf = new JMenuItem("Wolf");
		
		legend.add(legendFerret);
		legend.add(legendRabbit);
		legend.add(legendSnake);
		legend.add(legendHawk);
		legend.add(legendWolf);

		add(animals);
		add(legend);
		add(about);
	}
}
