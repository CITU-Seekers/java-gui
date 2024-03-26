package com.codechum;

import com.codechum.swing.jList.ColorPaletteExplorer;
import java.awt.Frame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ColorPaletteExplorerTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;
    private FrameFixture frame;

    private JList colorList;
    private JButton displayColorButton;
    private JLabel colorLabel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ColorPaletteExplorer.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    @Test
    public void shouldHaveAllComponents() {
        colorList = (JList) TestUtils.findComponent("colorList", true);
        displayColorButton = (JButton) TestUtils.findComponent("displayColorButton", true);
        colorLabel = (JLabel) TestUtils.findComponent("colorLabel", true);

        assertNotNull(colorList, "No colorList found.");
        assertNotNull(displayColorButton, "No displayColorButton found.");
        assertNotNull(colorLabel, "No colorLabel found.");
    }

    @Test
    public void shouldBeAbleToDisplaySelectedColor() {
        colorList = (JList) TestUtils.findComponent("colorList", true);
        displayColorButton = (JButton) TestUtils.findComponent("displayColorButton", true);
        colorLabel = (JLabel) TestUtils.findComponent("colorLabel", true);

        frame.list("colorList").selectItem(0);
        robot().click(displayColorButton);
        robot().waitForIdle();

        String redColor = frame.label("colorLabel").text();
        assertEquals(redColor, "Red");
        
        
        frame.list("colorList").selectItem(1);
        robot().click(displayColorButton);
        robot().waitForIdle();

        String blueColor = frame.label("colorLabel").text();
        assertEquals(blueColor, "Blue");
        
        
        frame.list("colorList").selectItem(2);
        robot().click(displayColorButton);
        robot().waitForIdle();

        String greenColor = frame.label("colorLabel").text();
        assertEquals(greenColor, "Green");
        
        
        frame.list("colorList").selectItem(3);
        robot().click(displayColorButton);
        robot().waitForIdle();

        String yellowColor = frame.label("colorLabel").text();
        assertEquals(yellowColor, "Yellow");
        
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
