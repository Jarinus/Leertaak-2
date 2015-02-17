package nl.hanze.sjet.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import nl.hanze.sjet.model.FieldStats;

@SuppressWarnings("serial")
public class StatView extends JPanel {
	private FieldStats stats;
	@SuppressWarnings("rawtypes")
	private Map<Class, Color> colors;
	
	@SuppressWarnings("rawtypes")
	public StatView() {
		stats = null;
		colors = new HashMap<Class, Color>();
		setPreferredSize(new Dimension(200, 200));
	}
	
	public void setStats(FieldStats stats) {
		this.stats = stats;
	}
	
	@SuppressWarnings("rawtypes")
	public void setColor(Class key, Color color) {
		colors.put(key, color);
	}
	
	@SuppressWarnings("rawtypes")
	public void paintComponent(Graphics g) {
		if(stats == null) {
			g.setColor(Color.gray);
			g.fillArc(0, 0, getWidth(), getHeight(), 0, 360);
		} else {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			Map<Class, Integer> values = stats.getPopulationDetails();
			if(values != null && values.size() > 0) {
				double total = 0.0;
				for(Class key : values.keySet()) {
					total += values.get(key);
				}
				int lastAngle = 90;
				int totalAngle = 0;
				for(Class key : values.keySet()) {
					double temp = (double) ((values.get(key) / total) * 360);
					int angle = (int) temp;
					totalAngle += angle;
					g.setColor(colors.get(key));
					g.fillArc(0, 0, getWidth(), getHeight(), lastAngle, -angle);
					lastAngle -= angle;
				}
				if(totalAngle < 360) {
					g.setColor(Color.orange);
					g.fillArc(0, 0, getWidth(), getHeight(), lastAngle, 360 - totalAngle);
				}
			}
		}
	}
}
