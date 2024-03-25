package com.codechum;

import com.codechum.awt.textField.EnableMeEnableMeNot;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class EnableMeEnableMeNotTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField textField;
    Button enableButton, disableButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(EnableMeEnableMeNot.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveTextField() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        assertNotNull(textField, "No textField found.");
    }
    
    @Test
    public void shouldHaveAllButtons() {
        enableButton = (Button) TestUtils.findComponent("enableButton", true);
        disableButton = (Button) TestUtils.findComponent("disableButton", true);
        assertNotNull(enableButton, "No enableButton found.");
        assertNotNull(disableButton, "No disableButton found.");
    }
    
    @Test
    public void shouldEnableOnButtonEnableClick() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        enableButton = (Button) TestUtils.findComponent("enableButton", true);
        
        robot().click(enableButton);
        robot().waitForIdle();
        
        assertTrue(textField.isEnabled(), "The text field should be enabled");
    }
    
    @Test
    public void shouldDisableOnButtonDisableClick() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        disableButton = (Button) TestUtils.findComponent("disableButton", true);
        
        robot().click(disableButton);
        robot().waitForIdle();
        
        assertFalse(textField.isEnabled(), "The text field should be disabled");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

