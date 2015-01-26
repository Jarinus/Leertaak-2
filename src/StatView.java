import java.awt.*;
import java.util.Map;
import javax.swing.JPanel;

public class StatView extends JPanel {
	private int x, y;
	private Map<Class, Color> colors;
	
	public StatView(int x, int y, Map<Class, Color> colors2) {
		setSize(x, y);
		this.x = x;
		this.y = y;
		this.colors = colors2;
	}
	
	public void paintComponent(Graphics g/*, int[] animals*/) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, x, y);
		int[] animals = {100, 80, 120, 80};
		Class[] animalClasses = {Rat.class, Rabbit.class, Snake.class, /*Hawk.class, */Wolf.class};
		int angle = 0;
		int total = 0;
		
		for(int i : animals) {
			total += i;
		}
		
		for(int i = 0; i < animals.length; i++) {
			int angleLength = (int) (360 * (animals[i] / total));
			g.setColor(colors.get(animalClasses[i]));
			g.fillArc(10, 10, (int) 0.9 * x, (int) 0.9 * y, angle, angleLength);
			angle += angleLength;
		}
	}
}
