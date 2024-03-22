package com.codechum.awt.dialog;

import java.awt.*;

public class February29 extends java.awt.Frame {

    /**
     * Creates new form CODECHUMACTIVITY
     */
    public February29() {
        initComponents();
        dlgLeapYear = new Dialog(this, "Message", false);
        dlgLeapYear.setSize(200, 200);
        Label lblMessage = new Label("Leap year");
        lblMessage.setName("leapYearLabel");
        Panel leapYearPanel = new Panel(new BorderLayout());
        leapYearPanel.add(new Panel(), BorderLayout.NORTH);
        leapYearPanel.add(new Panel(), BorderLayout.SOUTH);
        leapYearPanel.add(lblMessage, BorderLayout.CENTER);
        leapYearPanel.add(new Panel(), BorderLayout.EAST);
        leapYearPanel.add(new Panel(), BorderLayout.WEST);
        dlgLeapYear.add(leapYearPanel);
        dlgLeapYear.setName("leapYearDialog");
        dlgLeapYear.setVisible(false);
        
        dlgNotLeapYear = new Dialog(this, "Message", false);
        dlgNotLeapYear.setSize(200, 200);
        Label lblNotMessage = new Label("Not a leap year");
        lblNotMessage.setName("notLeapYearLabel");
        Panel notLeapYearPanel = new Panel(new BorderLayout());
        notLeapYearPanel.add(new Panel(), BorderLayout.NORTH);
        notLeapYearPanel.add(new Panel(), BorderLayout.SOUTH);
        notLeapYearPanel.add(lblNotMessage, BorderLayout.CENTER);
        notLeapYearPanel.add(new Panel(), BorderLayout.EAST);
        notLeapYearPanel.add(new Panel(), BorderLayout.WEST);
        dlgNotLeapYear.add(notLeapYearPanel);
        dlgNotLeapYear.setName("notLeapYearDialog");
        dlgNotLeapYear.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        yearTextField = new java.awt.TextField();
        checkYearButton = new java.awt.Button();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        yearTextField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        yearTextField.setName("yearTextField"); // NOI18N

        checkYearButton.setLabel("checkYearButton");
        checkYearButton.setName("checkYearButton"); // NOI18N
        checkYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(checkYearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(yearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(checkYearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

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
        boolean isLeapYear = false;
        int year = Integer.parseInt(yearTextField.getText());

        if (year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    isLeapYear = true;
                }
                else{
                    isLeapYear = false;
                }
            }
            else{
                isLeapYear = true;
            }
        }
        else{
            isLeapYear = false;
        }

        //JOptionPane j = new JOptionPane();
        if(isLeapYear){
            dlgLeapYear.setVisible(true);
        }
        else{
            dlgNotLeapYear.setVisible(true);
        }
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new February29().setVisible(true);
            }
        });
    }

    
    private java.awt.Dialog dlgLeapYear;
    private java.awt.Dialog dlgNotLeapYear;   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button checkYearButton;
    private java.awt.TextField yearTextField;
    // End of variables declaration//GEN-END:variables
}
