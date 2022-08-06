import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

/**
 * A class to create pot luck game with gui
 * @author Eylul Badem
 * @version 1.0, 31.03.2021
*/ 
public class PotLuck extends JComponent implements ActionListener {

private JPanel    contentPane;
private JPanel    panel;
private JFrame    frame;
private JLabel    label;
private JButton   but;
private Bomb      bombBut1;
private Bomb      bombBut2;
private int       prize = 0;
private int       bomb1 = 0;
private int       bomb2 = 0;
private int       count;
final int ROW = 5;
final int COLUMN = 5;
  
  // Main method
  public static void main(String[] args) { 
    
    PotLuck game = new PotLuck();
    game.setVisible(true);
    
  }
  
  // Constructor
  public PotLuck() {
    
    count = 0;
    frame = new JFrame("Pot Luck");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 500, 500);
    frame.setVisible(true);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(COLUMN, ROW, COLUMN, ROW));
    contentPane.setLayout(new BorderLayout(0, 0));
    frame.setContentPane(contentPane);
    
    panel = new JPanel();
    contentPane.add(panel, BorderLayout.CENTER);
    panel.setLayout(new GridLayout(ROW, COLUMN, ROW*2, COLUMN*2));
    
    // Build buttons
    while (prize == bomb1 ||  prize == bomb2 ||bomb1 == bomb2){
      prize = (int)(Math.random()*(ROW*COLUMN));
      bomb1 = (int)(Math.random()*(ROW*COLUMN));
      bomb2 = (int)(Math.random()*(ROW*COLUMN));
    }
    System.out.println(prize + 1);
    System.out.println(bomb1 + 1);
    System.out.println(bomb2 + 1);
    
    for(int i = 0; i < (ROW*COLUMN); i++){
      int number = i + 1;
      String buttonNo = number + "";
      if(prize == i){
        but = new JButton();
        but.addActionListener(this);
        but.setText(buttonNo);
        panel.add(but);
      }
      else if  (bomb1 == i ){
        bombBut1 = new Bomb(buttonNo);
        bombBut1.addActionListener(this);
        panel.add(bombBut1);
      }
      else if  (bomb2 == i ){
        bombBut2 = new Bomb(buttonNo);
        bombBut2.addActionListener(this);
        panel.add(bombBut2);
      }
      else{
        JButton temp = new JButton();
        panel.add(temp);
        temp.setText(buttonNo);
        temp.addActionListener(this);
      }
    }
    
    // Guess counter
    label = new JLabel("You guessed " + count + " times\r\n");
    label.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(label, BorderLayout.SOUTH);
    
  }
  
  /**
     * This method controls what will happen when an action is perceived
     * @param event any action perceived
     */
    @Override
  public void actionPerformed( ActionEvent e)
  {
    if ( e.getSource() != but && e.getSource() != bombBut1 && e.getSource() != bombBut2){
      count ++;
      ((JButton) e.getSource()).setEnabled(false);
      label.setText("You guessed " + count + " times");
    }
    
    else if ( e.getSource() == but){ 
      count ++;
      label.setText("You got it in " + count + " attempts!" );
      but.setText("★");
      but.setFont(new Font("Serif", Font.PLAIN, 50));
      but.setForeground(Color.ORANGE);
      but.setOpaque(true);
      for(int i = 0; i < (ROW*COLUMN); i++){
        if(panel.getComponent(i) != but){
          ((JButton) panel.getComponent(i)).setEnabled(false);
          bombBut1.setFinished(true);
          bombBut2.setFinished(true);
          but.setEnabled(true);
          bombBut1.setEnabled(true);
          bombBut2.setEnabled(true); 
          bombBut1.setOpaque(true);
          bombBut2.setOpaque(true);
        }
      }
    }
    else {
      count ++;
      label.setText("Sorry! You are blown up at attempt " + count + "!");
      for(int i = 0; i < (ROW*COLUMN); i++){
        if(panel.getComponent(i) != but || panel.getComponent(i) != bombBut1 || panel.getComponent(i) != bombBut2){
          ((JButton) panel.getComponent(i)).setEnabled(false);
          bombBut1.setFinished(true);
          bombBut2.setFinished(true);
          but.setText("★");
          but.setFont(new Font("Serif", Font.PLAIN, 50));
          but.setForeground(Color.ORANGE);
          but.setOpaque(true);
          but.setEnabled(true);
          bombBut1.setEnabled(true);
          bombBut2.setEnabled(true); 
          bombBut1.setOpaque(true);
          bombBut2.setOpaque(true);
        }
      }
    }
  }
  
    
}