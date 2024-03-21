/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codechum.swing.jButton;

import javax.swing.*;

public class ButtonClassPracticeProblemI extends javax.swing.JFrame {
    public int counter = 0;

    public ButtonClassPracticeProblemI() {
        this.setTitle("Counter");
        
        JButton incrementButton = new JButton("Click");
        JLabel counterLabel = new JLabel(Integer.toString(counter));
        
        counterLabel.setBounds(140,100,80,30);
        counterLabel.setName("counterLabel");

        incrementButton.setBounds(100,150,80,30);
        incrementButton.addActionListener(e -> {
            counter++;
            counterLabel.setText(Integer.toString(counter));
        });
        incrementButton.setName("incrementButton");

        this.add(counterLabel);
        this.add(incrementButton);
        this.setSize(300,300);    
        this.setLayout(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ButtonClassPracticeProblemI().setVisible(true);
            }
        });
    }                
}

