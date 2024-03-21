package com.codechum.swing.swingEventListeners;

import java.awt.event.*;

import javax.swing.*;

public class TextLengthChecker extends JFrame{
    public TextLengthChecker() {
        this.setTitle("Text Length Checker");
        
        JTextField textField = new JTextField();
        JLabel messageLabel = new JLabel("The text is short.");

        textField.setName("textField");
        messageLabel.setName("messageLabel");

        textField.setBounds(100,80,120,30);
        messageLabel.setBounds(110,100,180,80);

        textField.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    if(textField.getText().length() > 10) {
                        messageLabel.setText("The text is long.");
                    }
                    else {
                        messageLabel.setText("The text is short.");
                    }
                }
            }
        );

        this.add(textField);
        this.add(messageLabel);
        this.setSize(350,300);    
        this.setLayout(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextLengthChecker().setVisible(true);
            }
        });
    }        
}
