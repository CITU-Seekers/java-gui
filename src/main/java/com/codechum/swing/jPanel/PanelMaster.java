/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codechum.swing.jPanel;

import javax.swing.*;
import java.awt.*;

public class PanelMaster extends JFrame {
    public PanelMaster() {
        setTitle("Panel Master");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new BorderedPanel();
        JPanel panel2 = new BorderedPanel();
        JPanel panel3 = new BorderedPanel();

        panel1.setName("panel1");
        panel2.setName("panel2");
        panel3.setName("panel3");

        panel1.setForeground(Color.blue);
        panel1.setPreferredSize(new Dimension(50, 50));
        
        panel2.setForeground(Color.red);
        panel2.setPreferredSize(new Dimension(50, 50));
        
        panel3.setForeground(Color.black);
        panel3.setPreferredSize(new Dimension(50, 50));

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.EAST);
        add(panel3, BorderLayout.SOUTH);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new PanelMaster().setVisible(true);
        });
    }
}

class BorderedPanel extends JPanel {
  
    private boolean drawBorder = true;
    private int borderOffset = 2;
  
    public void setBorderVisible(boolean b) {
        drawBorder = b;
        repaint();
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
        if (drawBorder) {
            g.setColor(getForeground());
            Rectangle border = getBorderBounds();
            g.drawRect(border.x, border.y, border.width, border.height);
        }
    }
}
