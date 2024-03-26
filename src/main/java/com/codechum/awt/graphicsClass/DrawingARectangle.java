package com.codechum.awt.graphicsClass;         

import java.awt.Color;
import java.awt.Graphics;

public class DrawingARectangle extends java.awt.Frame {

    /**
     * Creates new form AWTGraphics1
     */
    public DrawingARectangle() {
        initComponents();
        setSize(200, 400);
        setTitle("Graphics");
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawRect(100, 100, 150, 100);
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
                new DrawingARectangle().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}