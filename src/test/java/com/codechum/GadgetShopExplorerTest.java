package com.codechum;

import com.codechum.swing.jCheckBox.GadgetShopExplorer;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the CODECHUMACTIVITY class.
 */
public class GadgetShopExplorerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JCheckBox phoneCheckBox, laptopCheckBox, headphonesCheckBox, chargerCheckBox;
    JButton calculateButton;
    JLabel totalCostLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the CODECHUMACTIVITY application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GadgetShopExplorer.class).start();
        robot().waitForIdle();
    }

    /**
     * Test to check the presence of all components including JCheckBox, JButton, and JLabel.
     */
    @Test
    public void shouldHaveAllComponents() {
        calculateButton = (JButton) TestUtils.findComponent("calculateButton", true);
        totalCostLabel = (JLabel) TestUtils.findComponent("totalCostLabel", true);
        String[] checkBoxes = {"phoneCheckBox", "laptopCheckBox", "headphonesCheckBox", "chargerCheckBox"};

        for (int i = 0; i < checkBoxes.length; i++) {
            JCheckBox checkbox = (JCheckBox) TestUtils.findComponent(checkBoxes[i], true);
            assertNotNull(checkbox, "No " + checkBoxes[i] + " found.");
        }

        assertNotNull(calculateButton, "No calculateButton found.");
        assertNotNull(totalCostLabel, "No totalCostLabel found.");
    }

    /**
     * Test to check if the label displays the correct total cost after calculating.
     */
    @Test
    public void shouldDisplayCorrectTotalCost() {
        phoneCheckBox = (JCheckBox) TestUtils.findComponent("phoneCheckBox", true);
        laptopCheckBox = (JCheckBox) TestUtils.findComponent("laptopCheckBox", true);
        headphonesCheckBox = (JCheckBox) TestUtils.findComponent("headphonesCheckBox", true);
        chargerCheckBox = (JCheckBox) TestUtils.findComponent("chargerCheckBox", true);
        calculateButton = (JButton) TestUtils.findComponent("calculateButton", true);
        totalCostLabel = (JLabel) TestUtils.findComponent("totalCostLabel", true);

        // Select Phone and Laptop, total cost should be 1500
        robot().click(phoneCheckBox);
        robot().waitForIdle();
        robot().click(laptopCheckBox);
        robot().waitForIdle();
        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(totalCostLabel.getText(), "1500.00");

        // Select Headphones and Charger, total cost should be 750
        robot().click(headphonesCheckBox);
        robot().waitForIdle();
        robot().click(chargerCheckBox);
        robot().waitForIdle();
        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(totalCostLabel.getText(), "1338.75");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
