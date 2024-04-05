package com.codechum;

import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.testng.annotations.*;

import com.codechum.swing.jTextArea.WordAndCharacterCounter;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class WordAndCharacterCounterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;
    
    JLabel wordCountLabel, charCountLabel;
    JTextArea textArea;
    JButton countButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(WordAndCharacterCounter.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    // Description: Should have all components `textArea`, `wordCountLabel`, `charCountLabel`, and `countButton`.
    @Test    
    public void shouldHaveAllComponents() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (JLabel) TestUtils.findComponent("wordCountLabel", true);
        charCountLabel = (JLabel) TestUtils.findComponent("charCountLabel", true);
        countButton = (JButton) TestUtils.findComponent("countButton", true);
        assertNotNull(textArea, "No textArea found.");
        assertNotNull(wordCountLabel, "No wordCountLabel found.");
        assertNotNull(charCountLabel, "No charCountLabel found.");
        assertNotNull(countButton, "No countButton found.");
    }

    // Description: Should check if the default value of the `textArea`, `wordCountLabel`, and `charCountLabel` is empty.
    @Test
    public void shouldCheckTextAreaAndLabelsDefaultValue() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (JLabel) TestUtils.findComponent("wordCountLabel", true);
        charCountLabel = (JLabel) TestUtils.findComponent("charCountLabel", true);;
        assertEquals(textArea.getText(), "");
        assertEquals(wordCountLabel.getText(), "");
        assertEquals(charCountLabel.getText(), "");
    }
    
    // Description: Should display the word count of the text entered in the `textArea` and display it in the `wordCountLabel` when the `countButton` is clicked.
    @Test
    public void shouldDisplayWordCount(){
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        wordCountLabel = (JLabel) TestUtils.findComponent("wordCountLabel", true);
        countButton = (JButton) TestUtils.findComponent("countButton", true);
        
        frame.textBox("textArea").enterText("I love Java");
        frame.button("countButton").click();
        
        assertEquals(wordCountLabel.getText(), "3");

        frame.textBox("textArea").deleteText();

        frame.textBox("textArea").enterText("CodeChum is the best!");
        frame.button("countButton").click();

        assertEquals(wordCountLabel.getText(), "4");
    }
    
    // Description: Should display the character count of the text entered in the `textArea` and display it in the `charCountLabel` when the `countButton` is clicked.
    @Test
    public void shouldDisplayCharacterCount(){
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        charCountLabel = (JLabel) TestUtils.findComponent("charCountLabel", true);
        countButton = (JButton) TestUtils.findComponent("countButton", true);
        frame.textBox("textArea").enterText("I love Java");
        frame.button("countButton").click();
        
        assertEquals(charCountLabel.getText(), "11");

        frame.textBox("textArea").deleteText();

        frame.textBox("textArea").enterText("CodeChum is the best!");
        frame.button("countButton").click();

        assertEquals(charCountLabel.getText(), "21");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}