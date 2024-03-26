package com.codechum;

import com.codechum.TestUtils;
import com.codechum.awt.panels.PanelMaster;
import static org.testng.Assert.*;

import java.awt.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class AWTPanelMasterTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;

    Panel panelA, panelB, panelC;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(PanelMaster .class).start();
        
        robot().waitForIdle();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = finder.find(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        });
    }


    @Test
    public void shouldHavePanelAInFrame() {
        panelA = (Panel) TestUtils.getChildNamed(frame, "panelA");
        assertNotNull(panelA, "No panelA found.");
    }

    @Test
    public void shouldHavePanelBInFrame() {
        panelB = (Panel) TestUtils.getChildNamed(frame, "panelB");
        assertNotNull(panelB, "No panelB found.");
    }

    @Test
    public void shouldHavePanelCInFrame() {
        panelC = (Panel) TestUtils.getChildNamed(frame, "panelC");
        assertNotNull(panelC, "No panelC found.");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
