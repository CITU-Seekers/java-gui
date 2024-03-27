package com.codechum;

import com.codechum.awt.menu.CheckboxMenuItemFeature;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import java.awt.*;
import java.awt.event.KeyEvent;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.testng.Assert.*;

import org.testng.annotations.*;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class CheckboxMenuItemFeatureTest extends AssertJSwingTestngTestCase{
    EmergencyAbortListener listener;
    
    MenuBar menuBar;
    Menu formatMenu, colorsMenu;
    CheckboxMenuItem featureCheckBoxMenu;
    MenuItem redMenuItem, greenMenuItem, blueMenuItem;
    Label featureLabel;
    
    Frame mainFrame;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(CheckboxMenuItemFeature.class).start();
        
        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        mainFrame = (Frame) finder.findByType(Frame.class, true);
        menuBar = mainFrame.getMenuBar();
    }
    
    public MenuItem retrieveFormatMenuItemByName(String menuItem) {
        formatMenu = menuBar.getMenu(0);
        MenuItem item = null;
        
        for (int i = 0; i < formatMenu.getItemCount(); i++) {
            String itemName = formatMenu.getItem(i).getName();
            
            if (itemName.equals(menuItem)) {
                item = formatMenu.getItem(i);
                break;
            }
        }
        
        return item;
    }
    
    public MenuItem retrieveMenuItemFromColorMenu(String menuItem) {
        formatMenu = menuBar.getMenu(0);
        colorsMenu = (Menu) retrieveFormatMenuItemByName("colorsMenu");
        MenuItem item = null;
        
        for (int i = 0; i < colorsMenu.getItemCount(); i++) {
            String itemName = colorsMenu.getItem(i).getName();
            
            if (itemName.equals(menuItem)) {
                item = colorsMenu.getItem(i);
                break;
            }
        }
        
        return item;
    }
    
    public void testCheckBoxMenuItemBehavior(String menuItemName, String expectedResult) {
        CheckboxMenuItem item = (CheckboxMenuItem) retrieveFormatMenuItemByName(menuItemName);
        featureLabel = (Label) TestUtils.findComponent("featureLabel", true);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertEquals(featureLabel.getText(), expectedResult);
    }

    public void testMenuItemFontColorBehavior(String menuItemName, Color color) {
        formatMenu = menuBar.getMenu(0);
        MenuItem item = retrieveMenuItemFromColorMenu(menuItemName);
        featureLabel = (Label) TestUtils.findComponent("featureLabel", true);
        
        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));
        
        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
        
        assertEquals(featureLabel.getForeground(), color);
    }

    @Test
    public void shouldHaveAllComponents(){
        formatMenu = menuBar.getMenu(0);
        assertNotNull(formatMenu, "No format menu found.");

        featureCheckBoxMenu = (CheckboxMenuItem) retrieveFormatMenuItemByName("featureCheckBoxMenu");
        assertNotNull(featureCheckBoxMenu, "No featureCheckBoxMenu found.");

        //get the colors menu
        colorsMenu = (Menu) retrieveFormatMenuItemByName("colorsMenu");
        assertNotNull(colorsMenu, "No colors menu found.");

        redMenuItem = retrieveMenuItemFromColorMenu("redMenuItem");
        assertNotNull(redMenuItem, "No redMenuItem found.");

        greenMenuItem = retrieveMenuItemFromColorMenu("greenMenuItem");
        assertNotNull(greenMenuItem, "No greenMenuItem found.");

        blueMenuItem = retrieveMenuItemFromColorMenu("blueMenuItem");
        assertNotNull(blueMenuItem, "No blueMenuItem found.");
    }

    @Test
    public void shouldDisplayLabelWhenFeatureCheckBoxIsSelected(){
        testCheckBoxMenuItemBehavior("featureCheckBoxMenu", "Feature enabled!");
    }

    @Test
    public void shouldDisplayLabelInRedWhenRedMenuItemIsSelected(){
        testMenuItemFontColorBehavior("redMenuItem", Color.RED);
    }

    @Test
    public void shouldDisplayLabelInGreenWhenGreenMenuItemIsSelected(){
        testMenuItemFontColorBehavior("greenMenuItem", Color.GREEN);
    }

    @Test
    public void shouldDisplayLabelInBlueWhenBlueMenuItemIsSelected(){
        testMenuItemFontColorBehavior("blueMenuItem", Color.BLUE);
    }
}
