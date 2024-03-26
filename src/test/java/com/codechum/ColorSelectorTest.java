package com.codechum;

import com.codechum.swing.jRadioButton.ColorSelector;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ColorSelectorTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;

    private JRadioButton redRadioButton;
    private JRadioButton blueRadioButton;
    private JRadioButton greenRadioButton;
    private JButton submitButton;
    private JLabel resultLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ColorSelector.class).start();
        robot().waitForIdle();

        redRadioButton = (JRadioButton) TestUtils.findComponent("redRadioButton", true);
        blueRadioButton = (JRadioButton) TestUtils.findComponent("blueRadioButton", true);
        greenRadioButton = (JRadioButton) TestUtils.findComponent("greenRadioButton", true);
        submitButton = (JButton) TestUtils.findComponent("submitButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
    }

    @Test
    public void shouldOnlySelectOneRadioButton() {
        robot().click(redRadioButton);
        robot().waitForIdle();
        assertFalse(blueRadioButton.isSelected());
        assertFalse(greenRadioButton.isSelected());
        
        robot().click(blueRadioButton);
        robot().waitForIdle();
        assertFalse(redRadioButton.isSelected());
        assertFalse(greenRadioButton.isSelected());
        
        robot().click(greenRadioButton);
        robot().waitForIdle();
        assertFalse(redRadioButton.isSelected());
        assertFalse(blueRadioButton.isSelected());
    }

    @Test
    public void shouldDisplayRedColor() {
        robot().click(redRadioButton);
        robot().click(submitButton);
        assertEquals(resultLabel.getText(), "Red");
    }
    
    @Test
    public void shouldDisplayBlueColor() {

        robot().click(blueRadioButton);
        robot().click(submitButton);
        assertEquals(resultLabel.getText(), "Blue");

    }
    
    @Test
    public void shouldDisplayGreenColor() {

        robot().click(greenRadioButton);
        robot().click(submitButton);
        assertEquals(resultLabel.getText(), "Green");
    }
    

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
