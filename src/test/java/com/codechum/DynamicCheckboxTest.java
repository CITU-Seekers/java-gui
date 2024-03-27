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
    
    Checkbox firstCheckBox, secondCheckBox;
    Choice categoryComboBox;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DynamicCheckbox.class).start();
    }
    
    // Description: Should have all components `firstCheckBox`, `secondCheckBox`, and `categoryComboBox`.
    @Test
    public void shouldHaveAllComponents() {
        String[] checkboxes = {"firstCheckBox", "secondCheckBox"};
        
        for (String checkbox : checkboxes) {
            Checkbox radButton = (Checkbox) TestUtils.findComponent(checkbox, true);
            assertNotNull(radButton, "No " + checkbox + " found.");
        }
        
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        assertNotNull(categoryComboBox, "No categoryComboBox found.");
    }
    
    // Description: Should only select one checkbox at a time.
    @Test
    public void shouldOnlySelectOneRadioButton(){
        String[] checkboxes = {"firstCheckBox", "secondCheckBox"};
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

    // Description: Should change the `firstCheckBox` and `secondCheckBox` value when "Food" is selected in `categoryComboBox`.
    @Test
    public void shouldChangeCheckboxTextWhenCategoryFoodIsSelected(){
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        firstCheckBox = (Checkbox) TestUtils.findComponent("firstCheckBox", true);
        secondCheckBox = (Checkbox) TestUtils.findComponent("secondCheckBox", true);
        
        categoryComboBox.select("Food");
        robot().waitForIdle();
        
        assertEquals(firstCheckBox.getLabel(), "Pizza");
        assertEquals(secondCheckBox.getLabel(), "Burger");
    }

    // Description: Should change the `firstCheckBox` and `secondCheckBox` value when "Drinks" is selected in `categoryComboBox`.
    @Test
    public void shouldChangeCheckboxTextWhenCategoryDrinkIsSelected() {
        categoryComboBox = (Choice) TestUtils.findComponent("categoryComboBox", true);
        firstCheckBox = (Checkbox) TestUtils.findComponent("firstCheckBox", true);
        secondCheckBox = (Checkbox) TestUtils.findComponent("secondCheckBox", true);
    
        robot().click(categoryComboBox);
        robot().pressAndReleaseKeys(VK_DOWN);
        robot().pressAndReleaseKeys(VK_ENTER);
        robot().waitForIdle();
    
        assertEquals(firstCheckBox.getLabel(), "Coke");
        assertEquals(secondCheckBox.getLabel(), "Orange Juice");
    }
    
}
