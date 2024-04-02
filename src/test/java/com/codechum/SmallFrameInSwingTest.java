package com.codechum;

import com.codechum.swing.jFrame.SmallFrameInSwing;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SmallFrameInSwingTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application( SmallFrameInSwing.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a frame with title "Small Frame".
    @Test
    public void shouldDisplayCorrectTitle() {
        assertEquals(frame.getTitle(), "Small Frame");
    }

    // Description: Should set the frame's width to 100.
    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 100);
    }

    // Description: Should set the frame's height to 200.
    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 200);
    }

    // Description: Should set the frame's x position to 600.
    @Test
    public void shouldHaveCorrectXPosition() {
        assertEquals(frame.getLocation().getX(), 600);
    }

    // Description: Should set the frame's y position to 450.
    @Test
    public void shouldHaveCorrectYPosition() {
        assertEquals(frame.getLocation().getY(), 450);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
