package com.codechum.awt.windows;

import java.awt.*;

public class MasterOfWindows extends Frame {

    public MasterOfWindows() {
        // Setting properties for the main frame
        setTitle("Master of Windows");
        setSize(1000, 500);
        setLocation(500, 200);
        setName("mainFrame");

        // Creating and setting properties for the first window
        Window window1 = new Window(this);
        window1.setSize(200, 200);
        window1.setLocation(400, 250);
        window1.setBackground(Color.RED); // Set a distinct color
        window1.setName("window1");

        // Creating and setting properties for the second window
        Window window2 = new Window(this);
        window2.setSize(200, 200);
        window2.setLocation(500, 350);
        window2.setBackground(Color.GREEN); // Set a distinct color
        window2.setName("window2");

        // Creating and setting properties for the third window
        Window window3 = new Window(this);
        window3.setSize(200, 200);
        window3.setLocation(600, 450);
        window3.setBackground(Color.BLUE); // Set a distinct color
        window3.setName("window3");

        // Adding a window listener for the main frame
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        // Making the child windows visible
        window1.setVisible(true);
        window2.setVisible(true);
        window3.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new MasterOfWindows().setVisible(true);
        });
    }
}
