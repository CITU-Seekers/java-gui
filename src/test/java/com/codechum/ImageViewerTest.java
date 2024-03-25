package com.codechum;

import com.codechum.TestUtils;
import com.codechum.awt.image.ImageViewer;
import static org.testng.Assert.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ImageViewerTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;

    Button openButton;
    Label statusLabel;
    Panel imagePanel, controlPanel;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ImageViewer.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        openButton = (Button) TestUtils.findComponent("openButton", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        imagePanel = (Panel) TestUtils.findComponent("imagePanel", true);
        controlPanel = (Panel) TestUtils.findComponent("controlPanel", true);

        assertNotNull(openButton, "No openButton found.");
        assertNotNull(statusLabel, "No statusLabel found.");
        assertNotNull(imagePanel, "No imagePanel found.");
        assertNotNull(controlPanel, "No controlPanel found.");
    }

    @Test
    public void shouldDisplayImage() {
        openButton = (Button) TestUtils.findComponent("openButton", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(openButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        // Select an image file in the dialog
        if (fd != null) {
            robot().pressAndReleaseKeys(
                    KeyEvent.VK_T,
                    KeyEvent.VK_E,
                    KeyEvent.VK_S,
                    KeyEvent.VK_T,
                    KeyEvent.VK_I,
                    KeyEvent.VK_M,
                    KeyEvent.VK_A,
                    KeyEvent.VK_G,
                    KeyEvent.VK_E,
                    KeyEvent.VK_PERIOD,
                    KeyEvent.VK_P, KeyEvent.VK_N, KeyEvent.VK_G, KeyEvent.VK_ENTER);
            robot().waitForIdle();
        }

        assertEquals(statusLabel.getText(), "Status: Image Loaded - testImage.png");
    }

}
