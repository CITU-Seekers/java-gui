package com.codechum.awt.canvas;

import java.awt.*;
import javax.swing.*;

public class DrawingAnOvalMyCanvas extends Canvas {
    public DrawingAnOvalMyCanvas() {    
        setBackground(Color.black);    
        setSize(300, 200);    
    }    
  
    public void paint(Graphics g) {   
        g.setColor(Color.yellow);
        g.fillOval(75, 75, 150, 75);
    }    
}
