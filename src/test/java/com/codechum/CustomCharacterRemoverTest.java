package com.codechum;

import com.codechum.awt.textField.CustomCharacterRemover;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CustomCharacterRemoverTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField messageTextField;
    Checkbox vowelCheckbox, consonantCheckbox, numberCheckbox;
    Button removeButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CustomCharacterRemover.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `messageTextField`, `vowelCheckbox`, `consonantCheckbox`, `numberCheckbox`, and `removeButton`.
    @Test
    public void shouldHaveAllComponents() {
        messageTextField = (TextField) TestUtils.findComponent("messageTextField", true);
        vowelCheckbox = (Checkbox) TestUtils.findComponent("vowelCheckbox", true);
        consonantCheckbox = (Checkbox) TestUtils.findComponent("consonantCheckbox", true);
        numberCheckbox = (Checkbox) TestUtils.findComponent("numberCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        assertNotNull(messageTextField, "No messageTextField found.");
        assertNotNull(vowelCheckbox, "No vowelCheckbox found.");
        assertNotNull(consonantCheckbox, "No consonantCheckbox found.");
        assertNotNull(numberCheckbox, "No numberCheckbox found.");
        assertNotNull(removeButton, "No Remove button found.");
    }

    // Description: Should remove vowels from the `messageTextField` when `vowelCheckbox` is checked and `removeButton` is clicked.    
    @Test
    public void shouldRemoveVowelCharactersFromTextField() {
        messageTextField = (TextField) TestUtils.findComponent("messageTextField", true);
        vowelCheckbox = (Checkbox) TestUtils.findComponent("vowelCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        // Set initial text in the text field
        robot().click(messageTextField);
        robot().enterText("Hello123");
        robot().waitForIdle();

        // Selecting checkboxes and clicking Remove button
        robot().click(vowelCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        // Assert that the text has been modified as expected
        assertEquals(messageTextField.getText(), "Hll123");
    }
    
    // Description: Should remove consonants from the `messageTextField` when `consonantCheckbox` is checked and `removeButton` is clicked.
    @Test
    public void shouldRemoveConsonantCharactersFromTextField() {
        messageTextField = (TextField) TestUtils.findComponent("messageTextField", true);
        consonantCheckbox = (Checkbox) TestUtils.findComponent("consonantCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        // Set initial text in the text field
        robot().click(messageTextField);
        robot().enterText("Hello123");
        robot().waitForIdle();

        // Selecting checkboxes and clicking Remove button
        robot().click(consonantCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        // Assert that the text has been modified as expected
        assertEquals(messageTextField.getText(), "eo123");
    }
    
    // Description: Should remove numbers from the `messageTextField` when `numberCheckbox` is checked and `removeButton` is clicked.
    @Test
    public void shouldRemoveNumberCharactersFromTextField() {
        messageTextField = (TextField) TestUtils.findComponent("messageTextField", true);
        numberCheckbox = (Checkbox) TestUtils.findComponent("numberCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);

        // Set initial text in the text field
        robot().click(messageTextField);
        robot().enterText("Hello123");
        robot().waitForIdle();

        // Selecting checkboxes and clicking Remove button
        robot().click(numberCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        // Assert that the text has been modified as expected
        assertEquals(messageTextField.getText(), "Hello");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

