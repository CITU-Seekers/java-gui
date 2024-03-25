package com.codechum;

import com.codechum.awt.textField.StringConcatenator;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class StringConcatenatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField textField1, textField2;
    Button concatenateButton;
    Label resultLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(StringConcatenator.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        textField1 = (TextField) TestUtils.findComponent("textField1", true);
        textField2 = (TextField) TestUtils.findComponent("textField2", true);
        concatenateButton = (Button) TestUtils.findComponent("concatenateButton", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);

        assertNotNull(textField1, "No textField1 found.");
        assertNotNull(textField2, "No textField2 found.");
        assertNotNull(concatenateButton, "No Concatenate button found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }

    @Test
    public void shouldConcatenateStringsOnButtonClick() {
        textField1 = (TextField) TestUtils.findComponent("textField1", true);
        textField2 = (TextField) TestUtils.findComponent("textField2", true);
        concatenateButton = (Button) TestUtils.findComponent("concatenateButton", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);

        // Set text in text fields
        robot().click(textField1);
        robot().enterText("Hello");
        robot().waitForIdle();
        
        robot().click(textField2);
        robot().enterText("World");
        robot().waitForIdle();

        // Click the Concatenate button
        robot().click(concatenateButton);
        robot().waitForIdle();

        // Assert that the label text is the concatenation of text fields
        assertEquals(resultLabel.getText(), "Hello World");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
