package nl.hanze.sjet.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	
	/**
	 * Constructor for ButtonView.
	 * Makes buttons and adds them to the JPanel.
	 */
	public ButtonView() {
		setLayout(new GridLayout(0, 1));
		
		oneStepButton = new JButton("One step");
		runButton = new JButton("Run");
		pauseButton = new JButton("Pause");
		resetButton = new JButton("Reset");
		
		add(oneStepButton);
		add(runButton);
		add(pauseButton);
		add(resetButton);
	}
	
	/**
	 * Getter for the oneStepButton.
	 * @return oneStepButton
	 */
	public JButton getOneStepButton() {
		return oneStepButton;
	}

	/**
	 * Getter for the runButton.
	 * @return runButton
	 */
	public JButton getRunButton() {
		return runButton;
	}

	/**
	 * Getter for the pauseButton.
	 * @return pauseButton
	 */
	public JButton getPauseButton() {
		return pauseButton;
	}

	/**
	 * Getter for the resetButton.
	 * @return resetButton
	 */
	public JButton getResetButton() {
		return resetButton;
	}
}
