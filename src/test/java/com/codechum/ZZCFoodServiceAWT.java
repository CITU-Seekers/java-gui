package com.codechum;

import java.awt.*;
import java.util.Arrays;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;

import customized.FoodServiceAWT;

public class ZZCFoodServiceAWT extends AssertJSwingTestngTestCase {
    FrameFixture frame;
    EmergencyAbortListener listener;
    
    Checkbox dineInCheckbox, takeOutCheckbox, sausageCheckbox, mushroomCheckbox, pepperoniCheckbox;
    List sizeList, orderList;
    Choice crustChoice;
    Button placeOrderButton, voidButton, proceedButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(FoodServiceAWT.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    // Description: Should have all components `dineInCheckbox`, `takeOutCheckbox`, `sausagCheckbox`, `mushroomCheckbox`, `pepperoniCheckbox`, `sizeList`, `orderList`, `crustChoice`, `placeOrderButton`, `voidButton`, and `proceedButton`.
    @Test
    public void shouldHaveAllComponents() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        orderList = (List) TestUtils.findComponent("orderList", true);
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        placeOrderButton = (Button) TestUtils.findComponent("placeOrderButton", true);
        voidButton = (Button) TestUtils.findComponent("voidButton", true);
        proceedButton = (Button) TestUtils.findComponent("proceedButton", true);
        
        assertNotNull(dineInCheckbox, "No dineInCheckbox found.");
        assertNotNull(takeOutCheckbox, "No takeOutCheckbox found.");
        assertNotNull(sausageCheckbox, "No sausageCheckbox found.");
        assertNotNull(mushroomCheckbox, "No mushroomCheckbox found.");
        assertNotNull(pepperoniCheckbox, "No pepperoniCheckbox found.");
        assertNotNull(sizeList, "No sizeList found.");
        assertNotNull(orderList, "No orderList found.");
        assertNotNull(crustChoice, "No crustChoice found.");
        assertNotNull(placeOrderButton, "No placeOrderButton found.");
        assertNotNull(voidButton, "No voidButton found.");
        assertNotNull(proceedButton, "No proceedButton found.");
    }

    // Description: Should have a text value of "Dine In" for `dineInCheckbox`, and "Take Out" for `takeOutCheckbox`.
    @Test
    public void shouldHaveCorrectTextValueInFoodService() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        
        assertEquals(dineInCheckbox.getLabel(), "Dine In");
        assertEquals(takeOutCheckbox.getLabel(), "Take Out");
    }

    // Description: Should have a text value of "Sausage P40" for `sausagCheckbox`, "Mushroom P30" for `mushroomCheckbox`, and "Pepperoni P50" for `pepperoniCheckbox`.
    @Test
    public void shouldHaveCorrectTextValueInToppings() {
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        
        assertEquals(sausageCheckbox.getLabel(), "Sausage P40");
        assertEquals(mushroomCheckbox.getLabel(), "Mushroom P30");
        assertEquals(pepperoniCheckbox.getLabel(), "Pepperoni P50");
    }

    // Solo P50, Medium 10inch P100, Large 12inch P150, Family 14inch P200
    // Description: Should have a list of items with text values "Solo P50", "Medium 10inch P100", "Large 12inch P150", and "Family 14inch P200" for `sizeList`.
    @Test
    public void shouldHaveCorrectTextValueInList() {
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        
        String[] expectedSizeListItems = {"Solo P50", "Medium 10inch P100", "Large 12inch P150", "Family 14inch P200"};
        String[] actualSizeListItems = sizeList.getItems();
        
        assertEquals(actualSizeListItems, expectedSizeListItems);
    }

    // Thin P30, Thick P50, Stuffed P80, Hand-Tossed P100
    // Description: Should have a list of items with text values "Thin P30", "Thick P50", "Stuffed P80", and "Hand-Tossed P100" for `crustChoice`.
    @Test
    public void shouldHaveCorrectTextValueInChoice() {
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        
        String[] expectedCrustChoiceItems = {"Thin P30", "Thick P50", "Stuffed P80", "Hand-Tossed P100"};
        int actualCrustChoiceItems = crustChoice.getItemCount();

        for (int i = 0; i < actualCrustChoiceItems; i++) {
            assertEquals(crustChoice.getItem(i), expectedCrustChoiceItems[i]);
        }
    }

    // Description: Should add correct items to `orderList`, show newline before total, and show correct total value when `placeOrderButton` is clicked.
    @Test
    public void shouldAddCorrectItemsToOrderList() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        orderList = (List) TestUtils.findComponent("orderList", true);
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        placeOrderButton = (Button) TestUtils.findComponent("placeOrderButton", true);
        int total = 40 + 30 + 50 + 100 + 80;

        robot().click(dineInCheckbox);
        robot().waitForIdle();
        robot().click(sausageCheckbox);
        robot().waitForIdle();
        robot().click(mushroomCheckbox);
        robot().waitForIdle();
        robot().click(pepperoniCheckbox);
        robot().waitForIdle();

        sizeList.select(1);  // This assumes the second item is the correct size
        crustChoice.select(2);  // This assumes the third choice is the correct crust

        robot().click(placeOrderButton);
        robot().waitForIdle();

        // Check each item in the List (assuming items are added to the List in the correct order)
        assertEquals(orderList.getItem(0), "Dine In");
        assertEquals(orderList.getItem(1), "Sausage P40");
        assertEquals(orderList.getItem(2), "Mushroom P30");
        assertEquals(orderList.getItem(3), "Pepperoni P50");
        assertEquals(orderList.getItem(4), "Medium 10inch P100");
        assertEquals(orderList.getItem(5), "Stuffed P80");
        assertEquals(orderList.getItem(6), "\n");
        assertEquals(orderList.getItem(7), "Total Amount: P" + total);
    }

    // Description: Show show Dialog with message "TRANSACTION VOIDED", and reset components when `voidButton` is clicked.
    @Test
    public void shouldShowDialogAndResetComponents() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        orderList = (List) TestUtils.findComponent("orderList", true);
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        placeOrderButton = (Button) TestUtils.findComponent("placeOrderButton", true);
        voidButton = (Button) TestUtils.findComponent("voidButton", true);
        proceedButton = (Button) TestUtils.findComponent("proceedButton", true);

        robot().click(dineInCheckbox);
        robot().waitForIdle();
        robot().click(sausageCheckbox);
        robot().waitForIdle();
        robot().click(mushroomCheckbox);
        robot().waitForIdle();
        robot().click(pepperoniCheckbox);
        robot().waitForIdle();

        sizeList.select(1);  // This assumes the second item is the correct size
        crustChoice.select(2);  // This assumes the third choice is the correct crust

        robot().click(placeOrderButton);
        robot().waitForIdle();

        robot().click(voidButton);
        robot().waitForIdle();

        Dialog dialog = findVisibleDialog();
        Label messageLabel = findLabelInDialog(dialog);
        assertEquals(messageLabel.getText(), "TRANSACTION VOIDED");
        

        // Check if the components are reset
        assertFalse(dineInCheckbox.getState());
        assertFalse(takeOutCheckbox.getState());
        assertFalse(sausageCheckbox.getState());
        assertFalse(mushroomCheckbox.getState());
        assertFalse(pepperoniCheckbox.getState());
        assertEquals(sizeList.getSelectedIndex(), -1);
        assertEquals(orderList.getItemCount(), 0);
    }

    // Description: Should show correct change message in Dialog when amount is sufficient after clicking `proceedButton` and entering amount.
    @Test
    public void shouldShowCorrectChangeMessage() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        orderList = (List) TestUtils.findComponent("orderList", true);
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        placeOrderButton = (Button) TestUtils.findComponent("placeOrderButton", true);
        voidButton = (Button) TestUtils.findComponent("voidButton", true);
        proceedButton = (Button) TestUtils.findComponent("proceedButton", true);
        int total = 40 + 30 + 50 + 100 + 80;

        robot().click(dineInCheckbox);
        robot().waitForIdle();
        robot().click(sausageCheckbox);
        robot().waitForIdle();
        robot().click(mushroomCheckbox);
        robot().waitForIdle();
        robot().click(pepperoniCheckbox);
        robot().waitForIdle();

        sizeList.select(1);  // This assumes the second item is the correct size
        crustChoice.select(2);  // This assumes the third choice is the correct crust

        robot().click(placeOrderButton);
        robot().waitForIdle();

        robot().click(proceedButton);
        robot().waitForIdle();

        Dialog dialog = findVisibleDialog(); 
        TextField amountTextField = findTextFieldInDialog(dialog);
        Button okButton = findButtonWithTextInDialog(dialog, "OK");

        robot().click(amountTextField);
        robot().enterText("1000");
        robot().click(okButton);

   
        Dialog anotherDialog = findVisibleDialog();
        Label messageLabel = findLabelInDialog(anotherDialog);

        assertEquals(messageLabel.getText(), "Change: " + (1000 - total));
    }

    // Description: Should show "INSUFFICIENT CASH" message in Dialog when payment amount is less than total amount.
    @Test
    public void shouldShowInsufficientCashMessage() {
        dineInCheckbox = (Checkbox) TestUtils.findComponent("dineInCheckbox", true);
        takeOutCheckbox = (Checkbox) TestUtils.findComponent("takeOutCheckbox", true);
        sausageCheckbox = (Checkbox) TestUtils.findComponent("sausageCheckbox", true);
        mushroomCheckbox = (Checkbox) TestUtils.findComponent("mushroomCheckbox", true);
        pepperoniCheckbox = (Checkbox) TestUtils.findComponent("pepperoniCheckbox", true);
        sizeList = (List) TestUtils.findComponent("sizeList", true);
        orderList = (List) TestUtils.findComponent("orderList", true);
        crustChoice = (Choice) TestUtils.findComponent("crustChoice", true);
        placeOrderButton = (Button) TestUtils.findComponent("placeOrderButton", true);
        voidButton = (Button) TestUtils.findComponent("voidButton", true);
        proceedButton = (Button) TestUtils.findComponent("proceedButton", true);
        int total = 40 + 30 + 50 + 100 + 80;

        robot().click(dineInCheckbox);
        robot().waitForIdle();
        robot().click(sausageCheckbox);
        robot().waitForIdle();
        robot().click(mushroomCheckbox);
        robot().waitForIdle();
        robot().click(pepperoniCheckbox);
        robot().waitForIdle();

        sizeList.select(1);  // This assumes the second item is the correct size
        crustChoice.select(2);  // This assumes the third choice is the correct crust

        robot().click(placeOrderButton);
        robot().waitForIdle();

        robot().click(proceedButton);
        robot().waitForIdle();

        Dialog dialog = findVisibleDialog(); 
        TextField amountTextField = findTextFieldInDialog(dialog);
        Button okButton = findButtonWithTextInDialog(dialog, "OK");

        robot().click(amountTextField);
        robot().enterText("50");
        robot().click(okButton);

        Dialog anotherDialog = findVisibleDialog();
        Label messageLabel = findLabelInDialog(anotherDialog);

        assertEquals(messageLabel.getText(), "INSUFFICIENT CASH");
    }


    private Dialog findVisibleDialog() {
    // Logic to find the visible dialog
    return (Dialog) Arrays.stream(Frame.getFrames())
            .flatMap(f -> Arrays.stream(f.getOwnedWindows()))
            .filter(d -> d instanceof Dialog && d.isVisible())
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Dialog not found"));
    }

    private Label findLabelInDialog(Dialog dialog) {
        // Logic to navigate dialog components and find the Label with the message
        for (Component comp : dialog.getComponents()) {
            if (comp instanceof Label) {
                return (Label) comp;
            }
        }
        throw new RuntimeException("Label not found in dialog");
    }

    private TextField findTextFieldInDialog(Dialog dialog) {
        // Logic to navigate dialog components and find the TextField with the amount
        for (Component comp : dialog.getComponents()) {
            if (comp instanceof TextField) {
                return (TextField) comp;
            }
        }
        throw new RuntimeException("TextField not found in dialog");
    }

    private Button findButtonWithTextInDialog(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof Button) {
                Button button = (Button) comp;
                if (button.getLabel().equals(text)) {
                    return button;
                }
            } else if (comp instanceof Container) {
                Button found = findButtonWithTextInDialog((Container) comp, text);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;  // Return null or throw an exception if button is not found
    }

}
