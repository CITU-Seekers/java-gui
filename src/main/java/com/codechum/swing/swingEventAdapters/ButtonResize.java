package com.codechum.swing.swingEventAdapters;

import java.awt.event.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.*;

public class ButtonResize extends JFrame {
    private JLabel helperLabel;
    private JRadioButton smallRadioButton, mediumRadioButton, largeRadioButton;
    private ButtonGroup btnGrp;
    private JButton mainButton;
    private JPanel pnlMain;

    public ButtonResize() {
        setTitle("Event Handling");

        helperLabel = new JLabel("");
        smallRadioButton = new JRadioButton("Small");
        mediumRadioButton = new JRadioButton("Medium");
        largeRadioButton = new JRadioButton("Large");
        btnGrp = new ButtonGroup();
        mainButton = new JButton("Button");

        helperLabel.setName("helperLabel");
        smallRadioButton.setName("smallRadioButton");
        mediumRadioButton.setName("mediumRadioButton");
        largeRadioButton.setName("largeRadioButton");
        mainButton.setName("mainButton");

        btnGrp.add(smallRadioButton);
        btnGrp.add(mediumRadioButton);
        btnGrp.add(largeRadioButton);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        pnlMain.setBorder(blackline);
        pnlMain.add(mainButton);
        pnlMain.setPreferredSize(new Dimension( 450, 200));
        pnlMain.setSize(new Dimension(500, 200));
        mainButton.setBounds((pnlMain.getWidth() / 2) - (mainButton.getWidth()/2), (pnlMain.getHeight() / 2) - (mainButton.getHeight()/2), 80, 30);
        smallRadioButton.setSelected(true);

        JPanel pnlRadioButtons = new JPanel();
        pnlRadioButtons.add(smallRadioButton);
        pnlRadioButtons.add(mediumRadioButton);
        pnlRadioButtons.add(largeRadioButton);

        JPanel pnlMessage = new JPanel();
        pnlMessage.add(helperLabel);

        smallRadioButton.addMouseListener(
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

        mediumRadioButton.addMouseListener(
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

        largeRadioButton.addMouseListener(
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
