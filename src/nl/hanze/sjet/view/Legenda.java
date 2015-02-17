package nl.hanze.sjet.view;

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
		LegendaItem li = new LegendaItem(Color.red, "Hunter");
		LegendaItem li2 = new LegendaItem(Color.blue, "Fox");
		LegendaItem li3 = new LegendaItem(Color.orange, "Rabbit");
		LegendaItem li4 = new LegendaItem(Color.magenta, "Chicken");
		LegendaItem li5 = new LegendaItem(Color.pink, "Egg");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(li);
		panel.add(li2);
		panel.add(li3);
		panel.add(li4);
		panel.add(li5);
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
