package nl.hanze.sjet.view;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame {
	// This class is not used
	public AboutFrame() {
		setTitle("About");
		setSize(400, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel version = new JLabel("Version 0.0");
		JLabel groupName = new JLabel("Sjet");
		JLabel names = new JLabel("Jari, Serkan, Tim, Ewoud");
		
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(3,0,4,4));
		contents.add(groupName);
		contents.add(names);
		contents.add(version);
		
		pack();
	}
}
