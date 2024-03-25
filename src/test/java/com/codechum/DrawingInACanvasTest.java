package com.codechum;
import com.codechum.awt.drawingShapes.DrawingInACanvas;
import static org.testng.Assert.*;

import java.awt.*;
import mockit.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class DrawingInACanvasTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Canvas mainCanvas;
    int xCenter, yCenter;
    @Mocked Graphics g;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingInACanvas.class).start();
        
        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        yCenter = mainCanvas.getHeight() / 2;
        xCenter = mainCanvas.getWidth() / 2;
        
        new MockUp<Canvas>(){
           @Mock
           public Graphics getGraphics(){
               return g;
           }
       };
    }
    
    public void pickColor(String checkboxName) {
        Checkbox checkBox = (Checkbox) TestUtils.findComponent(checkboxName, true);
        
        robot().click(checkBox);
        robot().waitForIdle();
    }
    
    public void clickCenterOfCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);
        
        robot().click(mainCanvas, centerOfCnvMain);
        robot().waitForIdle();
    }
    
    public void drawInCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);
        
        robot().pressMouse(mainCanvas, centerOfCnvMain);
        
        int i = 0;
        while (i < 5) {
            Point endPointOfDrawing = new Point(xCenter - i, yCenter - i);
            robot().moveMouse(mainCanvas, endPointOfDrawing);
            i++;
        }
    }
    
    @Test
    public void shouldHaveAllComponents() {
        String[] checkboxes = {"redCheckBox", "greenCheckBox", "blueCheckBox"};
        
        for (String checkbox: checkboxes) {
            Checkbox checkBox = (Checkbox) TestUtils.findComponent(checkbox, true);
            assertNotNull(checkBox, "No " + checkbox + " found.");
        }
        
        assertNotNull(mainCanvas, "No mainCanvas found.");
    }
    
    @Test
    public void shouldDrawRedOvalOnClickedArea() {
        pickColor("redCheckBox");
        
        clickCenterOfCnvMain();
        
        new Verifications() {{
            g.setColor(Color.red);
            g.fillOval(xCenter, yCenter, 15, 15);
        }};
    }
    
    @Test
    public void shouldDrawGreenOvalOnClickedArea() {
        pickColor("greenCheckBox");
        
        clickCenterOfCnvMain();
        
        new Verifications() {{
            g.setColor(Color.green);
            g.fillOval(xCenter, yCenter, 15, 15);
        }};
    }
    
    @Test
    public void shouldDrawBlueOvalOnClickedArea() {
        pickColor("blueCheckBox");
        
        clickCenterOfCnvMain();
        
        new Verifications() {{
            g.setColor(Color.blue);
            g.fillOval(xCenter, yCenter, 15, 15);
        }};
    }
    
    @Test
    public void shouldDrawRedOvalOnClickedAreaToEndPoint() {
        pickColor("redCheckBox");
        
        drawInCnvMain();
        
        new Verifications() {{
            g.setColor(Color.red);
            g.fillOval(xCenter - 1, yCenter - 1, 15, 15);
            g.fillOval(xCenter - 2, yCenter - 2, 15, 15);
            g.fillOval(xCenter - 3, yCenter - 3, 15, 15);
            g.fillOval(xCenter - 4, yCenter - 4, 15, 15);
        }};
    }
    @Test
    public void shouldDrawGreenOvalOnClickedAreaToEndPoint() {
        pickColor("greenCheckBox");
        
        drawInCnvMain();
        
        new Verifications() {{
            g.setColor(Color.green);
            g.fillOval(xCenter - 1, yCenter - 1, 15, 15);
            g.fillOval(xCenter - 2, yCenter - 2, 15, 15);
            g.fillOval(xCenter - 3, yCenter - 3, 15, 15);
            g.fillOval(xCenter - 4, yCenter - 4, 15, 15);
        }};
    }
    
    @Test
    public void shouldDrawBlueOvalOnClickedAreaToEndPoint() {
        pickColor("blueCheckBox");
        
        drawInCnvMain();
        
        new Verifications() {{
            g.setColor(Color.blue);
            g.fillOval(xCenter - 1, yCenter - 1, 15, 15);
            g.fillOval(xCenter - 2, yCenter - 2, 15, 15);
            g.fillOval(xCenter - 3, yCenter - 3, 15, 15);
            g.fillOval(xCenter - 4, yCenter - 4, 15, 15);
        }};
    }
    
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
