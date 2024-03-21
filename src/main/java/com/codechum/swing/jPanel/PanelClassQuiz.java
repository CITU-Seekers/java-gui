package com.codechum.swing.jPanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class PanelClassQuiz extends JFrame {
    public PanelClassQuiz() {
        this.setTitle("Panel Quiz");
        this.setSize(300,220);

        JPanel mainPanel = new JPanel();
        mainPanel.setName("mainPanel");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);

        JPanel subPanelA = new JPanel();
        JPanel subPanelB = new JPanel();

        subPanelA.setName("subPanelA");
        subPanelB.setName("subPanelB");

        Border blueline = BorderFactory.createLineBorder(Color.blue);
        Border redline = BorderFactory.createLineBorder(Color.red);

        subPanelA.setBorder(blueline);
        subPanelA.setSize(50,50);
        subPanelB.setBorder(redline);
        subPanelB.setSize(50,50);

        subPanelA.setLocation(10, 10);
        subPanelB.setLocation(200, 90);

        mainPanel.setLayout(null);
        
        mainPanel.add(subPanelA);
        mainPanel.add(subPanelB);

        // These panels are added just to properly display the main panel.
        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelClassQuiz().setVisible(true);
            }
        });
    }
}