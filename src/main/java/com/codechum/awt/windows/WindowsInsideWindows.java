package com.codechum.awt.windows;

import java.awt.*;

public class WindowsInsideWindows extends Frame {
    public WindowsInsideWindows() {
        Window window1 = new Window(this);

        window1.setSize(300, 250);
        window1.setVisible(true);
        window1.setBackground(Color.RED);
        window1.setName("window1");

        window1.setLocation(300, 150);

        Window window2 = new Window(this);

        window2.setSize(300, 250);
        window2.setVisible(true);
        window2.setBackground(Color.BLUE);
        window2.setName("window2");

        window2.setLocation(600, 150);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        this.setName("mainFrame");
        this.setTitle("Creating JWindows");
        this.setSize(800,350);
        this.setLocation(200, 100);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new WindowsInsideWindows().setVisible(true);
        });
    }
}