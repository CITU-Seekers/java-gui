package com.codechum;

import com.codechum.awt.buttons.PlusButton;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class PlusButtonTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Label counterLabel;
    Button incrementButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PlusButton.class).start();
        robot().waitForIdle();
    }
    
    public void clickButton() {
        robot().click(incrementButton);
        robot().waitForIdle();
    }

    // Description: Should have all components named `counterLabel` and `incrementButton`.
    @Test
    public void shouldHaveAllComponents() {
        counterLabel = (Label) TestUtils.findComponent("counterLabel", true);
        incrementButton = (Button) TestUtils.findComponent("incrementButton", true);

        assertNotNull(counterLabel, "No counterLabel found.");
        assertNotNull(incrementButton, "No incrementButton found.");
    }

    // Description: Should have a default value of 0 for `counterLabel`.
    @Test
    public void shouldHaveCorrectDefaultValue() {
        counterLabel = (Label) TestUtils.findComponent("counterLabel", true);
        assertNotNull(counterLabel.getText(), "0");
    }

    // Description: Should increment the value of `counterLabel` by 1 each time `incrementButton` is clicked.
    @Test
    public void shouldIncrementCounterIfButtonIsClicked() {
        counterLabel = (Label) TestUtils.findComponent("counterLabel", true);
        incrementButton = (Button) TestUtils.findComponent("incrementButton", true);
        
        clickButton();
        assertEquals(counterLabel.getText(), "1");
        clickButton();
        assertEquals(counterLabel.getText(), "2");
        clickButton();
        assertEquals(counterLabel.getText(), "3");
        clickButton();
        assertEquals(counterLabel.getText(), "4");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

