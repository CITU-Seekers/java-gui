package com.codechum;

import com.codechum.awt.dialog.InteractiveGreetings;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class InteractiveGreetingsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField nameTextField;
    Button greetButton;
    
    Dialog dialogNotice;
    Label messageLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(InteractiveGreetings.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components `greetButton` and `nameTextField`.
    @Test
    public void shouldHaveAllComponents(){
        greetButton = (Button) TestUtils.findComponent("greetButton",true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField",true);
        
        assertNotNull(greetButton, "No greetButton found.");
        assertNotNull(nameTextField, "No nameTextField found.");
    }
    
    // Description: Should show the `greetingsDialog` with the `messageLabel` displaying "Hello, {name}! Have a good day!" on `greetButton` click.
    @Test
    public void shouldDisplayCorrectNameInGreetingsDialog(){
        greetButton = (Button) TestUtils.findComponent("greetButton", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        
        robot().click(nameTextField);
        robot().enterText("John Doe");
        robot().click(greetButton);
        robot().waitForIdle();
        
        dialogNotice = (Dialog) TestUtils.findComponent("greetingsDialog", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        assertTrue(dialogNotice.isVisible(), "The dialogNotice should be visible");
        assertEquals(messageLabel.getText(), "Hello, John Doe! Have a good day!");
    }
    
}

