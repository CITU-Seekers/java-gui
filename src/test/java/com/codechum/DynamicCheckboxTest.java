package com.codechum;

import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import com.codechum.awt.checkBoxGroup.DynamicCheckbox;
import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class DynamicCheckboxTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;
    
    Checkbox checkbox1, checkbox2;
    Choice categoryComboBox;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DynamicCheckbox.class).start();
    }
    
    @Test
    public void hasAllComponents() {
        String[] checkboxes = {"checkbox1", "checkbox1"};
        
        for (String checkbox : checkboxes) {
            Checkbox radButton = (Checkbox) TestUtils.findComponent(checkbox, true);
            assertNotNull(radButton, "No " + checkbox + " found.");
        }
        
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        assertNotNull(categoryComboBox, "No categoryComboBox found.");
    }
    
    @Test
    public void shouldOnlySelectOneRadioButton(){
        String[] checkboxes = {"checkbox1", "checkbox1"};
        Checkbox[] rb = new Checkbox[checkboxes.length];
        for (int i = 0; i < checkboxes.length; i++) {
            rb[i] = (Checkbox) TestUtils.findComponent(checkboxes[i], true);
        }

        for(int i = 0; i < checkboxes.length; i++){
            robot().click(rb[i]);
            robot().waitForIdle();

            for(int j = 0; j < checkboxes.length; j++){
                if(rb[i] != rb[j]){
                    assertFalse(rb[j].getState(), "The "+ rb[j].getName() +" radio button should be unselected when another radio button is clicked.");
                }
                else{
                    assertTrue(rb[j].getState(), "The "+ rb[j].getName() +" radio button should be selected when clicked.");
                }
            }
        }
    }

    @Test
    public void shouldChangeCheckboxTextWhenCategoryFoodIsSelected(){
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        checkbox1 = (Checkbox) TestUtils.findComponent("checkbox1", true);
        checkbox2 = (Checkbox) TestUtils.findComponent("checkbox2", true);
        
        categoryComboBox.select("Food");
        robot().waitForIdle();
        
        assertEquals(checkbox1.getLabel(), "Pizza");
        assertEquals(checkbox2.getLabel(), "Burger");
    }

    @Test
    public void shouldChangeCheckboxTextWhenCategoryDrinkIsSelected() {
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        checkbox1 = (Checkbox) TestUtils.findComponent("checkbox1", true);
        checkbox2 = (Checkbox) TestUtils.findComponent("checkbox2", true);
    
        robot().click(categoryComboBox);
        robot().pressAndReleaseKeys(VK_DOWN);
        robot().pressAndReleaseKeys(VK_ENTER);
        robot().waitForIdle();
    
        assertEquals(checkbox1.getLabel(), "Coke");
        assertEquals(checkbox2.getLabel(), "Orange Juice");
    }
    
}
