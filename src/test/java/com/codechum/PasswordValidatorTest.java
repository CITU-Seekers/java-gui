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

    @Test
    public void shouldHaveAllComponents(){
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        assertNotNull(passwordTextField);
        assertNotNull(statusLabel);
    }

    @Test
    public void shouldDisplayCorrectStatus(){
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(passwordTextField);   
        robot().enterText("adm23");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must be 8 characters long");

        robot().click(passwordTextField);   
        passwordTextField.setText("");
        robot().waitForIdle();

        robot().enterText("adminqwe");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must contain at least one digit");

        passwordTextField.setText("");
        robot().waitForIdle();

        robot().enterText("admin123");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password must contain at least one special character");

        passwordTextField.setText("");
        robot().waitForIdle();

        robot().enterText("admin123@");
        robot().waitForIdle();
        assertEquals(statusLabel.getText(), "Password is valid");
    }
}
