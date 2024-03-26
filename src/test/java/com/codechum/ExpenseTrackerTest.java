package com.codechum;

import com.codechum.swing.swingLayouts.ExpenseTracker;
import java.awt.Container;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ExpenseTrackerTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;
    JFrame frame;

    JTextField expenseTextField;
    JLabel totalLabel;
    JButton addExpenseButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ExpenseTracker.class).start();

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
            assertEquals(gridLayout.getRows(), 3, "Grid should have 3 rows.");
            assertEquals(gridLayout.getColumns(), 2, "Grid should have 2 columns.");
        }
    }
    
    @Test
    public void shouldHaveAllComponents() {
        expenseTextField = (JTextField) TestUtils.findComponent("expenseTextField", true);
        totalLabel = (JLabel) TestUtils.findComponent("totalLabel", true);
        addExpenseButton = (JButton) TestUtils.findComponent("addExpenseButton", true);

        assertNotNull(expenseTextField, "No expenseTextField found.");
        assertNotNull(totalLabel, "No totalLabel found.");
        assertNotNull(addExpenseButton, "No addExpenseButton found.");

    }

    @Test
    public void shouldUpdateTotalExpenseOnAddExpenseButtonClick() {
         expenseTextField = (JTextField) TestUtils.findComponent("expenseTextField", true);
        totalLabel = (JLabel) TestUtils.findComponent("totalLabel", true);
        addExpenseButton = (JButton) TestUtils.findComponent("addExpenseButton", true);
        
        robot().click(expenseTextField);
        robot().enterText("50");
        robot().click(addExpenseButton);

        assertEquals(totalLabel.getText(), "50.0");
        
        robot().click(expenseTextField);
        robot().enterText("0");
        robot().click(addExpenseButton);
        
        assertEquals(totalLabel.getText(), "550.0");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
