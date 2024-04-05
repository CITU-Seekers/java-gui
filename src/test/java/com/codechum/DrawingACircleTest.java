package com.codechum;

import com.codechum.awt.graphicsClass.DrawingACircle;
import java.awt.Graphics;

import mockit.*;
import org.testng.annotations.Test;

public class DrawingACircleTest {
    @Tested
    DrawingACircle codeChumActivity;
    @Mocked Graphics g;

    // Description: Should override the frame's paint method and display a circle.
    @Test
    public void shouldOverridePaintMethod() {
        codeChumActivity.paint(g);

        new Verifications() {
            {
                g.drawOval(anyInt, anyInt, anyInt, anyInt);
            }
        };
    }
}
