package nl.hanze.sjet.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Legenda extends JPanel {
	public Legenda() {
		setBackground(getBackground());
		LegendaItem li = new LegendaItem(Color.blue, "Fox");
		LegendaItem li2 = new LegendaItem(Color.green, "Snake");
		LegendaItem li3 = new LegendaItem(Color.yellow, "Rat");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(li);
		panel.add(li2);
		panel.add(li3);
		add(panel, BorderLayout.WEST);
	}
	
	public class LegendaItem extends JPanel {
		public LegendaItem(Color color, String name) {
			setBackground(getBackground());
			ColoredRectangle cr = new ColoredRectangle(color);
			add(cr);
			add(new JLabel(name));
		}
	}
	
	public class ColoredRectangle extends JPanel {
		Color color;
		
		public ColoredRectangle(Color color) {
			setBackground(getBackground());
			this.color = color;
		}
		
		public void paintComponent(Graphics g) {
			g.setColor(color);
			g.fillRect(0, 0, 20, 20);
		}
	}
}
