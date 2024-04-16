/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customized;

/**
 *
 * @author Sheinn
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryManagement extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JFrame mainFrame;
    private JFrame secondFrame;
    private DefaultListModel listModel;
    private JList itemList;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton logoutButton;

    private ArrayList<Item> inventory;
    private ArrayList<String> registeredUsernames;
    private ArrayList<String> registeredPasswords;

    public InventoryManagement() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainFrame = this;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(mainFrame, "Login successful!");
                    Inventory();
                    mainFrame.setVisible(false);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Incorrect password or username. Please try again.");
                    clearFields();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registration();
            }
        });

        usernameLabel.setName("usernameLabel");
        usernameField.setName("usernameField");
        passwordLabel.setName("passwordLabel");
        passwordField.setName("passwordField");
        loginButton.setName("loginButton");
        registerButton.setName("registerButton");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);

        // Initialize registered users
        registeredUsernames = new ArrayList<>();
        registeredPasswords = new ArrayList<>();
        // Default user
        registeredUsernames.add("a");
        registeredPasswords.add("a");
    }

    private boolean isValidLogin(String username, String password) {
        return registeredUsernames.contains(username) && registeredPasswords.contains(password);
    }

    private void Registration() {
        JFrame registrationFrame = new JFrame("Registration");
        registrationFrame.setSize(300, 150);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("New Username:");
        JTextField newUsernameField = new JTextField();
        JLabel passwordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());

                if (registeredUsernames.contains(newUsername)) {
                    JOptionPane.showMessageDialog(registrationFrame, "Username already exists. Please choose a different one.");
                } else {
                    // Add new user
                    registeredUsernames.add(newUsername);
                    registeredPasswords.add(newPassword);
                    JOptionPane.showMessageDialog(registrationFrame, "Registration successful!");
                    registrationFrame.dispose();
                }
            }
        });

        usernameLabel.setName("usernameLabel");
        newUsernameField.setName("newUsernameField");
        passwordLabel.setName("passwordLabel");
        newPasswordField.setName("newPasswordField");
        registerButton.setName("registerButton");

        panel.add(usernameLabel);
        panel.add(newUsernameField);
        panel.add(passwordLabel);
        panel.add(newPasswordField);
        panel.add(registerButton);

        registrationFrame.add(panel);
        registrationFrame.setVisible(true);
    }

        private void Inventory() {
        secondFrame = new JFrame("Inventory Management");
        secondFrame.setSize(400, 300);
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        secondFrame.setLocationRelativeTo(null); 

        inventory = new ArrayList();
        listModel = new DefaultListModel();
        itemList = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        
        itemNameField = new JTextField();
        itemQuantityField = new JTextField();
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        logoutButton = new JButton("Logout");
              
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Item Quantity:"));
        inputPanel.add(itemQuantityField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        addButton.setName("addButton");
        updateButton.setName("updateButton");
        deleteButton.setName("deleteButton");
        logoutButton.setName("logoutButton");
        itemList.setName("itemList");
        itemNameField.setName("itemNameField");
        itemQuantityField.setName("itemQuantityField");

        secondFrame.add(scrollPane, BorderLayout.CENTER);
        secondFrame.add(inputPanel, BorderLayout.NORTH);
        secondFrame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameField.getText();
                int quantity = Integer.parseInt(itemQuantityField.getText());
                inventory.add(new Item(itemName, quantity));
                updateInventoryList();
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String itemName = itemNameField.getText();
                    int quantity = Integer.parseInt(itemQuantityField.getText());
                    inventory.set(selectedIndex, new Item(itemName, quantity));
                    updateInventoryList();
                    clearFields();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    inventory.remove(selectedIndex);
                    updateInventoryList();
                    clearFields();
                }
            }
        });

        itemList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Item selectedItem = inventory.get(selectedIndex);
                    itemNameField.setText(selectedItem.getName());
                    itemQuantityField.setText(String.valueOf(selectedItem.getQuantity()));
                }
            }
        });
        

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondFrame.setVisible(false); 
                mainFrame.setVisible(true);
            }
        });

        secondFrame.setVisible(true);
    }
        
    private void updateInventoryList() {
        listModel.clear();
        for (Item item : inventory) {
            listModel.addElement(item.getName() + " - Quantity: " + item.getQuantity());
        }
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        itemNameField.setText("");
        itemQuantityField.setText("");
        itemList.clearSelection();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagement();
            }
        });
    }

    // Item class
    private class Item {
        private String name;
        private int quantity;

        public Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}


