package com.codechum;

import com.codechum.swing.jTextField.TextFieldEnableDisable;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TextFieldEnableDisableTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    JTextField textField;
    JButton enableButton, disableButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextFieldEnableDisable.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have a text field named `textField`.
    @Test
    public void shouldHaveTextField() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        assertNotNull(textField, "No textField found.");
    }
    
    // Description: Should have all buttons `enableButton` and `disableButton`.
    @Test
    public void shouldHaveAllButtons() {
        enableButton = (JButton) TestUtils.findComponent("enableButton", true);
        disableButton = (JButton) TestUtils.findComponent("disableButton", true);
        assertNotNull(enableButton, "No enableButton found.");
        assertNotNull(disableButton, "No disableButton found.");
    }
    
    // Description: Should enable `textField` when `enableButton` is clicked.
    @Test
    public void shouldEnableOnButtonEnableClick() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        enableButton = (JButton) TestUtils.findComponent("enableButton", true);
        
        robot().click(enableButton);
        robot().waitForIdle();
        
        assertTrue(textField.isEnabled(), "The text field should be enabled");
    }
    
    // Description: Should disable `textField` when `disableButton` is clicked.
    @Test
    public void shouldDisableOnButtonDisableClick() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        disableButton = (JButton) TestUtils.findComponent("disableButton", true);
        
        robot().click(disableButton);
        robot().waitForIdle();
        
        assertFalse(textField.isEnabled(), "The text field should be disabled");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
