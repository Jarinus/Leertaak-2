package nl.hanze.sjet.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import nl.hanze.sjet.controller.ButtonFunctions;
import nl.hanze.sjet.model.Simulator;

@SuppressWarnings("serial")
public class ButtonView extends JPanel {
	// The button to perform one step.
	private JButton oneStepButton;
	// The button to run the current simulation.
	private JButton runButton;
	// The button to pause the current simulation.
	private JButton pauseButton;
	// The button to reset the current situation.
	private JButton resetButton;
	// 
	private ButtonFunctions buttonFunctions;
	
	/**
	 * Constructor for ButtonView.
	 * Makes buttons and adds them to the JPanel.
	 */
	public ButtonView(ButtonFunctions buttonFunctions) {
		this.buttonFunctions = buttonFunctions;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		
		oneStepButton = new JButton("One step");
		runButton = new JButton("Run");
		pauseButton = new JButton("Pause");
		resetButton = new JButton("Reset");
		
		panel.add(oneStepButton);
		panel.add(runButton);
		panel.add(pauseButton);
		panel.add(resetButton);
		
		add(panel);
		addButtonFunctions();
	}
	
	public void addButtonFunctions() {
		buttonFunctions.addFunctionOneStep(oneStepButton);
		buttonFunctions.addFunctionRun(runButton);
		buttonFunctions.addFunctionPause(pauseButton);
		buttonFunctions.addFunctionReset(resetButton);
	}
}
