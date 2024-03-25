package com.codechum;

import com.codechum.awt.checkBox.StartingMyFoodBusiness;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class StartingMyFoodBusinessTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox pizzaCheckBox, burgerCheckBox, teaCheckBox;
    Button orderButton;
    Label totalCostLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(StartingMyFoodBusiness.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        orderButton = (Button) TestUtils.findComponent("orderButton", true);
        totalCostLabel = (Label) TestUtils.findComponent("totalCostLabel", true);
        String[] checkBoxes = {"pizzaCheckBox", "burgerCheckBox", "teaCheckBox"};

        for (int i = 0; i < checkBoxes.length; i++) {
            Checkbox checkbox = (Checkbox) TestUtils.findComponent(checkBoxes[i], true);
            assertNotNull(checkbox, "No "+ checkBoxes[i] + " found.");
        }

        assertNotNull(orderButton, "No orderButton found.");
        assertNotNull(totalCostLabel, "No totalCostLabel found.");
    }

    @Test
    public void shouldDisplayCorrectPriceInLabel(){
        pizzaCheckBox = (Checkbox) TestUtils.findComponent("pizzaCheckBox", true);
        burgerCheckBox = (Checkbox) TestUtils.findComponent("burgerCheckBox", true);
        teaCheckBox= (Checkbox) TestUtils.findComponent("teaCheckBox", true);
        orderButton = (Button) TestUtils.findComponent("orderButton", true);
        totalCostLabel = (Label) TestUtils.findComponent("totalCostLabel", true);

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

