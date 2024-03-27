package com.codechum;

import com.codechum.awt.checkBoxGroup.ReceiptGenerator;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class ReceiptGeneratorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;

    Checkbox shoesCheckBox, pantsCheckBox, shirtCheckBox, defaultCheckBox, specialCheckBox;
    Button generateButton;
    TextArea receiptTextArea;
    TextField nameTextField;
    Choice paymentMethodChoice;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ReceiptGenerator.class).start();
        robot().waitForIdle();
    }
    
    public void clickCheckbox(String component){
        Checkbox checkbox = (Checkbox) TestUtils.findComponent(component, true);
        robot().click(checkbox);
        robot().waitForIdle();
    }

    public void input(String name) {
        robot().click(nameTextField);
        robot().enterText(name);
    }
    
    // Description: Should have all checkboxes `shoesCheckBox`, `pantsCheckBox`, `shirtCheckBox`, `defaultCheckBox`, and `specialCheckBox`.
    @Test
    public void shouldHaveAllCheckboxes() {
        String[] checkboxes = {"shoesCheckBox", "pantsCheckBox", "shirtCheckBox", "defaultCheckBox", "specialCheckBox"};
        
        for (String checkbox : checkboxes) {
            Checkbox checkboxButton = (Checkbox) TestUtils.findComponent(checkbox, true);
            assertNotNull(checkboxButton, "No " + checkbox + " found.");
        }
    }
    
    // Description: Should have all other components `generateButton`, `receiptTextArea`, `nameTextField`, and `paymentMethodChoice`.
    @Test
    public void shouldHaveAllOtherComponents() {
        generateButton = (Button) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (TextArea) TestUtils.findComponent("receiptTextArea", true);
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        paymentMethodChoice = (Choice) TestUtils.findComponent("paymentMethodChoice", true);
        
        assertNotNull(generateButton, "No generateButton found.");
        assertNotNull(receiptTextArea, "No receiptTextArea found.");
        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(paymentMethodChoice, "No paymentMethodChoice found.");
    }
    
    // Description: Should only select one checkbox at a time.
    @Test
    public void shouldOnlySelectOneRadioButton(){
        defaultCheckBox = (Checkbox) TestUtils.findComponent("defaultCheckBox", true);
        specialCheckBox = (Checkbox) TestUtils.findComponent("specialCheckBox", true);

        robot().click(defaultCheckBox);
        robot().waitForIdle();

        assertTrue(defaultCheckBox.getState(), "The defaultCheckBox should be selected when clicked.");
        assertFalse(specialCheckBox.getState(), "The specialCheckBox should be unselected when another check box is clicked.");

        robot().click(specialCheckBox);
        robot().waitForIdle();

        assertTrue(specialCheckBox.getState(), "The specialCheckBox should be selected when clicked.");
        assertFalse(defaultCheckBox.getState(), "The defaultCheckBox should be unselected when another check box is clicked.");
    }
    
    // Description: Should display receipt with name, items, payment method, and total cost for default promo that exceeds 300 cost in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForDefaultPromoCostExceed300() {
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        paymentMethodChoice = (Choice) TestUtils.findComponent("paymentMethodChoice", true);
        generateButton = (Button) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (TextArea) TestUtils.findComponent("receiptTextArea", true);

        paymentMethodChoice.select("Cash");
        input("John Doe");
        clickCheckbox("shirtCheckBox");
        clickCheckbox("pantsCheckBox");
        clickCheckbox("shoesCheckBox");
        clickCheckbox("defaultCheckBox");
        robot().click(generateButton);
        robot().waitForIdle();

        String expectedReceipt = "Name: John Doe\nItems: Shoes, Pants, Shirt\nPayment Method: Cash\nTotal Cost: 446.50";
        assertEquals(receiptTextArea.getText(), expectedReceipt);
    }

    // Description: Should display receipt with name, items, payment method, and total cost for default promo that is less than 300 cost in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForDefaultPromoCostLessThan300() {
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        paymentMethodChoice = (Choice) TestUtils.findComponent("paymentMethodChoice", true);
        generateButton = (Button) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (TextArea) TestUtils.findComponent("receiptTextArea", true);

        paymentMethodChoice.select("Cash");
        input("John Doe");
        clickCheckbox("shirtCheckBox");
        clickCheckbox("pantsCheckBox");
        clickCheckbox("defaultCheckBox");
        robot().click(generateButton);
        robot().waitForIdle();

        String expectedReceipt = "Name: John Doe\nItems: Pants, Shirt\nPayment Method: Cash\nTotal Cost: 270.00";
        assertEquals(receiptTextArea.getText(), expectedReceipt);
    }

    // Description: Should display receipt with name, items, payment method, and total cost for special promo in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForSpecialPromo() {
        nameTextField = (TextField) TestUtils.findComponent("nameTextField", true);
        paymentMethodChoice = (Choice) TestUtils.findComponent("paymentMethodChoice", true);
        generateButton = (Button) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (TextArea) TestUtils.findComponent("receiptTextArea", true);

        paymentMethodChoice.select("Cash");
        input("John Doe");
        clickCheckbox("shirtCheckBox");
        clickCheckbox("pantsCheckBox");
        clickCheckbox("shoesCheckBox");
        clickCheckbox("specialCheckBox");
        robot().click(generateButton);
        robot().waitForIdle();

        String expectedReceipt = "Name: John Doe\nItems: Shoes, Pants, Shirt\nPayment Method: Cash\nTotal Cost: 399.50";
        assertEquals(receiptTextArea.getText(), expectedReceipt);
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
    
