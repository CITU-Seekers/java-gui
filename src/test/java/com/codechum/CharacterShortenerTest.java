package com.codechum;

import com.codechum.awt.buttons.CharacterShortener;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CharacterShortenerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Label label1;
    Button shortenButton;

    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CharacterShortener.class).start();
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        label1 = (Label) TestUtils.findComponent("textLabel", true);
        shortenButton = (Button) TestUtils.findComponent("shortenButton", true);
        
        assertNotNull(label1, "No textLabel found.");
        assertNotNull(shortenButton, "No shortenButton found.");
    }

    @Test
    public void shouldShortenTextOnClick() {
        label1 = (Label) TestUtils.findComponent("textLabel", true);
        shortenButton = (Button) TestUtils.findComponent("shortenButton", true);

        assertEquals(label1.getText(), "This is a text");

        robot().click(shortenButton);
        robot().waitForIdle();

        assertEquals(label1.getText(), "This is a tex");
        
        robot().click(shortenButton);
        robot().waitForIdle();
        
        assertEquals(label1.getText(), "This is a te");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
