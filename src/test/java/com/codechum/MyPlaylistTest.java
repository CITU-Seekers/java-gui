package com.codechum;

import com.codechum.awt.list.MyPlaylist;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MyPlaylistTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    List musicList;
    TextField musicTextField;
    Button addButton, removeButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MyPlaylist.class).start();
    }

    public String convertListToString(List list) {
        String[] listItems = list.getItems();
        String listString = "";
        for (int i = 0; i < listItems.length; i++) {
            if (i == listItems.length - 1) {
                listString += listItems[i];
            } else {
                listString += listItems[i] + ", ";
            }
        }

        return listString;
    }

    public void input(String title) {
        robot().click(musicTextField);
        robot().enterText(title);
    }
    
    // Description: Should have all components `musicList`, `musicTextField`, `addButton`, and `removeButton`.
    @Test
    public void shouldHaveAllComponents() {
        musicList = (List) TestUtils.findComponent("musicList", true);
        musicTextField = (TextField) TestUtils.findComponent("musicTextField", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        
        assertNotNull(musicList, "No musicList found.");
        assertNotNull(musicTextField, "No musicTextField found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(removeButton, "No removeButton found.");
    }
    
    // Description: Should add music entered in `musicTextField` to `musicList` when `addButton` is clicked.
    @Test
    public void shouldBeAbleToAddMusicToList() {
        musicList = (List) TestUtils.findComponent("musicList", true);
        musicTextField = (TextField) TestUtils.findComponent("musicTextField", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        
        input("New Music");
        robot().click(addButton);
        robot().waitForIdle();

        String actualMusicListString = convertListToString(musicList);
        
        assertEquals(actualMusicListString, "Music Main 1, Music Main 2, New Music");
    }
    
    // Description: Should remove selected music from `musicList` when `removeButton` is clicked.
    @Test
    public void shouldBeAbleToRemoveMusicFromList() {
        musicList = (List) TestUtils.findComponent("musicList", true);
        removeButton = (Button) TestUtils.findComponent("removeButton", true);
        
        musicList.select(0);
        robot().click(removeButton);
        robot().waitForIdle();
        
        String actualMusicListString = convertListToString(musicList);
        
        assertEquals(actualMusicListString, "Music Main 2");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}

