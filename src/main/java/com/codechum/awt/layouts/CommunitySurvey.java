/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.layouts;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author alysa
 */
public class CommunitySurvey extends java.awt.Frame {

    /**
     * Creates new form CommunitySurvey
     */
    
    private Dialog dlgNotice;
    public CommunitySurvey() {
        initComponents();
        panel1.setVisible(false);
        
        dlgNotice = new Dialog(this, "Status", false);
        dlgNotice.setSize(200, 200);
        dlgNotice.setLayout(new BorderLayout());
        dlgNotice.setName("dialogNotice");
        Label messageLabel = new Label();
        messageLabel.setName("messageLabel");
        dlgNotice.add(messageLabel, BorderLayout.CENTER);

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
        checkbox1 = new java.awt.Checkbox();
        checkbox2 = new java.awt.Checkbox();
        label1 = new java.awt.Label();
        panel1 = new java.awt.Panel();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        textField4 = new java.awt.TextField();
        button1 = new java.awt.Button();

        setTitle("Community Survey");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField1.setName("nameTextField"); // NOI18N
        add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 190, 30));

        checkbox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        checkbox1.setLabel("Yes");
        checkbox1.setName("yesCheckBox"); // NOI18N
        checkbox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkbox1ItemStateChanged(evt);
            }
        });
        add(checkbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        checkbox2.setLabel("No");
        checkbox2.setName("noCheckBox"); // NOI18N
        checkbox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkbox2ItemStateChanged(evt);
            }
        });
        add(checkbox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setText("Do you own a pet?");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        panel1.setName("panel1"); // NOI18N
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField2.setName("dogNameTextField"); // NOI18N
        textField2.setText("Dog Name");
        panel1.add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 230, 30));

        textField3.setName("dogBreedTextField"); // NOI18N
        textField3.setText("Dog Breed");
        panel1.add(textField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 230, 30));

        textField4.setName("dogAgeTextField"); // NOI18N
        textField4.setText("Age");
        panel1.add(textField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 230, 30));

        add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 280, 170));

        button1.setLabel("Submit");
        button1.setName("submitButton"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox1ItemStateChanged
        // TODO add your handling code here:
        
        if(checkbox1.getState() == true){
            panel1.setVisible(true);
        }
        else{
            panel1.setVisible(false);
        }
    }//GEN-LAST:event_checkbox1ItemStateChanged

    private void checkbox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox2ItemStateChanged
        // TODO add your handling code here:
        if(checkbox2.getState() == true){
            panel1.setVisible(false);
        }
    }//GEN-LAST:event_checkbox2ItemStateChanged

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        Label messageLabel = (Label) dlgNotice.getComponent(0);
        messageLabel.setText("Response has been recorded!");
        dlgNotice.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CommunitySurvey().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Checkbox checkbox1;
    private java.awt.Checkbox checkbox2;
    private java.awt.Label label1;
    private java.awt.Panel panel1;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
    private java.awt.TextField textField4;
    // End of variables declaration//GEN-END:variables
}
