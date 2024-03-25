package com.codechum;

import com.codechum.TestUtils;
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

public class PanelPositioningTest extends AssertJSwingTestngTestCase {
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

    @Test
    public void shouldHavePanelA() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertNotNull(panelA, "No panelA found.");
    }

    @Test
    public void shouldHavePanelB() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertNotNull(panelB, "No panelB found.");
    }

    @Test
    public void panelAShouldBeInCorrectXPosition() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getX(), 155);
    }

    @Test
    public void panelAShouldBeInCorrectYPosition() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getY(), 55);
    }

    @Test
    public void panelBShouldBeInCorrectXPosition() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getX(), 200);
    }

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
