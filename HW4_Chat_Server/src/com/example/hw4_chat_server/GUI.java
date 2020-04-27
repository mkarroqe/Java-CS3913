package com.example.hw4_chat_server;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GUI extends JPanel implements ActionListener {
    // -------------------------- Colors: --------------------------
    // Thanks to https://www.rapidtables.com/web/color/RGB_Color.html
    final static Color dark_grey = new Color(32, 32, 32);
    final static Color med_grey = new Color(64, 64, 64);
    final static Color baby_blue = new Color(102, 178, 255);
    final static Color minty_white = new Color(242, 255, 249);
    final static Color minty = new Color(204, 255, 229);

    // ------------------------ For Layout: -------------------------
    protected JPanel input_field;
    protected JTextField text_box;
    protected JButton send_button;
    protected JLabel instructions;
    protected static JTextArea message_area;
    protected JPanel chat_box;

    protected JPanel user_info;
    protected JTextField text_box2;
    protected JTextField text_box3;
    protected JButton connect_button;

    static String username;
    static int port;
    static PrintStream output;

    public GUI() {
        // ---------------------- Input Section: -----------------------
        input_field = new JPanel();
        input_field.setLayout(new BorderLayout());
        input_field.setBackground(minty);

        text_box = new JTextField(22);
        text_box.setToolTipText("Type here!");
        text_box.setForeground(med_grey);
        text_box.addActionListener(this);

        send_button = new JButton("Send");
        send_button.setBackground(baby_blue);
        send_button.setForeground(dark_grey);
        send_button.setOpaque(true);
        send_button.setBorderPainted(false);
        send_button.addActionListener(evt -> actionSend(evt)); // yay!

        instructions = new JLabel();
        instructions.setText("<html><p><i>" +
                "> Type \"STOP\" to exit." +
                "</i></p></html>");
        instructions.setForeground(Color.gray);

        input_field.add(text_box);
        input_field.add(send_button, BorderLayout.EAST);
        input_field.add(instructions, BorderLayout.NORTH);

        instructions = new JLabel();
        instructions.setText("<html><p><i>" +
                "> Type \"STOP\" to exit." +
                "</i></p></html>");
        instructions.setForeground(Color.gray);

        input_field.add(text_box, BorderLayout.CENTER);
        input_field.add(send_button, BorderLayout.EAST);
        input_field.add(instructions, BorderLayout.NORTH);

        // User Info: -----------------------------
        user_info = new JPanel();
        user_info.setLayout(new BorderLayout());
        user_info.setBackground(minty);

        text_box2 = new JTextField(11);
        text_box2.setText("Username: ");
        text_box2.setForeground(med_grey);
        text_box2.addActionListener(this);

        text_box3 = new JTextField(11);
        text_box3.setText("Port Num: ");
        text_box3.setForeground(med_grey);
        text_box3.addActionListener(this);

        connect_button = new JButton("Connect");
        connect_button.setBackground(med_grey);
        connect_button.setForeground(Color.white);
        connect_button.setOpaque(true);
        connect_button.setBorderPainted(false);
        connect_button.addActionListener(evt -> actionConnect(evt));

        user_info.add(text_box2, BorderLayout.WEST);
        user_info.add(text_box3, BorderLayout.CENTER);
        user_info.add(connect_button, BorderLayout.EAST);

        input_field.add(user_info, BorderLayout.SOUTH);

        // -----------------------------------------

        // --------------------- Output Section: -----------------------
        message_area = new JTextArea(18, 22);
        message_area.setEditable(false);
        message_area.setForeground(med_grey);

        chat_box = new JPanel();
        chat_box.setLayout(new BorderLayout());
        chat_box.setBackground(minty_white);
        chat_box.add(input_field, BorderLayout.SOUTH);
        chat_box.add(message_area, BorderLayout.NORTH);

        add(chat_box);
    }

    public void actionPerformed(ActionEvent evt) {
        String message = text_box.getText();
        output.print(message + "\r\n");
        text_box.setText("");
//        message_area.append(message + "\n");
//        text_box.selectAll();
    }

    public void actionSend(ActionEvent evt) {
        String message = text_box.getText();
        output.print(message + "\r\n");
        text_box.setText("");
//        message_area.append(message + "\n");
//        text_box.selectAll();
    }

    public void actionConnect(ActionEvent evt) {
        username = text_box2.getText();
        port = Integer.parseInt(text_box3.getText());
        output.print(username + "\r\n");
    }

    public static void GUI_setup() {
        // ------------------------ Main Panel: -------------------------
        JFrame jf = new JFrame("Chat Void");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(new GUI());
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        GUI_setup();

        try {
            Socket s = new Socket("localhost", 3000);
            Scanner input = new Scanner(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            String msg = "";
            while (!msg.equalsIgnoreCase("STOP")){
                msg = input.nextLine();
                message_area.setText(message_area.getText() + msg + "\n");
            }
        }
        catch (IOException ex) {}
    }
}
