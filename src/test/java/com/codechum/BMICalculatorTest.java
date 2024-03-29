package com.codechum;

import com.codechum.swing.jOptionPane.BMICalculator;
import java.awt.Frame;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import javax.swing.JButton;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import static java.awt.event.KeyEvent.*;
import javax.swing.JTextField;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class BMICalculatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    JTextField weightTextField;
    JTextField heightTextField;
    JButton calculateButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(BMICalculator.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    @Test
    public void hasWeightTextField(){
        weightTextField = (JTextField) TestUtils.getChildNamed(frame, "weightTextField");
        assertNotNull(weightTextField, "No weightTextField found.");
    }

    @Test
    public void hasHeightTextField(){
        heightTextField = (JTextField) TestUtils.getChildNamed(frame, "heightTextField");
        assertNotNull(heightTextField, "No heightTextField found.");
    }

    @Test
    public void hasCalculateButton(){
        calculateButton = (JButton) TestUtils.getChildNamed(frame, "calculateButton");
        assertNotNull(calculateButton, "No calculateButton found.");
    }

    @Test
    public void checkWeightTextFieldDefaultValue(){
        weightTextField = (JTextField) TestUtils.getChildNamed(frame, "weightTextField");
        assertEquals(weightTextField.getText(), "", "The weightTextField should be empty by default");
    }

    @Test
    public void checkHeightTextFieldDefaultValue(){
        heightTextField = (JTextField) TestUtils.getChildNamed(frame, "heightTextField");
        assertEquals(heightTextField.getText(), "", "The heightTextField should be empty by default");
    } 

    @Test
    public void shouldShowDialogWithCalculatedBMIOnButtonClick(){
        weightTextField = (JTextField) TestUtils.getChildNamed(frame, "weightTextField");
        heightTextField = (JTextField) TestUtils.getChildNamed(frame, "heightTextField");
        calculateButton = (JButton) TestUtils.getChildNamed(frame, "calculateButton");
        robot().click(weightTextField);
        robot().enterText("70");
        robot().click(heightTextField);
        robot().enterText("1.75");
        robot().click(calculateButton);

        String message = TestUtils.getJOptionPaneMessage();
        
        assertEquals(message, "Your BMI is: 22.86");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
