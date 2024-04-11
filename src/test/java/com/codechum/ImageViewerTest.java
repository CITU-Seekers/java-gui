package com.codechum;

import com.codechum.awt.image.ImageViewer;
import static org.testng.Assert.*;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static java.awt.event.KeyEvent.VK_BACK_SLASH;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_SEMICOLON;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_SLASH;
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

    // Description: Should have all components `openButton`, `statusLabel`, `imagePanel`, and `controlPanel`.
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

    // Description: Should have a file named `testImage.png`.
    @Test
    public void createFile() {
        String fileNameToFind = "testImage.png";

        String dir = System.getProperty("user.dir");
        final ArrayList<File> foundFiles = new ArrayList<>();

        File activityFile = null;
        File rootDirectory = new File(dir);
        try {
            try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
                walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith(fileNameToFind)) {
                            foundFiles.add(f.toFile());
                        }
                    });
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        activityFile = foundFiles.get(0);
        
        assertTrue(activityFile.isFile(), "There should be a manually created file named testImage.png in the project directory.");
    }

    // Description: Should loaded image from `fileDialog` and display it in the `imagePanel` and update the `statusLabel`.
    @Test
    public void shouldDisplayImage() {
        openButton = (Button) TestUtils.findComponent("openButton", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);

        robot().click(openButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        String dir = System.getProperty("user.dir");
        String fileNameToFind = "testImage.png";

        final ArrayList<File> foundFiles = new ArrayList<>();

        File activityFile = null;
        File rootDirectory = new File(dir);
        try {
            try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
                walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith(fileNameToFind)) {
                            foundFiles.add(f.toFile());
                        }
                    });
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        activityFile = foundFiles.get(0);

        String filePath = activityFile.getAbsolutePath();

        if (fd != null) {
            fd.setMultipleMode(true);

            for (int i = 0; i < filePath.length(); i++) {
                if (filePath.charAt(i) == '.') {
                    robot().pressAndReleaseKeys(VK_PERIOD);
                } else if (filePath.charAt(i) == ':') {
                    robot().pressKey(VK_SHIFT);   // Press the Shift key
                    robot().pressKey(VK_SEMICOLON); 
                    robot().releaseKey(VK_SHIFT);  // Release the semicolon key
                    robot().releaseKey(VK_SEMICOLON);  // Release the semicolon key  
                } else if (filePath.charAt(i) == '/') {
                    robot().pressAndReleaseKeys(VK_SLASH);
                } else if (filePath.charAt(i) == '\\') {
                    robot().pressAndReleaseKeys(VK_BACK_SLASH);
                } else {
                    robot().pressKey(String.valueOf(filePath.charAt(i)).toUpperCase().charAt(0));
                    robot().releaseKey(String.valueOf(filePath.charAt(i)).toUpperCase().charAt(0));
                }
            }

            robot().waitForIdle();
            robot().pressAndReleaseKeys(VK_ENTER);
            robot().waitForIdle();
        }
        assertEquals(statusLabel.getText(), "Status: Image Loaded - testImage.png");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
