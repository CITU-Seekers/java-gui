package com.codechum;

import com.codechum.swing.swingEventClasses.MouseEventClass;
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
        application(MouseEventClass.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        lblText = (JLabel) TestUtils.findComponent("lblText", true);
        assertNotNull(lblText, "No lblText found.");
        
        for (int i = 0; i < 20; i++) {
            btn = (JButton) TestUtils.findComponent("btn" + (i+1), true);
            assertNotNull(btn, "No btn" + (i+1) +" found.");
        }
    }
    
    @Test
    public void shouldDisplayTextCorrectly() {
        lblText = (JLabel) TestUtils.findComponent("lblText", true);
        
        for (int i = 0; i < 20; i++) {
            btn = (JButton) TestUtils.findComponent("btn" + (i+1), true);
            
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