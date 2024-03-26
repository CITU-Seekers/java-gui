package com.codechum;

import com.codechum.swing.jTextField.AdditionMaster;
import java.awt.Frame;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

/**
 * Unit tests for TestCases in CODECHUMACTIVITY.
 */
public class AdditionMasterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JTextField operand1Field;
    JTextField operand2Field;
    JButton addButton;
    JLabel resultLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the CODECHUMACTIVITY application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(AdditionMaster.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    /**
     * Test to check the presence of all components including JTextField, JButton, and JLabel.
     */
    @Test
    public void shouldHaveAllComponents() {
        operand1Field = (JTextField) TestUtils.findComponent("operand1Field", true);
        operand2Field = (JTextField) TestUtils.findComponent("operand2Field", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        assertNotNull(operand1Field, "No operand1Field found.");
        assertNotNull(operand2Field, "No operand2Field found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }

    /**
     * Test to check the addition operation on button click.
     */
    @Test
    public void shouldPerformAddition() {
        operand1Field = (JTextField) TestUtils.findComponent("operand1Field", true);
        operand2Field = (JTextField) TestUtils.findComponent("operand2Field", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        frame.textBox("operand1Field").setText("5");
        frame.textBox("operand2Field").setText("10");
        robot().click(addButton);
        robot().waitForIdle();

        assertEquals(resultLabel.getText(), "15");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
