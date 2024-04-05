package com.codechum;

import com.codechum.swing.swingEventClasses.ItemEventActivity;
import javax.swing.JButton;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SwingItemEventActivityTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;
     FrameFixture frame;
    JComboBox<String> comboBox;
    JCheckBox uppercaseCheckBox;
    JLabel selectedItemLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ItemEventActivity.class).start();

        frame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    // Description: Should have all components `comboBox`, `uppercaseCheckBox`, and `selectedItemLabel`.
     @Test
    public void shouldHaveAllComponents() {
       comboBox = (JComboBox<String>) TestUtils.findComponent("comboBox", true);
        uppercaseCheckBox = (JCheckBox) TestUtils.findComponent("uppercaseCheckBox", true);
        selectedItemLabel = (JLabel) TestUtils.findComponent("selectedItemLabel", true);

        assertNotNull(comboBox, "No comboBox found.");
        assertNotNull(uppercaseCheckBox, "No uppercaseCheckBox found.");
        assertNotNull(selectedItemLabel, "No selectedItemLabel found.");
    }

    // Description: Should update the text value of the `selectedItemLabel` depending on the selected item in the `comboBox`.
    @Test
    public void shouldUpdateLabelOnComboBoxSelection() {
        comboBox = (JComboBox<String>) TestUtils.findComponent("comboBox", true);
        selectedItemLabel = (JLabel) TestUtils.findComponent("selectedItemLabel", true);
        
        frame.comboBox("comboBox").selectItem("Burger");
        assertEquals(selectedItemLabel.getText(), "Burger");
        
        frame.comboBox("comboBox").selectItem("Chicken");
        assertEquals(selectedItemLabel.getText(), "Chicken");
        
        frame.comboBox("comboBox").selectItem("Spaghetti");
        assertEquals(selectedItemLabel.getText(), "Spaghetti");
    }

    // Description: Should update the `selectedItemLabel` to uppercase when the `uppercaseCheckBox` is selected.
    @Test
    public void shouldUpdateLabelToUppercaseOnCheckBoxSelection() {
        comboBox = (JComboBox<String>) TestUtils.findComponent("comboBox", true);
        uppercaseCheckBox = (JCheckBox) TestUtils.findComponent("uppercaseCheckBox", true);
        selectedItemLabel = (JLabel) TestUtils.findComponent("selectedItemLabel", true);
        
        frame.comboBox("comboBox").selectItem("Spaghetti");
        
        robot().click(uppercaseCheckBox);
         robot().waitForIdle();
        assertEquals(selectedItemLabel.getText(), "SPAGHETTI");
    }

    // Description: Should update the `selectedItemLabel` to original case when the `uppercaseCheckBox` is deselected.
    @Test
    public void shouldUpdateLabelToOriginalCaseOnCheckBoxDeselection() {
        comboBox = (JComboBox<String>) TestUtils.findComponent("comboBox", true);
        uppercaseCheckBox = (JCheckBox) TestUtils.findComponent("uppercaseCheckBox", true);
        selectedItemLabel = (JLabel) TestUtils.findComponent("selectedItemLabel", true);
        
        frame.comboBox("comboBox").selectItem("Burger");
        
        robot().click(uppercaseCheckBox);
        robot().waitForIdle();
         
        assertEquals(selectedItemLabel.getText(), "BURGER");

        robot().click(uppercaseCheckBox);
        robot().waitForIdle();
         
        assertEquals(selectedItemLabel.getText(), "Burger");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
