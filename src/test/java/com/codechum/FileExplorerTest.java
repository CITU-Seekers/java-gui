package com.codechum;

import com.codechum.awt.menu.FileExplorer;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import java.awt.*;
import java.awt.event.KeyEvent;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.testng.Assert.*;

import org.testng.annotations.*;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class FileExplorerTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame mainFrame;
    
    MenuBar menuBar;
    Menu fileMenu, viewMenu;
    MenuItem newMenuItem, openMenuItem, saveMenuItem;
    CheckboxMenuItem viewHiddenMenuItem;
    List fileList;
    TextField fileNameTextField;
    Button addButton;
    Label statusLabel;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FileExplorer.class).start();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        mainFrame = (Frame) finder.findByType(Frame.class, true);
        menuBar = mainFrame.getMenuBar();
    }
    
    public MenuItem retrieveFileMenuItemByName(String menuItem) {
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
    
    public MenuItem retrieveViewMenuItemByName(String menuItem) {
        viewMenu = menuBar.getMenu(1);
        MenuItem item = null;
        
        for (int i = 0; i < viewMenu.getItemCount(); i++) {
            String itemName = viewMenu.getItem(i).getName();
            
            if (itemName.equals(menuItem)) {
                item = viewMenu.getItem(i);
                break;
            }
        }
        
        return item;
    }

    public void testMenuItemBehavior(String menuItemName, String expectedResult) {
        MenuItem item = retrieveFileMenuItemByName(menuItemName);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        
        assertEquals(statusLabel.getText(), expectedResult);
    }
    
    public void testNewMenuItemBehavior(String menuItemName) {
        MenuItem item = retrieveFileMenuItemByName(menuItemName);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        // Assert that fileNameTextField and addButton are enabled
        fileNameTextField = (TextField) TestUtils.findComponent("fileNameTextField", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);

        assertTrue(fileNameTextField.isEnabled(), "fileNameTextField is not enabled.");
        assertTrue(addButton.isEnabled(), "addButton is not enabled.");
    }
    
    public void testCheckBoxMenuItemBehavior(String menuItemName) {
        CheckboxMenuItem item = (CheckboxMenuItem) retrieveViewMenuItemByName(menuItemName);
        fileList = (List) TestUtils.findComponent("fileList", true);
        int itemCount = fileList.getItemCount();
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertEquals(fileList.getItemCount(), itemCount + 2);
    }

    // Description: Should have components `fileList`, `fileNameTextField`, `addButton`, and `statusLabel` and menus `fileMenu` and its items `newMenuItem`, `openMenuItem`, `saveMenuItem`, and `viewMenu` containing `viewHiddenMenuItem`.
    @Test
    public void shouldHaveAllComponents(){
        newMenuItem = retrieveFileMenuItemByName("newMenuItem");
        openMenuItem = retrieveFileMenuItemByName("openMenuItem");
        saveMenuItem = retrieveFileMenuItemByName("saveMenuItem");
        viewHiddenMenuItem = (CheckboxMenuItem) retrieveViewMenuItemByName("viewHiddenMenuItem");
        fileList = (List) TestUtils.findComponent("fileList", true);
        fileNameTextField = (TextField) TestUtils.findComponent("fileNameTextField", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        statusLabel = (Label) TestUtils.findComponent("statusLabel", true);
        
        assertNotNull(newMenuItem, "No newMenuItem found.");
        assertNotNull(openMenuItem, "No openMenuItem found.");
        assertNotNull(saveMenuItem, "No saveMenuItem found.");
        assertNotNull(viewHiddenMenuItem, "No viewHiddenMenuItem found.");
        assertNotNull(fileList, "No fileList found.");
        assertNotNull(fileNameTextField, "No fileNameTextField found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(statusLabel, "No statusLabel found.");
    }

    // Description: Should enable `fileNameTextField` and `addButton` when `newMenuItem` is clicked.
    @Test
    public void shouldEnableComponentsWhenNewMenuItemIsClicked(){
        testNewMenuItemBehavior("newMenuItem");    
    }

    // Description: Should display "Opening a file..." in `statusLabel` when `openMenuItem` is clicked.
    @Test
    public void shouldDisplayStatusLabelWhenOpenMenuItemIsClicked(){
        testMenuItemBehavior("openMenuItem", "Opening a file...");
    }

    // Description: Should display "Saving a file..." in `statusLabel` when `saveMenuItem` is clicked.
    @Test
    public void shouldDisplayStatusLabelWhenSaveMenuItemIsClicked(){
        testMenuItemBehavior("saveMenuItem", "Saving a file...");
    }

    // Description: Should display hidden items in `fileList` when `viewHiddenMenuItem` is clicked.
    @Test
    public void shouldDisplayHiddenFilesWhenViewHiddenMenuItemIsClicked(){
        testCheckBoxMenuItemBehavior("viewHiddenMenuItem");
    }

    // Description: Should add item to `fileList` when `fileNameTextField` is filled and `addButton` is clicked.
    @Test
    public void shouldAddItemToList(){
        MenuItem item = retrieveFileMenuItemByName("newMenuItem");
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        // Assert that fileNameTextField and addButton are enabled
        fileNameTextField = (TextField) TestUtils.findComponent("fileNameTextField", true);
        addButton = (Button) TestUtils.findComponent("addButton", true);
        fileList = (List) TestUtils.findComponent("fileList", true);
        
        int itemCount = fileList.getItemCount();

        robot().click(fileNameTextField);
        robot().enterText("Videos");
        robot().click(addButton);
        robot().waitForIdle();
        
        assertEquals(fileList.getItemCount(), itemCount + 1);
    }
    
}
