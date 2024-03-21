package com.codechum.swing.swingLayouts;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator extends JFrame implements ActionListener {
    private JLabel resultLabel;
    private String operator;
    private double firstNumber, secondNumber;

    public Calculator() {
        setTitle("Border Layout");

        JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, addButton, subtractButton, divideButton, multiplyButton, computeButton, clearButton;
        JPanel buttonsPanel = new JPanel();
        resultLabel = new JLabel("");

        // create number buttons
        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
 
        // equals/compute button
        computeButton = new JButton("=");
 
        // operator buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        divideButton = new JButton("/");
        multiplyButton = new JButton("*");
        clearButton = new JButton("C");

        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        divideButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        clearButton.addActionListener(this);
        computeButton.addActionListener(this);

        button0.setName("button0");
        button1.setName("button1");
        button2.setName("button2");
        button3.setName("button3");
        button4.setName("button4");
        button5.setName("button5");
        button6.setName("button6");
        button7.setName("button7");
        button8.setName("button8");
        button9.setName("button9");
        addButton.setName("addButton");
        subtractButton.setName("subtractButton");
        divideButton.setName("divideButton");
        multiplyButton.setName("multiplyButton");
        clearButton.setName("clearButton");
        computeButton.setName("computeButton");
        resultLabel.setName("resultLabel");

        buttonsPanel.add(button7);
        buttonsPanel.add(button8);
        buttonsPanel.add(button9);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(button4);
        buttonsPanel.add(button5);
        buttonsPanel.add(button6);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(button0);
        buttonsPanel.add(addButton);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(computeButton);

        buttonsPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonsPanel.setName("buttonsPanel");

        resultLabel.setPreferredSize(new Dimension(350, 30));
        resultLabel.setBounds(120, 20, 350, 300);

        this.add(resultLabel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(new Panel(), BorderLayout.WEST);
        this.add(new Panel(), BorderLayout.EAST);
        this.add(new Panel(), BorderLayout.SOUTH);

        this.pack();
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if(button.getText().equals("+") ||
        button.getText().equals("-") ||
        button.getText().equals("/") ||
        button.getText().equals("*")) {
            firstNumber = Double.parseDouble(resultLabel.getText());
            operator = button.getText();
            resultLabel.setText(resultLabel.getText() + button.getText());
        } else if (button.getText().equals("C")) {
            resultLabel.setText("");
        } else if (button.getText().equals("=")) {
            String firstNumberString = Double.toString(firstNumber).substring(0, Double.toString(firstNumber).length() - 2);
            String secondNumberString = resultLabel.getText().substring(firstNumberString.length() + 1, resultLabel.getText().length());
            secondNumber = Double.parseDouble(secondNumberString);
            double result = 0;

            if (operator.equals("+")) {
                result = firstNumber + secondNumber;
            } else if (operator.equals("-")) {
                result = firstNumber - secondNumber;
            } else if (operator.equals("*")) {
                result = firstNumber * secondNumber;
            } else {
                result = firstNumber / secondNumber;
            }

            String resultString = Double.toString(result);

            resultLabel.setText(resultString.substring(0, resultString.length()-2));
        } else {
            resultLabel.setText(resultLabel.getText() + button.getText());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    } 
}
