package com.codechum;

import com.codechum.awt.colorClass.ShapeInAShape;
import java.awt.Color;
import java.awt.Graphics;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

public class ShapeInAShapeTest {
    @Tested
    ShapeInAShape codeChumActivity;
    @Mocked Graphics g;

    // Description: Should override the frame's paint method and display a red circle centered at (200, 200) with a radius of 150 containing a white rectangle with a width of 150 and a height of 50.
    @Test
    public void shouldOverridePaintMethod() {
        codeChumActivity.paint(g);

        new Verifications() {
            {
                int centerX = 200;
                int centerY = 200;
                int circleRadius = 150;
                int rectangleWidth = 150;
                int rectangleHeight = 50;

                // Draw red circle
                g.setColor(Color.RED);
                g.fillOval(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius);

                // Draw white rectangle inside the circle
                g.setColor(Color.WHITE);
                g.fillRect(centerX - rectangleWidth / 2, centerY - rectangleHeight / 2, rectangleWidth, rectangleHeight);
            }
        };
    }
}
               