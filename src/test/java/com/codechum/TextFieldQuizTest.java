package com.codechum;

import com.codechum.swing.jTextArea.TextFieldQuiz;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class TextFieldQuizTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;
    
    JTextField stringTextField, numberTextField;
    JTextArea resultTextArea;
    JButton displayTextButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextFieldQuiz.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    public void clearTextBoxes() {
        frame.textBox("stringTextField").deleteText();
        frame.textBox("numberTextField").deleteText();
    }
    
    // Description: Should have all components named `stringTextField`, `numberTextField`, `displayTextButton`, and `resultTextArea`.
    @Test
    public void shouldHaveAllComponents() {
        stringTextField = (JTextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (JButton) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);
        assertNotNull(stringTextField, "No stringTextField found.");
        assertNotNull(numberTextField, "No numberTextField found.");
        assertNotNull(displayTextButton, "No displayTextButton found.");
        assertNotNull(resultTextArea, "No resultTextArea found.");
    }

    // Description: Should check if `resultTextArea`, `stringTextField`, and `numberTextField` display text is empty by default.
    @Test
    public void shouldCheckTextFieldAndTextAreaDefaultValue() {
        stringTextField = (JTextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);
        assertEquals(resultTextArea.getText(), "");
        assertEquals(stringTextField.getText(), "");
        assertEquals(numberTextField.getText(), "");
    }

    // Description: Should display the text in `stringTextField` n times of `numberTextField` in `resultTextArea` when `displayTextButton` is clicked.
    @Test
    public void shouldDisplayTextNTimes(){
        stringTextField = (JTextField) TestUtils.findComponent("stringTextField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (JButton) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);

        frame.textBox("stringTextField").enterText("Java");
        frame.textBox("numberTextField").enterText("5");
        robot().click(displayTextButton);
        robot().waitForIdle();

        assertEquals(resultTextArea.getText(), "Java Java Java Java Java");

        clearTextBoxes();

        frame.textBox("stringTextField").enterText("CodeChum");
        frame.textBox("numberTextField").enterText("3");
        robot().click(displayTextButton);
        robot().waitForIdle();

        assertEquals(resultTextArea.getText(), "CodeChum CodeChum CodeChum");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
