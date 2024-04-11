package com.codechum;

import com.codechum.awt.graphicsClass.HelloWorldGraphics;

import java.awt.Graphics;
import java.awt.Window;
import java.lang.reflect.Method;

import static org.testng.Assert.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class HelloWorldGraphicsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(HelloWorldGraphics.class).start();
    }
    
    // Description: Should override the frame's paint method and display "Hello World".
    @Test
    public void shouldOverridePaintMethod() {
        boolean isOverridden = false;

        try {
            Method methodInMyCanvas = HelloWorldGraphics.class.getDeclaredMethod("paint", Graphics.class);
            Method methodInSuperclass = Window.class.getDeclaredMethod("paint", Graphics.class);

            // Check if the method in MyCanvas has the same name and return type as the method in the superclass
            isOverridden = methodInMyCanvas.getName().equals(methodInSuperclass.getName()) &&
                        methodInMyCanvas.getReturnType().equals(methodInSuperclass.getReturnType());

        } catch (NoSuchMethodException e) {
            fail("Method paint(Graphics) not found: " + e.getMessage());
        }

        assertTrue(isOverridden);
    }
}
