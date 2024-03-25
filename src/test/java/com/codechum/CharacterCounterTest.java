package com.codechum;

import com.codechum.awt.textArea.CharacterCounter;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CharacterCounterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextArea messageTextArea;
    TextField characterTextField;
    Button countButton;
    Label countLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CharacterCounter.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        messageTextArea = (TextArea) TestUtils.findComponent("messageTextArea", true);
        characterTextField = (TextField) TestUtils.findComponent("characterTextField", true);
        countButton = (Button) TestUtils.findComponent("countButton", true);
        countLabel = (Label) TestUtils.findComponent("countLabel", true);

        assertNotNull(messageTextArea, "No messageTextArea found.");
        assertNotNull(characterTextField, "No characterTextField found.");
        assertNotNull(countButton, "No countButton found.");
        assertNotNull(countLabel, "No countLabel found.");
    }

    @Test
    public void shouldCountOccurrencesOfCharacter() {
        messageTextArea = (TextArea) TestUtils.findComponent("messageTextArea", true);
        characterTextField = (TextField) TestUtils.findComponent("characterTextField", true);
        countButton = (Button) TestUtils.findComponent("countButton", true);
        countLabel = (Label) TestUtils.findComponent("countLabel", true);

        // Set initial text in the text area and character in the text field
        robot().click(messageTextArea);
        robot().enterText("Hello, how are you?");
        robot().click(characterTextField);
        robot().enterText("o");
        robot().waitForIdle();

        // Click the Count button
        robot().click(countButton);
        robot().waitForIdle();

        // Assert that the countLabel shows the correct count
        assertEquals(countLabel.getText(), "3");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

