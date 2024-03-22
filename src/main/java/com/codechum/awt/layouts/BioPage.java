package com.codechum.awt.layouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BioPage extends Frame implements ActionListener {
    private int page = 1;
    private Button nextButton = new Button("Next");
    private Button prevButton = new Button("Previous");
    private Panel cardPanelContainer = new Panel();
    private CardLayout card = new CardLayout(60, 30);
    private TextField nameTextField = new TextField();
    private TextField ageTextField = new TextField();
    private TextArea quoteTextArea = new TextArea();
    private Label nameLabel = new Label();
    private Label ageLabel = new Label();
    private Label quoteLabel = new Label();

    public BioPage() {
        Panel firstContainer = new Panel();
        Panel secondContainer = new Panel();
        Panel thirdContainer = new Panel();
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
        
        Label name = new Label("Enter Name: ");
        Label age = new Label("Enter Age: ");

        firstContainer.setLayout(new GridLayout(6,2));
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());
        firstContainer.add(name);
        firstContainer.add(nameTextField);
        firstContainer.add(age);
        firstContainer.add(ageTextField);
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());
        firstContainer.add(new Panel());

        quoteTextArea.setBounds(0, 0, 100, 100);
        secondContainer.setLayout(new BoxLayout(secondContainer, BoxLayout.PAGE_AXIS));
        Panel sec = new Panel();
        sec.setLayout(new FlowLayout());
        sec.add(new Label("Enter Quote"));
        secondContainer.add(sec, BorderLayout.NORTH);
        secondContainer.add(quoteTextArea, BorderLayout.CENTER);

        thirdContainer.setLayout(new GridLayout(4,2));
        
        thirdContainer.add(new Label("Name: "));
        thirdContainer.add(nameLabel, BorderLayout.WEST);
        thirdContainer.add(new Label("Age: "));
        thirdContainer.add(ageLabel, BorderLayout.WEST);
        thirdContainer.add(new Label("Fav Quote: "));
        thirdContainer.add(quoteLabel, BorderLayout.WEST);
        thirdContainer.add(new Panel());
        thirdContainer.add(new Panel());

        cardPanelContainer.setLayout(card);
        cardPanelContainer.add(firstContainer);
        cardPanelContainer.add(secondContainer);
        cardPanelContainer.add(thirdContainer);

        nextButton.setName("nextButton");
        prevButton.setName("prevButton");
        nextButton.addActionListener(this);
        prevButton.addActionListener(this);
        Panel btnPanel = new Panel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(prevButton);
        btnPanel.add(nextButton);
        prevButton.setVisible(false);

        this.add(cardPanelContainer, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.setSize(500, 400);
        this.setTitle("Info Recorder");
    }

    public void actionPerformed(ActionEvent e){
        Button btn = (Button) e.getSource();

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
        
        
        this.validate();
        this.repaint();
    }  

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BioPage().setVisible(true);
            }
        });
    } 
}
