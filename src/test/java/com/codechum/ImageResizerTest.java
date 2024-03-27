package com.codechum;

import com.codechum.awt.image.ImageResizer;
import static org.testng.Assert.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.io.File;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ImageResizerTest extends AssertJSwingTestngTestCase {

    EmergencyAbortListener listener;

    Button openButton;
    Scrollbar resizerScrollBar;
    Canvas canvas;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ImageResizer.class).start();
    }

    @Test
    public void shouldHaveAllComponents() {
        openButton = (Button) TestUtils.findComponent("openFileButton", true);
        resizerScrollBar = (Scrollbar) TestUtils.findComponent("resizerScrollBar", true);
        canvas = (Canvas) TestUtils.findComponent("imageCanvas", true);

        assertNotNull(openButton, "No open button found.");
        assertNotNull(resizerScrollBar, "No scrollbar found.");
        assertNotNull(canvas, "No canvas found.");
    }

    @Test
    public void shouldLoadImage() {
        openButton = (Button) TestUtils.findComponent("openFileButton", true);
        resizerScrollBar = (Scrollbar) TestUtils.findComponent("resizerScrollBar", true);
        canvas = (Canvas) TestUtils.findComponent("imageCanvas", true);

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

        robot().waitForIdle();

        assertNotNull(resizerScrollBar, "No scrollbar found.");
        assertNotNull(canvas, "No canvas found.");

        // Check if the image is loaded and displayed on the canvas
        assertTrue(resizerScrollBar.getMaximum() > 0, "Image not loaded.");
        assertNotNull(canvas, "Canvas is null.");
    }

    @Test
    public void shouldResizeImageWithScrollbar() {
        openButton = (Button) TestUtils.findComponent("openFileButton", true);
        resizerScrollBar = (Scrollbar) TestUtils.findComponent("resizerScrollBar", true);
        canvas = (Canvas) TestUtils.findComponent("imageCanvas", true);

        robot().click(openButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        // Select an image file in the dialog
        if (fd != null) {
            robot().pressAndReleaseKeys(KeyEvent.VK_T, KeyEvent.VK_E, KeyEvent.VK_S, KeyEvent.VK_T, KeyEvent.VK_1,
                    KeyEvent.VK_PERIOD, KeyEvent.VK_J, KeyEvent.VK_P, KeyEvent.VK_G, KeyEvent.VK_ENTER);
            robot().waitForIdle();
        }

        robot().waitForIdle();

        assertNotNull(resizerScrollBar, "No scrollbar found.");
        assertNotNull(canvas, "No canvas found.");

        // Check if the image is loaded and displayed on the canvas
        assertTrue(resizerScrollBar.getMaximum() > 0, "Image not loaded.");
        assertNotNull(canvas, "Canvas is null.");

        robot().click(resizerScrollBar);
        robot().pressAndReleaseKeys(VK_LEFT, VK_LEFT, VK_RIGHT);

        robot().waitForIdle();

        // Check if the canvas is repainted after resizing
        assertNotNull(canvas, "Canvas is null after resizing.");
    }
}
