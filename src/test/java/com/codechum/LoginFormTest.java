package com.codechum;

import com.codechum.TestUtils;
import com.codechum.awt.dialog.LoginForm;
import static org.testng.Assert.*;

import java.awt.*;
import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class LoginFormTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    TextField userNameTextField, passwordTextField;
    Button loginButton;
    
    Dialog statusDialog;
    Label messageLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LoginForm.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components `userNameTextField`, `passwordTextField`, and `loginButton`.
    @Test 
    public void shouldHaveAllComponents(){
        userNameTextField = (TextField) TestUtils.findComponent("userNameTextField", true);
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        loginButton = (Button) TestUtils.findComponent("loginButton", true);
        
        assertNotNull(userNameTextField, "No userNameTextField found");
        assertNotNull(passwordTextField, "No passwordTextField found");
        assertNotNull(loginButton, "No loginButton found");
    }

    // Description: Should display "Login Successful!" in `messageLabel` of `statusDialog` on `loginButton` click.
    @Test
    public void shouldDisplayLoginSucessInStatusDialog(){
        userNameTextField = (TextField) TestUtils.findComponent("userNameTextField", true);
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        loginButton = (Button) TestUtils.findComponent("loginButton", true);
        
        robot().click(userNameTextField);
        robot().enterText("admin");
        robot().click(passwordTextField);
        robot().enterText("admin");
        robot().click(loginButton);
        robot().waitForIdle();
        
        statusDialog = (Dialog) TestUtils.findComponent("statusDialog", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        assertTrue(statusDialog.isVisible(), "The statusDialog should be visible");
        assertEquals(messageLabel.getText(), "Login Successful!");
    }

    // Description: Should display "Login Failed!" in `messageLabel` of `statusDialog` on `loginButton` click.
    @Test
    public void shouldDisplayLoginFailedInStatusDialog(){
        userNameTextField = (TextField) TestUtils.findComponent("userNameTextField", true);
        passwordTextField = (TextField) TestUtils.findComponent("passwordTextField", true);
        loginButton = (Button) TestUtils.findComponent("loginButton", true);
        
        robot().click(userNameTextField);
        robot().enterText("admin");
        robot().click(passwordTextField);
        robot().enterText("wrongpassword");
        robot().click(loginButton);
        robot().waitForIdle();
        
        statusDialog = (Dialog) TestUtils.findComponent("statusDialog", true);
        messageLabel = (Label) TestUtils.findComponent("messageLabel", true);
        
        assertTrue(statusDialog.isVisible(), "The statusDialog should be visible");
        assertEquals(messageLabel.getText(), "Login Failed!");
    }
    
}
