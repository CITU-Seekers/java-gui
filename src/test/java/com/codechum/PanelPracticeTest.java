package com.codechum;

import com.codechum.swing.jPanel.PanelPractice;
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

public class PanelPracticeTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    JPanel mainPanel, subPanelA, subPanelB;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PanelPractice.class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }

    // Description: Should have a JPanel named `mainPanel`.
    @Test
    public void shouldHavePanelMain() {
        mainPanel = (JPanel) TestUtils.getChildNamed(frame, "mainPanel");
        assertNotNull(mainPanel, "No mainPanel found.");
    }

    // Description: Should have a JPanel named `subPanelA` in `mainPanel`.
    @Test
    public void shouldHavePanelSubAInPanelMain() {
        mainPanel = (JPanel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelA = (JPanel) TestUtils.getChildNamed(mainPanel, "subPanelA");
        assertNotNull(subPanelA, "No subPanelA found.");
    }

    // Description: Should have a JPanel named `subPanelB` in `mainPanel`.
    @Test
    public void shouldHavePanelSubBInPanelMain() {
        mainPanel = (JPanel) TestUtils.getChildNamed(frame, "mainPanel");
        subPanelB = (JPanel) TestUtils.getChildNamed(mainPanel, "subPanelB");
        assertNotNull(subPanelB, "No subPanelB found.");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
