package com.codechum;

import com.codechum.awt.image.ImageInCanvas;

import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class ImageInCanvasTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ImageInCanvas.class).start();
    }
    
    @Test
    public void shouldOverridePaintMethodOfCanvasClass() throws IOException {
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
        Canvas mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(mainCanvas, "No mainCanvas found");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
