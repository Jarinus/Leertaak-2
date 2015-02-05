package nl.hanze.sjet.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EditingFrame extends JFrame {
	@SuppressWarnings("rawtypes")
	public EditingFrame(Class c) {
		setTitle("Editing Frame: " + c.getName().substring(20));
		setSize(300, 200);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
