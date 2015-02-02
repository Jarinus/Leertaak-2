package nl.hanze.sjet.controller;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import nl.hanze.sjet.view.AnimalEditingFrame;
import javax.swing.*;
import nl.hanze.sjet.model.Simulator;

public class AnimalEditingFrameHandler {
	private AnimalEditingFrame animaleditFrame;
	
	public AnimalEditingFrameHandler(AnimalEditingFrame animaleditFrame){
		this.animaleditFrame = animaleditFrame;
	}
	
	public void addCancelButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.dispose();
			}
		});
	}
	
	public void addDefaultButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.getJTextFieldVariables();
			}
		});
	}
	
	public void addSubmitButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}
	
	public void addMaxAgeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setMaxAgeAni(Integer.parseInt(textField.getText()));
			}
		});
	}
	
	public void addBreedingAgeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setBreedingAgeAni(Integer.parseInt(textField.getText()));
			}
		});
	}
	public void addBreedingProbField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setBreedingProbAni(Double.parseDouble(textField.getText()));
			}
		});
	}
	public void addMaxLitterSizeField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setMaxLitterSize(Integer.parseInt(textField.getText()));
			}
		});
	}
	public void addFoodValueField(JTextField textField){
		textField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				animaleditFrame.setFoodValueAni(Integer.parseInt(textField.getText()));
			}
		});
	}
}
