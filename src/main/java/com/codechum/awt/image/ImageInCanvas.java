package com.codechum.awt.image;

import java.awt.*;

public class ImageInCanvas extends Frame {
    public ImageInCanvas() {    
        // creating a frame
        ImageInCanvasMyCanvas cnvMain = new ImageInCanvasMyCanvas();
        cnvMain.setName("mainCanvas");
        setTitle("Image");
        
        // adding canvas to frame   
        add(cnvMain, BorderLayout.CENTER);
    
        // setting layout, size and visibility of frame 
        setSize(520, 520);    
        setVisible(true);
    }    
  
    // main method  
    public static void main(String args[]) {  
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new ImageInCanvas();    
        }
    });
    }    
}
