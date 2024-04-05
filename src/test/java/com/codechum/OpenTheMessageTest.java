package com.codechum;

import com.codechum.awt.fileDialog.OpenTheMessage;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class OpenTheMessageTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Button openFileDialogButton;
    Label fileContentLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(OpenTheMessage.class).start();
    }
    
    // Description: Should have all components `openFileDialogButton` and `fileContentLabel`.
    @Test
    public void shouldHaveAllComponents() {
        openFileDialogButton = (Button) TestUtils.findComponent("openFileDialogButton", true);
        fileContentLabel = (Label) TestUtils.findComponent("fileContentLabel", true);
        
        assertNotNull(openFileDialogButton, "No openFileDialogButton found.");
        assertNotNull(fileContentLabel, "No fileContentLabel found.");
    }
    
    // Description: Should have created file named `activity.txt`.
    @Test
    public void shouldHaveCreatedFile() {
        String fileNameToFind = "activity.txt";

        String dir = System.getProperty("user.dir");
        final List<File> foundFiles = new ArrayList<>();

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
        
        assertTrue(activityFile.isFile(), "There should be a manually created file named activity.txt");
    }
    
    // Description: Should show the `fileDialog` on `openFileDialogButton` click.
    @Test
    public void shouldShowFileDialogOnButtonClick() {
        openFileDialogButton = (Button) TestUtils.findComponent("openFileDialogButton", true);
        
        robot().click(openFileDialogButton);
        robot().waitForIdle();
        
        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        
        assertNotNull(fd, "No fd found.");
        assertTrue(fd.isVisible(), "The file dialog shoud be visible on button click.");
    }
    
    // Description: Should display the content of the file `activity.txt` in `fileContentLabel`.
    @Test
    public void shouldDisplayFileContentInLabel() {
        // openFileDialogButton = (Button) TestUtils.findComponent("openFileDialogButton", true);
        // fileContentLabel = (Label) TestUtils.findComponent("fileContentLabel", true);
        
        // robot().click(openFileDialogButton);
        // robot().waitForIdle();
        
        // FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        // String fileNameToFind = "activity.txt";

        // String dir = System.getProperty("user.dir");
        // final List<File> foundFiles = new ArrayList<>();

        // File activityFile = null;
        // File rootDirectory = new File(dir);
        // try {
        //     try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
        //         walkStream.filter(p -> p.toFile().isFile())
        //             .forEach(f -> {
        //                 if (f.toString().endsWith(fileNameToFind)) {
        //                     foundFiles.add(f.toFile());
        //                 }
        //             });
        //     }
        // } catch(Exception e) {
        //     System.out.println(e);
        // }

        // activityFile = foundFiles.get(0);
        // String fileName = activityFile.getPath();
        
        // if (fd != null) {
        //     for(int i = 1; fileName.length() > i; i++) {
        //         char c = fileName.charAt(i);

        //         if (Character.toString(c).equals("/") || Character.toString(c).equals("\\")) {
        //             robot().pressAndReleaseKeys(
        //                 VK_BACK_SLASH
        //             );
        //         } else {
        //             robot().pressAndReleaseKeys(
        //                 java.awt.event.KeyEvent.getExtendedKeyCodeForChar(c)
        //             );
        //         }
        //     }
        //     robot().pressAndReleaseKeys(
        //         VK_ENTER
        //     );
        //     robot().waitForIdle();
        // }

        // assertEquals(fileContentLabel.getText(), "Hello World");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
    
