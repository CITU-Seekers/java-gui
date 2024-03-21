/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.windows;

/**
 *
 * @author AzalithenPC
 */
import java.awt.*;

public class PrecisionWindows extends Frame {

    public PrecisionWindows() {
        // Setting properties for the main frame
        setTitle("Precision Windows");
        setSize(700, 500);
        setLocation(300, 200);
        setName("mainFrame");

        // Creating and setting properties for the first window
        Window window1 = new Window(this);
        window1.setSize(100, 100);
        window1.setLocation(450, 300);
        window1.setBackground(Color.RED); // Set a distinct color
        window1.setName("window1");

        // Creating and setting properties for the second window
        Window window2 = new Window(this);
        window2.setSize(100, 100);
        window2.setLocation(550, 400);
        window2.setBackground(Color.GREEN); // Set a distinct color
        window2.setName("window2");

        // Creating and setting properties for the third window
        Window window3 = new Window(this);
        window3.setSize(100, 100);
        window3.setLocation(650, 500);
        window3.setBackground(Color.BLUE); // Set a distinct color
        window3.setName("window3");

        // Creating and setting properties for the fourth window
        Window window4 = new Window(this);
        window4.setSize(100, 100);
        window4.setLocation(750, 600);
        window4.setBackground(Color.YELLOW); // Set a distinct color
        window4.setName("window4");

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
        window4.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new PrecisionWindows().setVisible(true);
        });
    }
}
