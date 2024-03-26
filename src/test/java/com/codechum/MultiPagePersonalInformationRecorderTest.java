package com.codechum;

import com.codechum.swing.swingLayouts.MultipagePersonalInformationRecorder;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

import org.assertj.swing.fixture.FrameFixture;

public class MultiPagePersonalInformationRecorderTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frameFixture;
    JFrame frame;

    JTextField nameTextField, ageTextField;
    JTextArea quoteTextArea;
    JPanel cardPanel, firstPanel, secondPanel, thirdPanel;
    JLabel nameLabel, ageLabel, quoteLabel;
    JButton nextButton, prevButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MultipagePersonalInformationRecorder.class).start();
        frameFixture = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
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
        
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveCardPanelInCenter() {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        Component comp  = layout.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(comp, "No cardPanel found.");
    }
    
    @Test
    public void shouldHaveAllButtonsAndPanels() {
        prevButton = (JButton) TestUtils.getChildNamed(frame, "prevButton");
        firstPanel = (JPanel) TestUtils.getChildNamed(frame, "firstPanel");
        secondPanel = (JPanel) TestUtils.getChildNamed(frame, "secondPanel");
        thirdPanel = (JPanel) TestUtils.getChildNamed(frame, "thirdPanel");
        
        assertNotNull(frameFixture.button("nextButton"), "No nextButton found.");
        assertNotNull(prevButton, "No btPrev found.");
        assertNotNull(firstPanel, "No firstPanel found.");
        assertNotNull(secondPanel, "No secondPanel found.");
        assertNotNull(thirdPanel, "No thirdPanel found.");
    }

    @Test
    public void secondAndThirdPanelShouldNotBeVisibleByDefault() {
        secondPanel = (JPanel) TestUtils.getChildNamed(frame, "secondPanel");
        thirdPanel = (JPanel) TestUtils.getChildNamed(frame, "thirdPanel");
        
        assertFalse(secondPanel.isVisible(), "secondPanel is visible.");
        assertFalse(thirdPanel.isVisible(), "secondPanel is visible.");
    }
    
    @Test
    public void cardPanelShouldHaveCardLayout() {
        cardPanel = (JPanel) TestUtils.findComponent("cardPanel", true);
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        
        assertTrue(layout.toString().contains("CardLayout"), "cardPanel should have CardLayout");
    }

    @Test
    public void firstPanelShouldContainCorrectComponentsAndLayout() {
        firstPanel = (JPanel) TestUtils.findComponent("firstPanel", true);
        GridLayout layout = (GridLayout) firstPanel.getLayout();
        nameTextField = (JTextField) TestUtils.getChildNamed(firstPanel, "nameTextField");
        ageTextField = (JTextField) TestUtils.getChildNamed(firstPanel, "ageTextField");
        
        assertTrue(layout.toString().contains("GridLayout"), "firstPanel should have GridLayout");
        assertNotNull(nameTextField, "No nameTextField found in firstPanel");
        assertNotNull(ageTextField, "No ageTextField found in firstPanel.");
    }

    @Test
    public void secondPanelShouldContainCorrectComponentsAndLayout() {
        secondPanel = (JPanel) TestUtils.getChildNamed(frame, "secondPanel");
        BoxLayout layout = (BoxLayout) secondPanel.getLayout();
        quoteTextArea = (JTextArea) TestUtils.getChildNamed(secondPanel, "quoteTextArea");
        
        assertTrue(layout.toString().contains("BoxLayout"), "secondPanel should have BoxLayout");
        assertNotNull(quoteTextArea, "No quoteTextArea found in secondPanel.");
    }

    @Test
    public void thirdPanelShouldContainCorrectComponentsAndLayout() {
        thirdPanel = (JPanel) TestUtils.getChildNamed(frame, "thirdPanel");
        GridLayout layout = (GridLayout) thirdPanel.getLayout();
        nameLabel = (JLabel) TestUtils.getChildNamed(thirdPanel, "nameLabel");
        ageLabel = (JLabel) TestUtils.getChildNamed(thirdPanel, "ageLabel");
        quoteLabel = (JLabel) TestUtils.getChildNamed(thirdPanel, "quoteLabel");
        
        assertTrue(layout.toString().contains("GridLayout"), "thirdPanel should have GridLayout");
        assertNotNull(nameLabel,"No nameLabel found in thirdPanel.");
        assertNotNull(ageLabel,"No ageLabel found in thirdPanel.");
        assertNotNull(quoteLabel,"No quoteLabel found in thirdPanel.");
    }

    @Test
    public void shouldNotDisplayPrevButtonOnFirstPage() {
        prevButton = (JButton) TestUtils.getChildNamed(frame, "prevButton");
        assertFalse(prevButton.isVisible(),"prevButton is visible.");
    }

    @Test
    public void shouldNotDisplayNextButtonOnLastPage() {
        frameFixture.button("nextButton").click().click();
        
        nextButton = (JButton) TestUtils.getChildNamed(frame, "nextButton");
        
        assertFalse(nextButton.isVisible(),"nextButton should not be visible on last page.");
    }

    @Test
    public void shouldBeAbleToDisplayValuesInLastPage() {
        frameFixture
            .textBox("nameTextField")
            .pressAndReleaseKeys(VK_J, VK_O, VK_H, VK_N, VK_SPACE, VK_D, VK_O, VK_E);
        frameFixture
            .textBox("ageTextField")
            .pressAndReleaseKeys(VK_1, VK_8);
        
        frameFixture.button("nextButton").click();
        robot().waitForIdle();
        
        frameFixture
            .textBox("quoteTextArea")
            .pressAndReleaseKeys(VK_A, VK_SPACE, VK_Q, VK_U, VK_O, VK_T, VK_E);
        
        frameFixture.button("nextButton").click();
        robot().waitForIdle();
        
        nameLabel = (JLabel) TestUtils.findComponent("nameLabel", true);
        ageLabel = (JLabel) TestUtils.findComponent("ageLabel", true);
        quoteLabel = (JLabel) TestUtils.findComponent("quoteLabel", true);
        
        assertEquals(nameLabel.getText(), "john doe");
        assertEquals(ageLabel.getText(), "18");
        assertEquals(quoteLabel.getText(), "a quote");
    }

    @Test
    public void shouldProperlyDisplayPanelsOnNextButtonClick() {
        firstPanel = (JPanel) TestUtils.findComponent("firstPanel", true);

        assertTrue(firstPanel.isVisible(), "firstPanel is not visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");
        
        frameFixture.button("nextButton").click();
        secondPanel = (JPanel) TestUtils.findComponent("secondPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertTrue(secondPanel.isVisible(), "secondPanel is not visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");

        frameFixture.button("nextButton").click();
        thirdPanel = (JPanel) TestUtils.findComponent("thirdPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertTrue(thirdPanel.isVisible(), "thirdPanel is not visible");
    }

    @Test
    public void shouldProperlyDisplayPanelsOnPrevButtonClick() {
        frameFixture.button("nextButton").click().click();
        
        thirdPanel = (JPanel) TestUtils.findComponent("thirdPanel", true);

        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertTrue(thirdPanel.isVisible(), "thirdPanel is not visible");


        frameFixture.button("prevButton").click();
        secondPanel = (JPanel) TestUtils.findComponent("secondPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertTrue(secondPanel.isVisible(), "secondPanel is not visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");

        frameFixture.button("prevButton").click();
        
        firstPanel = (JPanel) TestUtils.findComponent("firstPanel", true);
        assertTrue(firstPanel.isVisible(), "firstPanel is not visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
