package com.codechum.swing.jOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LeapYearChecker extends JFrame{
    private JPanel panel1;
    private JTextField tfYear;
    private JButton btnCheckYear;

    public LeapYearChecker() {
        this.setTitle("Leap Year Checker");
        this.tfYear = new JTextField();
        this.tfYear.setName("tfYear");
        this.btnCheckYear = new JButton();
        this.btnCheckYear.setName("btnCheckYear");
        this.btnCheckYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkYear();
            }
        });
        panel1 = new JPanel();
        panel1.add(tfYear);
        panel1.add(btnCheckYear);
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
            year = Integer.parseInt(tfYear.getText());
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