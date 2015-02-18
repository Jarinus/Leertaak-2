package nl.hanze.sjet.view;

import javax.swing.*;

import nl.hanze.sjet.model.Chicken;
import nl.hanze.sjet.model.Fox;
import nl.hanze.sjet.model.Rabbit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EditingFrame extends JFrame {
	//The animal variables.
	private int maxAge, breedingAge, maxLitterSize, foodValue;
	private double breedingProb;
	//the number of columns for the JtextFields.
	private static final int NUMBER_OF_COLUMNS = 12;
	//The Buttons for the Frame
	private JButton cancel;
	private JButton defaultBut;
	private JButton submitBut;
	//The TextFields for the Frame.
	private JTextField maxAgeField;
	private JTextField breedingAgeField;
	private JTextField breedingProbField;
	private JTextField maxLitterSizeField;
	private JTextField foodValueField;
	//The Container for the frame 
	private Container contents;
	//The Image object for the images in the frame.
	private ImageIcon images;
	//The animal class for the type of animal.
	@SuppressWarnings("rawtypes")
	private Class animal;
	
	//The Button and the Backgroundcolor.
	private static final Color BACKGROUND_COLOR = new Color(200, 200, 200);
	private static final Color BUTTON_COLOR = new Color(220, 220, 220);
	
	/**
	 * Makes the Editing frame for the type of animal.
	 * @param c
	 */
	@SuppressWarnings("rawtypes")
	public EditingFrame(Class c) {
		animal = c;
		
		setTitle("Editing Frame: " + c.getName().substring(20));
		setSize(300, 200);
		getValues();
		setImage();
		
		JLabel image = new JLabel(images);
		JLabel animalName = new JLabel(c.getName().substring(20));
		
		JLabel maxAge = new JLabel("Max Age:");
		JLabel breedingAge = new JLabel("Breeding age:");
		JLabel breedingProb = new JLabel("Breeding Probability:");
		JLabel maxLitterSize = new JLabel("Max Litter Size:");
		JLabel foodValue = new JLabel("Food Value:");
		
		maxAgeField = new JTextField("" + this.maxAge,NUMBER_OF_COLUMNS);
		breedingAgeField = new JTextField(""+ this.breedingAge, NUMBER_OF_COLUMNS);
		breedingProbField = new JTextField("" + this.breedingProb, NUMBER_OF_COLUMNS);
		maxLitterSizeField = new JTextField("" + this.maxLitterSize, NUMBER_OF_COLUMNS);
		foodValueField = new JTextField("" + this.foodValue, NUMBER_OF_COLUMNS);
		
		cancel = new JButton("Cancel");
		cancel.setBackground(BUTTON_COLOR);
		defaultBut = new JButton("Default");
		defaultBut.setBackground(BUTTON_COLOR);
		submitBut = new JButton("Submit");
		submitBut.setBackground(BUTTON_COLOR);
		setButtons();
		
		JPanel upper = new JPanel(new GridLayout(1,5,4,4));
		upper.setBackground(BACKGROUND_COLOR);
		upper.add(image);
		upper.add(animalName);
		JPanel upperFlow = new JPanel();
		upperFlow.add(upper);
		upperFlow.setBackground(BACKGROUND_COLOR);
		
		JPanel centralLeft = new JPanel(new GridLayout(5,1,0,6));
		centralLeft.setBackground(BACKGROUND_COLOR);
		centralLeft.add(maxAge);
		centralLeft.add(breedingAge);
		centralLeft.add(breedingProb);
		centralLeft.add(maxLitterSize);
		centralLeft.add(foodValue);
		JPanel centralLeftFlow = new JPanel();
		centralLeftFlow.add(centralLeft);
		centralLeftFlow.setBackground(BACKGROUND_COLOR);
		
		JPanel centralRight = new JPanel(new GridLayout(5,1,0,2));
		centralRight.setBackground(BACKGROUND_COLOR);
		centralRight.add(maxAgeField);
		centralRight.add(breedingAgeField);
		centralRight.add(breedingProbField);
		centralRight.add(maxLitterSizeField);
		centralRight.add(foodValueField);
		JPanel centralRightFlow = new JPanel();
		centralRightFlow.add(centralRight);
		centralRightFlow.setBackground(BACKGROUND_COLOR);
		
		JPanel bottom = new JPanel(new GridLayout(1,3,4,0));
		bottom.setBackground(BACKGROUND_COLOR);
		bottom.add(cancel);
		bottom.add(defaultBut);
		bottom.add(submitBut);
		JPanel bottomFlow = new JPanel();
		bottomFlow.setBackground(BACKGROUND_COLOR);
		bottomFlow.add(bottom);
		
		JPanel centralFlow = new JPanel(new GridLayout(1,2));
		centralFlow.add(centralLeftFlow);
		centralFlow.add(centralRightFlow);
		
		contents = getContentPane();
		contents.setBackground(BACKGROUND_COLOR);
		contents.setLayout(new BorderLayout(6,6));
		contents.add(upperFlow, BorderLayout.NORTH);
		contents.add(centralFlow, BorderLayout.CENTER);
		contents.add(bottomFlow, BorderLayout.SOUTH);
		
		repaint();
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}
	
	/**
	 * gets the variables from the type of animal selected.
	 */
	public void getValues(){
		if(animal.getName() == "nl.hanze.sjet.model.Fox"){
			//Fox fox = (Fox) animal.cast(Fox.class);
			maxAge = Fox.getMaxAge();
			breedingAge = Fox.getBreedingAge();
			breedingProb = Fox.getBreedingProbability();
			maxLitterSize = Fox.getMaxLitterSize();
			foodValue = Fox.getRabbitFoodValue();
		}
		if(animal.getName() == "nl.hanze.sjet.model.Rabbit"){
			maxAge = Rabbit.getMaxAge();
			breedingAge = Rabbit.getBreedingAge();
			breedingProb = Rabbit.getBreedingProbability();
			maxLitterSize = Rabbit.getMaxLitterSize();
			foodValue = 0;
		}
		if(animal.getName() == "nl.hanze.sjet.model.Chicken");{
			maxAge = Chicken.getMAX_AGE();
			breedingAge = Chicken.getBREEDING_AGE();
			breedingProb = Chicken.getBREEDING_PROBABILITY();
			maxLitterSize = Chicken.getMAX_LITTER_SIZE();
			foodValue = 0;
		}
	}
	/**
	 * sets the image to the frame of the tpe of animal selected.
	 */
	public void setImage(){
		switch(animal.getName().substring(20)){
		case "Rabbit": 
			images = new ImageIcon("bin/nl/hanze/sjet/images/Rabbit.jpg");
			break;
		case "Fox":
			images = new ImageIcon("bin/nl/hanze/sjet/images/Fox.jpg");
			break;
		case "Chicken":
			images = new ImageIcon("bin/nl/hanze/sjet/images/Chicken.jpg");
			break;
		}
	}
	/**
	 * sets the values of the animals with the new variables.
	 * throws a NumberFormatException if a false char is typed.
	 */
	public void setValues(){
		try{
		switch(animal.getName().substring(20)){
		case "Rabbit":
			Rabbit.setMAX_AGE(Integer.parseInt(maxAgeField.getText()));
			Rabbit.setBREEDING_AGE(Integer.parseInt(breedingAgeField.getText()));
			Rabbit.setBREEDING_PROBABILITY(Double.parseDouble(breedingProbField.getText()));
			Rabbit.setMAX_LITTER_SIZE(Integer.parseInt(maxLitterSizeField.getText()));
			break;
		case "Fox":
			Fox.setMAX_AGE(Integer.parseInt(maxAgeField.getText()));
			Fox.setBREEDING_AGE(Integer.parseInt(breedingAgeField.getText()));
			Fox.setBREEDING_PROBABILITY(Double.parseDouble(breedingProbField.getText()));
			Fox.setMAX_LITTER_SIZE(Integer.parseInt(maxLitterSizeField.getText()));
			Fox.setRABBIT_FOOD_VALUE(Integer.parseInt(foodValueField.getText()));
			break;
		case "Chicken":
			Chicken.setMAX_AGE(Integer.parseInt(maxAgeField.getText()));
			Chicken.setBREEDING_AGE(Integer.parseInt(breedingAgeField.getText()));
			Chicken.setBREEDING_PROBABILITY(Double.parseDouble(breedingProbField.getText()));
			Chicken.setMAX_LITTER_SIZE(Integer.parseInt(maxLitterSizeField.getText()));
			break;
			}
		}catch(NumberFormatException e){
			System.err.println("Please type right format Int or Double");
			JOptionPane.showMessageDialog(this, "Sjet Simulator\n\n Please use a number", "Wrong Type Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		dispose();
		
		}
	/**
	 * sets the default variables for the type of animal selected. 
	 */
	public void setDefaultValues(){
		switch(animal.getName().substring(20)){
		case "Rabbit":
			maxAgeField.setText("" + Rabbit.DEF_MAX_AGE);
			breedingAgeField.setText("" + Rabbit.DEF_BREEDING_AGE);
			breedingProbField.setText("" + Rabbit.DEF_BREEDING_PROBABILITY);
			maxLitterSizeField.setText("" + Rabbit.DEF_MAX_LITTER_SIZE);
			//foodValueField.setText("" + Rabbit.);
		case "Fox":
			maxAgeField.setText("" + Fox.DEF_MAX_AGE);
			breedingAgeField.setText("" + Fox.DEF_BREEDING_AGE);
			breedingProbField.setText("" + Fox.DEF_BREEDING_PROBABILITY);
			maxLitterSizeField.setText("" + Fox.DEF_MAX_LITTER_SIZE);
			foodValueField.setText("" + Fox.DEF_RABBIT_FOOD_VALUE);
		case "Chicken":
			maxAgeField.setText("" + Chicken.DEF_MAX_AGE);
			breedingAgeField.setText("" + Chicken.DEF_BREEDING_AGE);
			breedingProbField.setText("" + Chicken.DEF_BREEDING_PROBABILITY);
			maxLitterSizeField.setText("" + Chicken.DEF_MAX_LITTER_SIZE);
			break;
		}
		
	}
	/**
	 * sets the Action Listeners for the buttons in the frame.
	 */
	public void setButtons(){
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		defaultBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent E){
				setDefaultValues();
				setValues();
				dispose();
				new EditingFrame(animal);
			}
		});
		
		submitBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent E){
				setValues();
			}
		});
		
	}
	
}
