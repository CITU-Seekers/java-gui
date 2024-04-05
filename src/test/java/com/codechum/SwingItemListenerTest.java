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
    
    // Description: Should have all components `cCheckBox`, `cppCheckBox`, `cSharpCheckBox`, `javaCheckBox`, `pythonCheckBox`, `languageLabel`, `proficiencyLabel`, and `proficiencyComboBox`.
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
    
    // Description: Should display the selected languages in the `languageLabel` when the checkboxes are clicked.
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
    
    // Description: Should display the selected proficiency in the `proficiencyLabel` when the `proficiencyComboBox` is changed.
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
