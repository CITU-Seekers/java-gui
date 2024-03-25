package com.codechum;

import com.codechum.awt.eventClasses.MouseEventClass;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MouseEventClassTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Label textLabel;
    Button button;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MouseEventClass.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        assertNotNull(textLabel, "No textLabel found.");
        
        for (int i = 0; i < 20; i++) {
            button = (Button) TestUtils.findComponent("button" + (i+1), true);
            assertNotNull(button, "No button" + (i+1) +" found.");
        }
    }
    
    @Test
    public void shouldDisplayTextCorrectly() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        
        for (int i = 0; i < 20; i++) {
            button = (Button) TestUtils.findComponent("button" + (i+1), true);
            
            robot().click(button);
            robot().waitForIdle();
            
            assertEquals(textLabel.getText(), (i+1) + "");
        }
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
