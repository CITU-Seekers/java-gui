package com.codechum.swing.jFrame;

import javax.swing.JFrame;

public class ArrangingFramesInSwing extends JFrame {
    public ArrangingFramesInSwing() {
        this.setTitle("Frame With Position");
        this.setName("frmPractice");
        this.setSize(600, 500);
        this.setLocation(750, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Properly handle window closing
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArrangingFramesInSwing().setVisible(true);
            }
        });
    }
}
