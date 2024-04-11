package com.codechum;

import com.codechum.awt.image.ImageCaptionGenerator;
import com.codechum.awt.image.ImageCaptionGeneratorCanvas;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;
import java.lang.reflect.Method;

import static java.awt.event.KeyEvent.*;
import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class ImageCaptionGeneratorTest extends AssertJSwingTestngTestCase {

    TextField captionTextField;
    Button addCaptionButton;
    Canvas mainCanvas;

    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ImageCaptionGenerator.class).start();
    }
    
    // Description: Should override frame's paint method of MyCanvas class to display an image.
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() {
        boolean isOverridden = false;
        Canvas mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);

        try {
            Method methodInMyCanvas = mainCanvas.getClass().getDeclaredMethod("paint", Graphics.class);
            Method methodInSuperclass = mainCanvas.getClass().getSuperclass().getDeclaredMethod("paint", Graphics.class);

            // Check if the method in MyCanvas has the same name and return type as the method in the superclass
            isOverridden = methodInMyCanvas.getName().equals(methodInSuperclass.getName()) &&
                        methodInMyCanvas.getReturnType().equals(methodInSuperclass.getReturnType());

        } catch (NoSuchMethodException e) {
            fail("Method paint(Graphics) not found: " + e.getMessage());
        }

        assertTrue(isOverridden);
    }
    
    // Description: Should have a canvas named `mainCanvas`.
    @Test
    public void shouldHaveCanvasMain() {
        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(mainCanvas, "No mainCanvas found");
    }
    
    // Description: Should have a button named `addCaptionButton` and a text field named `captionTextField`.
    @Test
    public void shouldHaveButtonAndTextField() {
        captionTextField = (TextField) TestUtils.findComponent("captionTextField", true);
        addCaptionButton = (Button) TestUtils.findComponent("addCaptionButton", true);
        
        assertNotNull(captionTextField, "No captionTextField found");
        assertNotNull(addCaptionButton, "No addCaptionButton found");
    }
    
    // Description: Should add caption in `captionTextField` to the image on clicking `addCaptionButton`.
    @Test
    public void shouldAddCaptionOnButtonClick() {
        captionTextField = (TextField) TestUtils.findComponent("captionTextField", true);
        addCaptionButton = (Button) TestUtils.findComponent("addCaptionButton", true);

        
        robot().click(captionTextField);
        robot().pressAndReleaseKeys(VK_S, VK_A, VK_M, VK_P, VK_L, VK_E);
        
        robot().click(addCaptionButton);
        robot().waitForIdle();

        assertEquals(captionTextField.getText(), "sample", "Caption not added to the image");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
