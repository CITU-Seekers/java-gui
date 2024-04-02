package com.codechum;

import com.codechum.awt.colorClass.ColoredTexts;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class ColoredTextsTest {
    @Tested ColoredTexts codeChumActivity;
    @Mocked Graphics g;
    
    // Description: Should override the frame's paint method and display "Red" text in red color.
    @Test
    public void shouldDrawRedText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setColor(Color.red);
            g.drawString("Red", anyInt, anyInt);
        }};
    }
    
    // Description: Should override the frame's paint method and display "Green" text in green color.
    @Test
    public void shouldDrawGreenText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.green);
            g.drawString("Green", anyInt, anyInt); 
        }};
    }
    
    // Description: Should override the frame's paint method and display "Blue" text in blue color.
    @Test
    public void shouldDrawBlueText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.blue);
            g.drawString("Blue", anyInt, anyInt); 
        }};
    }
}
