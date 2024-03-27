package com.codechum;

import com.codechum.awt.eventClasses.ActionEventActivity;
import static org.testng.Assert.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class ActionEventActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Button colorChangeButton;
    Label textLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ActionEventActivity.class).start();
    }
    
    @Test
    public void shouldHaveAllComponents(){
        colorChangeButton = (Button) TestUtils.findComponent("colorChangeButton", true);
        textLabel = (Label) TestUtils.findComponent("textLabel", true);

        assertNotNull(colorChangeButton);
        assertNotNull(textLabel);
    }

    @Test 
    public void shouldChangeColor(){
        colorChangeButton = (Button) TestUtils.findComponent("colorChangeButton", true);
        textLabel = (Label) TestUtils.findComponent("textLabel", true);

        assertEquals(textLabel.getForeground(), Color.BLACK);
        robot().click(colorChangeButton);
        assertEquals(textLabel.getForeground(), Color.RED);
    }
}
