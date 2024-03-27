package com.codechum;

import com.codechum.awt.frame.FrameMaster;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class AWTFrameMasterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FrameMaster.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a frame with a title of "Frame Master".
    @Test
    public void shouldDisplayCorrectTitle() {
        assertEquals(frame.getTitle(), "Frame Master");
    }

    // Description: Should have a frame with a width of 700.
    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 700);
    }

    // Description: Should have a frame with a height of 500.
    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 500);
    }

    // Description: Should have a frame with an x position of 500.
    @Test
    public void shouldHaveCorrectXPosition() {
        assertEquals(frame.getLocation().getX(), 500);
    }

    // Description: Should have a frame with a y position of 300.
    @Test
    public void shouldHaveCorrectYPosition() {
        assertEquals(frame.getLocation().getY(), 300);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
