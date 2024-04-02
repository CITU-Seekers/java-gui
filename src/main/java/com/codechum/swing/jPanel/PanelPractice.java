package com.codechum.swing.jPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class PanelPractice extends JFrame {
    public PanelPractice() {
        this.setTitle("Panel Practice");
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
        subPanelA.setPreferredSize(new Dimension(100, 100));
        subPanelB.setBorder(redline);
        subPanelB.setPreferredSize(new Dimension(100, 100));
        
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PanelPractice().setVisible(true);
            }
        });
    }
}