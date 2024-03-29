package com.codechum;

import com.codechum.swing.jOptionPane.SwingPalindromeCheckerWithJOptionPane;
import java.awt.Frame;
import static org.testng.Assert.*;
import org.testng.annotations.*;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import static java.awt.event.KeyEvent.*;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingPalindromeCheckerWithJOptionPaneTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    JTextField inputTextField;
    JButton checkButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SwingPalindromeCheckerWithJOptionPane.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    @Test
    public void hasInputTextField(){
        inputTextField = (JTextField) TestUtils.getChildNamed(frame, "inputTextField");
        assertNotNull(inputTextField, "No inputTextField found.");
    }

    @Test
    public void hasCheckButton(){
        checkButton = (JButton) TestUtils.getChildNamed(frame, "checkButton");
        assertNotNull(checkButton, "No checkButton found.");
    }

    @Test
    public void checkInputTextFieldDefaultValue(){
        inputTextField = (JTextField) TestUtils.getChildNamed(frame, "inputTextField");
        assertEquals(inputTextField.getText(), "", "The inputTextField should be empty by default");
    }

    @Test
    public void shouldShowDialogForPalindromeOnButtonClick(){
        inputTextField = (JTextField) TestUtils.getChildNamed(frame, "inputTextField");
        checkButton = (JButton) TestUtils.getChildNamed(frame, "checkButton");
        robot().click(inputTextField);
        robot().enterText("level");
        robot().click(checkButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Palindrome");
    }

    @Test
    public void shouldShowDialogForNotAPalindromeOnButtonClick(){
        inputTextField = (JTextField) TestUtils.getChildNamed(frame, "inputTextField");
        checkButton = (JButton) TestUtils.getChildNamed(frame, "checkButton");
        robot().click(inputTextField);
        robot().pressAndReleaseKeys(VK_1, VK_9, VK_9, VK_9);
        robot().enterText("hello");
        robot().click(checkButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Not a Palindrome");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
