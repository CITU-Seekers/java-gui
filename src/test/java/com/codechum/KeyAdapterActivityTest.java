package com.codechum;

import com.codechum.awt.eventAdapters.KeyAdapterActivity;
import static org.testng.Assert.*;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class KeyAdapterActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField inputTextField;
    Label statusLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyAdapterActivity.class).start();
    }

    // Description: Should have all components `inputTextField` and `statusLabel`.
    @Test
    public void shouldHaveAllComponents() {
        inputTextField = (TextField) TestUtils.findComponent("inputTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        assertNotNull(inputTextField);
        assertNotNull(statusLabel);
    }

    // Description: Should display the key pressed message on the `statusLabel` when a key is pressed in the `inputTextField`.
    @Test
    public void shouldDisplayKeyTypedMessage() {
        inputTextField = (TextField) TestUtils.findComponent("inputTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(inputTextField);
        robot().pressKey(VK_H);

        assertEquals(statusLabel.getText(), "Key Typed: h");
    }

    // Description: Should display the key released message on the `statusLabel` when a key is released in the `inputTextField`.
    @Test
    public void shouldDisplayKeyReleasedMessage() {
        inputTextField = (TextField) TestUtils.findComponent("inputTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(inputTextField);
        robot().pressKey(VK_H);
        robot().releaseKey(VK_H);

        assertEquals(statusLabel.getText(), "Key Released: h");
    }

}
