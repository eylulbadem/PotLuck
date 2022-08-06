import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A class to create pot luck game with gui
 * @author Eylul Badem
 * @version 1.0, 31.03.2021
*/ 
public class Bomb extends JButton implements ActionListener {
    
    // Properties
    boolean finished;
    
    // Constructor
    public Bomb(String number) {
        setText(number);
    }
    
    // Methods
    
    public boolean getFinished() {
        return finished;
    }
    
    public void setFinished(boolean newFinished) {
        finished = newFinished;
    }
    
    /**
     * This method controls what will happen when an action is perceived
     * @param event any action perceived
     */
    @Override
    public void actionPerformed( ActionEvent e)
    {
        System.out.println("");
    }
    
    /**
     * This method overrides the usual paintComponent method to draw a bomb
     * @param g any graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
    Graphics2D graphics = (Graphics2D) g;
    
    super.paintComponent(g);
    
    if (finished) 
    {
        // Bomb fuse
        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawArc(getWidth() / 2, getHeight() / 9, getWidth() / 4, getHeight() * 2 / 9, 180, -100);
            
        // Bomb cap
        graphics.setColor(Color.GRAY);
        graphics.fillRect( 3 * getWidth() / 8, getHeight() / 5, getWidth() / 4, getHeight() / 6);
            
        // Bomb body
        graphics.setColor(Color.BLACK);
        graphics.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
    }
}
}
    