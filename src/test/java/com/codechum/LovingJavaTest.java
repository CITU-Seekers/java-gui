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

    @Test
    public void checkLabel1Value() {
        label1 = (Label) TestUtils.findComponent("label1", true);
        assertEquals(label1.getText(), "Hello World!");
    }

    @Test
    public void checkLabel2Value() {
        label2 = (Label) TestUtils.findComponent("label2", true);
        assertEquals(label2.getText(), "I love programming");
    }

    @Test
    public void checkLabel3Value() {
        label3 = (Label) TestUtils.findComponent("label3", true);
        assertEquals(label3.getText(), "I love CodeChum");
    }

    @Test
    public void checkLabel4Value() {
        label4 = (Label) TestUtils.findComponent("label4", true);
        assertEquals(label4.getText(), "I love Java");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

