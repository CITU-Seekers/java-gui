package com.codechum.awt.ColorClass; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ColoredShapesAndWords extends java.awt.Frame {

    /**
     * Creates new form AWTGraphics1
     */
    public ColoredShapesAndWords() {
        initComponents();
        setSize(200, 400);
        setTitle("Graphics");
    }
    
    @Override
    public void paint(Graphics g){
        Font font = new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20);
        g.setFont(font);
        
        g.setColor(Color.GRAY);
        g.fillRect(100, 100, 150, 100);
        g.setColor(Color.yellow);
        g.drawOval(105, 105, 140, 90);
        g.setColor(Color.RED);
        g.drawString("Sample", 140, 160);
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
                new ColoredShapesAndWords().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
