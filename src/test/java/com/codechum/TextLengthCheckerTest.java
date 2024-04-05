package com.codechum;

import com.codechum.awt.eventListeners.TextLengthChecker;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class TextLengthCheckerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField wordTextField;
    Label messageLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextLengthChecker.class).start();
    }

    // Description: Should have all components `wordTextField` and `messageLabel`.
    @Test
    public void shouldHaveAllComponents() {
        wordTextField = (TextField) TestUtils.findComponent("wordTextField", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        assertNotNull(wordTextField, "No wordTextField found.");
        assertNotNull(messageLabel, "No messageLabel found.");
    }

    // Description: Should display the message “The text is short.” in `wordTextField` when the text in `wordTextField` is less than 10 characters.
    @Test
    public void shouldDisplayCorrectMessageIfLengthOfTextIsBelow10() {
        wordTextField = (TextField) TestUtils.findComponent("wordTextField", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        robot().click(wordTextField);
        robot().pressAndReleaseKeys(VK_C, VK_O, VK_D, VK_E);

        assertEquals(messageLabel.getText(), "The text is short.");
    }

    // Description: Should display the message “The text is long.” in `wordTextField` when the text in `wordTextField` is more than 10 characters.
    @Test
    public void shouldDisplayCorrectMessageIfLengthOfTextIsMoreThan10() {
        wordTextField = (TextField) TestUtils.findComponent("wordTextField", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        robot().click(wordTextField);
        robot().pressAndReleaseKeys(VK_H, VK_E, VK_L, VK_L, VK_O, VK_SPACE, VK_W, VK_O, VK_R, VK_L);
        
        assertEquals(messageLabel.getText(), "The text is short.");
        
        robot().pressAndReleaseKeys(VK_D);
        
        assertEquals(messageLabel.getText(), "The text is long.");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
   