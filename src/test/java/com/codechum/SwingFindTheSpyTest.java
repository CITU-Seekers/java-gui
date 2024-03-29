package com.codechum;

import com.codechum.swing.jOptionPane.SwingFindTheSpy;
import static org.testng.Assert.*;

import javax.swing.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class SwingFindTheSpyTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    String[] buttons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8", "button9", "button10"};
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SwingFindTheSpy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SwingFindTheSpy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SwingFindTheSpy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SwingFindTheSpy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        application(SwingFindTheSpy.class).start();
        robot().waitForIdle();
    }

    private boolean checkSpy(int input){
        int digit, sum = 0, product = 1;
        while (input > 0){
            digit = input % 10;
            sum += digit;
            product *= digit;
            input = input / 10;
        }
         
        return sum == product;
    }
    
    // Description: Should have all buttons `button1`, `button2`, `button3`, `button4`, `button5`, `button6`, `button7`, `button8`, `button9`, and `button10`.
    @Test
    public void shouldHaveAllButtons() {
        for (String button: buttons) {
            JButton btn = (JButton) TestUtils.findComponent(button,true);
            assertNotNull(btn, "No " + button + " found.");
        }
    }
    
    // Description: Should display JOptionPane with message "Spy found!" when a spy is identified.
    @Test
    public void shouldIdentifySpyCorrectly() {
        for (int i = 0; i < buttons.length; i++) {
            JButton button = (JButton) TestUtils.findComponent(buttons[i],true);
            robot().click(button);
            robot().waitForIdle();

            if(checkSpy(Integer.parseInt(button.getText()))) {
                String message = TestUtils.getJOptionPaneMessage();

                assertEquals(message, "Spy found!");

                TestUtils.clickOKButtonInDialog();
            }
        }
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
