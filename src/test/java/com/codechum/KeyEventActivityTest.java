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
    
    TextArea editableTextArea, nonEditableTextArea;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyEventActivity.class).start();
    }
    
    // Description: Should have all components `editableTextArea` and `nonEditableTextArea`.
    @Test
    public void shouldHaveAllComponents() {
        editableTextArea = (TextArea) TestUtils.findComponent("editableTextArea", true);
        nonEditableTextArea = (TextArea) TestUtils.findComponent("nonEditableTextArea", true);
        
        assertNotNull(editableTextArea, "No editableTextArea found.");
        assertNotNull(nonEditableTextArea, "No nonEditableTextArea found.");
    }
    
    // Description: Should display vowel letters in uppercase in `nonEditableTextArea` based on the text typed in `editableTextArea`.
    @Test
    public void shouldDisplayTextInNonEditable() {
        editableTextArea = (TextArea) TestUtils.findComponent("editableTextArea", true);
        nonEditableTextArea = (TextArea) TestUtils.findComponent("nonEditableTextArea", true);
        
        robot().click(editableTextArea);
        robot().pressAndReleaseKey(VK_C);
        
        assertEquals(nonEditableTextArea.getText(), "c");
        
        robot().pressAndReleaseKey(VK_O);
        
        assertEquals(nonEditableTextArea.getText(), "cO");
        
        robot().pressAndReleaseKey(VK_D);
        
        assertEquals(nonEditableTextArea.getText(), "cOd");
        
        robot().pressAndReleaseKey(VK_E);
        
        assertEquals(nonEditableTextArea.getText(), "cOdE");
       
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
