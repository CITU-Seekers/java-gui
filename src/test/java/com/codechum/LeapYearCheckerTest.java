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
    JTextField tfYear;
    JButton btnCheckYear;

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

    @Test
    public void checkAppTitleValue() {
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        Frame swingFrame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
        assertEquals(swingFrame.getTitle(), "Leap Year Checker");
    }

    @Test
    public void hasTfYear(){
        tfYear = (JTextField) TestUtils.findComponent("tfYear", true);
        assertNotNull(tfYear, "No tfYear found.");
    }

    @Test
    public void hasBtnCheckYear(){
        btnCheckYear = (JButton) TestUtils.findComponent("btnCheckYear", true);
        assertNotNull(btnCheckYear, "No btnCheckYear found.");
    }

    @Test
    public void checkTfYearDefaultValue(){
        tfYear = (JTextField) TestUtils.findComponent("tfYear", true);
        assertEquals(tfYear.getText(), "", "The tfYear text field should be empty by default");
    }

    @Test
    public void shouldShowDialogForLeapYearOnButtonClick(){
        btnCheckYear = (JButton) TestUtils.findComponent("btnCheckYear", true);
        tfYear = (JTextField) TestUtils.findComponent("tfYear", true);
        robot().click(tfYear);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_0);
        robot().click(btnCheckYear);

        robot().click(tfYear);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_4);
        robot().click(btnCheckYear);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Leap year");
    }   

    @Test
    public void shouldShowDialogForNotALeapYearOnButtonClick(){
        btnCheckYear = (JButton) TestUtils.findComponent("btnCheckYear", true);
        tfYear = (JTextField) TestUtils.findComponent("tfYear", true);
        robot().click(tfYear);
        robot().pressAndReleaseKeys(VK_1, VK_9, VK_9, VK_9);
        robot().click(btnCheckYear);

        robot().click(tfYear);
        robot().pressAndReleaseKeys(VK_2, VK_0, VK_0, VK_1);
        robot().click(btnCheckYear);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Not a leap year");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
     