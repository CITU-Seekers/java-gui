package com.codechum;

import com.codechum.awt.canvas.DrawingRectangles;
import com.codechum.awt.canvas.MyCanvasRectangle;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;

public class DrawingRectangleTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Canvas canvas;

    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingRectangles.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should override frame's paint method of MyCanvas class to draw a red rectangle and three green squares.
    @Test
public void shouldDrawGreenSquares() {
    Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);

    // Create an off-screen BufferedImage
    BufferedImage image = new BufferedImage(canvasMain.getWidth(), canvasMain.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.getGraphics();

    // Call the paint method to draw onto the BufferedImage
    canvasMain.paint(g);

    // Now we can check specific pixels
    assertPixelColor(image, 50, 50, Color.GREEN); // top-left corner of first square
    assertPixelColor(image, 120, 50, Color.GREEN); // top-left corner of second square
    assertPixelColor(image, 190, 50, Color.GREEN); // top-left corner of third square

    // Clean up
    g.dispose();
}

private void assertPixelColor(BufferedImage image, int x, int y, Color expectedColor) {
    int color = image.getRGB(x, y);
    assertEquals(new Color(color, true), expectedColor);
}

    
    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        Canvas canvasMain = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(canvasMain, "No mainCanvas found");
    }
}
