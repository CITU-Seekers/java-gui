package com.codechum;

import com.codechum.swing.swingEventAdapters.MouseAdapterActivity;
import java.awt.Frame;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MouseAdapterActivityTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MouseAdapterActivity.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("textArea"), "No textArea found.");
    }
    
     @Test
    public void shouldHaveInitialTextForTextArea() {
        assertEquals(frame.textBox("textArea").text(), "No clicks");
    }

    @Test
    public void shouldUpdateTextAreaOnLeftMouseClick() {
        frame.textBox("textArea").click();

        assertEquals(frame.textBox("textArea").text(), "Left Mouse Button Clicked!");
    }

    @Test
    public void shouldUpdateTextAreaOnRightMouseClick() {
        frame.textBox("textArea").rightClick();

        assertEquals(frame.textBox("textArea").text(), "Right Mouse Button Clicked!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
