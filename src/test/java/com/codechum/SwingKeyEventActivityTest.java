package com.codechum;

import com.codechum.swing.swingEventClasses.KeyEventActivity;
import static org.testng.Assert.*;

import javax.swing.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingKeyEventActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    JTextArea editableTextArea, nonEditableTextArea;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(KeyEventActivity.class).start();
        
        editableTextArea = (JTextArea) TestUtils.findComponent("editableTextArea", true);
        nonEditableTextArea = (JTextArea) TestUtils.findComponent("nonEditableTextArea", true);
    }

    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(editableTextArea, "No editableTextArea found.");
        assertNotNull(nonEditableTextArea, "No nonEditableTextArea found.");
    }
    
    // Should display the character 'c' in the non-editable text area when the 'c' key is pressed.
    @Test
    public void shouldDisplayCharCInNonEditable() {
        robot().click(editableTextArea);
        robot().pressAndReleaseKey(VK_C);
        robot().waitForIdle();
        assertEquals(nonEditableTextArea.getText(), "c");
    }

    // Should display the character 'o' in the non-editable text area when the 'o' key is pressed.
    @Test
    public void shouldDisplayCharCOInNonEditable() {
        robot().click(editableTextArea);
        robot().pressAndReleaseKey(VK_C);
        robot().pressAndReleaseKey(VK_O);
        robot().waitForIdle();
        assertEquals(nonEditableTextArea.getText(), "cO");
    }

    @Test
    public void shouldDisplayCharCOdInNonEditable() {
        robot().click(editableTextArea);
        robot().pressAndReleaseKey(VK_C);
        robot().pressAndReleaseKey(VK_O);
        robot().pressAndReleaseKey(VK_D);
        robot().waitForIdle();
        assertEquals(nonEditableTextArea.getText(), "cOd");
    }

    @Test
    public void shouldDisplayCharCOdEInNonEditable() {
        robot().click(editableTextArea);
        robot().pressAndReleaseKey(VK_C);
        robot().pressAndReleaseKey(VK_O);
        robot().pressAndReleaseKey(VK_D);
        robot().pressAndReleaseKey(VK_E);
        robot().waitForIdle();
        assertEquals(nonEditableTextArea.getText(), "cOdE");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
