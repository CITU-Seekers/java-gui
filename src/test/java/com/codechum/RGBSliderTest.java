package com.codechum;

import com.codechum.awt.scrollBar.RGBSlider;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class RGBSliderTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Scrollbar redScrollBar, greenScrollBar, blueScrollBar;
    Panel colorPanel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(RGBSlider.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        redScrollBar = (Scrollbar) TestUtils.findComponent("redScrollBar", true);
        greenScrollBar = (Scrollbar) TestUtils.findComponent("greenScrollBar", true);
        blueScrollBar = (Scrollbar) TestUtils.findComponent("blueScrollBar", true);
        colorPanel = (Panel) TestUtils.findComponent("colorPanel", true);
        
        assertNotNull(redScrollBar, "No redScrollBar found.");
        assertNotNull(greenScrollBar, "No greenScrollBar found.");
        assertNotNull(blueScrollBar, "No blueScrollBar found.");
        assertNotNull(colorPanel, "No colorPanel found.");
    }
    
    @Test
    public void allScrollBarsShouldHaveCorrectDefaultMinAndMaxValues() {
        String[] scrollBars = {"redScrollBar", "greenScrollBar", "blueScrollBar"};
        
        String minMaxValues;
        for (String scrollBar: scrollBars) {
            Scrollbar sb = (Scrollbar) TestUtils.findComponent(scrollBar, true);
            
            minMaxValues = "Default: " + sb.getValue() + ", Min: " + sb.getMinimum() + ", Max: " + sb.getMaximum();
            
            assertEquals(minMaxValues, "Default: 0, Min: 0, Max: 255");
        }
    }
    
    @Test
    public void shouldChangeColorOnRedScrollbarChange() {
        redScrollBar = (Scrollbar) TestUtils.findComponent("redScrollBar", true);
        colorPanel = (Panel) TestUtils.findComponent("colorPanel", true);
        
        robot().click(redScrollBar);
        
        for (int i = 0; i < 10; i++) {
            robot().pressAndReleaseKeys(VK_RIGHT);
        }
        
        assertEquals(colorPanel.getBackground().getRed(), redScrollBar.getValue());
    }
    
    @Test
    public void shouldChangeColorOnGreenScrollbarChange() {
        greenScrollBar = (Scrollbar) TestUtils.findComponent("greenScrollBar", true);
        colorPanel = (Panel) TestUtils.findComponent("colorPanel", true);
        
        robot().click(greenScrollBar);
        
        for (int i = 0; i < 10; i++) {
            robot().pressAndReleaseKeys(VK_RIGHT);
        }
        
        assertEquals(colorPanel.getBackground().getGreen(), greenScrollBar.getValue());
    }
    
    @Test
    public void shouldChangeColorOnBlueScrollbarChange() {
        blueScrollBar = (Scrollbar) TestUtils.findComponent("blueScrollBar", true);
        colorPanel = (Panel) TestUtils.findComponent("colorPanel", true);
        
        robot().click(blueScrollBar);
        
        for (int i = 0; i < 10; i++) {
            robot().pressAndReleaseKeys(VK_RIGHT);
        }
        
        assertEquals(colorPanel.getBackground().getBlue(), blueScrollBar.getValue());
    }
}
