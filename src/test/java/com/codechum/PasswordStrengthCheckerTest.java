package com.codechum;

import com.codechum.swing.jPasswordField.PasswordStrengthChecker;
import java.awt.Frame;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import javax.swing.JButton;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import static java.awt.event.KeyEvent.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class PasswordStrengthCheckerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
   
    JPasswordField passwordTextField;
    JButton checkStrengthButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PasswordStrengthChecker.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have all components `passwordTextField` and `checkStrengthButton`.
    @Test
    public void shouldHaveAllComponents() {
        passwordTextField = (JPasswordField) TestUtils.findComponent("passwordTextField", true);
        checkStrengthButton = (JButton) TestUtils.findComponent("checkStrengthButton", true);

        assertNotNull(passwordTextField, "No passwordTextField found.");
        assertNotNull(checkStrengthButton, "No checkStrengthButton found.");
    }

    // Description: Should show dialog with message "Password Strength: Weak" when `passwordTextField` is weak and `checkStrengthButton` is clicked.
    @Test
    public void shouldShowWeakPasswordMessage(){
        passwordTextField = (JPasswordField) TestUtils.getChildNamed(frame, "passwordTextField");
        checkStrengthButton = (JButton) TestUtils.getChildNamed(frame, "checkStrengthButton");
        robot().click(passwordTextField);
        robot().enterText("weak");
        robot().click(checkStrengthButton);

        String message = TestUtils.getJOptionPaneMessage();
        assertEquals(message, "Password Strength: Weak");
    }
    
    // Description: Should show dialog with message "Password Strength: Moderate" when `passwordTextField` is moderaate and `checkStrengthButton` is clicked.
    @Test
    public void shouldShowModeratePasswordMessage(){
        passwordTextField = (JPasswordField) TestUtils.getChildNamed(frame, "passwordTextField");
        checkStrengthButton = (JButton) TestUtils.getChildNamed(frame, "checkStrengthButton");
        robot().click(passwordTextField);
        robot().enterText("moderate123");
        robot().click(checkStrengthButton);

        String message = TestUtils.getJOptionPaneMessage();
        assertEquals(message, "Password Strength: Moderate");
    }
    
    // Description: Should show dialog with message "Password Strength: Strong" when `passwordTextField` is strong and `checkStrengthButton` is clicked.
    @Test
    public void shouldShowStrongPasswordMessage(){
        passwordTextField = (JPasswordField) TestUtils.getChildNamed(frame, "passwordTextField");
        checkStrengthButton = (JButton) TestUtils.getChildNamed(frame, "checkStrengthButton");
        robot().click(passwordTextField);
        robot().enterText("Strong@123");
        robot().click(checkStrengthButton);

        String message = TestUtils.getJOptionPaneMessage();
        assertEquals(message, "Password Strength: Strong");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
