/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.scrollBar;

/**
 *
 * @author alysa
 */
public class DatePicker extends java.awt.Frame {

    /**
     * Creates new form AWTScrollbar2
     */
    public DatePicker() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollbar1 = new java.awt.Scrollbar();
        scrollbar2 = new java.awt.Scrollbar();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();

        setTitle("Date Picker");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollbar1.setMaximum(13);
        scrollbar1.setMinimum(1);
        scrollbar1.setName("monthScrollBar"); // NOI18N
        scrollbar1.setOrientation(java.awt.Scrollbar.HORIZONTAL);
        scrollbar1.setVisibleAmount(1);
        scrollbar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollbar1AdjustmentValueChanged(evt);
            }
        });
        add(scrollbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 320, 30));

        scrollbar2.setMaximum(32);
        scrollbar2.setMinimum(1);
        scrollbar2.setName("dateScrollBar"); // NOI18N
        scrollbar2.setOrientation(java.awt.Scrollbar.HORIZONTAL);
        scrollbar2.setVisibleAmount(1);
        scrollbar2.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollbar2AdjustmentValueChanged(evt);
            }
        });
        add(scrollbar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 320, 30));

        label1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label1.setName("monthLabel"); // NOI18N
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 170, 50));

        label2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label2.setName("dateLabel"); // NOI18N
        add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 100, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void scrollbar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollbar1AdjustmentValueChanged
        // TODO add your handling code here:

        // Get the value of the scrollbar and set the label to the corresponding month
        int month = scrollbar1.getValue();
        switch (month) {
            case 1:
                label1.setText("January");
                break;
            case 2:
                label1.setText("February");
                break;
            case 3:
                label1.setText("March");
                break;
            case 4:
                label1.setText("April");
                break;
            case 5:
                label1.setText("May");
                break;
            case 6:
                label1.setText("June");
                break;
            case 7:
                label1.setText("July");
                break;
            case 8:
                label1.setText("August");
                break;
            case 9:
                label1.setText("September");
                break;
            case 10:
                label1.setText("October");
                break;
            case 11:
                label1.setText("November");
                break;
            case 12:
                label1.setText("December");
                break;
            default:
                label1.setText("Invalid Month");
                break;
        }
        
    }//GEN-LAST:event_scrollbar1AdjustmentValueChanged

    private void scrollbar2AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollbar2AdjustmentValueChanged
        // TODO add your handling code here:
        // Get the value of the scrollbar and set the label to the corresponding date
        int date = scrollbar2.getValue();
        label2.setText(String.valueOf(date));
    }//GEN-LAST:event_scrollbar2AdjustmentValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatePicker().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Scrollbar scrollbar1;
    private java.awt.Scrollbar scrollbar2;
    // End of variables declaration//GEN-END:variables
}
