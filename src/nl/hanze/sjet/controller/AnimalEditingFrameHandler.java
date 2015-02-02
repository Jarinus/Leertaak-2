package nl.hanze.sjet.controller;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.hanze.sjet.model.Simulator;

public class AnimalEditingFrameHandler {
	Simulator simulator;
	
	public AnimalEditingFrameHandler(){
		
	}
	
	public void addCancelButton(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}

}
