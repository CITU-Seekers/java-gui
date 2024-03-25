package com.codechum;

import com.codechum.awt.windows.WindowWithPosition;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class WindowWithPositionTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    Window mainWindow;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(WindowWithPosition.class).start();
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

        assertEquals(actualDimensions, "600x500");
    }

    @Test
    public void shouldHaveCorrectLocationForMainFrame() {
        String actualLocation = "(" + frame.getX() + "," + frame.getY() + ")";

        assertEquals(actualLocation, "(200,100)");
    }

    @Test
    public void shouldHaveMainWindow() {
        mainWindow = (Window) TestUtils.findComponent("mainWindow", true);
        assertNotNull(mainWindow, "No mainWindow found.");
    }

    @Test
    public void shouldHaveCorrectParentForMainWindow() {
        mainWindow = (Window) TestUtils.findComponent("mainWindow", true);

        assertEquals(mainWindow.getParent().getName(), "mainFrame");
    }

    @Test
    public void shouldHaveCorrectDimensionsForMainWindow() {
        mainWindow = (Window) TestUtils.findComponent("mainWindow", true);

        String actualDimensions = mainWindow.getWidth() + "x" + mainWindow.getHeight();

        assertEquals(actualDimensions, "400x400");
    }

    @Test
    public void shouldHaveCorrectLocationForMainWindow() {
        mainWindow = (Window) TestUtils.findComponent("mainWindow", true);

        String actualLocation = "(" + mainWindow.getX() + "," + mainWindow.getY() + ")";

        assertEquals(actualLocation, "(300,150)");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
  
