package com.codechum;
import com.codechum.swing.swingEventAdapters.MostFrequentCharacter;
import java.awt.Frame;
import static org.testng.Assert.*;

import static java.awt.event.KeyEvent.*;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.testng.annotations.*;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;

public class SwingMostFrequentCharacterTest extends AssertJSwingTestngTestCase {
    FrameFixture frame;

    EmergencyAbortListener listener;
    
    @Override
    protected void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
        application(MostFrequentCharacter.class).start();
        frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).withTimeout(10000).using(robot());
    }
    
    // Description: Should have all components `textArea` and `frequentCharLabel`.
    @Test
    public void shouldHaveAllComponents() {
        assertNotNull(frame.textBox("textArea"), "No textArea found.");
        assertNotNull(frame.label("frequentCharLabel"), "No frequentCharLabel found.");
    }
    
    // Description: Should display the correct frequent character in the `frequentCharLabel` when a text is entered in the `textArea`.
    @Test
    public void shouldDisplayCorrectFrequentChar() {
        boolean isLetterCorrect = false;
                
        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_N);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("N") ||
                frame.label("frequentCharLabel").text().equals("n");
        assertTrue(isLetterCorrect, "The displayed text should be 'N' or 'n'");
        

        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_E)
        .pressAndReleaseKeys(VK_V)
        .pressAndReleaseKeys(VK_E);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("E") ||
                frame.label("frequentCharLabel").text().equals("e");
        assertTrue(isLetterCorrect, "The displayed text should be 'E' or 'e'");
        
        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_R);

        assertTrue(isLetterCorrect, "The displayed text should be 'E' or 'e'");
    }
    
    // Description: Should not count the space as a frequent character in the `frequentCharLabel` when a text is entered in the `textArea`.
    @Test
    public void shouldDisplayCorrectFrequentCharWithSpace() {
        boolean isLetterCorrect = false;
                
        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_I);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("I") ||
                frame.label("frequentCharLabel").text().equals("i");
        assertTrue(isLetterCorrect, "The displayed text should be 'I' or 'i'");
        

        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_SPACE);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("I") ||
                frame.label("frequentCharLabel").text().equals("i");
        assertTrue(isLetterCorrect, "The displayed text should be 'I' or 'i'");
        
        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_A)
        .pressAndReleaseKeys(VK_SPACE);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("I") ||
                frame.label("frequentCharLabel").text().equals("i") ||
                frame.label("frequentCharLabel").text().equals("A") ||
                frame.label("frequentCharLabel").text().equals("a");
        assertTrue(isLetterCorrect, "The displayed text should be 'I' or 'i'");
        
        frame.textBox("textArea")
        .pressAndReleaseKeys(VK_A);
        
        isLetterCorrect = frame.label("frequentCharLabel").text().equals("A") ||
                frame.label("frequentCharLabel").text().equals("a");

        assertTrue(isLetterCorrect, "The displayed text should be 'A' or 'a'");
    }
    
    @AfterMethod
    public void tearDownAbortListener() {
        listener.unregister();
    }
}
