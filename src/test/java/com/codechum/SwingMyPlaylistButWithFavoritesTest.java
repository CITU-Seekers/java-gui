package com.codechum;

import com.codechum.swing.jList.MyPlaylistButWithFavorites;
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

public class SwingMyPlaylistButWithFavoritesTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JList musicList, favoritesList;
    JTextField musicTextField;
    JButton addButton, removeButton, addToFavoritesButton;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MyPlaylistButWithFavorites.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    public String convertListToString(String[] listItems) {
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
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        
        frame.textBox("musicTextField").setText("New Music");
        robot().click(addButton);
        robot().waitForIdle();

        String[] actualMusicJListItems = frame.list("musicList").contents();
        String actualMusicJListString = convertListToString(actualMusicJListItems);
        
        assertEquals(actualMusicJListString, "Music Main 1, Music Main 2, New Music");
    }
    
    // Description: Should remove selected music from `musicList` when `removeButton` is clicked.
    @Test
    public void shouldBeAbleToRemoveMusicFromList() {
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);
        
        frame.list("musicList").selectItem(0);
        robot().click(removeButton);
        robot().waitForIdle();

        String[] actualMusicJListItems = frame.list("musicList").contents();
        String actualMusicJListString = convertListToString(actualMusicJListItems);
        
        assertEquals(actualMusicJListString, "Music Main 2");
    }
    
    // Description: Should add selected music from `musicList` to `favoritesList` when `addToFavoritesButton` is clicked.
    @Test
    public void shouldBeAbleToAddToFavorites() {
        addToFavoritesButton = (JButton) TestUtils.findComponent("addToFavoritesButton", true);
        
        frame.list("musicList").selectItem(0);
        robot().click(addToFavoritesButton);
        robot().waitForIdle();

        String[] actualFavoritesJListItems = frame.list("favoritesList").contents();
        String actualMusicJListString = convertListToString(actualFavoritesJListItems);
        
        assertEquals(actualMusicJListString, "Music Main 1");
    }
    
    // Description: Should remove selected music from `musicList` and in `favoritesList` if it exists when `removeButton` is clicked.
    @Test
    public void shouldDeleteFromBothLists() {
        musicList = (JList) TestUtils.findComponent("musicList", true);
        favoritesList = (JList) TestUtils.findComponent("favoritesList", true);
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);
        addToFavoritesButton = (JButton) TestUtils.findComponent("addToFavoritesButton", true);
        
        frame.list("musicList").selectItem(1);
        robot().click(addToFavoritesButton);
        robot().waitForIdle();
        robot().click(removeButton);
        robot().waitForIdle();

        String[] actualMusicJListItems = frame.list("musicList").contents();
        String actualMusicJListString = convertListToString(actualMusicJListItems);

        String[] actualFavoritesJListItems = frame.list("favoritesList").contents();
        String actualFavoritesJListString = convertListToString(actualFavoritesJListItems);
        
        assertEquals(actualMusicJListString, "Music Main 1");
        assertEquals(actualFavoritesJListString, "");
    }
    
    @AfterMethod
    public void tearDownAbortJListener() {
        listener.unregister();
    }
}
