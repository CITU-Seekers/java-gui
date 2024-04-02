package com.codechum;

import com.codechum.swing.jRadioButton.ReceiptGenerator;
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

public class SwingReceiptGeneratorTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    FrameFixture frame;

    JCheckBox shoesCheckBox, pantsCheckBox, shirtCheckBox;
    JRadioButton defaultRadioButton, specialRadioButton;
    JButton generateButton;
    JTextArea receiptTextArea;
    JTextField nameTextField;
    JComboBox paymentMethodComboBox;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(ReceiptGenerator.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    public void clickJCheckBox(String component){
        JCheckBox checkbox = (JCheckBox) TestUtils.findComponent(component, true);
        robot().click(checkbox);
        robot().waitForIdle();
    }

    public void clickRadioButton(String component){
        JRadioButton rad = (JRadioButton) TestUtils.findComponent(component, true);
        robot().click(rad);
        robot().waitForIdle();
    }
    
    // Description: Should have all checkboxes `shoesCheckBox`, `pantsCheckBox`, `shirtCheckBox`, `defaultRadioButton`, and `specialRadioButton`.
    @Test
    public void shouldHaveAllCheckBoxesAndRadioButtons() {
        String[] checkboxes = {"shoesCheckBox", "pantsCheckBox", "shirtCheckBox"};
        String[] radioButtons = {"defaultRadioButton", "specialRadioButton"};
        
        for (String checkbox : checkboxes) {
            JCheckBox checkboxButton = (JCheckBox) TestUtils.findComponent(checkbox, true);
            assertNotNull(checkboxButton, "No " + checkbox + " found.");
        }

        for (String rad: radioButtons) {
            JRadioButton radiobutton = (JRadioButton) TestUtils.findComponent(rad, true);
            assertNotNull(radiobutton, "No " + rad + " found.");
        }
    }
    
    // Description: Should have all other components `generateButton`, `receiptTextArea`, `nameTextField`, and `paymentMethodComboBox`.
    @Test
    public void shouldHaveAllOtherComponents() {
        generateButton = (JButton) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (JTextArea) TestUtils.findComponent("receiptTextArea", true);
        nameTextField = (JTextField) TestUtils.findComponent("nameTextField", true);
        paymentMethodComboBox = (JComboBox) TestUtils.findComponent("paymentMethodComboBox", true);
        
        assertNotNull(generateButton, "No generateButton found.");
        assertNotNull(receiptTextArea, "No receiptTextArea found.");
        assertNotNull(nameTextField, "No nameTextField found.");
        assertNotNull(paymentMethodComboBox, "No paymentMethodComboBox found.");
    }
    
    // Description: Should only select one radio button at a time between `defaultRadioButton` and `specialRadioButton`.
    @Test
    public void shouldOnlySelectOneRadioButton(){
        defaultRadioButton = (JRadioButton) TestUtils.findComponent("defaultRadioButton", true);
        specialRadioButton = (JRadioButton) TestUtils.findComponent("specialRadioButton", true);

        robot().click(defaultRadioButton);
        robot().waitForIdle();

        assertTrue(defaultRadioButton.isSelected(), "The defaultRadioButton should be selected when clicked.");
        assertFalse(specialRadioButton.isSelected(), "The specialRadioButton should be unselected when another check box is clicked.");

        robot().click(specialRadioButton);
        robot().waitForIdle();

        assertTrue(specialRadioButton.isSelected(), "The specialRadioButton should be selected when clicked.");
        assertFalse(defaultRadioButton.isSelected(), "The defaultRadioButton should be unselected when another check box is clicked.");
    }
    
    // Description: Should display receipt with name, items, payment method, and total cost for default promo that exceeds 300 cost in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForDefaultPromoCostExceed300() {
        nameTextField = (JTextField) TestUtils.findComponent("nameTextField", true);
        generateButton = (JButton) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (JTextArea) TestUtils.findComponent("receiptTextArea", true);

        frame.comboBox("paymentMethodComboBox").selectItem("Cash");
        frame.textBox("nameTextField").setText("John Doe");
        clickJCheckBox("shirtCheckBox");
        clickJCheckBox("pantsCheckBox");
        clickJCheckBox("shoesCheckBox");
        clickRadioButton("defaultRadioButton");
        robot().click(generateButton);
        robot().waitForIdle();

        String expectedReceipt = "Name: John Doe\nItems: Shoes, Pants, Shirt\nPayment Method: Cash\nTotal Cost: 446.50";
        assertEquals(receiptTextArea.getText(), expectedReceipt);
    }

    // Description: Should display receipt with name, items, payment method, and total cost for default promo that is less than 300 cost in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForDefaultPromoCostLessThan300() {
        nameTextField = (JTextField) TestUtils.findComponent("nameTextField", true);
        generateButton = (JButton) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (JTextArea) TestUtils.findComponent("receiptTextArea", true);

        frame.comboBox("paymentMethodComboBox").selectItem("Cash");
        frame.textBox("nameTextField").setText("John Doe");
        clickJCheckBox("shirtCheckBox");
        clickJCheckBox("pantsCheckBox");
        clickRadioButton("defaultRadioButton");
        robot().click(generateButton);
        robot().waitForIdle();

        String expectedReceipt = "Name: John Doe\nItems: Pants, Shirt\nPayment Method: Cash\nTotal Cost: 270.00";
        assertEquals(receiptTextArea.getText(), expectedReceipt);
    }

    // Description: Should display receipt with name, items, payment method, and total cost for special promo in `receiptTextArea` when `generateButton` is clicked.
    @Test
    public void shouldDisplayReceiptForSpecialPromo() {
        nameTextField = (JTextField) TestUtils.findComponent("nameTextField", true);
        generateButton = (JButton) TestUtils.findComponent("generateButton", true);
        receiptTextArea = (JTextArea) TestUtils.findComponent("receiptTextArea", true);

        frame.comboBox("paymentMethodComboBox").selectItem("Cash");
        frame.textBox("nameTextField").setText("John Doe");
        clickJCheckBox("shirtCheckBox");
        clickJCheckBox("pantsCheckBox");
        clickJCheckBox("shoesCheckBox");
        clickRadioButton("specialRadioButton");
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
