/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author alysa
 */
public class LoginForm extends java.awt.Frame {

    /**
     * Creates new form LoginForm
     */
    
    private Dialog dlgNotice;
    
    public LoginForm() {
        initComponents();
        initDialog();
    }
    
    private void initDialog() {
        dlgNotice = new Dialog(this, "Status", false);
        dlgNotice.setSize(200, 200);
        dlgNotice.setLayout(new BorderLayout());
        dlgNotice.setName("dialogStatus");
        Label lblMessage = new Label();
        lblMessage.setName("lblMessage");
        dlgNotice.add(lblMessage, BorderLayout.CENTER);

        // Add a WindowListener to handle the dialog closing event
        dlgNotice.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dlgNotice.setVisible(false); // Hide the dialog on close
            }
        });

        dlgNotice.setVisible(false);
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
        label2 = new java.awt.Label();
        textField2 = new java.awt.TextField();
        button1 = new java.awt.Button();

        setName("LoginForm"); // NOI18N
        setTitle("Login Form");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField1.setName("userNameTextField"); // NOI18N
        add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 180, 30));

        label1.setText("Username");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        label2.setName(""); // NOI18N
        label2.setText("Password");
        add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        textField2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textField2.setEchoChar('*');
        textField2.setName("passwordTextField"); // NOI18N
        add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 180, 30));

        button1.setLabel("Login");
        button1.setName("loginButton"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

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
        // check if username and password is correct where username = admin and password = admin
        String username = textField1.getText();
        String password = textField2.getText();

        if (username.equals("admin") && password.equals("admin")) {
            // Show the dialog
            Label lblMessage = (Label) dlgNotice.getComponent(0);
            lblMessage.setText("Login Successful!");
            dlgNotice.setVisible(true);
        } else {
            // Show the dialog
            Label lblMessage = (Label) dlgNotice.getComponent(0);
            lblMessage.setText("Login Failed!");
            dlgNotice.setVisible(true);
        }
        
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    // End of variables declaration//GEN-END:variables
}
