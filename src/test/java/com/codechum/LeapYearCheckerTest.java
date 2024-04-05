package com.codechum;

import com.codechum.swing.jOptionPane.LeapYearChecker;
import static org.testng.Assert.*;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;

import static org.assertj.swing.finder.WindowFinder.findFrame;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static java.awt.event.KeyEvent.*;

public class LeapYearCheckerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;
    JTextField yearTextField;
    JButton checkYearButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LeapYearChecker.class).start();
        
        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should set the title of the app to "Leap Year Checker".
    @Test
    public void shouldCheckAppTitleValue() {
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        Frame swingFrame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
        assertEquals(swingFrame.getTitle(), "Leap Year Checker");
    }

    // Description: Should have a text field named `yearTextField`.
    @Test
    public void shouldHaveTextFieldYear(){
        yearTextField = (JTextField) TestUtils.findComponent("yearTextField", true);
        assertNotNull(yearTextField, "No yearTextField found.");
    }

    // Description: Should have a button named `checkYearButton`.
    @Test
    public void shouldHaveBtnCheckYear(){
        checkYearButton = (JButton) TestUtils.findComponent("checkYearButton", true);
        assertNotNull(checkYearButton, "No checkYearButton found.");
    }

    // Description: Should have an empty default value for `yearTextField`.
    @Test
    public void shouldcheckTextFieldYearDefaultValue(){
        yearTextField = (JTextField) TestUtils.findComponent("yearTextField", true);
        assertEquals(yearTextField.getText(), "", "The yearTextField text field should be empty by default");
    }

    // Description: Should show dialog with message "Leap year" when a leap year is entered in `yearTextField` and `checkYearButton` is clicked.
    @Test
    public void shouldShowDialogForLeapYearOnButtonClick(){
        checkYearButton = (JButton) TestUtils.findComponent("checkYearButton", true);
        yearTextField = (JTextField) TestUtils.findComponent("yearTextField", true);
        robot().click(yearTextField);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_0);
        robot().click(checkYearButton);

        robot().click(yearTextField);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_4);
        robot().click(checkYearButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Leap year");
    }   

    // Description: Should show dialog with message "Not a leap year" when a non-leap year is entered in `yearTextField` and `checkYearButton` is clicked.
    @Test
    public void shouldShowDialogForNotALeapYearOnButtonClick(){
        checkYearButton = (JButton) TestUtils.findComponent("checkYearButton", true);
        yearTextField = (JTextField) TestUtils.findComponent("yearTextField", true);
        robot().click(yearTextField);
        robot().pressAndReleaseKeys(VK_1, VK_9, VK_9, VK_9);
        robot().click(checkYearButton);

        robot().click(yearTextField);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_1);
        robot().click(checkYearButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Not a leap year");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
     