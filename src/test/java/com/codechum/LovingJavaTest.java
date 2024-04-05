package com.codechum;

import com.codechum.awt.labels.LovingJava;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class LovingJavaTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Label label1, label2, label3, label4;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LovingJava.class).start();
    }

    // Description: Should have all labels named `label1`, `label2`, `label3`, and `label4`.
    @Test
    public void shouldHaveAllLabels() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        label2 = (Label) TestUtils.findComponent("label2", true);
        label3 = (Label) TestUtils.findComponent("label3", true);
        label4 = (Label) TestUtils.findComponent("label4", true);
        assertNotNull(label1, "No label1 found.");
        assertNotNull(label2, "No label2 found.");
        assertNotNull(label3, "No label3 found.");
        assertNotNull(label4, "No label4 found.");
    }

    // Description: Should have a text value of "Hello World!" for `label1`.
    @Test
    public void shouldCheckLabel1Value() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        assertEquals(label1.getText(), "Hello World!");
    }

    // Description: Should have a text value of "I love programming" for `label2`.
    @Test
    public void shouldCheckLabel2Value() {
        label2 = (Label) TestUtils.findComponent("label2", true);
        assertEquals(label2.getText(), "I love programming");
    }

    // Description: Should have a text value of "I love CodeChum" for `label3`.
    @Test
    public void shouldCheckLabel3Value() {
        label3 = (Label) TestUtils.findComponent("label3", true);
        assertEquals(label3.getText(), "I love CodeChum");
    }

    // Description: Should have a text value of "I love Java" for `label4`.
    @Test
    public void shouldCheckLabel4Value() {
        label4 = (Label) TestUtils.findComponent("label4", true);
        assertEquals(label4.getText(), "I love Java");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

