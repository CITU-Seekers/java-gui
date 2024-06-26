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
    
    // Description: Should have all components named `petNameTextField`, `petTypeChoice`, `generateTextButton`, and `displayLabel`.
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
    
    // Description: Should display "The pet is a dog named Pero" when "Pero" is entered in `petNameTextField` and "Dog" is selected in `petTypeChoice`.
    @Test
    public void shouldDisplayCorrectTextForChoiceDog() {
        checkLabel("Pero", "Dog", "The pet is a dog named Pero");
    }

    // Description: Should display "The pet is a cat named Ming" when "Ming" is entered in `petNameTextField` and "Cat" is selected in `petTypeChoice`.
    @Test
    public void shouldDisplayCorrectTextForChoiceCat() {
        checkLabel("Ming", "Cat", "The pet is a cat named Ming");
    }

    // Description: Should display "The pet is a rabbit named Pekora" when "Pekora" is entered in `petNameTextField` and "Rabbit" is selected in `petTypeChoice`.
    @Test
    public void shouldDisplayCorrectTextForChoiceRabbit() {
        checkLabel("Pekora", "Rabbit", "The pet is a rabbit named Pekora");
    }

    // Description: Should display "The pet is a parrot named Birdie" when "Birdie" is entered in `petNameTextField` and "Parrot" is selected in `petTypeChoice`.
    @Test
    public void shouldDisplayCorrectTextForChoiceParrot() {
        checkLabel("Birdie", "Parrot", "The pet is a parrot named Birdie");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

