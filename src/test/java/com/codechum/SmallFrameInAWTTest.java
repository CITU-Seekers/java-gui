package com.codechum;

import com.codechum.awt.frame.SmallFrame;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SmallFrameInAWTTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SmallFrame.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a frame with a title of "Small Frame".
    @Test
    public void shouldDisplayCorrectTitle() {
        assertEquals(frame.getTitle(), "Small Frame");
    }

    // Description: Should have a frame with a width of 100.
    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 100);
    }

    // Description: Should have a frame with a height of 200.
    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 200);
    }

    // Description: Should have a frame with an x position of 600.
    @Test
    public void shouldHaveCorrectXPosition() {
        assertEquals(frame.getLocation().getX(), 600);
    }

    // Description: Should have a frame with a y position of 450.
    @Test
    public void shouldHaveCorrectYPosition() {
        assertEquals(frame.getLocation().getY(), 450);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

