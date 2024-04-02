package com.codechum.awt.eventListeners;

import java.awt.*;
import java.awt.event.*;

public class TextLengthChecker extends Frame{
    public TextLengthChecker() {
        this.setTitle("Text Length Checker");
        
        TextField wordTextField = new TextField();
        Label messageLabel = new Label("The text is short.");

        wordTextField.setName("wordTextField");
        messageLabel.setName("messageLabel");

        wordTextField.setBounds(80,80,100,30);
        messageLabel.setBounds(90,120,120,80);

        wordTextField.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    if(wordTextField.getText().length() > 10) {
                        messageLabel.setText("The text is long.");
                    }
                    else {
                        messageLabel.setText("The text is short.");
                    }
                }
            }
        );


        this.add(wordTextField);
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
