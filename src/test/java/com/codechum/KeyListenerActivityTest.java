package com.codechum;

import com.codechum.swing.swingEventListeners.KeyListenerActivity;
import java.awt.Frame;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class KeyListenerActivityTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyListenerActivity.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `textField` and `feedbackLabel`.
    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("textField"), "No textField found.");
        assertNotNull(frame.label("feedbackLabel"), "No feedbackLabel found.");
    }

    // Description: Should update the text value of the `feedbackLabel` depending on the key pressed in the `textField`.
    @Test
    public void shouldUpdateFeedbackLabelOnKeyPress() {
        frame.textBox("textField").pressKey(VK_A);

        assertEquals(frame.label("feedbackLabel").text(), "Key pressed: a");
    }

    // Description: Should update the text value of the `feedbackLabel` depending on the key released in the `textField`.
    @Test
    public void shouldUpdateFeedbackLabelOnKeyRelease() {
        frame.textBox("textField").pressAndReleaseKeys(VK_B);

        assertEquals(frame.label("feedbackLabel").text(), "Key released: b");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
