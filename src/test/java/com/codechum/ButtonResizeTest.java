package com.codechum;

import com.codechum.awt.eventAdapters.ButtonResize;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.testng.Assert.*;

import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ButtonResizeTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox smallCheckBox, mediumCheckBox, largeCheckBox;
    Label helperLabel;
    Button mainButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ButtonResize.class).start();
    }

    public void simulateRadioButtonClick(String checkBoxName, int expectedBtnHeight, int expectedBtnWidth) {
        mainButton = (Button) TestUtils.findComponent("mainButton", true);
        Checkbox checkBox = (Checkbox) TestUtils.findComponent(checkBoxName, true);
        
        robot().click(checkBox);
        robot().waitForIdle();
        
        String buttonSize = "height: " + mainButton.getHeight() + ", width: " + mainButton.getWidth();
        assertEquals(buttonSize, "height: " +  expectedBtnHeight + ", width: " + expectedBtnWidth);
    }

    @Test
    public void shouldHaveAllComponents() {
        String[] checkBoxioButtons = {"smallCheckBox", "mediumCheckBox", "largeCheckBox"};
        mainButton = (Button) TestUtils.findComponent("mainButton", true);
        helperLabel = (Label) TestUtils.findComponent("helperLabel", true);

        for (String checkBox : checkBoxioButtons) {
            Checkbox checkBoxButton = (Checkbox) TestUtils.findComponent(checkBox, true);
            assertNotNull(checkBoxButton, "No " + checkBox + " found.");
        }
        
        assertNotNull(mainButton, "No mainButton found.");
        assertNotNull(helperLabel, "No helperLabel found.");
    }

    @Test
    public void checkBoxSmallShouldBeSelectedByDefault() {
        smallCheckBox = (Checkbox) TestUtils.findComponent("smallCheckBox", true);
        assertTrue(smallCheckBox.getState(), "smallCheckBox should be selected by default");
    }

    @Test
    public void helperMessageShouldBeDisplayedOnHover() {
        smallCheckBox = (Checkbox) TestUtils.findComponent("smallCheckBox", true);
        mediumCheckBox = (Checkbox) TestUtils.findComponent("mediumCheckBox", true);
        largeCheckBox = (Checkbox) TestUtils.findComponent("largeCheckBox", true);
        helperLabel = (Label) TestUtils.findComponent("helperLabel", true);

        robot().moveMouse(smallCheckBox);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on smallCheckBox");

        robot().moveMouse(mediumCheckBox);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on mediumCheckBox");

        robot().moveMouse(largeCheckBox);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on largeCheckBox");
    }

    @Test
    public void shouldChangeSizeOnRadSmallClick() {
        simulateRadioButtonClick("smallCheckBox", 30, 80);
    }

    @Test
    public void shouldChangeSizeOnRadMediumClick() {
        simulateRadioButtonClick("mediumCheckBox", 60, 160);
    }

    @Test
    public void shouldChangeSizeOnRadLargeClick() {
        simulateRadioButtonClick("largeCheckBox", 90, 240);
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
