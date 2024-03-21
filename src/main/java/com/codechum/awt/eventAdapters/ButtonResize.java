package com.codechum.awt.eventAdapters;

import java.awt.event.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.*;

public class ButtonResize extends Frame {
    private Label helperLabel;
    private Checkbox smallCheckBox, mediumCheckBox, largeCheckBox;
    private ButtonGroup btnGrp;
    private Button mainButton;
    private JPanel pnlMain;

    public ButtonResize() {
        setTitle("Event Handling");

        CheckboxGroup group = new CheckboxGroup();
        helperLabel = new Label("");
        smallCheckBox = new Checkbox("Small", group, true);
        mediumCheckBox = new Checkbox("Medium", group, false);
        largeCheckBox = new Checkbox("Large", group, false);
        btnGrp = new ButtonGroup();
        mainButton = new Button("Button");

        helperLabel.setName("helperLabel");
        smallCheckBox.setName("smallCheckBox");
        mediumCheckBox.setName("mediumCheckBox");
        largeCheckBox.setName("largeCheckBox");
        mainButton.setName("mainButton");

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        pnlMain.setBorder(blackline);
        pnlMain.add(mainButton);
        pnlMain.setPreferredSize(new Dimension( 450, 200));
        pnlMain.setSize(new Dimension(500, 200));
        mainButton.setBounds((pnlMain.getWidth() / 2) - (mainButton.getWidth()/2), (pnlMain.getHeight() / 2) - (mainButton.getHeight()/2), 80, 30);

        JPanel pnlRadioButtons = new JPanel();
        pnlRadioButtons.add(smallCheckBox);
        pnlRadioButtons.add(mediumCheckBox);
        pnlRadioButtons.add(largeCheckBox);

        JPanel pnlMessage = new JPanel();
        pnlMessage.add(helperLabel);

        smallCheckBox.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    mainButton.setBounds((pnlMain.getWidth() / 2) - (mainButton.getWidth()/2), (pnlMain.getHeight() / 2) - (mainButton.getHeight()/2), 80, 30);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("Set the button's size to small");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("");
                }
            }
        );

        mediumCheckBox.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    mainButton.setBounds((pnlMain.getWidth() / 2) - (mainButton.getWidth()/2), (pnlMain.getHeight() / 2) - (mainButton.getHeight()/2), 160, 60);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("Set the button's size to medium");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("");
                }
            }
        );

        largeCheckBox.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    mainButton.setBounds((pnlMain.getWidth() / 2) - (mainButton.getWidth()/2), (pnlMain.getHeight() / 2) - (mainButton.getHeight()/2), 240, 90);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("Set the button's size to large");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    helperLabel.setText("");
                }
            }
        );
        
        add(pnlMessage, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlRadioButtons, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ButtonResize().setVisible(true);
            }
        });
    }
}
