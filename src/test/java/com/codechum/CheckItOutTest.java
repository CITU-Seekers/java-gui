package com.codechum;

import com.codechum.awt.checkBox.CheckItOut;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CheckItOutTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox checkBoxFirst, checkBoxThird;
    Button buttonDisplaySelected;
    Label labelCheckBoxes;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CheckItOut.class).start();
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
        checkBoxFirst = (Checkbox) TestUtils.findComponent("checkBoxFirst", true);
        checkBoxThird = (Checkbox) TestUtils.findComponent("checkBoxThird", true);
        buttonDisplaySelected = (Button) TestUtils.findComponent("buttonDisplaySelected", true);
        labelCheckBoxes = (Label) TestUtils.findComponent("labelCheckBoxes", true);
        
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

