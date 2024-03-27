package com.codechum;

import com.codechum.awt.labels.LabelsAreImportant;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class LabelsAreImportantTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label labelHelloWorld;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelsAreImportant.class).start();
    }

    // Description: Should have a label named `labelHelloWorld`.
    @Test
    public void shouldHaveLabelHelloWorld() {
        labelHelloWorld = (Label) TestUtils.findComponent("labelHelloWorld", true);
        assertNotNull(labelHelloWorld, "No labelHelloWorld found.");
    }

    // Description: Should have a text value of "Hello World! I love Java!" for `labelHelloWorld`.
    @Test
    public void shouldHaveCorrectTextValue() {
        labelHelloWorld = (Label) TestUtils.findComponent("labelHelloWorld", true);
        assertEquals(labelHelloWorld.getText(), "Hello World! I love Java!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

