package com.codechum.swing.swingEventClasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseEventClass extends JFrame  implements ActionListener {

    public MouseEventClass() {
        initComponents();
        
        JButton[] buttons = new JButton[20];
        
        panel1.setPreferredSize(new Dimension(300, 300));
        
        for (int i = 0; i < 20; i++) {
            buttons[i] = new JButton((i+1)+"");
            buttons[i].setName("button"+(i+1));
            buttons[i].addActionListener(this);
            buttons[i].setPreferredSize(new Dimension(200, 200));
        }
        
        panel1.setLayout(new GridLayout(4, 5));
        for (JButton btn: buttons) {
            panel1.add(btn);
        }
        
        setTitle("Action Events");
        this.setSize(400, 400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new javax.swing.JPanel();
        textLabel = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        textLabel.setName("textLabel"); // NOI18N
        panel2.add(textLabel);

        add(panel2, java.awt.BorderLayout.NORTH);
        add(panel1, java.awt.BorderLayout.CENTER);

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
                new MouseEventClass().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        
        textLabel.setText(btn.getText());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel textLabel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    // End of variables declaration//GEN-END:variables
}
