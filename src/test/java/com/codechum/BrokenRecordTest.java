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
    
    TextField textField, numberTextField;
    TextArea resultTextArea;
    Button displayTextButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(BrokenRecord.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (Button) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        assertNotNull(textField, "No textField found.");
        assertNotNull(numberTextField, "No numberTextField found.");
        assertNotNull(displayTextButton, "No displayTextButton found.");
        assertNotNull(resultTextArea, "No resultTextArea found.");
    }

    @Test
    public void checkTextFieldAndTextAreaDefaultValue() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        assertEquals(resultTextArea.getText(), "");
        assertEquals(textField.getText(), "");
        assertEquals(numberTextField.getText(), "");
    }

    @Test
    public void shouldDisplayTextNTimes(){
        textField = (TextField) TestUtils.findComponent("textField", true);
        numberTextField = (TextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (Button) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (TextArea) TestUtils.findComponent("resultTextArea", true);
        robot().click(textField);
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
