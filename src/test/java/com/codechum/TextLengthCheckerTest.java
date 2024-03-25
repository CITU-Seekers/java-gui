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

    TextField textField;
    Label labelMessage;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextLengthChecker.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        labelMessage = (Label) TestUtils.findComponent("labelMessage", true);
        
        assertNotNull(textField, "No textField found.");
        assertNotNull(labelMessage, "No labelMessage found.");
    }

    @Test
    public void shouldDisplayCorrectMessageIfLengthOfTextIsBelow10() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        labelMessage = (Label) TestUtils.findComponent("labelMessage", true);
        
        robot().click(textField);
        robot().pressAndReleaseKeys(VK_C, VK_O, VK_D, VK_E);

        assertEquals(labelMessage.getText(), "The text is short.");
    }

    @Test
    public void shouldDisplayCorrectMessageIfLengthOfTextIsMoreThan10() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        labelMessage = (Label) TestUtils.findComponent("labelMessage", true);
        
        robot().click(textField);
        robot().pressAndReleaseKeys(VK_H, VK_E, VK_L, VK_L, VK_O, VK_SPACE, VK_W, VK_O, VK_R, VK_L);
        
        assertEquals(labelMessage.getText(), "The text is short.");
        
        robot().pressAndReleaseKeys(VK_D);
        
        assertEquals(labelMessage.getText(), "The text is long.");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
   