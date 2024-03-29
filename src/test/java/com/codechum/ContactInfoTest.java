package com.codechum;

import com.codechum.swing.swingLayouts.ContactInfo;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;
import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ContactInfoTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frameFixture;
    JFrame frame;

    JTextField nameTextField;
    JTextField phoneTextField;
    JTextField emailTextField;
    JButton clearButton;
    JButton submitButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ContactInfo.class).start();

        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        });
    }
    
    // Description: Should set the JFrame's layout to GridLayout with 4 rows and 2 columns.
    @Test
    public void shouldUseGridLayout() {
     Container contentPane = frame.getContentPane();
        LayoutManager layout = contentPane.getLayout();

        // Check if the layout is an instance of GridLayout
        assertTrue(layout instanceof GridLayout, "should use GridLayout.");
        
        // If needed, you can cast the layout to GridLayout after ensuring it's an instance of GridLayout
        if (layout instanceof GridLayout) {
            GridLayout gridLayout = (GridLayout) layout;

            // Additional assertions related to GridLayout
            assertEquals(gridLayout.getRows(), 4, "Grid should have 4 rows.");
            assertEquals(gridLayout.getColumns(), 2, "Grid should have 2 columns.");
        }
    }

    // Description: Should have all components `nameTextField`, `phoneTextField`, `emailTextField`, `clearButton`, and `submitButton`.
    @Test
    public void shouldHaveAllComponents() {
        nameTextField = (JTextField) TestUtils.findComponent("nameTextField", true);
        phoneTextField = (JTextField) TestUtils.findComponent("phoneTextField", true);
        emailTextField = (JTextField) TestUtils.findComponent("emailTextField", true);
        clearButton = (JButton) TestUtils.findComponent("clearButton", true);
        submitButton = (JButton) TestUtils.findComponent("submitButton", true);


        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(phoneTextField, "No phoneTextField found.");
        assertNotNull(nameTextField, "No emailTextField found.");
        assertNotNull(clearButton, "No clearButton found.");
        assertNotNull(submitButton, "No submitButton found.");
    }

    // Description: Should clear all text fields when `clearButton` is clicked.
    @Test
    public void shouldClearFields() {
          nameTextField = (JTextField) TestUtils.getChildNamed(frame, "nameTextField");
        phoneTextField = (JTextField) TestUtils.getChildNamed(frame, "phoneTextField");
        emailTextField = (JTextField) TestUtils.getChildNamed(frame, "emailTextField");
        clearButton = (JButton) TestUtils.getChildNamed(frame, "clearButton");
        submitButton = (JButton) TestUtils.getChildNamed(frame, "submitButton");
        
        robot().click(nameTextField);
        robot().enterText("John Doe");
        robot().click(phoneTextField);
        robot().enterText("123-456-7890");
        robot().click(emailTextField);
        robot().enterText("john.doe@example.com");
        robot().click(clearButton);

        assertEquals(nameTextField.getText(), "");
        assertEquals(phoneTextField.getText(), "");
        assertEquals(emailTextField.getText(), "");
    }

    @Mocked JOptionPane jOptionPane;
    
    // Description: Should show a message dialog with the entered contact information when `submitButton` is clicked.
    @Test
    public void shouldShowMessageOnSubmit() {
         nameTextField = (JTextField) TestUtils.getChildNamed(frame, "nameTextField");
        phoneTextField = (JTextField) TestUtils.getChildNamed(frame, "phoneTextField");
        emailTextField = (JTextField) TestUtils.getChildNamed(frame, "emailTextField");
        clearButton = (JButton) TestUtils.getChildNamed(frame, "clearButton");
        submitButton = (JButton) TestUtils.getChildNamed(frame, "submitButton");
        
       robot().click(nameTextField);
        robot().enterText("TEST");
        robot().click(phoneTextField);
        robot().enterText("12355");
        robot().click(emailTextField);
        robot().enterText("emailss");
        robot().click(submitButton);

        String expectedMessage = "Name: TEST\nPhone: 12355\nEmail: emailss";

         new Verifications(){{
            JOptionPane.showMessageDialog(null, expectedMessage); times = 1;
        }};
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
