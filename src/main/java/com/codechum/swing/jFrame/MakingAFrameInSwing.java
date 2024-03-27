/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.swing.jFrame;

/**
 *
 * @author AzalithenPC
 */
import java.awt.*;

public class MakingAFrameInSwing extends Frame {
    public MakingAFrameInSwing() {
        this.setTitle("Frame");
        this.setName("frmPractice");
        this.setSize(500,400);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakingAFrameInSwing().setVisible(true);
            }
        });
    }
}