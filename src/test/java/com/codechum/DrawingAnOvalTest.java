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
    
    @Test
    public void testPaintMethodOfCanvasClass() {
        canvas.paint(g);
        
        new Verifications(){{
            g.fillOval(anyInt,anyInt, anyInt, anyInt);
        }};
    }
    
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.getChildNamed(codeChumActivity, "canvasMain");
        assertNotNull(canvasMain, "No canvasMain found");
    }
}
