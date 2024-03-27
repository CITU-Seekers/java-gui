package com.codechum;

import com.codechum.awt.canvas.DrawingText;
import com.codechum.awt.canvas.DrawingTextMyCanvas;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import mockit.Tested;
import mockit.Injectable;
import mockit.Verifications;

public class DrawingTextTest {
    @Tested DrawingText codeChumActivity;
    @Tested DrawingTextMyCanvas canvas;
    @Injectable Graphics g;
    
    // Description: Should override frame's paint method of MyCanvas class to draw text.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        canvas.paint(g);
        
        new Verifications(){{
            g.drawString(anyString, anyInt, anyInt);
        }};
    }
    
    // Description: Should have a canvas named `canvasMain`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.getChildNamed(codeChumActivity, "canvasMain");
        assertNotNull(canvasMain, "No canvasMain found");
    }
}
