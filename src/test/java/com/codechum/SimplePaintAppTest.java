package com.codechum;

import com.codechum.awt.drawingShapes.SimplePaintApp;
import java.awt.*;
import java.awt.event.KeyEvent;
import mockit.Mock;
import mockit.MockUp;

import mockit.Mocked;
import mockit.Verifications;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.EmergencyAbortListener;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.testng.Assert.*;

import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class SimplePaintAppTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    MenuBar menuBar;
    Menu shapesMenu, colorsMenu;
    Canvas mainCanvas;
    int xCenter, yCenter;
    @Mocked
    Graphics g;

    Frame mainFrame;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(SimplePaintApp.class).start();

        ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        mainFrame = (Frame) finder.findByType(Frame.class, true);
        menuBar = mainFrame.getMenuBar();

        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        yCenter = mainCanvas.getHeight() / 2;
        xCenter = mainCanvas.getWidth() / 2;

        new MockUp<Canvas>() {
            @Mock
            public Graphics getGraphics() {
                return g;
            }
        };
    }

    public void clickCenterOfCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);

        robot().click(mainCanvas, centerOfCnvMain);
        robot().waitForIdle();
    }

    public void drawInCnvMain() {
        Point centerOfCnvMain = new Point(xCenter, yCenter);

        robot().pressMouse(mainCanvas, centerOfCnvMain);

        int i = 0;
        while (i < 5) {
            Point endPointOfDrawing = new Point(xCenter - i, yCenter - i);
            robot().moveMouse(mainCanvas, endPointOfDrawing);
            i++;
        }
    }

    public MenuItem retrieveShapesMenuItemByName(String menuItem) {
        shapesMenu = menuBar.getMenu(0);
        MenuItem item = null;

        for (int i = 0; i < shapesMenu.getItemCount(); i++) {
            String itemName = shapesMenu.getItem(i).getName();

            if (itemName.equals(menuItem)) {
                item = shapesMenu.getItem(i);
                break;
            }
        }

        return item;
    }

    public MenuItem retrieveColorsMenuItemByName(String menuItem) {
        colorsMenu = menuBar.getMenu(1);
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

    public void testShapesMenuItemBehavior(String menuItemName) {
        MenuItem item = retrieveShapesMenuItemByName(menuItemName);

        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));

        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
    }

    public void testColorsMenuItemBehavior(String menuItemName) {
        MenuItem item = retrieveColorsMenuItemByName(menuItemName);

        item.setShortcut(new MenuShortcut(KeyEvent.VK_A, false));

        robot().pressKey(KeyEvent.VK_CONTROL);
        robot().pressKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_A);
        robot().releaseKey(KeyEvent.VK_CONTROL);
        robot().waitForIdle();
    }

    @Test
    public void shouldHaveAllComponents() {
        shapesMenu = menuBar.getMenu(0);
        colorsMenu = menuBar.getMenu(1);
        mainCanvas = (Canvas) TestUtils.findComponent("mainCanvas", true);
        assertNotNull(mainCanvas, "No mainCanvas found.");

        String[] shapesMenuItemNames = { "rectangleMenuItem", "circleMenuItem", "linesMenuItem" };

        assertNotNull(shapesMenu, "No shapesMenu found.");

        for (String menuItemName : shapesMenuItemNames) {
            MenuItem item = retrieveShapesMenuItemByName(menuItemName);
            assertNotNull(item, "No " + menuItemName + " found.");
        }

        String[] colorsMenuItemNames = { "redMenuItem", "greenMenuItem", "blueMenuItem" };

        assertNotNull(colorsMenu, "No colorsMenu found.");

        for (String menuItemName : colorsMenuItemNames) {
            MenuItem item = retrieveColorsMenuItemByName(menuItemName);
            assertNotNull(item, "No " + menuItemName + " found.");
        }
    }

    @Test
    public void shouldDrawRectangle() {
        testShapesMenuItemBehavior("rectangleMenuItem");

        // Draw the rectangle
        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.drawRect(anyInt, anyInt, anyInt, anyInt);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDrawCircle() {
        testShapesMenuItemBehavior("circleMenuItem");

        // Draw the rectangle
        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.drawOval(anyInt, anyInt, anyInt, anyInt);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDrawLines() {
        testShapesMenuItemBehavior("linesMenuItem");

        // Draw the rectangle
        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.drawLine(anyInt, anyInt, anyInt, anyInt);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDrawRed() {
        testColorsMenuItemBehavior("redMenuItem");

        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.setColor(Color.RED);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDrawGreen() {
        testColorsMenuItemBehavior("greenMenuItem");

        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.setColor(Color.GREEN);
                times = 1;
            }
        };
    }

    @Test
    public void shouldDrawBlue() {
        testColorsMenuItemBehavior("blueMenuItem");

        clickCenterOfCnvMain();
        drawInCnvMain();

        new Verifications() {
            {
                g.setColor(Color.BLUE);
                times = 1;
            }
        };
    }

}
