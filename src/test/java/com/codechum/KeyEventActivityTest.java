package com.codechum;

import com.codechum.awt.eventClasses.KeyEventActivity;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class KeyEventActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextArea textArea1, textArea2;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyEventActivity.class).start();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        textArea1 = (TextArea) TestUtils.findComponent("textArea1", true);
        textArea2 = (TextArea) TestUtils.findComponent("textArea2", true);
        
        assertNotNull(textArea1, "No textArea1 found.");
        assertNotNull(textArea2, "No textArea2 found.");
    }
    
    @Test
    public void shouldDisplayTextInNonEditable() {
        textArea1 = (TextArea) TestUtils.findComponent("textArea1", true);
        textArea2 = (TextArea) TestUtils.findComponent("textArea2", true);
        
        robot().click(textArea1);
        robot().pressAndReleaseKey(VK_C);
        
        assertEquals(textArea2.getText(), "c");
        
        robot().pressAndReleaseKey(VK_O);
        
        assertEquals(textArea2.getText(), "cO");
        
        robot().pressAndReleaseKey(VK_D);
        
        assertEquals(textArea2.getText(), "cOd");
        
        robot().pressAndReleaseKey(VK_E);
        
        assertEquals(textArea2.getText(), "cOdE");
       
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
