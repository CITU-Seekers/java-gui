package com.codechum.swing.jButton;

import javax.swing.*;

public class ButtonClassQuiz extends javax.swing.JFrame {
    public ButtonClassQuiz() {
        this.setTitle("Case Switch");
        
        JButton switchVowelCaseButton = new JButton("Vowel");
        JButton switchConsonantCaseButton = new JButton("Consonant");
        JButton switchAllCaseButton = new JButton("All");
        JLabel textLabel = new JLabel("I love programming!");
        
        textLabel.setBounds(100,100,160,30);
        textLabel.setName("textLabel");

        switchVowelCaseButton.setBounds(30,150,80,30);
        switchConsonantCaseButton.setBounds(120,150,110,30);
        switchAllCaseButton.setBounds(240,150,80,30);
        switchVowelCaseButton.addActionListener(e -> {
            String txt = "";

            for(int i = 0; i < textLabel.getText().length(); i++) {
                char ch = textLabel.getText().charAt(i);

                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                    if(Character.isUpperCase(ch)) {
                        txt += Character.toLowerCase(ch);
                    }
                    else {
                        txt += Character.toUpperCase(ch);
                    }
                }
                else {
                    txt += ch;
                } 
            }

            textLabel.setText(txt);
        });
        switchConsonantCaseButton.addActionListener(e -> {
            String txt = "";

            for(int i = 0; i < textLabel.getText().length(); i++) {
                char ch = textLabel.getText().charAt(i);

                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                    txt += ch;
                }
                else {
                    if(Character.isUpperCase(ch)) {
                        txt += Character.toLowerCase(ch);
                    }
                    else {
                        txt += Character.toUpperCase(ch);
                    }
                } 
            }

            textLabel.setText(txt);
        });
        switchAllCaseButton.addActionListener(e -> {
            String txt = "";

            for(int i = 0; i < textLabel.getText().length(); i++) {
                char ch = textLabel.getText().charAt(i);

                if(Character.isUpperCase(ch)) {
                    txt += Character.toLowerCase(ch);
                }
                else {
                    txt += Character.toUpperCase(ch);
                }
            }

            textLabel.setText(txt);
        });
        switchVowelCaseButton.setName("switchVowelCaseButton");
        switchConsonantCaseButton.setName("switchConsonantCaseButton");
        switchAllCaseButton.setName("switchAllCaseButton");

        this.add(textLabel);
        this.add(switchVowelCaseButton);
        this.add(switchConsonantCaseButton);
        this.add(switchAllCaseButton);
        this.setSize(500,300);    
        this.setLayout(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ButtonClassQuiz().setVisible(true);
            }
        });
    }                
}
