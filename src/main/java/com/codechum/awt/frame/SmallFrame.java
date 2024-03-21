/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.frame;

/**
 *
 * @author AzalithenPC
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author AzalithenPC
 */
import java.awt.*;

public class SmallFrame extends Frame {
    public SmallFrame() {
        this.setTitle("Small Frame");
        this.setName("frmSmall");
        this.setSize(100,200);
        this.setLocation(600, 450);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmallFrame().setVisible(true);
            }
        });
    }
}
