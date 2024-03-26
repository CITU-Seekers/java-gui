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
    
    JTextField textField, numberTextField;
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
        frame.textBox("textField").deleteText();
        frame.textBox("numberTextField").deleteText();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (JButton) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);
        assertNotNull(textField, "No textField found.");
        assertNotNull(numberTextField, "No numberTextField found.");
        assertNotNull(displayTextButton, "No displayTextButton found.");
        assertNotNull(resultTextArea, "No resultTextArea found.");
    }

    @Test
    public void checkTextFieldAndTextAreaDefaultValue() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);
        assertEquals(resultTextArea.getText(), "");
        assertEquals(textField.getText(), "");
        assertEquals(numberTextField.getText(), "");
    }

    @Test
    public void shouldDisplayTextNTimes(){
        textField = (JTextField) TestUtils.findComponent("textField", true);
        numberTextField = (JTextField) TestUtils.findComponent("numberTextField", true);
        displayTextButton = (JButton) TestUtils.findComponent("displayTextButton", true);
        resultTextArea = (JTextArea) TestUtils.findComponent("resultTextArea", true);

        frame.textBox("textField").enterText("Java");
        frame.textBox("numberTextField").enterText("5");
        robot().click(displayTextButton);
        robot().waitForIdle();

        assertEquals(resultTextArea.getText(), "Java Java Java Java Java");

        clearTextBoxes();

        frame.textBox("textField").enterText("CodeChum");
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
