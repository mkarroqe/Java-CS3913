package com.example.hw5_analog_clock;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.net.ssl.*;
import javax.swing.*;

public class HW5_Analog_Clock {
    // -------------------------- Colors: --------------------------
    // Thanks to https://www.rapidtables.com/web/color/RGB_Color.html
    final static Color baby_blue = new Color(102, 178, 255);
    final static Color minty_white = new Color(242, 255, 249);
    final static Color minty = new Color(204, 255, 229);

    static int sec, min, hr;

    public static void main(String[] args) {
        JFrame jf = new JFrame("Tick tock, it's a clock");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // analog clock
        Analog_Panel analog_panel = new Analog_Panel();
        analog_panel.setLayout(new BorderLayout());
        jf.add(analog_panel, BorderLayout.CENTER);

//        // digital clock for fun + debugging
//        JPanel digital_panel = new JPanel();
//        digital_panel.setLayout(new BorderLayout());
//        getUTC();
//        JLabel digital = new JLabel("<html><br/>" + hr + ":" + min + ":" + sec + "<br/><br/></html>",
//                                    SwingConstants.CENTER);
//        digital_panel.add(digital);
//        jf.add(digital_panel, BorderLayout.SOUTH);

        getUTC();

        new Thread() {
            public void run() {
                while (true){
                    if(sec % 60 == 0) {
                        getUTC();
                    }
                    sec += 1;
                    analog_panel.repaint();
                    try {
                        sleep(1000);
                    }
                    catch(InterruptedException ex) {}
                }
            }
        }.start();

        jf.setVisible(true);
    }

    private static void getUTC() {
        long time = 0;

        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket("nist.time.gov", 443);
            socket.startHandshake();

            PrintStream output = new PrintStream(socket.getOutputStream());
            Scanner input = new Scanner(socket.getInputStream());
            output.print("GET /actualtime.cgi HTTP/1.0\r\nHOST: nist.time.gov\r\n\r\n");

            String time_String = "";
            while (input.hasNext()) {
                time_String = input.nextLine();
                if (time_String.equalsIgnoreCase("stop")) {
                    continue;
                }
            }

            int start = time_String.indexOf("\"");
            int end = time_String.indexOf("\"", start + 1);
            time_String = time_String.substring(start + 1,end);
            time = Long.parseLong(time_String);
        }

        catch (Exception ex) {}

        finally {
            time /= 1000;

            Date date = new Date(time);
            SimpleDateFormat _hr = new SimpleDateFormat("HH");
            SimpleDateFormat _min = new SimpleDateFormat("mm");
            SimpleDateFormat _sec = new SimpleDateFormat("ss");

            hr = Integer.parseInt(_hr.format(date));
            min = Integer.parseInt(_min.format(date));
            sec = Integer.parseInt(_sec.format(date));

            System.out.println(hr + ":" + min + ":" + sec);
        }
    }

}
class Analog_Panel extends JPanel{
    int radius;

    Analog_Panel() {
        super();
        HW5_Analog_Clock.sec = 0;
    }

    Point getLocation(String type) {
        Point pt = new Point();
        double x_base = 0;
        double y_base = 0;

        if (type == "second") {
            x_base = Math.sin(Math.toRadians(HW5_Analog_Clock.sec) * 6);
            y_base = Math.cos(Math.toRadians(HW5_Analog_Clock.sec) * 6);
        }
        else if (type == "minute") {
            x_base = Math.sin(Math.toRadians(HW5_Analog_Clock.min * 6 + HW5_Analog_Clock.sec / 10));
            y_base = Math.cos(Math.toRadians(HW5_Analog_Clock.min * 6 + HW5_Analog_Clock.sec / 10));
        }
        else if (type == "hour") {
            x_base = Math.sin(Math.toRadians((HW5_Analog_Clock.hr % 12) * 30 + HW5_Analog_Clock.min / 2));
            y_base = Math.cos(Math.toRadians((HW5_Analog_Clock.hr % 12) * 30 + HW5_Analog_Clock.min / 2));
        }
        else {
            System.out.println("Wrong time type");
        }

        pt.x = (int)(x_base * radius);
        pt.y = (int)(y_base * radius);
        return pt;
    }

    protected void paintComponent(Graphics g) {
        // ------------------- Background -------------------
        int height = this.getSize().height;
        int width = this.getSize().width;
        g.setColor(HW5_Analog_Clock.minty_white);
        g.fillRect(0, 0, width, height);

        // -------------------- Clock Face -------------------
        radius = height / 3;
        int x_center = width / 2;
        int center_x_pt = x_center - radius;

        int y_center = height / 2;
        int center_y_pt = y_center - radius;

        int size = radius * 2; // used for width and height

        g.setColor(HW5_Analog_Clock.minty);
        g.fillOval(center_x_pt, center_y_pt, size, size);

        // ------------------- Clock Hands -------------------
        // Seconds
        Point pt_sec = getLocation("second");
        g.setColor(Color.ORANGE);
        g.drawLine(x_center, y_center, x_center + pt_sec.x, y_center - pt_sec.y);

        // Minutes
        Point pt_min = getLocation("minute");
        g.setColor(HW5_Analog_Clock.baby_blue);
        g.drawLine(x_center, y_center, x_center + pt_min.x, y_center - pt_min.y);

        // Hour
        Point pt_hr = getLocation("hour");
        g.setColor(HW5_Analog_Clock.baby_blue);
        g.drawLine(x_center, y_center, x_center + pt_hr.x, y_center - pt_hr.y);
    }
}

class Point {
    int x, y;

    Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    Point() {
        this(0,0);
    }
}