package com.codechum;

import com.codechum.swing.jList.LanguageLearner;
import java.awt.Frame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LanguageLearnerTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;
    private FrameFixture frame;

    private JList wordList;
    private JTextField wordTextField;
    private JTextField languageTextField;
    private JButton addButton;
    private JButton removeButton;

    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(LanguageLearner.class).start();
        robot().waitForIdle();

        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }

    @Test
    public void shouldHaveAllComponents() {
        wordList = (JList) TestUtils.findComponent("wordList", true);
        wordTextField = (JTextField) TestUtils.findComponent("wordTextField", true);
        languageTextField = (JTextField) TestUtils.findComponent("languageTextField", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);

        assertNotNull(wordList, "No wordList found.");
        assertNotNull(wordTextField, "No wordTextField found.");
        assertNotNull(languageTextField, "No languageTextField found.");
        assertNotNull(addButton, "No addButton found.");
        assertNotNull(removeButton, "No removeButton found.");
    }

    @Test
    public void shouldBeAbleToAddWordToList() {
        wordList = (JList) TestUtils.findComponent("wordList", true);
        wordTextField = (JTextField) TestUtils.findComponent("wordTextField", true);
        languageTextField = (JTextField) TestUtils.findComponent("languageTextField", true);
        addButton = (JButton) TestUtils.findComponent("addButton", true);

        frame.textBox("wordTextField").setText("Salut");
        frame.textBox("languageTextField").setText("French");
        robot().click(addButton);
        robot().waitForIdle();

        String[] actualWordListItems = frame.list("wordList").contents();
        String actualWordListString = String.join(", ", actualWordListItems);

        assertEquals(actualWordListString, "Bonjour (French), Hello (English), Salut (French)");
    }

    @Test
    public void shouldBeAbleToRemoveWordFromList() {
        wordList = (JList) TestUtils.findComponent("wordList", true);
        removeButton = (JButton) TestUtils.findComponent("removeButton", true);

        frame.list("wordList").selectItem(0);
        robot().click(removeButton);
        robot().waitForIdle();

        String[] actualWordListItems = frame.list("wordList").contents();
        String actualWordListString = String.join(", ", actualWordListItems);

        assertEquals(actualWordListString, "Hello (English)");
    }

    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
