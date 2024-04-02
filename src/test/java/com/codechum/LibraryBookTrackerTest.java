package com.codechum;

import com.codechum.swing.jCheckBox.LibraryBookTracker;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the CODECHUMACTIVITY class.
 */
public class LibraryBookTrackerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JCheckBox fictionCheckBox, nonFictionCheckBox, referenceCheckBox;
    JButton calculateButton;
    JLabel lateFeeLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the CODECHUMACTIVITY application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LibraryBookTracker.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `fictionCheckBox`, `nonFictionCheckBox`, `referenceCheckBox`, `calculateButton`, and `lateFeeLabel`.
    /**
     * Test to check the presence of all components including JCheckBox, JButton, and JLabel.
     */
    @Test
    public void shouldHaveAllComponents() {
        calculateButton = (JButton) TestUtils.findComponent("calculateButton ", true);
        lateFeeLabel = (JLabel) TestUtils.findComponent("lateFeeLabel", true);
        String[] checkBoxes = {"fictionCheckBox", "nonFictionCheckBox", "referenceCheckBox"};

        for (int i = 0; i < checkBoxes.length; i++) {
            JCheckBox checkbox = (JCheckBox) TestUtils.findComponent(checkBoxes[i], true);
            assertNotNull(checkbox, "No " + checkBoxes[i] + " found.");
        }

        assertNotNull(calculateButton, "No calculateButton found.");
        assertNotNull(lateFeeLabel, "No lateFeeLabel found.");
    }

    // Description: Should display the correct late fee in `lateFeeLabel` when checkboxes are selected and `calculateButton` is clicked.
    /**
     * Test to check if the label displays the correct late fee after calculating.
     */
    @Test
    public void shouldDisplayCorrectLateFee() {
        fictionCheckBox = (JCheckBox) TestUtils.findComponent("fictionCheckBox", true);
        nonFictionCheckBox = (JCheckBox) TestUtils.findComponent("nonFictionCheckBox", true);
        referenceCheckBox = (JCheckBox) TestUtils.findComponent("referenceCheckBox", true);
        calculateButton = (JButton) TestUtils.findComponent("calculateButton ", true);
        lateFeeLabel = (JLabel) TestUtils.findComponent("lateFeeLabel", true);

        // Select all checkboxes, total fee should be 9 per day
        robot().click(fictionCheckBox);
        robot().waitForIdle();
        robot().click(nonFictionCheckBox);
        robot().waitForIdle();
        robot().click(referenceCheckBox);
        robot().waitForIdle();
        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(lateFeeLabel.getText(), "Php 9 per day");

        // Deselect one checkbox, total fee should be 8 per day
        robot().click(fictionCheckBox);
        robot().waitForIdle();
        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(lateFeeLabel.getText(), "Php 4 per day");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
