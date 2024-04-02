package com.codechum;

import com.codechum.awt.drawingShapes.RandomDots;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class RandomDotsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Canvas mainCanvas;
    int xCenter, yCenter;
    @Mocked
    Graphics g;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(RandomDots.class).start();

        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        yCenter = mainCanvas.getHeight() / 2;
        xCenter = mainCanvas.getWidth() / 2;

        new MockUp<Canvas>() {
            @Mock
            public Graphics getGraphics() {
                return g;
            }
        };
    }

    public void clickCenterOfCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);

        robot().click(mainCanvas, centerOfCnvMain);
        robot().waitForIdle();
    }

    public void drawInCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);

        robot().pressMouse(mainCanvas, centerOfCnvMain);

        int i = 0;
        while (i < 5) {
            Point endPointOfDrawing = new Point(xCenter - i, yCenter - i);
            robot().moveMouse(mainCanvas, endPointOfDrawing);
            i++;
        }
    }

    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveACanvas() {
        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(mainCanvas, "No mainCanvas found");
    }

    // Description: Should draw dots on `mainCanvas`.
    @Test
    public void shouldDrawDots() {
        clickCenterOfCnvMain();
        drawInCnvMain();
        
        clickCenterOfCnvMain();
        drawInCnvMain();
        
        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.fillOval(anyInt, anyInt, anyInt, anyInt);
                g.fillOval(anyInt, anyInt, anyInt, anyInt);
                g.fillOval(anyInt, anyInt, anyInt, anyInt);
                
            }
        };
    }

    // Description: Should draw dots with no consecutive same colors on `mainCanvas` each time the mouse is clicked.
    @Test
    public void shouldDrawDotsWithNoConsecutiveSameColors() {
        clickCenterOfCnvMain();
        drawInCnvMain();

        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.setColor(Color.RED);
                g.fillOval(anyInt, anyInt, anyInt, anyInt);

                g.setColor(Color.GREEN);
                g.fillOval(anyInt, anyInt, anyInt, anyInt);
            }
        };

    }

}
  