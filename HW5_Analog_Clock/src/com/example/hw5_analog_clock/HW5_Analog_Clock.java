package com.example.hw5_analog_clock;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.net.ssl.*;
import javax.swing.*;
import javax.swing.border.Border;

public class HW5_Analog_Clock {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Tick tock it's a clock");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel analog_panel = new JPanel();
        analog_panel.setLayout(new BorderLayout());
        JLabel analog = new JLabel("Analog clock here");
        analog_panel.add(analog);

        // just for fun + debugging
        JPanel digital_panel = new JPanel();
        digital_panel.setLayout(new BorderLayout());
        JLabel digital = new JLabel("Digital clock here");
        analog_panel.add(digital);

        jf.add(analog, BorderLayout.CENTER);
        jf.add(digital, BorderLayout.SOUTH);
        jf.setVisible(true);
    }
}
