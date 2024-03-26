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

    JCheckBox checkBoxFirst, checkBoxThird;
    JButton buttonDisplaySelected;
    JLabel labelCheckBoxes;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SelectingCheckBoxes.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        String[] components = {"checkBoxFirst", "checkBoxSecond", "checkBoxThird", "buttonDisplaySelected", "labelCheckBoxes"};
        
        for (String comp: components) {
            Component component = TestUtils.findComponent(comp, true);
            assertNotNull(component, "No " + comp + " found.");
        }
    }
    
    @Test
    public void shouldDisplayCorrectLabels() {
        checkBoxFirst = (JCheckBox) TestUtils.findComponent("checkBoxFirst", true);
        checkBoxThird = (JCheckBox) TestUtils.findComponent("checkBoxThird", true);
        buttonDisplaySelected = (JButton) TestUtils.findComponent("buttonDisplaySelected", true);
        labelCheckBoxes = (JLabel) TestUtils.findComponent("labelCheckBoxes", true);
        
        robot().click(checkBoxFirst);
        robot().waitForIdle();
        robot().click(checkBoxThird);
        robot().waitForIdle();
        
        robot().click(buttonDisplaySelected);
        robot().waitForIdle();
        
        assertEquals(labelCheckBoxes.getText(), "First, Third");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
