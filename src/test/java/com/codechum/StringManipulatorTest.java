package com.codechum;

import com.codechum.swing.jButton.StringManipulator;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the CODECHUMACTIVITY class.
 */
public class StringManipulatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    JLabel textLabel;
    JButton reverseButton, resetButton;

    /**
     * Set up method to register an EmergencyAbortListener and start the CODECHUMACTIVITY application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(StringManipulator.class).start();
        robot().waitForIdle();
    }

    // Description: Should have a label named `textLabel` with text "Reverse the string!".
    /**
     * Test to check the initial state of the text label.
     */
    @Test
    public void shouldHaveInitialTextLabel() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        assertNotNull(textLabel, "No textLabel found.");
        assertEquals(textLabel.getText(), "Reverse the string!");
    }

    // Description: Should reverse the text in `textLabel` when `reverseButton` is clicked.
    /**
     * Test to check the presence and functionality of the reverseButton.
     */
    @Test
    public void shouldReverseString() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        reverseButton = (JButton) TestUtils.findComponent("reverseButton", true);

        clickJButton(reverseButton);
        assertEquals(textLabel.getText(), "!gnirts eht esreveR");
    }

    // Description: Should reset the text in `textLabel` to "Reverse the string!" when `resetButton` is clicked.
    /**
     * Test to check the presence and functionality of the resetButton.
     */
    @Test
    public void shouldResetString() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        reverseButton = (JButton) TestUtils.findComponent("reverseButton", true);
        resetButton = (JButton) TestUtils.findComponent("resetButton", true);

        clickJButton(reverseButton); // Reverse the string first
        clickJButton(resetButton);
        assertEquals(textLabel.getText(), "Reverse the string!");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }

    /**
     * Helper method to simulate clicking a JButton.
     *
     * @param btn The JButton to click.
     */
    private void clickJButton(JButton btn) {
        robot().click(btn);
        robot().waitForIdle();
    }
}
