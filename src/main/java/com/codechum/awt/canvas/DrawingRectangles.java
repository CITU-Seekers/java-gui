/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.canvas;

/**
 *
 * @author alysa
 */

import com.codechum.awt.canvas.MyCanvasRectangle;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class DrawingRectangles extends java.awt.Frame {

    /**
     * Creates new form AWTCanvas1
     */
    public DrawingRectangles() {
        initComponents();
        setTitle("Drawing Squares");
        Canvas canvas = new MyCanvasRectangle();
        canvas.setName("mainCanvas");
        
        add(canvas);
        
        // Set the size of the frame
        setSize(300, 150);
        
        // Make the frame visible
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
                new DrawingRectangles().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}