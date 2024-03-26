package com.codechum;

import com.codechum.swing.jLabel.LabelLove;
import static org.testng.Assert.*;
import javax.swing.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the JLabel1 class.
 */
public class LabelLoveTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    /**
     * Set up method to register an EmergencyAbortListener and start the JLabel1 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelLove.class).start();
        robot().waitForIdle();
    }

    /**
     * Test to check the presence and text value of the "Code is Poetry!" label.
     */
    @Test
    public void shouldHaveCodeIsPoetryLabel() {
        JLabel label = (JLabel) TestUtils.findComponent("codingLabel", true);
        assertNotNull(label, "No codingLabel found.");
        assertEquals(label.getText(), "Code is Poetry!");
    }

    /**
     * Test to check the presence and text value of the "Debugging is an adventure" label.
     */
    @Test
    public void shouldHaveDebuggingLabel() {
        JLabel label = (JLabel) TestUtils.findComponent("debugLabel", true);
        assertNotNull(label, "No debugLabel found.");
        assertEquals(label.getText(), "Debugging is an adventure");
    }

    /**
     * Test to check the presence and text value of the "Java is my cup of coffee" label.
     */
    @Test
    public void shouldHaveJavaLabel() {
        JLabel label = (JLabel) TestUtils.findComponent("javaLabel", true);
        assertNotNull(label, "No javaLabel found.");
        assertEquals(label.getText(), "Java is my cup of coffee");
    }

    /**
     * Test to check the presence and text value of the "CodeChum makes learning fun" label.
     */
    @Test
    public void shouldHaveCodeChumLabel() {
        JLabel label = (JLabel) TestUtils.findComponent("chumLabel", true);
        assertNotNull(label, "No chumLabel found.");
        assertEquals(label.getText(), "CodeChum makes learning fun");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
