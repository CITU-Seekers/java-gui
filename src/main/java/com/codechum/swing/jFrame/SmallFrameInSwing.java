package com.codechum.swing.jFrame;

import javax.swing.JFrame;

public class SmallFrameInSwing extends JFrame {
    public SmallFrameInSwing() {
        this.setTitle("Small Frame");
        this.setName("frmSmall");
        this.setSize(100, 200);
        this.setLocation(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing properly
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmallFrameInSwing().setVisible(true);
            }
        });
    }
}
