package com.codechum;
import com.codechum.awt.drawingShapes.DrawingArcs;
import static org.testng.Assert.*;

import java.awt.*;
import mockit.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class DrawingArcsTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextField startingAngleTextField, extentAngleTextField;
    Button drawArcButton;
    Canvas mainCanvas;
    
    @Mocked Graphics g;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(DrawingArcs.class).start();
    }
    
    public void initializeComponents() {
        startingAngleTextField = (TextField) TestUtils.findComponent("startingAngleTextField", true);
        extentAngleTextField = (TextField) TestUtils.findComponent("extentAngleTextField", true);
        drawArcButton = (Button) TestUtils.findComponent("drawArcButton", true);
        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
    }
    
    @Test
    public void shouldHaveAllComponents() {
        initializeComponents();
        assertNotNull(startingAngleTextField, "No startingAngleTextField found.");
        assertNotNull(extentAngleTextField, "No textFieldEndingAnlge found.");
        assertNotNull(drawArcButton, "No drawArcButton found.");
        assertNotNull(mainCanvas, "No mainCanvas found.");
    }
    
    @Test
    public void shouldDrawArcWithCorrectAngles() {
        initializeComponents();
        
        new MockUp<Canvas>(){
           @Mock
           public Graphics getGraphics(){
               return g;
           }
       };
        
        mainCanvas = new Canvas();
        
        robot().click(startingAngleTextField);
        robot().enterText("45");
        robot().click(extentAngleTextField);
        robot().enterText("290");
        robot().click(drawArcButton);
        robot().waitForIdle();
        
        int startAngle = Integer.parseInt("45");
        int extentAngle = Integer.parseInt("290");
        
        new VerificationsInOrder() {{
            g.fillArc(anyInt, anyInt, anyInt, anyInt, startAngle, extentAngle);
        }};
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
 