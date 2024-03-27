package com.codechum;

import com.codechum.awt.layouts.GUICalculatorUpdate;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class GUICalculatorUpdateTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    Panel buttonsPanel;
    Label resultLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GUICalculatorUpdate.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }
    
    public void clickButton(String componentName) {
        Button button = (Button) TestUtils.findComponent(componentName, true);
        
        robot().click(button);
        robot().waitForIdle();
    }

    // Description: Should use `BorderLayout` for the frame.
    @Test
    public void shouldUseBorderLayout() {
        BorderLayout layout = (BorderLayout) frame.getLayout();
        assertTrue(layout.toString().contains("BorderLayout"), "should use BorderLayout.");
    }

    // Description: Should contain `resultLabel` in the `NORTH` of the frame.
    @Test
    public void shouldContainLblResultInNorth() {
        BorderLayout layout = (BorderLayout) frame.getLayout();
        Component comp = layout.getLayoutComponent(BorderLayout.NORTH);
        comp = comp != null ? comp : layout.getLayoutComponent(BorderLayout.PAGE_START);
        assertNotNull(comp, "No resultLabel found.");
    }
    
    // Description: Should contain `buttonsPanel` in the `CENTER` of the frame.
    @Test
    public void shouldContainPnlButtonsWithGridLayoutInCenter() {
        BorderLayout layout = (BorderLayout) frame.getLayout();
        buttonsPanel = (Panel) layout.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(buttonsPanel, "No buttonsPanel found.");
        
        GridLayout compLayout = (GridLayout) buttonsPanel.getLayout();
        assertTrue(compLayout.toString().contains("GridLayout"), "buttonsPanel should have GridLayout");
    }

    // Description: Should contain number buttons in `buttonsPanel` such as `button0`, `button1`, `button2`, `button3`, `button4`, `button5`, `button6`, `button7`, `button8`, and `button9`.
    @Test
    public void shouldContainNumberButtonsInPnlButtons() {
        buttonsPanel = (Panel) TestUtils.getChildNamed(frame, "buttonsPanel");

        for (int i = 0; i < 10; i++) {
            Button buttonNumber = (Button) TestUtils.getChildNamed(buttonsPanel, "button" + i);
            assertNotNull(buttonNumber, "No button"+ i + " found.");
        }
    }

    // Description: Should contain operation buttons in `buttonsPanel` such as `addButton`, `subtractButton`, `multiplyButton`, `divideButton`, `clearButton`, and `computeButton`.
    @Test
    public void shouldContainBtnOperationsInPnlButtons() {
        String[] operations = {"addButton", "subtractButton", "multiplyButton", "divideButton", "clearButton", "computeButton"};
        buttonsPanel = (Panel) TestUtils.getChildNamed(frame, "buttonsPanel");

        for (String operation : operations) {
            Button buttonOperation = (Button) TestUtils.getChildNamed(buttonsPanel, operation);
            assertNotNull(buttonOperation, "No " + operation + " found.");
        }
    }

    // Description: Should perform addition operation and show the result in `resultLabel` on `computeButton` click.
    @Test
    public void shouldPerformAddition() {
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        
        clickButton("button5");
        clickButton("addButton");
        clickButton("button3");
        clickButton("computeButton");

        assertEquals(resultLabel.getText(), "8");
    }

    // Description: Should perform subtraction operation and show the result in `resultLabel` on `computeButton` click.
    @Test
    public void shouldPerformSubtraction() {
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        
        clickButton("button5");
        clickButton("subtractButton");
        clickButton("button2");
        clickButton("computeButton");

        assertEquals(resultLabel.getText(), "3");
    }

    // Description: Should perform multiplication operation and show the result in `resultLabel` on `computeButton` click.
    @Test
    public void shouldPerformMultiplication() {
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        
        clickButton("button7");
        clickButton("multiplyButton");
        clickButton("button7");
        clickButton("computeButton");

        assertEquals(resultLabel.getText(), "49");
    }

    // Description: Should perform division operation and show the result in `resultLabel` on `computeButton` click.
    @Test
    public void shouldPerformDivision() {
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        
        clickButton("button6");
        clickButton("divideButton");
        clickButton("button3");
        clickButton("computeButton");

        assertEquals(resultLabel.getText(), "2");
    }

    // Description: Should perform clear operation and clear the `resultLabel` on `clearButton` click.
    @Test
    public void shouldPerformClear() {
        resultLabel = (Label) TestUtils.findComponent("resultLabel", true);
        
        clickButton("button6");
        clickButton("addButton");
        clickButton("button3");
        clickButton("button4");
        clickButton("clearButton");

        assertEquals(resultLabel.getText(), "");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

