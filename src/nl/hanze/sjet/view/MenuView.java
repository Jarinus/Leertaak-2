package nl.hanze.sjet.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nl.hanze.sjet.controller.MenuFunctions;

@SuppressWarnings("serial")
public class MenuView extends JMenuBar {
	JMenu animals, legend, about;
	JMenuItem 	animalsFerret, animalsRabbit, animalsSnake, animalsHawk, animalsWolf,
				legendFerret, legendRabbit, legendSnake, legendHawk, legendWolf;
	
	public MenuView() {
		MenuFunctions menuFunctions = new MenuFunctions();
		
		animals = new JMenu("Animals");
		legend = new JMenu("Legend");
		about = new JMenu("About");

		animals.add(menuFunctions.makeNewEditable("Ferret"));
		animals.add(menuFunctions.makeNewEditable("Rabbit"));
		animals.add(menuFunctions.makeNewEditable("Snake"));
		animals.add(menuFunctions.makeNewEditable("Hawk"));
		animals.add(menuFunctions.makeNewEditable("Wolf"));
		
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
