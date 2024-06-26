/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.menu;

import java.awt.Color;

/**
 *
 * @author alysa
 */
public class CheckboxMenuItemFeature extends java.awt.Frame {

    /**
     * Creates new form AWTMenu1
     */
    public CheckboxMenuItemFeature() {
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
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        checkboxMenuItem1 = new java.awt.CheckboxMenuItem();
        menu2 = new java.awt.Menu();
        menuItem2 = new java.awt.MenuItem();
        menuItem3 = new java.awt.MenuItem();
        menuItem4 = new java.awt.MenuItem();

        setName("mainFrame"); // NOI18N
        setTitle("CheckBox Menu ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label1.setName("featureLabel"); // NOI18N
        label1.setText("Feature disabled!");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        menuBar1.setName("menuBar");

        menu1.setLabel("Format");
        menu1.setName("formatMenu");

        checkboxMenuItem1.setLabel("Feature");
        checkboxMenuItem1.setName("featureCheckBoxMenu");
        checkboxMenuItem1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkboxMenuItem1ItemStateChanged(evt);
            }
        });
        checkboxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxMenuItem1ActionPerformed(evt);
            }
        });
        menu1.add(checkboxMenuItem1);

        menu2.setLabel("Colors");
        menu2.setName("colorsMenu");

        menuItem2.setLabel("Red");
        menuItem2.setName("redMenuItem");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        menu2.add(menuItem2);

        menuItem3.setLabel("Green");
        menuItem3.setName("greenMenuItem");
        menuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem3ActionPerformed(evt);
            }
        });
        menu2.add(menuItem3);

        menuItem4.setLabel("Blue");
        menuItem4.setName("blueMenuItem");
        menuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem4ActionPerformed(evt);
            }
        });
        menu2.add(menuItem4);

        menu1.add(menu2);

        menuBar1.add(menu1);

        setMenuBar(menuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void checkboxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(checkboxMenuItem1.getState() == true){
            label1.setText("Feature enabled!");
        }
    }//GEN-LAST:event_checkboxMenuItem1ActionPerformed

    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        // TODO add your handling code here:
        label1.setForeground(Color.red);
    }//GEN-LAST:event_menuItem2ActionPerformed

    private void menuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem3ActionPerformed
        // TODO add your handling code here:
        label1.setForeground(Color.green);
    }//GEN-LAST:event_menuItem3ActionPerformed

    private void menuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem4ActionPerformed
        // TODO add your handling code here:
        label1.setForeground(Color.blue);
    }//GEN-LAST:event_menuItem4ActionPerformed

    private void checkboxMenuItem1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkboxMenuItem1ItemStateChanged
        // TODO add your handling code here:
        if (checkboxMenuItem1.getState()) {
            label1.setText("Feature enabled!");
        } else {
            label1.setText("Feature disabled!");
        }
    }//GEN-LAST:event_checkboxMenuItem1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckboxMenuItemFeature().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.CheckboxMenuItem checkboxMenuItem1;
    private java.awt.Label label1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuItem menuItem2;
    private java.awt.MenuItem menuItem3;
    private java.awt.MenuItem menuItem4;
    // End of variables declaration//GEN-END:variables
}
