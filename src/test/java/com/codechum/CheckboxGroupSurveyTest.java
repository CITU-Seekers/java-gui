package com.codechum;

import com.codechum.awt.checkBoxGroup.CheckboxGroupSurvey;

import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CheckboxGroupSurveyTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;
    
    Checkbox maleCheckbox, femaleCheckbox, javaCheckbox, pythonCheckbox, cppCheckbox, csharpCheckbox, javascriptCheckbox;
    Label resultLabel;
    Button submitButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CheckboxGroupSurvey.class).start();
    }
    
    // Description: Should have all components `maleCheckbox`, `femaleCheckbox`, `javaCheckbox`, `pythonCheckbox`, `cppCheckbox`, `csharpCheckbox`, `javascriptCheckbox`, `resultLabel`, and `submitButton`.
    @Test
    public void shouldHaveAllComponents() {
        robot().waitForIdle(); 
        
        maleCheckbox = (Checkbox) TestUtils.findComponent("maleCheckbox", true);
        femaleCheckbox = (Checkbox) TestUtils.findComponent("femaleCheckbox", true);
        javaCheckbox = (Checkbox) TestUtils.findComponent("javaCheckbox", true);
        pythonCheckbox = (Checkbox) TestUtils.findComponent("pythonCheckbox", true);
        cppCheckbox = (Checkbox) TestUtils.findComponent("cppCheckbox", true);
        csharpCheckbox = (Checkbox) TestUtils.findComponent("csharpCheckbox", true);
        javascriptCheckbox = (Checkbox) TestUtils.findComponent("javascriptCheckbox", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        submitButton = (Button) TestUtils.findComponent("submitButton", true);

        assertNotNull(maleCheckbox, "No maleCheckbox found.");
        assertNotNull(femaleCheckbox, "No femaleCheckbox found.");
        assertNotNull(javaCheckbox, "No javaCheckbox found.");
        assertNotNull(pythonCheckbox, "No pythonCheckbox found.");
        assertNotNull(cppCheckbox, "No cppCheckbox found.");
        assertNotNull(csharpCheckbox, "No csharpCheckbox found.");
        assertNotNull(javascriptCheckbox, "No javascriptCheckbox found.");
        assertNotNull(resultLabel, "No resultLabel found.");
        assertNotNull(submitButton, "No submitButton found.");
    }
    
    // Description: Should only select one gender checkbox between `maleCheckbox` and `femaleCheckbox`.
    @Test 
    public void shouldOnlySelectOneRadioButtonInGenderCheckboxGroup(){
        String[] checkboxes = {"maleCheckbox", "femaleCheckbox"};
        Checkbox[] rb = new Checkbox[checkboxes.length];
        for (int i = 0; i < checkboxes.length; i++) {
            rb[i] = (Checkbox) TestUtils.findComponent(checkboxes[i], true);
        }

        for(int i = 0; i < checkboxes.length; i++){
            robot().click(rb[i]);
            robot().waitForIdle();

            for(int j = 0; j < checkboxes.length; j++){
                if(rb[i] != rb[j]){
                    assertFalse(rb[j].getState(), "The "+ rb[j].getName() +" radio button should be unselected when another radio button is clicked.");
                }
                else{
                    assertTrue(rb[j].getState(), "The "+ rb[j].getName() +" radio button should be selected when clicked.");
                }
            }
        }
    }

    // Description: Should only select one programming checkbox between `javaCheckbox`, `pythonCheckbox`, `cppCheckbox`, `csharpCheckbox`, and `javascriptCheckbox`.
    @Test
    public void shouldOnlySelectOneCheckboxInProgrammingCheckboxGroup(){
        String[] checkboxes = {"javaCheckbox", "pythonCheckbox", "cppCheckbox", "csharpCheckbox", "javascriptCheckbox"};
        Checkbox[] cb = new Checkbox[checkboxes.length];
        for (int i = 0; i < checkboxes.length; i++) {
            cb[i] = (Checkbox) TestUtils.findComponent(checkboxes[i], true);
        }

        for(int i = 0; i < checkboxes.length; i++){
            robot().click(cb[i]);
            robot().waitForIdle();

            for(int j = 0; j < checkboxes.length; j++){
                if(cb[i] != cb[j]){
                    assertFalse(cb[j].getState(), "The "+ cb[j].getName() +" checkbox should be unselected when another checkbox is clicked.");
                }
                else{
                    assertTrue(cb[j].getState(), "The "+ cb[j].getName() +" checkbox should be selected when clicked.");
                }
            }
        }
    }

    // Description: Should display "Your response has been recorded." in `resultLabel` when `submitButton` is clicked.
    @Test
    public void shouldDisplayCorrectMessageLabel(){
        maleCheckbox = (Checkbox) TestUtils.findComponent("maleCheckbox", true);
        femaleCheckbox = (Checkbox) TestUtils.findComponent("femaleCheckbox", true);
        javaCheckbox = (Checkbox) TestUtils.findComponent("javaCheckbox", true);
        pythonCheckbox = (Checkbox) TestUtils.findComponent("pythonCheckbox", true);
        cppCheckbox = (Checkbox) TestUtils.findComponent("cppCheckbox", true);
        csharpCheckbox = (Checkbox) TestUtils.findComponent("csharpCheckbox", true);
        javascriptCheckbox = (Checkbox) TestUtils.findComponent("javascriptCheckbox", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        submitButton = (Button) TestUtils.findComponent("submitButton", true);

        robot().click(maleCheckbox);
        robot().click(javaCheckbox);
        robot().click(submitButton);
        robot().waitForIdle();

        assertEquals(resultLabel.getText(), "Your response has been recorded.");
    } 
}


