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

    @Test
    public void shouldHavePanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        assertNotNull(mainPanel, "No mainPanel found.");
    }

    @Test
    public void shouldHavePanelSubAInPanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertNotNull(subPanelA, "No subPanelA found.");
    }

    @Test
    public void shouldHavePanelSubBInPanelMain() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertNotNull(subPanelB, "No subPanelB found.");
    }

    @Test
    public void subPanelAShouldBeInCorrectXPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertEquals(subPanelA.getLocation().getX(), 10);
    }

    @Test
    public void subPanelAShouldBeInCorrectYPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertEquals(subPanelA.getLocation().getY(), 10);
    }

    @Test
    public void subPanelBShouldBeInCorrectXPosition() {
        mainPanel = (Panel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (Panel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertEquals(subPanelB.getLocation().getX(), 200);
    }

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
