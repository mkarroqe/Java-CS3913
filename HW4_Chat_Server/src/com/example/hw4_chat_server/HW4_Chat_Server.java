package com.example.hw4_chat_server;

import java.io.*;
import java.net.*;
import java.util.*;

public class HW4_Chat_Server {
    static int port = 5190;

    public static void main(String[] args) {
        ServerSocket socket = null;

        try {
            socket = new ServerSocket(port);

            while(true) {
                Socket client = socket.accept();
                new Connection(client).start();
            }
        }
        catch(IOException ex) {
            System.out.println("Socket unreachable..");
        }
    }
}

class Connection extends Thread{
    static ArrayList<Connection> users = new ArrayList<Connection>();
    Socket client;
    String username;
//    int port;

    Connection(Socket data) { client = data; }

    public void run() {
        try {
            Scanner user_input = new Scanner(client.getInputStream());
            String message = "";
//            port = Integer.parseInt(user_input.nextLine());
            username = user_input.nextLine();
            users.add(this);

            while (!message.equals("STOP")) {
                message = user_input.nextLine();
                for (Connection conn : users) {
                    conn.send(message, username);
                }
            }
            client.close();
        }
        catch(IOException ex) {}
    }

    public void send(String message, String username){
        try {
            PrintStream output = new PrintStream(client.getOutputStream());
//            String output_text = "  " + username + ": " + msg;
            output.println("  " + username + ": " + message);
        }
        catch(IOException ex) {}
    }
}