/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.frame;

/**
 *
 * @author AzalithenPC
 */
import java.awt.*;

public class MakingAFrame extends Frame {
    public MakingAFrame() {
        this.setTitle("Frame");
        this.setName("frmPractice");
        this.setSize(500,400);
    }

    public static void MakingAFrame(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakingAFrame().setVisible(true);
            }
        });
    }
}