package nl.hanze.sjet.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import nl.hanze.sjet.model.Field;
import nl.hanze.sjet.model.FieldStats;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author Jan A. Germeraad
 * @version 22-01-2015
 */
@SuppressWarnings("serial")
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;

    private final String STEP_PREFIX = "Step: ";
    private final String POPULATION_PREFIX = "Population: ";
    private JLabel stepLabel, population;
    
    private StatView animalsAlive;
    private StatView animalsDeceased;
    
    private FieldView fieldView;
    // A list of all the buttons.
    private JButton[] buttonList = {new JButton("One step"),
    								new JButton("Run"),
    								new JButton("Pause"),
    								new JButton("Reset")};
    // A list of all the menus.
	private JMenu[] menus = {	new JMenu("Menu 1"),
								new JMenu("Menu 2"),
								new JMenu("Animals"),
								new JMenu("Help")};
	private JMenuItem[] animalItems = {	new JMenuItem("Rat"),
										new JMenuItem("Rabbit"),
										new JMenuItem("Snake"),
										new JMenuItem("Hawk"),
										new JMenuItem("Wolf")};
    // A map for storing colors for participants in the simulation
    @SuppressWarnings("rawtypes")
	private Map<Class, Color> colors;
    // A statistics object computing and storing simulation information
    private FieldStats stats;

    /**
     * Create a view of the given width and height.
     * @param height The simulation's height.
     * @param width  The simulation's width.
     */
    @SuppressWarnings("rawtypes")
	public SimulatorView(int height, int width)
    {
    	JMenuBar menuBar = new JMenuBar();
    	
    	for(JMenuItem item : animalItems) {
    		menus[2].add(item);
    	}
    	
    	for(int i = 0; i < menus.length; i++) {
    		menuBar.add(menus[i]);
    	}
    	
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	for (int i = 0; i < buttonList.length; i++) {
    		buttonList[i].setSize(180, 40);
    		panel.add(buttonList[i]);
    	}
    	
    	JPanel flow = new JPanel();
    	flow.add(panel);
    	
        stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("Wolf and Rabbit Simulation");
        stepLabel = new JLabel(STEP_PREFIX, JLabel.CENTER);
        population = new JLabel(POPULATION_PREFIX, JLabel.CENTER);
        
        setLocation(100, 50);
        
        fieldView = new FieldView(height, width);
        
        animalsAlive = new StatView(200, 200, colors);
        animalsDeceased = new StatView(200, 200, colors);
        
        JPanel statView = new JPanel(new BorderLayout(6, 6));
        statView.add(animalsAlive, BorderLayout.NORTH);
        statView.add(animalsDeceased, BorderLayout.SOUTH);
        
        JPanel simPanel = new JPanel(new BorderLayout(6, 6));
        simPanel.setBorder(new EtchedBorder());
        simPanel.add(stepLabel, BorderLayout.NORTH);
        simPanel.add(fieldView, BorderLayout.CENTER);
        simPanel.add(population, BorderLayout.SOUTH);
        
        Container contents = getContentPane();
        contents.setLayout(new BorderLayout(6, 6));
        contents.add(flow, BorderLayout.WEST);
        contents.add(statView, BorderLayout.CENTER);
        contents.add(simPanel, BorderLayout.EAST);

        setJMenuBar(menuBar);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
    }
    
    /**
     * Define a color to be used for a given class of animal.
     * @param animalClass The animal's Class object.
     * @param color The color to be used for the given class.
     */
    @SuppressWarnings("rawtypes")
    public void setColor(Class animalClass, Color color)
    {
        colors.put(animalClass, color);
    }

    /**
     * @return The color to be used for a given class of animal.
     */
    @SuppressWarnings("rawtypes")
	private Color getColor(Class animalClass)
    {
        Color col = colors.get(animalClass);
        if(col == null) {
            // no color defined for this class
            return UNKNOWN_COLOR;
        }
        else {
            return col;
        }
    }

    /**
     * Show the current status of the field.
     * @param step Which iteration step it is.
     * @param field The field whose status is to be displayed.
     */
    public void showStatus(int step, Field field)
    {
        if(!isVisible()) {
            setVisible(true);
        }
            
        stepLabel.setText(STEP_PREFIX + step);
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, getColor(animal.getClass()));
                }
                else {
                    fieldView.drawMark(col, row, EMPTY_COLOR);
                }
            }
        }
        stats.countFinished();

        population.setText(POPULATION_PREFIX + stats.getPopulationDetails(field));
        fieldView.repaint();
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    /**
     * Provide a graphical view of a rectangular field. This is 
     * a nested class (a class defined inside a class) which
     * defines a custom component for the user interface. This
     * component displays the field.
     * This is rather advanced GUI stuff - you can ignore this 
     * for your project if you like.
     */
    private class FieldView extends JPanel
    {
        private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;

        /**
         * Create a new FieldView component.
         */
        public FieldView(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component
         * may be resized, compute the scaling factor again.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                fieldImage = fieldView.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }
        
        /**
         * Paint on grid location on this field in a given color.
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }

        /**
         * The field view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    // Rescale the previous image.
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
    
    public JButton[] getButtonList() {
    	return buttonList;
    }
    
    public JMenu[] getMenuList() {
    	return menus;
    }
}
