package com.codechum;

import com.codechum.awt.textField.MyNameIs;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MyNameIsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField nameTextField;
    Label nameLabel;
    Button displayButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MyNameIs.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `nameTextField`, `nameLabel`, and `displayButton`.
    @Test
    public void shouldHaveAllComponents() {
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        nameLabel = (Label) TestUtils.findComponent("nameLabel", true);
        displayButton = (Button) TestUtils.findComponent("displayButton", true);
        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(nameLabel, "No nameLabel found.");
        assertNotNull(displayButton, "No displayButton found.");
    }

    // Description: Should check if `nameLabel` display text is empty by default.
    @Test
    public void shouldCheckLblDisplayTextDefaultValue() {
        nameLabel = (Label) TestUtils.findComponent("nameLabel", true);
        assertEquals(nameLabel.getText(), "");
    }

    // Description: Should display the text in `nameTextField` to `nameLabel` when `displayButton` is clicked.
    @Test
    public void shouldDisplayTextWhenClicked(){
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        nameLabel = (Label) TestUtils.findComponent("nameLabel", true);
        displayButton = (Button) TestUtils.findComponent("displayButton", true);
        
        robot().click(nameTextField);
        robot().enterText("Java");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(nameLabel.getText(), nameTextField.getText());
        robot().click(nameTextField);
        robot().pressAndReleaseKeys(
            VK_BACK_SPACE,
            VK_BACK_SPACE,
            VK_BACK_SPACE,
            VK_BACK_SPACE
        );
        robot().enterText("CodeChum");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(nameLabel.getText(), nameTextField.getText());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
