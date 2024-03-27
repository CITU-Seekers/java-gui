package com.codechum;

import com.codechum.awt.eventAdapters.FocusAdapterActivity;
import static org.testng.Assert.*;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class FocusAdapterActivityTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    List list1, list2;
    Label statusLabel;
    Button focusTestButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FocusAdapterActivity.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        list1 = (List) TestUtils.findComponent("list1", true);
        list2 = (List) TestUtils.findComponent("list2", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        focusTestButton = (Button) TestUtils.findComponent("focusTestButton", true);

        assertNotNull(list1);
        assertNotNull(list2);
        assertNotNull(statusLabel);
        assertNotNull(focusTestButton);
    }

    @Test
    public void shouldDisplayFocusGainedMessage() {
        list1 = (List) TestUtils.findComponent("list1", true);
        list2 = (List) TestUtils.findComponent("list2", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(list1);

        assertEquals(statusLabel.getText(), "List 1 has focus.");
    }

    @Test
    public void shouldDisplayFocusLostMessage() {
        list1 = (List) TestUtils.findComponent("list1", true);
        list2 = (List) TestUtils.findComponent("list2", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        focusTestButton = (Button) TestUtils.findComponent("focusTestButton", true);

        robot().click(list1);
        robot().click(focusTestButton);

        assertEquals(statusLabel.getText(), "List 1 has lost focus.");
    }

}
