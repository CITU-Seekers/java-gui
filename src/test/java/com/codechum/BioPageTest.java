package com.codechum;

import com.codechum.awt.layouts.BioPage;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class BioPageTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    TextField nameTextField, ageTextField;
    TextArea quoteTextArea;
    Panel cardPanel, firstPanel, secondPanel, thirdPanel;
    Label nameLabel, ageLabel, quoteLabel;
    Button nextButton, prevButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(BioPage.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }
    
    public void clickButton(Button button) {
        robot().click(button);
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveCardPanelInCenter() {
        BorderLayout layout = (BorderLayout) frame.getLayout();
        Component comp  = layout.getLayoutComponent(BorderLayout.CENTER);
        assertNotNull(comp, "No cardPanel found.");
    }
    
    @Test
    public void hasButtonsAndPanels() {
        prevButton = (Button) TestUtils.getChildNamed(frame, "prevButton");
        nextButton = (Button) TestUtils.getChildNamed(frame, "nextButton");
        firstPanel = (Panel) TestUtils.getChildNamed(frame, "firstPanel");
        secondPanel = (Panel) TestUtils.getChildNamed(frame, "secondPanel");
        thirdPanel = (Panel) TestUtils.getChildNamed(frame, "thirdPanel");
        
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(prevButton, "No btPrev found.");
        assertNotNull(firstPanel, "No firstPanel found.");
        assertNotNull(secondPanel, "No secondPanel found.");
        assertNotNull(thirdPanel, "No thirdPanel found.");
    }

    @Test
    public void secondAndThirdPanelShouldNotBeVisibleByDefault() {
        secondPanel = (Panel) TestUtils.getChildNamed(frame, "secondPanel");
        thirdPanel = (Panel) TestUtils.getChildNamed(frame, "thirdPanel");
        
        assertFalse(secondPanel.isVisible(), "secondPanel is visible.");
        assertFalse(thirdPanel.isVisible(), "secondPanel is visible.");
    }
    
    @Test
    public void cardPanelShouldHaveCardLayout() {
        cardPanel = (Panel) TestUtils.findComponent("cardPanel", true);
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        
        assertTrue(layout.toString().contains("CardLayout"), "cardPanel should have CardLayout");
    }

    @Test
    public void firstPanelShouldContainCorrectComponentsAndLayout() {
        firstPanel = (Panel) TestUtils.findComponent("firstPanel", true);
        GridLayout layout = (GridLayout) firstPanel.getLayout();
        nameTextField = (TextField) TestUtils.getChildNamed(firstPanel, "nameTextField");
        ageTextField = (TextField) TestUtils.getChildNamed(firstPanel, "ageTextField");
        
        assertTrue(layout.toString().contains("GridLayout"), "firstPanel should have GridLayout");
        assertNotNull(nameTextField, "No nameTextField found in firstPanel");
        assertNotNull(ageTextField, "No ageTextField found in firstPanel.");
    }

    @Test
    public void secondPanelShouldContainCorrectComponents() {
        secondPanel = (Panel) TestUtils.getChildNamed(frame, "secondPanel");
        quoteTextArea = (TextArea) TestUtils.getChildNamed(secondPanel, "quoteTextArea");
        
        assertNotNull(quoteTextArea, "No quoteTextArea found in secondPanel.");
    }

    @Test
    public void thirdPanelShouldContainCorrectComponents() {
        thirdPanel = (Panel) TestUtils.getChildNamed(frame, "thirdPanel");
        GridLayout layout = (GridLayout) thirdPanel.getLayout();
        nameLabel = (Label) TestUtils.getChildNamed(thirdPanel, "nameLabel");
        ageLabel = (Label) TestUtils.getChildNamed(thirdPanel, "ageLabel");
        quoteLabel = (Label) TestUtils.getChildNamed(thirdPanel, "quoteLabel");
        
        assertTrue(layout.toString().contains("GridLayout"), "thirdPanel should have GridLayout");
        assertNotNull(nameLabel,"No nameLabel found in thirdPanel.");
        assertNotNull(ageLabel,"No ageLabel found in thirdPanel.");
        assertNotNull(quoteLabel,"No quoteLabel found in thirdPanel.");
    }

    @Test
    public void shouldNotDisplayPrevButtonOnFirstPage() {
        prevButton = (Button) TestUtils.getChildNamed(frame, "prevButton");
        assertFalse(prevButton.isVisible(),"prevButton is visible.");
    }

    @Test
    public void shouldNotDisplayNextButtonOnLastPage() {
        nextButton = (Button) TestUtils.getChildNamed(frame, "nextButton");
        
        clickButton(nextButton);
        clickButton(nextButton);
        
        assertFalse(nextButton.isVisible(),"nextButton should not be visible on last page.");
    }

    @Test
    public void shouldBeAbleToDisplayValuesInLastPage() {
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        ageTextField = (TextField) TestUtils.findComponent("ageTextField", true);
        nextButton = (Button) TestUtils.getChildNamed(frame, "nextButton");
        
        robot().click(nameTextField);
        robot().pressAndReleaseKeys(VK_J, VK_O, VK_H, VK_N, VK_SPACE, VK_D, VK_O, VK_E);
        
        robot().click(ageTextField);
        robot().pressAndReleaseKeys(VK_1, VK_8);
        
        clickButton(nextButton);
        
        quoteTextArea = (TextArea) TestUtils.findComponent("quoteTextArea", true);
        
        robot().click(quoteTextArea);
        robot().pressAndReleaseKeys(VK_A, VK_SPACE, VK_Q, VK_U, VK_O, VK_T, VK_E);
        
        clickButton(nextButton);
        
        nameLabel = (Label) TestUtils.findComponent("nameLabel", true);
        ageLabel = (Label) TestUtils.findComponent("ageLabel", true);
        quoteLabel = (Label) TestUtils.findComponent("quoteLabel", true);
        
        assertEquals(nameLabel.getText(), "john doe");
        assertEquals(ageLabel.getText(), "18");
        assertEquals(quoteLabel.getText(), "a quote");
    }

    @Test
    public void shouldProperlyDisplayPanelsOnBtnNextClick() {
        firstPanel = (Panel) TestUtils.findComponent("firstPanel", true);
        nextButton = (Button) TestUtils.getChildNamed(frame, "nextButton");

        assertTrue(firstPanel.isVisible(), "firstPanel is not visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");
        
        clickButton(nextButton);
        secondPanel = (Panel) TestUtils.findComponent("secondPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertTrue(secondPanel.isVisible(), "secondPanel is not visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");

        clickButton(nextButton);
        thirdPanel = (Panel) TestUtils.findComponent("thirdPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertTrue(thirdPanel.isVisible(), "thirdPanel is not visible");
    }

    @Test
    public void shouldProperlyDisplayPanelsOnBtnPrevClick() {
        nextButton = (Button) TestUtils.getChildNamed(frame, "nextButton");
        prevButton = (Button) TestUtils.getChildNamed(frame, "prevButton");
        clickButton(nextButton);
        clickButton(nextButton);
        
        thirdPanel = (Panel) TestUtils.findComponent("thirdPanel", true);

        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertTrue(thirdPanel.isVisible(), "thirdPanel is not visible");


        clickButton(prevButton);
        secondPanel = (Panel) TestUtils.findComponent("secondPanel", true);
        
        assertFalse(firstPanel.isVisible(), "firstPanel is visible.");
        assertTrue(secondPanel.isVisible(), "secondPanel is not visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");

        clickButton(prevButton);
        
        firstPanel = (Panel) TestUtils.findComponent("firstPanel", true);
        assertTrue(firstPanel.isVisible(), "firstPanel is not visible.");
        assertFalse(secondPanel.isVisible(), "secondPanel is visible");
        assertFalse(thirdPanel.isVisible(), "thirdPanel is visible");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
