package com.codechum;

import com.codechum.awt.canvas.DrawingText;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;


public class DrawingTextTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Canvas canvas;
   
    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingText.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should override frame's paint method of MyCanvas class to draw text.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);

        // Test these
        // g.setColor(Color.yellow);
        // g.setFont(new Font("Bold", 1, 20));

        Graphics graphics = canvasMain.getGraphics();
        canvasMain.paint(graphics);

        assertEquals(graphics.getColor(), Color.yellow, "Color should be yellow");
        assertEquals(graphics.getFont(), new Font("Bold", 1, 20), "Font should be Bold, 1, 20");
    }
    
    // Description: Should have a canvas named `mainCanvas`.
     @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(canvasMain, "No mainCanvas found");
    }
}
