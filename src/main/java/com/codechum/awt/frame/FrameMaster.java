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

public class FrameMaster extends Frame {
    public FrameMaster() {
        this.setTitle("Frame Master");
        this.setName("frmMaster");
        this.setSize(700,500);
        this.setLocation(500, 300);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMaster().setVisible(true);
            }
        });
    }
}
