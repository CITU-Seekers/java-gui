package com.codechum;

import com.codechum.swing.jPanel.PanelPositioning;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingPanelPositioningTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    JPanel panelA, panelB;

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
        panelA = (JPanel) TestUtils.getChildNamed(frame, "panelA");
        assertNotNull(panelA, "No panelA found.");
    }

    @Test
    public void shouldHavePanelB() {
        panelB = (JPanel) TestUtils.getChildNamed(frame, "panelB");
        assertNotNull(panelB, "No panelB found.");
    }

    @Test
    public void panelAShouldBeInCorrectXPosition() {
        panelA = (JPanel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getX(), 155);
    }

    @Test
    public void panelAShouldBeInCorrectYPosition() {
        panelA = (JPanel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getY(), 55);
    }

    @Test
    public void panelBShouldBeInCorrectXPosition() {
        panelB = (JPanel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getX(), 200);
    }

    @Test
    public void panelBShouldBeInCorrectYPosition() {
        panelB = (JPanel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getY(), 35);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
