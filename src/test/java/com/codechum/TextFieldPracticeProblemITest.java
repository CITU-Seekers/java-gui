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
    
    JTextField textField;
    JLabel label;
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

    @Test
    public void shouldHaveAllComponents() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        label = (JLabel) TestUtils.findComponent("label", true);
        displayButton = (JButton) TestUtils.findComponent("displayButton", true);
        assertNotNull(textField, "No textField found.");
        assertNotNull(label, "No label found.");
        assertNotNull(displayButton, "No displayButton found.");
    }

    @Test
    public void checkLblDisplayTextDefaultValue() {
        label = (JLabel) TestUtils.findComponent("label", true);
        assertEquals(label.getText(), "");
    }

    @Test
    public void shouldDisplayTextWhenClicked(){
        textField = (JTextField) TestUtils.findComponent("textField", true);
        label = (JLabel) TestUtils.findComponent("label", true);
        displayButton = (JButton) TestUtils.findComponent("displayButton", true);
        
        frame.textBox("textField").setText("Java");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(label.getText(), textField.getText());

        frame.textBox("textField").deleteText();

        frame.textBox("textField").setText("CodeChum");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(label.getText(), textField.getText());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
