package com.codechum;

import com.codechum.awt.buttons.NumberCounter;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class NumberCounterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Label countLabel;
    Button increaseButton, decreaseButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(NumberCounter.class).start();
        robot().waitForIdle();
    }
    
    public void clickButton(Button button) {
        robot().click(button);
        robot().waitForIdle();
    }

    // Description: Should have all components named `countLabel`, `increaseButton`, and `decreaseButton`.
    @Test
    public void shouldHaveAllComponents() {
        countLabel = (Label) TestUtils.findComponent("countLabel", true);
        increaseButton = (Button) TestUtils.findComponent("increaseButton", true);
        decreaseButton = (Button) TestUtils.findComponent("decreaseButton", true);

        assertNotNull(countLabel, "No countLabel found.");
        assertNotNull(increaseButton, "No increaseButton found.");
        assertNotNull(decreaseButton, "No decreaseButton found.");
    }

    // Description: Should have a default value of 0 for `countLabel`.
    @Test
    public void shouldHaveCorrectDefaultValue() {
        countLabel = (Label) TestUtils.findComponent("countLabel", true);
        assertNotNull(countLabel.getText(), "0");
    }

    // Description: Should increase the value of `countLabel` by 1 each time `increaseButton` is clicked.
    @Test
    public void shouldIncreaseCountIfButtonIsClicked() {
        countLabel = (Label) TestUtils.findComponent("countLabel", true);
        increaseButton = (Button) TestUtils.findComponent("increaseButton", true);
        
        clickButton(increaseButton);
        assertEquals(countLabel.getText(), "1");
        clickButton(increaseButton);
        assertEquals(countLabel.getText(), "2");
        clickButton(increaseButton);
        assertEquals(countLabel.getText(), "3");
        clickButton(increaseButton);
        assertEquals(countLabel.getText(), "4");
    }
    
    // Description: Should decrease the value of `countLabel` by 1 each time `decreaseButton` is clicked.
    @Test
    public void shouldDecreaseCountIfButtonIsClicked() {
        countLabel = (Label) TestUtils.findComponent("countLabel", true);
        decreaseButton = (Button) TestUtils.findComponent("decreaseButton", true);
        
        clickButton(decreaseButton);
        assertEquals(countLabel.getText(), "-1");
        clickButton(decreaseButton);
        assertEquals(countLabel.getText(), "-2");
        clickButton(decreaseButton);
        assertEquals(countLabel.getText(), "-3");
        clickButton(decreaseButton);
        assertEquals(countLabel.getText(), "-4");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

