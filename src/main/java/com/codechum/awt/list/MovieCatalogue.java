/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.list;

/**
 *
 * @author alysa
 */
public class MovieCatalogue extends java.awt.Frame {

    /**
     * Creates new form AWTList1
     */
    public MovieCatalogue() {
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
        ActionList = new java.awt.List();
        ComedyList = new java.awt.List();
        HorrorList = new java.awt.List();
        label1 = new java.awt.Label();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        button4 = new java.awt.Button();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();

        setTitle("Movie Catalogue");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField1.setName("movieTextField"); // NOI18N
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });
        add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 180, 20));

        ActionList.setName("actionList"); // NOI18N
        add(ActionList, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 160, 170));

        ComedyList.setName("comedyList"); // NOI18N
        add(ComedyList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 160, 170));

        HorrorList.setName("horrorList"); // NOI18N
        add(HorrorList, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 160, 170));

        label1.setText("Movie Title:");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, 20));

        button1.setLabel("Add Action");
        button1.setName("addActionButton"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, -1));

        button2.setLabel("Add Comedy");
        button2.setName("addComedyButton"); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        button3.setLabel("Add Horror");
        button3.setName("addHorrorButton"); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        button4.setLabel("Remove");
        button4.setName("removeButton"); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        label2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label2.setText("Action");
        add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        label3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label3.setText("Comedy");
        add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        label4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label4.setText("Horror");
        add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        String movieName = textField1.getText();
    
        HorrorList.add(movieName);
    }//GEN-LAST:event_button3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String movieName = textField1.getText();
    
    // Add the movie name to ComedyList
        ComedyList.add(movieName);
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        String movieName = textField1.getText();
        
        ActionList.add(movieName);
    }//GEN-LAST:event_button1ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:

        // Remove the selected movie from the list
        if (ActionList.getSelectedIndex() != -1) {
        ActionList.remove(ActionList.getSelectedIndex());
        }

        if (ComedyList.getSelectedIndex() != -1) {
            ComedyList.remove(ComedyList.getSelectedIndex());
        }

        if (HorrorList.getSelectedIndex() != -1) {
            HorrorList.remove(HorrorList.getSelectedIndex());
        }
        
    }//GEN-LAST:event_button4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovieCatalogue().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.List ActionList;
    private java.awt.List ComedyList;
    private java.awt.List HorrorList;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
