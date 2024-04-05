package com.codechum;

import com.codechum.awt.labels.ThreeColumns;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ThreeColumnsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label label1, label2, label3;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ThreeColumns.class).start();
    }

    // Description: Should have all components named `label1`, `label2`, and `label3`.
    @Test
    public void shouldHaveAllComponents() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        label3 = (Label) TestUtils.findComponent("label3", true);
        
        assertNotNull(label1, "No label1 found.");
        assertNotNull(label2, "No label1 found.");
        assertNotNull(label3, "No label1 found.");
    }

    // Description: Should have a text value of "This is the first column" for `label1`, "This is the second column" for `label2`, and "This is the third column" for `label3`.
    @Test
    public void shouldHaveCorrectTextValue() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        label3 = (Label) TestUtils.findComponent("label3", true);
        
        assertEquals(label1.getText(), "This is the first column");
        assertEquals(label2.getText(), "This is the second column");
        assertEquals(label3.getText(), "This is the third column");
    }
    
    // Description: Should have a background color of light gray for `label1`, default color for `label2`, and light gray for `label3`.
    @Test
    public void shouldHaveCorrectBackgroundColor() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        label3 = (Label) TestUtils.findComponent("label3", true);
        
        Color expectedColorLabel1 = Color.lightGray;
        Color expectedColorLabel2 = SystemColor.window; 
        Color expectedColorLabel3 = Color.lightGray;

        assertEquals(expectedColorLabel1, label1.getBackground());
        assertEquals(expectedColorLabel2, label2.getBackground());
        assertEquals(expectedColorLabel3, label3.getBackground());
  
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

