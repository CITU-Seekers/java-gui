package com.codechum;

import com.codechum.swing.jPanel.PanelMaster;
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

public class SwingPanelMasterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    JPanel panelA, panelB, panelC;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PanelMaster.class).start();
        
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
    public void shouldHavePanelC() {
        panelC = (JPanel) TestUtils.getChildNamed(frame, "panelC");
        assertNotNull(panelC, "No panelC found.");
    }

    @Test
    public void panelAShouldBeInCorrectXPosition() {
        panelA = (JPanel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getX(), 50);
    }

    @Test
    public void panelAShouldBeInCorrectYPosition() {
        panelA = (JPanel) TestUtils.getChildNamed(frame, "panelA");
        assertEquals(panelA.getLocation().getY(), 50);
    }

    @Test
    public void panelBShouldBeInCorrectXPosition() {
        panelB = (JPanel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getX(), 100);
    }

    @Test
    public void panelBShouldBeInCorrectYPosition() {
        panelB = (JPanel) TestUtils.getChildNamed(frame, "panelB");
        assertEquals(panelB.getLocation().getY(), 20);
    }

    @Test
    public void panelCShouldBeInCorrectXPosition() {
        panelC = (JPanel) TestUtils.getChildNamed(frame, "panelC");
        assertEquals(panelC.getLocation().getX(), 300);
    }

    @Test
    public void panelCShouldBeInCorrectYPosition() {
        panelC = (JPanel) TestUtils.getChildNamed(frame, "panelC");
        assertEquals(panelC.getLocation().getY(), 100);
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
