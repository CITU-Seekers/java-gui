package com.codechum;

import com.codechum.swing.jTextArea.TextReverser;
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
 * Unit tests for JTextArea1.
 */
public class TextReverserTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JTextArea textArea;
    JLabel reversedTextLabel;
    JButton reverseButton;

    /**
     * Set up method to register an EmergencyAbortListener and start the JTextArea1 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextReverser.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    /**
     * Test to check the presence of JTextArea, JLabel, and JButton components.
     */
    @Test
    public void shouldHaveAllComponents() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        reversedTextLabel = (JLabel) TestUtils.findComponent("reversedTextLabel", true);
        reverseButton = (JButton) TestUtils.findComponent("reverseButton", true);

        assertNotNull(textArea, "No textArea found.");
        assertNotNull(reversedTextLabel, "No reversedTextLabel found.");
        assertNotNull(reverseButton, "No reverseButton found.");
    }

    /**
     * Test to check if JTextArea and JLabel have default values.
     */
    @Test
    public void checkTextAreaAndLabelDefaultValue() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        reversedTextLabel = (JLabel) TestUtils.findComponent("reversedTextLabel", true);

        assertEquals(textArea.getText(), "");
        assertEquals(reversedTextLabel.getText(), "");
    }

    /**
     * Test to check if text is reversed correctly.
     */
    @Test
    public void shouldReverseText() {
        textArea = (JTextArea) TestUtils.findComponent("textArea", true);
        reversedTextLabel = (JLabel) TestUtils.findComponent("reversedTextLabel", true);
        reverseButton = (JButton) TestUtils.findComponent("reverseButton", true);

        frame.textBox("textArea").enterText("CodeChum");
        frame.button("reverseButton").click();

        assertEquals(reversedTextLabel.getText(), "muhCedoC");

        frame.textBox("textArea").deleteText();

        frame.textBox("textArea").enterText("Java Swing");
        frame.button("reverseButton").click();

        assertEquals(reversedTextLabel.getText(), "gniwS avaJ");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
