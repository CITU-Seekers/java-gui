package com.codechum;

import com.codechum.swing.swingEventListeners.ItemListenerActivity;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class SwingItemListenerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    JLabel languageLabel, proficiencyLabel;
    JComboBox proficiencyComboBox;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ItemListenerActivity.class).start();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        String[] checkButtons = {"cCheckBox", "cppCheckBox", "cSharpCheckBox", "javaCheckBox", "pythonCheckBox"};
        
        for (String chkButton: checkButtons) {
            JCheckBox chk = (JCheckBox) TestUtils.findComponent(chkButton, true);
            assertNotNull(chk, "No " + chkButton + " found.");
        }
        
        languageLabel = (JLabel) TestUtils.findComponent("languageLabel", true);
        proficiencyLabel = (JLabel) TestUtils.findComponent("proficiencyLabel", true);
        proficiencyComboBox = (JComboBox) TestUtils.findComponent("proficiencyComboBox", true);
        
        assertNotNull(languageLabel, "No languageLabel found.");
        assertNotNull(proficiencyLabel, "No proficiencyLabel found.");
        assertNotNull(proficiencyComboBox, "No proficiencyComboBox found.");
    }
    
    @Test
    public void shouldProperlyDisplayOnLanguageLabel() {
        languageLabel = (JLabel) TestUtils.findComponent("languageLabel", true);
        String[] checkButtons = {"cppCheckBox", "cCheckBox", "pythonCheckBox", "cSharpCheckBox",  "javaCheckBox"};
        
        for (String chkButton: checkButtons) {
            JCheckBox chk = (JCheckBox) TestUtils.findComponent(chkButton, true);
            
            robot().click(chk);
            robot().waitForIdle();
        }
        
        assertEquals(languageLabel.getText(), "C++, C, Python, C#, Java");
    }
    
    @Test
    public void shouldProperlyDisplayOnProficiencyLabel() {
        proficiencyLabel = (JLabel) TestUtils.findComponent("proficiencyLabel", true);
        proficiencyComboBox = (JComboBox) TestUtils.findComponent("proficiencyComboBox", true);
        
        robot().click(proficiencyComboBox);
        robot().pressAndReleaseKeys(VK_DOWN, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyComboBox.getModel().getSelectedItem().toString());
        
        robot().click(proficiencyComboBox);
        robot().pressAndReleaseKeys(VK_DOWN, VK_UP, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyComboBox.getModel().getSelectedItem().toString());
        
        robot().click(proficiencyComboBox);
        robot().pressAndReleaseKeys(VK_DOWN, VK_DOWN, VK_ENTER);
        
        assertEquals(proficiencyLabel.getText(), proficiencyComboBox.getModel().getSelectedItem().toString());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
