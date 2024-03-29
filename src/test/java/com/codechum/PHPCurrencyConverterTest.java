package com.codechum;

import com.codechum.swing.jComboBox.PHPCurrencyConverter;
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
 * Unit tests for JComboBox1 functionality.
 */
public class PHPCurrencyConverterTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;

    JTextField amountTextField;
    JComboBox<String> toCurrencyComboBox;
    JButton convertButton;
    JLabel resultLabel;

    /**
     * Set up method to register an EmergencyAbortListener and start the JComboBox1 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PHPCurrencyConverter.class).start();
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
        amountTextField = (JTextField) TestUtils.findComponent("amountTextField", true);
        toCurrencyComboBox = (JComboBox) TestUtils.findComponent("toCurrencyComboBox", true);
        convertButton = (JButton) TestUtils.findComponent("convertButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
    }

    /**
     * Method to click the "Convert" button.
     */
    public void clickConvertButton() {
        frame.button("convertButton").click();
    }

    // Description: Should have all components named `amountTextField`, `toCurrencyComboBox`, `convertButton`, and `resultLabel`.
    /**
     * Test to check if all the necessary components are present.
     */
    @Test
    public void shouldHaveAllComponents() {
        initializeComponents();

        assertNotNull(amountTextField, "No amountTextField found.");
        assertNotNull(toCurrencyComboBox, "No toCurrencyComboBox found.");
        assertNotNull(convertButton, "No convertButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }

    // Description: Should convert the amount in `amountTextField` to USD when `toCurrencyComboBox` is set to USD and `convertButton` is clicked.
    /**
     * Test to check the currency conversion for USD.
     */
    @Test
    public void shouldConvertToUSD() {
        initializeComponents();
        frame.comboBox("toCurrencyComboBox").selectItem("USD");

        frame.textBox("amountTextField").enterText("100");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "2.00");
    }

    // Description: Should convert the amount in `amountTextField` to EUR when `toCurrencyComboBox` is set to EUR and `convertButton` is clicked.
    /**
     * Test to check the conversion for EUR.
     */
    @Test
    public void shouldConvertToEUR() {
       initializeComponents();
        frame.comboBox("toCurrencyComboBox").selectItem("EUR");

        frame.textBox("amountTextField").enterText("100");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "1.80");
    }

    // Description: Should convert the amount in `amountTextField` to JPY when `toCurrencyComboBox` is set to JPY and `convertButton` is clicked.
    /**
     * Test to check the conversion for JPY.
     */
    @Test
    public void shouldConvertToJPY() {
       initializeComponents();
        frame.comboBox("toCurrencyComboBox").selectItem("JPY");

        frame.textBox("amountTextField").enterText("100");
        clickConvertButton();
        assertEquals(resultLabel.getText(), "213.00");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
