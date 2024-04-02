package com.codechum;

import com.codechum.awt.colorClass.ColoredShapesAndWords;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class ColoredShapesAndWordsTest {
    @Tested ColoredShapesAndWords codeChumActivity;
    @Mocked Graphics g;
    
    // Description: Should override the frame's paint method and display a gray rectangle.
    @Test
    public void shouldDrawGreyRectangle() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setColor(Color.gray);
            g.fillRect(anyInt, anyInt, withNotEqual(0), withNotEqual(0));
        }};
    }
    
    // Description: Should override the frame's paint method and display a yellow oval.
    @Test
    public void shouldDrawYellowOval() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.yellow);
            g.drawOval(anyInt, anyInt, withNotEqual(0), withNotEqual(0));
        }};
    }
    
    // Description: Should override the frame's paint method and display a red text.
    @Test
    public void shouldDrawRedText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.red);
            g.drawString(anyString, anyInt, anyInt); 
        }};
    }
}
