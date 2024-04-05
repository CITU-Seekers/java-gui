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

    // Description: Should have all components `inputField` and `focusLabel`.
    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("inputField"), "No inputField found.");
        assertNotNull(frame.label("focusLabel"), "No focusLabel found.");
    }
    
    // Description: Should display "Input field has focus." in `focusLabel` when focus is gained in the `inputField`.
    @Test
    public void shouldUpdateFocusLabelOnFocusGained() {
        frame.textBox("inputField").focus();
        assertEquals(frame.label("focusLabel").text(), "Input field has focus.");
    }

    // Description: Should display "Input field lost focus." in `focusLabel` when focus is lost in the `inputField`.
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
