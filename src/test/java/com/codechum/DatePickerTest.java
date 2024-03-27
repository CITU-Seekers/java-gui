package com.codechum;

import com.codechum.awt.scrollBar.DatePicker;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class DatePickerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Scrollbar monthScrollBar, dateScrollBar;
    Label monthLabel, dateLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DatePicker.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components `monthScrollBar`, `dateScrollBar`, `monthLabel` and `dateLabel`.
    @Test
    public void shouldHaveAllComponents(){
        monthScrollBar = (Scrollbar) TestUtils.findComponent("monthScrollBar", true);
        dateScrollBar = (Scrollbar) TestUtils.findComponent("dateScrollBar", true);
        monthLabel = (Label) TestUtils.findComponent("monthLabel", true);
        dateLabel = (Label) TestUtils.findComponent("dateLabel", true);

        assertNotNull(monthScrollBar, "No monthScrollBar found");
        assertNotNull(dateScrollBar, "No dateScrollBar found");
        assertNotNull(monthLabel, "No monthLabel found");
        assertNotNull(dateLabel, "No dateLabel found");
    }

    // Description: Should display correct date in `monthLabel` and `dateLabel` when `monthScrollBar` and `dateScrollBar` are moved.
    @Test
    public void shouldDisplayCorrectDate(){
        monthScrollBar = (Scrollbar) TestUtils.findComponent("monthScrollBar", true);
        dateScrollBar = (Scrollbar) TestUtils.findComponent("dateScrollBar", true);
        monthLabel = (Label) TestUtils.findComponent("monthLabel", true);
        dateLabel = (Label) TestUtils.findComponent("dateLabel", true);

        robot().click(monthScrollBar);
        robot().pressAndReleaseKeys(VK_LEFT,VK_LEFT,VK_RIGHT);

        robot().click(dateScrollBar);
        robot().pressAndReleaseKeys(VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT);

        int monthValue = monthScrollBar.getValue();
        int dateValue = dateScrollBar.getValue();

        String monthName = "";
        switch(monthValue){
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        assertEquals(monthLabel.getText(), monthName);
        assertEquals(dateLabel.getText(), Integer.toString(dateValue));
    }
    
}

