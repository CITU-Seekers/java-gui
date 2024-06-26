package com.codechum;

import com.codechum.awt.colorClass.DrawingARedFilledRectangle;
import java.awt.Color;
import java.awt.Graphics;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

public class DrawingARedFilledRectangleTest {
    @Tested
    DrawingARedFilledRectangle codeChumActivity;
    @Mocked Graphics g;

    // Description: Should override the frame's paint method and display a red filled rectangle.
    @Test
    public void shouldOverridePaintMethod() {   
        codeChumActivity.paint(g);

        new Verifications() {
            {
                g.setColor(Color.RED);
                g.fillRect(anyInt, anyInt, anyInt, anyInt);
            }
        };
    }
}
      