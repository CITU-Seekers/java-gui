package com.codechum;

import com.codechum.awt.colorClass.ColoredTexts;
import java.awt.*;

import mockit.*;
import org.testng.annotations.Test;

public class ColoredTextsTest {
    @Tested ColoredTexts codeChumActivity;
    @Mocked Graphics g;
    
    @Test
    public void shouldDrawRedText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder(){{
            g.setColor(Color.red);
            g.drawString("Red", anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldDrawGreenText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.green);
            g.drawString("Green", anyInt, anyInt); 
        }};
    }
    
    @Test
    public void shouldDrawBlueText() {
        codeChumActivity.paint(g);
        
        new VerificationsInOrder() {{
            g.setColor(Color.blue);
            g.drawString("Blue", anyInt, anyInt); 
        }};
    }
}
