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
    Label lblMessage;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(InteractiveGreetings.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents(){
        greetButton = (Button) TestUtils.findComponent("greetButton",true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField",true);
        
        assertNotNull(greetButton, "No greetButton found.");
        assertNotNull(nameTextField, "No nameTextField found.");
    }
    
    @Test
    public void shouldDisplayCorrectNameInGreetingsDialog(){
        greetButton = (Button) TestUtils.findComponent("greetButton", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        
        robot().click(nameTextField);
        robot().enterText("John Doe");
        robot().click(greetButton);
        robot().waitForIdle();
        
        dialogNotice = (Dialog) TestUtils.findComponent("dialogGreetings", true);
        lblMessage = (Label) TestUtils.findComponent("lblMessage", true);
        
        assertTrue(dialogNotice.isVisible(), "The dialogNotice should be visible");
        assertEquals(lblMessage.getText(), "Hello, John Doe! Have a good day!");
    }
    
}

