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

public class ArrangingFrames extends Frame {
    public ArrangingFrames() {
        this.setTitle("Frame With Position");
        this.setName("frmPractice");
        this.setSize(600,500);
        this.setLocation(750, 300);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArrangingFrames().setVisible(true);
            }
        });
    }
}