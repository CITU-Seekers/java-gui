package com.codechum;

import com.codechum.awt.eventListeners.InputCount;
import static org.testng.Assert.*;
import java.awt.*;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import org.testng.annotations.Test;

public class InputCountTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    
    TextArea contentTextArea;
    Label textStatisticsLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(InputCount.class).start();
    }
    
    // Description: Should have all components `contentTextArea` and `textStatisticsLabel`.
    @Test 
    public void shouldHaveAllComponents(){
        contentTextArea = (TextArea) TestUtils.findComponent("contentTextArea", true);
        textStatisticsLabel = (Label) TestUtils.findComponent("textStatisticsLabel", true);
        
        assertNotNull(contentTextArea);
        assertNotNull(textStatisticsLabel);
    }

    // Description: Should display the correct number of characters, words, and lines in the `textStatisticsLabel` when the text is entered in the `contentTextArea`.
    @Test
    public void shouldDisplayCorrectStatus(){
        contentTextArea = (TextArea) TestUtils.findComponent("contentTextArea", true);
        textStatisticsLabel = (Label) TestUtils.findComponent("textStatisticsLabel", true);
        
        robot().click(contentTextArea);
        robot().enterText("Hello World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 11 | Words: 2 | Ln: 1");
        
        contentTextArea.setText("");
        robot().waitForIdle();
        
        robot().enterText("Hello World\nHello World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 23 | Words: 4 | Ln: 2");
        
        contentTextArea.setText("");
        robot().waitForIdle();
        
        robot().enterText("Hello World\nHello World\nHello World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 35 | Words: 6 | Ln: 3");
    }
    
}
