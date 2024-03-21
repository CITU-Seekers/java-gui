/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AzalithenPC
 */
package com.codechum.awt.windows;

import java.awt.*;

public class WindowWithPosition extends Frame {
    public WindowWithPosition() {
        Window mainWindow = new Window(this);
        mainWindow.getParent();

        mainWindow.setSize(400, 400);
        mainWindow.setVisible(true);
        mainWindow.setBackground(Color.RED);
        mainWindow.setName("mainWindow");

        mainWindow.setLocation(300, 150);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        this.setName("mainFrame");
        this.setTitle("Window with Position");
        this.setSize(600,500);
        this.setLocation(200, 100);
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new WindowWithPosition().setVisible(true);
        });
    }
}
