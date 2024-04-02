package com.codechum;

import com.codechum.swing.jPasswordField.LoginDialog;
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

public class LoginDialogTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JPasswordField passwordTextField;
    JTextField usernameTextField;
    JButton loginButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        application(LoginDialog.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `passwordTextField`, `usernameTextField`, and `loginButton`.
    @Test
    public void shouldHaveAllComponents() {
        passwordTextField = (JPasswordField) TestUtils.findComponent("passwordTextField", true);
        usernameTextField = (JTextField) TestUtils.findComponent("usernameTextField", true);
        loginButton = (JButton) TestUtils.findComponent("loginButton", true);

        assertNotNull(passwordTextField, "No passwordTextField found.");
        assertNotNull(usernameTextField, "No usernameTextField found.");
        assertNotNull(loginButton, "No loginButton found.");
    }

    // Description: Should show JOptionPane with the "Failed!" message if `usernameTextField` and `passwordTextField` is not equal.
    @Test
    public void shouldShowFailedMessage() {
        passwordTextField = (JPasswordField) TestUtils.findComponent("passwordTextField", true);
        usernameTextField = (JTextField) TestUtils.findComponent("usernameTextField", true);
        loginButton = (JButton) TestUtils.findComponent("loginButton", true);

        // Enter text in usernameTextField
        robot().click(usernameTextField);
        usernameTextField.requestFocus();
        robot().enterText("CodeChum");
        robot().waitForIdle();


        // Enter text in passwordTextField
        robot().click(passwordTextField);
        passwordTextField.requestFocus();
        robot().enterText("CodeChumy");
        robot().waitForIdle();

        // Click the login button
        robot().click(loginButton);
        robot().waitForIdle();
        robot().waitForIdle();

        // Wait for the dialog to appear and capture the message
        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Failed!");
    }

    // Description: Should show JOptionPane with the "Success!" message if `usernameTextField` and `passwordTextField` is equal.
    @Test
    public void shouldShowSuccessMessage() {
        passwordTextField = (JPasswordField) TestUtils.findComponent("passwordTextField", true);
        usernameTextField = (JTextField) TestUtils.findComponent("usernameTextField", true);
        loginButton = (JButton) TestUtils.findComponent("loginButton", true);

        // Enter text in usernameTextField
        robot().click(usernameTextField);
        usernameTextField.requestFocus();
        robot().enterText("CodeChum");
        robot().waitForIdle();


        // Enter text in passwordTextField
        robot().click(passwordTextField);
        passwordTextField.requestFocus();
        robot().enterText("CodeChum");
        robot().waitForIdle();

        // Click the login button
        robot().click(loginButton);
        robot().waitForIdle();
        robot().waitForIdle();

        // Wait for the dialog to appear and capture the message
        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Success!");
    }
}
