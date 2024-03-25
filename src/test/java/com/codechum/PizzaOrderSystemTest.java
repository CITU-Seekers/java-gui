package com.codechum;

import com.codechum.awt.choice.PizzaOrderSystem;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class PizzaOrderSystemTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Choice pizzaSizeChoice;
    Choice pizzaToppingsChoice;
    Choice extraCheeseChoice;
    Button calculateButton;
    Label totalLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PizzaOrderSystem.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        pizzaSizeChoice = (Choice) TestUtils.findComponent("pizzaSizeCheckBox", true);
        pizzaToppingsChoice = (Choice) TestUtils.findComponent("pizzaTopppingsCheckBox", true);
        extraCheeseChoice = (Choice) TestUtils.findComponent("extraCheeseCheckBox", true);
        calculateButton = (Button) TestUtils.findComponent("calculateButton", true);
        totalLabel = (Label) TestUtils.findComponent("totalLabel", true);

        assertNotNull(pizzaSizeChoice, "No pizzaSizeChoice found.");
        assertNotNull(pizzaToppingsChoice, "No pizzaToppingsChoice found.");
        assertNotNull(extraCheeseChoice, "No extraCheeseChoice found.");
        assertNotNull(calculateButton, "No calculateButton found.");
        assertNotNull(totalLabel, "No totalLabel found.");
    }

    @Test
    public void shouldCalculateTotalForSmallPizzaWithNoExtraCheese() {
        calculateTotal("Small", "Mushrooms", "No", 12);
    }
    
    @Test
    public void shouldCalculateTotalForSmallPizzaWithToppingsAndExtraCheese() {
        calculateTotal("Small", "Mushrooms", "Yes", 15);
    }
    
    @Test
    public void shouldCalculateTotalForMediumPizzaWithToppingsAndExtraCheese() {
        calculateTotal("Large", "Pepperoni", "Yes", 25);
    }

    @Test
    public void shouldCalculateTotalForLargePizzaWithToppingsAndExtraCheese() {
        calculateTotal("Large", "Onions", "Yes", 25);
    }

    private void calculateTotal(String size, String toppings, String extraCheese, int expectedTotal) {
        pizzaSizeChoice = (Choice) TestUtils.findComponent("pizzaSizeCheckBox", true);
        pizzaToppingsChoice = (Choice) TestUtils.findComponent("pizzaTopppingsCheckBox", true);
        extraCheeseChoice = (Choice) TestUtils.findComponent("extraCheeseCheckBox", true);
        calculateButton = (Button) TestUtils.findComponent("calculateButton", true);
        totalLabel = (Label) TestUtils.findComponent("totalLabel", true);

        pizzaSizeChoice.select(size);

        if (toppings != null) {
            pizzaToppingsChoice.select(toppings);
        }

        if (extraCheese != null) {
             extraCheeseChoice.select(extraCheese);
        }

        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(Integer.parseInt(totalLabel.getText()), expectedTotal);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

