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
                sleep(1000); // 1 second

                if(!button.pressed) {
                    // change color
                    int[] randRGB = HW3_Auto_Buttons.getRandRGB();
                    int r = randRGB[0];
                    int g = randRGB[1];
                    int b = randRGB[2];

                    Color new_color = new Color(r, g, b);
                    button.setBackground(new_color);
                }
                else {
                    // keep sleeping a lil longer
                    sleep(1000); // check
                }
            }
            catch(Exception e) {}
        }
    }
}

public class HW3_Auto_Buttons {
    static JButton button;
    static final int GRID_ROWS = 4;
    static final int GRID_COLS = 2;
    static final int num_buttons = GRID_ROWS * GRID_COLS;

    static int[] getRandRGB() {
        Random rand = new Random();
        int[] rands = new int[3];

        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        rands[0] = r;
        rands[1] = g;
        rands[2] = b;

        return rands;
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
            int[] randRGB = getRandRGB();
            int r = randRGB[0];
            int g = randRGB[1];
            int b = randRGB[2];
            Color curr_color = new Color(r, g, b);

            // Create button
            betterButton curr_button = new betterButton();
            curr_button.setBackground(curr_color);
            curr_button.setOpaque(true);
            curr_button.setBorderPainted(false);

            curr_button.addActionListener(new ButtonListener(buttons, curr_button));
            curr_button.bThread.start();

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
