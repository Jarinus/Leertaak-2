package nl.hanze.sjet.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import nl.hanze.sjet.controller.ButtonFunctions;

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
	public ButtonView() {
		setLayout(new GridLayout(0, 1));
		
		buttonFunctions = new ButtonFunctions();
		
		oneStepButton = new JButton("One step");
		runButton = new JButton("Run");
		pauseButton = new JButton("Pause");
		resetButton = new JButton("Reset");
		
		add(oneStepButton);
		add(runButton);
		add(pauseButton);
		add(resetButton);
	}
	
	public void addButtonFunctions() {
		buttonFunctions.addFunctionOneStep(oneStepButton);
		buttonFunctions.addFunctionRun(runButton);
		buttonFunctions.addFunctionPause(pauseButton);
		buttonFunctions.addFunctionReset(resetButton);
	}
}
