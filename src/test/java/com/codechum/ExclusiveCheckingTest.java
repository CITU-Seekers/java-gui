package com.codechum;

import com.codechum.awt.checkBox.ExclusiveChecking;
import java.awt.Checkbox;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ExclusiveCheckingTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox firstCheckbox, secondCheckbox, thirdCheckbox;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ExclusiveChecking.class).start();
        robot().waitForIdle();
    }

    // Description: Should have all components named `firstCheckbox`, `secondCheckbox`, and `thirdCheckbox`.
    @Test
    public void shouldHaveAllComponents() {
        firstCheckbox = (Checkbox) TestUtils.findComponent("firstCheckbox", true);
        secondCheckbox = (Checkbox) TestUtils.findComponent("secondCheckbox", true);
        thirdCheckbox = (Checkbox) TestUtils.findComponent("thirdCheckbox", true);

        assertNotNull(firstCheckbox, "No firstCheckbox found.");
        assertNotNull(secondCheckbox, "No secondCheckbox found.");
        assertNotNull(thirdCheckbox, "No thirdCheckbox found.");
    }

    // Description: Should handle only one checkbox to be checked at a time.
    @Test
    public void shouldHandleCheckboxExclusiveChecking() {
        firstCheckbox = (Checkbox) TestUtils.findComponent("firstCheckbox", true);
        secondCheckbox = (Checkbox) TestUtils.findComponent("secondCheckbox", true);
        thirdCheckbox = (Checkbox) TestUtils.findComponent("thirdCheckbox", true);

        // Initially, firstCheckbox should be selected
        assertTrue(firstCheckbox.getState());
        assertFalse(secondCheckbox.getState());
        assertFalse(thirdCheckbox.getState());

        // Selecting secondCheckbox should unselect others
        robot().click(secondCheckbox);
        robot().waitForIdle();
        assertFalse(firstCheckbox.getState());
        assertTrue(secondCheckbox.getState());
        assertFalse(thirdCheckbox.getState());

        // Selecting thirdCheckbox should unselect others
        robot().click(thirdCheckbox);
        robot().waitForIdle();
        assertFalse(firstCheckbox.getState());
        assertFalse(secondCheckbox.getState());
        assertTrue(thirdCheckbox.getState());

        // Selecting firstCheckbox should unselect others
        robot().click(firstCheckbox);
        robot().waitForIdle();
        assertTrue(firstCheckbox.getState());
        assertFalse(secondCheckbox.getState());
        assertFalse(thirdCheckbox.getState());
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
