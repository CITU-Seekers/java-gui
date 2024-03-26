package com.codechum;

import com.codechum.swing.jTextField.CelsiusToFahrenheit;
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
 * Unit tests for JTextField2 in CODECHUMACTIVITY.
 */
public class CelsiusToFahrenheitTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JTextField celsiusField;
    JButton convertButton;
    JLabel resultLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the CODECHUMACTIVITY application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CelsiusToFahrenheit.class).start();
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
        celsiusField = (JTextField) TestUtils.findComponent("celsiusField", true);
        convertButton = (JButton) TestUtils.findComponent("convertButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        assertNotNull(celsiusField, "No celsiusField found.");
        assertNotNull(convertButton, "No convertButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }

    /**
     * Test to check the conversion from Celsius to Fahrenheit.
     */
    @Test
    public void shouldConvertCelsiusToFahrenheit() {
        celsiusField = (JTextField) TestUtils.findComponent("celsiusField", true);
        convertButton = (JButton) TestUtils.findComponent("convertButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);

        frame.textBox("celsiusField").setText("25");
        robot().click(convertButton);
        robot().waitForIdle();

        assertEquals(resultLabel.getText(), "77.0");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
