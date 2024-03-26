package com.codechum;

import com.codechum.swing.jRadioButton.SimpleQuestionnaire;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SimpleQuestionnaireTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JButton checkButton;
    JLabel resultLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SimpleQuestionnaire.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        String[] radiobuttons = {"charRadioButton", "booleanRadioButton", "intRadioButton", "stringRadioButton"};
        
        for (String checkbox : radiobuttons) {
            JRadioButton radioButtonJButton = (JRadioButton) TestUtils.findComponent(checkbox, true);
            assertNotNull(radioButtonJButton, "No " + checkbox + " found.");
        }
        
        checkButton = (JButton) TestUtils.findComponent("checkButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
        assertNotNull(checkButton, "No checkButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }
    
    @Test
    public void shouldOnlySelectOneRadioButton(){
        String[] radiobuttons = {"charRadioButton", "booleanRadioButton", "intRadioButton", "stringRadioButton"};
        JRadioButton[] rb = new JRadioButton[radiobuttons.length];
        for (int i = 0; i < radiobuttons.length; i++) {
            rb[i] = (JRadioButton) TestUtils.findComponent(radiobuttons[i], true);
        }

        for(int i = 0; i < radiobuttons.length; i++){
            robot().click(rb[i]);
            robot().waitForIdle();

            for(int j = 0; j < radiobuttons.length; j++){
                if(rb[i] != rb[j]){
                    assertFalse(rb[j].isSelected(), "The "+ rb[j].getName() +" check box should be unselected when another check box is clicked.");
                }
                else{
                    assertTrue(rb[j].isSelected(), "The "+ rb[j].getName() +" check box should be selected when clicked.");
                }
            }
        }
    }
    
    @Test
    public void shouldDisplayCorrespondingMessagesOnButtonClick() {
        String[] radiobuttons = {"charRadioButton", "booleanRadioButton", "intRadioButton", "stringRadioButton"};
        checkButton = (JButton) TestUtils.findComponent("checkButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
        for (String checkbox : radiobuttons) {
            JRadioButton radioButtonJButton = (JRadioButton) TestUtils.findComponent(checkbox, true);
            robot().click(radioButtonJButton);
            robot().waitForIdle();
            robot().click(checkButton);
            robot().waitForIdle();
            
            String msg = "Incorrect!";
            if (radiobuttons[3] == null ? radioButtonJButton.getName() == null : radiobuttons[3].equals(radioButtonJButton.getName())) {
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
