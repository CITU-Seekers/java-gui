package com.codechum;

import com.codechum.awt.eventClasses.ItemEventActivity;
import static org.testng.Assert.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class ItemEventActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Checkbox checkbox1, checkbox2, checkbox3;
    Label statusLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ItemEventActivity.class).start();
    }
    
    // Description: Should have all components `checkbox1`, `checkbox2`, `checkbox3` and `statusLabel`.
    @Test
    public void shouldHaveAllComponents(){
        checkbox1 = (Checkbox) TestUtils.findComponent("checkbox1", true);
        checkbox2 = (Checkbox) TestUtils.findComponent("checkbox2", true);
        checkbox3 = (Checkbox) TestUtils.findComponent("checkbox3", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        assertNotNull(checkbox1);
        assertNotNull(checkbox2);
        assertNotNull(checkbox3);
        assertNotNull(statusLabel);
    }

    // Description: Should display correct status in `statusLabel` based on the checkboxes selected.
    @Test
    public void shouldDisplayCorrectStatus(){
        checkbox1 = (Checkbox) TestUtils.findComponent("checkbox1", true);
        checkbox2 = (Checkbox) TestUtils.findComponent("checkbox2", true);
        checkbox3 = (Checkbox) TestUtils.findComponent("checkbox3", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(checkbox1);
        assertEquals(statusLabel.getText(), "Checkbox 1 selected");
        robot().click(checkbox2);
        assertEquals(statusLabel.getText(), "Checkbox 1 and 2 selected");
        robot().click(checkbox3);
        assertEquals(statusLabel.getText(), "Checkbox 1, 2 and 3 selected");
        robot().click(checkbox1);
        assertEquals(statusLabel.getText(), "Checkbox 2 and 3 selected");
        robot().click(checkbox2);
        assertEquals(statusLabel.getText(), "Checkbox 3 selected");
    }
    
}
