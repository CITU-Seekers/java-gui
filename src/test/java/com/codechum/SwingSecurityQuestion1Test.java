package com.codechum;

import com.codechum.swing.jComboBox.SecurityQuestion1;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class SwingSecurityQuestion1Test extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;

    JTextField petNameTextField;
    JComboBox petTypeComboBox;
    JButton generateTextButton;
    JLabel displayLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SecurityQuestion1.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    public void checkLabel(int[] petKeyCodes, String petType, String expectedResult) {
        generateTextButton = (JButton) TestUtils.findComponent("generateTextButton", true);
        displayLabel = (JLabel) TestUtils.findComponent("displayLabel", true);
        
        for (int keyCodes: petKeyCodes) {
            frame.textBox("petNameTextField").pressAndReleaseKeys(keyCodes);
        }
        frame.comboBox("petTypeComboBox").selectItem(petType);
        
        robot().click(generateTextButton);
        robot().waitForIdle();
        
        assertEquals(displayLabel.getText(), expectedResult);
    }
    
    // Description: Should have all components named `petNameTextField`, `petTypeComboBox`, `generateTextButton`, and `displayLabel`.
    @Test
    public void shouldHaveAllComponents() {
        petNameTextField = (JTextField) TestUtils.findComponent("petNameTextField", true);
        petTypeComboBox = (JComboBox) TestUtils.findComponent("petTypeComboBox", true);
        generateTextButton = (JButton) TestUtils.findComponent("generateTextButton", true);
        displayLabel = (JLabel) TestUtils.findComponent("displayLabel", true);
        
        assertNotNull(petNameTextField, "No petNameTextField found.");
        assertNotNull(petTypeComboBox, "No petTypeComboBox found.");
        assertNotNull(generateTextButton, "No generateTextButton found.");
        assertNotNull(displayLabel, "No labelDisplayText found.");
    }
    
    // Description: Should display in `displayLabel` "The pet is a dog named Pero" when "Pero" is entered in `petNameTextField` and "Dog" is selected in `petTypeComboBox` and `generateTextButton` is clicked.
    @Test
    public void shouldDisplayCorrectTextForDog() {
        int[] keys ={VK_CAPS_LOCK, VK_P, VK_CAPS_LOCK, VK_E, VK_R, VK_O};
        checkLabel(keys, "Dog", "The pet is a dog named Pero");
    }

    // Description: Should display in `displayLabel` "The pet is a cat named Ming" when "Ming" is entered in `petNameTextField` and "Cat" is selected in `petTypeComboBox` and `generateTextButton` is clicked.
    @Test
    public void shouldDisplayCorrectTextForCat() {
        int[] keys ={VK_CAPS_LOCK, VK_M, VK_CAPS_LOCK, VK_I, VK_N, VK_G};
        checkLabel(keys, "Cat", "The pet is a cat named Ming");
    }

    // Description: Should display in `displayLabel` "The pet is a rabbit named Pekora" when "Pekora" is entered in `petNameTextField` and "Rabbit" is selected in `petTypeComboBox` and `generateTextButton` is clicked.
    @Test
    public void shouldDisplayCorrectTextForRabbit() {
        int[] keys ={VK_CAPS_LOCK, VK_P, VK_CAPS_LOCK, VK_E, VK_K, VK_O, VK_R, VK_A};
        checkLabel(keys, "Rabbit", "The pet is a rabbit named Pekora");
    }

    // Description: Should display in `displayLabel` "The pet is a parrot named Birdie" when "Birdie" is entered in `petNameTextField` and "Parrot" is selected in `petTypeComboBox` and `generateTextButton` is clicked.
    @Test
    public void shouldDisplayCorrectTextForParrot() {
        int[] keys ={VK_CAPS_LOCK, VK_B, VK_CAPS_LOCK, VK_I, VK_R, VK_D, VK_I, VK_E};
        checkLabel(keys, "Parrot", "The pet is a parrot named Birdie");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
