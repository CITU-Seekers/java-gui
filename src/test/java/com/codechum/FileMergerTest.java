package com.codechum;

import com.codechum.awt.fileDialog.FileMerger;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FileMergerTest extends AssertJSwingTestngTestCase {
    
    EmergencyAbortListener listener;
    
    Button openFileButton;
    TextField contentTextField;
    File file1, file2;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FileMerger.class).start();

        file1 = new File("test1.txt");
        file2 = new File("test2.txt");

        String content1 = "Hello";
        String content2 = "World";

        writeFile("test1.txt", content1);
        writeFile("test2.txt", content2);
    }
    
    // Description: Should have created files `test1.txt` and `test2.txt`.
    @Test
    public void shouldHaveCreatedFiles() {
        assertTrue(file1.exists(), "File 1 does not exist.");
        assertTrue(file2.exists(), "File 2 does not exist.");
    }
    
    // Description: Should have all components `openFileButton` and `contentTextField`.
    @Test
    public void shouldHaveAllComponents() {
        openFileButton = (Button) TestUtils.findComponent("openFileButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);
        
        assertNotNull(openFileButton, "No openFileButton found.");
        assertNotNull(contentTextField, "No contentTextField found.");
    }
    
    // Description: Should display merged files content in `contentTextField`.
    @Test
    public void shouldDisplayMergedFilesContentInTextField() {
        shouldHaveCreatedFiles();
        openFileButton = (Button) TestUtils.findComponent("openFileButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);

        robot().click(openFileButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        String file1Path = file1.getAbsolutePath();
        String file2Path = file2.getAbsolutePath();

        // select multiple files in the dialog
        if (fd != null) {
            fd.setMultipleMode(true);

            for (int i = 0; i < file1Path.length(); i++) {
                if (file1Path.charAt(i) == '.') {
                    robot().pressAndReleaseKeys(VK_PERIOD);
                } else if (file1Path.charAt(i) == ':') {
                    robot().pressKey(VK_SHIFT);   // Press the Shift key
                    robot().pressKey(VK_SEMICOLON); 
                    robot().releaseKey(VK_SHIFT);  // Release the semicolon key
                    robot().releaseKey(VK_SEMICOLON);  // Release the semicolon key  
                } else if (file1Path.charAt(i) == '/') {
                    robot().pressAndReleaseKeys(VK_SLASH);
                } else if (file1Path.charAt(i) == '\\') {
                    robot().pressAndReleaseKeys(VK_BACK_SLASH);
                } else {
                    robot().pressKey(String.valueOf(file1Path.charAt(i)).toUpperCase().charAt(0));
                    robot().releaseKey(String.valueOf(file1Path.charAt(i)).toUpperCase().charAt(0));
                }
            }

            robot().pressAndReleaseKeys(VK_ENTER);
            robot().waitForIdle();
        }
        
        robot().click(openFileButton);
        robot().waitForIdle();
        
        if (fd != null) {
            fd.setMultipleMode(true);

            for (int i = 0; i < file2Path.length(); i++) {
                if (file2Path.charAt(i) == '.') {
                    robot().pressAndReleaseKeys(VK_PERIOD);
                } else if (file2Path.charAt(i) == ':') {
                    robot().pressKey(VK_SHIFT);   // Press the Shift key
                    robot().pressKey(VK_SEMICOLON); 
                    robot().releaseKey(VK_SHIFT);  // Release the semicolon key
                    robot().releaseKey(VK_SEMICOLON);  // Release the semicolon key  
                } else if (file2Path.charAt(i) == '/') {
                    robot().pressAndReleaseKeys(VK_SLASH);
                } else if (file2Path.charAt(i) == '\\') {
                    robot().pressAndReleaseKeys(VK_BACK_SLASH);
                } else {
                    robot().pressKey(String.valueOf(file2Path.charAt(i)).toUpperCase().charAt(0));
                    robot().releaseKey(String.valueOf(file2Path.charAt(i)).toUpperCase().charAt(0));
                }
            }

            robot().pressAndReleaseKeys(VK_ENTER);
            robot().waitForIdle();
        }

        assertEquals(contentTextField.getText(), "Hello World");
    }

    private void writeFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
