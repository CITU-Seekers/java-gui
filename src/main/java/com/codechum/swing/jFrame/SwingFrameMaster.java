package com.codechum.swing.jFrame;

import javax.swing.JFrame;

public class SwingFrameMaster extends JFrame {
    public SwingFrameMaster() {
        this.setTitle("Frame Master");
        this.setName("frmMaster");
        this.setSize(700, 500);
        this.setLocation(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Properly handle window closing
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SwingFrameMaster().setVisible(true);
            }
        });
    }
}
