package com.codechum;

import static org.testng.Assert.*;

import com.codechum.awt.eventListeners.ItemListenerActivity;
import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class ItemListenerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label languageLabel, proficiencyLabel;
    Choice proficiencyChoice;
    
    @Override
    protected void onSetUp() {  
        listener = EmergencyAbortListener.registerInToolkit();
        application(ItemListenerActivity.class).start();
    }
        
    @Test
    public void shouldHaveAllComponents() {
        String[] checkButtons = {"cCheckBox", "cppCheckBox", "cSharpCheckBox", "javaCheckBox", "pythonCheckBox"};
        
        for (String checkBoxButton: checkButtons) {
            Checkbox checkBox = (Checkbox) TestUtils.findComponent(checkBoxButton, true);
            assertNotNull(checkBox, "No " + checkBoxButton + " found.");
        }
        
        languageLabel = (Label) TestUtils.findComponent("languageLabel", true);
        proficiencyLabel = (Label) TestUtils.findComponent("proficiencyLabel", true);
        proficiencyChoice = (Choice) TestUtils.findComponent("proficiencyChoice", true);
        
        assertNotNull(languageLabel, "No languageLabel found.");
        assertNotNull(proficiencyLabel, "No proficiencyLabel found.");
        assertNotNull(proficiencyChoice, "No proficiencyChoice found.");
    }
    
    @Test
    public void shouldProperlyDisplayOnLanguageLabel() {
        languageLabel = (Label) TestUtils.findComponent("languageLabel", true);
        String[] checkButtons = {"cppCheckBox", "cCheckBox", "pythonCheckBox", "cSharpCheckBox", "javaCheckBox"};
        
        for (String checkBoxButton: checkButtons) {
            Checkbox checkBox = (Checkbox) TestUtils.findComponent(checkBoxButton, true);
            
            robot().click(checkBox);
            robot().waitForIdle();
        }
        
        assertEquals(languageLabel.getText(), "C++, C, Python, C#, Java");
    }
    
    @Test
    public void shouldProperlyDisplayOnProficiencyLabel() {
        proficiencyLabel = (Label) TestUtils.findComponent("proficiencyLabel", true);
        proficiencyChoice = (Choice) TestUtils.findComponent("proficiencyChoice", true);
        
        robot().click(proficiencyChoice);
        robot().pressAndReleaseKeys(VK_DOWN, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyChoice.getSelectedItem());
        
        robot().click(proficiencyChoice);
        robot().pressAndReleaseKeys(VK_DOWN, VK_UP, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyChoice.getSelectedItem());
        
        robot().click(proficiencyChoice);
        robot().pressAndReleaseKeys(VK_DOWN, VK_DOWN, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyChoice.getSelectedItem());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
