package com.codechum;

import com.codechum.awt.canvas.DrawingAnOval;
import com.codechum.awt.canvas.DrawingAnOvalMyCanvas;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import mockit.Tested;
import mockit.Injectable;
import mockit.Verifications;

public class DrawingAnOvalTest {

    @Tested DrawingAnOval codeChumActivity;
    @Tested DrawingAnOvalMyCanvas canvas;
    @Injectable Graphics g;
    
    @BeforeMethod
    public void setUp() {
        codeChumActivity = new DrawingAnOval();
        canvas = new DrawingAnOvalMyCanvas();
    }

    // Description: Should override frame's paint method of MyCanvas class to draw an oval.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        canvas.paint(g);

        new Verifications() {{
            g.fillOval(anyInt, anyInt, anyInt, anyInt);
        }};
    }
    
    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.getChildNamed(codeChumActivity, "mainCanvas");
        assertNotNull(canvasMain, "No mainCanvas found");
    }
}
