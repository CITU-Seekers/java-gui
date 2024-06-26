/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.colorClass;

/**
 *
 * @author alysa
 */

import java.awt.Graphics;
import java.awt.Color;

public class DrawingARedFilledRectangle extends java.awt.Frame {

    /**
     * Creates new form AWTColorClass1
     */
    public DrawingARedFilledRectangle() {
        initComponents();
        setSize(400, 400);
        setLocationRelativeTo(null);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 100, 250, 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName(""); // NOI18N
        setTitle("Red Rectangle");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawingARedFilledRectangle().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
