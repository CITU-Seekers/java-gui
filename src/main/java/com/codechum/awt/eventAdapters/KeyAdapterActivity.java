/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.eventAdapters;

/**
 *
 * @author alysa
 */
public class KeyAdapterActivity extends java.awt.Frame {

    /**
     * Creates new form AWTEventListener1
     */
    public KeyAdapterActivity() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();

        setTitle("Key Adapter");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField1.setName("inputTextField"); // NOI18N
        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textField1KeyTyped(evt);
            }
        });
        add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, 40));

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setName("statusLabel"); // NOI18N
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 160, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void textField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyPressed
        // TODO add your handling code here:

        label1.setText("Key Pressed: " + evt.getKeyChar());
    }//GEN-LAST:event_textField1KeyPressed

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased
        // TODO add your handling code here:
        label1.setText("Key Released: " + evt.getKeyChar());
    }//GEN-LAST:event_textField1KeyReleased

    private void textField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyTyped
        // TODO add your handling code here:
        label1.setText("Key Typed: " + evt.getKeyChar());
    }//GEN-LAST:event_textField1KeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeyAdapterActivity().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label label1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
