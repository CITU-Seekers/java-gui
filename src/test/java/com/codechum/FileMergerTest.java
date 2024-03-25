package com.codechum;

import com.codechum.TestUtils;
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
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FileMerger.class).start();
    }
    
    @Test
    public void shouldHaveCreatedFiles() {
        File file = new File("test1.txt");
        File file2 = new File("test2.txt");

        String content1 = "Hello";
        String content2 = " World";

        writeFile("test1.txt", content1);
        writeFile("test2.txt", content2);

        assertTrue(file.exists(), "File 1 does not exist.");
        assertTrue(file2.exists(), "File 2 does not exist.");
    }
    
    @Test
    public void shouldHaveAllComponents() {
        openFileButton = (Button) TestUtils.findComponent("openFileButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);
        
        assertNotNull(openFileButton, "No openFileButton found.");
        assertNotNull(contentTextField, "No contentTextField found.");
    }
    
    @Test
    public void shouldDisplayMergedFilesContentInTextField() {
        shouldHaveCreatedFiles();
        openFileButton = (Button) TestUtils.findComponent("openFileButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);

        robot().click(openFileButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        // select multiple files in the dialog
        if (fd != null) {
            fd.setMultipleMode(true);
            robot().pressAndReleaseKeys(VK_T, VK_E, VK_S, VK_T, VK_1,VK_PERIOD,
                    VK_T,
                    VK_X,
                    VK_T,
                    VK_ENTER);
            robot().waitForIdle();
        }
        
        robot().click(openFileButton);
        robot().waitForIdle();
        
        if (fd != null) {
            fd.setMultipleMode(true);
            robot().pressAndReleaseKeys(VK_T, VK_E, VK_S, VK_T, VK_2,VK_PERIOD,
                    VK_T,
                    VK_X,
                    VK_T,
                    VK_ENTER);
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
