package com.codechum.awt.canvas; 

import java.awt.*;
import javax.swing.*;

public class DrawingAnOval extends Frame {
    public DrawingAnOval() {    
        setTitle("Text");

        // creating the canvas
        Canvas cnvMain = new DrawingAnOvalMyCanvas();
        cnvMain.setName("mainCanvas");

        // adding canvas to frame   
        add(cnvMain);    

        setLayout(null);    
        setSize(300, 230);    
        setVisible(true);    
    }    
  
    // main method  
    public static void main(String args[]) {    
     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawingAnOval();
            }
        });
    }    
}
