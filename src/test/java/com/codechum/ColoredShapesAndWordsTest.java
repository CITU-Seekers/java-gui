package com.codechum;

import com.codechum.awt.colorClass.ColoredShapesAndWords;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class ColoredShapesAndWordsTest {
    @Tested ColoredShapesAndWords codeChumActivity;
    @Mocked Graphics g;
    
    @Test
    public void shouldDrawGreyRectangle() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setColor(Color.gray);
            g.fillRect(anyInt, anyInt, withNotEqual(0), withNotEqual(0));
        }};
    }
    
    @Test
    public void shouldDrawYellowOval() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.yellow);
            g.drawOval(anyInt, anyInt, withNotEqual(0), withNotEqual(0));
        }};
    }
    
    @Test
    public void shouldDrawRedText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.red);
            g.drawString(anyString, anyInt, anyInt); 
        }};
    }
}
