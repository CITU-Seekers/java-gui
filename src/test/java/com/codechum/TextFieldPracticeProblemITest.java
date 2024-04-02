package com.codechum;

import com.codechum.swing.jTextField.TextFieldPracticeProblemI;
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

public class TextFieldPracticeProblemITest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;
    
    JTextField inputTextField;
    JLabel displayLabel;
    JButton displayButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextFieldPracticeProblemI.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `inputTextField`, `displayLabel`, and `displayButton`.
    @Test
    public void shouldHaveAllComponents() {
        inputTextField = (JTextField) TestUtils.findComponent("inputTextField", true);
        displayLabel = (JLabel) TestUtils.findComponent("displayLabel", true);
        displayButton = (JButton) TestUtils.findComponent("displayButton", true);
        assertNotNull(inputTextField, "No inputTextField found.");
        assertNotNull(displayLabel, "No displayLabel found.");
        assertNotNull(displayButton, "No displayButton found.");
    }

    // Description: Should have a default text value of `inputTextField` as empty. 
    @Test
    public void shouldCheckLblDisplayTextDefaultValue() {
        displayLabel = (JLabel) TestUtils.findComponent("displayLabel", true);
        assertEquals(displayLabel.getText(), "");
    }

    // Description: Should copy the text from `inputTextField` to `displayLabel` when `displayButton` is clicked.
    @Test
    public void shouldDisplayTextWhenClicked(){
        inputTextField = (JTextField) TestUtils.findComponent("inputTextField", true);
        displayLabel = (JLabel) TestUtils.findComponent("displayLabel", true);
        displayButton = (JButton) TestUtils.findComponent("displayButton", true);
        
        frame.textBox("inputTextField").setText("Java");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(displayLabel.getText(), inputTextField.getText());

        frame.textBox("inputTextField").deleteText();

        frame.textBox("inputTextField").setText("CodeChum");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(displayLabel.getText(), inputTextField.getText());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
