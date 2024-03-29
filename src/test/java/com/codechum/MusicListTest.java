package com.codechum;

import com.codechum.swing.jList.MusicList;
import static org.testng.Assert.*;

import java.awt.*;
import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class MusicListTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener lstener;
    FrameFixture frame;

    JList musicList;
    JTextField musicTextField;
    JButton addButton, removeButton;
    
    @Override
    protected void onSetUp() {
        lstener = EmergencyAbortListener.registerInToolkit();
        application(MusicList.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    // Description: Should have all components `musicList`, `musicTextField`, `addButton`, and `removeButton`.
    @Test
    public void shouldHaveAllComponents() {
        musicList = (JList) TestUtils.findComponent("musicList", true);
        musicTextField = (JTextField) TestUtils.findComponent("musicTextField", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);
        
        assertNotNull(musicList, "No musicList found.");
        assertNotNull(musicTextField, "No musicTextField found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(removeButton, "No removeButton found.");
    }
    
    // Description: Should add new entered music from `musicTextField` to `musicList` when `addButton` is clicked.
    @Test
    public void shouldBeAbleToAddMusicToList() {
        musicList = (JList) TestUtils.findComponent("musicList", true);
        musicTextField = (JTextField) TestUtils.findComponent("musicTextField", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        
        frame.textBox("musicTextField").setText("New Music");
        robot().click(addButton);
        robot().waitForIdle();

        String[] actualMusicListItems = frame.list("musicList").contents();
        String actualMusicListString = "";
        for (int i = 0; i < actualMusicListItems.length; i++) {
            if (i == actualMusicListItems.length - 1) {
                actualMusicListString += actualMusicListItems[i];
            } else {
                actualMusicListString += actualMusicListItems[i] + ", ";
            }
        }
        
        assertEquals(actualMusicListString, "Music Main 1, Music Main 2, New Music");
    }
    
    // Description: Should remove selected music from `musicList` when `removeButton` is clicked.
    @Test
    public void shouldBeAbleToRemoveMusicFromList() {
        musicList = (JList) TestUtils.findComponent("musicList", true);
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);
        
        frame.list("musicList").selectItem(0);
        robot().click(removeButton);
        robot().waitForIdle();

        String[] actualMusicListItems = frame.list("musicList").contents();
        String actualMusicListString = "";
        for (int i = 0; i < actualMusicListItems.length; i++) {
            if (i == actualMusicListItems.length - 1) {
                actualMusicListString += actualMusicListItems[i];
            } else {
                actualMusicListString += actualMusicListItems[i] + ", ";
            }
        }
        
        
        assertEquals(actualMusicListString, "Music Main 2");
    }
    
    @AfterMethod
    public void tearDownAbortJListener() {
        lstener.unregister();
    }
}
