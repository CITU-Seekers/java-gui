
package com.codechum;

import customized.InventoryManagement;
import javax.swing.*;

import static org.testng.Assert.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.*;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.*;


public class ZZCInventoryManagement extends AssertJSwingTestngTestCase {
    FrameFixture loginFrame, registrationFrame, inventoryManagementFrame;
    EmergencyAbortListener listener;

    JTextField usernameTextField;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    JList itemList;
    JTextField itemNameField;
    JTextField itemQuantityField;
    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton logoutButton;
    JTextField newUsernameField;
    JPasswordField newPasswordField;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(InventoryManagement.class).start();
        robot().waitForIdle();

        // add delay
        loginFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Login".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).withTimeout(10000).using(robot());
    }

    // Description: Should have all components named `usernameField`, `passwordField`, `loginButton`, and `registerButton` on the first frame.
    @Test
    public void shouldHaveAllComponentsInLoginFrame() {
        usernameField = (JTextField) TestUtils.getChildNamed(loginFrame.target(), "usernameField");
        passwordField = (JPasswordField) TestUtils.getChildNamed(loginFrame.target(), "passwordField");
        loginButton = (JButton) TestUtils.getChildNamed(loginFrame.target(), "loginButton");
        registerButton = (JButton) TestUtils.getChildNamed(loginFrame.target(), "registerButton");

        assertNotNull(usernameField);   
        assertNotNull(passwordField);
        assertNotNull(loginButton);
        assertNotNull(registerButton);
    }

    // Description: Should have all components named `newUsernameField`, `newPasswordField`, and `registerButton` on the second frame after clicking the `registerButton` on the first frame then show "Registration successful!". After, should show "Login successful!" dialog after logging in with the registered username and password.
    @Test
    public void shouldHaveAllComponentsInRegistrationFrameAndShowRegistrationMessages() {
        loginFrame.button("registerButton").click();
        robot().waitForIdle();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        newUsernameField = (JTextField) TestUtils.getChildNamed(registrationFrame.target(), "newUsernameField");
        newPasswordField = (JPasswordField) TestUtils.getChildNamed(registrationFrame.target(), "newPasswordField");
        registerButton = (JButton) TestUtils.getChildNamed(registrationFrame.target(), "registerButton");

        assertNotNull(newUsernameField);   
        assertNotNull(newPasswordField);
        assertNotNull(registerButton);

        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();

        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Registration successful!");

        TestUtils.clickOKButtonInDialog();

        loginFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Login".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        assertNotNull(loginFrame);

        loginFrame.textBox("usernameField").focus().enterText("admin");
        robot().waitForIdle();
        loginFrame.textBox("passwordField").focus().enterText("admin");
        robot().waitForIdle();

        loginFrame.button("loginButton").click();

        robot().waitForIdle();

        message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Login successful!");
    }

    // Description: Should have all components named `itemList`, `itemNameField`, `itemQuantityField`, `addButton`, `updateButton`, `deleteButton`, and `logoutButton` on the third frame after registering and clicking the `loginButton` on the first frame.
    @Test
    public void shouldHaveAllComponentsInInventoryManagementFrame() {
        loginFrame.button("registerButton").click();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();
        
        TestUtils.clickOKButtonInDialog();

        loginFrame.textBox("usernameField").focus().enterText("admin");
        robot().waitForIdle();
        loginFrame.textBox("passwordField").focus().enterText("admin");
        robot().waitForIdle();
        loginFrame.button("loginButton").click();
        robot().waitForIdle();

        TestUtils.clickOKButtonInDialog();

        inventoryManagementFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Inventory Management".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        itemList = (JList) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemList");
        itemNameField = (JTextField) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemNameField");
        itemQuantityField = (JTextField) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemQuantityField");
        addButton = (JButton) TestUtils.getChildNamed(inventoryManagementFrame.target(), "addButton");
        updateButton = (JButton) TestUtils.getChildNamed(inventoryManagementFrame.target(), "updateButton");
        deleteButton = (JButton) TestUtils.getChildNamed(inventoryManagementFrame.target(), "deleteButton");
        logoutButton = (JButton) TestUtils.getChildNamed(inventoryManagementFrame.target(), "logoutButton");

        assertNotNull(itemList);   
        assertNotNull(itemNameField);
        assertNotNull(itemQuantityField);
        assertNotNull(addButton);
        assertNotNull(updateButton);
        assertNotNull(deleteButton);
        assertNotNull(logoutButton);

    }

    // Description: Should show "Username already exists. Please choose a different one." dialog after registering with an existing username.
    @Test
    public void shouldShowUsernameExistsMessage() {
        loginFrame.button("registerButton").click();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();
        
        TestUtils.clickOKButtonInDialog();

        loginFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Login".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        loginFrame.button("registerButton").click();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        registrationFrame.textBox("newUsernameField").deleteText();
        robot().waitForIdle();
        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.textBox("newPasswordField").deleteText();
        robot().waitForIdle();
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();

        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Username already exists. Please choose a different one.");
    }

    // Description: Should show "Incorrect password or username. Please try again." dialog after logging in with an incorrect password.
    @Test
    public void shouldShowIncorrectPasswordMessage() {
        loginFrame.button("registerButton").click();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        robot().waitForIdle();
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();
        
        TestUtils.clickOKButtonInDialog();

        loginFrame.textBox("usernameField").focus().enterText("admin");
        loginFrame.textBox("passwordField").focus().enterText("wrongpassword");
        loginFrame.button("loginButton").click();
        robot().waitForIdle();

        String message = TestUtils.getJOptionPaneMessage();

        assertEquals(message, "Incorrect password or username. Please try again.");
    }

    // Description: Should perform add, update, and delete operations properly.
    @Test
    public void shouldPerformAddUpdateDeleteProperly() {
        loginFrame.button("registerButton").click();

        registrationFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Registration".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        registrationFrame.textBox("newUsernameField").focus().enterText("admin");
        registrationFrame.textBox("newPasswordField").focus().enterText("admin");
        registrationFrame.button("registerButton").click();
        robot().waitForIdle();
        
        TestUtils.clickOKButtonInDialog();

        loginFrame.textBox("usernameField").focus().enterText("admin");
        loginFrame.textBox("passwordField").focus().enterText("admin");
        loginFrame.button("loginButton").click();
        robot().waitForIdle();

        TestUtils.clickOKButtonInDialog();

        inventoryManagementFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Inventory Management".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        itemList = (JList) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemList");
        itemNameField = (JTextField) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemNameField");
        itemQuantityField = (JTextField) TestUtils.getChildNamed(inventoryManagementFrame.target(), "itemQuantityField");
        addButton = (JButton) TestUtils.getChildNamed(inventoryManagementFrame.target(), "addButton");

        inventoryManagementFrame.textBox("itemNameField").focus().enterText("item1");
        inventoryManagementFrame.textBox("itemQuantityField").focus().enterText("1");
        inventoryManagementFrame.button("addButton").click();
        robot().waitForIdle();

        String firstItem = itemList.getModel().getElementAt(0).toString();

        assertEquals(firstItem, "item1 - Quantity: 1");

        inventoryManagementFrame.list("itemList").selectItem(0);

        robot().waitForIdle();

        String itemName = inventoryManagementFrame.textBox("itemNameField").text();
        String itemQuantity = inventoryManagementFrame.textBox("itemQuantityField").text();

        assertEquals(itemName, "item1");
        assertEquals(itemQuantity, "1");

        inventoryManagementFrame.textBox("itemNameField").deleteText();
        robot().waitForIdle();
        inventoryManagementFrame.textBox("itemQuantityField").deleteText();
        robot().waitForIdle();

        inventoryManagementFrame.textBox("itemNameField").focus().enterText("item2");
        robot().waitForIdle();
        inventoryManagementFrame.textBox("itemQuantityField").focus().enterText("2");
        robot().waitForIdle();
        inventoryManagementFrame.button("updateButton").click();
        robot().waitForIdle();

        firstItem = itemList.getModel().getElementAt(0).toString();

        assertEquals(firstItem, "item2 - Quantity: 2");

        inventoryManagementFrame.list("itemList").selectItem(0);

        robot().waitForIdle();

        inventoryManagementFrame.button("deleteButton").click();

        int itemCount = itemList.getModel().getSize();

        assertEquals(itemCount, 0);

        inventoryManagementFrame.button("logoutButton").click();

        loginFrame = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
            protected boolean isMatching(JFrame frame) {
                return "Login".equals(frame.getTitle()) && frame.isShowing();
            }
        }).withTimeout(10000).using(robot());

        assertNotNull(loginFrame);
    }

    @Override
    protected void onTearDown() {
        if (loginFrame != null) {
            loginFrame.cleanUp();
        }

        if (registrationFrame != null) {
            registrationFrame.cleanUp();
        }

        if (inventoryManagementFrame != null) {
            inventoryManagementFrame.cleanUp();
        }

        listener.unregister();
    }
}
