package com.codechum;

import com.codechum.swing.jTextArea.PalindromeChecker;
import java.awt.Frame;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

/**
 * Unit tests for JTextArea2.
 */
public class PalindromeCheckerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JTextArea textArea;
    JLabel resultLabel;
    JButton checkButton;

    /**
     * Set up method to register an EmergencyAbortListener and start the JTextArea2 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PalindromeChecker.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `textArea`, `resultLabel`, and `checkButton`.
    /**
     * Test to check the presence of JTextArea, JLabel, and JButton components.
     */
    @Test
    public void shouldHaveAllComponents() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
        checkButton = (JButton) TestUtils.findComponent("checkButton", true);

        assertNotNull(textArea, "No textArea found.");
        assertNotNull(resultLabel, "No resultLabel found.");
        assertNotNull(checkButton, "No checkButton found.");
    }

    // Description: Should have an empty default value for `textArea` and `resultLabel`.
    /**
     * Test to check if JTextArea and JLabel have default values.
     */
    @Test
    public void shouldCheckTextAreaAndLabelDefaultValue() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        assertEquals(textArea.getText(), "");
        assertEquals(resultLabel.getText(), "");
    }

    // Description: Should display the result as "Palindrome" if the text in `textArea` is a palindrome and "Not a Palindrome" if it is not in `resultLabel` when `checkButton` is clicked.
    /**
     * Test to check if the text is correctly identified as a palindrome.
     */
    @Test
    public void shouldCheckPalindrome() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
        checkButton = (JButton) TestUtils.findComponent("checkButton", true);

        frame.textBox("textArea").enterText("level");
        frame.button("checkButton").click();

        assertEquals(resultLabel.getText(), "Palindrome");

        frame.textBox("textArea").deleteText();

        frame.textBox("textArea").enterText("hello");
        frame.button("checkButton").click();

        assertEquals(resultLabel.getText(), "Not a Palindrome");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
