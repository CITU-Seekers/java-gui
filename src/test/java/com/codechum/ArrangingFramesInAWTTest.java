package com.codechum;

import com.codechum.awt.frame.ArrangingFrames;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ArrangingFramesInAWTTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ArrangingFrames.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    @Test
    public void shouldDisplayCorrectTitle() {
        assertEquals(frame.getTitle(), "Frame With Position");
    }

    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 600);
    }

    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 500);
    }

    @Test
    public void shouldHaveCorrectXPosition() {
        assertEquals(frame.getLocation().getX(), 750);
    }

    @Test
    public void shouldHaveCorrectYPosition() {
        assertEquals(frame.getLocation().getY(), 300);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}