package com.codechum.swing.jPasswordField;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JFrame{
    public LoginDialog() {
        setTitle("GridLayout");

        JPanel pnlForm = new JPanel(new GridLayout(2,2, 5, 5));
        pnlForm.setName("pnlForm");

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JTextField usernameTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        lblUsername.setName("lblUsername");
        lblPassword.setName("lblPassword");
        usernameTextField.setName("usernameTextField");
        passwordTextField.setName("passwordTextField");
        loginButton.setName("loginButton");

        lblUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblUsername.setMaximumSize(new Dimension(120, 30));
        lblPassword.setMaximumSize(new Dimension(120, 30));
        usernameTextField.setMaximumSize(new Dimension(120, 30));
        passwordTextField.setMaximumSize(new Dimension(120, 30));

        pnlForm.add(lblUsername);
        pnlForm.add(usernameTextField);
        pnlForm.add(lblPassword);
        pnlForm.add(passwordTextField);
        pnlForm.setMaximumSize(new Dimension(200, 100));
        
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, 20))); 
        add(pnlForm);
        add(Box.createRigidArea(new Dimension(0, 20))); 
        add(loginButton);
        add(Box.createRigidArea(new Dimension(0, 20))); 

        loginButton.addActionListener(e -> {
            String password = new String(passwordTextField.getPassword());

            if (password.equals(usernameTextField.getText())) JOptionPane.showMessageDialog(null, "Success!");
            else JOptionPane.showMessageDialog(null, "Failed!");
        });

        this.pack();
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new LoginDialog().setVisible(true);
        });
    } 
}
