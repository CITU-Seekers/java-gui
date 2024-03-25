package com.codechum;

import com.codechum.awt.graphicsClass.DrawingARectangle;
import java.awt.Graphics;

import mockit.*;
import org.testng.annotations.Test;

public class DrawingARectangleTest {
    @Tested DrawingARectangle codeChumActivity;
    @Mocked Graphics g;
    
    @Test
    public void shouldOverridePaintMethod() {
        codeChumActivity.paint(g);
        
       new Verifications(){{
           g.drawRect(anyInt, anyInt, anyInt, anyInt);
       }};
    }
}
