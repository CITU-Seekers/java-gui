package com.codechum;

import com.codechum.awt.drawingShapes.DrawingInACanvas;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;

public class DrawingInACanvasTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Canvas mainCanvas;
    Checkbox redCheckBox, greenCheckBox, blueCheckBox;

    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingInACanvas.class).start();
        robot().waitForIdle();

        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        redCheckBox = (Checkbox) TestUtils.findComponent("redCheckBox", true);
        greenCheckBox = (Checkbox) TestUtils.findComponent("greenCheckBox", true);
        blueCheckBox = (Checkbox) TestUtils.findComponent("blueCheckBox", true);
    }

    // Should draw colored ovals on checkbox selection
    @Test
    public void shouldDrawColoredOvals() {
        //
    }

    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(mainCanvas, "No mainCanvas found");
        assertNotNull(redCheckBox, "No redCheckBox found");
        assertNotNull(greenCheckBox, "No greenCheckBox found");
        assertNotNull(blueCheckBox, "No blueCheckBox found");
    }

    // Should only have one checkbox selected at a time.
    @Test
    public void shouldHaveOneCheckboxSelected() {
        //use robot to click on checkboxes
        //assert that only one checkbox is selected

        //click on redCheckBox
        robot().click(redCheckBox);

        assertTrue(redCheckBox.getState(), "redCheckBox not selected");
        assertFalse(greenCheckBox.getState(), "greenCheckBox selected");
        assertFalse(blueCheckBox.getState(), "blueCheckBox selected");
    }
}
