package nl.hanze.sjet.view;

import java.awt.*;

import javax.swing.*;

import nl.hanze.sjet.controller.AnimalEditingFrameHandler;
import nl.hanze.sjet.model.Rabbit;
import nl.hanze.sjet.model.Simulator;

@SuppressWarnings("serial")
public class AnimalEditingFrame extends JFrame {
	private Container contents;
	private static final int TEXTFIELD_LENGHT = 11;
	private AnimalEditingFrameHandler animalEditFrameHandler;
	private Simulator sim;
	private JButton cancel;
	private JButton defaultBut;
	private JButton submitBut;
	private String animalName;
	private int breedingAgeAni, maxAgeAni, maxLitterSizeAni, foodValueAni;
	private double breedingProbability;
	
	private JTextField maxAgeField;
	private JTextField breedingAgeField;
	private JTextField breedingProbField;
	private JTextField maxLitterSizeField;
	private JTextField foodValueField;
	
	public AnimalEditingFrame(String string, Simulator simulator) {
		animalName = string;
		sim = simulator;
		animalEditFrameHandler = new AnimalEditingFrameHandler(this);
		getJTextFieldVariables();
		
		//TODO add action listeners in new class AnimalEditingFunctions
		//make labels
		JLabel dierNaam = new JLabel(string);
		JLabel maxAge = new JLabel("Maximum Age:");
		JLabel breedingAge = new JLabel("Breeding Age:");
		JLabel breedingProb = new JLabel("Breeding Prob.:");
		JLabel maxLitterSize = new JLabel("Max Litter Size:");
		JLabel foodValue = new JLabel("Food Value:");
		
		//make image labels
		JLabel ferretImg = new JLabel(new ImageIcon("images/ferret.jpg"));
		JLabel rabbitImg = new JLabel(new ImageIcon("images/Rabbit.jpg"));
		JLabel snakeImg = new JLabel(new ImageIcon("images/cobra.jpg"));
		JLabel hawkImg = new JLabel(new ImageIcon("images/hawk.jpg"));
		JLabel wolfImg = new JLabel(new ImageIcon("images/wolf.jpg"));
		
		//make buttons
		/*JButton ferretBut = new JButton("Ferret");
		JButton rabbitBut = new JButton("Rabbit");
		JButton snakeBut = new JButton("Snake");
		JButton hawkBut = new JButton("Hawk");
		JButton wolfBut = new JButton("Wolf");*/
		cancel = new JButton("Cancel");
		defaultBut = new JButton("Default");
		submitBut = new JButton("Submit");
		
		//make textfields
		maxAgeField = new JTextField("" + maxAgeAni,TEXTFIELD_LENGHT);
		breedingAgeField = new JTextField("" + breedingAgeAni,TEXTFIELD_LENGHT);
		breedingProbField = new JTextField("" + breedingProbability,TEXTFIELD_LENGHT);
		maxLitterSizeField = new JTextField("" + maxLitterSizeAni,TEXTFIELD_LENGHT);
		foodValueField = new JTextField("" + foodValueAni,TEXTFIELD_LENGHT);
		
		//make the upper set
		JPanel upper = new JPanel();
		upper.setLayout(new GridLayout(1,6,4,0));
		switch(string){
		case "Ferret": upper.add(ferretImg);
		break;
		case "Rabbit": upper.add(rabbitImg);
		break;
		case "Snake": upper.add(snakeImg);
		break;
		case "Hawk": upper.add(hawkImg);
		break;
		case "Wolf": upper.add(wolfImg);
		break;
		};
		upper.add(dierNaam);
		JPanel upperFlow = new JPanel();
		upperFlow.add(upper);
		
		//make left set
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(5,1,0,6));
		left.add(maxAge);
		left.add(breedingAge);
		left.add(breedingProb);
		left.add(maxLitterSize);
		left.add(foodValue);
		JPanel leftFlow = new JPanel();
		leftFlow.add(left);
		
		//make right set
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(5,1,0,2));
		right.add(maxAgeField);
		right.add(breedingAgeField);
		right.add(breedingProbField);
		right.add(maxLitterSizeField);
		right.add(foodValueField);
		JPanel rightFlow = new JPanel();
		rightFlow.add(right);
		
		// make the center
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1,2));
		center.add(leftFlow);
		center.add(rightFlow);
		
		//make the bottom
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,3,4,0));
		bottom.add(cancel);
		bottom.add(defaultBut);
		bottom.add(submitBut);
		JPanel bottomFlow = new JPanel();
		bottomFlow.add(bottom);
		
		
		setLocation(450,100);
		
		//set the panels to the frame
		contents = getContentPane();
		contents.setLayout(new BorderLayout(6,6));
		contents.add(upperFlow,BorderLayout.NORTH);
		contents.add(center,BorderLayout.CENTER);
		contents.add(bottomFlow,BorderLayout.SOUTH);
		
		addFunctions();
		setTitle("Animalediting frame");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
	}
	
	public void addFunctions(){
		animalEditFrameHandler.addCancelButton(cancel);
		animalEditFrameHandler.addDefaultButton(defaultBut);
		animalEditFrameHandler.addMaxAgeField(maxAgeField);
		animalEditFrameHandler.addBreedingAgeField(breedingAgeField);
		animalEditFrameHandler.addBreedingProbField(breedingProbField);
		animalEditFrameHandler.addMaxLitterSizeField(maxLitterSizeField);
		animalEditFrameHandler.addFoodValueField(foodValueField);
	}
	
	public void getJTextFieldVariables(){
		switch(animalName){
		case "Ferret": 
			maxAgeAni = sim.getFerret().getMaxAge();
			breedingAgeAni = sim.getFerret().getBreedingAge();
			breedingProbability = sim.getFerret().getBreedingProbability();
			maxLitterSizeAni = sim.getFerret().getMaxLitterSize();
			foodValueAni = sim.getFerret().getFoodValue();
			break;
		case "Rabbit":
			maxAgeAni = sim.getRabbit().getMaxAge();
			breedingAgeAni = sim.getRabbit().getBreedingAge();
			breedingProbability = sim.getRabbit().getBreedingProbability();
			maxLitterSizeAni = sim.getRabbit().getMaxLitterSize();
			foodValueAni = sim.getRabbit().getFoodValue();
			break;
		case "Snake": 
			maxAgeAni = sim.getSnake().getMaxAge();
			breedingAgeAni = sim.getSnake().getBreedingAge();
			breedingProbability = sim.getSnake().getBreedingProbability();
			maxLitterSizeAni = sim.getSnake().getMaxLitterSize();
			foodValueAni = sim.getSnake().getFoodValue();
			break;
		case "Hawk":
			maxAgeAni = sim.getHawk().getMaxAge();
			breedingAgeAni = sim.getHawk().getBreedingAge();
			breedingProbability = sim.getHawk().getBreedingProbability();
			maxLitterSizeAni = sim.getHawk().getMaxLitterSize();
			foodValueAni = sim.getHawk().getFoodValue();
			break;
		case "Wolf":
			maxAgeAni = sim.getWolf().getMaxAge();
			breedingAgeAni = sim.getWolf().getBreedingAge();
			breedingProbability = sim.getWolf().getBreedingProbability();
			maxLitterSizeAni = sim.getWolf().getMaxLitterSize();
			foodValueAni = sim.getHawk().getFoodValue();
			break;
		}
	}
	public void setBreedingAgeAni(int newBreedingAge){
		switch(animalName){
		case "Ferret":
			sim.getFerret().setBreedingAge(newBreedingAge);
			break;
		case "Rabbit":
			sim.getRabbit().setBreedingAge(newBreedingAge);
			break;
		case "Snake":
			sim.getSnake().setBreedingAge(newBreedingAge);
			break;
		case "Hawk":
			sim.getHawk().setBreedingAge(newBreedingAge);
			break;
		case "Wolf":
			sim.getWolf().setBreedingAge(newBreedingAge);
			break;
		}
	}
	
	public void setMaxAgeAni(int newMaxAge){
		switch(animalName){
		case "Ferret":
			sim.getFerret().setMaxAge(newMaxAge);
			break;
		case "Rabbit":
			sim.getRabbit().setMaxAge(newMaxAge);
			break;
		case "Snake":
			sim.getSnake().setMaxAge(newMaxAge);
			break;
		case "Hawk":
			sim.getHawk().setMaxAge(newMaxAge);
			break;
		case "Wolf":
			sim.getWolf().setMaxAge(newMaxAge);
			break;
		}
	}
	
	public void setBreedingProbAni(double newBreedingProb){
		switch(animalName){
		case "Ferret":
			sim.getFerret().setBreedingProbability(newBreedingProb);
			break;
		case "Rabbit":
			sim.getRabbit().setBreedingProbability(newBreedingProb);
			break;
		case "Snake":
			sim.getSnake().setBreedingProbability(newBreedingProb);
			break;
		case "Hawk":
			sim.getHawk().setBreedingProbability(newBreedingProb);
			break;
		case "Wolf":
			sim.getWolf().setBreedingProbability(newBreedingProb);
			break;
		}
	}
	
	public void setMaxLitterSize(int newMaxLitterSize){
		switch(animalName){
		case "Ferret":
			sim.getFerret().setMaxLitterSize(newMaxLitterSize);
			break;
		case "Rabbit":
			sim.getRabbit().setMaxLitterSize(newMaxLitterSize);
			break;
		case "Snake":
			sim.getSnake().setMaxLitterSize(newMaxLitterSize);
			break;
		case "Hawk":
			sim.getHawk().setMaxLitterSize(newMaxLitterSize);
			break;
		case "Wolf":
			sim.getWolf().setMaxLitterSize(newMaxLitterSize);
			break;
		}
	}
	
	public void setFoodValueAni(int newFoodValue){
		switch(animalName){
		case "Ferret":
			sim.getFerret().setFoodValue(newFoodValue);
			break;
		case "Rabbit":
			sim.getRabbit().setFoodValue(newFoodValue);
			break;
		case "Snake":
			sim.getSnake().setFoodValue(newFoodValue);
			break;
		case "Hawk":
			sim.getHawk().setFoodValue(newFoodValue);
			break;
		case "Wolf":
			sim.getWolf().setFoodValue(newFoodValue);
			break;
		}
	}
}
