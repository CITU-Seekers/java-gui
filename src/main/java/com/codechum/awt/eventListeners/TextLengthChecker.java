package com.codechum.awt.eventListeners;

import java.awt.*;
import java.awt.event.*;

public class TextLengthChecker extends Frame{
    public TextLengthChecker() {
        this.setTitle("Text Length Checker");
        
        TextField textField = new TextField();
        Label messageLabel = new Label("The text is short.");

        textField.setName("textField");
        messageLabel.setName("messageLabel");

        textField.setBounds(80,80,100,30);
        messageLabel.setBounds(90,120,120,80);

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
        this.setSize(300,250);    
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
