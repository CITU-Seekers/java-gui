/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.panels;

import java.awt.*;

/**
 *
 * @author AzalithenPC
 */
public class PanelMaster extends Frame {
    public PanelMaster() {
        this.setTitle("Panel Master");
        this.setSize(600, 500);
        

        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();

        panel1.setName("panelA");
        panel2.setName("panelB");
        panel3.setName("panelC");

        panel1.setForeground(Color.blue);
        panel1.setSize(50,50);
        
        panel2.setForeground(Color.red);
        panel2.setSize(50,50);
        
        panel3.setForeground(Color.black);
        panel3.setSize(50,50);

        panel1.setLocation(10, 10);
        panel2.setLocation(200, 90);
        panel3.setLocation(500, 170);
      
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.EAST);
        this.add(panel3, BorderLayout.SOUTH);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelMaster().setVisible(true);
            }
        });
    }
}