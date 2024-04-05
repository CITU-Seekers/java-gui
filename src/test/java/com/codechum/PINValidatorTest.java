package com.codechum;

import com.codechum.swing.jPasswordField.PINValidator;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PINValidatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
   
    JPasswordField pinTextField;
    JButton validateButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PINValidator.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have all components `pinTextField` and `validateButton`.
    @Test
    public void shouldHaveAllComponents() {
        pinTextField = (JPasswordField) TestUtils.findComponent("pinTextField", true);
        validateButton = (JButton) TestUtils.findComponent("validateButton", true);

        assertNotNull(pinTextField, "No pinTextField found.");
        assertNotNull(validateButton, "No validateButton found.");
    }

    // Description: Should show dialog with message "Valid PIN" when `pinTextField` is valid and `validateButton` is clicked.
    @Test
    public void shouldShowValidPINMessage(){
        pinTextField = (JPasswordField) TestUtils.getChildNamed(frame, "pinTextField");
        validateButton = (JButton) TestUtils.getChildNamed(frame, "validateButton");
        robot().click(pinTextField);
        robot().enterText("1234");
        robot().click(validateButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Valid PIN");
    }
    
    // Description: Should show dialog with message "Invalid PIN" when `pinTextField` is invalid and `validateButton` is clicked.
    @Test
    public void shouldShowInvalidPINMessage(){
        pinTextField = (JPasswordField) TestUtils.getChildNamed(frame, "pinTextField");
        validateButton = (JButton) TestUtils.getChildNamed(frame, "validateButton");
        robot().click(pinTextField);
        robot().enterText("abcd");
        robot().click(validateButton);

        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Invalid PIN");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
