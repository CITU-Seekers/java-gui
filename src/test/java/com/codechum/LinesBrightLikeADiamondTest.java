package com.codechum;

import com.codechum.awt.canvas.LinesBrightLikeADiamond;
import com.codechum.awt.canvas.LinesBrightLikeADiamondMyCanvas;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;

public class LinesBrightLikeADiamondTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Canvas canvas;

    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LinesBrightLikeADiamond.class).start();
        robot().waitForIdle();
    }

    // Description: Should override frame's paint method of MyCanvas class to draw a diamond.
    @Test
    public void shouldDrawDiamond() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);

        Dimension size = canvasMain.getSize();
        
        Graphics graphics = canvasMain.getGraphics();
        canvasMain.paint(graphics);
        assertEquals(graphics.getColor(), Color.yellow, "Color should be yellow");
        // should be 400 x 400
        assertEquals(size.width, 400, "Width should be 400");
        assertEquals(size.height, 400, "Height should be 400");
    }

    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(canvasMain, "No mainCanvas found");
    }
}
