package com.codechum;

import com.codechum.awt.dialog.February29;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class February29Test extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Dialog leapYearDialog, notLeapYearDialog;
    Label leapYearLabel, notLeapYearLabel;
    TextField yearTextField;
    Button checkYearButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(February29.class).start();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        yearTextField = (TextField) TestUtils.findComponent("yearTextField", true);
        checkYearButton = (Button) TestUtils.findComponent("checkYearButton", true);
        
        assertNotNull(yearTextField, "No yearTextField found.");
        assertNotNull(checkYearButton, "No checkYearButton found.");
    }
    
    @Test
    public void shouldShowDialogForLeapYearOnBtnCheckYearClick() {
        yearTextField = (TextField) TestUtils.findComponent("yearTextField", true);
        checkYearButton = (Button) TestUtils.findComponent("checkYearButton", true);
        
        robot().click(yearTextField);
        robot().enterText("2000");
        robot().click(checkYearButton);
        robot().waitForIdle();
        
        leapYearDialog = (Dialog) TestUtils.findComponent("leapYearDialog", true);
        leapYearLabel = (Label) TestUtils.findComponent("leapYearLabel", true);
        
        assertTrue(leapYearDialog.isVisible(), "The leapYearDialog should be visible");
        assertEquals(leapYearLabel.getText(), "Leap year");
    }
    
    @Test
    public void shouldShowDialogForNotALeapYearOnBtnCheckYearClick(){
        yearTextField = (TextField) TestUtils.findComponent("yearTextField", true);
        checkYearButton = (Button) TestUtils.findComponent("checkYearButton", true);
        
        robot().click(yearTextField);
        robot().enterText("1999");
        robot().click(checkYearButton);
        robot().waitForIdle();
        
        notLeapYearDialog = (Dialog) TestUtils.findComponent("notLeapYearDialog", true);
        notLeapYearLabel = (Label) TestUtils.findComponent("notLeapYearLabel", true);
        
        assertTrue(notLeapYearDialog.isVisible(), "The notLeapYearDialog should be visible");
        assertEquals(notLeapYearLabel.getText(), "Not a leap year");
    }
}

