package com.codechum.swing.jPanel;

import javax.swing.*;
import java.awt.*;

public class PanelPositioning extends JFrame {
    public PanelPositioning() {
        this.setTitle("Panel Positioning");
        this.setSize(600, 500);
        this.setLayout(null);

        BorderedPanel4 panelA = new BorderedPanel4();
        BorderedPanel4 panelB = new BorderedPanel4();

        panelA.setName("panelA");
        panelB.setName("panelB");

        panelA.setForeground(Color.blue);
        panelB.setForeground(Color.red);
        
        panelA.setBounds(155, 55, 100, 100); // Width and height needed for layout
        panelB.setBounds(200, 35, 100, 100);
        
        this.add(panelA);
        this.add(panelB);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new PanelPositioning().setVisible(true);
        });
    }
}

class BorderedPanel4 extends JPanel {
  
    private boolean drawBorder = true;
    private int borderOffset = 2;
  
    public void setBorderVisible(boolean b) {
        if (b != drawBorder) {
            drawBorder = b;
            repaint();
        }
    }
  
    public boolean isBorderVisible() {
        return drawBorder;
    }
  
    public void setBorderOffset(int i) {
        borderOffset = i;
        repaint();
    }
  
    public int getBorderOffset() {
        return borderOffset;
    }
  
    protected Rectangle getBorderBounds() {
        int x = borderOffset;
        int y = borderOffset;
        int width = getWidth() - borderOffset * 2;
        int height = getHeight() - borderOffset * 2;
        return new Rectangle(x, y, width, height);
    }
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        if (drawBorder) {
            g.setColor(getForeground());
            Rectangle border = getBorderBounds();
            g.drawRect(border.x, border.y, border.width, border.height);
        }
    }
}
