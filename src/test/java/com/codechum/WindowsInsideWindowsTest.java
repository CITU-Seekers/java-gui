package com.codechum;

import com.codechum.awt.windows.WindowsInsideWindows;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class WindowsInsideWindowsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    Window window1, window2;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(WindowsInsideWindows.class).start();
        robot().waitForIdle();

        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a frame with dimensions 800x350.
    @Test
    public void shouldHaveCorrectDimensionsForMainFrame() {
        String actualDimensions = frame.getWidth() + "x" + frame.getHeight();

        assertEquals(actualDimensions, "800x350");
    }

    // Description: Should have a location of (200, 100) for the frame.
    @Test
    public void shouldHaveCorrectLocationForMainFrame() {
        String actualLocation = "(" + frame.getX() + "," + frame.getY() + ")";

        assertEquals(actualLocation, "(200,100)");
    }

    // Description: Should have all windows named `window1` and window2`.
    @Test
    public void shouldHaveAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);

        assertNotNull(window1, "No window1 found.");
        assertNotNull(window2, "No window2 found.");
    }

    // Description: Should have a parent named `mainFrame` for all windows.
    @Test
    public void shouldHaveCorrectParentForAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);

        assertEquals(window1.getParent().getName(), "mainFrame");
        assertEquals(window2.getParent().getName(), "mainFrame");
    }

    // Description: Should have a dimension of 300x250 for `window1`.
    @Test
    public void shouldHaveCorrectDimensionsForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualDimensions = window1.getWidth() + "x" + window1.getHeight();

        assertEquals(actualDimensions, "300x250");
    }

    // Description: Should have a location of (300, 150) for `window1`.
    @Test
    public void shouldHaveCorrectLocationForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualLocation = "(" + window1.getX() + "," + window1.getY() + ")";

        assertEquals(actualLocation, "(300,150)");
    }

    // Description: Should have a dimension of 300x250 for `window2`.
    @Test
    public void shouldHaveCorrectDimensionsForWindow2() {
        window2 = (Window) TestUtils.findComponent("window2", true);

        String actualDimensions = window2.getWidth() + "x" + window2.getHeight();

        assertEquals(actualDimensions, "300x250");
    }

    // Description: Should have a location of (600, 150) for `window2`.
    @Test
    public void shouldHaveCorrectLocationForWindow2() {
        window2 = (Window) TestUtils.findComponent("window2", true);

        String actualLocation = "(" + window2.getX() + "," + window2.getY() + ")";

        assertEquals(actualLocation, "(600,150)");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

