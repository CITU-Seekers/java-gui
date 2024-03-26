package com.codechum;

import com.codechum.swing.jButton.CaseManipulator;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the JButton1 class.
 */
public class CaseManipulatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    JLabel textLabel;
    JButton toUpperCaseButton, toLowerCaseButton;

    /**
     * Set up method to register an EmergencyAbortListener and start the JButton1 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CaseManipulator.class).start();
        robot().waitForIdle();
    }

    /**
     * Test to check the initial state of the text label.
     */
    @Test
    public void shouldHaveInitialTextLabel() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        assertNotNull(textLabel, "No textLabel found.");
        assertEquals(textLabel.getText(), "Unlock the Power of Strings!");
    }

    /**
     * Test to check the presence and functionality of the toUpperCaseButton.
     */
    @Test
    public void shouldConvertToUpperCase() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        toUpperCaseButton = (JButton) TestUtils.findComponent("toUpperCaseButton", true);

        clickJButton(toUpperCaseButton);
        assertEquals(textLabel.getText(), "UNLOCK THE POWER OF STRINGS!");
    }

    /**
     * Test to check the presence and functionality of the toLowerCaseButton.
     */
    @Test
    public void shouldConvertToLowerCase() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        toLowerCaseButton = (JButton) TestUtils.findComponent("toLowerCaseButton", true);

        clickJButton(toLowerCaseButton);
        assertEquals(textLabel.getText(), "unlock the power of strings!");
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
