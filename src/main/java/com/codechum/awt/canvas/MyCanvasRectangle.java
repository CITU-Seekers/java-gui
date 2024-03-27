/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alysa
 */
public class MyCanvasRectangle extends Canvas {
    public MyCanvasRectangle() {    
        setBackground (Color.black);    
        setSize(300, 150);    
    }    
  
    public void paint(Graphics g){   
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw three green rectangles
        g.setColor(Color.GREEN);
        g.fillRect(50, 50, 50, 50);
        g.fillRect(120, 50, 50, 50);
        g.fillRect(190, 50, 50, 50);
    }    
}
