package com.codechum;

import com.codechum.awt.choice.FishPriceOfTheDay;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FishPriceOfTheDayTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Choice dayChoice;
    TextField amountTextField;
    Button calculateButton;
    Label priceLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FishPriceOfTheDay.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `dayChoice`, `amountTextField`, `calculateButton`, and `priceLabel`.
    @Test
    public void shouldHaveAllComponents() {
        dayChoice = (Choice) TestUtils.findComponent("dayChoice", true);
        amountTextField = (TextField) TestUtils.findComponent("amountTextField", true);
        calculateButton = (Button) TestUtils.findComponent("calculateButton", true);
        priceLabel = (Label) TestUtils.findComponent("priceLabel", true);

        assertNotNull(dayChoice, "No dayChoice found.");
        assertNotNull(amountTextField, "No amountTextField found.");
        assertNotNull(calculateButton, "No calculateButton found.");
        assertNotNull(priceLabel, "No priceLabel found.");
    }

    // Description: Should calculate the price of fish for "Monday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForMonday() {
        calculatePrice("Monday", 5, 1250);
    }
    
    // Description: Should calculate the price of fish for "Tuesday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForTuesday() {
        calculatePrice("Tuesday", 3, 600);
    }

    // Description: Should calculate the price of fish for "Wednesday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForWednesday() {
        calculatePrice("Wednesday", 3, 450);
    }
    
    // Description: Should calculate the price of fish for "Thursday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForThursday() {
        calculatePrice("Thursday", 3, 450);
    }
    
    // Description: Should calculate the price of fish for "Friday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForFriday() {
        calculatePrice("Friday", 3, 600);
    }
    
    // Description: Should calculate the price of fish for "Saturday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForSaturday() {
        calculatePrice("Saturday", 3, 900);
    }

    // Description: Should calculate the price of fish for "Sunday" and display it in `priceLabel` when `calculateButton` is clicked.
    @Test
    public void shouldCalculatePriceForSunday() {
        calculatePrice("Sunday", 2, 600);
    }

    private void calculatePrice(String day, int amount, int expectedPrice) {
        dayChoice = (Choice) TestUtils.findComponent("dayChoice", true);
        amountTextField = (TextField) TestUtils.findComponent("amountTextField", true);
        calculateButton = (Button) TestUtils.findComponent("calculateButton", true);
        priceLabel = (Label) TestUtils.findComponent("priceLabel", true);

        dayChoice.select(day);
        robot().click(amountTextField);
        robot().enterText(String.valueOf(amount));

        robot().click(calculateButton);
        robot().waitForIdle();

        assertEquals(Integer.parseInt(priceLabel.getText()), expectedPrice);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

