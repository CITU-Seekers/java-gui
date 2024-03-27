package com.codechum;

import com.codechum.awt.eventClasses.AWTMouseEventClass;
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
        application(AWTMouseEventClass.class).start();
    }

    // Description: Should have all components `textLabel` and `button1`, `button2`, ..., `button20`.
    @Test
    public void shouldHaveAllComponents() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        assertNotNull(textLabel, "No textLabel found.");
        
        for (int i = 0; i < 20; i++) {
            button = (Button) TestUtils.findComponent("button" + (i+1), true);
            assertNotNull(button, "No button" + (i+1) +" found.");
        }
    }
    
    // Description: Should display text correctly in `textLabel` based on the button clicked.
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
