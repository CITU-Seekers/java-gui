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

    JTextField nameField;
    JTextField phoneField;
    JTextField emailField;
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

    
    @Test
    public void shouldHaveAllComponents() {
        nameField = (JTextField) TestUtils.findComponent("nameField", true);
        phoneField = (JTextField) TestUtils.findComponent("phoneField", true);
        emailField = (JTextField) TestUtils.findComponent("emailField", true);
        clearButton = (JButton) TestUtils.findComponent("clearButton", true);
        submitButton = (JButton) TestUtils.findComponent("submitButton", true);


        assertNotNull(nameField, "No nameField found.");
        assertNotNull(phoneField, "No phoneField found.");
        assertNotNull(nameField, "No emailField found.");
        assertNotNull(clearButton, "No clearButton found.");
        assertNotNull(submitButton, "No submitButton found.");
    }

    @Test
    public void shouldClearFields() {
          nameField = (JTextField) TestUtils.getChildNamed(frame, "nameField");
        phoneField = (JTextField) TestUtils.getChildNamed(frame, "phoneField");
        emailField = (JTextField) TestUtils.getChildNamed(frame, "emailField");
        clearButton = (JButton) TestUtils.getChildNamed(frame, "clearButton");
        submitButton = (JButton) TestUtils.getChildNamed(frame, "submitButton");
        
        robot().click(nameField);
        robot().enterText("John Doe");
        robot().click(phoneField);
        robot().enterText("123-456-7890");
        robot().click(emailField);
        robot().enterText("john.doe@example.com");
        robot().click(clearButton);

        assertEquals(nameField.getText(), "");
        assertEquals(phoneField.getText(), "");
        assertEquals(emailField.getText(), "");
    }

    @Mocked JOptionPane jOptionPane;
    
    @Test
    public void shouldShowMessageOnSubmit() {
         nameField = (JTextField) TestUtils.getChildNamed(frame, "nameField");
        phoneField = (JTextField) TestUtils.getChildNamed(frame, "phoneField");
        emailField = (JTextField) TestUtils.getChildNamed(frame, "emailField");
        clearButton = (JButton) TestUtils.getChildNamed(frame, "clearButton");
        submitButton = (JButton) TestUtils.getChildNamed(frame, "submitButton");
        
       robot().click(nameField);
        robot().enterText("TEST");
        robot().click(phoneField);
        robot().enterText("12355");
        robot().click(emailField);
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
