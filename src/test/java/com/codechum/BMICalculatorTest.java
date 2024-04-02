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

    // Description: Should have all components `weightTextField`, `heightTextField`, and `calculateButton`.
    @Test
    public void shouldHaveAllComponents(){
        weightTextField = (JTextField) TestUtils.getChildNamed(frame, "weightTextField");
        heightTextField = (JTextField) TestUtils.getChildNamed(frame, "heightTextField");
        calculateButton = (JButton) TestUtils.getChildNamed(frame, "calculateButton");
        
        assertNotNull(weightTextField, "No weightTextField found.");
        assertNotNull(heightTextField, "No heightTextField found.");
        assertNotNull(calculateButton, "No calculateButton found.");
    }

    // Description: Should have an empty default value for `weightTextField`.
    @Test
    public void shouldCheckWeightTextFieldDefaultValue(){
        weightTextField = (JTextField) TestUtils.getChildNamed(frame, "weightTextField");
        assertEquals(weightTextField.getText(), "", "The weightTextField should be empty by default");
    }

    // Description: Should have an empty default value for `heightTextField`.
    @Test
    public void shouldCheckHeightTextFieldDefaultValue(){
        heightTextField = (JTextField) TestUtils.getChildNamed(frame, "heightTextField");
        assertEquals(heightTextField.getText(), "", "The heightTextField should be empty by default");
    } 

    // Description: Should show a dialog with the calculated BMI when the `calculateButton` is clicked.
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
