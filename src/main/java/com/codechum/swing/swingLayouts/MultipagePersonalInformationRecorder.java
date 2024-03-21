package com.codechum.swing.swingLayouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultipagePersonalInformationRecorder extends JFrame implements ActionListener {
    private int page = 1;
    private JButton nextButton = new JButton("Next");
    private JButton prevButton = new JButton("Previous");
    private JPanel cardPanelContainer = new JPanel();
    private CardLayout card = new CardLayout(60, 30);
    private JTextField nameTextField = new JTextField();
    private JTextField ageTextField = new JTextField();
    private JTextArea quoteTextArea = new JTextArea();
    private JLabel nameLabel = new JLabel();
    private JLabel ageLabel = new JLabel();
    private JLabel quoteLabel = new JLabel();

    public MultipagePersonalInformationRecorder() {
        JPanel firstContainer = new JPanel();
        JPanel secondContainer = new JPanel();
        JPanel thirdContainer = new JPanel();
        cardPanelContainer.setName("cardPanel");
        firstContainer.setName("firstPanel");
        secondContainer.setName("secondPanel");
        thirdContainer.setName("thirdPanel");
        nameTextField.setName("nameTextField");
        ageTextField.setName("ageTextField");
        quoteTextArea.setName("quoteTextArea");
        nameLabel.setName("nameLabel");
        ageLabel.setName("ageLabel");
        quoteLabel.setName("quoteLabel");
        
        JLabel name = new JLabel("Enter Name: ");
        JLabel age = new JLabel("Enter Age: ");

        firstContainer.setLayout(new GridLayout(6,2));
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());
        firstContainer.add(name);
        firstContainer.add(nameTextField);
        firstContainer.add(age);
        firstContainer.add(ageTextField);
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());
        firstContainer.add(new JPanel());

        quoteTextArea.setBounds(0, 0, 100, 100);
        secondContainer.setLayout(new BoxLayout(secondContainer, BoxLayout.PAGE_AXIS));
        JPanel sec = new JPanel();
        sec.setLayout(new FlowLayout());
        sec.add(new JLabel("Enter Quote"));
        secondContainer.add(sec);
        secondContainer.add(Box.createRigidArea(new Dimension(0, 20))); 
        secondContainer.add(quoteTextArea);

        thirdContainer.setLayout(new GridLayout(4,2));
        
        thirdContainer.add(new Label("Name: "));
        thirdContainer.add(nameLabel, BorderLayout.WEST);
        thirdContainer.add(new Label("Age: "));
        thirdContainer.add(ageLabel, BorderLayout.WEST);
        thirdContainer.add(new Label("Fav Quote: "));
        thirdContainer.add(quoteLabel, BorderLayout.WEST);
        thirdContainer.add(new JPanel());
        thirdContainer.add(new JPanel());

        cardPanelContainer.setLayout(card);
        cardPanelContainer.add(firstContainer);
        cardPanelContainer.add(secondContainer);
        cardPanelContainer.add(thirdContainer);

        nextButton.setName("nextButton");
        prevButton.setName("prevButton");
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(prevButton);
        btnPanel.add(nextButton);
        prevButton.setVisible(false);

        this.add(cardPanelContainer, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.setSize(300, 300);
        this.setTitle("Info Recorder");
    }

    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton) e.getSource();

        if(btn.getName().equals("nextButton")) {
            page++;

            nameLabel.setText(nameTextField.getText());
            ageLabel.setText(ageTextField.getText());
            quoteLabel.setText(quoteTextArea.getText());
            
            card.next(cardPanelContainer);
            if(page == 3) {
                nextButton.setVisible(false);
            }

            if(!prevButton.isVisible()) {
                prevButton.setVisible(true);
            }
        }
        else {
            page--;

            card.previous(cardPanelContainer);
            if(page == 1) {
                prevButton.setVisible(false);
            }

            if(!nextButton.isVisible()) {
                nextButton.setVisible(true);
            }
        }
    }  

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultipagePersonalInformationRecorder().setVisible(true);
            }
        });
    } 
}
