package com.codechum;

import com.codechum.swing.swingEventAdapters.KeyListenerAdapterActivity;
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

public class KeyListenerAdapterActivityTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyListenerAdapterActivity.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `inputField`, `keyLabel`, and `specialLabel`.
    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("inputField"), "No inputField found.");
        assertNotNull(frame.label("keyLabel"), "No keyLabel found.");
        assertNotNull(frame.label("specialLabel"), "No specialLabel found.");
    }

    // Description: Should update the `keyLabel` text to "Key pressed: {key}" when a key is pressed and the `specialLabel` if the key is a special character.
    @Test
    public void shouldUpdateLabelsOnKeyPress() {
        frame.textBox("inputField").pressKey(VK_A);

        assertEquals(frame.label("keyLabel").text(), "Key pressed: a");
        assertEquals(frame.label("specialLabel").text(), "Special character not pressed.");
    }

    // Description: Should update the `keyLabel` text to "Key released: {key}" when a key is released and the `specialLabel` if the key is a special character.
    @Test
    public void shouldUpdateLabelsOnSpecialKeyPressAndRelease() {
         frame.textBox("inputField").pressKey(VK_Z);
         
         assertEquals(frame.label("keyLabel").text(), "Key pressed: z");
         assertEquals(frame.label("specialLabel").text(), "Special character pressed.");
         
        frame.textBox("inputField").pressAndReleaseKeys(VK_Z);

        assertEquals(frame.label("keyLabel").text(), "Key released: z");
        assertEquals(frame.label("specialLabel").text(), "Special character not pressed.");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
