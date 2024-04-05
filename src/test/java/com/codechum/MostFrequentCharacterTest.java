package com.codechum;

import com.codechum.awt.eventAdapters.MostFrequentCharacter;
import java.awt.*;
import static org.testng.Assert.*;

import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class MostFrequentCharacterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextArea textArea;
    Label frequentCharLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MostFrequentCharacter.class).start();
    }
    
    // Description: Should have all components `textArea` and `frequentCharLabel`.
    @Test
    public void shouldHaveAllComponents() {
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        frequentCharLabel = (Label) TestUtils.findComponent("frequentCharLabel", true);
        
        assertNotNull(textArea, "No textArea found.");
        assertNotNull(frequentCharLabel, "No frequentCharLabel found.");
    }
    
    // Description: Should display the correct frequent character in the `frequentCharLabel` when the text is entered in the `textArea`.
    @Test
    public void shouldDisplayCorrectFrequentChar() {
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        frequentCharLabel = (Label) TestUtils.findComponent("frequentCharLabel", true);
        boolean isLetterCorrect;
        
        robot().click(textArea);
        robot().waitForIdle();
        
        robot().pressAndReleaseKey(VK_N);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("n");
        assertTrue(isLetterCorrect, "The displayed text should be 'N' or 'n'");
        
        robot().pressAndReleaseKeys(VK_E, VK_V, VK_E);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("e");
        assertTrue(isLetterCorrect, "The displayed text should be 'E' or 'e'");
        
        robot().pressAndReleaseKey(VK_R);
        robot().waitForIdle();

        assertTrue(isLetterCorrect, "The displayed text should be 'E' or 'e'");
    }
    
    // Description: Should not count the space as a frequent character in the `frequentCharLabel` when the text is entered in the `textArea`.
    @Test
    public void shouldDisplayCorrectFrequentCharWithSpace() {
        textArea = (TextArea) TestUtils.findComponent("textArea", true);
        frequentCharLabel = (Label) TestUtils.findComponent("frequentCharLabel", true);
        boolean isLetterCorrect;
                
        robot().click(textArea);
        robot().waitForIdle();
        
        robot().pressAndReleaseKey(VK_I);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("i");
        assertTrue(isLetterCorrect, "The displayed text should be 'I' or 'i'");
        
        robot().pressAndReleaseKey(VK_SPACE);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("i");
        assertTrue(isLetterCorrect, "The displayed text should be 'I' or 'i'");
        
        robot().pressAndReleaseKeys(VK_A, VK_SPACE);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("a") ||
                frequentCharLabel.getText().toLowerCase().equals("i");
        assertTrue(isLetterCorrect, "The displayed text should be 'A', 'I', 'a', or 'i'");
        
        robot().pressAndReleaseKey(VK_A);
        robot().waitForIdle();
        
        isLetterCorrect = frequentCharLabel.getText().toLowerCase().equals("a");
        assertTrue(isLetterCorrect, "The displayed text should be 'A' or 'a'");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
