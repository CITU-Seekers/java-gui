package com.codechum;

import com.codechum.awt.choice.GUICalculator;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class GUICalculatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField num1TextField, num2TextField;
    Choice operationsChoice;
    Button computeButton;
    Label resultLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GUICalculator.class).start();
        robot().waitForIdle();
    }
    
    public void clickComputeButton() {
        robot().click(computeButton);
        robot().waitForIdle();
    }

    public void input(String num1, String num2) {
        robot().click(num1TextField);
        robot().enterText(num1);
        robot().click(num2TextField);
        robot().enterText(num2);
    }
    
    public void initializeComponents() {
        num1TextField = (TextField) TestUtils.findComponent("num1TextField", true);
        num2TextField = (TextField) TestUtils.findComponent("num2TextField", true);
        operationsChoice = (Choice) TestUtils.findComponent("operationsChoice", true);
        computeButton = (Button) TestUtils.findComponent("computeButton", true);
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
    }
    
    // Description: Should have all components named `num1TextField`, `num2TextField`, `operationsChoice`, `computeButton`, and `resultLabel`.
    @Test
    public void shouldHaveAllComponents() {
        initializeComponents();
        
        assertNotNull(num1TextField, "No num1TextField found.");
        assertNotNull(num2TextField, "No num2TextField found.");
        assertNotNull(operationsChoice, "No operationsChoice found.");
        assertNotNull(computeButton, "No computeButton found.");
        assertNotNull(resultLabel, "No resultLabel found.");
    }
    
    // Description: Should display sum of `num1TextField` and `num2TextField` in `resultLabel` when "+" is selected in `operationsChoice` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedSum(){
        initializeComponents();
        operationsChoice.select("+");

        input("3", "2");
        clickComputeButton();
        assertEquals(resultLabel.getText(), "5");
    }

    // Description: Should display difference of `num1TextField` and `num2TextField` in `resultLabel` when "-" is selected in `operationsChoice` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedDifference(){
        initializeComponents();
        operationsChoice.select("-");

        input("10", "5");
        clickComputeButton();
        assertEquals(resultLabel.getText(), "5");
    }

    // Description: Should display product of `num1TextField` and `num2TextField` in `resultLabel` when "*" is selected in `operationsChoice` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedProduct(){
        initializeComponents();
        operationsChoice.select("*");

        input("10", "5");
        clickComputeButton();
        assertEquals(resultLabel.getText(), "50");
    }

    // Description: Should display quotient of `num1TextField` and `num2TextField` in `resultLabel` when "/" is selected in `operationsChoice` and `computeButton` is clicked.
    @Test
    public void shouldShowComputedQuotient(){
        initializeComponents();
        operationsChoice.select("/");

        input("6", "2");
        clickComputeButton();
        assertEquals(resultLabel.getText(), "3");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
   
