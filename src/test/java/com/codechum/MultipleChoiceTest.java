package com.codechum;

import com.codechum.awt.checkBoxGroup.MultipleChoice;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MultipleChoiceTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Button checkButton;
    Label resultLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MultipleChoice.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components `charCheckBox`, `booleanCheckBox`, `intCheckBox`, `stringCheckBox`, `checkButton`, and `resultLabel`.
    @Test
    public void shouldHaveAllComponents() {
        String[] checkboxes = {"charCheckBox", "booleanCheckBox", "intCheckBox", "stringCheckBox"};
        
        for (String checkbox : checkboxes) {
            Checkbox checkBoxButton = (Checkbox) TestUtils.findComponent(checkbox, true);
            assertNotNull(checkBoxButton, "No " + checkbox + " found.");
        }
        
        checkButton = (Button) TestUtils.findComponent("checkButton", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        assertNotNull(checkButton, "No checkButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }
    
    // Description: Should only select one checkbox at a time.
    @Test
    public void shouldOnlySelectOneRadioButton(){
        String[] checkboxes = {"charCheckBox", "booleanCheckBox", "intCheckBox", "stringCheckBox"};
        Checkbox[] rb = new Checkbox[checkboxes.length];
        for (int i = 0; i < checkboxes.length; i++) {
            rb[i] = (Checkbox) TestUtils.findComponent(checkboxes[i], true);
        }

        for(int i = 0; i < checkboxes.length; i++){
            robot().click(rb[i]);
            robot().waitForIdle();

            for(int j = 0; j < checkboxes.length; j++){
                if(rb[i] != rb[j]){
                    assertFalse(rb[j].getState(), "The "+ rb[j].getName() +" check box should be unselected when another check box is clicked.");
                }
                else{
                    assertTrue(rb[j].getState(), "The "+ rb[j].getName() +" check box should be selected when clicked.");
                }
            }
        }
    }
    
    // Description: Should display "Correct!" or "Incorrect!" in `resultLabel` depending on what check box is selected when `checkButton` is clicked.
    @Test
    public void shouldDisplayCorrespondingMessagesOnButtonClick() {
        String[] checkboxes = {"charCheckBox", "booleanCheckBox", "intCheckBox", "stringCheckBox"};
        checkButton = (Button) TestUtils.findComponent("checkButton", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        for (String checkbox : checkboxes) {
            Checkbox checkBoxButton = (Checkbox) TestUtils.findComponent(checkbox, true);
            robot().click(checkBoxButton);
            robot().waitForIdle();
            robot().click(checkButton);
            robot().waitForIdle();
            
            String msg = "Incorrect!";
            if (checkboxes[3] == null ? checkBoxButton.getName() == null : checkboxes[3].equals(checkBoxButton.getName())) {
                msg = "Correct!";
            }
            
            assertEquals(resultLabel.getText(), msg);
        }
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

