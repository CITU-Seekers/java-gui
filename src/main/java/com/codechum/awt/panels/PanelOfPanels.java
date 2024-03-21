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

public class PanelOfPanels extends Frame {
    public PanelOfPanels() {
        this.setTitle("Panels");
        this.setSize(300,220);

        // Using BorderedPanel is optional since adding borders/backgrounds is not required.
        Panel mainPanel = new BorderedPanel2();
        mainPanel.setName("mainPanel");

        Panel subPanelA = new BorderedPanel2();
        Panel subPanelB = new BorderedPanel2();

        subPanelA.setName("subPanelA");
        subPanelB.setName("subPanelB");

        subPanelA.setForeground(Color.blue);
        subPanelB.setForeground(Color.red);

        subPanelA.setPreferredSize(new Dimension(100, 100));
        subPanelB.setPreferredSize(new Dimension(100, 100));
        
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
        new PanelOfPanels().setVisible(true);
    }
}

// Custom class to add border to AWT Panels
class BorderedPanel extends Panel {
  
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