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

    // Description: Should have all components `smallCheckBox`, `mediumCheckBox`, `largeCheckBox`, `mainButton`, and `helperLabel`.
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

    // Description: Should have the `smallCheckBox` selected by default.
    @Test
    public void shouldBeSelectedByDefaultCheckBoxSmall() {
        smallCheckBox = (Checkbox) TestUtils.findComponent("smallCheckBox", true);
        assertTrue(smallCheckBox.getState(), "smallCheckBox should be selected by default");
    }

    // Description: Should display a helper message on `helperLabel` when mouse pointer is hovered on any check box.
    @Test
    public void shouldBeDisplayedOnHoverHelperMessage() {
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

    // Description: Should change the size of the `mainButton` to 30x80 when `smallCheckBox` is clicked.
    @Test
    public void shouldChangeSizeOnRadSmallClick() {
        simulateRadioButtonClick("smallCheckBox", 30, 80);
    }

    // Description: Should change the size of the `mainButton` to 60x160 when `mediumCheckBox` is clicked.
    @Test
    public void shouldChangeSizeOnRadMediumClick() {
        simulateRadioButtonClick("mediumCheckBox", 60, 160);
    }
    
    // Description: Should change the size of the `mainButton` to 90x240 when `largeCheckBox` is clicked.
    @Test
    public void shouldChangeSizeOnRadLargeClick() {
        simulateRadioButtonClick("largeCheckBox", 90, 240);
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
