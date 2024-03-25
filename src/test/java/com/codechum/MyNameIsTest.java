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
    
    TextField textField;
    Label label;
    Button displayButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MyNameIs.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        textField = (TextField) TestUtils.findComponent("textField", true);
        label = (Label) TestUtils.findComponent("label", true);
        displayButton = (Button) TestUtils.findComponent("displayButton", true);
        assertNotNull(textField, "No textField found.");
        assertNotNull(label, "No label found.");
        assertNotNull(displayButton, "No displayButton found.");
    }

    @Test
    public void checkLblDisplayTextDefaultValue() {
        label = (Label) TestUtils.findComponent("label", true);
        assertEquals(label.getText(), "");
    }

    @Test
    public void shouldDisplayTextWhenClicked(){
        textField = (TextField) TestUtils.findComponent("textField", true);
        label = (Label) TestUtils.findComponent("label", true);
        displayButton = (Button) TestUtils.findComponent("displayButton", true);
        
        robot().click(textField);
        robot().enterText("Java");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(label.getText(), textField.getText());
        robot().click(textField);
        robot().pressAndReleaseKeys(
            VK_BACK_SPACE,
            VK_BACK_SPACE,
            VK_BACK_SPACE,
            VK_BACK_SPACE
        );
        robot().enterText("CodeChum");
        robot().click(displayButton);
        robot().waitForIdle();

        assertEquals(label.getText(), textField.getText());
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
