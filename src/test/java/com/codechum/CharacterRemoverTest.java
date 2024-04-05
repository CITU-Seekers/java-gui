package com.codechum;

import com.codechum.awt.checkBox.CharacterRemover;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Label;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CharacterRemoverTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox vowelCheckbox, consonantCheckbox, numberCheckbox;
    Button removeButton, restoreButton;
    Label label;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CharacterRemover.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `vowelCheckbox`, `consonantCheckbox`, `numberCheckbox`, `removeButton`, `restoreButton`, and `textLabel`.
    @Test
    public void shouldHaveAllComponents() {
        vowelCheckbox = (Checkbox) TestUtils.findComponent("vowelCheckbox", true);
        consonantCheckbox = (Checkbox) TestUtils.findComponent("consonantCheckbox", true);
        numberCheckbox = (Checkbox) TestUtils.findComponent("numberCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        restoreButton = (Button) TestUtils.findComponent("restoreButton", true);
        label = (Label) TestUtils.findComponent("textLabel", true);

        assertNotNull(vowelCheckbox, "No vowelCheckbox found.");
        assertNotNull(consonantCheckbox, "No consonantCheckbox found.");
        assertNotNull(numberCheckbox, "No numberCheckbox found.");
        assertNotNull(removeButton, "No Remove button found.");
        assertNotNull(restoreButton, "No Restore button found.");
        assertNotNull(label, "No label found.");
    }

    // Description: Should remove vowels from the text in `textLabel` when `vowelCheckbox` is selected and `removeButton` is clicked.
    @Test
    public void shouldRemoveVowels() {
        vowelCheckbox = (Checkbox) TestUtils.findComponent("vowelCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        label = (Label) TestUtils.findComponent("textLabel", true);

        robot().click(vowelCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        assertEquals(label.getText(), "BCDFGHJKLMNPQRSTVWXYZ0123456789");
    }
    
    // Description: Should remove consonants from the text in `textLabel` when `consonantCheckbox` is selected and `removeButton` is clicked.
    @Test
    public void shouldRemoveConsonants() {
        consonantCheckbox = (Checkbox) TestUtils.findComponent("consonantCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        label = (Label) TestUtils.findComponent("textLabel", true);

        robot().click(consonantCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        assertEquals(label.getText(), "AEIOU0123456789");
    }
    
    // Description: Should remove numbers from the text in `textLabel` when `numberCheckbox` is selected and `removeButton` is clicked.
    @Test
    public void shouldRemoveNumbers() {
        numberCheckbox = (Checkbox) TestUtils.findComponent("numberCheckbox", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        label = (Label) TestUtils.findComponent("textLabel", true);

        robot().click(numberCheckbox);
        robot().click(removeButton);
        robot().waitForIdle();

        assertEquals(label.getText(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    // Description: Should restore the original text in `textLabel` when `restoreButton` is clicked.
    @Test
    public void shouldRestoreOriginalText() {
        restoreButton = (Button) TestUtils.findComponent("restoreButton", true);
        label = (Label) TestUtils.findComponent("textLabel", true);

        robot().click(restoreButton);
        robot().waitForIdle();

        assertEquals(label.getText(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
