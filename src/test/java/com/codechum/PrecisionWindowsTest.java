package com.codechum;

import com.codechum.awt.windows.PrecisionWindows;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class PrecisionWindowsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    Window window1, window2, window3, window4;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PrecisionWindows.class).start();
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

        assertEquals(actualDimensions, "700x500");
    }

    @Test
    public void shouldHaveCorrectLocationForMainFrame() {
        String actualLocation = "(" + frame.getX() + "," + frame.getY() + ")";

        assertEquals(actualLocation, "(300,200)");
    }

    @Test
    public void shouldHaveAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);
        window3 = (Window) TestUtils.findComponent("window3", true);
        window4 = (Window) TestUtils.findComponent("window4", true);

        assertNotNull(window1, "No window1 found.");
        assertNotNull(window2, "No window2 found.");
        assertNotNull(window3, "No window3 found.");
         assertNotNull(window4, "No window4 found.");
    }

    @Test
    public void shouldHaveCorrectParentForAllWindows() {
        window1 = (Window) TestUtils.findComponent("window1", true);
        window2 = (Window) TestUtils.findComponent("window2", true);
         window3 = (Window) TestUtils.findComponent("window3", true);
        window4 = (Window) TestUtils.findComponent("window4", true);

        assertEquals(window1.getParent().getName(), "mainFrame");
        assertEquals(window2.getParent().getName(), "mainFrame");
         assertEquals(window3.getParent().getName(), "mainFrame");
         assertEquals(window4.getParent().getName(), "mainFrame");
    }

    @Test
    public void shouldHaveCorrectDimensionsForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualDimensions = window1.getWidth() + "x" + window1.getHeight();

        assertEquals(actualDimensions, "100x100");
    }

    @Test
    public void shouldHaveCorrectLocationForWindow1() {
        window1 = (Window) TestUtils.findComponent("window1", true);

        String actualLocation = "(" + window1.getX() + "," + window1.getY() + ")";

        assertEquals(actualLocation, "(450,300)");
    }

    @Test
    public void shouldHaveCorrectDimensionsForWindow2() {
        window2 = (Window) TestUtils.findComponent("window2", true);

        String actualDimensions = window2.getWidth() + "x" + window2.getHeight();

        assertEquals(actualDimensions, "100x100");
    }

    @Test
    public void shouldHaveCorrectLocationForWindow2() {
        window2 = (Window) TestUtils.findComponent("window2", true);

        String actualLocation = "(" + window2.getX() + "," + window2.getY() + ")";

        assertEquals(actualLocation, "(550,400)");
    }

    @Test
    public void shouldHaveCorrectDimensionsForWindow3() {
        window3 = (Window) TestUtils.findComponent("window3", true);

        String actualDimensions = window3.getWidth() + "x" + window3.getHeight();

        assertEquals(actualDimensions, "100x100");
    }

    @Test
    public void shouldHaveCorrectLocationForWindow3() {
        window3 = (Window) TestUtils.findComponent("window3", true);

        String actualLocation = "(" + window3.getX() + "," + window3.getY() + ")";

        assertEquals(actualLocation, "(650,500)");
    }

     @Test
    public void shouldHaveCorrectDimensionsForWindow4() {
        window4 = (Window) TestUtils.findComponent("window4", true);

        String actualDimensions = window4.getWidth() + "x" + window4.getHeight();

        assertEquals(actualDimensions, "100x100");
    }

    @Test
    public void shouldHaveCorrectLocationForWindow4() {
        window4 = (Window) TestUtils.findComponent("window4", true);

        String actualLocation = "(" + window4.getX() + "," + window4.getY() + ")";

        assertEquals(actualLocation, "(750,600)");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

