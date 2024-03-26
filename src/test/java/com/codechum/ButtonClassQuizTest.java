package com.codechum;

import com.codechum.swing.jButton.ButtonClassQuiz;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ButtonClassQuizTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JLabel textLabel;
    JButton switchVowelCaseButton, switchConsonantCaseButton, switchAllCaseButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ButtonClassQuiz.class).start();
        robot().waitForIdle();
    }

    public void clickJButton(JButton btn) {
        robot().click(btn);
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveLabelText() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        
        assertNotNull(textLabel, "No textLabel found.");
        assertEquals(textLabel.getText(), "I love programming!");
    }

    @Test
    public void shouldHaveAllButtons() {
        switchVowelCaseButton = (JButton) TestUtils.findComponent("switchVowelCaseButton", true);
        switchConsonantCaseButton = (JButton) TestUtils.findComponent("switchConsonantCaseButton", true);
        switchAllCaseButton = (JButton) TestUtils.findComponent("switchAllCaseButton", true);
        assertNotNull(switchVowelCaseButton, "No switchVowelCaseButton found.");
        assertNotNull(switchConsonantCaseButton, "No switchConsonantCaseButton found.");
        assertNotNull(switchAllCaseButton, "No switchAllCaseButton found.");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfVowelLetters() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        switchVowelCaseButton = (JButton) TestUtils.findComponent("switchVowelCaseButton", true);
        textLabel.setText("I love programming!");

        clickJButton(switchVowelCaseButton);
        assertEquals(textLabel.getText(), "i lOvE prOgrAmmIng!");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfConsonantLetters() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        switchConsonantCaseButton = (JButton) TestUtils.findComponent("switchConsonantCaseButton", true);
        textLabel.setText("I love programming!");
        
        clickJButton(switchConsonantCaseButton);
        assertEquals(textLabel.getText(), "I LoVe PRoGRaMMiNG!");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfAllLetters() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        switchAllCaseButton = (JButton) TestUtils.findComponent("switchAllCaseButton", true);
        textLabel.setText("I love programming!");

        clickJButton(switchAllCaseButton);
        assertEquals(textLabel.getText(), "i LOVE PROGRAMMING!");
    }

    @Test
    public void shouldSwitchCaseProperlyWhenAllButtonsAreClicked() {
        textLabel = (JLabel) TestUtils.findComponent("textLabel", true);
        switchAllCaseButton = (JButton) TestUtils.findComponent("switchAllCaseButton", true);
        switchVowelCaseButton = (JButton) TestUtils.findComponent("switchVowelCaseButton", true);
        switchConsonantCaseButton = (JButton) TestUtils.findComponent("switchConsonantCaseButton", true);
        switchAllCaseButton = (JButton) TestUtils.findComponent("switchAllCaseButton", true);
        textLabel.setText("I love programming!");

        clickJButton(switchAllCaseButton);
        clickJButton(switchAllCaseButton);
        clickJButton(switchConsonantCaseButton);
        clickJButton(switchConsonantCaseButton);
        clickJButton(switchVowelCaseButton);
        clickJButton(switchVowelCaseButton);

        assertEquals(textLabel.getText(), "I love programming!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
