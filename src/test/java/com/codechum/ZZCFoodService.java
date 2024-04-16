package com.codechum;

import customized.FoodService;

import javax.swing.*;

import static org.testng.Assert.*;

import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;

import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ZZCFoodService extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;

    JRadioButton dineInRadioButton, takeOutRadioButton;
    JCheckBox sausageCheckBox, mushroomCheckBox, pepperoniCheckBox;
    JList<String> sizeList, orderList;
    JComboBox<String> crustComboBox;
    JButton placeOrderButton, voidButton, proceedButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FoodService.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components named `dineInRadioButton`, `takeOutRadioButton`, `sausageCheckBox`, `mushroomCheckBox`, `pepperoniCheckBox`, `sizeList`, `orderList`, `crustComboBox`, `placeOrderButton`, `voidButton`, and `proceedButton`.
    @Test
    public void shouldHaveAllComponents() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        takeOutRadioButton = (JRadioButton) TestUtils.findComponent("takeOutRadioButton", true);
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        sizeList = (JList) TestUtils.findComponent("sizeList", true);
        orderList = (JList) TestUtils.findComponent("orderList", true);
        crustComboBox = (JComboBox) TestUtils.findComponent("crustComboBox", true);
        placeOrderButton = (JButton) TestUtils.findComponent("placeOrderButton", true);
        voidButton = (JButton) TestUtils.findComponent("voidButton", true);
        proceedButton = (JButton) TestUtils.findComponent("proceedButton", true);
        
        assertNotNull(dineInRadioButton, "No dineInRadioButton found.");
        assertNotNull(takeOutRadioButton, "No takeOutRadioButton found.");
        assertNotNull(sausageCheckBox, "No sausageCheckBox found.");
        assertNotNull(mushroomCheckBox, "No mushroomCheckBox found.");
        assertNotNull(pepperoniCheckBox, "No pepperoniCheckBox found.");
        assertNotNull(sizeList, "No sizeList found.");
        assertNotNull(orderList, "No orderList found.");
        assertNotNull(crustComboBox, "No crustComboBox found.");
        assertNotNull(placeOrderButton, "No orderButton found.");
        assertNotNull(voidButton, "No voidButton found.");
        assertNotNull(proceedButton, "No proceedButton found.");
    }

    // Description: Should have a text value of "Dine In" for `dineInRadioButton` and "Take Out" for `takeOutRadioButton`.
    @Test
    public void shouldHaveCorrectTextValueInRadioButtons() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        takeOutRadioButton = (JRadioButton) TestUtils.findComponent("takeOutRadioButton", true);
        
        assertEquals(dineInRadioButton.getText(), "Dine In");
        assertEquals(takeOutRadioButton.getText(), "Take Out");
    }

    // Description: Should have a text value of "Sausage P40" for `sausageCheckBox`, "Mushroom P30" for `mushroomCheckBox`, and "Pepperoni P50" for `pepperoniCheckBox`.
    @Test
    public void shouldHaveCorrectTextValueInCheckBoxes() {
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        
        assertEquals(sausageCheckBox.getText(), "Sausage P40");
        assertEquals(mushroomCheckBox.getText(), "Mushroom P30");
        assertEquals(pepperoniCheckBox.getText(), "Pepperoni P50");
    }

    // Solo P50, Medium 10inch P100, Large 12inch P150, Family 14inch P200
    // Description: Should have a list of items with text values "Solo P50", "Medium 10inch P100", "Large 12inch P150", and "Family 14inch P200" for `sizeList`.
    @Test
    public void shouldHaveCorrectTextValueInList() {
        sizeList = (JList<String>) TestUtils.findComponent("sizeList", true);
        
        String[] expectedValues = {"Solo P50", "Medium 10inch P100", "Large 12inch P150", "Family 14inch P200"};
        ListModel<String> model = sizeList.getModel();
        
        for (int i = 0; i < model.getSize(); i++) {
            assertEquals(model.getElementAt(i), expectedValues[i]);
        }
    }

    // Thin P30, Thick P50, Stuffed P80, Hand-Tossed P100
    // Description: Should have a list of items with text values "Thin P30", "Thick P50", "Stuffed P80", and "Hand-Tossed P100" for `crustComboBox`.
    @Test
    public void shouldHaveCorrectTextValueInComboBox() {
        crustComboBox = (JComboBox<String>) TestUtils.findComponent("crustComboBox", true);
        
        String[] expectedValues = {"Thin P30", "Thick P50", "Stuffed P80", "Hand-Tossed P100"};
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) crustComboBox.getModel();
        
        for (int i = 0; i < model.getSize(); i++) {
            assertEquals(model.getElementAt(i), expectedValues[i]);
        }
    }

    // Description: Should add correct items to `orderList`, show newline before total, and show correct total value when `placeOrderButton` is clicked.
    @Test
    public void shouldAddCorrectItemsToOrderList() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        sizeList = (JList<String>) TestUtils.findComponent("sizeList", true);
        crustComboBox = (JComboBox<String>) TestUtils.findComponent("crustComboBox", true);
        orderList = (JList<String>) TestUtils.findComponent("orderList", true);
        placeOrderButton = (JButton) TestUtils.findComponent("placeOrderButton", true);
        int total = 40 + 30 + 50 + 100 + 80;


        robot().click(dineInRadioButton);
        robot().waitForIdle();
        robot().click(sausageCheckBox);
        robot().waitForIdle();
        robot().click(mushroomCheckBox);
        robot().waitForIdle();
        robot().click(pepperoniCheckBox);
        robot().waitForIdle();
        
        frame.list("sizeList").selectItem(1);
        frame.comboBox("crustComboBox").selectItem(2);
        
        robot().click(placeOrderButton);
        robot().waitForIdle();

        DefaultListModel<String> model = (DefaultListModel<String>) orderList.getModel();
        
        assertEquals(model.getElementAt(0), "Dine In");
        assertEquals(model.getElementAt(1), "Sausage P40");
        assertEquals(model.getElementAt(2), "Mushroom P30");
        assertEquals(model.getElementAt(3), "Pepperoni P50");
        assertEquals(model.getElementAt(4), "Medium 10inch P100");
        assertEquals(model.getElementAt(5), "Stuffed P80");
        assertEquals(model.getElementAt(6), "\n");
        assertEquals(model.getElementAt(7), "Total Amount: P" + total);
    }

    // Description: Should show JOption message "TRANSACTION VOIDED", clear `orderList`, and reset components when `voidButton` is clicked.
    @Test
    public void shouldVoidTransaction() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        sizeList = (JList<String>) TestUtils.findComponent("sizeList", true);
        crustComboBox = (JComboBox<String>) TestUtils.findComponent("crustComboBox", true);
        orderList = (JList<String>) TestUtils.findComponent("orderList", true);
        voidButton = (JButton) TestUtils.findComponent("voidButton", true);
        
        robot().click(dineInRadioButton);
        robot().waitForIdle();
        robot().click(sausageCheckBox);
        robot().waitForIdle();
        robot().click(mushroomCheckBox);
        robot().waitForIdle();
        robot().click(pepperoniCheckBox);
        robot().waitForIdle();
        
        frame.list("sizeList").selectItem(1);
        frame.comboBox("crustComboBox").selectItem(2);
        
        robot().click(voidButton);
        robot().waitForIdle();


        String message = TestUtils.getJOptionPaneMessage();

        assertFalse(sausageCheckBox.isSelected());
        assertFalse(mushroomCheckBox.isSelected());
        assertFalse(pepperoniCheckBox.isSelected());
        assertEquals(sizeList.getSelectedIndex(), -1);
        assertEquals(message, "TRANSACTION VOIDED");
    }

    // Description: Should show JOption input dialog for payment on `proceedButton` click.
    @Test
    public void shouldProceedToPayment() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        sizeList = (JList<String>) TestUtils.findComponent("sizeList", true);
        crustComboBox = (JComboBox<String>) TestUtils.findComponent("crustComboBox", true);
        orderList = (JList<String>) TestUtils.findComponent("orderList", true);
        voidButton = (JButton) TestUtils.findComponent("voidButton", true);
        placeOrderButton = (JButton) TestUtils.findComponent("placeOrderButton", true);
        proceedButton = (JButton) TestUtils.findComponent("proceedButton", true);
        int total = 40 + 30 + 50 + 100 + 80;
        
        robot().click(dineInRadioButton);
        robot().waitForIdle();
        robot().click(sausageCheckBox);
        robot().waitForIdle();
        robot().click(mushroomCheckBox);
        robot().waitForIdle();
        robot().click(pepperoniCheckBox);
        robot().waitForIdle();
        
        frame.list("sizeList").selectItem(1);
        frame.comboBox("crustComboBox").selectItem(2);


        robot().click(placeOrderButton);
        robot().waitForIdle();

        robot().click(proceedButton);
        robot().waitForIdle();

        // Find the text field in the dialog
        JDialog dialog = (JDialog) Arrays.stream(JFrame.getFrames())
                                     .flatMap(frame -> Arrays.stream(frame.getOwnedWindows()))
                                     .filter(window -> window instanceof JDialog && window.isVisible())
                                     .findFirst()
                                     .orElseThrow(() -> new RuntimeException("Dialog not found"));

        // Find the JTextField within the JOptionPane
        JTextField textField = findTextField(dialog);

        if (textField == null) {
            fail("No text field found in dialog.");
        }

        // Enter the payment amount
        robot().click(textField);
        robot().enterText("1000");

        // Press Enter
        robot().pressAndReleaseKeys(VK_ENTER);

        String message = TestUtils.getJOptionPaneMessage();


        assertEquals(message, "Change: " + (1000 - total));
    }

    // Description: Should show "INSUFFICIENT CASH" message in JOption dialog when payment amount is less than total amount.
    @Test
    public void shouldShowInsufficientCashMessage() {
        dineInRadioButton = (JRadioButton) TestUtils.findComponent("dineInRadioButton", true);
        sausageCheckBox = (JCheckBox) TestUtils.findComponent("sausageCheckBox", true);
        mushroomCheckBox = (JCheckBox) TestUtils.findComponent("mushroomCheckBox", true);
        pepperoniCheckBox = (JCheckBox) TestUtils.findComponent("pepperoniCheckBox", true);
        sizeList = (JList<String>) TestUtils.findComponent("sizeList", true);
        crustComboBox = (JComboBox<String>) TestUtils.findComponent("crustComboBox", true);
        orderList = (JList<String>) TestUtils.findComponent("orderList", true);
        voidButton = (JButton) TestUtils.findComponent("voidButton", true);
        placeOrderButton = (JButton) TestUtils.findComponent("placeOrderButton", true);
        proceedButton = (JButton) TestUtils.findComponent("proceedButton", true);
        int total = 40 + 30 + 50 + 100 + 80;
        
        robot().click(dineInRadioButton);
        robot().waitForIdle();
        robot().click(sausageCheckBox);
        robot().waitForIdle();
        robot().click(mushroomCheckBox);
        robot().waitForIdle();
        robot().click(pepperoniCheckBox);
        robot().waitForIdle();
        
        frame.list("sizeList").selectItem(1);
        frame.comboBox("crustComboBox").selectItem(2);

        robot().click(placeOrderButton);
        robot().waitForIdle();

        robot().click(proceedButton);
        robot().waitForIdle();

        JDialog dialog = (JDialog) Arrays.stream(JFrame.getFrames())
                                     .flatMap(frame -> Arrays.stream(frame.getOwnedWindows()))
                                     .filter(window -> window instanceof JDialog && window.isVisible())
                                     .findFirst()
                                     .orElseThrow(() -> new RuntimeException("Dialog not found"));

        // Find the JTextField within the JOptionPane
        JTextField textField = findTextField(dialog);

        if (textField == null) {
            fail("No text field found in dialog.");
        }

        // Enter the payment amount
        robot().click(textField);
        robot().enterText("50");

        // Press Enter
        robot().pressAndReleaseKeys(VK_ENTER);

        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "INSUFFICIENT CASH");
    }

    private JTextField findTextField(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                return (JTextField) comp;
            } else if (comp instanceof Container) {
                JTextField result = findTextField((Container) comp);
                if (result != null) {
                    return result;
                }
            }
        }
        return null; // or throw an exception if appropriate
    }
}
