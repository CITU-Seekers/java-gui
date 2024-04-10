package com.codechum;

import com.codechum.awt.eventListeners.PasswordValidator;
import static org.testng.Assert.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class PasswordValidatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField passwordTextField;
    Label statusLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PasswordValidator.class).start();
    }

    // Description: Should have all components `passwordTextField` and `statusLabel`.
    @Test
    public void shouldHaveAllComponents() {
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        assertNotNull(passwordTextField);
        assertNotNull(statusLabel);
    }

    // Description: Should display 'Password must be 8 characters long' when password is less than 8 characters.
    @Test
    public void shouldDisplayLengthErrorForShortPassword() {
        prepareForTest();
        robot().enterText("adm23");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must be 8 characters long");
    }

    // Description: Should display 'Password must contain at least one digit' when password does not contain any digits.
    @Test
    public void shouldDisplayDigitErrorForPasswordWithoutDigits() {
        prepareForTest();
        robot().enterText("adminqwer");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must contain at least one digit");
    }

    // Description: Should display 'Password must contain at least one special character' when password does not contain any special characters.
    @Test
    public void shouldDisplaySpecialCharErrorForPasswordWithoutSpecialChars() {
        prepareForTest();
        robot().enterText("admin123");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must contain at least one special character");
    }

    // Description: Should display 'Password is valid' when password is valid.
    @Test
    public void shouldDisplayValidPasswordMessage() {
        prepareForTest();
        robot().enterText("admin123@");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password is valid");
    }

    private void prepareForTest() {
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        robot().click(passwordTextField);   
        passwordTextField.setText("");
        robot().waitForIdle();
    }
}
