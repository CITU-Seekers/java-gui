/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.checkBox;

/**
 *
 * @author AzalithenPC
 */
public class CharacterRemover extends java.awt.Frame {

    /**
     * Creates new form AWTCheckBox1
     */
    public CharacterRemover() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        checkbox1 = new java.awt.Checkbox();
        checkbox2 = new java.awt.Checkbox();
        checkbox3 = new java.awt.Checkbox();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();

        setTitle("Character Remover");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setName("textLabel"); // NOI18N
        label1.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 270, 50));

        checkbox1.setLabel("Vowels");
        checkbox1.setName("vowelCheckbox"); // NOI18N
        add(checkbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        checkbox2.setLabel("Consonants");
        checkbox2.setName("consonantCheckbox"); // NOI18N
        add(checkbox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        checkbox3.setLabel("Numbers");
        checkbox3.setName("numberCheckbox"); // NOI18N
        add(checkbox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        button1.setActionCommand("button1");
        button1.setLabel("Remove");
        button1.setName("removeButton"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));
        button1.getAccessibleContext().setAccessibleName("button1");
        button1.getAccessibleContext().setAccessibleDescription("");

        button2.setLabel("Restore");
        button2.setName("restoreButton"); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        if (checkbox1.getState()) {
            label1.setText(label1.getText().replaceAll("[AEIOUaeiou]", ""));
        }
        if (checkbox2.getState()) {
            label1.setText(label1.getText().replaceAll("[BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz]", ""));
        }
        if (checkbox3.getState()) {
            label1.setText(label1.getText().replaceAll("\\d", ""));
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        label1.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }//GEN-LAST:event_button2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CharacterRemover().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Checkbox checkbox1;
    private java.awt.Checkbox checkbox2;
    private java.awt.Checkbox checkbox3;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
