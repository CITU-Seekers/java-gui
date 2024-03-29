package com.codechum;

import com.codechum.swing.jCheckBox.SelectingCheckBoxes;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SelectingCheckBoxesTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JCheckBox firstCheckBox, thirdCheckBox;
    JButton displaySelectedButton;
    JLabel checkBoxesLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SelectingCheckBoxes.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components named `firstCheckBox`, `secondCheckBox`, `thirdCheckBox`, `displaySelectedButton`, and `checkBoxesLabel`.
    @Test
    public void shouldHaveAllComponents() {
        String[] components = {"firstCheckBox", "secondCheckBox", "thirdCheckBox", "displaySelectedButton", "checkBoxesLabel"};
        
        for (String comp: components) {
            Component component = TestUtils.findComponent(comp, true);
            assertNotNull(component, "No " + comp + " found.");
        }
    }
    
    // Description: Should display the correct label in `checkBoxesLabel` when checkboxes are selected and `displaySelectedButton` is clicked.
    @Test
    public void shouldDisplayCorrectLabels() {
        firstCheckBox = (JCheckBox) TestUtils.findComponent("firstCheckBox", true);
        thirdCheckBox = (JCheckBox) TestUtils.findComponent("thirdCheckBox", true);
        displaySelectedButton = (JButton) TestUtils.findComponent("displaySelectedButton", true);
        checkBoxesLabel = (JLabel) TestUtils.findComponent("checkBoxesLabel", true);
        
        robot().click(firstCheckBox);
        robot().waitForIdle();
        robot().click(thirdCheckBox);
        robot().waitForIdle();
        
        robot().click(displaySelectedButton);
        robot().waitForIdle();
        
        assertEquals(checkBoxesLabel.getText(), "First, Third");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
