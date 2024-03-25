package com.codechum;

import com.codechum.awt.choice.SecurityQuestion1;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SecurityQuestion1Test extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    TextField petNameTextField;
    Choice petTypeChoice;
    Button generateTextButton;
    Label displayLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SecurityQuestion1.class).start();
        robot().waitForIdle();
    }

    public void checkLabel(String petName, String petType, String expectedResult) {
        petNameTextField = (TextField) TestUtils.findComponent("petNameTextField", true);
        petTypeChoice = (Choice) TestUtils.findComponent("petTypeChoice", true);
        generateTextButton = (Button) TestUtils.findComponent("generateTextButton", true);
        displayLabel = (Label) TestUtils.findComponent("displayLabel", true);
        
        robot().click(petNameTextField);
        robot().enterText(petName);
        petTypeChoice.select(petType);
        
        robot().click(generateTextButton);
        robot().waitForIdle();
        
        assertEquals(displayLabel.getText(), expectedResult);
    }
    
    @Test
    public void shouldHaveAllComponents() {
        petNameTextField = (TextField) TestUtils.findComponent("petNameTextField", true);
        petTypeChoice = (Choice) TestUtils.findComponent("petTypeChoice", true);
        generateTextButton = (Button) TestUtils.findComponent("generateTextButton", true);
        displayLabel = (Label) TestUtils.findComponent("displayLabel", true);
        
        assertNotNull(petNameTextField, "No petNameTextField found.");
        assertNotNull(petTypeChoice, "No petTypeChoice found.");
        assertNotNull(generateTextButton, "No generateTextButton found.");
        assertNotNull(displayLabel, "No displayLabelText found.");
    }
    
    @Test
    public void shouldDisplayCorrectTextForChoiceDog() {
        checkLabel("Pero", "Dog", "The pet is a dog named Pero");
    }

    @Test
    public void shouldDisplayCorrectTextForChoiceCat() {
        checkLabel("Ming", "Cat", "The pet is a cat named Ming");
    }

    @Test
    public void shouldDisplayCorrectTextForChoiceRabbit() {
        checkLabel("Pekora", "Rabbit", "The pet is a rabbit named Pekora");
    }

    @Test
    public void shouldDisplayCorrectTextForChoiceParrot() {
        checkLabel("Birdie", "Parrot", "The pet is a parrot named Birdie");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

