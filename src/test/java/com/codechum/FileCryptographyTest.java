package com.codechum;

import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import com.codechum.awt.fileDialog.FileCryptography;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FileCryptographyTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField contentTextField;
    Button openButton, encryptButton, decryptButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FileCryptography.class).start();
    }
    
    // Description: Should have all components `openButton`, `encryptButton`, `decryptButton`, and `contentTextField`.
    @Test 
    public void shouldHaveAllComponents(){
        openButton = (Button) TestUtils.findComponent("openButton", true);
        encryptButton = (Button) TestUtils.findComponent("encryptButton", true);
        decryptButton = (Button) TestUtils.findComponent("decryptButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);
        
        assertNotNull(openButton, "No openFileButton found.");
        assertNotNull(encryptButton, "No encryptButton found.");
        assertNotNull(decryptButton, "No decryptButton found.");
        assertNotNull(contentTextField, "No contentTextField found.");
    }
    
    // Description: Should have created files `test1.txt` and `test2.txt`.
    @Test
    public void shouldHaveCreatedFiles() {
        File file = new File("test1.txt");
        File file2 = new File("test2.txt");

        // // Create content for the files
        String content1 = "Hello";
        String content2 = " World";

        // // Write content to files
        writeFile("test1.txt", content1);
        writeFile("test2.txt", content2);

        assertTrue(file.exists(), "File 1 does not exist.");
        assertTrue(file2.exists(), "File 2 does not exist.");
    }
    
    private void writeFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Description: Should open `fileDialog` on `openButton` click.
    @Test
    public void shouldOpenFile() throws IOException {

        Button openButton = (Button) TestUtils.findComponent("openButton", true);
        
        robot().click(openButton);
        robot().waitForIdle();

        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        assertTrue(fd.isVisible(), "The file dialog should be visible");
    }

    // Description: Should encrypt the entered text in `contentTextField` and save it to a file on `encryptButton` click.
    @Test
    public void shouldEncryptAndSaveFile(){
        encryptButton = (Button) TestUtils.findComponent("encryptButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);
        
        // contentTextField.setText("Ifmmp");
        // use robot to enter text
        robot().click(contentTextField);
        robot().enterText("Ifmmp");
        
        robot().click(encryptButton);
        robot().waitForIdle();
        
        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);

        if (fd != null) {
            fd.setMultipleMode(true);
            robot().pressAndReleaseKeys(VK_T, VK_E, VK_S, VK_T, VK_6,VK_PERIOD,
                    VK_T,
                    VK_X,
                    VK_T,
                    VK_ENTER);
            robot().waitForIdle();
        }
        
        assertEquals(contentTextField.getText(), "Ifmmp");
    }

    // Description: Should decrypt the entered text in `contentTextField` and display it on `decryptButton` click.
    @Test
    public void shouldDecryptFile(){
        decryptButton = (Button) TestUtils.findComponent("decryptButton", true);
        contentTextField = (TextField) TestUtils.findComponent("contentTextField", true);
        
        robot().click(contentTextField);
        robot().enterText("Ifmmp");

        robot().click(decryptButton);
        robot().waitForIdle();
        
        assertEquals(contentTextField.getText(), "Hello");
    }
    
}
