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

    JLabel labelHelloWorld;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelClassPracticeProblemI.class).start();
    }

    @Test
    public void shouldHaveLabelHelloWorld() {
        labelHelloWorld = (JLabel) TestUtils.findComponent("labelHelloWorld", true);
        assertNotNull(labelHelloWorld, "No labelHelloWorld found.");
    }

    @Test
    public void shouldHaveCorrectTextValue() {
        labelHelloWorld = (JLabel) TestUtils.findComponent("labelHelloWorld", true);
        assertEquals(labelHelloWorld.getText(), "Hello World! I love Java!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
