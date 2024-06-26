/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.scrollBar;

/**
 *
 * @author alysa
 */
public class TemperatureConverter extends java.awt.Frame {

    /**
     * Creates new form AWTScrollbar1
     */
    public TemperatureConverter() {
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
        TemperatureRange = new java.awt.Scrollbar();
        label2 = new java.awt.Label();

        setTitle("Temperature Converter");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label1.setName("fahrenheitTempLabel"); // NOI18N
        label1.setText("32.00 °F");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        TemperatureRange.setName("temperatureRangeBar"); // NOI18N
        TemperatureRange.setOrientation(java.awt.Scrollbar.HORIZONTAL);
        TemperatureRange.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                TemperatureRangeAdjustmentValueChanged(evt);
            }
        });
        add(TemperatureRange, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 270, 30));

        label2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label2.setName("celsiusTempLabel"); // NOI18N
        label2.setText("0.00 °C");
        add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void TemperatureRangeAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_TemperatureRangeAdjustmentValueChanged
        // TODO add your handling code here:

        double scrollbarValue = (double) TemperatureRange.getValue();
        label1.setText(String.format("%.2f °F", (scrollbarValue * 1.8) + 32));
        label2.setText(String.format("%.2f °C", scrollbarValue));
        
        
    }//GEN-LAST:event_TemperatureRangeAdjustmentValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Scrollbar TemperatureRange;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
}
