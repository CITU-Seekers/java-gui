package com.codechum;

import com.codechum.swing.jPasswordField.CharacterValidator;
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

public class CharacterValidatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JPasswordField passwordTextField;
    JLabel resultLabel;
    JButton validateButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CharacterValidator.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `passwordTextField`, `resultLabel`, and `validateButton`.
    @Test
    public void shouldHaveAllComponents() {
        passwordTextField = (JPasswordField) TestUtils.findComponent("passwordTextField", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
        validateButton = (JButton) TestUtils.findComponent("validateButton", true);

        assertNotNull(passwordTextField, "No passwordTextField found.");
        assertNotNull(resultLabel, "No resultLabel found.");
        assertNotNull(validateButton, "No validateButton found.");
    }

    // Description: Should display "Has special characters" in `resultLabel` when `passwordTextField` contains special characters.
    @Test
    public void shouldBeAbleToValidateSpecialCharacters() {
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        frame.textBox("passwordTextField").setText("Hello@#$!World");
        frame.button("validateButton").click();

        assertEquals(resultLabel.getText(), "Has special characters");
    }

    // Description: Should display "No special characters" in `resultLabel` when `passwordTextField` does not contain special characters.
    @Test
    public void shouldBeAbleToValidateNoSpecialCharacters() {
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        frame.textBox("passwordTextField").setText("HelloWorld");
        frame.button("validateButton").click();

        assertEquals(resultLabel.getText(), "No special characters");
    }
}
