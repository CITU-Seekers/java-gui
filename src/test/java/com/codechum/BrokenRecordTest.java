package com.codechum;

import com.codechum.awt.textArea.BrokenRecord;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class BrokenRecordTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField stringTextField, numberTextField;
    TextArea resultTextArea;
    Button displayTextButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(BrokenRecord.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components named `stringTextField`, `numberTextField`, `displayTextButton`, and `resultTextArea`.
    @Test
    public void shouldHaveAllComponents() {
        stringTextField = (TextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (Button) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        assertNotNull(stringTextField, "No stringTextField found.");
        assertNotNull(numberTextField, "No numberTextField found.");
        assertNotNull(displayTextButton, "No displayTextButton found.");
        assertNotNull(resultTextArea, "No resultTextArea found.");
    }

    // Description: Should check if `resultTextArea`, `stringTextField`, and `numberTextField` display text is empty by default.
    @Test
    public void shouldCheckTextFieldAndTextAreaDefaultValue() {
        stringTextField = (TextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        assertEquals(resultTextArea.getText(), "");
        assertEquals(stringTextField.getText(), "");
        assertEquals(numberTextField.getText(), "");
    }

    // Description: Should display the text in `stringTextField` n times of `numberTextField` in `resultTextArea` when `displayTextButton` is clicked.
    @Test
    public void shouldDisplayTextNTimes(){
        stringTextField = (TextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (Button) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        robot().click(stringTextField);
        robot().enterText("CodeChum");
        robot().click(numberTextField);
        robot().enterText("3");
        robot().click(displayTextButton);
        robot().waitForIdle();

        assertEquals(resultTextArea.getText(), "CodeChum CodeChum CodeChum");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
