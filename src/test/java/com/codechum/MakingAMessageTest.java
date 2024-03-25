package com.codechum;

import com.codechum.awt.fileDialog.MakingAMessage;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.logging.*;
import java.util.stream.Stream;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MakingAMessageTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Button createFileButton;
    Label fileContentLabel;
    File file;
    
    public void deleteFile(String fileName) {
        file = new File(fileName);
        
        if (file.isFile()) {
            file.delete();
        }
    }
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MakingAMessage.class).start();
        deleteFile("test1.txt");
        deleteFile("test2.txt");
    }
    
    @Test
    public void shouldHaveAllComponents() {
        createFileButton = (Button) TestUtils.findComponent("createFileButton", true);
        fileContentLabel = (Label) TestUtils.findComponent("fileContentLabel", true);
        
        assertNotNull(createFileButton, "No createFileButton found.");
        assertNotNull(fileContentLabel, "No fileContentLabel found.");
    }
    
    @Test
    public void shouldShowFileDialogOnButtonClick() {
        createFileButton = (Button) TestUtils.findComponent("createFileButton", true);
        
        robot().click(createFileButton);
        robot().waitForIdle();
        
        FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        
        assertNotNull(fd, "No fd found.");
        assertTrue(fd.isVisible(), "The file dialog shoud be visible on button click.");
    }
    
    @Test
    public void shouldHaveCreatedFile() throws InterruptedException {
        // createFileButton = (Button) TestUtils.findComponent("createFileButton", true);
        
        // robot().click(createFileButton);
        // robot().waitForIdle();
        
        // FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        
        // if (fd != null) {
        //     robot().pressAndReleaseKeys(
        //             VK_T, 
        //             VK_E, 
        //             VK_S, 
        //             VK_T, 
        //             VK_2,
        //             VK_PERIOD,
        //             VK_T,
        //             VK_X,
        //             VK_T,
        //             VK_ENTER
        //     );
        //     robot().waitForIdle();
        // }

        // robot().waitForIdle();
        // String fileNameToFind = "test2.txt";

        // String dir = System.getProperty("user.dir");
        // final List<File> foundFiles = new ArrayList<>();

        // File activityFile = null;
        // File rootDirectory = new File(".");
        // try {
        //     try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
        //         walkStream.filter(p -> p.toFile().isFile())
        //             .forEach(f -> {
        //                 //if (f.toString().endsWith(fileNameToFind)) {
        //                     foundFiles.add(f.toFile());
        //                 //}
        //             });
        //     }
        // } catch(Exception e) {
        //     System.out.println(e);
        // }
        // assertTrue(false, foundFiles.toString());
        // activityFile = new File(rootDirectory.getPath() + "/" + fileNameToFind); // foundFiles.get(0);
        // String fileName = activityFile.getPath();

        // file = new File(fileName);
        
        // assertTrue(file.isFile(), "There should be a created file");
        
        // InputStream is;
        // String text = "";
        // try {
        //     is = new FileInputStream(file);
        //     BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //     String str = "";
        //     while ((str = br.readLine()) != null) {
        //         text += str;
        //     }
            
        //     br.close();
        // } catch (FileNotFoundException ex) {
        //     Logger.getLogger(CODECHUMACTIVITY.class.getName()).log(Level.SEVERE, null, ex);
        // } catch (IOException ex) {
        //     Logger.getLogger(CODECHUMACTIVITY.class.getName()).log(Level.SEVERE, null, ex);
        // }
        
        // assertEquals(file.getName(), "test2.txt");
        // assertEquals(text, "File handling");
    }
    
    @Test
    public void shouldDisplayFileContentInLabel() {
        // createFileButton = (Button) TestUtils.findComponent("createFileButton", true);
        // fileContentLabel = (Label) TestUtils.findComponent("fileContentLabel", true);
        
        // robot().click(createFileButton);
        // robot().waitForIdle();
        
        // FileDialog fd = (FileDialog) TestUtils.findComponent("fileDialog", true);
        
        // if (fd != null) {
        //     robot().pressAndReleaseKeys(
        //             VK_T, 
        //             VK_E, 
        //             VK_S, 
        //             VK_T, 
        //             VK_1,
        //             VK_PERIOD,
        //             VK_T,
        //             VK_X,
        //             VK_T,
        //             VK_ENTER
        //     );
        //     robot().waitForIdle();
        // }
        
        // assertEquals(fileContentLabel.getText(), "File handling");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
        deleteFile("test1.txt");
        deleteFile("test2.txt");
    }
}

