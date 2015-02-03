package nl.hanze.sjet.view;

import java.awt.Container;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WindowView extends JFrame {
	public WindowView(int width, int height) {
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        Container contents = getContentPane();
        
        //TODO: Create a Simulator.
        
        //TODO: Add new MenuHandler and add menus to frame.
        
        //TODO: Add new ButtonHandler and add buttons to frame.
        
        //TODO: Add new SimulatorView which uses Simulator and add to frame.
        
        //TODO: Add two new CircleDiagramViews and add to frame.
	}
}
