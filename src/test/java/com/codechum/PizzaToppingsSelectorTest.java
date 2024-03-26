package com.codechum;

import com.codechum.swing.jRadioButton.PizzaToppingsSelector;
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

public class PizzaToppingsSelectorTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;

    private JRadioButton pepperoniRadioButton;
    private JRadioButton mushroomRadioButton;
    private JRadioButton oliveRadioButton;
    private JRadioButton pineappleRadioButton;
    private JButton confirmButton;
    private JLabel resultLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PizzaToppingsSelector.class).start();
        robot().waitForIdle();

        pepperoniRadioButton = (JRadioButton) TestUtils.findComponent("pepperoniRadioButton", true);
        mushroomRadioButton = (JRadioButton) TestUtils.findComponent("mushroomRadioButton", true);
        oliveRadioButton = (JRadioButton) TestUtils.findComponent("oliveRadioButton", true);
        pineappleRadioButton = (JRadioButton) TestUtils.findComponent("pineappleRadioButton", true);
        confirmButton = (JButton) TestUtils.findComponent("confirmButton", true);
        resultLabel = (JLabel) TestUtils.findComponent("resultLabel", true);
    }

    @Test
    public void shouldOnlySelectOneRadioButton() {
        robot().click(pepperoniRadioButton);
        robot().waitForIdle();
        assertFalse(mushroomRadioButton.isSelected());
        assertFalse(oliveRadioButton.isSelected());
        assertFalse(pineappleRadioButton.isSelected());

        robot().click(mushroomRadioButton);
        robot().waitForIdle();
        assertFalse(pepperoniRadioButton.isSelected());
        assertFalse(oliveRadioButton.isSelected());
        assertFalse(pineappleRadioButton.isSelected());

        robot().click(oliveRadioButton);
        robot().waitForIdle();
        assertFalse(pepperoniRadioButton.isSelected());
        assertFalse(mushroomRadioButton.isSelected());
        assertFalse(pineappleRadioButton.isSelected());

        robot().click(pineappleRadioButton);
        robot().waitForIdle();
        assertFalse(pepperoniRadioButton.isSelected());
        assertFalse(mushroomRadioButton.isSelected());
        assertFalse(oliveRadioButton.isSelected());
    }

    @Test
    public void shouldDisplaySelectedToppingOnButtonClick() {
        robot().click(pepperoniRadioButton);
        robot().click(confirmButton);
        assertEquals(resultLabel.getText(), "Pepperoni");

        robot().click(mushroomRadioButton);
        robot().click(confirmButton);
        assertEquals(resultLabel.getText(), "Mushroom");

        robot().click(oliveRadioButton);
        robot().click(confirmButton);
        assertEquals(resultLabel.getText(), "Olive");

        robot().click(pineappleRadioButton);
        robot().click(confirmButton);
        assertEquals(resultLabel.getText(), "Pineapple");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
