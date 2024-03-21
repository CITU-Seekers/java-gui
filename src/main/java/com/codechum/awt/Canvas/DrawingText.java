package com.codechum.awt.Canvas;        

import java.awt.*;
import javax.swing.*;

public class DrawingText extends JFrame {
    public DrawingText() {    
        setTitle("Text");

        // creating the canvas
        Canvas cnvMain = new DrawingTextMyCanvas();
        cnvMain.setName("mainCanvas");

        // adding canvas to frame   
        add(cnvMain);    

        setLayout(null);    
        setSize(300, 230);    
        setVisible(true);    
    }    
  
    // main method  
    public static void main(String args[]) {    
        new DrawingText();    
    }    
}
