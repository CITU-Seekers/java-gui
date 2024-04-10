package com.codechum;

import com.codechum.awt.canvas.DrawingAnOval;
import com.codechum.awt.canvas.DrawingAnOvalMyCanvas;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;


public class DrawingAnOvalTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Canvas canvas;
    
    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingAnOval.class).start();
        robot().waitForIdle();
    }

    // Description: Should override frame's paint method of MyCanvas class to draw an oval.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);

        Graphics graphics = canvasMain.getGraphics();
        canvasMain.paint(graphics);

        assertEquals(graphics.getColor(), Color.yellow, "Color should be yellow");
    }
        
    
    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(canvasMain, "No mainCanvas found");
    }
}
