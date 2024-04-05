package com.codechum;

import com.codechum.swing.jButton.ButtonClassPracticeProblemI;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ButtonClassPracticeProblemITest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    JLabel counterLabel;
    JButton incrementButton;    
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ButtonClassPracticeProblemI.class).start();
        robot().waitForIdle();
    }
    
    public void clickButton() {
        robot().click(incrementButton);
        robot().waitForIdle();
    }

    // Description: Should have all components `counterLabel` and `incrementButton`.
    @Test
    public void shouldHaveAllComponents() {
        counterLabel = (JLabel) TestUtils.findComponent("counterLabel", true);
        incrementButton = (JButton) TestUtils.findComponent("incrementButton", true);

        assertNotNull(counterLabel, "No counterLabel found.");
        assertNotNull(incrementButton, "No incrementButton found.");
    }

    // Description: Should have a default value of 0 in `counterLabel`.
    @Test
    public void shouldHaveCorrectDefaultValue() {
        counterLabel = (JLabel) TestUtils.findComponent("counterLabel", true);
        assertNotNull(counterLabel.getText(), "0");
    }

    // Description: Should increment the value of `counterLabel` by 1 when `incrementButton` is clicked.
    @Test
    public void shouldIncrementCounterIfButtonIsClicked() {
        counterLabel = (JLabel) TestUtils.findComponent("counterLabel", true);
        incrementButton = (JButton) TestUtils.findComponent("incrementButton", true);
        
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
