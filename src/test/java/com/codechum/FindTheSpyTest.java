package com.codechum;

import com.codechum.awt.dialog.FindTheSpy;
import static org.testng.Assert.*;

import java.awt.*;
import mockit.Mocked;
import mockit.Verifications;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FindTheSpyTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    String[] buttons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8", "button9", "button10"};
    
    @Mocked Dialog dialogNotice;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FindTheSpy.class).start();
        robot().waitForIdle();
    }
    
    @Test
    public void shouldHaveAllButtons() {
        for (String button: buttons) {
            Button btn = (Button) TestUtils.findComponent(button,true);
            assertNotNull(btn, "No " + button + " found.");
        }
    }
    
    @Test
    public void shouldIdentifySpyCorrectly() {
        for (int i = 0; i < buttons.length; i++) {
            Button btn = (Button) TestUtils.findComponent(buttons[i],true);
            robot().click(btn);
            robot().waitForIdle(); 
        }
        
        new Verifications(){{
            dialogNotice.setVisible(true); times = 3;
        }};
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
