package com.codechum.awt.canvas; 

import java.awt.*;
import javax.swing.*;

public class DrawingTextMyCanvas extends Canvas {
    public DrawingTextMyCanvas() {    
        setBackground (Color.black);    
        setSize(300, 200);    
    }    
  
    public void paint(Graphics g){   
        g.setColor(Color.yellow);
        g.setFont(new Font("Bold", 1, 20));
        g.drawString("Sample", 100, 100);
    }    
}