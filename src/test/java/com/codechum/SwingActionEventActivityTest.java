package com.codechum;

import com.codechum.swing.swingEventClasses.ActionEventActivity;
import javax.swing.JButton;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mockit.Mocked;
import mockit.Verifications;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SwingActionEventActivityTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;
    JFrame frame;
    
    JTextField textField;
    JLabel countLabel;
    JButton button1, button2;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ActionEventActivity.class).start();
      
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        });
    }
    
    // Description: Should have all components `textField`, `countLabel`, `button1`, and `button2`.
    @Test
    public void shouldHaveAllComponents() {
        textField = (JTextField) TestUtils.findComponent("textField", true);
        countLabel = (JLabel) TestUtils.findComponent("countLabel", true);
        button1 = (JButton) TestUtils.findComponent("button1", true);
        button2 = (JButton) TestUtils.findComponent("button2", true);

        assertNotNull(textField, "No textField found.");
        assertNotNull(countLabel, "No countLabel found.");
        assertNotNull(button1, "No button1 found.");
        assertNotNull(button2, "No button2 found.");
    }

    // Description: Should update `textField` text value to "Button 1 is Clicked!" and increment `countLabel` by 1 on `button1` click.
    @Test
    public void shouldUpdateTextFieldAndLabelOnButton1Click() {
        textField = (JTextField) TestUtils.getChildNamed(frame, "textField");
        countLabel = (JLabel) TestUtils.getChildNamed(frame, "countLabel");
        button1 = (JButton) TestUtils.getChildNamed(frame, "button1");
       
       robot().click(button1);

       assertEquals(textField.getText(), "Button 1 is Clicked!");
       assertEquals(countLabel.getText(), "1");
    }

    // Description: Should update `textField` text value to "Button 2 is Clicked!" and increment `countLabel` by 1 on `button2` click.
    @Test
    public void shouldUpdateTextFieldAndLabelOnButton2Click() {
        textField = (JTextField) TestUtils.getChildNamed(frame, "textField");
        countLabel = (JLabel) TestUtils.getChildNamed(frame, "countLabel");
      button2 = (JButton) TestUtils.getChildNamed(frame, "button2");
       
        robot().click(button2);
        robot().click(button2);

       assertEquals(textField.getText(), "Button 2 is Clicked!");
       assertEquals(countLabel.getText(), "2");
    }
    
    @Mocked JOptionPane jOptionPane;
    
    // Description: Should show message dialog "Button 1 is Clicked!" on `button1` click.
    @Test
    public void shouldShowMessageDialogOnButton1Click() {
        button1 = (JButton) TestUtils.getChildNamed(frame, "button1");
       
        robot().click(button1);
        
         new Verifications(){{
            JOptionPane.showMessageDialog(null, "Button 1 is Clicked!"); times = 1;
        }};
    }
    
    // Description: Should show message dialog "Button 2 is Clicked!" on `button2` click.
     @Test
     public void shouldShowMessageDialogOnButton2Click() {
         button2 = (JButton) TestUtils.getChildNamed(frame, "button2");
        robot().click(button2);
        
         new Verifications(){{
            JOptionPane.showMessageDialog(null, "Button 2 is Clicked!"); times = 1;
        }};
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
