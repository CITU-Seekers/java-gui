package com.codechum;

import com.codechum.swing.swingEventClasses.ActionEventActivity;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingActionEventActivityTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;

    JTextField textField;
    JLabel countLabel;
    JButton button1, button2;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ActionEventActivity.class).start();
    }
    
    // Description: Should have all components `textField`, `countLabel`, `button1`, and `button2`.
    @Test
    public void shouldHaveAllComponents() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        countLabel = (JLabel) TestUtils.findComponent("countLabel", true);
        button1 = (JButton) TestUtils.findComponent("button1", true);
        button2 = (JButton) TestUtils.findComponent("button2", true);

        assertNotNull(textField, "No textField found.");
        assertNotNull(countLabel, "No countLabel found.");
        assertNotNull(button1, "No button1 found.");
        assertNotNull(button2, "No button2 found.");
    }

    // Description: Should update `textField` text value to "Button 1 is Clicked!" and increment `countLabel` by 1 on `button1` click.
    @Test
    public void shouldUpdateTextFieldAndLabelOnButton1Click() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        countLabel = (JLabel) TestUtils.findComponent("countLabel", true);
        button1 = (JButton) TestUtils.findComponent("button1", true);
       
       robot().click(button1);

       assertEquals(textField.getText(), "Button 1 is Clicked!");
       assertEquals(countLabel.getText(), "1");
    }

    // Description: Should update `textField` text value to "Button 2 is Clicked!" and increment `countLabel` by 1 on `button2` click.
    @Test
    public void shouldUpdateTextFieldAndLabelOnButton2Click() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        countLabel = (JLabel) TestUtils.findComponent("countLabel", true);
        button2 = (JButton) TestUtils.findComponent("button2", true);
       
        robot().click(button2);

       assertEquals(textField.getText(), "Button 2 is Clicked!");
       assertEquals(countLabel.getText(), "1");
    }
    
    @Test
    public void shouldShowMessageDialogOnButton1Click() {
        button1 = (JButton) TestUtils.findComponent("button1", true);
       
        robot().click(button1);
    
        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Button 1 is Clicked!");
    }
    
    // Description: Should show message dialog "Button 2 is Clicked!" on `button2` click.
     @Test
     public void shouldShowMessageDialogOnButton2Click() {
         button2 = (JButton) TestUtils.findComponent("button2", true);
        robot().click(button2);
        
        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Button 2 is Clicked!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
