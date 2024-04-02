package com.codechum;

import com.codechum.swing.jLabel.LabelClassPracticeProblemI;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class LabelClassPracticeProblemITest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JLabel helloWorldLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelClassPracticeProblemI.class).start();
    }

    // Description: Should have a label named `helloWorldLabel`.
    @Test
    public void shouldHaveLabelHelloWorld() {
        helloWorldLabel = (JLabel) TestUtils.findComponent("helloWorldLabel", true);
        assertNotNull(helloWorldLabel, "No helloWorldLabel found.");
    }

    // Description: Should have a text value of "Hello World! I love Java!" in `helloWorldLabel`.
    @Test
    public void shouldHaveCorrectTextValue() {
        helloWorldLabel = (JLabel) TestUtils.findComponent("helloWorldLabel", true);
        assertEquals(helloWorldLabel.getText(), "Hello World! I love Java!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
