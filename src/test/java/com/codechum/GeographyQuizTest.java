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
    Panel Panel1, Panel2, Panel3;
    
    
    @Override   
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(GeographyQuiz.class).start();
        
        robot().waitForIdle();
    }
    
    @Test 
    public void shouldHaveAllComponentsInPanel1(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);
        Panel1 = (Panel) TestUtils.findComponent("Panel1", true);
        question1TextField = (TextField) TestUtils.getChildNamed(Panel1, "question1TextField");

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(Panel1, "No Panel1 found.");
        assertNotNull(question1TextField, "No question1TextField found.");
    }

    @Test
    public void shouldHaveAllComponentsInPanel2(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);

        robot().click(nextButton);
        robot().waitForIdle();

        backButton = (Button) TestUtils.findComponent("backButton", true);
        Panel2 = (Panel) TestUtils.findComponent("Panel2", true);
        question2TextField = (TextField) TestUtils.getChildNamed(Panel2, "question2TextField");

        

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(backButton, "No backButton found.");
        assertNotNull(Panel2, "No Panel2 found.");
        assertNotNull(question2TextField, "No question2TextField found.");
    }

    @Test
    public void shouldHaveAllComponentsInPanel3(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);

        robot().click(nextButton);
        robot().waitForIdle();
        robot().click(nextButton);
        robot().waitForIdle();

        backButton = (Button) TestUtils.findComponent("backButton", true);
        Panel3 = (Panel) TestUtils.findComponent("Panel3", true);
        question3TextField = (TextField) TestUtils.getChildNamed(Panel3, "question3TextField");

        assertNotNull(scoreLabel, "No scoreLabel found.");
        assertNotNull(nextButton, "No nextButton found.");
        assertNotNull(backButton, "No backButton found.");
        assertNotNull(Panel3, "No Panel3 found.");
        assertNotNull(question3TextField, "No question3TextField found.");
    }

    @Test
    public void shouldComputeScore(){
        scoreLabel = (Label) TestUtils.findComponent("scoreLabel", true);
        nextButton = (Button) TestUtils.findComponent("nextButton", true);
        Panel1 = (Panel) TestUtils.findComponent("Panel1", true);
        question1TextField = (TextField) TestUtils.getChildNamed(Panel1, "question1TextField");

        robot().click(question1TextField);
        robot().enterText("Paris");

        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 1/3");

        backButton = (Button) TestUtils.findComponent("backButton", true);
        Panel2 = (Panel) TestUtils.findComponent("Panel2", true);
        question2TextField = (TextField) TestUtils.getChildNamed(Panel2, "question2TextField");

        robot().click(question2TextField);
        robot().enterText("Tokyo");
        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 2/3");

        Panel3 = (Panel) TestUtils.findComponent("Panel3", true);
        question3TextField = (TextField) TestUtils.getChildNamed(Panel3, "question3TextField");

        robot().click(question3TextField);
        robot().enterText("Berlin");
        robot().click(nextButton);
        robot().waitForIdle();
        assertEquals(scoreLabel.getText(), "Your Score: 3/3");
    }
    
}
