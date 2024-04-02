package com.codechum;

import com.codechum.awt.labels.TwoRows;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TwoRowsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label label1, label2;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TwoRows.class).start();
    }

    // Description: Should have all components named `label1` and `label2`.
    @Test
    public void shouldHaveAllComponents() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        
        assertNotNull(label1, "No label1 found.");
        assertNotNull(label2, "No label1 found.");
    }

    // Description: Should have a text value of "This is the first row" for `label1` and "This is the second row" for `label2`.
    @Test
    public void shouldHaveCorrectTextValue() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        
        assertEquals(label1.getText(), "This is the first row");
        assertEquals(label2.getText(), "This is the second row");
    }
    
    // Description: Should have a background color of light gray for `label1` and default color for `label2`.
    @Test
    public void shouldHaveCorrectBackgroundColor() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        
        Color expectedColorLabel1 = Color.lightGray;
        Color expectedColorLabel2 = SystemColor.window; 

        assertEquals(expectedColorLabel1, label1.getBackground());
        assertEquals(expectedColorLabel2, label2.getBackground());
  
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

