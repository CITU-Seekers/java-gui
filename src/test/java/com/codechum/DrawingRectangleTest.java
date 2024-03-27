package com.codechum;

import com.codechum.awt.canvas.DrawingRectangles;
import com.codechum.awt.canvas.MyCanvasRectangle;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import mockit.Tested;
import mockit.Injectable;
import mockit.Verifications;

public class DrawingRectangleTest {
    @Tested DrawingRectangles codeChumActivity;
    @Tested MyCanvasRectangle canvas;
    @Injectable Graphics g;
    
    @Test
    public void shouldDrawGreenSquares() {
        canvas.paint(g);

        new Verifications() {
            {
                g.setColor(Color.RED);
                g.fillRect(0, 0, 300, 150);

                // Draw three green rectangles
                g.setColor(Color.GREEN);
                g.fillRect(50, 50, 50, 50);
                g.fillRect(120, 50, 50, 50);
                g.fillRect(190, 50, 50, 50);
            }
        };
    }
    
    @Test
    public void shouldHaveCanvas() {
        Canvas cnvMain = (Canvas) TestUtils.getChildNamed(codeChumActivity, "canvasMain");
        assertNotNull(cnvMain, "No canvasMain found");
    }
}
