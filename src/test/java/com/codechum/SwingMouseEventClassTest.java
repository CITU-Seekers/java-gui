package com.codechum;

import com.codechum.swing.swingEventClasses.SwingMouseEventClass;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingMouseEventClassTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    JLabel lblText;
    JButton btn;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SwingMouseEventClass.class).start();
    }

    // Description: Should have all components `textLabel` and `button1`, `button2`, ..., `button20`.
    @Test
    public void shouldHaveAllComponents() {
        lblText = (JLabel) TestUtils.findComponent("textLabel", true);
        assertNotNull(lblText, "No textLabel found.");
        
        for (int i = 0; i < 20; i++) {
            btn = (JButton) TestUtils.findComponent("button" + (i+1), true);
            assertNotNull(btn, "No button" + (i+1) +" found.");
        }
    }
    
    // Description: Should display text correctly in `textLabel` based on the button clicked.
    @Test
    public void shouldDisplayTextCorrectly() {
        lblText = (JLabel) TestUtils.findComponent("textLabel", true);
        
        for (int i = 0; i < 20; i++) {
            btn = (JButton) TestUtils.findComponent("button" + (i+1), true);
            
            robot().click(btn);
            robot().waitForIdle();
            
            assertEquals(lblText.getText(), (i+1) + "");
        }
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
