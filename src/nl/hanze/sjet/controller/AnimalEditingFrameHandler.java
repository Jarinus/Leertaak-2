package nl.hanze.sjet.controller;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nl.hanze.sjet.view.AnimalEditingFrame;
import javax.swing.*;

/**
 * Handles the functionality of the AnimalEditingFrame.
 * @author Jan A. Germeraad, S. Orhan
 * @version 03-02-2015
 */
public class AnimalEditingFrameHandler {
	// The AnimalEditingFrame which is to be used.
	private AnimalEditingFrame animaleditFrame;
	
	/**
	 * Constructor for AnimalEditingFrameHandler
	 * @param animaleditFrame The frame which is to be used.
	 */
	public AnimalEditingFrameHandler(AnimalEditingFrame animaleditFrame){
		this.animaleditFrame = animaleditFrame;
	}
	
	/**
	 * Adds the cancel function to a specified button.
	 * @param button The button that uses this functionality
	 */
	public void addCancelButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.dispose();
			}
		});
	}
	
	/**
	 * Adds the default function to a specified button.
	 * @param button The button that uses this functionality
	 */
	public void addDefaultButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.getJTextFieldVariables();
			}
		});
	}

	/**
	 * Adds the submit function to a specified button.
	 * @param button The button that uses this functionality
	 */
	public void addSubmitButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}

	/**
	 * Adds an ActionListener to edit MaxAge.
	 * @param textField The textfield used.
	 */
	public void addMaxAgeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setMaxAgeAni(Integer.parseInt(textField.getText()));
			}
		});
	}

	/**
	 * Adds an ActionListener to edit BreedingAge.
	 * @param textField The textfield used.
	 */
	public void addBreedingAgeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setBreedingAgeAni(Integer.parseInt(textField.getText()));
			}
		});
	}

	/**
	 * Adds an ActionListener to edit BreedingProbability.
	 * @param textField The textfield used.
	 */
	public void addBreedingProbField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setBreedingProbAni(Double.parseDouble(textField.getText()));
			}
		});
	}

	/**
	 * Adds an ActionListener to edit MaxLitterSize.
	 * @param textField The textfield used.
	 */
	public void addMaxLitterSizeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setMaxLitterSize(Integer.parseInt(textField.getText()));
			}
		});
	}

	/**
	 * Adds an ActionListener to edit FoodValue.
	 * @param textField The textfield used.
	 */
	public void addFoodValueField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setFoodValueAni(Integer.parseInt(textField.getText()));
			}
		});
	}
}
