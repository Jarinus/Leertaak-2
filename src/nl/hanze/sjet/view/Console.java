package nl.hanze.sjet.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Console extends JPanel {
	private JTextArea textArea;
	
	public Console() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel filler = new JPanel();
		filler.setBackground(getBackground());
		add(filler, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 0;
		
		setPreferredSize(new Dimension(100, 160));
		setBorder(BorderFactory.createEtchedBorder());
		
		textArea = new JTextArea(10, 150);
		textArea.append("Console version 1.0");
		textArea.setBackground(getBackground());
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setCaretPosition(textArea.getText().length());
		add(textArea, gbc);
	}
	
	public void write(String str) {
		textArea.append(str + "\n");
		textArea.setCaretPosition(textArea.getText().length());
	}
}
