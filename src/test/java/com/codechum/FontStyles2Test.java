package com.codechum;
                
import com.codechum.TestUtils;
import com.codechum.awt.fonts.FontStyles2;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.Transient;

import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class FontStyles2Test extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    @Mocked
    Graphics g;

    MenuBar menuBar;
    Menu fontMenu;

    Frame mainFrame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FontStyles2.class).start();

        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        mainFrame = (Frame) finder.findByType(Frame.class, true);
        menuBar = mainFrame.getMenuBar();
    }

    public MenuItem retrieveMenuItemByName(String menuItem) {
        fontMenu = menuBar.getMenu(0);
        MenuItem item = null;

        for (int i = 0; i < fontMenu.getItemCount(); i++) {
            String itemName = fontMenu.getItem(i).getName();

            if (itemName.equals(menuItem)) {
                item = fontMenu.getItem(i);
                break;
            }
        }

        return item;
    }

    public void testMenuItemStyleBehavior(String menuItemName) {
        MenuItem item = retrieveMenuItemByName(menuItemName);

        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));

        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();

        // Paint the string with the selected menuItemName font style
        mainFrame.paint(g);
    }

    @Test
    public void shouldHaveFontMenuAndItsItems() {
        fontMenu = menuBar.getMenu(0);
        String[] menuItemNames = { "arialMenuItem", "rockwellMenuItem", "timesNewRomanMenuItem" };

        assertNotNull(fontMenu, "No menuFile found.");

        for (String menuItemName : menuItemNames) {
            MenuItem item = retrieveMenuItemByName(menuItemName);
            assertNotNull(item, "No " + menuItemName + " found.");
        }
    }

    @Test
    public void shouldDisplayTextWithArialFontStyle() {
        testMenuItemStyleBehavior("arialMenuItem");

        new Verifications() {
            {
                g.setFont(new Font("Arial", Font.PLAIN, 48));
                g.drawString("Hello World", 50, 100);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDisplayTextWithRockwellFontStyle() {
        testMenuItemStyleBehavior("rockwellMenuItem");

        new Verifications() {
            {
                g.setFont(new Font("Rockwell", Font.PLAIN, 48));
                g.drawString("Hello World", 50, 100);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDisplayTextWithTimesNewRomanFontStyle() {
        testMenuItemStyleBehavior("timesNewRomanMenuItem");

        new Verifications() {
            {
                g.setFont(new Font("Times New Roman", Font.PLAIN, 48));
                g.drawString("Hello World", 50, 100);
                times = 1;
            }
        };
    }

}
        