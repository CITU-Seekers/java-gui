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
   
    JPasswordField pinField;
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

    @Test
    public void shouldHaveAllComponents() {
        pinField = (JPasswordField) TestUtils.findComponent("pinField", true);
        validateButton = (JButton) TestUtils.findComponent("validateButton", true);

        assertNotNull(pinField, "No pinField found.");
        assertNotNull(validateButton, "No validateButton found.");
    }

    @Test
    public void shouldShowValidPINMessage(){
        pinField = (JPasswordField) TestUtils.getChildNamed(frame, "pinField");
        validateButton = (JButton) TestUtils.getChildNamed(frame, "validateButton");
        robot().click(pinField);
        robot().enterText("1234");
        robot().click(validateButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Valid PIN");
    }
    
    @Test
    public void shouldShowInvalidPINMessage(){
        pinField = (JPasswordField) TestUtils.getChildNamed(frame, "pinField");
        validateButton = (JButton) TestUtils.getChildNamed(frame, "validateButton");
        robot().click(pinField);
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
