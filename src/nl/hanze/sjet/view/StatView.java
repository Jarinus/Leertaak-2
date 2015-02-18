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
	//The Field statistics for the Arc. 
	private FieldStats stats;
	//A map for the colors
	@SuppressWarnings("rawtypes")
	private Map<Class, Color> colors;
	
	/**
	 * The constructor for the StatView.
	 */
	@SuppressWarnings("rawtypes")
	public StatView() {
		stats = null;
		colors = new HashMap<Class, Color>();
		setPreferredSize(new Dimension(200, 200));
	}
	/**
	 * sets the statistics of Field to a local variable
	 * @param stats
	 */
	public void setStats(FieldStats stats) {
		this.stats = stats;
	}
	/**
	 * Set colors for the arc.
	 * @param key
	 * @param color
	 */
	@SuppressWarnings("rawtypes")
	public void setColor(Class key, Color color) {
		colors.put(key, color);
	}
	/**
	 * Paints the arc with the statistics of the field
	 */
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
				double total = 10800.0;
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
				if(totalAngle != 360) {
					g.setColor(getBackground());
					g.fillArc(0, 0, getWidth(), getHeight(), lastAngle, -(360 - totalAngle));
				}
			}
		}
	}
}
