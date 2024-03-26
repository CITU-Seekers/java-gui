package com.codechum;

import com.codechum.swing.swingEventListeners.TextLengthChecker;
import java.awt.*;

import static org.testng.Assert.*;

import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import org.testng.annotations.*;

public class SwingTextLengthCheckerTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextLengthChecker.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("textField"), "No textField found.");
        assertNotNull(frame.label("messageLabel"), "No messageLabel found.");
    }

    @Test
    public void shouldDisplayIsShortMessageIfLengthOfTextIsBelow10() {
        frame.textBox("textField")
        .pressAndReleaseKeys(VK_C, VK_O, VK_D, VK_E);

        assertEquals(frame.label("messageLabel").text(), "The text is short.");
    }

    @Test
    public void shouldDisplayTextIsLongMessageIfLengthOfTextIsMoreThan10() {
        frame.textBox("textField")
        .pressAndReleaseKeys(VK_H, VK_E, VK_L, VK_L, VK_O, VK_SPACE, VK_W, VK_O, VK_R, VK_L);

        assertEquals(frame.label("messageLabel").text(), "The text is short.");

        frame.textBox("textField")
        .pressAndReleaseKeys(VK_D);

        assertEquals(frame.label("messageLabel").text(), "The text is long.");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
