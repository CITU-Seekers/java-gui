package com.codechum;

import com.codechum.awt.scrollBar.TextResizer;
import static org.testng.Assert.*;

import java.awt.*;
import static java.awt.event.KeyEvent.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class TextResizerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    Scrollbar resizeScrollBar;
    Label textLabel;
    
    @Override   
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(TextResizer.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllComponents() {
        resizeScrollBar = (Scrollbar) TestUtils.findComponent("resizeScrollBar", true);
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        
        assertNotNull(resizeScrollBar, "No resizeScrollBar found.");
        assertNotNull(textLabel, "No textLabel found.");
    }
    
    @Test
    public void shouldChangeFontSizeCorrectly() throws InterruptedException {
        resizeScrollBar = (Scrollbar) TestUtils.findComponent("resizeScrollBar", true);
        textLabel = (Label) TestUtils.findComponent("textLabel", true);
        
        robot().click(resizeScrollBar);
        robot().pressAndReleaseKeys(VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT,VK_RIGHT);
        
        Font font = textLabel.getFont();
        
        assertEquals(font.getSize(), resizeScrollBar.getValue());
    }
}

