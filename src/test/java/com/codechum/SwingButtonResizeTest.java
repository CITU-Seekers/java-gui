package com.codechum;

import com.codechum.swing.swingEventAdapters.ButtonResize;
import java.awt.Frame;
import static org.testng.Assert.*;

import javax.swing.*;

import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingButtonResizeTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    JRadioButton smallRadioButton, mediumRadioButton, largeRadioButton;
    JLabel helperLabel;
    JButton mainButton;

    @Override
    protected void onSetUp() {
        application(ButtonResize.class).start();
        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    public void simulateRadioButtonClick(String radioButtonName, int expectedBtnHeight, int expectedBtnWidth) {
        mainButton = (JButton) TestUtils.findComponent("mainButton", true);
        frame.radioButton(radioButtonName).click();
        String buttonSize = "height: " + mainButton.getHeight() + ", width: " + mainButton.getWidth();
        assertEquals(buttonSize, "height: " +  expectedBtnHeight + ", width: " + expectedBtnWidth);
    }

    @Test
    public void shouldHaveAllComponents() {
        String[] radioButtons = {"smallRadioButton", "mediumRadioButton", "largeRadioButton"};
        mainButton = (JButton) TestUtils.findComponent("mainButton", true);
        helperLabel = (JLabel) TestUtils.findComponent("helperLabel", true);

        for (String radioButton : radioButtons) {
            JRadioButton radButton = (JRadioButton) TestUtils.findComponent(radioButton, true);
            assertNotNull(radButton, "No " + radioButton + " found.");
        }
        
        assertNotNull(mainButton, "No mainButton found.");
        assertNotNull(helperLabel, "No helperLabel found.");
    }

    @Test
    public void radSmallShouldBeSelectedByDefault() {
        JRadioButton smallRadioButton = (JRadioButton) TestUtils.findComponent("smallRadioButton", true);
        assertTrue(smallRadioButton.isSelected(), "smallRadioButton should be selected by default");
    }

    @Test
    public void helperMessageShouldBeDisplayedOnHover() {
        smallRadioButton = (JRadioButton) TestUtils.findComponent("smallRadioButton", true);
        mediumRadioButton = (JRadioButton) TestUtils.findComponent("mediumRadioButton", true);
        largeRadioButton = (JRadioButton) TestUtils.findComponent("largeRadioButton", true);
        helperLabel = (JLabel) TestUtils.findComponent("helperLabel", true);

        robot().moveMouse(smallRadioButton);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on smallRadioButton");

        robot().moveMouse(mediumRadioButton);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on mediumRadioButton");

        robot().moveMouse(largeRadioButton);
        robot().waitForIdle();
        assertTrue(helperLabel.getText().length() > 0, "There should be a message when mouse is hovered on largeRadioButton");
    }

    @Test
    public void shouldChangeSizeOnRadSmallClick() {
        simulateRadioButtonClick("smallRadioButton", 30, 80);
    }

    @Test
    public void shouldChangeSizeOnRadMediumClick() {
        simulateRadioButtonClick("mediumRadioButton", 60, 160);
    }

    @Test
    public void shouldChangeSizeOnRadLargeClick() {
        simulateRadioButtonClick("largeRadioButton", 90, 240);
    }
}
