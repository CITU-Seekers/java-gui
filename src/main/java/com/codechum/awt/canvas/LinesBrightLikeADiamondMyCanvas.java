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
public class LinesBrightLikeADiamondMyCanvas extends Canvas {
    public LinesBrightLikeADiamondMyCanvas() {    
        setBackground (Color.black);    
        setSize(400, 400);    
    }    
  
    public void paint(Graphics g){   
        int width = getWidth();
        int height = getHeight();

        // Calculate the coordinates of the diamond based on the canvas size
        int[] xPoints = {width / 2, width, width / 2, 0};
        int[] yPoints = {0, height / 2, height, height / 2};

        g.setColor(Color.YELLOW);

        // Draw the diamond using lines
        g.drawPolygon(xPoints, yPoints, 4);

        // Fill the diamond with yellow color
        g.fillPolygon(xPoints, yPoints, 4);
    }    
}
