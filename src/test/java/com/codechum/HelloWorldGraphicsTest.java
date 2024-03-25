package com.codechum;

import com.codechum.awt.graphicsClass.HelloWorldGraphics;
import java.awt.Graphics;

import mockit.*;
import org.testng.annotations.Test;

public class HelloWorldGraphicsTest {
    @Tested HelloWorldGraphics codeChumActivity;
    @Mocked Graphics g;
    
    @Test
    public void shouldOverridePaintMethod() {
        codeChumActivity.paint(g);
        
       new Verifications(){{
           g.drawString("Hello World", anyInt, anyInt);
       }};
    }
}
