package com.codechum.awt.Canvas;

 

import java.awt.*;
import javax.swing.*;

// THIS SHOULD BE NAMED MY CANVAS
public class DrawingAnOvalMyCanvas extends Canvas {
    public DrawingAnOvalMyCanvas() {    
        setBackground (Color.black);    
        setSize(300, 200);    
    }    
  
    public void paint(Graphics g){   
        g.setColor(Color.yellow);
        g.fillOval(75, 75, 150, 75);
    }    
}