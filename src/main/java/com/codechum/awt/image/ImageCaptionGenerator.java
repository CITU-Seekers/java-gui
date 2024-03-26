package com.codechum.awt.image;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class ImageCaptionGenerator extends Frame {
    public ImageCaptionGenerator() {    
        // creating a frame
        ImageCaptionGeneratorCanvas mainCanvas = new ImageCaptionGeneratorCanvas();
        mainCanvas.setName("mainCanvas");
        setTitle("Image");
        
        TextField captionTextField = new TextField();
        captionTextField.setName("captionTextField");
        Button addCaptionButton = new Button("Add Caption");
        addCaptionButton.setName("addCaptionButton");
        addCaptionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                mainCanvas.addCaption(captionTextField.getText());
                // Graphics g = mainCanvas.getGraphics();
                // g.setColor(Color.red);
                
                // System.out.println(g);
        
                // // set Font
                // g.setFont(new Font("Bold", 1, 60));
        
                // // draw a string
                // g.drawString(captionTextField.getText(), 50, 50);
            }
        });
        
        JPanel pnlActions = new JPanel();
        pnlActions.setLayout(new BoxLayout(pnlActions, BoxLayout.PAGE_AXIS));
        pnlActions.add(captionTextField);
        pnlActions.add(addCaptionButton);
        
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        captionTextField.setFont(font1);
        addCaptionButton.setFont(font1);
        captionTextField.setPreferredSize(new Dimension(120, 30));
        
        // adding canvas to frame   
        add(mainCanvas, BorderLayout.CENTER);
        add(pnlActions, BorderLayout.SOUTH);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
    
        // setting layout, size and visibility of frame 
        setSize(500, 500);    
        setVisible(true);    
    }    
  
    // main method  
    public static void main(String args[])    
    {    
        new ImageCaptionGenerator();    
    }    
}
