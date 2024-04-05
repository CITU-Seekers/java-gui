/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.awt.panels;

/**
 *
 * @author AzalithenPC
 */
import java.awt.*;

public class ArrangingPanels extends Frame {
    public ArrangingPanels() {
        this.setTitle("Frame with Position");
        this.setSize(300,220);

        Panel mainPanel = new ArrangingPanelsBorderedPanel();
        mainPanel.setName("mainPanel");

        mainPanel.setForeground(Color.black);

        Panel subPanelA = new Panel();
        Panel subPanelB = new Panel();

        subPanelA.setName("subPanelA");
        subPanelB.setName("subPanelB");

        subPanelA.setForeground(Color.blue);
        subPanelA.setSize(50,50);
        subPanelB.setForeground(Color.red);
        subPanelB.setSize(50,50);

        subPanelA.setLocation(10, 10);
        subPanelB.setLocation(200, 90);

        mainPanel.setLayout(null);
        
        mainPanel.add(subPanelA);
        mainPanel.add(subPanelB);

        // These panels are added just to properly display the main panel.
        this.add(new Panel(), BorderLayout.NORTH);
        this.add(new Panel(), BorderLayout.EAST);
        this.add(new Panel(), BorderLayout.SOUTH);
        this.add(new Panel(), BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArrangingPanels().setVisible(true);
            }
        });
    }
}

// Custom class to add border to AWT Panels
class ArrangingPanelsBorderedPanel extends Panel {
  
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