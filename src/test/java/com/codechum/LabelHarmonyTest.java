package com.codechum;

import com.codechum.swing.jLabel.LabelHarmony;
import static org.testng.Assert.*;
import javax.swing.*;
import org.testng.annotations.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

/**
 * Unit tests for the JLabel2 class.
 */
public class LabelHarmonyTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    /**
     * Set up method to register an EmergencyAbortListener and start the JLabel2 application.
     */
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LabelHarmony.class).start();
        robot().waitForIdle();
    }

    /**
     * Utility method to find a JLabel by its name using TestUtils.findComponent.
     *
     * @param labelName The name of the JLabel to find.
     * @return The found JLabel.
     */
    private JLabel findLabel(String labelName) {
        return (JLabel) TestUtils.findComponent(labelName, true);
    }

    // Description: Should have a label named `passionLabel` with text "Passionate about Code".
    /**
     * Test to check the presence and text value of the "Passionate about Code" label.
     */
    @Test
    public void shouldHavePassionLabel() {
        JLabel label = findLabel("passionLabel");
        assertNotNull(label, "No passionLabel found.");
        assertEquals(label.getText(), "Passionate about Code");
    }

    // Description: Should have a label named `techLabel` with text "Tech Enthusiast".
    /**
     * Test to check the presence and text value of the "Tech Enthusiast" label.
     */
    @Test
    public void shouldHaveTechLabel() {
        JLabel label = findLabel("techLabel");
        assertNotNull(label, "No techLabel found.");
        assertEquals(label.getText(), "Tech Enthusiast");
    }

    // Description: Should have a label named `syntaxLabel` with text "Syntax Perfectionist".
    /**
     * Test to check the presence and text value of the "Syntax Perfectionist" label.
     */
    @Test
    public void shouldHaveSyntaxLabel() {
        JLabel label = findLabel("syntaxLabel");
        assertNotNull(label, "No syntaxLabel found.");
        assertEquals(label.getText(), "Syntax Perfectionist");
    }

    // Description: Should have a label named `devLabel` with text "Dev by Heart".
    /**
     * Test to check the presence and text value of the "Dev by Heart" label.
     */
    @Test
    public void shouldHaveDevLabel() {
        JLabel label = findLabel("devLabel");
        assertNotNull(label, "No devLabel found.");
        assertEquals(label.getText(), "Dev by Heart");
    }

    /**
     * Method to unregister the EmergencyAbortListener after each test method.
     */
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
