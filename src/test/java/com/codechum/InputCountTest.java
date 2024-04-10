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
    public void shouldHaveAllComponents() {
        findComponents();
        assertNotNull(contentTextArea);
        assertNotNull(textStatisticsLabel);
    }

    // Description: Should display 'Characters: 11 | Words: 2 | Ln: 1' for 'Hello World'.
    @Test
    public void shouldDisplayCorrectStatusForSingleLine() {
        prepareForTest();
        robot().enterText("Hello");
        robot().enterText(" ");
        robot().enterText("World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 11 | Words: 2 | Ln: 1");
    }

    // Description: Should display 'Characters: 23 | Words: 4 | Ln: 2' for 'Hello World\nHello World'.
    @Test
    public void shouldDisplayCorrectStatusForTwoLines() {
        prepareForTest();
        robot().enterText("Hello World\nHello World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 23 | Words: 4 | Ln: 2");
    }

    // Description: Should display 'Characters: 35 | Words: 6 | Ln: 3' for 'Hello World\nHello World\nHello World'.
    @Test
    public void shouldDisplayCorrectStatusForThreeLines() {
        prepareForTest();
        robot().enterText("Hello World\nHello World\nHello World");
        robot().waitForIdle();
        assertEquals(textStatisticsLabel.getText(), "Characters: 35 | Words: 6 | Ln: 3");
    }

    private void findComponents() {
        contentTextArea = (TextArea) TestUtils.findComponent("contentTextArea", true);
        textStatisticsLabel = (Label) TestUtils.findComponent("textStatisticsLabel", true);
    }

    private void prepareForTest() {
        findComponents();
        robot().click(contentTextArea);
        contentTextArea.setText("");
        robot().waitForIdle();
    }
}
