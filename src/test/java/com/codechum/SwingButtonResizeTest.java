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

    // Description: Should have all components `smallRadioButton`, `mediumRadioButton`, `largeRadioButton`, `mainButton`, and `helperLabel`.
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

    // Description: Should have the `smallRadioButton` selected by default.
    @Test
    public void ShouldBeSelectedByDefaultRadioSmall() {
        JRadioButton smallRadioButton = (JRadioButton) TestUtils.findComponent("smallRadioButton", true);
        assertTrue(smallRadioButton.isSelected(), "smallRadioButton should be selected by default");
    }

    // Description: Should display a helper message on `helperLabel` when mouse pointer is hovered on any radio button.
    @Test
    public void shouldBeDisplayedOnHoverHelperMessage() {
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

    // Description: Should change the size of the `mainButton` to 30x80 when `smallRadioButton` is selected.
    @Test
    public void shouldChangeSizeOnRadSmallClick() {
        simulateRadioButtonClick("smallRadioButton", 30, 80);
    }

    // Description: Should change the size of the `mainButton` to 60x160 when `mediumRadioButton` is selected.
    @Test
    public void shouldChangeSizeOnRadMediumClick() {
        simulateRadioButtonClick("mediumRadioButton", 60, 160);
    }

    // Description: Should change the size of the `mainButton` to 90x240 when `largeRadioButton` is selected.
    @Test
    public void shouldChangeSizeOnRadLargeClick() {
        simulateRadioButtonClick("largeRadioButton", 90, 240);
    }
}
