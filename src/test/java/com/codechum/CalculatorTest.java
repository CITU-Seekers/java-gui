package com.codechum;

import com.codechum.swing.swingLayouts.Calculator;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CalculatorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frameFixture;
    JFrame frame;

    JPanel buttonsPanel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(Calculator.class).start();
        
        frameFixture = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should use BorderLayout for the frame.
    @Test
    public void shouldUseBorderLayout() {
        BorderLayout layout = (BorderLayout) frame.getLayout();
        assertTrue(layout.toString().contains("BorderLayout"), "should use BorderLayout.");
    }

    // Description: Should have `resultLabel` at the NORTH location of the frame.
    @Test
    public void shouldContainLblResultInNorth() {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        Component comp = layout.getLayoutComponent(BorderLayout.NORTH);
        comp = comp != null ? comp : layout.getLayoutComponent(BorderLayout.PAGE_START);
        assertNotNull(comp, "No resultLabel found.");
    }
    
    // Description: Should have `buttonsPanel` at the CENTER location of the frame.
    @Test
    public void shouldContainPnlButtonsWithGridLayoutInCenter() {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        buttonsPanel = (JPanel) layout.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(buttonsPanel, "No buttonsPanel found.");
        
        GridLayout compLayout = (GridLayout) buttonsPanel.getLayout();
        assertTrue(compLayout.toString().contains("GridLayout"), "buttonsPanel should have GridLayout");
    }

    // Description: Should have the number buttons contained inside `buttonsPanel` named `button0` to `button9`.
    @Test
    public void shouldContainNumberButtonsInPnlButtons() {
        buttonsPanel = (JPanel) TestUtils.getChildNamed(frame, "buttonsPanel");

        for (int i = 0; i < 10; i++) {
            JButton buttonNumber = (JButton) TestUtils.getChildNamed(buttonsPanel, "button" + i);
            assertNotNull(buttonNumber, "No button"+ i + " found.");
        }
    }

    // Description: Should have the operation buttons contained inside `buttonsPanel` named `addButton`, `subtractButton`, `multiplyButton`, `divideButton`, `clearButton`, `computeButton`.
    @Test
    public void shouldContainBtnOperationsInPnlButtons() {
        String[] operations = {"addButton", "subtractButton", "multiplyButton", "divideButton", "clearButton", "computeButton"};
        buttonsPanel = (JPanel) TestUtils.getChildNamed(frame, "buttonsPanel");

        for (String operation : operations) {
            JButton buttonOperation = (JButton) TestUtils.getChildNamed(buttonsPanel, operation);
            assertNotNull(buttonOperation, "No " + operation + " found.");
        }
    }

    // Description: Should perform addition operation and display the result in `resultLabel` when `addButton` is clicked.
    @Test
    public void shouldPerformAddition() {
        frameFixture.button("button5").click();
        frameFixture.button("addButton").click();
        frameFixture.button("button3").click();
        frameFixture.button("computeButton").click();

        assertEquals(frameFixture.label("resultLabel").text(), "8");
    }

    // Description: Should perform subtraction operation and display the result in `resultLabel` when `subtractButton` is clicked.
    @Test
    public void shouldPerformSubtraction() {
        frameFixture.button("button5").click();
        frameFixture.button("subtractButton").click();
        frameFixture.button("button2").click();
        frameFixture.button("computeButton").click();

        assertEquals(frameFixture.label("resultLabel").text(), "3");
    }

    // Description: Should perform multiplication operation and display the result in `resultLabel` when `multiplyButton` is clicked.
    @Test
    public void shouldPerformMultiplication() {
        frameFixture.button("button7").click();
        frameFixture.button("multiplyButton").click();
        frameFixture.button("button7").click();
        frameFixture.button("computeButton").click();

        assertEquals(frameFixture.label("resultLabel").text(), "49");
    }

    // Description: Should perform division operation and display the result in `resultLabel` when `divideButton` is clicked.
    @Test
    public void shouldPerformDivision() {
        frameFixture.button("button6").click();
        frameFixture.button("divideButton").click();
        frameFixture.button("button3").click();
        frameFixture.button("computeButton").click();

        assertEquals(frameFixture.label("resultLabel").text(), "2");
    }

    // Description: Should clear the result in `resultLabel` when `clearButton` is clicked.
    @Test
    public void shouldPerformClear() {
        frameFixture.button("button6").click();
        frameFixture.button("addButton").click();
        frameFixture.button("button3").click();
        frameFixture.button("button4").click();
        frameFixture.button("clearButton").click();

        assertEquals(frameFixture.label("resultLabel").text(), "");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
