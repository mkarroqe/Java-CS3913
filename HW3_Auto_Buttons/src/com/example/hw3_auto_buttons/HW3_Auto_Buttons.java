//  Mary Karroqe
//  HW 3: Auto Buttons

//  Modify your button GUI program so that the buttons change color,
//  automatically, about every second unless they've been pressed.

package com.example.hw3_auto_buttons;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class betterButton extends JButton {
    boolean pressed;
    buttonThread bThread;

    betterButton() {
        pressed = false;
        bThread = new buttonThread(this);
    }
}

class buttonThread extends Thread {
    betterButton button;

    buttonThread(betterButton _button) {
        button = _button;
    }

    @Override
    public void run() {
        while(true) {
            try {
                sleep(1000);
                if(!button.pressed) {
                    // change color
                    Color new_color = HW3_Auto_Buttons.getRandColor();
                    button.setBackground(new_color);
                }
                else { // button is pressed
                    sleep(1000); // we sleep and don't change color
                }
            }
            catch(Exception e) {}
        }
    }
}

// Modified from HW 2:
public class HW3_Auto_Buttons {
    static JButton button;
    static final int GRID_ROWS = 4;
    static final int GRID_COLS = 2;
    static final int num_buttons = GRID_ROWS * GRID_COLS;

    // Edited to return Color instead of int[]
    static Color getRandColor() {
        Random rand = new Random();
        int[] rands = new int[3];

        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        rands[0] = r;
        rands[1] = g;
        rands[2] = b;

        Color new_color = new Color(r, g, b);
        return new_color;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("They don't need u anymore");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 400);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        betterButton[] buttons = new betterButton[num_buttons];

        for (int i = 0; i < num_buttons; i++) {
            // Get Random Color
            Color curr_color = getRandColor();

            // Create button
            betterButton curr_button = new betterButton();
            curr_button.setBackground(curr_color);
            curr_button.setOpaque(true);
            curr_button.setBorderPainted(false);

            curr_button.addActionListener(new ButtonListener(buttons, curr_button));
            curr_button.bThread.start();                // Added this to start button thread

            buttons[i] = curr_button;
            jp.add(buttons[i]);
        }

        jf.add(jp);
        jf.setVisible(true);
    }
}

class ButtonListener implements ActionListener {
    betterButton[] buttons;
    betterButton curr_button;

    ButtonListener(betterButton[] _buttons, betterButton _curr_button) {
        buttons = _buttons;
        curr_button = _curr_button;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        curr_button.pressed = true;

        for(int i=0; i < buttons.length; i++) {
            if(buttons[i] != curr_button){
                Color new_color = HW3_Auto_Buttons.getRandColor();
                buttons[i].setBackground(new_color);
            }
        }
    }
}
