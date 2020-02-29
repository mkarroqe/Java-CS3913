/*
    Create a window with 8 buttons organized in a 4x2 grid.  
    Have the background color (see java class Color) of each button start 
    at some random value (See java class Random).  
    When a button is pressed, it should cause every OTHER button 
    to change background color.  
    The button pressed should not change background color at all. 
 */

/**
 *
 * @author mkarroqe
 */

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
        
public class HW2_Buttons {
    static JButton button;
    static final int GRID_ROWS = 4;
    static final int GRID_COLS = 2;
    static final int num_buttons = GRID_ROWS * GRID_COLS;
    
    public static void main(String []args) {
        JFrame jf = new JFrame("Some Wild Colors");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 400);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        JButton[] buttons = new JButton[num_buttons];

        for(int i=0; i < num_buttons; i++) {
            // Get Random Color
            Random rand = new Random();
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color curr_color = new Color(r, g, b);
            
            // Create button
            JButton curr_button = new JButton();
            curr_button.setBackground(curr_color);
            curr_button.setOpaque(true);
            curr_button.setBorderPainted(false);
            
            curr_button.addActionListener(new ButtonListener(buttons, curr_button));
            buttons[i] = curr_button;
            jp.add(buttons[i]);
        }

        jf.add(jp);
        jf.setVisible(true);
    }
}

class ButtonListener implements ActionListener {
    JButton[] buttons;
    JButton curr_button;
    
    ButtonListener(JButton[] _buttons, JButton _curr_button) {
        buttons = _buttons;
        curr_button = _curr_button;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        for(int i=0; i < buttons.length; i++) {
            if(buttons[i] != curr_button){
                Random rand = new Random();
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                Color new_color = new Color(r, g, b);
                
                buttons[i].setBackground(new_color);
          }
        }
    }
}