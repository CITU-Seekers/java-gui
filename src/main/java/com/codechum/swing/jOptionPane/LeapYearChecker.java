package com.codechum.swing.jOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LeapYearChecker extends JFrame{
    private JPanel panel1;
    private JTextField yearTextField;
    private JButton checkYearButton;

    public LeapYearChecker() {
        this.setTitle("Leap Year Checker");
        this.yearTextField = new JTextField();
        this.yearTextField.setName("yearTextField");
        this.checkYearButton = new JButton();
        this.checkYearButton.setName("checkYearButton");
        this.checkYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkYear();
            }
        });
        panel1 = new JPanel();
        panel1.add(yearTextField);
        panel1.add(checkYearButton);
        setContentPane(panel1);
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setName("leapYearChecker");
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                LeapYearChecker app = new LeapYearChecker();
            }
        });
    }

    public void checkYear() {
        int year = 0;
        try {
            year = Integer.parseInt(yearTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input a valid number.");
            return;
        }

        boolean is_ly = false;
        if (year % 400 == 0) {
            is_ly = true;
        }
        else if (year % 100 == 0) {
            is_ly = false;
        }
        else if (year % 4 == 0) {
            is_ly = true;
        }

        JOptionPane.showMessageDialog(null, ( is_ly ? "Leap year" : "Not a leap year"));
    }
}