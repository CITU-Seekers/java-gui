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

    @Test
    public void shouldHaveCorrectDimensionsForMainFrame() {
        String actualDimensions = frame.getWidth() + "x" + frame.getHeight();

        assertEquals(actualDimensions, "800x350");
    }

    @Test
    public void shouldHaveCorrectLocationForMainFrame() {
        String actualLocation = "(" + frame.getX() + "," + frame.getY() + ")";

        assertEquals(actualLocation, "(200,100)");
    }

    @Test
    public void shouldHaveAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);

        assertNotNull(window1, "No window1 found.");
        assertNotNull(window2, "No window2 found.");
    }

    @Test
    public void shouldHaveCorrectParentForAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);

        assertEquals(window1.getParent().getName(), "mainFrame");
        assertEquals(window2.getParent().getName(), "mainFrame");
    }

    @Test
    public void shouldHaveCorrectDimensionsForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualDimensions = window1.getWidth() + "x" + window1.getHeight();

        assertEquals(actualDimensions, "300x250");
    }

    @Test
    public void shouldHaveCorrectLocationForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualLocation = "(" + window1.getX() + "," + window1.getY() + ")";

        assertEquals(actualLocation, "(300,150)");
    }

    @Test
    public void shouldHaveCorrectDimensionsForWindow2() {
        window2 = (Window) TestUtils.findComponent("window2", true);

        String actualDimensions = window2.getWidth() + "x" + window2.getHeight();

        assertEquals(actualDimensions, "300x250");
    }

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

