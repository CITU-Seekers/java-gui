package com.codechum;

import com.codechum.swing.swingEventListeners.FocusListenerActivity;
import java.awt.Frame;
import javax.swing.JLabel;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FocusListenerActivityTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FocusListenerActivity.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("inputField"), "No inputField found.");
        assertNotNull(frame.label("focusLabel"), "No focusLabel found.");
    }
    
    @Test
    public void shouldUpdateFocusLabelOnFocusGained() {
        frame.textBox("inputField").focus();
        assertEquals(frame.label("focusLabel").text(), "Input field has focus.");
    }

    @Test
    public void shouldUpdateFocusLabelOnFocusLost() {
        frame.textBox("inputField").focus();
        frame.label("focusLabel").focus();

        assertEquals(frame.label("focusLabel").text(), "Input field lost focus.");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
