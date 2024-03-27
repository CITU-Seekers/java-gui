package com.codechum;

import com.codechum.awt.layouts.GeographyQuiz;
import static org.testng.Assert.*;

import java.awt.*;

import org.testng.annotations.*;

import org.assertj.swing.core.EmergencyAbortListener;

import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.*;

public class GeographyQuizTest extends AssertJSwingTestngTestCase {
    EmergencyAbortListener listener;
    Frame frame;
    
    TextField question1TextField, question2TextField, question3TextField;
    Button nextButton, backButton;
    Label scoreLabel;
    Panel panel1, panel2, panel3;
    
    
    @Override   
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GeographyQuiz.class).start();
        
        robot().waitForIdle();
    }
    
    // Description: Should have all components in `panel1` such as `scoreLabel`, `nextButton`, and `question1TextField`. 
    @Test 
    public void shouldHaveAllComponentsInPanel1(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);
        panel1 = (Panel) TestUtils.findComponent("panel1", true);
        question1TextField = (TextField) TestUtils.getChildNamed(panel1, "question1TextField");

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(panel1, "No panel1 found.");
        assertNotNull(question1TextField, "No question1TextField found.");
    }

    // Description: Should have all components in `panel2` such as `scoreLabel`, `nextButton`, `backButton`, and `question2TextField`.
    @Test
    public void shouldHaveAllComponentsInPanel2(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);

        robot().click(nextButton);
        robot().waitForIdle();

        backButton = (Button) TestUtils.findComponent("backButton", true);
        panel2 = (Panel) TestUtils.findComponent("panel2", true);
        question2TextField = (TextField) TestUtils.getChildNamed(panel2, "question2TextField");

        

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(backButton, "No backButton found.");
        assertNotNull(panel2, "No panel2 found.");
        assertNotNull(question2TextField, "No question2TextField found.");
    }

    // Description: Should have all components in `panel3` such as `scoreLabel`, `nextButton`, `backButton`, and `question3TextField`.
    @Test
    public void shouldHaveAllComponentsInPanel3(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);

        robot().click(nextButton);
        robot().waitForIdle();
        robot().click(nextButton);
        robot().waitForIdle();

        backButton = (Button) TestUtils.findComponent("backButton", true);
        panel3 = (Panel) TestUtils.findComponent("panel3", true);
        question3TextField = (TextField) TestUtils.getChildNamed(panel3, "question3TextField");

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(backButton, "No backButton found.");
        assertNotNull(panel3, "No panel3 found.");
        assertNotNull(question3TextField, "No question3TextField found.");
    }

    // Description: Should compute score correctly and display it in `scoreLabel`.
    @Test
    public void shouldComputeScore(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);
        panel1 = (Panel) TestUtils.findComponent("panel1", true);
        question1TextField = (TextField) TestUtils.getChildNamed(panel1, "question1TextField");

        robot().click(question1TextField);
        robot().enterText("Paris");

        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 1/3");

        backButton = (Button) TestUtils.findComponent("backButton", true);
        panel2 = (Panel) TestUtils.findComponent("panel2", true);
        question2TextField = (TextField) TestUtils.getChildNamed(panel2, "question2TextField");

        robot().click(question2TextField);
        robot().enterText("Tokyo");
        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 2/3");

        panel3 = (Panel) TestUtils.findComponent("panel3", true);
        question3TextField = (TextField) TestUtils.getChildNamed(panel3, "question3TextField");

        robot().click(question3TextField);
        robot().enterText("Berlin");
        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 3/3");
    }
    
}
