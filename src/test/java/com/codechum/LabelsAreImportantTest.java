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

    Label helloWorldLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelsAreImportant.class).start();
    }

    // Description: Should have a label named `helloWorldLabel`.
    @Test
    public void shouldHaveLabelHelloWorld() {
        helloWorldLabel = (Label) TestUtils.findComponent("helloWorldLabel", true);
        assertNotNull(helloWorldLabel, "No helloWorldLabel found.");
    }

    // Description: Should have a text value of "Hello World! I love Java!" for `helloWorldLabel`.
    @Test
    public void shouldHaveCorrectTextValue() {
        helloWorldLabel = (Label) TestUtils.findComponent("helloWorldLabel", true);
        assertEquals(helloWorldLabel.getText(), "Hello World! I love Java!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

