package com.codechum;

import com.codechum.awt.textArea.TextSegregation;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TextSegregationTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Label wordCountLabel, charCountLabel;
    TextArea textArea;
    Button countButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextSegregation.class).start();
        robot().waitForIdle();
    }
    
    // Description: Should have all components named `textArea`, `wordCountLabel`, `charCountLabel`, and `countButton`.
    @Test
    public void shouldHaveAllComponents() {
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (Label) TestUtils.findComponent("wordCountLabel", true);
        charCountLabel = (Label) TestUtils.findComponent("charCountLabel", true);
        countButton = (Button) TestUtils.findComponent("countButton", true);
        assertNotNull(textArea, "No textArea found.");
        assertNotNull(wordCountLabel, "No wordCountLabel found.");
        assertNotNull(charCountLabel, "No charCountLabel found.");
        assertNotNull(countButton, "No countButton found.");
    }

    // Description: Should check if `textArea`, `wordCountLabel`, and `charCountLabel` display text is empty by default.
    @Test
    public void checkTextAreaAndLabelsDefaultValue() {
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (Label) TestUtils.findComponent("wordCountLabel", true);
        charCountLabel = (Label) TestUtils.findComponent("charCountLabel", true);
        assertEquals(textArea.getText(), "");
        assertEquals(wordCountLabel.getText(), "");
        assertEquals(charCountLabel.getText(), "");
    }
    
    // Description: Should display the word count of the text in `textArea` in `wordCountLabel` when `countButton` is clicked.
    @Test
    public void shouldDisplayWordCount(){
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (Label) TestUtils.findComponent("wordCountLabel", true);
        countButton = (Button) TestUtils.findComponent("countButton", true);
        robot().click(textArea);
        robot().enterText("CodeChum is the best!");
        robot().click(countButton);
        robot().waitForIdle();

        assertEquals(wordCountLabel.getText(), "4");
    }
    
    // Description: Should display the character count of the text in `textArea` in `charCountLabel` when `countButton` is clicked.
    @Test
    public void shouldDisplayCharacterCount(){
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        charCountLabel = (Label) TestUtils.findComponent("charCountLabel", true);
        countButton = (Button) TestUtils.findComponent("countButton", true);
        robot().click(textArea);
        robot().enterText("CodeChum is the best!");
        robot().click(countButton);
        robot().waitForIdle();

        assertEquals(charCountLabel.getText(), "21");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

