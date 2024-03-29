package com.codechum;

import com.codechum.swing.jComboBox.GUICalculator;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;


public class SwingGUICalculatorTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;

    JTextField num1TextField, num2TextField;
    JComboBox operationsComboBox;
    JButton computeButton;
    JLabel resultLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GUICalculator.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    public void clickComputeJButton() {
        frame.button("computeButton").click();
    }

    public void clearTextBox() {
        frame.textBox("num1TextField").deleteText();
        frame.textBox("num2TextField").deleteText();
    }
    
    public void initializeComponents() {
        num1TextField = (JTextField) TestUtils.findComponent("num1TextField", true);
        num2TextField = (JTextField) TestUtils.findComponent("num2TextField", true);
        operationsComboBox = (JComboBox) TestUtils.findComponent("operationsComboBox", true);
        computeButton = (JButton) TestUtils.findComponent("computeButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
    }
    
    // Description: Should have all components named `num1TextField`, `num2TextField`, `operationsComboBox`, `computeButton`, and `resultLabel`.
    @Test
    public void shouldHaveAllComponents() {
        initializeComponents();
        
        assertNotNull(num1TextField, "No num1TextField found.");
        assertNotNull(num2TextField, "No num2TextField found.");
        assertNotNull(operationsComboBox, "No operationsComboBox found.");
        assertNotNull(computeButton, "No computeButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }
    
    // Description: Should display sum of `num1TextField` and `num2TextField` in `resultLabel` when "+" is selected in `operationsComboBox` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedSum(){
        initializeComponents();
        frame.comboBox("operationsComboBox").selectItem("+");
        
        frame.textBox("num1TextField").enterText("3");
        frame.textBox("num2TextField").enterText("2");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "5");

        clearTextBox();

        frame.textBox("num1TextField").enterText("-3");
        frame.textBox("num2TextField").enterText("2");
        clickComputeJButton();
        
        assertEquals(resultLabel.getText(), "-1");
    }

    // Description: Should display difference of `num1TextField` and `num2TextField` in `resultLabel` when "-" is selected in `operationsComboBox` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedDifference(){
        initializeComponents();
        frame.comboBox("operationsComboBox").selectItem("-");

        frame.textBox("num1TextField").enterText("3");
        frame.textBox("num2TextField").enterText("2");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "1");

        clearTextBox();

        frame.textBox("num1TextField").enterText("10");
        frame.textBox("num2TextField").enterText("5");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "5");
    }

    // Description: Should display product of `num1TextField` and `num2TextField` in `resultLabel` when "*" is selected in `operationsComboBox` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedProduct(){
        initializeComponents();
        frame.comboBox("operationsComboBox").selectItem("*");

        frame.textBox("num1TextField").enterText("3");
        frame.textBox("num2TextField").enterText("2");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "6");

        clearTextBox();

        frame.textBox("num1TextField").enterText("10");
        frame.textBox("num2TextField").enterText("5");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "50");
    }

    // Description: Should display quotient of `num1TextField` and `num2TextField` in `resultLabel` when "/" is selected in `operationsComboBox` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedQuotient(){
        initializeComponents();
        frame.comboBox("operationsComboBox").selectItem("/");

        frame.textBox("num1TextField").enterText("6");
        frame.textBox("num2TextField").enterText("2");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "3");

        clearTextBox();

        frame.textBox("num1TextField").enterText("10");
        frame.textBox("num2TextField").enterText("5");
        clickComputeJButton();
        assertEquals(resultLabel.getText(), "2");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
