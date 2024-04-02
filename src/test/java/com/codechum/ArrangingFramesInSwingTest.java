package com.codechum;

import com.codechum.swing.jFrame.ArrangingFramesInSwing;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ArrangingFramesInSwingTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ArrangingFramesInSwing.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a frame with title "Frame With Position".
    @Test
    public void shouldDisplayCorrectTitle() {
        assertEquals(frame.getTitle(), "Frame With Position");
    }

    // Description: Should set the frame's width to 600.
    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 600);
    }

    // Description: Should set the frame's height to 500.
    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 500);
    }

    // Description: Should set the frame's x position to 750.
    @Test
    public void shouldHaveCorrectXPosition() {
        assertEquals(frame.getLocation().getX(), 750);
    }

    // Description: Should set the frame's y position to 300.
    @Test
    public void shouldHaveCorrectYPosition() {
        assertEquals(frame.getLocation().getY(), 300);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
