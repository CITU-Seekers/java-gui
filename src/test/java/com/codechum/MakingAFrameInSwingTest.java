package com.codechum;

import com.codechum.swing.jFrame.MakingAFrameInSwing;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MakingAFrameInSwingTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MakingAFrameInSwing.class).start();
        
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
        assertEquals(frame.getTitle(), "Frame");
    }

    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(frame.getSize().getWidth(), 500);
    }

    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(frame.getSize().getHeight(), 400);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
 