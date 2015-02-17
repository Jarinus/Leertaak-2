package nl.hanze.sjet.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import nl.hanze.sjet.controller.ButtonHandler;
import nl.hanze.sjet.controller.MenuHandler;
import nl.hanze.sjet.model.Field;
import nl.hanze.sjet.model.FieldStats;
import nl.hanze.sjet.model.Simulator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A graphical view of the simulation grid.
 * The view displays a colored rectangle for each location 
 * representing its contents. It uses a default background color.
 * Colors for each type of species can be defined using the
 * setColor method.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
@SuppressWarnings("serial")
public class SimulatorView extends JFrame
{
    // Colors used for empty locations.
    private static final Color EMPTY_COLOR = Color.white;

    // Color used for objects that have no defined color.
    private static final Color UNKNOWN_COLOR = Color.gray;
    private FieldView fieldView;
    private StatView statView;
    private JButton oneStepButton;
    private JButton runButton;
    private JButton pauseButton;
    private JButton resetButton;
    private JButton sicknessButton;
    private JButton switchDefecationButton;
    private JLabel status;
    public Console console;
    private Legenda legenda;
	private Color backgroundColor = new Color(200, 200, 200);
    
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
	public SimulatorView(Simulator sim, int height, int width)
    {
        fieldView = new FieldView(height, width);
        statView = new StatView();
        statView.setBackground(backgroundColor);
        console = new Console();
        console.setBackground(backgroundColor);
        legenda = new Legenda();
        console.setBackground(backgroundColor);
    	
    	ButtonHandler buttonHandler = new ButtonHandler(sim);
    	oneStepButton = buttonHandler.makeNewOneStepButton(new JButton("One Step"));
    	runButton = buttonHandler.makeNewRunButton(new JButton("Run"));
    	pauseButton = buttonHandler.makeNewPauseButton(new JButton("Pause"));
    	resetButton = buttonHandler.makeNewResetButton(new JButton("Reset"));
    	switchDefecationButton = buttonHandler.makeNewSwitchDefecationButton(new JButton("Switch Defecation"));
    	sicknessButton = buttonHandler.makeNewSicknessButton(new JButton("Start sickness"));
    	
    	JPanel panel = new JPanel(new GridLayout(0, 1));
    	panel.setBackground(backgroundColor);
    	panel.add(oneStepButton);
    	panel.add(runButton);
    	panel.add(pauseButton);
    	panel.add(resetButton);
    	panel.add(switchDefecationButton);
    	panel.add(sicknessButton);
    	
    	JPanel flow = new JPanel();
    	flow.setBackground(backgroundColor);
    	flow.add(panel);
    	
        stats = new FieldStats();
        colors = new LinkedHashMap<Class, Color>();

        setTitle("SJET: Environmental Behavior Simulation");
        
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(backgroundColor);
    	status = new JLabel("");
    	updateStatusText(false, true);
        statusPanel.add(status);
        
        JPanel simPanel = new JPanel(new BorderLayout(6, 6));
        simPanel.setBackground(backgroundColor);
        fieldView.setBorder(new EtchedBorder());
        simPanel.add(statusPanel, BorderLayout.NORTH);
        simPanel.add(fieldView, BorderLayout.CENTER);
        simPanel.add(console, BorderLayout.SOUTH);
        
        JPanel statPanel = new JPanel();
        statPanel.setPreferredSize(new Dimension(200, 200));
        statPanel.setBackground(backgroundColor);
        statPanel.add(statView, BorderLayout.NORTH);
        statPanel.add(legenda, BorderLayout.SOUTH);
        
        JPanel contents = new JPanel();
        contents.setBackground(backgroundColor);
        contents.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contents.setLayout(new BorderLayout(6, 6));
        contents.add(flow, BorderLayout.WEST);
        contents.add(simPanel, BorderLayout.CENTER);
        
        JPanel infoPanel = new JPanel(new BorderLayout(6, 6));
        infoPanel.setBackground(backgroundColor);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        infoPanel.add(new JLabel("Version 0.0"));

        Container container = getContentPane();
        container.setBackground(backgroundColor);
        container.add(contents, BorderLayout.WEST);
        container.add(statPanel, BorderLayout.CENTER);
        container.add(infoPanel, BorderLayout.SOUTH);
        
        setJMenuBar(new MenuHandler());
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setBackground(new Color(255, 255, 255));
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
        statView.setColor(animalClass, color);
    }
    
    public void updateStatusText(boolean running, boolean defecation) {
    	status.setText("Running: " + running + " | Defecation: " + defecation);
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
        stats.reset();
        
        fieldView.preparePaint();

        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                int grassIntensity = field.getGrassIntensity(row, col);
                if(animal != null) {
                    stats.incrementCount(animal.getClass());
                    fieldView.drawMark(col, row, greenShading(grassIntensity, getColor(animal.getClass())));
                }
                else {
                    fieldView.drawMark(col, row, greenShading(grassIntensity, EMPTY_COLOR));
                }
            }
        }
        stats.countFinished();
        statView.setStats(stats);
        statView.repaint();
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
        private final int GRID_VIEW_SCALING_FACTOR = 5;

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
    
    public Console getConsole() {
    	return console;
    }
    
    public Color greenShading(int grassIntensity, Color color) {
    	if(grassIntensity > 10) {
    		grassIntensity = 10;
    	}
    	Color grass = new Color(0, grassIntensity * 25, 0, 255);
    	int red = (int) ((color.getRed() * 2) + grass.getRed()) / 3,
    		green = (int) ((color.getGreen() * 2) + grass.getGreen()) / 3,
    		blue = (int) ((color.getBlue() * 2) + grass.getBlue()) / 3;
    	return (new Color(red, green, blue, 255));
    }
}
