/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.panels;

import java.awt.*;

/**
 *
 * @author AzalithenPC
 */
public class PanelPositioning extends Frame {
    public PanelPositioning() {
        this.setTitle("Panel Positioning");
        this.setSize(600, 500);
        this.setLayout(null);
        // Using BorderedPanel is optional since adding borders/backgrounds is not required.

        Panel panelA = new BorderedPanel4();
        Panel panelB = new BorderedPanel4();

        panelA.setName("panelA");
        panelB.setName("panelB");

        panelA.setForeground(Color.blue);
        panelB.setForeground(Color.red);
        
        panelA.setLocation(155, 55);
        panelB.setLocation(200, 35);
        
        this.add(panelA);
        this.add(panelB);
    }

    public static void main(String args[]) {
        new PanelPositioning().setVisible(true);
    }
}

class BorderedPanel4 extends Panel {
  
    private boolean drawBorder = true;
    private int borderOffset = 2;
  
    public void setBorderVisible( boolean b ) {
        if ( b != drawBorder ) {
            drawBorder = b;
            repaint();
        }
    }
  
    public boolean isBorderVisible() {
        return drawBorder;
    }
  
    public void setBorderOffset( int i ) {
        borderOffset = i;
        repaint();
    }
  
    public int getBorderOffset() {
        return borderOffset;
    }
  
    protected Rectangle getBorderBounds() {
        int x = borderOffset;
        int y = borderOffset;
        int width = getSize().width - borderOffset * 2;
        int height = getSize().height - borderOffset * 2;
        Rectangle bounds = new Rectangle( x, y, width, height );
        return bounds;
    }
  
    public void update( Graphics g ) {
        paint( g );
    }
  
    public void paint( Graphics g ) {
        g.setColor( getBackground() );
        g.fillRect( 0, 0, getSize().width, getSize().height );
        g.setColor( getForeground() );
        Rectangle border = getBorderBounds();
        g.drawRect( border.x, border.y, border.width, border.height );
    }
}