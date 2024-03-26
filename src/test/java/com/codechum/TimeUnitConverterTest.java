package com.codechum;

import com.codechum.swing.jComboBox.TimeUnitConverter;
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
 * Unit tests for JComboBox2 functionality.
 */
public class TimeUnitConverterTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;

    JTextField secondsTextField;
    JComboBox<String> toUnitComboBox;
    JButton convertButton;
    JLabel resultLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the JComboBox1 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TimeUnitConverter.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    /**
     * Method to initialize components.
     */
    public void initializeComponents() {
        secondsTextField = (JTextField) TestUtils.findComponent("secondsTextField", true);
        toUnitComboBox = (JComboBox) TestUtils.findComponent("toUnitComboBox", true);
        convertButton = (JButton) TestUtils.findComponent("convertButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
    }

    /**
     * Method to click the "Convert" button.
     */
    public void clickConvertButton() {
        frame.button("convertButton").click();
    }

    /**
     * Test to check if all the necessary components are present.
     */
    @Test
    public void shouldHaveAllComponents() {
        initializeComponents();

        assertNotNull(secondsTextField, "No secondsTextField found.");
        assertNotNull(toUnitComboBox, "No toUnitComboBox found.");
        assertNotNull(convertButton, "No convertButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }

    @Test
    public void shouldConvertToMilliSeconds() {
        initializeComponents();
        frame.comboBox("toUnitComboBox").selectItem("Milliseconds");

        frame.textBox("secondsTextField").enterText("5");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "5000.00");
    }
    
    @Test
    public void shouldConvertToMicroSeconds() {
       initializeComponents();
        frame.comboBox("toUnitComboBox").selectItem("Microseconds");

        frame.textBox("secondsTextField").enterText("5");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "5000000.00");
    }

    @Test
    public void shouldConvertToNanoSeconds() {
       initializeComponents();
        frame.comboBox("toUnitComboBox").selectItem("Nanoseconds");

        frame.textBox("secondsTextField").enterText("5");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "5000000000.00");
    }
    
    @Test
    public void shouldConvertToPicoSeconds() {
       initializeComponents();
        frame.comboBox("toUnitComboBox").selectItem("Picoseconds");

        frame.textBox("secondsTextField").enterText("5");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "5000000000000.00");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
