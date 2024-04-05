package com.codechum;

import com.codechum.awt.canvas.LinesBrightLikeADiamond;
import com.codechum.awt.canvas.LinesBrightLikeADiamondMyCanvas;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import mockit.Tested;
import mockit.Injectable;
import mockit.Verifications;

public class LinesBrightLikeADiamondTest {
    @Tested LinesBrightLikeADiamond codeChumActivity;
    @Tested LinesBrightLikeADiamondMyCanvas canvas;
    @Injectable Graphics g;

    // Description: Should override frame's paint method of MyCanvas class to draw a diamond.
    @Test
    public void shouldDrawDiamond() {
        canvas.paint(g);

        new Verifications() {
            {
                canvas.setBackground (Color.black);    
                canvas.setSize(400, 400);
                int width = 400;
                int height = 400;

                // Calculate the coordinates of the diamond based on the canvas size
                int[] xPoints = {width / 2, width, width / 2, 0};
                int[] yPoints = {0, height / 2, height, height / 2};

                g.setColor(Color.YELLOW);

                // Draw the diamond using lines
                g.drawPolygon(xPoints, yPoints, 4);

                // Fill the diamond with yellow color
                g.fillPolygon(xPoints, yPoints, 4);
            }
        };
    }

    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvas() {
        Canvas cnvMain = (Canvas) TestUtils.getChildNamed(codeChumActivity, "mainCanvas");
        assertNotNull(cnvMain, "No mainCanvas found");
    }
}
