package com.example.hw4_chat_server;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GUI {
    public static void main(String[] args) {
        // -------------------------- Colors: --------------------------
        // Thanks to https://www.rapidtables.com/web/color/RGB_Color.html
        Color dark_grey = new Color(32, 32, 32);
        Color med_grey = new Color(64, 64, 64);
        Color baby_blue = new Color(102, 178, 255);
        Color minty_white = new Color(242, 255, 249);
        Color minty = new Color(204, 255, 229);

        // ---------------------- Input Section: -----------------------
        JPanel input_field = new JPanel();
        input_field.setLayout(new BorderLayout());
        input_field.setBackground(minty);

        JTextField text_box = new JTextField();
        text_box.setToolTipText("Don't think too much just type!");
        text_box.setForeground(med_grey);

        JButton send_button = new JButton("Send");
        send_button.setBackground(baby_blue);
        send_button.setForeground(dark_grey);
        send_button.setOpaque(true);
        send_button.setBorderPainted(false);

        JLabel instructions = new JLabel();
        instructions.setText("<html><p><i>" +
                             "> First message? Type your username first." +
                             "</i></p></html>");
        instructions.setForeground(Color.gray);

        input_field.add(text_box);
        input_field.add(send_button, BorderLayout.EAST);
        input_field.add(instructions, BorderLayout.NORTH);

        // ------------------------ Main Panel: -------------------------
        JFrame jf = new JFrame("Chat Void");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel text_msg = new JLabel();
        text_msg.setForeground(med_grey);

        JPanel chat_box = new JPanel();
        chat_box.setLayout(new BorderLayout());
        chat_box.setBackground(minty_white);
        chat_box.add(input_field, BorderLayout.SOUTH);
    }
}