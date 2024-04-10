package com.codechum;

import com.codechum.awt.menu.MenuInAMenu;
import java.awt.*;
import java.awt.event.KeyEvent;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.testng.Assert.*;

import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class MenuInAMenuTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    MenuBar menuBar;
    Menu fileMenu, formatMenu, fontColorMenu;
    Label statusLabel;
    
    Frame frame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MenuInAMenu.class).start();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        frame = (Frame) finder.findByType(Frame.class, true);
        menuBar = frame.getMenuBar();
    }
    
    public MenuItem retrieveMenuItemByName(String menuItem) {
        fileMenu = menuBar.getMenu(0);
        MenuItem item = null;
        
        for (int i = 0; i < fileMenu.getItemCount(); i++) {
            String itemName = fileMenu.getItem(i).getName();
            
            if (itemName.equals(menuItem)) {
                item = fileMenu.getItem(i);
                break;
            }
        }
        
        return item;
    }
    
    public MenuItem retrieveMenuItemFromFontColor(String menuItem) {
        formatMenu = menuBar.getMenu(1);
        fontColorMenu = (Menu) formatMenu.getItem(0);
        MenuItem item = null;
        
        for (int i = 0; i < fontColorMenu.getItemCount(); i++) {
            String itemName = fontColorMenu.getItem(i).getName();
            
            if (itemName.equals(menuItem)) {
                item = fontColorMenu.getItem(i);
                break;
            }
        }
        
        return item;
    }
    
    public void testMenuItemFontColorBehavior(String menuItemName, Color color) {
        MenuItem item = retrieveMenuItemFromFontColor(menuItemName);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertEquals(statusLabel.getForeground(), color);
    }
    
    public void testMenuItemBehavior(String menuItemName, String expectedResult) {
        MenuItem item = retrieveMenuItemByName(menuItemName);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertEquals(statusLabel.getText(), expectedResult);
    }
    
    // Description: Should have a `fileMenu` and its items `newMenuItem`, `openMenuItem`, `saveMenuItem`, and `exitMenuItem`.
    @Test
    public void shouldHaveMenuFileAndItsItems() {
        fileMenu = menuBar.getMenu(0);
        String[] menuItemNames = {"newMenuItem", "openMenuItem", "saveMenuItem", "exitMenuItem"};

        assertNotNull(fileMenu, "No fileMenu found.");
        
        for (String menuItemName : menuItemNames) {
            MenuItem item = retrieveMenuItemByName(menuItemName);
            assertNotNull(item, "No " + menuItemName + " found.");
        }
    }
    
    // Description: Should have a `formatMenu` and its item `fontColorMenu` that contains `redColorMenuItem`, `greenColorMenuItem`, and `blueColorMenuItem`.
    @Test
    public void shouldHaveMenuFormatAndItsItems() {
        String[] menuItemNames = {"redColorMenuItem", "greenColorMenuItem", "blueColorMenuItem"};
        formatMenu = menuBar.getMenu(1);

        assertNotNull(formatMenu, "No formatMenu found.");
        
        fontColorMenu = (Menu) formatMenu.getItem(0);
        
        assertNotNull(fontColorMenu, "No fontColorMenu found in formatMenu.");
        
        for (String menuItemName : menuItemNames) {
            MenuItem item = retrieveMenuItemFromFontColor(menuItemName);
            assertNotNull(item, "No " + menuItemName + " found.");
        }
    }
    
    // Description: Should have a label named `statusLabel`.
    @Test
    public void shouldHaveLabelStatus() {
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        assertNotNull(statusLabel, "No statusLabel found.");
    }
    
    // Description: Should display "Creating new file..." in `statusLabel` when `newMenuItem` is clicked.
    @Test
    public void shouldDisplayCorrectMessageOnNewClick() {
        testMenuItemBehavior("newMenuItem", "Creating new file...");
    }
    
    // Description: Should display "Opening file..." in `statusLabel` when `openMenuItem` is clicked.
    @Test
    public void shouldDisplayCorrectMessageOnOpenClick() {
        testMenuItemBehavior("openMenuItem", "Opening file...");
    }
    
    // Description: Should display "Saving file..." in `statusLabel` when `saveMenuItem` is clicked.
    @Test
    public void shouldDisplayCorrectMessageOnSaveClick() {
        testMenuItemBehavior("saveMenuItem", "Saving file...");
    }
    
    // Description: Should change foreground color of `statusLabel` to red when `redColorMenuItem` is clicked.
    @Test
    public void shouldChangeFontColorOnRedClick() {
        testMenuItemFontColorBehavior("redColorMenuItem", Color.RED);
    }
    
    // Description: Should change foreground color of `statusLabel` to green when `greenColorMenuItem` is clicked.
    @Test
    public void shouldChangeFontColorOnGreenClick() {
        testMenuItemFontColorBehavior("greenColorMenuItem", Color.GREEN);
    }
    
    // Description: Should change foreground color of `statusLabel` to blue when `blueColorMenuItem` is clicked.
    @Test
    public void shouldChangeFontColorOnBlueClick() {
        testMenuItemFontColorBehavior("blueColorMenuItem", Color.BLUE);
    }
    
    // Description: Should close the app when `exitMenuItem` is clicked.
    @Test
    public void shouldCloseAppOnExitClick() {
        MenuItem item = retrieveMenuItemByName("exitMenuItem");
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertFalse(frame.isActive(), "The frame should be closed on exit click.");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
