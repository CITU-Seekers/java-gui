package com.codechum;

import com.codechum.swing.jCheckBox.FoodKiosk;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FoodKioskTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JCheckBox pizzaCheckBox, burgerCheckBox, teaCheckBox;
    JButton orderButton;
    JLabel totalCostLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FoodKiosk.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `pizzaCheckBox`, `burgerCheckBox`, `teaCheckBox`, `orderButton`, and `totalCostLabel`.
    @Test
    public void shouldHaveAllComponents() {
        orderButton = (JButton) TestUtils.findComponent("orderButton", true);
        totalCostLabel = (JLabel) TestUtils.findComponent("totalCostLabel", true);
        String[] checkBoxes = {"pizzaCheckBox", "burgerCheckBox", "teaCheckBox"};

        for (int i = 0; i < checkBoxes.length; i++) {
            JCheckBox checkbox = (JCheckBox) TestUtils.findComponent(checkBoxes[i], true);
            assertNotNull(checkbox, "No "+ checkBoxes[i] + " found.");
        }

        assertNotNull(orderButton, "No orderButton found.");
        assertNotNull(totalCostLabel, "No totalCostLabel found.");
    }

    // Description: Should display the correct price in `totalCostLabel` when checkboxes are selected and `orderButton` is clicked.
    @Test
    public void shouldDisplayCorrectPriceInLabel(){
        pizzaCheckBox = (JCheckBox) TestUtils.findComponent("pizzaCheckBox", true);
        burgerCheckBox = (JCheckBox) TestUtils.findComponent("burgerCheckBox", true);
        teaCheckBox= (JCheckBox) TestUtils.findComponent("teaCheckBox", true);
        orderButton = (JButton) TestUtils.findComponent("orderButton", true);
        totalCostLabel = (JLabel) TestUtils.findComponent("totalCostLabel", true);

        robot().click(pizzaCheckBox);
        robot().waitForIdle();
        robot().click(burgerCheckBox);
        robot().waitForIdle();
        robot().click(orderButton);
        robot().waitForIdle();         // (100 + 80) = 180.00

        assertEquals(totalCostLabel.getText(), "180.00");

        robot().click(pizzaCheckBox);
        robot().waitForIdle();

        robot().click(teaCheckBox);
        robot().waitForIdle();
        robot().click(orderButton);
        robot().waitForIdle();        // (80 + 50) = 130

        assertEquals(totalCostLabel.getText(), "130.00");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
