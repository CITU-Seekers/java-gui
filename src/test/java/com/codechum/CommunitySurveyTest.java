package com.codechum;

import com.codechum.awt.layouts.CommunitySurvey;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CommunitySurveyTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Panel panel1;
    TextField nameTextField, dogNameTextField, dogBreedTextField, dogAgeTextField;
    Checkbox yesCheckBox, noCheckBox;
    Button submitButton;

    Dialog dialogNotice;
    Label messageLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CommunitySurvey.class).start();
        
        robot().waitForIdle();
    }
    
    // Description: Should have all components `nameTextField`, `yesCheckBox`, `noCheckBox`, and `submitButton`.
    @Test
    public void shouldHaveAllComponents(){
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        yesCheckBox = (Checkbox) TestUtils.findComponent("yesCheckBox", true);
        noCheckBox = (Checkbox) TestUtils.findComponent("noCheckBox", true);
        submitButton = (Button) TestUtils.findComponent("submitButton", true);
        
        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(yesCheckBox, "No yesCheckBox found.");
        assertNotNull(noCheckBox, "No noCheckBox found.");
        assertNotNull(submitButton, "No submitButton found.");
    }

    // Description: Should display `panel1` components when `yesCheckBox` is selected such as `dogNameTextField`, `dogBreedTextField`, and `dogAgeTextField`.
    @Test
    public void shouldDisplayPanel1ComponentsWhenYesCheckBoxIsSelected(){
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        yesCheckBox = (Checkbox) TestUtils.findComponent("yesCheckBox", true);
        noCheckBox = (Checkbox) TestUtils.findComponent("noCheckBox", true);
        submitButton = (Button) TestUtils.findComponent("submitButton", true);

        robot().click(yesCheckBox);
        robot().waitForIdle();

        panel1 = (Panel) TestUtils.findComponent("panel1", true);
        dogNameTextField = (TextField) TestUtils.getChildNamed(panel1, "dogNameTextField");
        dogBreedTextField = (TextField) TestUtils.getChildNamed(panel1, "dogBreedTextField");
        dogAgeTextField = (TextField) TestUtils.getChildNamed(panel1, "dogAgeTextField");

        assertNotNull(panel1, "No panel1 found.");
        assertNotNull(dogNameTextField, "No dogNameTextField found.");
        assertNotNull(dogBreedTextField, "No dogBreedTextField found.");
        assertNotNull(dogAgeTextField, "No dogAgeTextField found.");
    }

    // Description: Should display `dialogNotice` with a message "Response has been recorded!" in `messageLabel` when `submitButton` is clicked.
    @Test
    public void shouldDisplayDialogWhenSubmitButtonIsClicked(){
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        yesCheckBox = (Checkbox) TestUtils.findComponent("yesCheckBox", true);
        noCheckBox = (Checkbox) TestUtils.findComponent("noCheckBox", true);
        submitButton = (Button) TestUtils.findComponent("submitButton", true);

        robot().click(submitButton);
        robot().waitForIdle();

        dialogNotice = (Dialog) TestUtils.findComponent("dialogNotice", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);

        assertNotNull(dialogNotice, "No dialogNotice found.");
        assertNotNull(messageLabel, "No messageLabel found.");
        assertEquals(messageLabel.getText(), "Response has been recorded!");
    } 
}
