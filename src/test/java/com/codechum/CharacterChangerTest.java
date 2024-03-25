package com.codechum;

import com.codechum.awt.buttons.CharacterChanger;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CharacterChangerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label textLabel;
    Button switchVowelCaseButton, switchConsonantCaseButton, switchAllCaseButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CharacterChanger.class).start();
        robot().waitForIdle();
    }

    public void clickButton(Button btn) {
        robot().click(btn);
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveLabelText() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        assertNotNull(textLabel, "No textLabel found.");
        assertEquals(textLabel.getText(), "I love programming!");
    }

    @Test
    public void shouldHaveAllButtons() {
        switchVowelCaseButton = (Button) TestUtils.findComponent("switchVowelCaseButton", true);
        switchConsonantCaseButton = (Button) TestUtils.findComponent("switchConsonantCaseButton", true);
        switchAllCaseButton = (Button) TestUtils.findComponent("switchAllCaseButton", true);
        assertNotNull(switchVowelCaseButton, "No switchVowelCaseButton found.");
        assertNotNull(switchConsonantCaseButton, "No switchConsonantCaseButton found.");
        assertNotNull(switchAllCaseButton, "No switchAllCaseButton found.");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfVowelLetters() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        switchVowelCaseButton = (Button) TestUtils.findComponent("switchVowelCaseButton", true);

        clickButton(switchVowelCaseButton);
        assertEquals(textLabel.getText(), "i lOvE prOgrAmmIng!");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfConsonantLetters() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        switchConsonantCaseButton = (Button) TestUtils.findComponent("switchConsonantCaseButton", true);

        clickButton(switchConsonantCaseButton);
        assertEquals(textLabel.getText(), "I LoVe PRoGRaMMiNG!");
    }

    @Test
    public void shouldBeAbleToSwitchCaseOfAllLetters() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        switchAllCaseButton = (Button) TestUtils.findComponent("switchAllCaseButton", true);

        clickButton(switchAllCaseButton);
        assertEquals(textLabel.getText(), "i LOVE PROGRAMMING!");
    }

    @Test
    public void shouldSwitchCaseProperlyWhenAllButtonsAreClicked() {
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        switchAllCaseButton = (Button) TestUtils.findComponent("switchAllCaseButton", true);
        switchVowelCaseButton = (Button) TestUtils.findComponent("switchVowelCaseButton", true);
        switchConsonantCaseButton = (Button) TestUtils.findComponent("switchConsonantCaseButton", true);
        switchAllCaseButton = (Button) TestUtils.findComponent("switchAllCaseButton", true);

        clickButton(switchAllCaseButton);
        clickButton(switchAllCaseButton);
        clickButton(switchConsonantCaseButton);
        clickButton(switchConsonantCaseButton);
        clickButton(switchVowelCaseButton);
        clickButton(switchVowelCaseButton);

        assertEquals(textLabel.getText(), "I love programming!");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

