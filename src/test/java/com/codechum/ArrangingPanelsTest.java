package com.codechum;

import com.codechum.TestUtils;
import com.codechum.awt.panels.ArrangingPanels;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ArrangingPanelsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    Panel mainPanel, subPanelA, subPanelB;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ArrangingPanels.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a panel named `mainPanel`.
    @Test
    public void shouldHavePanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        assertNotNull(mainPanel, "No mainPanel found.");
    }

    // Description: Should have a panel named `subPanelA` in `mainPanel`.
    @Test
    public void shouldHavePanelSubAInPanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertNotNull(subPanelA, "No subPanelA found.");
    }

    // Description: Should have a panel named `subPanelB` in `mainPanel`.
    @Test
    public void shouldHavePanelSubBInPanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertNotNull(subPanelB, "No subPanelB found.");
    }

    // Description: Should have an x position of 10 for `subPanelA`
    @Test
    public void subPanelAShouldBeInCorrectXPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertEquals(subPanelA.getLocation().getX(), 10);
    }

    // Description: Should have a y position of 10 for `subPanelA`
    @Test
    public void subPanelAShouldBeInCorrectYPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertEquals(subPanelA.getLocation().getY(), 10);
    }

    // Description: Should have an x position of 200 for `subPanelB`
    @Test
    public void subPanelBShouldBeInCorrectXPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertEquals(subPanelB.getLocation().getX(), 200);
    }

    // Description: Should have a y position of 90 for `subPanelB`
    @Test
    public void subPanelBShouldBeInCorrectYPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertEquals(subPanelB.getLocation().getY(), 90);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
