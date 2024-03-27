package com.codechum;

import com.codechum.awt.textArea.AreaCasing;
import java.awt.Button;
import java.awt.TextArea;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class AreaCasingTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextArea messageTextArea;
    Button lowercaseButton, uppercaseButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(AreaCasing.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `messageTextArea`, `lowercaseButton`, and `uppercaseButton`.
    @Test
    public void shouldHaveAllComponents() {
        messageTextArea = (TextArea) TestUtils.findComponent("messageTextArea", true);
        lowercaseButton = (Button) TestUtils.findComponent("lowercaseButton", true);
        uppercaseButton = (Button) TestUtils.findComponent("uppercaseButton", true);

        assertNotNull(messageTextArea, "No messageTextArea found.");
        assertNotNull(lowercaseButton, "No lowercaseButton found.");
        assertNotNull(uppercaseButton, "No uppercaseButton found.");
    }

    // Description: Should convert the text in `messageTextArea` to uppercase when `uppercaseButton` is clicked.
    @Test
    public void shouldConvertToUppercase() {
        messageTextArea = (TextArea) TestUtils.findComponent("messageTextArea", true);
        uppercaseButton = (Button) TestUtils.findComponent("uppercaseButton", true);

        // Set initial text in the text area
        robot().click(messageTextArea);
        robot().enterText("Hello, how are you?");
        robot().waitForIdle();

        // Click the Uppercase button
        robot().click(uppercaseButton);
        robot().waitForIdle();

        // Assert that the text has been converted to uppercase
        assertEquals(messageTextArea.getText(), "HELLO, HOW ARE YOU?");
    }

    // Description: Should convert the text in `messageTextArea` to lowercase when `lowercaseButton` is clicked.
    @Test
    public void shouldConvertToLowercase() {
        messageTextArea = (TextArea) TestUtils.findComponent("messageTextArea", true);
        lowercaseButton = (Button) TestUtils.findComponent("lowercaseButton", true);

        // Set initial text in the text area
        robot().click(messageTextArea);
        robot().enterText("Hello, how are you?");
        robot().waitForIdle();

        // Click the Lowercase button
        robot().click(lowercaseButton);
        robot().waitForIdle();

        // Assert that the text has been converted to lowercase
        assertEquals(messageTextArea.getText(), "hello, how are you?");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

