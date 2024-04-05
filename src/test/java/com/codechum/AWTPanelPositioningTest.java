package com.codechum;

import com.codechum.awt.panels.PanelPositioning;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class AWTPanelPositioningTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    Panel panelA, panelB;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PanelPositioning.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a panel named `panelA`.
    @Test
    public void shouldHavePanelA() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertNotNull(panelA, "No panelA found.");
    }

    // Description: Should have a panel named `panelB`.
    @Test
    public void shouldHavePanelB() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertNotNull(panelB, "No panelB found.");
    }

    // Description: Should have an x position of 155 for `panelA`
    @Test
    public void panelAShouldBeInCorrectXPosition() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getX(), 155);
    }

    // Description: Should have a y position of 55 for `panelA`
    @Test
    public void panelAShouldBeInCorrectYPosition() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getY(), 55);
    }

    // Description: Should have an x position of 200 for `panelB`
    @Test
    public void panelBShouldBeInCorrectXPosition() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getX(), 200);
    }

    // Description: Should have a y position of 35 for `panelB`
    @Test
    public void panelBShouldBeInCorrectYPosition() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getY(), 35);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
